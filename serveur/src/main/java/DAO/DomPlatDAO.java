package DAO;

import java.util.ArrayList;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomPlatDAO extends DomDAO<Plat> {

	public DomPlatDAO() {
		super();
	}

	@Override
	public boolean create(Plat obj) {
		boolean res = false;
		try {

			Node listPlats = doc.getElementsByTagName("plats").item(0);

			Element nodePlat = doc.createElement("plat");
			listPlats.appendChild(nodePlat);	

			Element idplat = doc.createElement("idPlat");
			idplat.appendChild(doc.createTextNode(obj.getId()));
			nodePlat.appendChild(idplat);
			
			Element nom = doc.createElement("nomPlat");
			nom.appendChild(doc.createTextNode(obj.getNom()));
			nodePlat.appendChild(nom);

			Element prix = doc.createElement("prixPlat");
			prix.appendChild(doc.createTextNode(Double.toString(obj.getPrix())));
			nodePlat.appendChild(prix);

			Element photo = doc.createElement("urlImagePlat");
			photo.appendChild(doc.createTextNode(obj.getPhoto()));
			nodePlat.appendChild(photo);

			Element type = doc.createElement("typePlat");
			type.appendChild(doc.createTextNode(obj.getType().toString()));
			nodePlat.appendChild(type);

			Element ingredients = doc.createElement("ingredients");
			for (int i = 0; i < obj.getIngredients().size(); i++) {
				Element ingredient = doc.createElement("ingredientPlat");
				ingredient.appendChild(doc.createTextNode(obj.getIngredients().get(i).toString()));
				ingredients.appendChild(ingredient);
			}
			nodePlat.appendChild(ingredients);
			res=this.updatexml();
		} finally {
		}
		return res;
	}

	@Override
	public Plat read(int id) {
		Plat p = null;
		NodeList iddoc = doc.getElementsByTagName("idPlat");
		NodeList nom = doc.getElementsByTagName("nomPlat");
		NodeList type = doc.getElementsByTagName("typePlat");
		NodeList prix = doc.getElementsByTagName("prixPlat");
		NodeList photo = doc.getElementsByTagName("urlImagePlat");
		NodeList Ingredients = doc.getElementsByTagName("ingredients");
		for (int i = 0; i < iddoc.getLength(); i++) {
			if (Integer.parseInt(iddoc.item(i).getTextContent()) == id) {
				Ingredients = ((Element) Ingredients.item(i)).getElementsByTagName("ingredientPlat");
				ArrayList<Ingredient> ingredientsjava = new ArrayList<Ingredient>();
				for (int j = 0; j < Ingredients.getLength(); j++) {
					Ingredient ing = null;
					switch (Ingredients.item(j).getTextContent()) {
					case "Chorizo":
						ing = Ingredient.Chorizo;
						break;
					case "Jambon":
						ing = Ingredient.Jambon;
						break;
					case "Mozzarella":
						ing = Ingredient.Mozzarella;
						break;
					case "Oeuf":
						ing = Ingredient.Oeuf;
						break;
					case "Oignon":
						ing = Ingredient.Oignon;
						break;
					case "Saumon":
						ing = Ingredient.Saumon;
						break;
					case "Thon":
						ing = Ingredient.Thon;
						break;
					case "Tomate":
						ing = Ingredient.Tomate;
						break;
					}

					ingredientsjava.add(ing);
				}
				TypeDePlat typeplat = null;
				switch (type.item(i).getTextContent()) {
				case "Boisson":
					typeplat = TypeDePlat.Boisson;
					break;
				case "Dessert":
					typeplat = TypeDePlat.Dessert;
					break;
				case "Entree":
					typeplat = TypeDePlat.Entree;
					break;
				case "Plat":
					typeplat = TypeDePlat.Plat;
					break;
				}
				double prixjava = Double.parseDouble(prix.item(i).getTextContent());
				String photojava = photo.item(i).getTextContent();
				String nomjava = nom.item(i).getTextContent();
				p = new Plat(String.valueOf(id), typeplat, prixjava, photojava, ingredientsjava, nomjava);
			}
		}
		return p;
	}

	@Override
	public boolean update(Plat obj) {
		return delete(obj) && create(obj);
	}

	@Override
	public boolean delete(Plat obj) {
		boolean res = false;
		try {
			NodeList iddoc = doc.getElementsByTagName("idPlat");
			for (int i = 0; i < iddoc.getLength(); i++) {
				if (iddoc.item(i).getTextContent().equals(obj.getId())) {
					doc.removeChild(iddoc.item(i));					
					res=this.updatexml();
				}
			}
		} finally {

		}
		return res;
	}

}
