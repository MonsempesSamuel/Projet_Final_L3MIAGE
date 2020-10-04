package DAO;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
public abstract class DomDAO<T> implements DAO<T>{
	
	protected String nomFichier = "src/main/java/DAO/Carte.xml";
	protected Document doc;
	
	public DomDAO() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = docFactory.newDocumentBuilder();
	        this.doc = docBuilder.parse(nomFichier);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updatexml () {
		boolean res = false;
        try {
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        	Transformer transformer = transformerFactory.newTransformer();
        	DOMSource source = new DOMSource(doc);
        	StreamResult result = new StreamResult(new File(nomFichier));
			transformer.transform(source, result);
			res = true;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
	}
	
	public abstract boolean create(T obj);
	
	public abstract T read(int id);
	
	public abstract boolean update(T obj);
	
	public abstract boolean delete(T obj);
}
