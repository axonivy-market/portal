package ch.ivy.addon.portalkit.bo;

public class ExpressProcess {

  private String id;
  private String processName;
  private String processDescription;
  private String processType;
  private String processPermission;
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

  public String getProcessPermission() {
    return processPermission;
  }

  public void setProcessPermission(String processPermission) {
    this.processPermission = processPermission;
  }

  public String getProcessOwner() {
    return processOwner;
  }

  public void setProcessOwner(String processOwner) {
    this.processOwner = processOwner;
  }

}
