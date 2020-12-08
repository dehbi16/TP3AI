
public class Case {
	private int[] position = new int[2];
	private boolean revelee;
	private State etat;
	
	public Case(int i, int j, boolean revelee, State etat) {
		
		this.position[0] = i;		
		this.position[1] = j;
		this.revelee = revelee;
		this.etat = etat;
	}
	
	public int getI() {
		return this.position[0];
	}

	public int getJ() {
		return this.position[1];
	}
	
	public boolean isRevelee() {
		return revelee;
	}

	public void setRevelee(boolean revelee) {
		this.revelee = revelee;
	}

	public State getEtat() {
		return etat;
	}

	public void setEtat(State etat) {
		this.etat = etat;
	}
	
	 
}
