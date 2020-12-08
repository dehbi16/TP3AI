
public class Effecteur {
	
	private boolean Gauche;
	private boolean Droite;
	private boolean Avant;
	private boolean Arriere;
	private boolean Tirer;
	private boolean Sortir;
	
	public Effecteur() {
		this.Gauche = false;
		this.Droite = false;
		this.Avant = false;
		this.Arriere = false;
		this.Tirer = false;
		this.Sortir = false;		
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
