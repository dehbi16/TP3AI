import java.util.List;

public class Agent {
	private int n;
	int[] positionActuelle = new int[2];
	private int etat; // 0 s'il est mort 1 s'il est vivant 2 s'il a gagné
	private Direction d;
	private Capteur capteur;
	private Effecteur effecteur;
	private Voisin[][] voisins;
	private List<Effecteur> actionPossible; 
	
	public Agent(int i, int j, int etat, Direction d, Capteur capteur, int n) {
		this.n = n;
		this.positionActuelle[0] = i;
		this.positionActuelle[1] = j;
		this.etat = etat;
		this.d = d;
		this.capteur = capteur;
		this.voisins = new Voisin[3][3];
	}

	public void tick() {
		creerNewTableau();
		actionPossible();
		choisirAction();

		
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
			initVoisin();
		}
		else {
			Voisin[][] newVoisin = new Voisin[3][3];
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					newVoisin[i][j] = new Voisin();
				}
			}
			
		}
	}

	private void initVoisin() {
		// le but de cette fonction est de remplir le tableau voisins. Pour cela, on suit les instructions
		// 1) la case qui représente l'agent se trouve dans le milieu du tableau voisins (position(1,1)). Cette case a les propriétés suivantes : 
		//    existe,visited,vide sont true et les autres paramétres sont falses;
		// 2) on ajoute les autres voisins de la case associée à l'agent. Il se peut que certaines cases n'existent pas si l'agent se trouvent dans les frontières
		//    dans ce cas tous les attributs de cette cas sont tous false.
		// 3) Pour les cases voisins qui existent vraiment, le seul attributs qui est true est l'attribut existe les autres sont tous false.
		Voisin[][] newVoisin = new Voisin[3][3];
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; i++) {
				Voisin v = new Voisin();
				if (i==1 && j==1) {
					v.setExiste(true);
					v.setVisited(true);
					v.setVide(true);
				}
				else {
					v.setExiste(true);
				}
				newVoisin[i][j] = v;
			}
		}
		if (this.positionActuelle[0]==0) {
			for(int j=0; j<3; j++)newVoisin[0][j].setExiste(false);
		}
		if (this.positionActuelle[0]==n-1){
			for(int j=0; j<3; j++)newVoisin[2][j].setExiste(false);
		}
		if (this.positionActuelle[1]==0){
			for(int i=0; i<3; i++)newVoisin[i][0].setExiste(false);
		}
		if (this.positionActuelle[1]==n-1) {
			for(int i=0; i<3; i++)newVoisin[i][2].setExiste(false);
		}
		
		this.voisins = newVoisin;
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
