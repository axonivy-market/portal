package ch.ivy.addon.portalkit.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.json.JSONException;

import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;
import ch.ivy.addon.portalkit.enums.RemoteWeekDay;
import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.persistence.domain.Server;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;
import ch.ivy.ws.addon.IvyEmailSetting;
import ch.ivyteam.wf.processes.EmailSettingServiceData;

/**
 * Class for mapping between web service object and remote object.
 * 
 * @author maonguyen
 *
 */
public class RemoteEmailSettingMapper {

  private RemoteEmailSettingMapper() {}
  
	/**
	 * Map from list of web service object to list of remote object.
	 * 
	 * @param emailSettings list of web service email setting {@link IvyEmailSetting}
	 * @param endPoint end point to call web service.
	 * @param server ivy server is working on
	 * @return list of remote email setting {@link RemoteEmailSetting}.
	 * @throws JSONException 
	 */
	public static List<RemoteEmailSetting> mapEmailSettings(
			List<IvyEmailSetting> emailSettings, String endPoint, Server server) throws JSONException {
		List<RemoteEmailSetting> result = null;
		if (emailSettings != null) {
			result = new ArrayList<>();
			for (IvyEmailSetting item : emailSettings) {

				result.add(mappEmailSetting(item, endPoint, server));
			}
		}
		return result;
	}

	/**
	 * Map from web service object to remote object.
	 * 
	 * @param ivyEmailSetting web service email setting
	 * @param endPoint end point to call web service
	 * @return remote object email setting.
	 * @throws JSONException 
	 */
	private static RemoteEmailSetting mappEmailSetting(IvyEmailSetting ivyEmailSetting, String endPoint, Server server) throws JSONException {

		RemoteEmailSetting result = new RemoteEmailSetting();
		List<RemoteWeekDay> weekDays = new ArrayList<>();

		for (Application app: server.getApplications()) {
			if (ivyEmailSetting.getAppName() != null && ivyEmailSetting.getAppName().equals(app.getName())) {
				result.setAppDisplayName(ApplicationMultiLanguage.getDisplayNameOfAllAppInSameIvyApplication(app, server));
				break;
			}
		}
		if (result.getAppDisplayName() == null || result.getAppDisplayName().trim().isEmpty()) {
		  result.setAppDisplayName(ivyEmailSetting.getAppName());
		}
		
		result.setServer(server);
		result.setAppName(ivyEmailSetting.getAppName());
		result.setMailNotification(ivyEmailSetting.getEmailSendOnNewWorkTasks());
		result.setFurtherMailFromApp(ivyEmailSetting.getCustomMailEnabled());
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnMonday()) {
			weekDays.add(RemoteWeekDay.MONDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnTuesday()) {
			weekDays.add(RemoteWeekDay.TUESDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnWednesday()) {
			weekDays.add(RemoteWeekDay.WEDNESDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnThursday()) {
			weekDays.add(RemoteWeekDay.THURSDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnFriday()) {
			weekDays.add(RemoteWeekDay.FRIDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnSaturday()) {
			weekDays.add(RemoteWeekDay.SATURDAY);
		}
		if (ivyEmailSetting.getEmailSendDailyTaskSummaryOnSunday()) {
			weekDays.add(RemoteWeekDay.SUNDAY);
		}

		result.setWeekDays(weekDays);
		result.setEndPoint(endPoint);

		return result;
	}
	
	/**
	 * Map from list of remote email setting to list of map endpoint and list web service object.
	 * 
	 * @param remoteEmailSettings list of remote email setting {@link RemoteEmailSetting}
	 * @return list of map endpoint and list web service email setting {@link IvyEmailSetting}.
	 */
	public static List<Map<String, List<IvyEmailSetting>>> mapEmailSetting(List<RemoteEmailSetting> remoteEmailSettings) {
		List<Map<String, List<IvyEmailSetting>>> result = new ArrayList<>();
		Map<String, List<IvyEmailSetting>> settingMap = new HashMap<>();
		
		if (remoteEmailSettings != null) {
			//Group setting in setting : Map <endPoint, List<Setting>>
			for (RemoteEmailSetting item : remoteEmailSettings) {
				List<IvyEmailSetting> settingsForEachEndPoint = settingMap.get(item.getEndPoint());
				if (settingsForEachEndPoint == null) {
					settingsForEachEndPoint = new ArrayList<>();
				}
				settingsForEachEndPoint.add(convertToIvyEmailSetting(item));
				settingMap.put(item.getEndPoint(), settingsForEachEndPoint);
			}
			
			// Put every item in the above map to the return list.
			for (Map.Entry<String, List<IvyEmailSetting>> item : settingMap.entrySet()){
				Map<String, List<IvyEmailSetting>> subItem = new HashMap<>();
				subItem.put(item.getKey(), item.getValue());
				result.add(subItem);
			}
		}
		
		return result;
	}

	/**
	 * Convert from remote object to web service object.
	 * 
	 * @param remoteEmailSetting
	 * @return WS object.
	 */
	private static IvyEmailSetting convertToIvyEmailSetting(
			RemoteEmailSetting remoteEmailSetting) {
		if (remoteEmailSetting != null) {
			IvyEmailSetting emailSetting = new IvyEmailSetting();
			emailSetting.setAppName(remoteEmailSetting.getAppName());
			emailSetting.setCustomMailEnabled(remoteEmailSetting.isFurtherMailFromApp());
			emailSetting.setEmailSendOnNewWorkTasks(remoteEmailSetting.isMailNotification());
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.MONDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnMonday(true);
			} else {
				emailSetting.setEmailSendDailyTaskSummaryOnMonday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.TUESDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnTuesday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnTuesday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.WEDNESDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnWednesday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnWednesday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.THURSDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnThursday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnThursday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.FRIDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnFriday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnFriday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.SATURDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnSaturday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnSaturday(false);
			}
			if (remoteEmailSetting.getWeekDays().contains(RemoteWeekDay.SUNDAY)) {
				emailSetting.setEmailSendDailyTaskSummaryOnSunday(true);
			}else {
				emailSetting.setEmailSendDailyTaskSummaryOnSunday(false);
			}
			
			return emailSetting;
		}
		return null;
	}
	
	/**
	 * Parse data from map for process data.
	 * 
	 * @param emailSettingServiceData data of EmailSettingService.
	 * @param mapSetting map of end point and list of web service email setting {@link IvyEmailSetting}
	 */
	public static void parseData(EmailSettingServiceData emailSettingServiceData, Map<String, List<IvyEmailSetting>> mapSetting) {
		if (mapSetting != null) {
			for (Map.Entry<String, List<IvyEmailSetting>> item : mapSetting.entrySet()) {
				emailSettingServiceData.setEndpoint(item.getKey());
				ch.ivyteam.ivy.scripting.objects.List<IvyEmailSetting> emailSettingList = ch.ivyteam.ivy.scripting.objects.List.create(IvyEmailSetting.class);
				emailSettingList.addAll(item.getValue());
				emailSettingServiceData.setIvyEmailSettings(emailSettingList);
				break;
			}
		}
	}

}
