package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.RemoteApplication;
import ch.ivy.ws.addon.IvyApplication;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between IvyApplication or RemoteApplication.
 * 
 * @author Bolt
 *
 */
public class RemoteApplicationMapper {
	
	/**
	 * Convert web service application to remote application.
	 * 
	 * @param ivyApplication web service application.
	 * @return Remote application.
	 * @see IvyApplication
	 * @see RemoteApplication
	 */
	public static RemoteApplication mapApplication(IvyApplication ivyApplication){
		RemoteApplication result = new RemoteApplication();
		
		result.setName(ivyApplication.getName());
		
		return result;
	}
	
	/**
	 * Map list of IvyApplication to list of RemoteApplication.
	 * 
	 * @param apps list of application name
	 * @return List of {@link RemoteApplication}
	 * @see IvyApplication
	 * @see RemoteApplication
	 */
	public static List<RemoteApplication> mapApplications(List<IvyApplication> apps){
		List<RemoteApplication> result = List.create(RemoteApplication.class);
		
		for(IvyApplication a : apps){
			RemoteApplication r = mapApplication(a);
			
			if(null != r){
				result.add(r);
			}
		}
		
		return result;
	}
	
}
