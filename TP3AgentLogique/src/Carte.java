
public class Carte {
	private int n;
	private State [][] carte;
	private double pc = 0.075;
	private double pm = 0.08;
	private int positionx;
	private int positiony;

	public Carte(int n) {
		this.n = n;
		this.carte = new State [n][n]; 
		init();
		generer();

		afficher();
		
	}

	private void init() {
		for (int i=0; i<n; i++) 
			for(int j=0; j<n; j++) 
				carte[i][j] = State.vide;
	}
	
	private void afficher() {
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(carte[i][j].toString()+" ");
			}
			System.out.println();
		}
	}

	void generer() {
		int i,j;
		int nbechecs = 0;
		int nbCrevasse = (int) ((n*n)*pc);
		System.out.println("nbcrevasse "+nbCrevasse);
		int nbMonstre =  (int) ((n*n)*pm);
		System.out.println("nb monstres "+nbMonstre);

		int count = 0;
		// Ajouter les crevasses
		do {
			
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
			if (ajouterCPossible(i,j)) {
				count++;
				ajouterC(i,j);
				//System.out.println("crevasse ajoutée");
			}
			
		}while(count<nbCrevasse );

		// Ajouter des monstres
		count=0;
		do {
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
			if (ajouterMPossible(i,j)) {
				count++;
				ajouterM(i,j);
				//System.out.println("monstre ajouté");
				nbechecs = 0;
			}
			else nbechecs++;
		}while(count<nbMonstre && nbechecs<(n*n - 5*nbCrevasse)); 
		System.out.println("nombre de monstre "+count);
		
		// Ajouter l'agent
		do {
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
		}while(carte[i][j]!=State.vide);
		carte[i][j]=State.agent;
		positionx = j; positiony = i;

		// Ajouter une sortie
		do {
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
		}while(carte[i][j]!=State.vide);
		carte[i][j]=State.sortie;
	}

	private void ajouterM(int i, int j) {
		carte[i][j] = State.monstre;
		if (i!=0) carte[i-1][j] = State.mauvais;
		if (i!=n-1) carte[i+1][j] = State.mauvais;
		if (j!=0) carte[i][j-1] = State.mauvais;
		if (j!=n-1) carte[i][j+1] = State.mauvais;

	}

	private boolean ajouterMPossible(int i, int j) {
		
		if (carte[i][j] != State.vide) return false;
		if (i!=0 && carte[i-1][j]!=State.vide) return false;
		if (i!=n-1 && carte[i+1][j]!=State.vide) return false;
		if (j!=0 && carte[i][j-1]!=State.vide) return false;
		if (j!=n-1 && carte[i][j+1]!=State.vide) return false;
		return true;
	}

	private void ajouterC(int i, int j) {
		carte[i][j] = State.crevasse;
		if (i!=0) carte[i-1][j] = State.venteuse;
		if (i!=n-1) carte[i+1][j] = State.venteuse;
		if (j!=0) carte[i][j-1] = State.venteuse;
		if (j!=n-1) carte[i][j+1] = State.venteuse;
	}

	private boolean ajouterCPossible(int i, int j) {
		if (carte[i][j] == State.vide) return true;
		return false;
	}


}
