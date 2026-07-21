package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@SessionScoped
public class LocaleBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public Locale getLocale(){
    
    if (Ivy.session().getFormattingLocale() != null && StringUtils.isNotBlank(Ivy.session().getFormattingLocale().toString())) {
      return Ivy.session().getFormattingLocale();
    } 
    return LanguageService.getInstance().getUserLocale();
  }
}
