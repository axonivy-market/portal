package com.axonivy.portal.developerexamples.showcase.enums;

import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
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
