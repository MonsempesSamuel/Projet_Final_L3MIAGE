package l3m;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Commande;
import DAO.CommandeDAO;

public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( this.processQueryTest(request) );
    }

	/*____________________________________________________________________________________________________________________
	 * doPost is expecting a HTTP parameter userId
	 * It sends back a XML serialization of the previous command with HTTP code 200 if a userId is specifyed
	 * It sends back a HTTP code 401 error if the userId is not specified or empty
	 * It sends back a HTTP code 500 error if a problem occured when accessing to the database
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		// Extract userId from HTTP parameters
		String userId = request.getParameter("idUsers");
		
		if(userId != null) {
			// Call the database and return result
	        try {	        	
	        	String res = BdAccess.authentifyUser(userId);
		        if(!res.equals("-1")) {
		        	//RECUPERATION DATA
		        	String lesFilms = request.getParameter("idFilms");
		        	String lesPlats = request.getParameter("idPlats");
		        	Double prix = Double.parseDouble(request.getParameter("prixCommande"));
		        	String userAdresse = request.getParameter("adresseLivraison");
		        	String[] films;
		        	String[] plats;
		        	films = lesFilms.split(" - ");
		        	plats = lesPlats.split(" - ");
		        	ArrayList<String> arrayFilms = new ArrayList<String>();
		        	ArrayList<String> arrayPlats = new ArrayList<String>();
		        	for(int i = 0; i < films.length; i++) {
		        		arrayFilms.add(films[i]);
		        	}
		        	for(int i = 0; i < plats.length; i++) {
		        		arrayPlats.add(plats[i]);
		        	}
		        	
		        	Date date = Calendar.getInstance().getTime();  
	                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	                String strDate = dateFormat.format(date);  
		        	
		        	Commande newCommande = new Commande("1", strDate, userId, arrayPlats, arrayFilms, prix, userAdresse);
	        		CommandeDAO daoCommande = new CommandeDAO();
	        		daoCommande.create(newCommande);
	        		
	        		//REPONSE
	        		
		        	response.setStatus(HttpServletResponse.SC_OK);
	        		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
									+ "<Commande>"
									+ "<idCommande>" + 1 + "</idCommande>"
									+ "<idClient>" + userId + "</idClient>"
									+ "<prix>" + prix + "<prix>"
									+ "<plats>";
					for(int i = 0; i < plats.length; i++) {
						xml += "<plat> " + plats[i] + " </plat>";
					}
					xml += 		"</plats>";
					
					xml += 		"</films>";
					for(int i = 0; i < films.length; i++) {
						xml += "<film> " + films[i] + " </film>";
					}
					
					xml	+= "</films>"
						+ "</Commande>";
	        		
		        	response.getWriter().println(xml);
		        }
		        else {
		        	response.setStatus(HttpServletResponse.SC_OK);
		        	response.getWriter().println("<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
													+ "<Commande>"
													+ "<idCommande>" + -1 + "</idCommande>"
													+ "<idClient>" + userId + "</idClient>"
												+ "</Commande>");
		        }
		        
	        } 
			catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println( e.toString() );
	        }
		}
		else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
        
    }

	private String processQueryTest(HttpServletRequest request) {
		String res = "{";
		Enumeration<String> P = request.getParameterNames();
		while (P.hasMoreElements()) {
			String p = P.nextElement();
			res += "\"" + p + "\": \"" + request.getParameter(p) + "\", ";
		}
		return res + "}";
	}
}
