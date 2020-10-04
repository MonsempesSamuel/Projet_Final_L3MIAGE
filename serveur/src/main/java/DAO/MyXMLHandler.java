package DAO;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXMLHandler extends DefaultHandler {

	private Plat P;
	private int id;
	private boolean Id;
	private boolean bonId;
	private boolean prix;

	// début du parsing
	public MyXMLHandler(Plat P, int id) {
		this.P = P;
		this.id = id;
	}

	public void startDocument() throws SAXException {
		System.out.println("Début du parsing");
	}

	public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {
		if (qname.equals("idPlat")) {
			this.Id = true;
		}
		if (qname.equals("prixPlat")) {
			this.prix = true;
		}
		if (qname.equals("plat")) {
			this.Id = false;
			this.bonId = false;
			this.prix = false;
		}
	}

	public boolean contientchiffre(String str) {
		char[] cs = str.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			if (!Character.isDigit(cs[i]) && cs[i] != '.')
				return false;
		}
		return true;
	}

	public void characters(char[] data, int start, int end) {
		String str = new String(data, start, end);
		if (contientchiffre(str)) {
			if (bonId && prix) {
				P.setPrix(Double.parseDouble(str));
			} else if (Id && id == Integer.parseInt(str) && !str.equals("/n")) {
				this.bonId = true;
			}
		}
	}

	// fin du parsing
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
	}
}