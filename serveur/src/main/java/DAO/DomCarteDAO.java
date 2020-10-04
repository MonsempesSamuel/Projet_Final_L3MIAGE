package DAO;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class DomCarteDAO extends DomDAO<Carte> {

	public DomCarteDAO() {
		super();
	}

	@Override
	public boolean create(Carte obj) {
		boolean res = false;
		try {

			if (doc.getElementsByTagName("carte").getLength() == 0) {
				res = true;
				Element carte = doc.createElement("carte");
				Element plats = doc.createElement("plats");				
				doc.appendChild(carte);
				carte.appendChild(plats);
				res=this.updatexml();
				ArrayList<Plat> listeplat = obj.getPlats();
				for (int i = 0; i < listeplat.size(); i++) {
					DomPlatDAO aux = new DomPlatDAO();
					res = aux.create(listeplat.get(i)) && res;
				}
			}
		} finally {
		}
		return res;
	}

	@Override
	public Carte read(int id) {
		NodeList iddoc = doc.getElementsByTagName("idPlat");
		ArrayList<Plat> plats = new ArrayList<Plat>();
		for (int i = 0; i < iddoc.getLength(); i++) {
			DomPlatDAO aux = new DomPlatDAO();
			int idplat = Integer.parseInt(iddoc.item(i).getTextContent());
			plats.add(aux.read(idplat));
		}
		Carte res = new Carte(plats);
		return res;
	}

	@Override
	public boolean update(Carte obj) {
		return delete(obj) && create(obj);
	}

	@Override
	public boolean delete(Carte obj) {
		boolean res = false;
		try {

			NodeList carte = doc.getElementsByTagName("carte");
			if (carte.getLength() == 1) {
				doc.removeChild(carte.item(0));
				res=this.updatexml();
			}
		} finally {
		}
		return res;
	}

}
