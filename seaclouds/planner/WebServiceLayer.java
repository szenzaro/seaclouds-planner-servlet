/**
 * @author Mattia Buccarella
 * 
 **/

package seaclouds.planner;

/* tosca parser by Leonardo */
import seaclouds.utils.toscamodel.*;

/* matchmaker by Leonardo (?) */
import seaclouds.planner.*;

/* servlet */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/* json parser */
import org.json.simple.parser.*;
import org.json.simple.*;

/* Map */
import java.util.*;


public class WebServiceLayer extends HttpServlet {
	private JSONParser jsonParser;
	private DummyOptimizer optimizer;
	private Matchmaker matchmaker;
	
	
	/* *********************************************************** */
	/* *****                    c.tor                        ***** */
	/* *********************************************************** */
	
	public WebServiceLayer() throws ServletException {
	}
	
	
	
	/* *********************************************************** */
	/* *****                    servlet                      ***** */
	/* *********************************************************** */
	
	public void init() { // throws ServletException {
		this.matchmaker = new Matchmaker(Tosca.newEnvironment());
		this.jsonParser = new JSONParser();
		this.optimizer = new DummyOptimizer();
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
		
		/* putting on stream and parsing */
		StringReader sr = new StringReader(strAam);
		IToscaEnvironment aam = Tosca.newEnvironment();
		aam.readFile(sr, false);
		
		/* invoking matchmaker and optimizer */
		Map<INodeTemplate, List<INodeType>> cloudOfferings = matchmaker.Match(aam);
		Object optimizerResult = optimizer.optimize(aam, cloudOfferings);
		
		/* response to the caller */
		out.print(optimizerResult);
		return;
	}

}