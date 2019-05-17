package ch.ivy.addon.portalkit.bo;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;

/*
 *Used for merging express process and ivy process into a process list 
 */
public class PortalExpressProcess extends Process {
  private ExpressProcess process;
  private static final String EXPRESS_WORKFLOW_ID_PARAM = "?workflowID=";
  
  public PortalExpressProcess(ExpressProcess process) {
    this.process = process;
  }

  @Override
  public String getStartLink() {
    return generateWorkflowStartLink();
  }

  @Override
  public String getDescription() {
    return process.getProcessDescription();
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
    return ProcessType.EXPRESS_PROCESS.getType();
  }
}
