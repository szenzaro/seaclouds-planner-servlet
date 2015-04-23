/**
 * @author Mattia Buccarella
 * 
 **/

package seaclouds.planner;

/* optimizer */
import eu.seaclouds.platform.planner.optimizer;

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
	private Optimizer optimizer;
	
	
	/* *********************************************************** */
	/* *****                    c.tor                        ***** */
	/* *********************************************************** */
	
	public WebServiceLayer() throws ServletException {
	}
	
	
	
	/* *********************************************************** */
	/* *****                    servlet                      ***** */
	/* *********************************************************** */
	
	public void init() { // throws ServletException {
		this.planner = new SimplePlanner();
		this.jsonParser = new JSONParser();
		this.optimizer = new Optimizer();
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
				
		/* 
		 * NOTE: current version of this servles is ****SEQUENTIAL**** and
		 * it does NOT use multithreading explicitly!
		 *
		 **/
		
				
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
		DeploymentModel[] dm = this.planner.plan(aam);
		Map<String, IToscaEnvironment> cods = this.planner.match(aam);
		
		/* invoking the optimizer */
		String[] oR = optimizer.optimize(aam, cods);
		
		/* response to the caller */
		// .. to do ..
		
		return;
	}

}