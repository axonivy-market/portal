package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getLocale(){
    return Ivy.session().getContentLocale().getLanguage();
  }
}
