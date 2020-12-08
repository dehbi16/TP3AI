
public class Agent {
	int[] positionActuelle = new int[2];
	private int etat; // 0 s'il est mort 1 s'il est vivant 2 s'il a gagné
	private Direction d;
	private Capteur capteur;
	private Effecteur effecteur;
	
	public Agent(int i, int j, int etat, Direction d, Capteur capteur) {
		this.positionActuelle[0] = i;
		this.positionActuelle[1] = j;
		this.etat = etat;
		this.d = d;
		this.capteur = capteur;
	}
	
	public void tick() {
		// observer le capteur
		// décider 
		// changer l'effecteur
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
