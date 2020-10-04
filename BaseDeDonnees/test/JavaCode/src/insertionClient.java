import java.sql.*;


public class insertionClient {
	
	public static void inserClient(Connection conn) {
		try {
	    	
			// Ici on veut ajouter un pilote dans la BD
	    	Statement requeteAjoutClient= conn.createStatement();
	    	System.out.println("Statement Crée \n");
	    	
	    	System.out.println("Ajouter un nouveau Client");
	    	
	    	//int idC = LectureClavier.lireEntier("Identifiant Client : ") ;
	    	System.out.print("Identifiant Client : ");
	    	String idC = LectureClavier.lireChaine() ;
	    	
	    	System.out.print("Votre Nom : ");
	    	String nomC = LectureClavier.lireChaine() ;
	    	
	    	System.out.print("Votre Prenom : ");
	    	String prenomC = LectureClavier.lireChaine() ;
	    	
	    	System.out.print("Votre Adresse : ");
	    	String adrC = LectureClavier.lireChaine() ;
	    	
	    	System.out.print("Votre email : ");
	    	String emailC = LectureClavier.lireChaine() ;
	    	
	    	System.out.print("Votre photo: ");
	    	String photoC = LectureClavier.lireChaine() ;
	    	
	    	int fidelC = LectureClavier.lireEntier("Fidelite: ") ;
	    	
	    	// La requete suivante permet d'insere dans la table PILOTE 
	    	// les valeurs entrer par l'utilisateur (celles lues par LectureClavier)
	    	int reqAC = requeteAjoutClient.executeUpdate
	    	(
	    	  "INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) "
	    			+ "VALUES ("+ idC +", '" + nomC + "', '" + prenomC + "', '" + adrC + "', '" + emailC + "', '" + photoC + "', " + fidelC + ")"
	    	);
	    	
	    	System.out.println("MAJ = " + reqAC);
	    	
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
	 		
	 		requeteAjoutClient.close();
	 		requeteClient.close();
	    	resClient.close(); 	

			
	    	conn.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	}

}
