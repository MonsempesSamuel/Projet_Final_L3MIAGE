package DAO;

public enum TypeDePlat {
	Entree("Entree"),
	Plat("Plat"),
	Dessert("Dessert"),
	Boisson("Boisson");
	
	private String name = "";
	   
	//Constructeur
	TypeDePlat(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
}
