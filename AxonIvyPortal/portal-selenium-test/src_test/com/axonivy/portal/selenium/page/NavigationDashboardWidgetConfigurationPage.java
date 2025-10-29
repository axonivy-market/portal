package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


public class NavigationDashboardWidgetConfigurationPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "div#new-widget-configuration-dialog";
  }
  
  public NavigationDashboardWidgetConfigurationPage() {}
  
  public NavigationDashboardWidgetConfigurationPage(String name, String description, String targetDashboard) {
    setWidgetTitle(name);
    setWidgetDescription(description);
    selectTargetDashboard(targetDashboard);
  }
  
  public void setWidgetTitle(String widgetTitle) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("span[id$=':widget-title-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("input[id$=':widget-title']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .setValue(widgetTitle);
  }
  
  public void setWidgetDescription(String description) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("span[id$=':widget-title-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("input[id$=':widget-description']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .setValue(description);
  }
  
  public void selectTargetDashboard(String targetDashboard) {
    $("form#widget-configuration-form").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("div[id$='dashboard-link-group']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("div[id$=':dashboard-link-selection-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    
    $("div[id$=':dashboard-link-selection-menu_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("ul").$$("li").filter(Condition.text(targetDashboard)).first().click();
  }
  
  public void save() {
    $("button#widget-configuration-save-button").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    getDialog().shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getDialog() {
    return $("div#new-widget-configuration-dialog");
  }
  
  public void waitForSelectionIcon() {
    waitForElementDisplayed($("a[class$='highlight-selected-icon']"), isDisplayed());
  }
  
  public void waitForWidgetDialogAppear() {
    waitForElementDisplayed($("div[class*='navigation-dashboard-widget-panel']"), true);
  }

  public void uploadImage(String fileName) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':navigation-widget-image-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(fileName));
    configDialog.find(".ui-fileupload-filename").shouldBe(Condition.disappear, DEFAULT_TIMEOUT)
        .shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void uploadImageDarkMode(String fileName) {
    var configDialog = $("#new-widget-configuration-dialog");
    // Switch to Dark Mode tab
    configDialog.findAll(".ui-tabs-nav li").filter(Condition.text("Dark Mode")).first().click();
    // Upload image for dark mode
    configDialog.find("[id $= ':navigation-widget-image-dark-mode-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(fileName));
    configDialog.find(".ui-fileupload-filename").shouldBe(Condition.disappear, DEFAULT_TIMEOUT)
        .shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
}
