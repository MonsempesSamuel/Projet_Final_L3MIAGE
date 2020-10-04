import java.sql.*;

public class procedureClient {
	
	public static void deleteProcClient(Connection conn) {
		try {
	    	
	    	// Décalaration des statements 
	    	Statement reqProcedureStockSuppReserve= conn.createStatement();
	    	
	    	// Cette requete, nous permet d'afficher les clients 
	  	    Statement requeteAfficheClient = conn.createStatement();
	 		ResultSet resClient = requeteAfficheClient.executeQuery
	 		(
	 				"SELECT id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client From MC_CLIENT"
	 				
	 		) ;
	 		
	 		// On procede a l'affichage des clients avec une reservation de la sorte : id, nom, prenom
 			System.out.println("Liste des clients avec une reservation:\n");
	 		while(resClient.next()) {
	 			System.out.println("id client :"+ resClient.getString(1) + ",\t" + "nom client : " + resClient.getString(2) + ",\t" +"prenom client : " + resClient.getString(3) + ",  \t" + "adresse client : " + resClient.getString(4) + ",\t\t\t" + "email client : " + resClient.getString(5) + ",\t\t" + "photo client : " + resClient.getString(6) + ",\t\t" + "fidelite client : " + resClient.getString(7));
	 		}
	    	
	    	System.out.print("Choisir l'identifiant du client à supprimer : ");
	    	String idCSupp = LectureClavier.lireChaine() ;

	    	// Appel de la procedure
	    	String sql = "{CALL delete_client(?)}";
	    	CallableStatement call = conn.prepareCall(sql); 
	    	
	    	// passage de l'entier idCSupp récupérer comme valeur du premier paramètre
	    	call.setString(1, idCSupp);
	    	
	    	// exécution 
	    	call.execute();
	    	
	    	System.out.println("Suppressions du client d'id :" + idCSupp);
	    	
	    	// Fermeture 
	    	reqProcedureStockSuppReserve.close();
	    	call.close();
	    	
	    	requeteAfficheClient.close();
	    	resClient.close();
	    	conn.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

	}

}
