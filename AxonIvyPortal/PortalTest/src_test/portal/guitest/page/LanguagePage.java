package portal.guitest.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LanguagePage extends TemplatePage {
  
  private final static String WARN_MESSAGE_ID = "language-settings-form:language-settings:change-language-warning-message";

  @Override
  protected String getLoadedLocator() {
    return "id('language-setting-dialog_title')";
  }

  public void selectLanguage(int newLanguage) {
    waitForElementDisplayed(By.cssSelector("div[id$='selection'] div.ui-selectonemenu-trigger"), true);
    clickByCssSelector("div[id$='selection'] div.ui-selectonemenu-trigger");
    clickByCssSelector("li[id$='selection_" + newLanguage + "']");
  }

  public void save() {
    WebElement save = findElementById("language-settings-form:language-settings:save-settings");
    click(save);
    waitForElementExisted("#language-settings-form\\:language-settings\\:save-settings", false, DEFAULT_TIMEOUT);
  }
  
  public void cancel() {
    WebElement cancel = findElementById("language-settings-form:language-settings:cancel-settings");
    click(cancel);
  }

  public boolean isWarningMessageShownOn() {
    if (isElementDisplayedById(WARN_MESSAGE_ID)) {
      WebElement warnMessage = findElementById(WARN_MESSAGE_ID);
      if (warnMessage != null) {
        return StringUtils.isNoneEmpty(warnMessage.getText());
      }
    }
    return false;
  }
}
