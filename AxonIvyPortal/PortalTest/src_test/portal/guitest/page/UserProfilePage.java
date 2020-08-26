package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;

public class UserProfilePage extends TemplatePage {
  
  private static String MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR = "div[id$=':mail-notification-on-task-assign']";
  private static String FURTHER_EMAIL_FROM_APP_SELECTOR = "div[id$=':further-mails-from-application']";
  private static String RECEIVE_DAILY_SUMMARY_SELECTOR = "div[id$=':daily-summary']";
  private static String DAILY_SUMMARY_CHECKBOX_SELECTOR = "label[for^='my-profile-form:daily-summary:']";
  private static String SELECTED_DAY_XPATH="//*[@id=\'my-profile-form:daily-summary-checkbox_panel\']/div[2]/ul/li[%d]/div/div[2]";
  private static String CHECKBOX_PANEL_CLOSE_BUTTON_XPATH = "//*[@id=\"my-profile-form:daily-summary-checkbox_panel\"]/div[1]/a";
  private static String DAILY_SUMMARY_CHECKBOX_LABEL_SELECTOR = "label[id$=':daily-summary-checkbox_label']";
  
  @Override
  protected String getLoadedLocator() {
    return "id('my-profile-form:save-settings')";
  }
  
  public void selectLanguage(int newLanguage) {
    waitForElementDisplayed(By.cssSelector("div[id$='selection'] div.ui-selectonemenu-trigger"), true);
    clickByCssSelector("div[id$='selection'] div.ui-selectonemenu-trigger");
    clickByCssSelector("li[id$='selection_" + newLanguage + "']");
  }

  public UserProfilePage save() {
    WebElement save = findElementByCssSelector("button[id$='save-settings']");
    click(save);
    waitAjaxIndicatorDisappear();
    waitForPageLoaded();
    return new UserProfilePage();
  }
  
  public String getLanguageSettingTitle() {
    return findElementByCssSelector("h2[id$='language-setting-title']").getText();
  }
  
  public void switchOnEmailOnTaskAssignmentSetting() {
    switchOnSetting(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }
  
  public void switchOnFurtherEmailFromAppSetting() {
    switchOnSetting(FURTHER_EMAIL_FROM_APP_SELECTOR);
  }
  
  public void switchOnReceiveSummarySetting() {
    switchOnSetting(RECEIVE_DAILY_SUMMARY_SELECTOR);
    waitAjaxIndicatorDisappear();
  }
  
  public void switchOffEmailOnTaskAssignmentSetting() {
    switchOffSetting(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }
  
  public void switchOffFurtherEmailFromAppSetting() {
    switchOffSetting(FURTHER_EMAIL_FROM_APP_SELECTOR);
  }
  public void switchOffReceiveSummarySetting() {
    switchOffSetting(RECEIVE_DAILY_SUMMARY_SELECTOR);
    waitAjaxIndicatorDisappear();
  }
  
  private void switchOnSetting(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    if (!inputSwitch.getAttribute("class").contains("ui-inputswitch-checked")) {
      click(inputSwitch);
    }
  }
  
  private void switchOffSetting(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    if (inputSwitch.getAttribute("class").contains("ui-inputswitch-checked")) {
      click(inputSwitch);
    }
  }

  public void selectDaysForDailySummary(List<Integer> indices) {
    for(int index : indices) {
      String selectedDayXPath = String.format(SELECTED_DAY_XPATH, index);
      WebElement selectedDayCheckbox = findChildElementByXpathExpression(checkbox, selectedDayXPath);
      if (!selectedDayCheckbox.getAttribute("class").contains("ui-state-active")) {
        click(selectedDayCheckbox);
      }
    }
    click(findElementByXpath(CHECKBOX_PANEL_CLOSE_BUTTON_XPATH));
  }

  public void waitUntilSelectedDayUpdated() {
    Awaitility.await().atMost(2, TimeUnit.SECONDS).until(() -> findElementByCssSelector(DAILY_SUMMARY_CHECKBOX_LABEL_SELECTOR).getText().contains("selected day"));
  }

  public String getSelectedDaySummary() {
    return findElementByCssSelector(DAILY_SUMMARY_CHECKBOX_LABEL_SELECTOR).getText();
  }
  
  public boolean isSettingSwitchedOn(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    return inputSwitch != null ? inputSwitch.getAttribute("class").contains("ui-inputswitch-checked") : false;
  }
  
  public boolean isEmailOnTaskAssignmentSettingSwitchedOn() {
    return isSettingSwitchedOn(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }
  
  public boolean isFurtherEmailFromAppSettingSwitchedOn() {
    return isSettingSwitchedOn(FURTHER_EMAIL_FROM_APP_SELECTOR);
  }

  public boolean isReceiveSummarySettingSwitchedOn() {
    return isSettingSwitchedOn(RECEIVE_DAILY_SUMMARY_SELECTOR);
  }
  
  public WebElement getUserSettingCard() {
    return findElementById("my-profile-container");
  }
}
