package ch.ivy.addon.portalkit.mapper;

import ch.ivy.addon.portalkit.bo.ServerApplication;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Mapper use for mapping between web service absence to remote absence.
 */
public class ServerApplicationMapper {
	
  private ServerApplicationMapper() {}
	/**
	 * Convert from list of application name to list of server application.
	 * 
	 * @param appNames list of application name
	 * @param serverId server id
	 * @return list of {@link ServerApplication}
	 * @see ServerApplication
	*/
	public static List<ServerApplication> mapToServerApplications(List<String> appNames, Integer serverId){
		List<ServerApplication> outServerApplications = List.create(ServerApplication.class);
		for(String appName : appNames){
			outServerApplications.add(new ServerApplication(appName, serverId));
		}
		return outServerApplications;
	}
	
	/**
	 * Maps list of server application to list of application name.
	 * 
	 * @param serverApplications list of {@link ServerApplication}
	 * @param serverId server id
	 * @return list application name
	 * @see ServerApplication
	 */
	public static List<String> mapApplicationForServer(List<ServerApplication> serverApplications, Integer serverId){
		List<String> outApplications = List.create(String.class);

		for (ServerApplication serverApplication : serverApplications){
			if(serverApplication.getServerId()!=null && serverApplication.getServerId()==serverId && null != serverApplication.getAppName()) {
			     outApplications.add(serverApplication.getAppName());
			}
		}

		return outApplications;
	}
}
