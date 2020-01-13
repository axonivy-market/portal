package ch.ivy.addon.portalkit.bo;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

/*
 * Used for merging express process and ivy process into a process list
 */
public class PortalExpressProcess implements Process {
  private ExpressProcess process;
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  private String processOwnerDisplayName;

  public PortalExpressProcess(ExpressProcess process) {
    this.process = process;
    IUser user = Ivy.session().getSecurityContext()
        .findUser(StringUtils.isNotBlank(process.getProcessOwner()) ? process.getProcessOwner().substring(1) : StringUtils.EMPTY);
    this.processOwnerDisplayName = Optional.ofNullable(user).map(IUser::getDisplayName).orElse(StringUtils.EMPTY);
  }

  @Override
  public String getStartLink() {
    return generateWorkflowStartLink();
  }

  @Override
  public String getDescription() {
    return new StringBuilder()
        .append(process.getProcessDescription())
        .append(". ")
        .append(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATOR"))
        .append(": ")
        .append(this.processOwnerDisplayName)
        .toString();
  }

  private String generateWorkflowStartLink() {
    ProcessStartCollector processStartCollector = new ProcessStartCollector(Ivy.request().getApplication());
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
}
