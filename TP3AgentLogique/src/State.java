
public enum State {
	vide("0"),
	agent("1"),
	crevasse("2"),
	venteuse("3"),
	monstre("4"),
	mauvais("5"),
	sortie("6");
	
	private String name = "";

	State(String name){
		this.name = name;
	}

	public String toString() {
		return name; 
	}
}
