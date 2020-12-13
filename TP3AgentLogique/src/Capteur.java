import java.util.ArrayList;
import java.util.List;

public class Capteur {
	
	private boolean odeur;
	private boolean Vent;
	private boolean Lumiere;
	
	public Capteur() {
		this.odeur = false;
		this.Vent = false;
		this.Lumiere = false;
	}
	
	public void setCapteur(Case c) {
		
		this.odeur = false;
		this.Vent = false;
		this.Lumiere = false;
		if(c.getEtat()==State.mauvais || c.getEtat()==State.agentM) this.odeur=true;
		if(c.getEtat()==State.venteuse || c.getEtat()==State.agentV) this.Vent=true;
		if(c.getEtat()==State.sortie) this.Lumiere=true;
	}
	

	public void setOdeur(boolean odeur) {
		this.odeur = odeur;
	}
	
	public void setVent(boolean vent) {
		this.Vent = vent;
	}
	
	public void setLumiere(boolean lumiere) {
		this.Lumiere = lumiere;
	}

	@Override
	public String toString() {
		return "Capteur [odeur=" + odeur + ", Vent=" + Vent + ", Lumiere=" + Lumiere + "]";
	}

	public boolean isOdeur() {
		return odeur;
	}

	public boolean isVent() {
		return Vent;
	}

	public boolean isLumiere() {
		return Lumiere;
	}

	
	
	
	
	
	
}
