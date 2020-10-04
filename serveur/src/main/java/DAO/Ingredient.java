package DAO;

public enum Ingredient {
	Tomate("Tomate"),
	Mozzarella("Mozzarella"),
	Oignon("Oignon"),
	Chorizo("Chorizo"),
	Thon("Thon"),
	Saumon("Saumon"),
	Jambon("Jambon"),
	Oeuf("Oeuf");
	
	private String name = "";
	   
	//Constructeur
	Ingredient(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}		
}
