package ch.ivy.addon.portalkit.mapper;

import java.util.Calendar;

import org.primefaces.json.JSONException;

import ch.ivy.addon.portalkit.bo.RemoteAbsence;
import ch.ivy.addon.portalkit.bo.RemoteApplicationUser;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.ws.addon.IvyAbsence;
import ch.ivy.ws.addon.IvyUser;
import ch.ivyteam.ivy.scripting.objects.List;

/**
 * Map between Map between Ivy user and RemoteApplicationUser.
 */
public class RemoteApplicationUserMapper {
	
	protected static RemoteApplicationUser mapToRemoteApplicationUser(IvyUser ivyUser, Server server) throws JSONException{
		RemoteApplicationUser result = new RemoteApplicationUser();
		for (Application application: server.getApplications()) {
			if (ivyUser.getApplication() != null && ivyUser.getApplication().equals(application.getName())){
				result.setAppDisplayName(ApplicationMultiLanguage.getDisplayNameOfAllAppInSameIvyApplication(application, server));
				break;
			}
		}
		if (result.getAppDisplayName() == null || result.getAppDisplayName().trim().isEmpty()) {
		  result.setAppDisplayName(ivyUser.getApplication());
		}
		
		result.setAppName(ivyUser.getApplication());
		result.setId(ivyUser.getId());
		result.setDisplayName(ivyUser.getDisplayName());
		if(ivyUser.getMemberName()!=null){
			result.setMemberName(ivyUser.getMemberName().replace("#", ""));
		}
		return result;
	}
	
	public static List<RemoteApplicationUser> mapToRemoteApplicationUsers(List<IvyUser> ivyUsers, Server server) throws JSONException{
		List<RemoteApplicationUser> outUsers = List.create(RemoteApplicationUser.class);

		for (IvyUser absence : ivyUsers){
			RemoteApplicationUser remoteUser = mapToRemoteApplicationUser(absence, server);

			if (null != remoteUser) {
				outUsers.add(remoteUser);
			}
		}

		return outUsers;
	}
	
	/**
	 * Convert from remoteAbsence to web service absence.
	 * 
	 * @param remoteAbsence remote absence
	 * @return web service absence
	 * @see IvyAbsence
	 * @see RemoteAbsence
	 */
	protected static IvyAbsence mapToIvyAbsence(RemoteAbsence remoteAbsence){
		IvyAbsence result = new IvyAbsence();
		result.setAppName(remoteAbsence.getAppName());
		result.setDescription(remoteAbsence.getDescription());
		Calendar cal = Calendar.getInstance();
		cal.setTime(remoteAbsence.getStartDateInclusive());
		result.setStartDateInclusive(cal);
		cal.setTime(remoteAbsence.getStopDateInclusive());
		result.setStopDateInclusive(cal);
		return result;
	}
	
	/**
	 * Map list of IvyAbsence to list of RemoteAbsence.
	 * 
	 * @param remoteAbsences list of {@link RemoteAbsence}
	 * @return list of {@link IvyAbsence}
	 * @see IvyAbsence
	 * @see RemoteAbsence
	 */
	public static List<IvyAbsence> mapToIvyAbsences(List<RemoteAbsence> remoteAbsences){
		List<IvyAbsence> outAbsences = List.create(IvyAbsence.class);

		for (RemoteAbsence absence : remoteAbsences){
			IvyAbsence ivyAbsence = mapToIvyAbsence(absence);

			if (null != ivyAbsence) {
				outAbsences.add(ivyAbsence);
			}
		}

		return outAbsences;
	}
}
