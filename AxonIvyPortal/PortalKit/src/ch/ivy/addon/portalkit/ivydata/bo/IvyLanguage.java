package ch.ivy.addon.portalkit.ivydata.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.model.SelectItem;

import ch.ivyteam.ivy.environment.Ivy;

public class IvyLanguage {

  private String appName;
  private String appDisplayName;
  private String userLanguage;
  private List<String> supportedLanguages;
  private List<SelectItem> items = new ArrayList<>();

  public List<SelectItem> getLanguages() {
    items.clear();
    if (supportedLanguages != null) {
      for (String item : supportedLanguages) {
        SelectItem it = new SelectItem(item.toLowerCase(), getDisplayLanguage(item.toLowerCase()));
        items.add(it);
      }
    }
    return items;
  }

  private String getDisplayLanguage(String item) {
    Locale displayedLocale = Locale.forLanguageTag(item);
    return displayedLocale.getDisplayName(Ivy.session().getContentLocale());
  }

  /**
   * Gets the appName
   * 
   * @return Returns the appName
   */
  public String getAppName() {
    return appName;
  }

  /**
   * Sets the appName
   * 
   * @param appName The appName to set
   */
  public void setAppName(String appName) {
    this.appName = appName;
  }

  /**
   * Gets the appDisplayName
   * 
   * @return Returns the appDisplayName
   */
  public String getAppDisplayName() {
    return appDisplayName;
  }

  /**
   * Sets the appDisplayName
   * 
   * @param appDisplayName The appDisplayName to set
   */
  public void setAppDisplayName(String appDisplayName) {
    this.appDisplayName = appDisplayName;
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
}
