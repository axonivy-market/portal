package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

public class AdminSettingsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='portal-management-container']";
  }

  public ExpressManagementPage openExpressManagementTab() {
    $("[id='admin-setting-component:adminTabView']").shouldBe(appear, DEFAULT_TIMEOUT).$$("li.ui-tabs-header")
      .asFixedIterable().stream().filter(WebElement::isDisplayed).filter(tab -> tab.$("a").getText().contentEquals("Express Management")).findFirst().get()
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id='admin-setting-component:adminTabView:express-management-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
    return new ExpressManagementPage();
  }

  public WebElement getAdminSettingContainer() {
    return $("[id='admin-settings-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
