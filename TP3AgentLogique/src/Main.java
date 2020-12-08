
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =10;
		int etatAgent = 1;
		long lStartTime = System.currentTimeMillis();
		Carte c = new Carte(n);
		Capteur capteur = new Capteur();
		capteur.setCapteur(c.getCaseObservable());
		
		Agent agent = new Agent(c.getPositioni(), c.getPositionj(), etatAgent, Direction.Droite, capteur);
		Effecteur effecteur = agent.getEffecteur();
		/*
		 * l'agent observe l'environnement grâce au capteur
		 * on change l'effecteur
		 * l'environnement observe l'effecteur
		 * on change le capteur
		 */
		
		System.out.println();
		float seconds = (System.currentTimeMillis() - lStartTime) / 1000F;
		System.out.println("Opération effectuée en: "+ Float.toString(seconds) + " secondes.");
	}

}
