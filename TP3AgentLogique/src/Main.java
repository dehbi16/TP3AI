
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n =10;
		long lStartTime = System.currentTimeMillis();
		Carte c = new Carte(n);
		System.out.println();
		float seconds = (System.currentTimeMillis() - lStartTime) / 1000F;
		System.out.println("Op�ration effectu�e en: "+ Float.toString(seconds) + " secondes.");
	}

}
