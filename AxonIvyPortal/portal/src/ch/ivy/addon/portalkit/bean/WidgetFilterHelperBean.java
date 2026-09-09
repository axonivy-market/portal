package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.FilterColumnModel;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class WidgetFilterHelperBean implements Serializable {

  private static final long serialVersionUID = 7129952876083492724L;

  private WidgetFilterModel saveFilter;
  private String newFilterName;

  public void saveInlineWidgetFilter(DashboardWidget widget) {
    WidgetFilterService.getInstance().prepareSaveFilter(widget);
    if (saveFilter == null || !hasFilterCriteria(saveFilter)) {
      addSaveFilterError("/ch.ivy.addon.portalkit.ui.jsf/dashboard/Filter/EmptyFilterValidationError");
      return;
    }
    saveFilter.setName(StringUtils.trim(newFilterName));

    if (isDuplicatedFilter()) {
      addSaveFilterError("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError");
      return;
    }

    WidgetFilterService.getInstance().save(saveFilter);
    setSaveFilter(null);
    newFilterName = null;
  }

  private boolean hasFilterCriteria(WidgetFilterModel filter) {
    if (CollectionUtils.isNotEmpty(filter.getUserFilters())) {
      return true;
    }
    return CollectionUtils.emptyIfNull(filter.getFilterableColumns()).stream().anyMatch(this::hasUserFilterValue);
  }

  private boolean hasUserFilterValue(FilterColumnModel column) {
    return StringUtils.isNotBlank(column.getUserFilter())
        || CollectionUtils.isNotEmpty(column.getUserFilterList())
        || StringUtils.isNotBlank(column.getUserFilterFrom())
        || StringUtils.isNotBlank(column.getUserFilterTo());
  }

  private void addSaveFilterError(String cmsUri) {
    FacesContext context = FacesContext.getCurrentInstance();
    context.validationFailed();
    context.addMessage(resolveNameInputClientId(context), FacesMessageUtils
        .sanitizedMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co(cmsUri), null));
  }

  private String resolveNameInputClientId(FacesContext context) {
    return UIComponent.getCurrentComponent(context)
        .findComponent("inline-save-filter-name").getClientId(context);
  }

  private boolean isDuplicatedFilter() {
    var result = WidgetFilterService.getInstance().findFiltersByWidgetId(saveFilter.getWidgetId());
    var foundFilter = result.stream().filter(filter -> Strings.CI.equals(filter.getName(), saveFilter.getName()))
        .findFirst().orElse(null);
    return foundFilter == null ? false : true;
  }

  public WidgetFilterModel getSaveFilter() {
    return saveFilter;
  }

  public void setSaveFilter(WidgetFilterModel saveFilter) {
    this.saveFilter = saveFilter;
  }

  public String getNewFilterName() {
    return newFilterName;
  }

  public void setNewFilterName(String newFilterName) {
    this.newFilterName = newFilterName;
  }
}
