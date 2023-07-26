package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

public class UserProfilePage extends TemplatePage {
  
  private static final String CASE_SORT_DIRECTION_SELECTION_ITEMS = "my-profile-form:case-sort-direction-selection_items";
  private static final String CASE_SORT_DIRECTION_SELECTION_LABEL = "my-profile-form:case-sort-direction-selection_label";
  private static final String CASE_SORT_DIRECTION_SELECTION = "my-profile-form:case-sort-direction-selection";
  private static final String CASE_SORT_FIELD_SELECTION_ITEMS = "my-profile-form:case-sort-field-selection_items";
  private static final String CASE_SORT_FIELD_SELECTION_LABEL = "my-profile-form:case-sort-field-selection_label";
  private static final String CASE_SORT_FIELD_SELECTION = "my-profile-form:case-sort-field-selection";
  public static final String TASK_SORT_DIRECTION_SELECTION_ITEMS = "my-profile-form:task-sort-direction-selection_items";
  private static final String TASK_SORT_DIRECTION_SELECTION_LABEL = "my-profile-form:task-sort-direction-selection_label";
  public static final String TASK_SORT_FIELD_SELECTION_ITEMS = "my-profile-form:task-sort-field-selection_items";
  private static final String TASK_SORT_FIELD_SELECTION_LABEL = "my-profile-form:task-sort-field-selection_label";
  private static final String TASK_SORT_FIELD_SELECTION = "my-profile-form:task-sort-field-selection";
  private static final String TASK_SORT_DIRECTION_SELECTION = "my-profile-form:task-sort-direction-selection";
  private static final String LANGUAGE_SELECTION_SELECTOR = "div[id$='language-selection'] div.ui-selectonemenu-trigger"; 

  private static String MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR = "div[id$=':mail-notification-on-task-assign']";
  private static String FURTHER_EMAIL_FROM_APP_SELECTOR = "div[id$=':further-mails-from-application']";
  private static String SELECTED_DAY_XPATH="//*[@id='my-profile-form:daily-summary']/div/div/div/div[2]";
  private static String SHOW_TUTORIAL_XPATH = "//*[@id='my-profile-form:general-show-tutorial']/div[2]";

  @Override
  protected String getLoadedLocator() {
    return "id('my-profile-form:save-settings')";
  }
  
  public void selectLanguage(int newLanguage) {
    waitForElementDisplayed(By.cssSelector(LANGUAGE_SELECTION_SELECTOR), true);
    clickByCssSelector(LANGUAGE_SELECTION_SELECTOR);
    clickByCssSelector("li[id$='language-selection_" + newLanguage + "']");
  }

  public HomePage save() {
    saveWithoutWaitingNavigation();
    return new HomePage();
  }
  
  @SuppressWarnings("deprecation")
  public void saveWithoutWaitingNavigation() {
    WebElement save = findElementByCssSelector("button[id$='save-settings']");
    WaitHelper.waitForNavigation(new UserProfilePage(), () -> click(save));
  }

  public String getLanguageSettingTitle() {
    return findElementByCssSelector("h2[id$='language-setting-title']").getText();
  }
  
  public void switchOnEmailOnTaskAssignmentSetting() {
    switchOnSetting(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }
  
  public void switchOnFurtherEmailFromAppSetting() {
    switchOnSetting(FURTHER_EMAIL_FROM_APP_SELECTOR);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "my-profile-form\\\\:further-mails-from-application", ID_PROPERTY);
  }
  
