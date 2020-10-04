import java.sql.*;

public class BdAccess {
	
	public static String authentifyUser(String userId, Connection conn) {
		String retour = "-1";
		try {
	    	
	  	    Statement verifUser = conn.createStatement();
	 		ResultSet resUser = verifUser.executeQuery
	 		(
	 				"SELECT count(id_client) AS nbUser From MC_CLIENT WHERE id_client = '"+ userId + "'"
	 				
	 		) ;
	 		
	 		resUser.next();
	 			
			if (resUser.getInt("nbUser") != 0 ){
				retour = userId;
			}
			
			else {
				System.out.println("\nVous n'tes pas enregistrer !\n");
			}
	    	
	 		verifUser.close();
	 		resUser.close();
	 		conn.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		//System.out.println("Valeur de l'id : "+retour);
		return retour;
	}

}


