package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class MainMenuPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('left-menu')";
  }

  public void openTaskList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.TASK").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void openCaseList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.CASE").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void openProcessList() {
    $(".layout-menu li[role='menuitem'] a.ripplelink.PROCESS").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  public void openUserSettingMenu() {
    $("a[id='user-settings-menu']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
}
