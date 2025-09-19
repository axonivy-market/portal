package ch.ivy.addon.portal.generic.bean;

import java.util.List;

import com.axonivy.portal.service.IvyTranslationService;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.DisplayNameConvertor;
import ch.ivy.addon.portalkit.util.UserUtils;

public interface IMultiLanguage {

  default boolean isShowTranslation(DisplayName title) {
    return IvyTranslationService.getInstance().isShowTranslation(title.getLocale());
  }

  default boolean isFocus(DisplayName title) {
    return !isShowTranslation(title) && title.getLocale().getLanguage().equals(UserUtils.getUserLanguage());
  }
  
  default boolean isRequiredField(DisplayName displayName) {
    String currentLanguage = UserUtils.getUserLanguage();
    String displayLanguage = displayName.getLocale().getLanguage();
    return currentLanguage.equals(displayLanguage);
  }
  
  default void initAndSetValue(String value, List<DisplayName> values) {
    DisplayNameConvertor.initMultipleLanguages(value, values);
    DisplayNameConvertor.setValue(value, values);
  }  
}
