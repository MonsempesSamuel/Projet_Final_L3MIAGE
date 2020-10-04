package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDAO extends SqlDAO<Client>{

	public ClientDAO() throws SQLException {
		super();
	}
	
	@Override
	public boolean create(Client obj) {
		boolean resul = false;
		try {
			Statement requete = super.connect.createStatement();
			int retour = requete.executeUpdate("INSERT INTO MC_Client (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES ('"+ obj.getId() +"', '" + obj.getNom() + "', '" + obj.getPrenom() + "', '" + obj.getAdresse() + "', '" + obj.getEmail() + "', '" + obj.getPhoto() + "', 0)");
			if(retour != 0) {
				resul = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	@Override
	public Client read(int id) {
		Client resul = null;
		try {
			Statement requete = super.connect.createStatement();
			ResultSet retour = requete.executeQuery("SELECT id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client "
												+ "FROM MC_Client "
												+ "WHERE id_client = " + id + "");
			retour.next();
			resul = new Client(retour.getString("id_client"), retour.getString("nom_client"), retour.getString("prenom_client"), retour.getString("adresse_client"), retour.getString("email_client"), null, null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}
	
	@Override
	public boolean update(Client obj) {
		boolean resul = false;
		try {
			Statement requete = super.connect.createStatement();
			int retour = requete.executeUpdate("UPDATE MC_CLIENT "
											+ "SET id_client = " + obj.getId() + ", nom_client = '" + obj.getNom() + "', prenom_client = '" + obj.getPrenom() + "', adresse_client = '" + obj.getAdresse() + "', email_client = '" + obj.getEmail() + "', photo_client = '" + obj.getPhoto() + "', fidelite_client = 0 "
											+ "WHERE id_client = " + obj.getId() + "");
			if(retour != 0) { 
				resul = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	@Override
	public boolean delete(Client obj) {
		boolean resul = false;
		try {
			Statement requete = super.connect.createStatement();
			int retour = requete.executeUpdate("DELETE FROM MC_CLIENT WHERE id_client = '" + obj.getId() +"'");
			if(retour != 0) {
				resul = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}

}
