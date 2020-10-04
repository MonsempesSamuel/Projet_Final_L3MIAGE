package DAO;

public class Client {
	private String id;
	private String nom;
	private String prenom;
	private String photo;
	private String email;
	private String tel;
	private String adresse;	
	
	public Client(String id, String nom, String prenom, String photo, String email, String tel, String adresse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.photo = photo;
		this.email = email;
		this.tel = tel;
		this.adresse = adresse;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String toXml() {
		String res = "<Client>" + 
				"    <idClient>"+this.id+"</idClient>" + 
				"    <Nom>"+this.nom+"</Nom>" + 
				"    <Prénom>"+this.prenom+"</Prénom>" + 
				"    <Photo>"+this.photo+"</Photo>" + 
				"    <Mail>"+this.email+"</Mail>" + 
				"    <Tel>"+this.tel+"</Tel>" + 
				"    <NbTotalCommande></NbTotalCommande>" + 
				"</Client>" + 
				"";
		return res ;
	}
}
