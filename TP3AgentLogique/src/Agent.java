import java.util.List;

public class Agent {
	private int n;
	int[] positionActuelle = new int[2];
	private int etat; // 0 s'il est mort 1 s'il est vivant 2 s'il a gagné
	private Direction d;
	private Capteur capteur;
	private Effecteur effecteur;
	private Voisin[] voisins;
	private List<Effecteur> actionPossible; 
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
		creerNewTableau();
		actionPossible();
		choisirAction();
		// agir();
		if (capteur.isOdeur()==true) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agentM);
		if (capteur.isVent()==true) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agentV);
		if (capteur.isOdeur()==false && capteur.isVent()==false) ancienCase = new Case(positionActuelle[0], positionActuelle[1], true, State.agent);
	}

	private void choisirAction() {
		// utiliser le tableau des voisins est créer la liste des actions possibles 
	}

	private void actionPossible() {
		// choisir une action la moins couteuse parmi les actions de la liste actionPossible 


	}

	private void creerNewTableau() {
		// cette fonction utilise le capteur et l'ancien effecteur et l'ancien tableau de voisin et elle rend le nouveau tableau 

		// Au tout début du jeu l'effecteur est null
		if (this.effecteur == null) {
			this.voisins = initVoisin();
		}
		else {
			Voisin[] newVoisin = initVoisin();
			
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Haut)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Haut)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Bas)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Bas)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Gauche)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Gauche)].setCrevasse(true);
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
					if (capteur.isOdeur()==true || this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstreP(true);
					}
					if (capteur.isVent()==true || this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasseP(true);
					}
					if (capteur.isOdeur()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isOdeur()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setMonstre(true);
					}
					if (capteur.isVent()==true && this.voisins[Direction.Direction2Integer(Direction.Droite)].isVent()==true) {
						newVoisin[Direction.Direction2Integer(Direction.Droite)].setCrevasse(true);
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

		if (this.positionActuelle[0]!=0) newVoisin[Direction.Direction2Integer(Direction.Haut)].setExiste(true);
		if (this.positionActuelle[0]!=n-1) newVoisin[Direction.Direction2Integer(Direction.Bas)].setExiste(true);
		if (this.positionActuelle[1]!=0) newVoisin[Direction.Direction2Integer(Direction.Gauche)].setExiste(true);
		if (this.positionActuelle[1]!=n-1) newVoisin[Direction.Direction2Integer(Direction.Droite)].setExiste(true);

		return newVoisin;
	}

	public void setCapteur(Capteur capteur) {
		this.capteur = capteur;
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
