import java.sql.*;

public class suppressionClient {
	
	public static void supprClient(Connection conn) {
		try {
	    	
			// Ici on veut ajouter un pilote dans la BD
	    	Statement requeteSupprClient= conn.createStatement();
	    	System.out.println("Statement Crée \n");
	    	//int idPSupp = LectureClavier.lireEntier("Choisir l'identifiant du client a supprimer : ") ;
	    	System.out.print("Identifiant Client : ");
	    	String idPSupp = LectureClavier.lireChaine() ;
	    	int reqSC = requeteSupprClient.executeUpdate
	    	(
	    	  "DELETE FROM MC_CLIENT WHERE id_client = '" + idPSupp +"'" 
	    	);
	    	
	    	// Une fois le pilote ajouter, la BD est mise a jour, 
	    	// ainsi on obtient un entier de retour pour dire que la mise a jour s'est effectuer
	    	System.out.println("MAJ = " + reqSC);
	    	
			///////////////////////////////////////////////////////////////////////////////////////////////////////
	    	// Cette requete, nous permet d'afficher les clients deja enregistrer 
	  	    Statement requeteClient = conn.createStatement();
	 		ResultSet resClient = requeteClient.executeQuery
	 		(
	 				"SELECT id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client From MC_CLIENT"
	 				
	 		) ;
	 		
	 		// On procede a l'affichage des clients de la sorte : id, nom, prenom, adresse, email, photo, fidelite
 			System.out.println("Liste des clients :\n");
	 		while(resClient.next()) {
	 			System.out.println("id client :"+ resClient.getString(1) + ",\t" + "nom client : " + resClient.getString(2) + ",\t" +"prenom client : " + resClient.getString(3) + ",  \t" + "adresse client : " + resClient.getString(4) + ",\t\t\t" + "email client : " + resClient.getString(5) + ",\t\t" + "photo client : " + resClient.getString(6) + ",\t\t" + "fidelite client : " + resClient.getString(7));
	 		}
	 		
	 		requeteSupprClient.close();
	 		requeteClient.close();
	    	resClient.close(); 	

			
	    	conn.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	}

}

