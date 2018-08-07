package ch.ivy.addon.portalkit.enums;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Enumeration for all days of week.
 *
 * @author bolt
 */
public enum RemoteWeekDay {
	/** MONDAY */
	MONDAY("monday"), 
	/** TUESDAY */
	TUESDAY("tuesday"), 
	/** WEDNESDAY */
	WEDNESDAY("wednesday"), 
	/** THURSDAY */
	THURSDAY("thursday"), 
	/** FRIDAY */
	FRIDAY("friday"), 
	/** SATURDAY */
	SATURDAY("saturday"), 
	/** SUNDAY */
	SUNDAY("sunday");
	
	private final String label;
	
	RemoteWeekDay(String label) {
		this.label = label;
	}
	
	/**
	 * Get the label value of date in week.
	 *
	 * @return string value of label
	 */
	public String getLabel() {
		return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/emailSetting/dayInWeek/" + label);
	}
}
