package ch.ivy.addon.portalkit.ivydata.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import javax.faces.model.SelectItem;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.language.LanguageConfigurator;
import ch.ivyteam.ivy.language.LanguageManager;
import ch.ivyteam.ivy.security.ISecurityContext;

public class IvyLanguage {

  @Deprecated(forRemoval = true, since = "9.4")
  private String appName;
  private String userLanguage;
  private List<String> supportedLanguages;
  private List<SelectItem> items = new ArrayList<>();
  private String userFormattingLanguage;
  private List<String> supportedFormattingLanguages;
  private SelectItem itemFormattingLanguage;
  private List<SelectItem> itemFormattingLanguages = new ArrayList<>();

  public List<SelectItem> getLanguages() {
    return getLanguages(items, supportedLanguages, LanguageConfigurator::content, false);
  }
  
  public List<SelectItem> getFormattingLanguages() {
    return getLanguages(itemFormattingLanguages, supportedFormattingLanguages, LanguageConfigurator::formatting, true);
  }
  
  private List<SelectItem> getLanguages(List<SelectItem> selectItems, List<String> languages, Function<LanguageConfigurator, Locale> localeLoader, boolean replaceParentheses){
    selectItems.clear();
    if (CollectionUtils.isNotEmpty(languages)) {
      for (String item : languages) {
        SelectItem it = new SelectItem(item.toLowerCase(), toDisplayName(item.toLowerCase(), localeLoader, replaceParentheses));
        selectItems.add(it);
      }
    }
    return selectItems;
  }
  
  private String toDisplayName(String languageTag, Function<LanguageConfigurator, Locale> loader, boolean replaceParentheses) {
    Locale contentLocale = Ivy.session().getContentLocale();
    if (StringUtils.isBlank(languageTag)) {
      String systemLanguage = loader.apply(LanguageManager.instance().configurator(ISecurityContext.current())).getDisplayName(contentLocale);
      if (replaceParentheses) {
        systemLanguage = systemLanguage.replaceAll("\\(", "[").replaceAll("\\)","]");
      }
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/MyProfile/defaultOption", Arrays.asList(systemLanguage) );
    }
    return Locale.forLanguageTag(languageTag).getDisplayName(contentLocale);
  }

  /**
   * Gets the appName
   * 
   * @return Returns the appName
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public String getAppName() {
    return appName;
  }

  /**
   * Sets the appName
   * 
   * @param appName The appName to set
   */
  @Deprecated(forRemoval = true, since = "9.4")
  public void setAppName(String appName) {
    this.appName = appName;
  }

  /**
   * Gets the userLanguage
   * 
   * @return Returns the userLanguage
   */
  public String getUserLanguage() {
    return userLanguage;
  }

  /**
   * Sets the userLanguage
   * 
   * @param userLanguage The userLanguage to set
   */
  public void setUserLanguage(String userLanguage) {
    this.userLanguage = userLanguage;
  }

  /**
   * Gets the supportedLanguages
   * 
   * @return Returns the supportedLanguages
   */
  public List<String> getSupportedLanguages() {
    return supportedLanguages;
  }

  /**
   * Sets the supportedLanguages
   * 
   * @param supportedLanguages The supportedLanguages to set
   */
  public void setSupportedLanguages(List<String> supportedLanguages) {
    this.supportedLanguages = supportedLanguages;
  }

  public List<String> getSupportedFormattingLanguages() {
    return supportedFormattingLanguages;
  }

  public void setSupportedFormattingLanguages(List<String> supportedFormattingLanguages) {
    this.supportedFormattingLanguages = supportedFormattingLanguages;
  }

  public void setUserFormattingLanguage(String userFormattingLanguage) {
    this.userFormattingLanguage = userFormattingLanguage;
  }

  public SelectItem getItemFormattingLanguage() {
    return itemFormattingLanguage;
  }

  public void setItemFormattingLanguage(SelectItem itemFormattingLanguage) {
    this.itemFormattingLanguage = itemFormattingLanguage;
  }
  
  public void initItemFormattingLanguage() {
    this.itemFormattingLanguage = new SelectItem(this.userFormattingLanguage, toDisplayName(this.userFormattingLanguage, LanguageConfigurator::formatting, true));
  }
}
