package portalmigration.version93.bo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressProcess extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = -7412792174579848169L;
  private String processName;
  private String processDescription;
  private String processType;
  private List<String> processPermissions;
  private String processOwner;
  private boolean isUseDefaultUI;
  private String processFolder;
  private boolean readyToExecute;
  private List<String> processCoOwners;
  private String icon;
  @JsonIgnore
  private boolean isAbleToEdit;
  private List<ExpressTaskDefinition> taskDefinitions;

  public ExpressProcess() {
    setIsPublic(true);
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

  public List<ExpressTaskDefinition> getTaskDefinitions() {
    return taskDefinitions;
  }

  public void setTaskDefinitions(List<ExpressTaskDefinition> taskDefinitions) {
    this.taskDefinitions = taskDefinitions;
  }
}
