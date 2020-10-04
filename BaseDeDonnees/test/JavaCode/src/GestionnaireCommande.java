import java.sql.*;

public class GestionnaireCommande {
	
	public static void HistoriqueCommande(Connection conn) {
		try {
	    	
	    	// Cette requete, nous permet d'afficher les informations sur les commandes 
	  	    Statement requeteCommande = conn.createStatement();
	 		ResultSet resCommande = requeteCommande.executeQuery
	 		(
	 				"SELECT id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande From MC_COMMANDE"
	 				
	 		) ;
	 		
	 		// On procede a l'affichage des commandes de la sorte : id comm, id client, date, heure, adr, prix
 			System.out.println("Affichages des commandes :\n");
	 		while(resCommande.next()) {
	 			System.out.println("id comm: " + resCommande.getString(1) + ",\t" + "id client: " + resCommande.getString(2) + ",\t"  + "date et heure comm: " + resCommande.getString(3) + ",\t" + "adr livr: " + resCommande.getString(4) + ",\t\t" + "prix commande: " + resCommande.getString(5));
	 		}
	    	
	 		requeteCommande.close();
	 		resCommande.close();
	    	conn.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}
