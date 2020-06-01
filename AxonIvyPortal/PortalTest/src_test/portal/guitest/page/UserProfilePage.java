package portal.guitest.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserProfilePage extends TemplatePage {
  private final static String WARN_MESSAGE_ID = "task-leave-warning-component-user-profile:task-losing-confirmation-dialog_title";
  
  @Override
  protected String getLoadedLocator() {
    return "id('my-profile-form:save-settings')";
  }
  
  public void selectLanguage(int newLanguage) {
    waitForElementDisplayed(By.cssSelector("div[id$='selection'] div.ui-selectonemenu-trigger"), true);
    clickByCssSelector("div[id$='selection'] div.ui-selectonemenu-trigger");
    clickByCssSelector("li[id$='selection_" + newLanguage + "']");
  }

  public void save() {
    WebElement save = findElementByCssSelector("button[id$='save-settings']");
    click(save);
    waitAjaxIndicatorDisappear();
    waitForPageLoaded();
  }
  
  public String getLanguageSettingTitle() {
    return findElementByCssSelector("h2[id$='language-setting-title']").getText();
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
  
  public UserProfilePage leaveTask() {
    click(By.id("task-leave-warning-component:leave-button"));
    return new UserProfilePage();
  }

}
