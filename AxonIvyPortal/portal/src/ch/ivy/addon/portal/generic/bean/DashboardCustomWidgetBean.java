package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import com.axonivy.portal.components.service.impl.ProcessService;

import ch.ivy.addon.portalkit.dto.dashboard.CustomDashboardWidgetParam;
import ch.ivy.addon.portalkit.enums.DashboardCustomWidgetType;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

@ManagedBean
@ViewScoped
public class DashboardCustomWidgetBean implements Serializable {

  private static final long serialVersionUID = 7637567927058415789L;

  private DashboardCustomWidgetType selectedType = DashboardCustomWidgetType.EXTERNAL_URL;
  private DashboardCustomWidgetType[] customWidgetTypes = DashboardCustomWidgetType.values();
  private List<IWebStartable> allCustomDashboardProcesses;

  public String generateParamName(CustomDashboardWidgetParam param) {
    return param.getType().getPrefix().concat(param.getName());
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

  public List<IWebStartable> getAllCustomDashboardProcesses() {
    if (CollectionUtils.isEmpty(allCustomDashboardProcesses)) {
      allCustomDashboardProcesses = ProcessService.getInstance().findCustomDashboardProcesses();
    }
    return allCustomDashboardProcesses;
  }
  
  public String encodeValue(String value) throws UnsupportedEncodingException {
    return URLEncoder.encode(value, "UTF-8");
  }
}
