package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.dashboard.WidgetFilterModel;
import ch.ivy.addon.portalkit.service.WidgetFilterService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class WidgetFilterHelperBean implements Serializable {

  private static final long serialVersionUID = 7129952876083492724L;

  private WidgetFilterModel saveFilter;
  private String loadFiltersRemoteCommand;

  public void saveNewWidgetFilter() {
    if (isDuplicatedFilter()) {
      FacesContext.getCurrentInstance().validationFailed();
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/taskView/filterExistedValidationError"), null);
      FacesContext.getCurrentInstance().addMessage("save-filter-form", message);
      return;
    }
    
    WidgetFilterService.getInstance().save(saveFilter);
    setSaveFilter(null);
  }

  private boolean isDuplicatedFilter() {
    var result = WidgetFilterService.getInstance().findFiltersByWidgetId(saveFilter.getWidgetId());
    var foundFilter = result.stream().filter(filter -> StringUtils.equalsIgnoreCase(filter.getName(), saveFilter.getName()))
        .findFirst().orElse(null);
    return foundFilter == null ? false : true;
  }

  public String getLoadWidgetFilters() {
    if (StringUtils.isNotEmpty(loadFiltersRemoteCommand)) {
      return loadFiltersRemoteCommand.concat("()");
    }
    return "";
  }

  public WidgetFilterModel getSaveFilter() {
    return saveFilter;
  }

  public void setSaveFilter(WidgetFilterModel saveFilter) {
    this.saveFilter = saveFilter;
  }

  public String getLoadFiltersRemoteCommand() {
    return loadFiltersRemoteCommand;
  }

  public void setLoadFiltersRemoteCommand(String loadFiltersRemoteCommand) {
    this.loadFiltersRemoteCommand = loadFiltersRemoteCommand;
  }
}
