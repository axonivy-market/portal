package ch.ivy.addon.portalkit.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portal.generic.navigation.PortalNavigator;
import ch.ivy.addon.portalkit.bo.ExternalLinkProcessItem;
import ch.ivy.addon.portalkit.bo.Process;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.dashboard.ProcessDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.process.DashboardProcess;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.enums.ProcessWidgetMode;
import ch.ivy.addon.portalkit.service.ExternalLinkService;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardProcessBean extends AbstractProcessBean implements Serializable {

  private static final long serialVersionUID = -6664090186198762432L;
  private List<ProcessWidgetMode> displayModes;
  private ProcessDashboardWidget widget;
  private PropertyChangeSupport propertyChangeSupport;
  private List<String> applications;
  private Boolean isPublicDashboard;

  @PostConstruct
  public void initBean() {
    super.init();
    displayModes = Arrays.asList(ProcessWidgetMode.values()).stream()
        .sorted((mode1, mode2) -> mode1.getLabel().compareToIgnoreCase(mode2.getLabel()))
        .collect(Collectors.toList());
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void initPortalDashboardProcesses(Boolean isPublicDashboard) {
    this.isPublicDashboard = isPublicDashboard;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    if (propertyChangeSupport.getPropertyChangeListeners().length == 0) {
      propertyChangeSupport.addPropertyChangeListener(listener);
    }
  }

  public void updateWidget(ProcessDashboardWidget newWidget) {
    propertyChangeSupport.firePropertyChange("widget", null, newWidget);
    setWidget(newWidget);
  }

  public Function<? super Process, ? extends DashboardProcess> toDashboardProcess() {
    return process -> new DashboardProcess(process);
  }

  @Override
  protected List<Process> findProcesses() {
    List<IWebStartable> processes = ProcessService.getInstance().findProcesses();
    List<Process> defaultPortalProcesses = new ArrayList<>();
    // TODO fix static ProcessService#ivyProcessResultDTO, maybe cause error when Jmeter 10 user NavigateToGlobalSearch
    if (CollectionUtils.isNotEmpty(processes)) {
      processes.forEach(process -> defaultPortalProcesses.add(new DashboardProcess(process)));
    }
    return defaultPortalProcesses;
  }

  public void preRender(ProcessDashboardWidget widget) {
    this.widget = widget;
  }

  public void preview() {
    widget.setPreview(true);
  }

  public void selectProcessMode(ProcessWidgetMode mode) {
    this.widget.setDisplayMode(mode);
  }

  public ProcessDashboardWidget getWidget() {
    return widget;
  }

  public void setWidget(ProcessDashboardWidget widget) {
    this.widget = widget;
  }

  public List<ProcessWidgetMode> getDisplayModes() {
    return displayModes;
  }

  public void setDisplayModes(List<ProcessWidgetMode> displayModes) {
    this.displayModes = displayModes;
  }

  public boolean isExternalLink(DashboardProcess process) {
    return process != null && process.getType() == ProcessType.EXTERNAL_LINK;
  }

  public String targetToStartProcess(DashboardProcess process) {
    return isExternalLink(process) ? "_blank" : "_self";
  }

  protected void redirectToLink(String link, boolean isEmbedInFrame) throws IOException {
    if (isEmbedInFrame) {
      link += (link.contains("?") ? "&" : "?" + "embedInFrame");
    } 
    FacesContext.getCurrentInstance().getExternalContext().redirect(link);
  }
  
  protected String getRedirectLink(String link, boolean isEmbedInFrame) {
    if (isEmbedInFrame) {
      link += (link.contains("?") ? "&" : "?" + "embedInFrame");
    }
    return link;
  }

  public boolean isCaseMap(DashboardProcess process) {
    return !Objects.isNull(process) && process.getStartLink().endsWith(".icm");
  }

  public String getProcessInformationPageUrl(DashboardProcess process) {
    return PortalNavigator.buildProcessInfoUrl(process.getId());
  }

  public List<DashboardProcess> getPortalDashboardProcesses() {
    if (CollectionUtils.isEmpty(getPortalProcesses())) {
      super.init();
    }
    return getPortalProcesses().stream()
        .filter(process -> StringUtils.isBlank(process.getApplication()) || CollectionUtils.isEmpty(applications) || applications.contains(process.getApplication()))
        .map(toDashboardProcess()).collect(Collectors.toList());
  }

  @Override
  protected List<Process> findExternalLink() {
    List<ExternalLink> externalLinks = new ArrayList<>();
    if (Objects.isNull(this.isPublicDashboard) || !this.isPublicDashboard) {
      externalLinks.addAll(ExternalLinkService.getInstance().getPrivateConfig());
    }
    externalLinks.addAll(ExternalLinkService.getInstance().filterPublicExternalLinksForIvySessionUser());
    List<Process> defaultPortalProcesses = new ArrayList<>();
    externalLinks.forEach(externalLink -> defaultPortalProcesses.add(new ExternalLinkProcessItem(externalLink)));
    return defaultPortalProcesses;
  }

  public List<String> getApplications() {
    return applications;
  }

  public void setApplications(List<String> applications) {
    this.applications = applications;
  }
}
