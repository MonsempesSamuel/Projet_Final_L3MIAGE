import java.sql.*;

public class testMenuCommande {
	
	private static final String configurationFile = "src/BD.properties";
	static Connection conn; 
	
	
    public static void main(String args[]) {

        try {

  	    // Enregistrement du driver Oracle
  	    System.out.print("Loading Oracle driver... "); 
  	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  	    
  	    System.out.println("loaded");
  	    
	    
  	    // Etablissement de la connection
	    DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
  	    System.out.print("Connecting to the database... "); 
 	    conn = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
   	    System.out.println("connected");
  	    
  	    conn.setAutoCommit(true);

	    // code métier de la fonctionnalité
  	    
		int choixUtilisateur=LectureClavier.lireEntier("Que souhaitez vous faire ?\n"+
		"1) Vérification client\n"+
		"2) GestionnaireClient\n"+
		"3) GestionnaireCommande\n"+
		"4) Inserer nouveau client\n"+
		"5) Suppression d'un client\n"+
		"6) Suppression d'un client (procedure stockée)\n"+
		"Un nombre n'etant pas dans ceux proposes fermera l'application !\n");

		switch(choixUtilisateur) {
			case 1:
				//int verif = LectureClavier.lireEntier("Verification, Veuillez entrer votre id :");
		    	System.out.print("Verification, Veuillez entrer votre id :");
		    	String verif = LectureClavier.lireChaine() ;
				BdAccess.authentifyUser(verif, conn);
				//int num = BdAccess.authentifyUser(verif, conn);
				//System.out.println("Voila : " + num);
				break;   
	
			case 2:
				GestionnaireClient.Client(conn);
				break;
				
			case 3:
				GestionnaireCommande.HistoriqueCommande(conn);
				break;
				
			case 4:
				insertionClient.inserClient(conn);
				break;
				
			case 5:
				suppressionClient.supprClient(conn);
				break;
				
			case 6:
				procedureClient.deleteProcClient(conn);
				break;
				
			default:
				System.out.println("Mauvais nombre au revoir !");
				break;
		}

  	    // Liberation des ressources et fermeture de la connexion...
 		conn.close(); 
 	    
  	    System.out.println("Au revoir, en espérant vous avoir été utile et à la prochaine fois !");
  	    
  	    // traitement d'exception
          } catch (SQLException e) {
              System.err.println("failed");
              System.out.println("Affichage de la pile d'erreur");
  	          e.printStackTrace(System.err);
              System.out.println("Affichage du message d'erreur");
              System.out.println(e.getMessage());
              System.out.println("Affichage du code d'erreur");
  	          System.out.println(e.getErrorCode());	    

          }
     }
	

}
