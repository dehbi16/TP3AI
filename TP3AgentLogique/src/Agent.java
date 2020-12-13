import java.util.ArrayList;
import java.util.List;

public class Agent {
	private int n;
	int[] positionActuelle = new int[2];
	private int etat; // 0 s'il est mort 1 s'il est vivant 2 s'il a gagné
	private Direction d;
	private Capteur capteur;
	private Effecteur effecteur;
	private Voisin[] voisins;

	private List<Effecteur> l1;
	private List<Effecteur> l2;
	private List<Effecteur> l3;
	private List<Effecteur> l4;
	private Case ancienCase;

	public Agent(int i, int j, int etat, Direction d, Capteur capteur, int n) {
		this.n = n;
		this.positionActuelle[0] = i;
		this.positionActuelle[1] = j;
		this.etat = etat;
		this.d = d;
		this.capteur = capteur;
		this.voisins = new Voisin[4];
	}

	public void tick() {
		if (this.capteur.isLumiere()) {
			this.etat = 2;
		}
		else {
			creerNewTableau(); 
			actionPossible();
			choisirAction();
			agir();

			if (capteur.isOdeur()==true) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agentM);
			if (capteur.isVent()==true) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agentV);
			if (capteur.isOdeur()==false && capteur.isVent()==false) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agent);
		}


	}

	private void agir() {
		if (effecteur.isArriere()) positionActuelle[0]+=1;
		if (effecteur.isAvant()) positionActuelle[0]-=1;
		if (effecteur.isGauche()) positionActuelle[1]-=1;
		if (effecteur.isDroite()) positionActuelle[1]+=1;

	}

	private void choisirAction() {
		// utiliser le tableau des voisins est créer la liste des actions possibles 
		// on ajoute les actions qui amene l'agent vers une case vide ou odeur ou vent ou visited ou la direction ou on peut tuer le monstre

		if (l1.size() <= 0 && l2.size() <= 0 && l3.size() <= 0 && l4.size() <= 0) {
			System.out.println("\nle robot n'a trouvé aucune action à faire\n");
		}
		else {
			int index;
			if(l1.size()>0) {
				this.effecteur = new Effecteur();
				index = (int) (Math.random()*(l1.size()));
				this.effecteur.setArriere(this.l1.get(index).isArriere());
				this.effecteur.setAvant(this.l1.get(index).isAvant());
				this.effecteur.setDroite(this.l1.get(index).isDroite());
				this.effecteur.setGauche(this.l1.get(index).isGauche());
				this.effecteur.setTirer(this.l1.get(index).isTirer());
			}
			else if (l2.size()>0) {
				this.effecteur = new Effecteur();
				index = (int) (Math.random()*(l2.size()));
				this.effecteur.setArriere(this.l2.get(index).isArriere());
				this.effecteur.setAvant(this.l2.get(index).isAvant());
				this.effecteur.setDroite(this.l2.get(index).isDroite());
				this.effecteur.setGauche(this.l2.get(index).isGauche());
				this.effecteur.setTirer(this.l2.get(index).isTirer());
			}
			else if (l3.size()>0) {
				this.effecteur = new Effecteur();
				index = (int) (Math.random()*(l3.size()));
				this.effecteur.setArriere(this.l3.get(index).isArriere());
				this.effecteur.setAvant(this.l3.get(index).isAvant());
				this.effecteur.setDroite(this.l3.get(index).isDroite());
				this.effecteur.setGauche(this.l3.get(index).isGauche());
				this.effecteur.setTirer(this.l3.get(index).isTirer());
			}
			else {
				this.effecteur = new Effecteur();
				index = (int) (Math.random()*(l4.size()));
				this.effecteur.setArriere(this.l4.get(index).isArriere());
				this.effecteur.setAvant(this.l4.get(index).isAvant());
				this.effecteur.setDroite(this.l4.get(index).isDroite());
				this.effecteur.setGauche(this.l4.get(index).isGauche());
				this.effecteur.setTirer(this.l4.get(index).isTirer());
			}



		}

		System.out.println("Effecteur = "+this.effecteur);
	}

	private void actionPossible() {
		// choisir une action la moins couteuse parmi les actions de la liste actionPossible 
		// l'ordre de préférence
		// 1) case non visitée et on est sur qu'elle est vide
		// 2) case non visitée mais on ne doute pas qu'il contient un monstre ou une crevasse 
		// 3) case vent case odeur 
		// 4) case  visitée
		// 5) tuer le monstre
		this.l1 = new ArrayList<Effecteur>();
		this.l2 = new ArrayList<Effecteur>();
		this.l3 = new ArrayList<Effecteur>();
		this.l4 = new ArrayList<Effecteur>();

		for(int i=0; i<4; i++) {
			if(voisins[i].isExiste() && voisins[i].isVide() && (voisins[i].isVisited()==false)) {
				Effecteur e = new Effecteur(Direction.Integer2Direction(i));
				l1.add(e);
			}
		}

		for(int i=0; i<4; i++) {
			if(voisins[i].isExiste() && voisins[i].isVisited()==false && voisins[i].isCrevasse()==false && voisins[i].isCrevasseP()==false && voisins[i].isMonstre()==false && voisins[i].isMonstreP()==false) {
				Effecteur e = new Effecteur(Direction.Integer2Direction(i));
				l2.add(e);
			}
		}

		for(int i=0; i<4; i++) {
			if(voisins[i].isExiste() && voisins[i].isVide() && voisins[i].isVisited()) {
				Effecteur e = new Effecteur(Direction.Integer2Direction(i));
				l3.add(e);
			}
		}


		for(int i=0; i<4; i++) {
			if(voisins[i].isExiste() && (voisins[i].isMonstre() || voisins[i].isMonstreP())) {
				Effecteur e = new Effecteur();
				e.setTirer(true);
				e.setdTire(Direction.Integer2Direction(i));
				l4.add(e);
			}
		}

	}

	private void creerNewTableau() {
		// cette fonction utilise le capteur et l'ancien effecteur et l'ancien tableau de voisin et elle rend le nouveau tableau 

		// Au tout début du jeu l'effecteur est null
		if (this.effecteur == null) {
			this.voisins = initVoisin();

		}
		else {
			Voisin[] newVoisin = new Voisin[4];

			newVoisin = initVoisin();

			// l'agent s'est déplacé vers le haut
			if(this.effecteur.isAvant()==true) {

				// Modification des parametres de la case voisine Haute
				if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) {

					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(true);
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(true);
					}
				}

				// Modification des parametres de la case voisine gauche
				if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(false);
						}
					}
					 
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}	
				} 

				// Modification des parametres de la case voisine Droite
				if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Droite)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Droite)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(false);
						}
					}
					
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}	
				}

				//Modification des parametres de la case voisine basse
				if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) {
					newVoisin[Direction.Direction2Integer(Direction.Bas)].setVisited(true);
					if(this.ancienCase.getEtat()==State.agent) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					if(this.ancienCase.getEtat()==State.agentM) newVoisin[Direction.Direction2Integer(Direction.Bas)].setOdeur(true);
					if(this.ancienCase.getEtat()==State.agentV) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVent(true);
				}

			}

			// l'agent s'est déplacé vers la droite
			if(this.effecteur.isDroite()==true) {
				// Modification des parametres de la case voisine Haute
				if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Haut)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Haut)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(false);
						}
					}
					
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}

				}

				//Modification des parametres de la case voisine gauche
				if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) {
					newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVisited(true);
					if(this.ancienCase.getEtat()==State.agent) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					if(this.ancienCase.getEtat()==State.agentM) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setOdeur(true);
					if(this.ancienCase.getEtat()==State.agentV) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVent(true);
				}

				//Modification des parametres de la case voisine droite
				if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(true);
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(true);
					}
				}

				// Modification des parametres de la case voisine basse
				if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Bas)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Bas)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(false);
						}
					}
					
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					}	
				} 

			}
			if(this.effecteur.isGauche()==true){
				// Modification des parametres de la case voisine Haute
				if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Haut)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Haut)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(false);
						}
					}
					
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					}
				}

				//Modification des parametres de la case voisine gauche
				if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(true);
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(true);
					}
				}

				//Modification des parametres de la case voisine droite
				if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) {
					newVoisin[Direction.Direction2Integer(Direction.Droite)].setVisited(true);
					if(this.ancienCase.getEtat()==State.agent) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					if(this.ancienCase.getEtat()==State.agentM) newVoisin[Direction.Direction2Integer(Direction.Droite)].setOdeur(true);
					if(this.ancienCase.getEtat()==State.agentV) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVent(true);
				}	

				// Modification des parametres de la case voisine basse
				if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Bas)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Bas)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(false);
						}
					}
					
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setVide(true);
					}	
				} 
			}
			if(this.effecteur.isArriere()==true) {
				//Modification des parametres de la case voisine haute
				if (newVoisin[Direction.Direction2Integer(Direction.Haut)].isExiste()) {
					newVoisin[Direction.Direction2Integer(Direction.Haut)].setVisited(true);
					if(this.ancienCase.getEtat()==State.agent) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVide(true);
					if(this.ancienCase.getEtat()==State.agentM) newVoisin[Direction.Direction2Integer(Direction.Haut)].setOdeur(true);
					if(this.ancienCase.getEtat()==State.agentV) newVoisin[Direction.Direction2Integer(Direction.Haut)].setVent(true);
				}

				// Modification des parametres de la case voisine gauche
				if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(false);
						}
					}
					
					 
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}	
				} 

				// Modification des parametres de la case voisine Droite
				if (newVoisin[Direction.Direction2Integer(Direction.Droite)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Droite)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(false);
						}
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(true);
						if(this.voisins[Direction.Direction2Integer(Direction.Droite)].isVisited()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==false) {
							newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(false);
						}
					}
					 
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstre(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);

					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasse(true);
						if (newVoisin[Direction.Direction2Integer(Direction.Gauche)].isExiste()) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setVide(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setVide(true);
					}	
				}

				// Modification des parametres de la case voisine basse
				if (newVoisin[Direction.Direction2Integer(Direction.Bas)].isExiste()) {
					if(capteur.isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(true);
					}
					if(capteur.isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(true);
					}
				}


			}
			if(this.effecteur.isTirer()==true) {
				this.voisins[Direction.Direction2Integer(d)].setMonstreP(false);
				this.voisins[Direction.Direction2Integer(d)].setMonstre(false);
				this.voisins[Direction.Direction2Integer(d)].setVide(true);
			}

			this.voisins = new Voisin[4];
			this.voisins = newVoisin;
			for(int i=0; i<4; i++) {
				if (newVoisin[i].isExiste()) {
					System.out.println(Direction.Integer2Direction(i)+" "+this.voisins[i]);
				}
			}

		}

	}

	private Voisin[] initVoisin() {
		// le but de cette fonction est de remplir le tableau voisins. Pour cela, on suit les instructions

		// 1) on ajoute les autres voisins de la case associée à l'agent. Il se peut que certaines cases n'existent pas si l'agent se trouvent dans les frontières
		//    dans ce cas tous les attributs de cette cas sont tous false.
		// 2) Pour les cases voisins qui existent vraiment, le seul attributs qui est true est l'attribut existe les autres sont tous false.
		Voisin[] newVoisin = new Voisin[4];
		for(int i=0; i<4; i++) {
			Voisin v = new Voisin();
			newVoisin[i] = v;
		}

		if (this.positionActuelle[0]==0) newVoisin[Direction.Direction2Integer(Direction.Haut)].setExiste(false);
		if (this.positionActuelle[0]==n-1) newVoisin[Direction.Direction2Integer(Direction.Bas)].setExiste(false);
		if (this.positionActuelle[1]==0) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setExiste(false);
		if (this.positionActuelle[1]==n-1) newVoisin[Direction.Direction2Integer(Direction.Droite)].setExiste(false);

		return newVoisin;
	}

	public void setCapteur(Capteur capteur) {

		this.capteur.setOdeur(capteur.isOdeur());
		this.capteur.setVent(capteur.isVent());
		this.capteur.setLumiere(capteur.isLumiere());



	}

	public int[] getPositionActuelle() {
		return positionActuelle;
	}

	public void setPositionActuelle(int[] positionActuelle) {
		this.positionActuelle = positionActuelle;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Direction getDirection() {
		return d;
	}

	public void setDirection(Direction d) {
		this.d = d;
	}

	public Effecteur getEffecteur() {

		return effecteur;
	}




}
