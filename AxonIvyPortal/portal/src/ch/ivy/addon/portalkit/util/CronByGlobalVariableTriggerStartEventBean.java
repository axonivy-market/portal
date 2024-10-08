package ch.ivy.addon.portalkit.util;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.vars.Variable;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.util.IvyRuntimeException;

public class CronByGlobalVariableTriggerStartEventBean extends AbstractProcessStartEventBean {

  private static final String PORTAL_DELETE_ALL_FINISHED_HIDDEN_CASE = "PortalDeleteAllFinishedHiddenCases";
  private static final String VARIABLE = "variable";
  public CronByGlobalVariableTriggerStartEventBean() {
    super("Portal Clean Obsolete Data", "This is a scheduled process of Portal");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    try {
      Variable var =
          Variables.of(eventRuntime.getProcessModelVersion().getApplication()).variable(configuration.get(VARIABLE));
      Variable deleteAllFinishedHiddenCasesVar = Variables.of(eventRuntime.getProcessModelVersion().getApplication())
          .variable(PORTAL_DELETE_ALL_FINISHED_HIDDEN_CASE);
      if (var != null) {
        String pattern = var.value();
        Boolean isJobTrigger = Optional.of(deleteAllFinishedHiddenCasesVar).map(Variable::value).map(Boolean::parseBoolean).orElse(false);
        if (StringUtils.isNotBlank(pattern) && isJobTrigger) {
          eventRuntime.poll().asDefinedByExpression(pattern);
        } else {
          eventRuntime.poll().disable();
        }
      }
    } catch (Exception ex) {
      throw new IvyRuntimeException("Cannot evaluate the ivyScript configuration ", ex);
    }
  }


  @Override
  public void poll() {
    try {
      getEventBeanRuntime().processStarter()
          .withReason("Time elapsed or reached cron pattern " + getConfig().get(VARIABLE)).start();
    } catch (RequestException ex) {
      throw new IvyRuntimeException("Cannot start process", ex);
    }
  }
}
