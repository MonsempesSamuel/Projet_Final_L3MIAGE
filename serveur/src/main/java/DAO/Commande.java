package DAO;

import java.util.ArrayList;

public class Commande {
	private String id;
	private String date;
	private String idClient;
	private ArrayList<String> idPlats;
	private ArrayList<String> idFilms;
	private double prix;
	private String adresseLivraison;
	
	public Commande(String id, String date, String idClient, ArrayList<String> idPlats, ArrayList<String> idFilms,
			double prix, String adresseLivraison) {
		super();
		this.id = id;
		this.date = date;
		this.idClient = idClient;
		this.idPlats = idPlats;
		this.idFilms = idFilms;
		this.prix = prix;
		this.adresseLivraison = adresseLivraison;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getIdClient() {
		return idClient;
	}
	
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	public ArrayList<String> getIdPlats() {
		return idPlats;
	}
	
	public void setIdPlats(ArrayList<String> idPlats) {
		this.idPlats = idPlats;
	}
	
	public ArrayList<String> getIdFilms() {
		return idFilms;
	}
	
	public void setIdFilms(ArrayList<String> idFilms) {
		this.idFilms = idFilms;
	}
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public String getAdresseLivraison() {
		return adresseLivraison;
	}
	
	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}
	
	public String toXml() {
		String res = 
				"<Commande>" + 
				"    <idCommande>"+this.id+"</idCommande>" + 
				"       <date>"+this.date+"</date>" + 
				"       <idplats>";
		for(int i=0; i<this.idPlats.size();i++)
				res+="         <idplat>"+this.idPlats.get(i)+"</idplat>";
				res+="       </idplats>" + 
				"       <idfilms>"; 
		for(int i=0; i<this.idFilms.size();i++)
				res+="         <idfilm>"+this.idFilms.get(i)+"</idfilm>"; 
				res+="       </idfilms>        " + 
				"       <prix>"+this.prix+"</prix>  " + 
				"       <DerniereAdresse>"+this.adresseLivraison+"</DerniereAdresse>"+
				"</Commande>";
		return res ;
	}
}
