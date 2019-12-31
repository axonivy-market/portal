package ch.ivy.addon.portalkit.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.PrimeFaces;

import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class ErrorDisplayBean {
  private List<PortalIvyDataException> errors;

  @PostConstruct
  public void init() {
    errors = new ArrayList<>();
  }
  
  public void displayErrors(List<PortalIvyDataException> errors) {
    setErrors(errors);
    if (CollectionUtils.isNotEmpty(errors)) {
      PrimeFaces.current().ajax().update("application-error-dialog-component:application-error-dialog");
      String errorLink = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/found") + " " + errors.size() + Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/clickToShowDetail");
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/errorNotification"), "<span style=\"cursor:pointer\"><a onclick=\"PF('application-error-dialog').show(); hideGrowl()\">"+errorLink+"</a></span>"));
      FacesContext.getCurrentInstance().validationFailed();
    }
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
