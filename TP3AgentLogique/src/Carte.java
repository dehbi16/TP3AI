
public class Carte {
	private int n;
	private Case [][] carte;
	private double pc = 0.075;
	private double pm = 0.08;
	private Case caseObservable ;
	private Effecteur effecteur;
	private int positioni;
	private int positionj;
	private int etatAgent;

	public Carte(int n, int etatAgent) {
		this.n = n;
		this.carte = new Case [n][n]; 
		this.etatAgent = etatAgent;
		init();
		generer();
		afficher();
		
	}
	
	public void tick() {
		if(effecteur.isTirer()) {
			Direction d = effecteur.getdTire();
			if(d==Direction.Haut && carte[positioni-1][positionj].getEtat()==State.monstre) carte[positioni-1][positionj].setEtat(State.vide);
			if(d==Direction.Bas && carte[positioni+1][positionj].getEtat()==State.monstre) carte[positioni+1][positionj].setEtat(State.vide);
			if(d==Direction.Gauche && carte[positioni][positionj-1].getEtat()==State.monstre) carte[positioni][positionj-1].setEtat(State.vide);
			if(d==Direction.Droite && carte[positioni][positionj+1].getEtat()==State.monstre) carte[positioni][positionj+1].setEtat(State.vide);
		}
		else {
			enleverAgent(positioni, positionj);
			if (effecteur.isArriere()) positioni++;
			if (effecteur.isAvant()) positioni--;
			if (effecteur.isDroite()) positionj++;
			if (effecteur.isGauche()) positionj--;
			if (this.carte[positioni][positionj].getEtat() == State.monstre || this.carte[positioni][positionj].getEtat() == State.crevasse) this.etatAgent=0;
			ajouterAgent(positioni, positionj);
		}
		
		


		setCaseObservable();
		afficher();
		
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

	public boolean isAlive() {
		if (this.etatAgent == 0) return false;
		return true;
	}

	private void generer() {
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
		caseObservable = new Case(i, j, true, State.agent);
		

		// Ajouter une sortie
		do {
			i = (int)(Math.random()*n+1) - 1;
			j = (int)(Math.random()*n+1) - 1;
		}while(carte[i][j].getEtat()!=State.vide);
		carte[i][j].setEtat(State.sortie);
	}
	
	private void enleverAgent(int i, int j) {
		if (carte[i][j].getEtat()==State.agent) carte[i][j].setEtat(State.vide);
		if (carte[i][j].getEtat()==State.agentM) carte[i][j].setEtat(State.mauvais);
		if (carte[i][j].getEtat()==State.agentV) carte[i][j].setEtat(State.venteuse);
	}
	
	private void ajouterAgent(int i, int j) {
		if (carte[i][j].getEtat()==State.vide) carte[i][j].setEtat(State.agent);
		if (carte[i][j].getEtat()==State.mauvais) carte[i][j].setEtat(State.agentM);
		if (carte[i][j].getEtat()==State.venteuse) carte[i][j].setEtat(State.agentV);
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
