package ch.ivy.addon.portalkit.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.axonivy.portal.components.util.ProcessViewerUtils;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivy.addon.portalkit.util.PortalProcessViewerUtils;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
public class ProcessItemActionBean extends ProcessWidgetBean {

  private static final long serialVersionUID = -4320413177325851923L;
  private boolean showProcessViewer;

  @PostConstruct
  public void initAction() {
    showProcessViewer =
        GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.ENABLE_PROCESS_VIEWER);
  }

  public String getProcessViewerPageUri(Process process) {
    var startLink = "";
    if (ProcessType.IVY_PROCESS.equals(process.getType())) {
      var processData = process.getProcess();
      if (processData instanceof IWebStartable) {
        startLink = ((IWebStartable) processData).getLink().getRelative();
      } else if (processData instanceof DashboardProcess) {
        startLink = ((DashboardProcess) processData).getStartLink();
      }
    }
    return PortalProcessViewerUtils.getStartProcessViewerPageUri(startLink);
  }

  public boolean isShowProcessViewer(Process process) {
    if (this.showProcessViewer && ProcessType.IVY_PROCESS.equals(process.getType())) {
      var processData = process.getProcess();
      IWebStartable webStartable = null;
      if (processData instanceof DashboardProcess) {
        webStartable = ProcessViewerUtils.findWebStartable(((DashboardProcess) processData).getStartLink());
      } else {
        webStartable = (IWebStartable) processData;
      }
      return ProcessViewerUtils.isViewerAllowed(webStartable);
    }
    return false;
  }
}
