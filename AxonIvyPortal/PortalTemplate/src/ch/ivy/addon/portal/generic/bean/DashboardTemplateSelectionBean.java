package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardTemplate;
import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;

@ViewScoped
@ManagedBean
public class DashboardTemplateSelectionBean extends DashboardModificationBean implements Serializable {

  private static final long serialVersionUID = 6975567087140152855L;

  public void onSelectTemplate(DashboardTemplate template, boolean isPublicDashboard) {
    onAddDashboard(isPublicDashboard);
    var storedId = this.selectedDashboard.getId();
    this.selectedDashboard = template.getDashboard();
    mappingCommonDataToNewDashboard(template.getId(), storedId);
    for (DashboardWidget widget : getSelectedDashboard().getWidgets()) {
      widget.setId(DashboardWidgetUtils.generateNewWidgetId(widget.getType()));
    }
  }

  public void createDashboardFromScratch(boolean isPublicDashboard) {
    onAddDashboard(isPublicDashboard);
    var storedId = this.selectedDashboard.getId();
    mappingCommonDataToNewDashboard(DashboardConstants.CREATE_FROM_SCRATCH, storedId);
    this.selectedDashboard.setWidgets(new ArrayList<>());
  }

  private void onAddDashboard(boolean isPublicDashboard) {
    this.selectedDashboardPermissions = new ArrayList<>();
    this.selectedDashboard = new Dashboard();
    this.selectedDashboard.setId(DashboardUtils.generateId());
    this.selectedDashboard.setIsPublic(isPublicDashboard);
    this.isPublicDashboard = isPublicDashboard;
  }

  private void mappingCommonDataToNewDashboard(String templateId, String dashboardId) {
    this.selectedDashboard.setTemplateId(templateId);
    this.selectedDashboard.setId(dashboardId);
    this.selectedDashboard.setTitle(null);
  }
}
