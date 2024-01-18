package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UserProfilePage extends TemplatePage {
  private static final String LANGUAGE_SELECTION_SELECTOR =
      "div[id$='language-selection'] div.ui-selectonemenu-trigger";
  public static final String TASK_SORT_DIRECTION_SELECTION_ITEMS =
      "my-profile-form:task-sort-direction-selection_items";
  private static String NOTI_CHANNELS_CHECKBOX_SELECTOR = "div[id$=':notification-Channels-Table'] div.ui-chkbox-box";
  private static final String TASK_SORT_FIELD_SELECTION = "my-profile-form:task-sort-field-selection";
  private static final String TASK_SORT_FIELD_SELECTION_LABEL = "my-profile-form:task-sort-field-selection_label";
  public static final String TASK_SORT_FIELD_SELECTION_ITEMS = "my-profile-form:task-sort-field-selection_items";
  private static final String TASK_SORT_DIRECTION_SELECTION = "my-profile-form:task-sort-direction-selection";
  private static final String TASK_SORT_DIRECTION_SELECTION_LABEL =
      "my-profile-form:task-sort-direction-selection_label";
  
  private static String MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR = "div[id$=':mail-notification-on-task-assign']";
  private static String FURTHER_EMAIL_FROM_APP_SELECTOR = "div[id$=':further-mails-from-application']";

  @Override
  protected String getLoadedLocator() {
    return "#my-profile-container";
  }

  public void selectLanguage(int newLanguage) {
    $(LANGUAGE_SELECTION_SELECTOR).shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("li[id$='language-selection_" + newLanguage + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public void save() {
    var save = $("button[id$='save-settings']").should(Condition.appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(() -> save.click());
  }

  public SelenideElement getUserSettingCard() {
    return $("[id='my-profile-container']");
  }
  
  public void checkBoxTosubscribeChannel() {
    checkIntoCheckbox(NOTI_CHANNELS_CHECKBOX_SELECTOR);
  }

  private void checkIntoCheckbox(String cssSelector) {
    SelenideElement inputSwitch = $(cssSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    if (inputSwitch.getAttribute("class").contains("ui-chkbox-box")) {
      $(inputSwitch).click();
    }
  }

  public void selectTaskSortField(String selectValue) {
    waitForElementDisplayed(By.id(TASK_SORT_FIELD_SELECTION), true);
    waitForElementClickableThenClick(findElementById(TASK_SORT_FIELD_SELECTION_LABEL));

    waitForElementDisplayed(By.id(TASK_SORT_FIELD_SELECTION_ITEMS), true);
    waitForElementClickableThenClick(
        By.xpath("//*[@id='" + TASK_SORT_FIELD_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
  }

  public void selectTaskSortDirection(String selectValue) {
    waitForElementDisplayed(By.id(TASK_SORT_DIRECTION_SELECTION), true);
    waitForElementClickableThenClick(findElementById(TASK_SORT_DIRECTION_SELECTION_LABEL));

    waitForElementDisplayed(By.id(TASK_SORT_DIRECTION_SELECTION_ITEMS), true);
    waitForElementClickableThenClick(
        By.xpath("//*[@id='" + TASK_SORT_DIRECTION_SELECTION_ITEMS + "']/li[contains(text(),'" + selectValue + "')]"));
  }

  public void switchOnEmailOnTaskAssignmentSetting() {
    switchOnSetting(MAIL_NOTI_ON_TASK_ASSIGNMENT_SELECTOR);
  }

  private void switchOnSetting(String cssSelector) {
    WebElement inputSwitch = findElementByCssSelector(cssSelector);
    if (!inputSwitch.getAttribute("class").contains("ui-inputswitch-checked")) {
      $(inputSwitch).shouldBe(getClickableCondition()).click();
    }
  }
  
  public void switchOnFurtherEmailFromAppSetting() {
    switchOnSetting(FURTHER_EMAIL_FROM_APP_SELECTOR);
    $("div[id$='my-profile-form:further-mails-from-application']").shouldBe(appear, TEN_SECOND);
  }

}
