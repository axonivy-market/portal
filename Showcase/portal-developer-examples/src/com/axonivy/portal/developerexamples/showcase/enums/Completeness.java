package com.axonivy.portal.developerexamples.showcase.enums;

import ch.ivyteam.ivy.cm.IContentObject;
import ch.ivyteam.ivy.cm.IContentObjectValue;
import ch.ivyteam.ivy.environment.Ivy;

public enum Completeness {
	PERSON_LEGITIMIZED,
	ADDITIONAL_IDENTIFICATION_NEEDED,
	REQUEST_PROOF,
	NO_INFORMATION;
	
	private String cmsPath = "/Dialogs/GdprRequest/Lists/Completeness/";
	
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
