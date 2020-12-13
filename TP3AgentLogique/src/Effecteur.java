
public class Effecteur {
	
	private boolean Gauche;
	private boolean Droite;
	private boolean Avant;
	private boolean Arriere;
	private boolean Tirer;
	private boolean Sortir;
	private Direction dTire;
	
	public Effecteur() {
		this.Gauche = false;
		this.Droite = false;
		this.Avant = false;
		this.Arriere = false;
		this.Tirer = false;
		this.Sortir = false;		
	}
	
	public Effecteur(Direction d) {
		this.Gauche = false;
		this.Droite = false;
		this.Avant = false;
		this.Arriere = false;
		this.Tirer = false;
		this.Sortir = false;
		if (d == Direction.Bas) this.Arriere = true;
		if (d == Direction.Haut) this.Avant = true;
		if (d == Direction.Gauche) this.Gauche = true;
		if (d == Direction.Droite) this.Droite = true;
	}

	public boolean Comparer(Effecteur e) {
		if (this.Arriere == true && e.Avant == true) return false;
		if (this.Avant == true && e.Arriere == true) return false;
		if (this.Gauche == true && e.Droite == true) return false;
		if (this.Droite == true && e.Gauche == true) return false;
		return true;
	}
	
	public String toString() {
		if (this.Arriere) return "Bas";
		if (this.Avant) return "Haut";
		if (this.Droite) return "droite";
		if (this.Gauche) return "gauche";
		return "Tirer";
		
	}

	public Direction getdTire() {
		return dTire;
	}

	public void setdTire(Direction dTire) {
		this.dTire = dTire;
	}

	public boolean isGauche() {
		return Gauche;
	}

	public void setGauche(boolean gauche) {
		Gauche = gauche;
	}

	public boolean isDroite() {
		return Droite;
	}

	public void setDroite(boolean droite) {
		Droite = droite;
	}

	public boolean isAvant() {
		return Avant;
	}

	public void setAvant(boolean avant) {
		Avant = avant;
	}

	public boolean isArriere() {
		return Arriere;
	}

	public void setArriere(boolean arriere) {
		Arriere = arriere;
	}

	public boolean isTirer() {
		return Tirer;
	}

	public void setTirer(boolean tirer) {
		Tirer = tirer;
	}

	public boolean isSortir() {
		return Sortir;
	}

	public void setSortir(boolean sortir) {
		Sortir = sortir;
	}
	
	
	
}
