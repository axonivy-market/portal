package ch.ivy.addon.portalkit.util;

import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ui.ExtensionUiBuilder;
import ch.ivyteam.ivy.process.extension.ui.IUiFieldEditor;
import ch.ivyteam.ivy.process.extension.ui.UiEditorExtension;
import ch.ivyteam.ivy.request.RequestException;
import ch.ivyteam.ivy.vars.Variable;
import ch.ivyteam.ivy.vars.Variables;
import ch.ivyteam.util.IvyRuntimeException;

/**
 * A bean that starts processes periodically with a defined delay or at certain times defined by a daily time or CRON
 * expression. The configuration can either be configured directly or indirectly via a variable. If the variable changes
 * the bean is reconfigured automatically.
 **/

public class CronByGlobalVariableTriggerStartEventBean extends AbstractProcessStartEventBean {

  public CronByGlobalVariableTriggerStartEventBean() {
    super("CronTrigger", "Description of CronTrigger");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, String configuration) {
    super.initialize(eventRuntime, configuration);
    try {
      Variable var = Variables.of(eventRuntime.getProcessModelVersion().getApplication()).variable(configuration);
      if (var != null) {
        String pattern = var.value();
        if (pattern != null && pattern.length() > 0) {
          eventRuntime.poll().asDefinedByExpression(pattern);
        }
      }
    } catch (Exception e) {
      getEventBeanRuntime().getRuntimeLogLogger().error(e);
    }
  }

  @Override
  public void poll() {
    try {
      getEventBeanRuntime().processStarter().withReason("Time elapsed or reached cron pattern" + getConfiguration())
          .start();
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

    private IUiFieldEditor globalVariable;

    @Override
    public void initUiFields(ExtensionUiBuilder ui) {
      globalVariable = ui.textField().create();
    }

    @Override
    public String getConfiguration() {
      return globalVariable.getText();
    }

    @Override
    public void setConfiguration(String configString) {
      globalVariable.setText(configString);
    }
  }
}
