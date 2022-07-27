package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardProcessViewerWidgetConfigurationBean implements Serializable {

  private static final long serialVersionUID = -5617205683669511383L;

  private List<DashboardProcess> defaultPortalProcesses;

  public void preRender() {
    List<IWebStartable> processes = ProcessService.newInstance().findProcesses().getProcesses();
    defaultPortalProcesses  = processes.stream().map(DashboardProcess::new)
        .sorted(Comparator.comparing(Process::getName))
        .collect(Collectors.toList());
  }

  public List<DashboardProcess> completeProcesses(String query) {
    return defaultPortalProcesses.stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query)).collect(Collectors.toList());
  }
}
