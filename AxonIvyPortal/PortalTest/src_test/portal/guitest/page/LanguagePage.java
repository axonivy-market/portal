package portal.guitest.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LanguagePage extends TemplatePage {

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
    final String warnMessageId = "language-settings-form:language-settings:change-language-warning-message";
    if (isElementDisplayedById(warnMessageId)) {
      WebElement warnMessage = findElementById(warnMessageId);
      if (warnMessage != null) {
        return StringUtils.isNoneEmpty(warnMessage.getText());
      }
    }
    return false;
  }
}
