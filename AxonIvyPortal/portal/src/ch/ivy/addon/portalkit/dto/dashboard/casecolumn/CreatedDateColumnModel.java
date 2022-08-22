package ch.ivy.addon.portalkit.dto.dashboard.casecolumn;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ch.ivy.addon.portalkit.constant.DashboardConfigurationPrefix;
import ch.ivy.addon.portalkit.enums.DashboardColumnFormat;
import ch.ivy.addon.portalkit.enums.DashboardStandardCaseColumn;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

public class CreatedDateColumnModel extends CaseColumnModel implements Serializable {

  private static final long serialVersionUID = 4220718522071199773L;

  @Override
  public void initDefaultValue() {
    super.initDefaultValue();
    this.field = DashboardStandardCaseColumn.CREATED.getField();
    this.style = defaultIfEmpty(this.style, getDefaultStyle());
    this.styleClass = defaultIfEmpty(this.styleClass, getDefaultStyleClass());
    this.format = getDefaultFormat();
  }

  @Override
  public String getHeaderText() {
    return translateHeader(defaultIfEmpty(this.header, DashboardConfigurationPrefix.CMS + getDefaultHeaderCMS()));
  }

  @Override
  public String getDefaultHeaderCMS() {
    return "/ch.ivy.addon.portalkit.ui.jsf/caseList/defaultColumns/CREATION_TIME";
  }

  @Override
  public DashboardColumnFormat getDefaultFormat() {
    return DashboardColumnFormat.TIMESTAMP;
  }

  @Override
  public String getDefaultStyle() {
    return SMALL_WIDTH;
  }

  @Override
  public String getDefaultStyleClass() {
    return "dashboard-cases__created-date u-text-align-center";
  }

  @Override
  public Object display(ICase caze) {
    if (caze == null) {
      return null;
    }
    return caze.getStartTimestamp();
  }

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value != null) {
      LocalDate inputDate = Instant.ofEpochMilli(((Date) value).getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
      if (inputDate.getYear() > 9999) {
        String message = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/dashboard/Filter/WrongDateFormat");
        var faceMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().validationFailed();
        throw new ValidatorException(faceMessage);
      }
    }
  }
}
