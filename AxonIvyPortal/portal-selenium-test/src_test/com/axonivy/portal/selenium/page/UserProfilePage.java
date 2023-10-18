package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;

public class UserProfilePage extends TemplatePage {
  private static final String LANGUAGE_SELECTION_SELECTOR = "div[id$='language-selection'] div.ui-selectonemenu-trigger";

  @Override
  protected String getLoadedLocator() {
    return "#my-profile-container";
  }

  public void selectLanguage(int newLanguage) {
    $(LANGUAGE_SELECTION_SELECTOR).shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("li[id$='language-selection_" + newLanguage + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
  }

  public NewDashboardPage save() {
    var save = $("button[id$='save-settings']").should(Condition.appear, DEFAULT_TIMEOUT);
    WaitHelper.waitForNavigation(() -> save.click());
    return new NewDashboardPage();
  }

  public void changeNewDashboardPageToCase() {
    String newdashboardpageLabel = "my-profile-form:homepage_label";
    $("[id='" + newdashboardpageLabel + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    String caseItemCssSelector = "li.ui-selectonemenu-item[data-label='Cases']";
    $(caseItemCssSelector).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='" + newdashboardpageLabel + "']").shouldBe(appear, DEFAULT_TIMEOUT).shouldHave(Condition.text("Cases"), DEFAULT_TIMEOUT);
  }

  public void saveWithoutWaitingNavigation() {
    WaitHelper.waitForNavigation(() -> $("button[id$='save-settings']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }
}
