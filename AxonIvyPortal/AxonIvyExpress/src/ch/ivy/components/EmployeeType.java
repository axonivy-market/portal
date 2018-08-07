package ch.ivy.components;

import java.util.Date;

import ch.ivyteam.ivy.cm.IContentObject;
import ch.ivyteam.ivy.cm.IContentObjectValue;
import ch.ivyteam.ivy.environment.Ivy;

/**
 * Enumeration for storing the type of the employer from the LDAP
 * 
 * @author A28030
 *
 */
public enum EmployeeType {
	
	/**
	 * Internal
	 */
	I,
	
	/**
	 * External
	 */
	E,
	
	/**
	 * Undefined
	 */
	UNKNOWN;

	private String cmsPath = "/de/eon/components/EmployeeType/";
	
	public void setCmsValue(String name){
		// Dummy Methode zur Generierung der Bean
	}

	public String getCmsValue(){
		IContentObject co = Ivy.cms().findContentObject(getCmsPath());
		String value = "";
		if(co != null){
			IContentObjectValue cv = co.getValue(new Date(), Ivy.session().getContentLocale());
			if(cv != null){
				value = cv.getContentAsString();
			}
		}
		return value;
	}
	
	public String getCmsPath(){
		String value = cmsPath + this.name() + "/name";
		return value;
	}
}
