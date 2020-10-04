package DAO;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxDAO implements DAO<Plat>{

	protected SAXParser saxParser;
	private String nomFichier = "src/main/java/DAO/Carte.xml";
	
	public SaxDAO() {
        try {
        	SAXParserFactory factory = SAXParserFactory.newInstance();
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(Plat obj) {
		return false;
	}
	
	public Plat read(int id) {
		Plat P = new Plat();
		try {
			MyXMLHandler MXH =new MyXMLHandler(P,id);
			saxParser.parse(nomFichier, MXH);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return P;
	}
	
	public boolean update(Plat obj) {
		return false;
	}
	
	public boolean delete(Plat obj) {
		return false;
	}
	
}