  public void switchOffEmailOnTaskAssignmentSetting() {
    switchOffSetting(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }
  
  public void switchOffFurtherEmailFromAppSetting() {
    switchOffSetting(FURTHER_EMAIL_FROM_APP_SELECTOR);
  }

  @SuppressWarnings("deprecation")
  private void switchOnSetting(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    if (!inputSwitch.getAttribute("class").contains("ui-inputswitch-checked")) {
      click(inputSwitch);
    }
  }
  
  @SuppressWarnings("deprecation")
  private void switchOffSetting(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    if (inputSwitch.getAttribute("class").contains("ui-inputswitch-checked")) {
      click(inputSwitch);
    }
  }

  @SuppressWarnings("deprecation")
  public void selectDaysForDailySummary(List<Integer> indices) {
    List<WebElement> selectDays = findListElementsByXpath(SELECTED_DAY_XPATH);
    for(int index : indices) {
      WebElement selectedDayCheckbox = selectDays.get(index);
      if (!selectedDayCheckbox.getAttribute("class").contains("ui-state-active")) {
        click(selectedDayCheckbox);
      }
    }
  }

  public int getSelectedDaySummary() {
    return findListElementsByXpath(SELECTED_DAY_XPATH)
        .stream()
        .filter(checkbox -> checkbox.getAttribute("class").contains("ui-state-active"))
        .collect(Collectors.toList())
        .size();
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
  
  public WebElement getUserSettingCard() {
    return findElementById("my-profile-container");
  }

  public boolean isDisableShowTutorialCheckbox() {
    WebElement checkbox = findElementByCssSelector("[id$=':general-show-tutorial']");
    return checkbox.getAttribute("class").contains("ui-state-disabled");
  }

  @SuppressWarnings("deprecation")
  public void checkShowTutorial() {
    WebElement checkbox = findElementByXpath(SHOW_TUTORIAL_XPATH);
    if (!checkbox.getAttribute("class").contains("ui-state-active")) {
      click(checkbox.findElement(By.cssSelector("span[class='ui-chkbox-label']")));
      waitForElementDisplayed(By.xpath(SHOW_TUTORIAL_XPATH + "/span[@class='ui-chkbox-icon ui-icon ui-c ui-icon-check']"), true);
    }
  }

  @SuppressWarnings("deprecation")
  public void selectTaskSortField(String selectValue) {
    waitForElementDisplayed(By.id(TASK_SORT_FIELD_SELECTION), true);
    click(findElementById(TASK_SORT_FIELD_SELECTION_LABEL));

    waitForElementDisplayed(By.id(TASK_SORT_FIELD_SELECTION_ITEMS), true);
    click(findElementByXpath("//*[@id='" + TASK_SORT_FIELD_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
    ensureNoBackgroundRequest();
  }

  @SuppressWarnings("deprecation")
  public void selectTaskSortDirection(String selectValue) {
    waitForElementDisplayed(By.id(TASK_SORT_DIRECTION_SELECTION), true);
    click(findElementById(TASK_SORT_DIRECTION_SELECTION_LABEL));

    waitForElementDisplayed(By.id(TASK_SORT_DIRECTION_SELECTION_ITEMS), true);
    click(findElementByXpath("//*[@id='" + TASK_SORT_DIRECTION_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
    ensureNoBackgroundRequest();
  }

  @SuppressWarnings("deprecation")
  public void selectCaseSortField(String selectValue) {
    waitForElementDisplayed(By.id(CASE_SORT_FIELD_SELECTION), true);
    click(findElementById(CASE_SORT_FIELD_SELECTION_LABEL));

    waitForElementDisplayed(By.id(CASE_SORT_FIELD_SELECTION_ITEMS), true);
    click(findElementByXpath("//*[@id='" + CASE_SORT_FIELD_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
    ensureNoBackgroundRequest();
  }

  @SuppressWarnings("deprecation")
  public void selectCaseSortDirection(String selectValue) {
    waitForElementDisplayed(By.id(CASE_SORT_DIRECTION_SELECTION), true);
    click(findElementById(CASE_SORT_DIRECTION_SELECTION_LABEL));

    waitForElementDisplayed(By.id(CASE_SORT_DIRECTION_SELECTION_ITEMS), true);
    click(findElementByXpath("//*[@id='" + CASE_SORT_DIRECTION_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
    ensureNoBackgroundRequest();
  }

  public void changeHomePageToCase() {
    String homepageLabel = "my-profile-form:homepage_label";
    click(By.id(homepageLabel));
    String caseItemCssSelector = "li.ui-selectonemenu-item[data-label='Cases']";
    waitForElementDisplayed(By.cssSelector(caseItemCssSelector), true);
    clickByCssSelector(caseItemCssSelector);
    waitUntilTextToBePresentInElement(findElementById(homepageLabel), "Cases", getTimeOutForLocator());
  }
  
  @SuppressWarnings("deprecation")
  public HomePage clickOnCancelLink() {
    click(findElementByCssSelector("a[id^='my-profile-form:']"));
    waitForPageLoaded();
    return new HomePage();
  }

  public void changeDateFormatToPattern(String pattern) {
    click(By.cssSelector("label[id$=':date-format-selection_label']"));
    waitForElementDisplayed(By.cssSelector("div[id$=':date-format-selection_panel']"), true);
    WebElement dateFormatContainer = findElementByCssSelector("ul[id$=':date-format-selection_items']");
    List<WebElement> dateFormats = dateFormatContainer.findElements(By.cssSelector("li"));
    for (WebElement dateFormat: dateFormats) {
      if (dateFormat.getText().indexOf(pattern) > -1) {
        dateFormat.click();
        break;
      }
    }
  }
}
