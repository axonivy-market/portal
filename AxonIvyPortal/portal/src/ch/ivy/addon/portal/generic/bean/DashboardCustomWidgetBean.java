package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessStartDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.ProcessService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.IProcessStart;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardCustomWidgetBean implements Serializable {

  private static final long serialVersionUID = 7637567927058415789L;
  private static final String IS_DASHBOARD_PROCESS = "isDashboardProcess";

  private DashboardCustomWidgetType selectedType = DashboardCustomWidgetType.EXTERNAL_URL;
  private DashboardCustomWidgetType[] customWidgetTypes = DashboardCustomWidgetType.values();
  private String process;
  private List<IWebStartable> allPortalProcesses;
  private List<IProcessStart> startableProcessStarts;

  public void onSelectProcess(CustomDashboardWidget widget) {
    var ivyProcessStartDTO = widget.getData().getIvyProcessStartDTO();
    if (Objects.isNull(ivyProcessStartDTO) || Objects.isNull(ivyProcessStartDTO.getStartableProcessStart())) {
      return;
    }
    var processStart = getStartableProcessStarts().stream()
        .filter(process -> process.getLink().getRelative().equals(widget.getData().getIvyProcessStartDTO().getStartableProcessStart().getLink().getRelative()))
        .findAny().get();
    widget.getData().setProcessPath(ivyProcessStartDTO.getStartableProcessStart().getId());
    widget.getData().setStartRequestPath(Optional.of(processStart).map(IProcessStart::getRequestPath).get());
    widget.loadParametersFromProcess();
  }

  public void onChangeType(CustomDashboardWidget widget) {
    if (widget.getData().getType() == DashboardCustomWidgetType.EXTERNAL_URL) {
      widget.getData().setUrl(null);
      widget.getData().setParams(null);
      widget.getData().setHasParamChanged(false);
      widget.getData().setProcessPath(null);
      widget.getData().setStartRequestPath(null);
      widget.getData().getIvyProcessStartDTO().setStartableProcessStart(null);
      widget.getData().setStartProcessParams(null);
    }
  }

  public String generateParamName(CustomDashboardWidgetParam param) {
    return param.getType().getPrefix().concat(param.getName());
  }

  public List<IvyProcessStartDTO> completeProcesses(String query) {
    List<IWebStartable> processes = getAllPortalProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query)).collect(Collectors.toList());
    List<IvyProcessStartDTO> result = new ArrayList<>();
    for (IWebStartable webStartable : processes) {
      IvyProcessStartDTO ivyProcessStartDTO = new IvyProcessStartDTO();
      ivyProcessStartDTO.setStartableProcessStart(webStartable);
      result.add(ivyProcessStartDTO);
    }
    return result;
  }

  public DashboardCustomWidgetType getSelectedType() {
    return selectedType;
  }

  public void setSelectedType(DashboardCustomWidgetType selectedType) {
    this.selectedType = selectedType;
  }

  public DashboardCustomWidgetType[] getCustomWidgetTypes() {
    return customWidgetTypes;
  }

  public void setCustomWidgetTypes(DashboardCustomWidgetType[] customWidgetTypes) {
    this.customWidgetTypes = customWidgetTypes;
  }

  public String getProcess() {
    return process;
  }

  public void setProcess(String process) {
    this.process = process;
  }

  public List<IWebStartable> getAllPortalProcesses() {
    if (CollectionUtils.isEmpty(allPortalProcesses)) {
      allPortalProcesses = ProcessService.newInstance().findProcesses()
          .getProcesses().stream()
          .filter(proccess -> StringUtils.isNotBlank(proccess.customFields().value(IS_DASHBOARD_PROCESS))
              && proccess.customFields().value(IS_DASHBOARD_PROCESS).contentEquals("true"))
          .collect(Collectors.toList());
    }
    return allPortalProcesses;
  }

  public List<IProcessStart> getStartableProcessStarts() {
    if (CollectionUtils.isEmpty(startableProcessStarts)) {
      startableProcessStarts = Ivy.session().getStartableProcessStarts();
    }
    return startableProcessStarts;
  }
}
