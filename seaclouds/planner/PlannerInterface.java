/**
 * @author Mattia Buccarella
 *
 **/


package seaclouds.planner;


/* tosca parser by Leonardo */
import seaclouds.utils.toscamodel.*;


/* map */
import java.util.*;


public interface PlannerInterface {

	public DeploymentModel[] plan(IToscaEnvironment aam); // data types might be changed later
	public Map<String, IToscaEnvironment> match(IToscaEnvironment aam); // data types might be changed later

}