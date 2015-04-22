/**
 * @author Mattia Buccarella 
 *
 **/

package seaclouds.planner;

import java.util.*;

public class SimplePlanner implements PlannerInterface {

	/* *********************************************************** */
	/* *****                    c.tor                        ***** */
	/* *********************************************************** */
	
	public SimplePlanner( ) {
		/* specify the matchmaker here */
	}
	
	
	/* *********************************************************** */
	/* *****                 PlannerInterface                ***** */
	/* *********************************************************** */
	
	@Override
	public DeploymentModel[] plan(AbstractApplicationModel aam) {
		return null;
	}
	
	
	@Override
	public Map<String, CloudOfferingDocument> match(AbstractApplicationModel aam) {
		return null;
	}
	
}