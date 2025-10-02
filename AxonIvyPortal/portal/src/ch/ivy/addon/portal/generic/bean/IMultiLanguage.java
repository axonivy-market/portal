package ch.ivy.addon.portal.generic.bean;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;

import com.axonivy.portal.service.DeepLTranslationService;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.ivydata.service.impl.LanguageService;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.environment.Ivy;

public interface IMultiLanguage {

  default boolean isShowTranslation(DisplayName title) {
    return DeepLTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  default boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }
  
  default boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = getSupportedUserLanguage().getLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }
  
  default void initAndSetValue(String value, List<DisplayName> values) {
    DisplayNameConvertor.initMultipleLanguages(value, values);
    DisplayNameConvertor.setValue(value, values);
  }
  
  default Locale getSupportedUserLanguage() {
    String supportedPortalUserLanguage = LanguageService.getInstance().getSupportedLanguageInPortal();
    Ivy.log().info(supportedPortalUserLanguage);
    return LocaleUtils.toLocale(supportedPortalUserLanguage);
  }
}
