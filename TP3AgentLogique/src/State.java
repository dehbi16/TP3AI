
public enum State {
	vide("0"),
	agent("1"),
	crevasse("2"),
	venteuse("3"),
	agentV("4"),
	monstre("5"),
	mauvais("6"),
	agentM("7"),
	sortie("8");
	
	private String name = "";

	State(String name){
		this.name = name;
	}

	public String toString() {
		return name; 
	}
}
