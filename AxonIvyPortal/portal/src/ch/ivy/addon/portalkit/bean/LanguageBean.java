package ch.ivy.addon.portalkit.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@RequestScoped
public class LanguageBean {
  private String language;

  public String getLanguage() {
      return language;
  }

  public void setLanguage(String language) {
      this.language = language;
  }
  
  public void init() {
    String lang = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lang");
    String cmsLang = Ivy.cms().coLocale("/ch.ivy.addon.portalkit.ui.jsf/common/sessionExpried", lang);
    Ivy.log().info(cmsLang);
    setLanguage(lang);
  }
}
