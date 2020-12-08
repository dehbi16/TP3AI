
public class Carte {
	private int n;
	private Case [][] carte;
	private double pc = 0.075;
	private double pm = 0.08;
	private Case caseObservable;
	private Effecteur effecteur;
	private int positioni;
	private int positionj;

	public Carte(int n) {
		this.n = n;
		this.carte = new Case [n][n]; 
		init();
		generer();
		afficher();
		
	}
	
	public void tick() {
		
		if (effecteur.isArriere()) positioni++;
		if (effecteur.isAvant()) positioni--;
		if (effecteur.isDroite()) positionj++;
		if (effecteur.isGauche()) positionj--;
		// Ajouter si l'agent à tirer ou non
		setCaseObservable();
		
	}

	private void init() {
		for (int i=0; i<n; i++) 
			for(int j=0; j<n; j++) 
				carte[i][j] = new Case(i, j, false, State.vide);
	}
	
	private void afficher() {
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(carte[i][j].getEtat().toString()+" ");
			}
			System.out.println();
		}
	}

	void generer() {
		int i,j;
		int nbechecs = 0;
		int nbCrevasse = (int) ((n*n)*pc);
		System.out.println("nombre de crevasse "+nbCrevasse);
		int nbMonstre =  (int) ((n*n)*pm);

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
		}while(carte[i][j].getEtat()!=State.vide);
		carte[i][j].setEtat(State.agent);
		positioni = i;
		positionj = j;
		caseObservable = carte[i][j];
		

		// Ajouter une sortie
		do {
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
		}while(carte[i][j].getEtat()!=State.vide);
		carte[i][j].setEtat(State.sortie);
	}

	public int getPositioni() {
		return positioni;
	}
	

	public void setPositioni(int positioni) {
		this.positioni = positioni;
	}

	public int getPositionj() {
		return positionj;
	}

	public void setPositionj(int positionj) {
		this.positionj = positionj;
	}

	public Case getCaseObservable() {
		return caseObservable;
	}
	
	private void setCaseObservable() {
		this.caseObservable = carte[positioni][positionj];
		this.carte[positioni][positionj].setRevelee(true);
	}
	
	public void setEffecteur(Effecteur effecteur) {
		this.effecteur = effecteur;
	}

	private void ajouterM(int i, int j) {
		carte[i][j].setEtat(State.monstre);
		if (i!=0) carte[i-1][j].setEtat(State.mauvais);
		if (i!=n-1) carte[i+1][j].setEtat(State.mauvais);
		if (j!=0) carte[i][j-1].setEtat(State.mauvais);
		if (j!=n-1) carte[i][j+1].setEtat(State.mauvais);

	}

	private boolean ajouterMPossible(int i, int j) {
		
		if (carte[i][j].getEtat() != State.vide) return false;
		if (i!=0 && carte[i-1][j].getEtat()!=State.vide) return false;
		if (i!=n-1 && carte[i+1][j].getEtat()!=State.vide) return false;
		if (j!=0 && carte[i][j-1].getEtat()!=State.vide) return false;
		if (j!=n-1 && carte[i][j+1].getEtat()!=State.vide) return false;
		return true;
	}

	private void ajouterC(int i, int j) {
		carte[i][j].setEtat(State.crevasse); 
		if (i!=0) carte[i-1][j].setEtat( State.venteuse);
		if (i!=n-1) carte[i+1][j].setEtat( State.venteuse);
		if (j!=0) carte[i][j-1].setEtat( State.venteuse);
		if (j!=n-1) carte[i][j+1].setEtat( State.venteuse);
	}

	private boolean ajouterCPossible(int i, int j) {
		if (carte[i][j].getEtat() == State.vide) return true;
		return false;
	}


}
