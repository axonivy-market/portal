package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class MainMenuPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".layout-menu li[role='menuitem'] a.ripplelink.DASHBOARD";
  }

  public TaskWidgetPage openTaskList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.TASK").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.CASE").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new CaseWidgetPage();
  }

  public ProcessWidgetPage openProcessList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.PROCESS").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new ProcessWidgetPage();
  }
  
  public void openUserSettingMenu() {
    $("a[id='user-settings-menu']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
}
