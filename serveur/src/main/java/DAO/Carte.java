package DAO;

import java.util.ArrayList;

public class Carte {
	private ArrayList<Plat> plats;

	public Carte(ArrayList<Plat> plats) {
		this.plats = plats;
	}

	public ArrayList<Plat> getPlats() {
		return plats;
	}

	public void setPlats(ArrayList<Plat> plats) {
		this.plats = plats;
	}

	public String toXml() {
		String res = "<plats>";
		for (int i = 0; i < this.plats.size(); i++)
			res += this.plats.get(i).toXml();
		res += "</plats>";
		return res;
	}
}
