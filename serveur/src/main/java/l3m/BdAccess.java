package l3m;

import java.sql.*;

public class BdAccess {
	
	public static String authentifyUser(String userId) {
		String retour = "-1";
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag", "delemosp", "Pierre1602");
			connect.setAutoCommit(true);

	  	    Statement verifUser = connect.createStatement();
	 		ResultSet resUser = verifUser.executeQuery("SELECT COUNT(id_client) AS nbUser "
	 													+ "FROM MC_CLIENT "
	 													+ "WHERE id_client = '"+ userId + "'");
	 		
	 		resUser.next();
			if (resUser.getInt("nbUser") == 0  || resUser.getInt("nbUser") == 1){
				retour = Integer.toString(resUser.getInt("nbUser"));
			}
	 		verifUser.close();
	 		resUser.close();
	 		connect.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return retour;
	}

}


