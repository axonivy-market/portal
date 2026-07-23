package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.service.impl.ProcessService;
import com.axonivy.portal.components.util.ProcessViewerUtils;

import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@Named
@ViewScoped
public class DashboardProcessViewerWidgetConfigurationBean implements Serializable {

  private static final long serialVersionUID = -5617205683669511383L;

  private List<DashboardProcess> defaultPortalProcesses;

  public void preRender() {
    List<IWebStartable> processes = ProcessService.getInstance().findProcesses();
    defaultPortalProcesses = processes.stream().filter(process -> ProcessViewerUtils.isViewerAllowed(process))
        .map(DashboardProcess::new).sorted(Comparator.comparing(Process::getName)).collect(Collectors.toList());
  }

  public List<DashboardProcess> completeProcesses(String query) {
    return defaultPortalProcesses.stream().filter(process -> Strings.CI.contains(process.getName(), query))
        .collect(Collectors.toList());
  }
}
