package ch.ivy.addon.portalkit.bo;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.DefaultImage;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.ExpressManagementUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.ISecurityMember;
import ch.ivyteam.ivy.security.IUser;

/*
 * Used for merging express process and ivy process into a process list
 */
public class PortalExpressProcess implements Process {

  public static final String DEFAULT_ICON = "si si-startup-launch";
  private static final String EXPRESS_CATEGORY_PRE_FIX = "ExpressWorkflow";
  private static final String NOT_AVAILABLE_CMS = "/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable";
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  private ExpressProcess process;
  private String processOwnerDisplayName;
  private String ableToStart;
  private String application;

  public PortalExpressProcess(ExpressProcess process) {
    this.process = process;
    String processOwner = ExpressManagementUtils.getValidMemberName(process.getProcessOwner());
    String processOwnerName = StringUtils.isNotBlank(processOwner) ? processOwner.substring(1) : null;

    IUser user = processOwnerName != null ? ISecurityContext.current().users().find(processOwnerName) : null;

    this.processOwnerDisplayName = Optional.ofNullable(user).map(IUser::getDisplayName).orElse(Ivy.cms().co(NOT_AVAILABLE_CMS));
    
    for (String username : this.process.getProcessPermissions()) {
      ISecurityMember assignee = ISecurityContext.current().members().find(username);
      String ableStartName = Optional.ofNullable(assignee).map(ISecurityMember::getDisplayName).orElse(Ivy.cms().co(NOT_AVAILABLE_CMS));
      this.ableToStart = StringUtils.isBlank(ableToStart) ? ableStartName : String.join(";", ableToStart, ableStartName);
    }
    
    application = IApplication.current().getName();
  }

  @Override
  public String getStartLink() {
    return generateWorkflowStartLink();
  }

  @Override
  public String getDescription() {
    StringBuilder builder = new StringBuilder();
    if (StringUtils.isNotBlank(process.getProcessDescription())) {
      builder.append(process.getProcessDescription()).append(". ");
    }
    return builder.append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR"))
        .append(": ")
        .append(this.processOwnerDisplayName)
        .toString();
  }

  private String generateWorkflowStartLink() {
    ProcessStartCollector processStartCollector = new ProcessStartCollector();
    return processStartCollector.findExpressWorkflowStartLink() + EXPRESS_WORKFLOW_ID_PARAM + this.process.getId();
  }

  @Override
  public String getName() {
    return process.getProcessName();
  }

  @Override
  public ExpressProcess getProcess() {
    return process;
  }

  @Override
  public String getId() {
    return process.getId();
  }

  @Override
  public ProcessType getType() {
    return ProcessType.EXPRESS_PROCESS;
  }

  @Override
  public String getTypeName() {
    return ProcessType.EXPRESS_PROCESS.name();
  }

  public String getProcessOwnerDisplayName() {
    return processOwnerDisplayName;
  }

  public void setProcessOwnerDisplayName(String processOwenerDisplayName) {
    this.processOwnerDisplayName = processOwenerDisplayName;
  }

  @Override
  public String getIcon() {
    String icon = this.process.getIcon();
    return StringUtils.isBlank(icon) ? DEFAULT_ICON : icon;
  }
  
  public String getAbleToStart() {
    return ableToStart;
  }

  public void setAbleToStart(String ableToStart) {
    this.ableToStart = ableToStart;
  }

  @Override
  public String getCategory() {
    return EXPRESS_CATEGORY_PRE_FIX + "/" + process.getProcessName();
  }

  @Override
  public String getImageUrl() {
    return DefaultImage.NASASTART.getPath();
  }

  @Override
  public String getDefaultImageSrc() {
    return StringUtils.EMPTY;
  }

  @Override
  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }
}
