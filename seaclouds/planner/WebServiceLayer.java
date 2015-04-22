/**
 * @author Mattia Buccarella
 * 
 **/

package seaclouds.planner;

/* tosca parser by Leonardo */
import seaclouds.utils.toscamodel.*;

/* servlet */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/* json parser */
import org.json.simple.parser.*;
import org.json.simple.*;


public class WebServiceLayer extends HttpServlet {
	private PlannerInterface planner;
	private JSONParser jsonParser;
	
	
	/* *********************************************************** */
	/* *****                    c.tor                        ***** */
	/* *********************************************************** */
	
	public WebServiceLayer() throws ServletException {
		init();
	}
	
	
	
	/* *********************************************************** */
	/* *****                    servlet                      ***** */
	/* *********************************************************** */
	
	public void init() { // throws ServletException {
		this.planner = new SimplePlanner();
		this.jsonParser = new JSONParser();
	}
	



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		out.println("Hail :3");
		out.println("Dat was a simple GET.");
		out.println("Try to do the POST stuff!");
		return;
	}
	
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {		
		/* the output stream */
		PrintWriter out = response.getWriter();
				
		/* getting the AAM data */
		String strAam = null;
		try {
			String postContent = request.getParameter("aam_json");
			JSONObject jsonData = (JSONObject) jsonParser.parse(postContent);
			strAam = (String) jsonData.get("yaml");
		} catch(Exception pe) {
			/* error */
			out.println("");
			pe.printStackTrace(out);
			return;
		}
		
		
		/* putting on stream */
		StringReader sr = new StringReader(strAam);
		
		/* parsing */
		IToscaEnvironment aam = Tosca.newEnvironment();
		aam.readFile(sr, false);
		
		/* invoking the planner and matchmaker*/
		
		/* invoking the optimizer */
		
		/* response to the caller */
	}

}