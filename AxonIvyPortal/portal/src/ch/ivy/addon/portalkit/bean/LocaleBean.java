package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getLocale(){
    
    if (Ivy.session().getFormattingLocale() != null && StringUtils.isNotBlank(Ivy.session().getFormattingLocale().toString())) {
      return Ivy.session().getFormattingLocale().toString();
    } 
    return Ivy.session().getContentLocale().toString();
  }
}
