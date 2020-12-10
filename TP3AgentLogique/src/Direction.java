
public enum Direction {
	Haut,
	Gauche,
	Bas,
	Droite;
	
	public static Direction Integer2Direction(int x) {
        switch(x) {
        case 0:
            return Haut;
        case 1:
            return Gauche;
        case 2:
            return Droite;
        case 3:
            return Bas;    
        }
        return null;
    }
	
	public static int Direction2Integer(Direction x) {
        switch(x) {
        case Haut:
            return 0;
        case Gauche:
            return 1;
        case Droite:
            return 2;
        case Bas:
            return 3;    
        }
        return -1;
    }
	
	public static Direction Opposee(Direction x){
		switch(x) {
		case Haut:
            return Bas;
        case Droite:
            return Gauche;
        case Bas:
            return Haut;
        case Gauche:
            return Droite;     
        }
        return null;
    }
}
