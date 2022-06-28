package com.axonivy.portal.developerexamples.showcase.enums;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;

public enum ProcessState {
	APPLICATION,
	IDENTITY_CHECK,
	INQURY,
	VERIFICATION,
	PROCESSING,
	INFORMATION,
	DONE;
	
	private String cmsPath = "/Dialogs/Lists/ProcessState/";

  public String getCmsValue() {
    String value = "";
    
    String path = getCmsPath();
    
    if (Ivy.cms().get(path).isPresent()) {
      value = Ivy.cms().coLocale(path, Ivy.session().getContentLocale());
    }
    
    return value;
  }

	public List<String> getListForProcessChain() {
		List<String> pStates = new ArrayList<String>();
		for (ProcessState p : ProcessState.values()) {
			pStates.add(p.getCmsValue());
		}
		return pStates;
	}
	
	public int getStepForProcessChain() {
		int index = 0;
		index = this.ordinal();
		return index;
	}

	public String getCmsPath(){
		String value = cmsPath + this.name();
		return value;
	}
}
