package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.ProcessViewerUtils;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
public class ProcessItemActionBean extends ProcessWidgetBean {

  private static final long serialVersionUID = -4320413177325851923L;
  private boolean showProcessViewer;

  @PostConstruct
  public void initAction() {
    showProcessViewer = GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER);
  }

  public String getProcessViewerPageUri(Process process) {
    var startLink = "";
    if (process.getType() == ProcessType.IVY_PROCESS) {
      var processData = process.getProcess();
      if (processData instanceof IWebStartable) {
        startLink = ((IWebStartable) processData).getLink().getRelative();
      }
      else if (processData instanceof DashboardProcess) {
        startLink = ((DashboardProcess) processData).getStartLink();
      }
    }
    return ProcessViewerUtils.getStartProcessViewerPageUri(startLink);
  }

  public boolean isShowProcessViewer(Process process) {
    return this.showProcessViewer && process.getType() == ProcessType.IVY_PROCESS
        && !isCaseMap(process);
  }
}
