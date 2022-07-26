package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.ProcessViewerDashboardWidget;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardProcessViewerWidgetBean implements Serializable {

  private static final long serialVersionUID = -5617205683669511383L;

  private List<IWebStartable> allPortalProcesses;
  private List<IProcessStart> startableProcessStarts;

  @PostConstruct
  public void init() {
    allPortalProcesses = ProcessService.newInstance().findProcesses().getProcesses();
    startableProcessStarts = Ivy.session().getStartableProcessStarts();
  }

  public List<IWebStartable> completeProcesses(String query) {
    return allPortalProcesses.stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query)).collect(Collectors.toList());
  }

  public void onSelectProcess(ProcessViewerDashboardWidget widget) {
    widget.setProcessStart(getStartableProcessStarts().stream()
        .filter(process -> process.getLink().getRelative().contentEquals(widget.getStartableProcessStart().getLink().getRelative()))
        .findFirst().get().getUserFriendlyRequestPath());
  }

  public List<IProcessStart> getStartableProcessStarts() {
    return startableProcessStarts;
  }

  public void setStartableProcessStarts(List<IProcessStart> startableProcessStarts) {
    this.startableProcessStarts = startableProcessStarts;
  }
}
