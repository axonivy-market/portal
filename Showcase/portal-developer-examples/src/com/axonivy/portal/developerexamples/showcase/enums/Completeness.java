package com.axonivy.portal.developerexamples.showcase.enums;

import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.environment.Ivy;

public enum Completeness {
	PERSON_LEGITIMIZED,
	ADDITIONAL_IDENTIFICATION_NEEDED,
	REQUEST_PROOF,
	NO_INFORMATION;
	
	private String cmsPath = "/Dialogs/GdprRequest/Lists/Completeness/";
	
	public String getCmsValue(){
		ContentObject co = Ivy.cms().get(getCmsPath()).orElseGet(null);
		String value = "";
		if (co != null) {
			ContentObjectValue cov = co.value().get(Ivy.session().getContentLocale());
			if (cov != null) {
				value = cov.read().toString();
			}
		}

		return value;
	}
	
	public String getCmsPath(){
		String value = cmsPath + this.name();
		return value;
	}
}
