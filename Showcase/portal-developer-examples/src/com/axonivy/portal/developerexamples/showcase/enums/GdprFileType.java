package com.axonivy.portal.developerexamples.showcase.enums;

import ch.ivyteam.ivy.cm.IContentObject;
import ch.ivyteam.ivy.cm.IContentObjectValue;
import ch.ivyteam.ivy.environment.Ivy;

public enum GdprFileType {
	ACCOUNTING_GENERAL,
	ACCOUNTING_CUSTOMS,
	ACCOUNTING_REASON,
	CONTRACTING_MOVING,
	CONTRACTING_IMMOVABLE,
	CONTRACTING_RENTAL,
	CONTRACT_CONTRACT_OF_WORK,
	CONTRACT_DAMAGE,
	EMPLOYMENT_APPLICATION,
	EMPLOYMENT_TIME_REGISTRATION,
	EMPLOYMENT_SOCIAL_SECURITY,
	EMPLOYMENT_CERTIFICATE,
	EMPLOYMENT_DOCUMENTATION_LED,
	EMPLOYMENT_DOCUMENTATION_GROSS,
	EMPLOYMENT_ACCIDENT;
	
	private String cmsPath = "/Dialogs/GdprRequest/Lists/ModalFilesTypes/";
	
	public String getCmsValue(){
		IContentObject co = Ivy.cms().getContentObject(getCmsPath());
		String value = "";
		if(co != null){
			IContentObjectValue cv = co.getValue(Ivy.session().getContentLocale());
			if(cv != null){
				value = cv.getContentAsString();
			}
		}
		return value;
	}
	
	public String getCmsPath(){
		String value = cmsPath + this.name();
		return value;
	}

}
