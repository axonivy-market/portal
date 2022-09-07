package ch.ivy.addon.portalkit.bean;

import ch.ivy.addon.portalkit.service.UserProcessService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.server.restricted.EngineMode;

@SuppressWarnings("restriction")
public class PortalTestInitBean extends AbstractProcessStartEventBean {

  public PortalTestInitBean() {
    super("Init Portal Test", "Init Portal Test");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, String configuration) {
    super.initialize(eventRuntime, configuration);
    getEventBeanRuntime().setPollTimeInterval(0);
    initUserProcesses();
  }

  private void initUserProcesses() {
    if (EngineMode.is(EngineMode.DESIGNER_EMBEDDED)) {
      String defaultUserProcesses =
          "[{\"id\":\"1\",\"processType\":\"IVY_PROCESS\",\"processName\":\"Employee Search\",\"icon\":\"fa-building\",\"processId\":\"designer/portal-developer-examples/Start Processes/CustomCaseInfomationForTaskTemplate/employeeSearch.ivp\",\"index\":1},{\"id\":\"2\",\"processType\":\"IVY_PROCESS\",\"processName\":\"Employee Search in Iframe\",\"icon\":\"fa-list-alt\",\"processId\":\"designer/portal-developer-examples/Start Processes/CustomCaseInfomationForTaskTemplate/employeeSearchInFrame.ivp\",\"index\":2},{\"id\":\"3\",\"processType\":\"IVY_PROCESS\",\"processName\":\"Create Investment\",\"icon\":\"fa-asterisk\",\"processId\":\"designer/portal-developer-examples/Start Processes/IFrameExample/CreateInvestment.ivp\",\"index\":3},{\"id\":\"4\",\"processType\":\"EXPRESS_PROCESS\",\"processName\":\"Favorite Express Process Display Name\",\"icon\":\"fa fa-play\",\"processId\":\"Your Express Process Name\",\"index\":4},{\"id\":\"5\",\"processType\":\"EXTERNAL_LINK\",\"processName\":\"Favorite External Process Display Name\",\"icon\":\"fa fa-play\",\"processId\":\"Your External Link Name\",\"index\":5}]";
      Ivy.var().set(UserProcessService.getInstance().getConfigKey(), defaultUserProcesses);
    }
  }

}
