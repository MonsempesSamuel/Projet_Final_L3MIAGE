package DAO;

import java.sql.SQLException;

public class test {

	public static void TestDOMDAO() {
		DomCarteDAO testDomDAO = new DomCarteDAO();
		Carte A = testDomDAO.read(0);
		A.getPlats().forEach((e)->System.out.print(e.toString()));
		A.getPlats().forEach((e)->e.setPhoto("haricovert.png"));
		
	}
	
	public static void TesttoXMLCarteDAO() {
		DomCarteDAO testDomDAO = new DomCarteDAO();
		Carte A = testDomDAO.read(0);
		System.out.print(A.toXml());
		
	}
	
	public static void TesttoXMLCommandeDAO() {
		CommandeDAO cD = null;
		try {
			cD = new CommandeDAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Commande A = cD.read2(3);
		System.out.print(A.toXml());
		
	}


	public static void testSaxDao() {
		SaxDAO SD = new SaxDAO();
		Plat A = SD.read(0);
		System.out.print(A.toString());
	}

	public static void main(String[] args) {
		TestDOMDAO();
	}
	

}
