package ch.ivy.addon.portalkit.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExpressProcess {

  private String id;
  private String processName;
  private String processDescription;
  private String processType;
  private List<String> processPermissions;
  //processOwner is processCreator
  private String processOwner;
  private boolean isUseDefaultUI;
  private String processFolder;
  private boolean readyToExecute;
  private List<String> processCoOwners;
  private String icon;
  
  @JsonIgnore
  private boolean isAbleToEdit;
  
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

  public List<String> getProcessPermissions() {
    return processPermissions;
  }

  public void setProcessPermissions(List<String> processPermissions) {
    this.processPermissions = processPermissions;
  }

  public String getProcessOwner() {
    return processOwner;
  }

  public void setProcessOwner(String processOwner) {
    this.processOwner = processOwner;
  }

  public boolean isUseDefaultUI() {
    return isUseDefaultUI;
  }

  public void setUseDefaultUI(boolean isUseDefaultUI) {
    this.isUseDefaultUI = isUseDefaultUI;
  }

  public String getProcessFolder() {
    return processFolder;
  }

  public void setProcessFolder(String processFolder) {
    this.processFolder = processFolder;
  }

  public boolean isReadyToExecute() {
    return readyToExecute;
  }

  public void setReadyToExecute(boolean readyToExecute) {
    this.readyToExecute = readyToExecute;
  }

  public boolean isAbleToEdit() {
    return isAbleToEdit;
  }

  public void setAbleToEdit(boolean isAbleToEdit) {
    this.isAbleToEdit = isAbleToEdit;
  }

  public List<String> getProcessCoOwners() {
    return processCoOwners;
  }

  public void setProcessCoOwners(List<String> processCoOwners) {
    this.processCoOwners = processCoOwners;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
  
}
