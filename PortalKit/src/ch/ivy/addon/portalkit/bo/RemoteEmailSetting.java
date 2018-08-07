package ch.ivy.addon.portalkit.bo;

import java.util.List;

import ch.ivy.addon.portalkit.enums.RemoteWeekDay;
import ch.ivy.addon.portalkit.persistence.domain.Server;

/**
 * Bean for remote email setting.
 * 
 * @author maonguyen
 */
public class RemoteEmailSetting {

	private String appName;
	private String appDisplayName;
	private boolean isMailNotification;
	private boolean isFurtherMailFromApp;
	private List<RemoteWeekDay> weekDays;
	private String endPoint;
	private Server server;
	/**
	 * Gets the appName
	 * 
	 * @return Returns the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the appName
	 * 
	 * @param appName
	 *            The appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Gets the isMailNotification
	 * 
	 * @return Returns the isMailNotification
	 */
	public boolean isMailNotification() {
		return isMailNotification;
	}

	/**
	 * Sets the isMailNotification
	 * 
	 * @param isMailNotification
	 *            The isMailNotification to set
	 */
	public void setMailNotification(boolean isMailNotification) {
		this.isMailNotification = isMailNotification;
	}

	/**
	 * Gets the isFurtherMailFromApp
	 * 
	 * @return Returns the isFurtherMailFromApp
	 */
	public boolean isFurtherMailFromApp() {
		return isFurtherMailFromApp;
	}

	/**
	 * Sets the isFurtherMailFromApp
	 * 
	 * @param isFurtherMailFromApp
	 *            The isFurtherMailFromApp to set
	 */
	public void setFurtherMailFromApp(boolean isFurtherMailFromApp) {
		this.isFurtherMailFromApp = isFurtherMailFromApp;
	}

	/**
	 * Gets the weekDays
	 * 
	 * @return Returns the weekDays
	 */
	public List<RemoteWeekDay> getWeekDays() {
		return weekDays;
	}

	/**
	 * Sets the weekDays
	 * 
	 * @param weekDays
	 *            The weekDays to set
	 */
	public void setWeekDays(List<RemoteWeekDay> weekDays) {
		this.weekDays = weekDays;
	}

	/**
	 * Gets the endPoint
	 * 
	 * @return Returns the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * Sets the endPoint
	 * 
	 * @param endPoint
	 *            The endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * Gets the appDisplayName
	 * 
	 * @return Returns the appDisplayName
	 */
	public String getAppDisplayName() {
		return appDisplayName;
	}

	/**
	 * Sets the appDisplayName
	 * 
	 * @param appDisplayName
	 *            The appDisplayName to set
	 */
	public void setAppDisplayName(String appDisplayName) {
		this.appDisplayName = appDisplayName;
	}

  public Server getServer() {
    return server;
  }

  public void setServer(Server server) {
    this.server = server;
  }

}
