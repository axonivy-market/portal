package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.dto.dashboard.DashboardWidget;
import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@ViewScoped
public class WidgetFilterHelperBean implements Serializable {

  private static final long serialVersionUID = 7129952876083492724L;

  private WidgetFilterModel saveFilter;
  private String newFilterName;

  public void saveInlineWidgetFilter(DashboardWidget widget) {
    WidgetFilterService.getInstance().prepareSaveFilter(widget);
    if (saveFilter == null) {
      return;
    }
    saveFilter.setName(StringUtils.trim(newFilterName));

    if (isDuplicatedFilter()) {
      FacesContext context = FacesContext.getCurrentInstance();
      context.validationFailed();
      context.addMessage(resolveNameInputClientId(context),
          FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR,
              Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), null));
      return;
    }

    WidgetFilterService.getInstance().save(saveFilter);
    setSaveFilter(null);
    newFilterName = null;
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
