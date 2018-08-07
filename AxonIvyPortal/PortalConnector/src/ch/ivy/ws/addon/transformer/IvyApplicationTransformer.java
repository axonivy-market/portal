package ch.ivy.ws.addon.transformer;

import java.util.ArrayList;
import java.util.List;
import ch.ivy.ws.addon.types.IvyApplication;
import ch.ivyteam.ivy.application.ActivityOperationState;
import ch.ivyteam.ivy.application.IApplication;


/**
 * Provides general methods for transformation from IApplication to IvyApplication
 * 
 * @author msmetana
 * @see IApplication
 * @see IvyApplication
 */
public class IvyApplicationTransformer {

	/**
	 * Transformation from IApplication to IvyApplication.
	 * @param app
	 * @return IvyApplication result
	 * @see IApplication
	 * @see IvyApplication
	 */
	public IvyApplication transform(IApplication app) {
		IvyApplication result = new IvyApplication();
		
		result.setName(app.getName());
		result.setIsActive(app.getActivityOperationState().equals(ActivityOperationState.ACTIVE));
		
		return result;
	}
	
	/**
	 * Transformation from java.util.List<IApplication> to java.util.List<IvyApplication>
	 * @param apps
	 * @return List<IvyApplication> result
	 * @see IApplication
	 * @see IvyApplication
	 */
	public List<IvyApplication> transform(List<IApplication> apps) {
		List<IvyApplication> result = new ArrayList<IvyApplication>();
		
		for (IApplication app : apps) {
			result.add(transform(app));
		}
 		
		return result;
	}
	
	/**
	 * Returns List of names by given List of IApplications
	 * @param apps
	 * @return List<String> result
	 */
	public List<String> getNames(List<IApplication> apps) {
		List<String> result = new ArrayList<String>();
		
		for (IApplication app : apps) {
			result.add(app.getName());
		}
		
		return result;
	}
	
}
