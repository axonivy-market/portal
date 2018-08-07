package ch.ivy.addon.portalkit.util;

import java.util.List;

import ch.ivy.addon.portalkit.bo.RemoteEmailSetting;

/**
 * Utility for email setting.
 * 
 * @author maonguyen
 *
 */
public class EmailSettingUtils {
	
	/**
	 * Update remote email settings when user use the general setting function.
	 * 
	 * @param remoteEmailSettings list of remote email setting need to update
	 * @param generalSetting general setting (Setting for all applications) 
	 */
	public static void updateRemoteEmailSettings(List<RemoteEmailSetting> remoteEmailSettings, RemoteEmailSetting generalSetting) {
		if (remoteEmailSettings != null) {
			for (RemoteEmailSetting item: remoteEmailSettings) {
				item.setFurtherMailFromApp(generalSetting.isFurtherMailFromApp());
				item.setMailNotification(generalSetting.isMailNotification());
				item.setWeekDays(generalSetting.getWeekDays());
			}
		}
	}
	

}
