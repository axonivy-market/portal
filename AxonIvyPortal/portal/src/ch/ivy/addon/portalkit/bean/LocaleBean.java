package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@SessionScoped
public class LocaleBean implements Serializable {
  private static final long serialVersionUID = 1L;

  public String getLocale(){
    
    if (Ivy.session().getFormattingLocale() != null && StringUtils.isNotBlank(Ivy.session().getFormattingLocale().toString())) {
      return Ivy.session().getFormattingLocale().toString();
    } 
    return LanguageService.getInstance().getUserLocale().toLanguageTag();
  }
}
