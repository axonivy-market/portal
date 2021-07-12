package ch.ivy.addon.portalkit.enums;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.process.call.SubProcessCall;

public enum PortalLibrary {
  PORTAL_STYLE("portalStyle"),
  PORTAL_KIT("portalKit"), 
  PORTAL_TEMPLATE("portalTemplate"),
  AXON_EXPRESS("axonIvyExpress"),
  SELF_SERVICE("selfService");
  private String value;

  private PortalLibrary(String value) {
    this.value = value;
  }

  public String getValue() {
    return IvyExecutor.executeAsSystem(() -> {
      String groupId = SubProcessCall.withPath(PortalConstants.GET_GROUP_ID_CALLABLE)
          .withStartName("getGroupId")
          .call()
          .get("groupId", String.class);
      
      if (StringUtils.isBlank(groupId)) {
        groupId = "ch.ivyteam.ivy.project.portal";
      }
      
      return String.format("%s:%s", groupId, value);
    });
    
  }
  
  public String getProjectId() {
    return value;
  }
}

