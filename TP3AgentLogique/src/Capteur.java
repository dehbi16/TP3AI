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
		if(c.getEtat()==State.mauvais) this.odeur=true;
		if(c.getEtat()==State.venteuse) this.Vent=true;
		if(c.getEtat()==State.sortie) this.Lumiere=true;
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
