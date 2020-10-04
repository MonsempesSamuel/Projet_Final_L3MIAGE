package DAO;

import java.util.ArrayList;

public class Plat {
	private String id;
	private TypeDePlat type;
	private double prix;
	private String photo;
	private String nom;
	private ArrayList<Ingredient> ingredients;
	
	public Plat(String id, TypeDePlat type, double prix, String photo, ArrayList<Ingredient> ingredients, String nom) {
		this.id = id;
		this.type = type;
		this.prix = prix;
		this.photo = photo;
		this.ingredients = ingredients;
		this.nom = nom;
	}

	public Plat() {
	}

	public String getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TypeDePlat getType() {
		return type;
	}

	public void setType(TypeDePlat type) {
		this.type = type;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Plat [id=" + id + ", type=" + type + ", prix=" + prix + ", photo=" + photo + ", nom=" + nom
				+ ", ingredients=" + ingredients + "]";
	}

	public String toXml() {
		String res =
				"   <plat>" + 
				"    <idPlat>"+this.id+"</idPlat>" + 
				"    <nomPlat>"+this.nom+"</nomPlat>" + 
				"    <prixPlat>"+this.prix+"</prixPlat>" + 
				"    <urlImagePlat>"+this.photo+"</urlImagePlat>" + 
				"    <typePlat>"+this.type.toString()+"</typePlat>" + 
				"    <ingredients>";
		for(int i = 0; i <this.ingredients.size(); i++)
				res+="         <ingredientPlat>"+this.ingredients.get(i).toString()+"</ingredientPlat>";
				res+="     </ingredients>" + 
				"   </plat>";
		return res;
	}
	
}
