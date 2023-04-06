package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivy.addon.portalkit.ivydata.dto.IvyProcessStartDTO;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardCustomWidgetBean implements Serializable {

  private static final long serialVersionUID = 7637567927058415789L;

  private DashboardCustomWidgetType selectedType = DashboardCustomWidgetType.EXTERNAL_URL;
  private DashboardCustomWidgetType[] customWidgetTypes = DashboardCustomWidgetType.values();
  private String process;
  private List<IWebStartable> allCustomDashboardProcesses;

  public void onSelectProcess(CustomDashboardWidget widget) {
    var ivyProcessStartDTO = widget.getData().getIvyProcessStartDTO();
    if (Objects.isNull(ivyProcessStartDTO) || Objects.isNull(ivyProcessStartDTO.getStartableProcessStart())) {
      return;
    }
    widget.getData().setProcessPath(ivyProcessStartDTO.getStartableProcessStart().getId());
    widget.getData().setStartRequestPath(ivyProcessStartDTO.getStartableProcessStart().getLink().getRelative());
    widget.loadParametersFromProcess();
  }

  public void onChangeType(CustomDashboardWidget widget) {
    if (widget.getData().getType() == DashboardCustomWidgetType.EXTERNAL_URL) {
      widget.getData().setUrl(null);
      widget.getData().setParams(null);
      widget.getData().setHasParamChanged(false);
      widget.getData().setProcessPath(null);
      widget.getData().setStartRequestPath(null);
    }
  }

  public String generateParamName(CustomDashboardWidgetParam param) {
    return param.getType().getPrefix().concat(param.getName());
  }

  public List<IvyProcessStartDTO> completeProcesses(String query) {
    return getAllCustomDashboardProcesses().stream()
        .filter(process -> StringUtils.containsIgnoreCase(process.getName(), query))
        .map(IvyProcessStartDTO::new)
        .collect(Collectors.toList());
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

  public List<IWebStartable> getAllCustomDashboardProcesses() {
    if (CollectionUtils.isEmpty(allCustomDashboardProcesses)) {
      allCustomDashboardProcesses = ProcessService.getInstance().findCustomDashboardProcesses();
    }
    return allCustomDashboardProcesses;
  }

}
