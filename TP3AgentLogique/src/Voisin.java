
public class Voisin {
	private boolean existe;
	private boolean visited;
	private boolean odeur;
	private boolean vent;
	private boolean vide;
	private boolean monstreP;
	private boolean crevasseP;
	private boolean monstre;
	private boolean crevasse;
	
	public Voisin() {
		this.existe = true;
		this.visited = false;
		this.odeur = false;
		this.vent = false;
		this.vide = false;
		this.monstreP = false;
		this.crevasseP = false;
		this.monstre = false;
		this.crevasse = false;
	}

	@Override
	public String toString() {
		return "Voisin [existe=" + existe + ", visited=" + visited + ", odeur=" + odeur + ", vent=" + vent + ", vide="
				+ vide + ", monstreP=" + monstreP + ", crevasseP=" + crevasseP + ", monstre=" + monstre + ", crevasse="
				+ crevasse + "]";
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isOdeur() {
		return odeur;
	}

	public void setOdeur(boolean odeur) {
		this.odeur = odeur;
	}

	public boolean isVent() {
		return vent;
	}

	public void setVent(boolean vent) {
		this.vent = vent;
	}

	public boolean isVide() {
		return vide;
	}

	public void setVide(boolean vide) {
		this.vide = vide;
	}

	public boolean isMonstreP() {
		return monstreP;
	}

	public void setMonstreP(boolean monstreP) {
		this.monstreP = monstreP;
	}

	public boolean isCrevasseP() {
		return crevasseP;
	}

	public void setCrevasseP(boolean crevasseP) {
		this.crevasseP = crevasseP;
	}

	public boolean isMonstre() {
		return monstre;
	}

	public void setMonstre(boolean monstre) {
		this.monstre = monstre;
	}

	public boolean isCrevasse() {
		return crevasse;
	}

	public void setCrevasse(boolean crevasse) {
		this.crevasse = crevasse;
	}
}
