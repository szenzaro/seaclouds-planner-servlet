/**
 * @author Mattia Buccarella
 *
 **/

package seaclouds.planner;

import java.util.*;

public interface PlannerInterface {

	public DeploymentModel[] plan(AbstractApplicationModel aam); // data types might be changed later
	public Map<String, CloudOfferingDocument> match(AbstractApplicationModel aam); // data types might be changed later

}