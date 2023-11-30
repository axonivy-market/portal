package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class UserProfilePage extends TemplatePage {
  private static final String LANGUAGE_SELECTION_SELECTOR = "div[id$='language-selection'] div.ui-selectonemenu-trigger";
  private static String NOTI_CHANNELS_CHECKBOX_SELECTOR = "div[id$=':notification-Channels-Table'] div.ui-chkbox-box";

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

  public WebElement getUserSettingCard() {
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

}
