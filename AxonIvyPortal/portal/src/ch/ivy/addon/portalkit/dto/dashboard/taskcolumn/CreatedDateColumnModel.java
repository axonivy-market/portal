package ch.ivy.addon.portalkit.dto.dashboard.taskcolumn;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.axonivy.portal.components.util.FacesMessageUtils;

import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardTaskColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ITask;

public class CreatedDateColumnModel extends TaskColumnModel implements Serializable {

  private static final long serialVersionUID = -4315469062114036720L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardTaskColumn.CREATED.getField();
    this.styleToDisplay = initDefaultStyle();
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/taskList/defaultColumns/CREATION_TIME";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.TIMESTAMP;
  }

  @Override
  protected int getDefaultColumnWidth() {
    return SMALL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-tasks__created-date text-center widget-column";
  }

  @Override
  public Object display(ITask task) {
    if (task == null) {
      return null;
    }
    return task.getStartTimestamp();
  }

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value != null) {
      LocalDate inputDate = Instant.ofEpochMilli(((Date) value).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
      if (inputDate.getYear() > 9999) {
        String message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/Filter/WrongDateFormat");
        var faceMessage = FacesMessageUtils.sanitizedMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().validationFailed();
        throw new ValidatorException(faceMessage);
      }
    }
  }

}
