package com.axonivy.portal.component.enums;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.constant.PortalComponentConstants;
import com.axonivy.portal.component.util.IvyExecutor;
import ch.ivyteam.ivy.process.call.SubProcessCall;

public enum PortalLibrary {
  PORTAL("portal"),
  AXON_EXPRESS("axonIvyExpress");
  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return IvyExecutor.executeAsSystem(() -> {
      String groupId = SubProcessCall.withPath(PortalComponentConstants.GET_GROUP_ID_CALLABLE)
          .withStartName("getGroupId")
          .call()
          .get("groupId", String.class);

      if (StringUtils.isBlank(groupId)) {
        groupId = value.equalsIgnoreCase("portal") ? "com.axonivy.portal" : "ch.ivyteam.ivy.project.portal";
      }

      return String.format("%s:%s", groupId, value);
    });
    
  }
  
  public String getProjectId() {
    return value;
  }
}

