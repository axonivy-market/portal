package ch.ivy.addon.portalkit.bo;

import java.util.List;

import ch.ivyteam.ivy.security.ISecurityMember;

public class ExpressProcess {

  private String id;
  private String processName;
  private String processDescription;
  private String processType;
  private List<ISecurityMember> processPermissions;
  private String processOwner;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public String getProcessDescription() {
    return processDescription;
  }

  public void setProcessDescription(String processDescription) {
    this.processDescription = processDescription;
  }

  public String getProcessType() {
    return processType;
  }

  public void setProcessType(String processType) {
    this.processType = processType;
  }

  public List<ISecurityMember> getProcessPermissions() {
    return processPermissions;
  }

  public void setProcessPermissions(List<ISecurityMember> processPermissions) {
    this.processPermissions = processPermissions;
  }

  public String getProcessOwner() {
    return processOwner;
  }

  public void setProcessOwner(String processOwner) {
    this.processOwner = processOwner;
  }

}
