package ch.ivy.addon.portalkit.ivydata.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.model.SelectItem;

import ch.ivyteam.ivy.environment.Ivy;

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
    items.clear();
    if (supportedLanguages != null) {
      for (String item : supportedLanguages) {
        SelectItem it = new SelectItem(item.toLowerCase(), toDisplayNameLanguage(item.toLowerCase()));
        items.add(it);
      }
    }
    return items;
  }
  
  public List<SelectItem> getFormattingLanguages() {
    itemFormattingLanguages.clear();
    if (supportedFormattingLanguages != null) {
      for (String item : supportedFormattingLanguages) {
        String displayName = toDisplayNameFormattingLanguage(item);
        SelectItem it = new SelectItem(item.toLowerCase(), displayName);
        itemFormattingLanguages.add(it);
      }
    }
    return itemFormattingLanguages;
  }
  
  private String toDisplayNameFormattingLanguage(String languageTag) {
    Locale locale = Locale.forLanguageTag(languageTag);
    if (Locale.ROOT.equals(locale) || locale == null) {
      return ""; 
    }
    return String.format("%s (%s)", locale.getDisplayName(Ivy.session().getContentLocale()), locale.toString());
  }

  private String toDisplayNameLanguage(String languageTag) {
    Locale displayedLocale = Locale.forLanguageTag(languageTag);
    return displayedLocale.getDisplayName(Ivy.session().getContentLocale());
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
    this.itemFormattingLanguage = new SelectItem(this.userFormattingLanguage, toDisplayNameFormattingLanguage(this.userFormattingLanguage));
  }
}
