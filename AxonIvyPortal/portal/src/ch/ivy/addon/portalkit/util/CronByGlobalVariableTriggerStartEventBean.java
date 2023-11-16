package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.eventstart.beans.TimerBean;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.process.extension.ui.ExtensionUiBuilder;
import ch.ivyteam.ivy.process.extension.ui.UiEditorExtension;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.vars.Variable;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.util.IvyRuntimeException;


/**
 * @deprecated reference to {@link TimerBean}.
 */

@Deprecated(since = "11.3")
public class CronByGlobalVariableTriggerStartEventBean extends AbstractProcessStartEventBean {
  private static final String VARIABLE = "variable";
  private static String name;
  private static String description;
  public CronByGlobalVariableTriggerStartEventBean() {
    super(name, description);
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig configuration) {
    super.initialize(eventRuntime, configuration);
    name = eventRuntime.getProcessStart().getName();
    description = eventRuntime.getProcessStart().getDescription();
    try {
      Variable var =
          Variables.of(eventRuntime.getProcessModelVersion().getApplication()).variable(configuration.get(VARIABLE));
      if (var != null) {
        String pattern = var.value();
        if (pattern != null && pattern.length() > 0) {
          eventRuntime.poll().asDefinedByExpression(pattern);
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

  /**
   * Editor class to work with the configuration.
   *
   * @author maonguyen
   */
  public static class Editor extends UiEditorExtension {

    @Override
    public void initUiFields(ExtensionUiBuilder ui) {
      ui.textField(VARIABLE).create();
    }
  }
}
