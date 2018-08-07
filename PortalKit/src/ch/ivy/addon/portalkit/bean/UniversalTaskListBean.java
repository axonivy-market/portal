package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ch.ivy.addon.portalkit.persistence.domain.Application;
import ch.ivy.addon.portalkit.service.ApplicationMultiLanguage;

@ManagedBean
@RequestScoped
public class UniversalTaskListBean {
  public String getDisplayName(Application application){
    return ApplicationMultiLanguage.getDisplayNameInCurrentLocale(application);
  }
} 
