package ch.ivy.ws.addon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import ch.ivy.ws.addon.WSErrorType;
import ch.ivy.ws.addon.WSException;
import ch.ivy.ws.addon.bo.AvailableAppsResult;
import ch.ivy.ws.addon.bo.UserSettingServiceResult;
import ch.ivy.ws.addon.types.IvyEmailSetting;
import ch.ivy.ws.addon.types.IvyUserSetting;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IEMailNotificationSettings;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserEMailNotificationSettings;
import ch.ivyteam.ivy.server.IServer;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.util.date.Weekday;

/**
 * 
 * 
 * @author mde
 *
 */
public class UserSettingServiceImpl extends AbstractService implements
		IUserSettingService {
	private static String DISABLE_CUSTOM_MAILS = "DisableCustomMails";
	private static String TRUE = "true";
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws WSException
	 */
	private IvyUserSetting findUserSetting(IUser user) throws WSException {
		
		IvyUserSetting result = new IvyUserSetting();
		try {
			if(user != null){
				IUserEMailNotificationSettings emailSettings = user.getEMailNotificationSettings();
				
				result.setEmailNotificationDisabled(emailSettings.isNotificationDisabled());
				
				if(emailSettings.getSendDailyTaskSummary().contains(Weekday.MONDAY)){
					result.setEmailSendDailyTaskSummaryOnMonday(true);
				}
				
				result.setEmailSendDailyTaskSummaryOnMonday(emailSettings.getSendDailyTaskSummary().contains(Weekday.MONDAY));
				result.setEmailSendDailyTaskSummaryOnTuesday(emailSettings.getSendDailyTaskSummary().contains(Weekday.TUESDAY));
				result.setEmailSendDailyTaskSummaryOnWednesday(emailSettings.getSendDailyTaskSummary().contains(Weekday.WEDNESDAY));
				result.setEmailSendDailyTaskSummaryOnThursday(emailSettings.getSendDailyTaskSummary().contains(Weekday.THURSDAY));
				result.setEmailSendDailyTaskSummaryOnFriday(emailSettings.getSendDailyTaskSummary().contains(Weekday.FRIDAY));
				result.setEmailSendDailyTaskSummaryOnSaturday(emailSettings.getSendDailyTaskSummary().contains(Weekday.SATURDAY));
				result.setEmailSendDailyTaskSummaryOnSunday(emailSettings.getSendDailyTaskSummary().contains(Weekday.SUNDAY));
				
				result.setEmailSendOnNewWorkTasks(emailSettings.isSendOnNewWorkTasks());
				result.setUseUserEmailSettings(!emailSettings.isUseApplicationDefault());
				if(user.getEMailLanguage() != null){
					result.setLanguage(user.getEMailLanguage().getLanguage());
				}
			}
		} catch (Exception e) {
			//Ivy.log().error(e);
			throw new WSException(10018, e);
		}
		return result;
	}

	/**
	 * 
	 * @param user
	 * @param setting
	 * @throws WSException
	 */
	private void saveUserSetting(IUser user, IvyUserSetting setting) throws WSException {
		try{
			if(user != null){
				IUserEMailNotificationSettings s = user.getEMailNotificationSettings();
				if(s != null){
					if(setting.getEmailNotificationDisabled() != null)
						s.setNotificationDisabled(setting.getEmailNotificationDisabled());
					
					if(setting.getEmailSendDailyTaskSummaryOnMonday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.MONDAY, setting.getEmailSendDailyTaskSummaryOnMonday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.MONDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnTuesday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.TUESDAY, setting.getEmailSendDailyTaskSummaryOnTuesday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.TUESDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnWednesday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY, setting.getEmailSendDailyTaskSummaryOnWednesday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnThursday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.THURSDAY, setting.getEmailSendDailyTaskSummaryOnThursday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.THURSDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnFriday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.FRIDAY, setting.getEmailSendDailyTaskSummaryOnFriday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.FRIDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnSaturday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.SATURDAY, setting.getEmailSendDailyTaskSummaryOnSaturday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.SATURDAY, false);
					}
					if(setting.getEmailSendDailyTaskSummaryOnSunday() != null){
						s.setSendDailyTaskSummaryOnDay(Weekday.SUNDAY, setting.getEmailSendDailyTaskSummaryOnSunday());
					}else{
						s.setSendDailyTaskSummaryOnDay(Weekday.SUNDAY, false);
					}
					
					if(setting.getUseUserEmailSettings() != null){
						s.setUseApplicationDefault(!setting.getUseUserEmailSettings());
					}else{
						s.setUseApplicationDefault(true);
					}

					if(setting.getEmailSendOnNewWorkTasks() != null){
						s.setSendOnNewWorkTasks(setting.getEmailSendOnNewWorkTasks());
					}else{
						s.setSendOnNewWorkTasks(false);
					}
					
					if(setting.getLanguage() != null){
						if(setting.getLanguage().equalsIgnoreCase("de")){
							user.setEMailLanguage(Locale.GERMAN);
						}else{
							user.setEMailLanguage(Locale.ENGLISH);
						}
					}
					user.setEMailNotificationSettings(s);
				}
			}
		}catch(Exception e){
			//Ivy.log().error(e);
			throw new WSException(10019, e);
		}
	}

	@Override
	public UserSettingServiceResult findUserSetting(String username, String appName)
			throws WSException {
		UserSettingServiceResult result = new UserSettingServiceResult();
		try {
			List<String> apps = new ArrayList<String>();
			apps.add(appName);

			List<IUser> users = findUsers(apps, username);
			if (users.size() > 0) {
				result.setUserSetting(findUserSetting(users.get(0)));
			}
			
		} catch (Exception e) {
			//Ivy.log().error(e);
			throw new WSException(10020, e);
		}
		
		return result;
	}

	@Override
	public void saveUserSetting(String username, IvyUserSetting setting,
			String appName) throws WSException {
		try {
			List<String> apps = new ArrayList<String>();
			apps.add(appName);

			List<IUser> users = findUsers(apps, username);
			if (users.size() > 0) {
				saveUserSetting(users.get(0), setting);
			}
		} catch (Exception e) {
			//Ivy.log().error(e);
			throw new WSException();
		}		
		
	}
	
	@Override
	public UserSettingServiceResult getEMailSettings(final List<String> applications,final String user) throws Exception {
		return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<UserSettingServiceResult>() {
			@Override
			public UserSettingServiceResult call() throws WSException {
				UserSettingServiceResult result = new UserSettingServiceResult();
				List<IvyEmailSetting> settings = new ArrayList<IvyEmailSetting>();
				List<WSException> errors = new ArrayList<WSException>();
				List<String> availableApps = new ArrayList<String>();
				try {
					if (user != null && !user.trim().equals("")) {
						AvailableAppsResult aaResult = findAvailableApplicationsAndUsers(applications, user);
						errors.addAll(aaResult.getErrors());
						availableApps.addAll(aaResult.getAvailableApps());
						IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
						for (IApplication serverApp : server.getApplicationConfigurationManager().getApplications()) {
							IvyEmailSetting setting = new IvyEmailSetting();
							if(availableApps.contains(serverApp.getName())){
								if(serverApp.getSecurityContext().findUser(user) != null) {       
									IUser iuser = serverApp.getSecurityContext().findUser(user);
									setting.setAppName(serverApp.getName());
									// set value for CustomMailsDisabled base on property in IUser
									if (iuser.getProperty(DISABLE_CUSTOM_MAILS) != null && TRUE.equals(iuser.getProperty(DISABLE_CUSTOM_MAILS).toLowerCase())) {
										setting.setCustomMailsDisabled(true);
									} else {
										setting.setCustomMailsDisabled(false);
									}
									IUserEMailNotificationSettings emailSettings = iuser.getEMailNotificationSettings();
									// if user settings is set to default, return default settings of the application
									if (emailSettings.isUseApplicationDefault()) {
										IEMailNotificationSettings defaultSettings = Ivy.wf().getApplication().getDefaultEMailNotifcationSettings();
										if (defaultSettings != null) {
											setting.setEmailNotificationDisabled(defaultSettings.isNotificationDisabled());
											setting.setEmailSendOnNewWorkTasks(defaultSettings.isSendOnNewWorkTasks());
											setting.setEmailSendDailyTaskSummaryOnMonday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.MONDAY));
											setting.setEmailSendDailyTaskSummaryOnTuesday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.TUESDAY));
											setting.setEmailSendDailyTaskSummaryOnWednesday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY));
											setting.setEmailSendDailyTaskSummaryOnThursday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.THURSDAY));
											setting.setEmailSendDailyTaskSummaryOnFriday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.FRIDAY));
											setting.setEmailSendDailyTaskSummaryOnSaturday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.SATURDAY));
											setting.setEmailSendDailyTaskSummaryOnSunday(defaultSettings.isSendDailyTaskSummaryOnDay(Weekday.SUNDAY));
										}
									} else {
										setting.setEmailNotificationDisabled(emailSettings.isNotificationDisabled());
										setting.setEmailSendOnNewWorkTasks(emailSettings.isSendOnNewWorkTasks());
										setting.setEmailSendDailyTaskSummaryOnMonday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.MONDAY));
										setting.setEmailSendDailyTaskSummaryOnTuesday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.TUESDAY));
										setting.setEmailSendDailyTaskSummaryOnWednesday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY));
										setting.setEmailSendDailyTaskSummaryOnThursday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.THURSDAY));
										setting.setEmailSendDailyTaskSummaryOnFriday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.FRIDAY));
										setting.setEmailSendDailyTaskSummaryOnSaturday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.SATURDAY));
										setting.setEmailSendDailyTaskSummaryOnSunday(emailSettings.isSendDailyTaskSummaryOnDay(Weekday.SUNDAY));
									}
									settings.add(setting);
								}
							}
							result.setEmailSettings(settings);
						}
					} else {
						//Username not given
						List<Object> userText = new ArrayList<Object>();
						userText.add(user);
						errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
					}
					result.setErrors(errors);
					
				} catch (Exception e) {
					throw new WSException(10032, e);
				}
				return result;
			}
		});
	}
	
	@Override
	public List<WSException> setEMailSettings(final List<IvyEmailSetting> settings, final String user) throws Exception {
		return ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<WSException>>() {
			@Override
			public List<WSException> call() throws WSException {
				List<WSException> errors = new ArrayList<WSException>();
				try {
					IServer server = ch.ivyteam.ivy.server.ServerFactory.getServer();
					for (IvyEmailSetting setting : settings) {
						if (setting != null) {
							IApplication serverApp = server.getApplicationConfigurationManager().findApplication(setting.getAppName());
							if (serverApp != null) {
								IUser iuser = serverApp.getSecurityContext().findUser(user);
								if (iuser != null) {
									/*IUserEMailNotificationSettings emailSettings = iuser.getEMailNotificationSettings();
									//  If change "use user settings" from true to false, copy default language setting from application's default
									if (emailSettings != null && !emailSettings.isUseApplicationDefault()) {
										iuser.setEMailLanguage(serverApp.getDefaultEMailLanguage());
									}*/
									// set value for email settings on the server
									iuser.setEMailNotificationSettings(convertFromIvyEmailSettingToIUserEMailNotificationSettings(iuser, setting));
									if (setting.getCustomMailsDisabled() != null) {
										iuser.setProperty(DISABLE_CUSTOM_MAILS, setting.getCustomMailsDisabled().toString());
									}
								} else {
									List<Object> userText = new ArrayList<Object>();
									userText.add(user);
									errors.add(new WSException(WSErrorType.WARNING, 10029, userText, null));
								}
							} else {
								List<Object> userText = new ArrayList<Object>();
								userText.add(setting.getAppName());
								errors.add(new WSException(WSErrorType.WARNING, 10030, userText, null));
							}
						}
					}
					return errors;
				} catch (Exception e) {
					throw new WSException(10033, e);
				}
			}
		});
	}
	
	/**
	 * convert from IvyEmailSetting to IUserEMailNotificationSettings
	 * @param user
	 * @param setting
	 * @throws
	 */
	private IUserEMailNotificationSettings convertFromIvyEmailSettingToIUserEMailNotificationSettings(IUser user, IvyEmailSetting setting) {
		IUserEMailNotificationSettings out = null;
		if (user != null) {
			out = user.getEMailNotificationSettings();
			if (setting != null) {
				//Always set false in case of user setting.
				out.setNotificationDisabled(false);
				if (setting.getEmailSendOnNewWorkTasks() != null) {
					out.setSendOnNewWorkTasks(setting.getEmailSendOnNewWorkTasks());
				}
				if (setting.getEmailSendDailyTaskSummaryOnMonday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.MONDAY, setting.getEmailSendDailyTaskSummaryOnMonday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnTuesday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.TUESDAY, setting.getEmailSendDailyTaskSummaryOnTuesday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnWednesday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.WEDNESDAY, setting.getEmailSendDailyTaskSummaryOnWednesday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnThursday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.THURSDAY, setting.getEmailSendDailyTaskSummaryOnThursday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnFriday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.FRIDAY, setting.getEmailSendDailyTaskSummaryOnFriday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnSaturday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.SATURDAY, setting.getEmailSendDailyTaskSummaryOnSaturday());
				}
				if (setting.getEmailSendDailyTaskSummaryOnSunday() != null) {
					out.setSendDailyTaskSummaryOnDay(Weekday.SUNDAY, setting.getEmailSendDailyTaskSummaryOnSunday());
				}
				out.setUseApplicationDefault(false);
			}
		}
		return out;
	}
}
