package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivyteam.ivy.environment.EnvironmentNotAvailableException;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getLocale(){
    try {
      return Ivy.session().getContentLocale().getLanguage();
    } catch (EnvironmentNotAvailableException e) {
      Ivy.log().error(e);
      return Locale.ENGLISH.getLanguage();
    }
  }
}
