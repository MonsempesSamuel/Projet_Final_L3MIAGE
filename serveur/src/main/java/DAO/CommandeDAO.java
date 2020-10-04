package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommandeDAO extends SqlDAO<Commande>{
	
	public CommandeDAO() throws SQLException{
		super();
	}
	
	@Override
	public boolean create(Commande obj) {
		boolean resul = true;
		try {
			Statement requete = super.connect.createStatement();
			ResultSet resultset = requete.executeQuery("SELECT id_commande FROM MC_COMMANDE ORDER BY id_commande DESC");
			resultset.next();
			obj.setId(Integer.toString(resultset.getInt("id_commande")+1));
			resultset.close();
			
			int retour = requete.executeUpdate("INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (" + obj.getId() + ", '" + obj.getIdClient() + "', TO_DATE('" + obj.getDate() + "', 'yyyy/mm/dd hh24:mi:ss'), '" + obj.getAdresseLivraison() + "',  " + obj.getPrix() + ")");
			if(retour == 0) {
				resul = false;
			}
			if(obj.getIdFilms().size() >= obj.getIdPlats().size()) {
				for(int i = 0; i < obj.getIdFilms().size(); i++) {
					ResultSet FilmExiste = requete.executeQuery("SELECT COUNT(id_film) AS nbFilm "
												+ "FROM MC_FILM "
												+ "WHERE id_film = " + obj.getIdPlats().get(i)  + "");
					FilmExiste.next();
					if(FilmExiste.getInt("nbFilm") != 1) {
						Statement requete2 = super.connect.createStatement();
						requete2.executeUpdate("INSERT INTO MC_FILM(id_film) VALUES ('" + obj.getIdFilms().get(i) + "')");
					}
					
					
					ResultSet valeur = requete.executeQuery("SELECT id_achat "
															+ "FROM MC_ACHAT "
															+ "ORDER BY id_achat DESC");
					valeur.next();
					
					Statement requete2 = super.connect.createStatement();
					int idAchat = valeur.getInt("id_achat") + 1;
					int retour2;
					
					if(obj.getIdPlats().size() > i) {
						ResultSet filmExiste = requete.executeQuery("SELECT COUNT(id_plat) AS nbPlat "
																	+ "FROM MC_PLAT "
																	+ "WHERE id_plat = " + obj.getIdPlats().get(i)  + "");
						filmExiste.next();
						if(filmExiste.getInt("nbPlat") != 1) {
							Statement requete3 = super.connect.createStatement();
							requete3.executeUpdate("INSERT INTO MC_PLAT(id_plat) VALUES ('" + obj.getIdPlats().get(i) + "')");
						}
						
						retour2 = requete2.executeUpdate("INSERT INTO MC_ACHAT(id_achat, id_film, id_plat, id_commande) VALUES ('" + idAchat + "', '" + obj.getIdFilms().get(i) + "', '" + obj.getIdPlats().get(i) + "', '" + obj.getId() + "')");
					}
					else {
						
						retour2 = requete2.executeUpdate("INSERT INTO MC_ACHAT(id_achat, id_film, id_plat, id_commande) VALUES ('" + idAchat + "', '" + obj.getIdFilms().get(i) + "', '', '" + obj.getId() + "')");
					}
					valeur.close();
					if(retour2 == 0) {
						resul = false;
					}
				}
			}
			else {
				for(int i = 0; i < obj.getIdPlats().size(); i++) {
					ResultSet filmExiste = requete.executeQuery("SELECT COUNT(id_plat) AS nbPlat "
							+ "FROM MC_PLAT "
							+ "WHERE id_plat = " + obj.getIdPlats().get(i) + "");
					filmExiste.next();
					if(filmExiste.getInt("nbPlat") != 1) {
						Statement requete3 = super.connect.createStatement();
						requete3.executeUpdate("INSERT INTO MC_PLAT(id_plat) VALUES ('" + obj.getIdPlats().get(i) + "')");
					}
					ResultSet valeur = requete.executeQuery("SELECT id_achat "
														+ "FROM MC_ACHAT "
														+ "ORDER BY id_achat DESC");
					valeur.next();
					
					Statement requete2 = super.connect.createStatement();
					int idAchat = valeur.getInt("id_achat") + 1;
					int retour2;
					
					if(obj.getIdFilms().size() > i) {
						ResultSet FilmExiste = requete.executeQuery("SELECT COUNT(id_film) AS nbFilm "
								+ "FROM MC_FILM "
								+ "WHERE id_film = " + obj.getIdPlats().get(i)  + "");
						FilmExiste.next();
						if(FilmExiste.getInt("nbFilm") != 1) {
							Statement requete3 = super.connect.createStatement();
							requete3.executeUpdate("INSERT INTO MC_FILM(id_film) VALUES ('" + obj.getIdFilms().get(i) + "')");
						}
						retour2 = requete2.executeUpdate("INSERT INTO MC_ACHAT(id_achat, id_film, id_plat, id_commande) VALUES('" + idAchat + "', '" + obj.getIdFilms().get(i) + "', '" + obj.getIdPlats().get(i) + "', '" + obj.getId() + "')");
					}
					else {
						retour2 = requete2.executeUpdate("INSERT INTO MC_ACHAT(id_achat, id_film, id_plat, id_commande) VALUES('" + idAchat + "', '', '" + obj.getIdPlats().get(i) + "', '" + obj.getId() + "')");
					}
					
					if(retour2 == 0) {
						resul = false;
					}
				}
			}
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	@Override
	public Commande read(int id) {
		Commande resul = null;
		try {
			Statement requete = super.connect.createStatement();
			ResultSet retour = requete.executeQuery("SELECT id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande "
													+ "FROM MC_COMMANDE "
													+ "INNER JOIN MC_CLIENT ON MC_CLIENT.id_client = MC_COMMANDE.id_client"
													+ "WHERE id_client = " + id + "");
			retour.next();
			resul = new Commande(retour.getString("id_commande"), retour.getString("date_heure_commande"), retour.getString("id_client"), new ArrayList<String>(), new ArrayList<String>(), retour.getDouble("prix_commande"), retour.getString("adresse_livraison")) ;
			requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}

	

	public int compte(int id) {
		int resul = 0;
		try {
			Statement requete = super.connect.createStatement();
			ResultSet retour = requete.executeQuery("count(SELECT id_commande)"
													+ "FROM MC_COMMANDE "
													+ "INNER JOIN MC_CLIENT ON MC_CLIENT.id_client = MC_COMMANDE.id_client"
													+ "WHERE id_client = " + id + "");
			retour.next();
			resul = retour.getInt(0);
			requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}
	
	public Commande read2(int id) {
		Commande resul = null;
		try {
			Statement requete = super.connect.createStatement();
			ResultSet retour = requete.executeQuery("SELECT id_commande, MC_COMMANDE.id_client, date_heure_commande, adresse_livraison, prix_commande FROM MC_COMMANDE INNER JOIN MC_CLIENT ON MC_CLIENT.id_client = MC_COMMANDE.id_client WHERE id_commande = "+ id );
			retour.next();
			resul = new Commande(retour.getString("id_commande"), retour.getString("date_heure_commande"), retour.getString("id_client"), new ArrayList<String>(), new ArrayList<String>(), retour.getDouble("prix_commande"), retour.getString("adresse_livraison")) ;
			requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resul;
	}

	@Override
	public boolean update(Commande obj) {
		boolean resul = false;
		try {
			Statement requete = super.connect.createStatement();
			int retour = requete.executeUpdate("UPDATE MC_COMMANDE"
												+ "SET id_client = '" + obj.getIdClient() + "', date_heure_commande = '" + obj.getDate() + "', adresse_livraison = '" + obj.getAdresseLivraison() + "', prix_commande = " + obj.getPrix() + ""
												+ "WHERE id_commande = '" + obj.getId() + "'");
			if(retour != 0) {
				resul = true;
			}
			
			retour = requete.executeUpdate("DELETE FROM MC_ACHAT WHERE id_commande = '" + obj.getId() +"'");
			if(retour != 0) {
				resul = true;
			}
			
			for(int i = 0; i < obj.getIdFilms().size(); i++) {
				ResultSet valeur = requete.executeQuery("SELECT id_achat"
														+ "FROM MC_ACHAT"
														+ "ORDER BY MC_ACHAT DESC"
														+ "LIMIT 1 ");
				valeur.next();
				
				Statement requete2 = super.connect.createStatement();
				int retour2 = requete2.executeUpdate("INSERT INTO MC_ACHAT(id_achat, id_film, id_plat, id_commande) VALUES('" + valeur.getInt("id_achat") + 1 + "', '" + obj.getIdFilms().get(i) + "', '" + obj.getIdPlats().get(i) + "', '" + obj.getId() + "')");
				
				if(retour2 == 0) {
					resul = false;
				}
			}
			
			requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resul;
	}
	
	@Override
	public boolean delete(Commande obj) {
		boolean resul = false;
		try {
			Statement requete = super.connect.createStatement();
			int retour = requete.executeUpdate("DELETE FROM MC_ACHAT WHERE id_commande = '" + obj.getId() +"'");
			if(retour != 0) {
				resul = true;
			}
			
			retour = requete.executeUpdate("DELETE FROM MC_COMMANDE WHERE id_commande = '" + obj.getId() +"'");
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
