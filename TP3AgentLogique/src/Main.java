
public class Main {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		
		
		int n =4;
		int etatAgent = 1;
		long lStartTime = System.currentTimeMillis();
		Carte c = new Carte(n);
		Capteur capteur = new Capteur();
		Effecteur effecteur = new Effecteur();
		capteur.setCapteur(c.getCaseObservable());
		Agent agent = new Agent(c.getPositioni(), c.getPositionj(), etatAgent, Direction.Droite, null, n);



		
		
		/*
		System.out.println("la case haute est visitée "+newVoisin[Direction.Direction2Integer(Direction.Haut)].isVisited());
		System.out.println("la case haute odeur "+newVoisin[Direction.Direction2Integer(Direction.Haut)].isOdeur());
		System.out.println("la case haute vent "+newVoisin[Direction.Direction2Integer(Direction.Haut)].isVent());
		*/
		/*
		while(true) {
			agent.setCapteur(capteur);
			agent.tick();
			effecteur = agent.getEffecteur();
			
			c.setEffecteur(effecteur);
			c.tick();
			capteur.setCapteur(c.getCaseObservable());
		}
		
		*/
		
		/*
		 * l'agent observe l'environnement grâce au capteur
		 * on change l'effecteur
		 * l'environnement observe l'effecteur
		 * on change le capteur
		 */
		/*
		System.out.println();
		float seconds = (System.currentTimeMillis() - lStartTime) / 1000F;
		System.out.println("Opération effectuée en: "+ Float.toString(seconds) + " secondes.");
		*/
	}

	
	public static int [] ilyas(int[] L, int i, int x) {
		L[i] = x;
		return L;
	}
	
}
