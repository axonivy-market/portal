package ch.ivy.addon.portalkit.bean;

import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class LocaleBean {
  public String getLocale(){
    try {
      return Ivy.session().getContentLocale().getLanguage();
    } catch (EnvironmentNotAvailableException e) {
      return Locale.ENGLISH.getLanguage();
    }
  }
}
