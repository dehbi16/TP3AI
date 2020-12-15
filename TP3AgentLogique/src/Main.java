import java.util.concurrent.TimeUnit;

public class Main {

	
	public static void main(String[] args) {
	
		int i=0;
		int fois = 30;
		int n = 3;
		int etatAgent = 1;
		
		while(true) {
			Carte c = new Carte(n, etatAgent);
			Capteur capteur = new Capteur();
			Effecteur effecteur = new Effecteur();
			capteur.setCapteur(c.getCaseObservable());
			Agent agent = new Agent(c.getPositioni(), c.getPositionj(), etatAgent, Direction.Droite, capteur, n);
			
			while(i<fois && c.isAlive()==true && agent.getEtat()==1) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				agent.tick();
				effecteur = agent.getEffecteur();
				c.setEffecteur(effecteur);
				c.tick();
				capteur.setCapteur(c.getCaseObservable()); 
				agent.setCapteur(capteur);
				i++;
			}
			if(agent.getEtat()==2) {
				n++;
			}
		}

		






		/*
		System.out.println("la case haute est visit�e "+newVoisin[Direction.Direction2Integer(Direction.Haut)].isVisited());
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
		 * l'agent observe l'environnement gr�ce au capteur
		 * on change l'effecteur
		 * l'environnement observe l'effecteur
		 * on change le capteur
		 */
		/*
		System.out.println();
		float seconds = (System.currentTimeMillis() - lStartTime) / 1000F;
		System.out.println("Op�ration effectu�e en: "+ Float.toString(seconds) + " secondes.");
		 */
	}




}
