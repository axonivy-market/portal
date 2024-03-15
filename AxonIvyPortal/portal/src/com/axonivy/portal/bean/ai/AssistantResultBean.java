package com.axonivy.portal.bean.ai;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.publicapi.PortalNavigatorAPI;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

@ManagedBean
@ViewScoped
public class AssistantResultBean implements Serializable {

  private static final long serialVersionUID = -4342231612239885871L;

  public String generateLinkToTaskDetails(ITask workingTask) {
    if (workingTask == null) {
      return PortalNavigator.getDashboardLink();
    }

    return PortalNavigatorAPI
        .buildUrlToPortalTaskDetailsPageByUUID(workingTask.uuid());
  }

  public String generateLinkToCaseDetails(ICase workingCase) {
    if (workingCase == null) {
      return PortalNavigator.getDashboardLink();
    }
    return PortalNavigatorAPI
        .buildUrlToPortalCaseDetailsPageByUUID(workingCase.uuid());
  }
}
