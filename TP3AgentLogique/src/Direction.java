
public enum Direction {
	Haut,
	Droite,
	Bas,
	Gauche;
	
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
