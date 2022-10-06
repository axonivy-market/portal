package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class MainMenuPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".layout-menu li[role='menuitem'] a.DASHBOARD";
  }

  public TaskWidgetPage openTaskList() {
    waitLeftMenuReady();
    var taskMenu = $(".layout-menu li[role='menuitem'] a.TASK").waitUntil(appear, DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(taskMenu);
    taskMenu.click();
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    waitLeftMenuReady();
    var caseMenu = $(".layout-menu li[role='menuitem'] a.CASE").waitUntil(appear, DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(caseMenu);
    caseMenu.click();
    return new CaseWidgetPage();
  }

  public ProcessWidgetPage openProcessList() {
    waitLeftMenuReady();
    var processMenu = $(".layout-menu li[role='menuitem'] a.PROCESS").waitUntil(appear, DEFAULT_TIMEOUT);
    waitUntilElementToBeClickable(processMenu);
    processMenu.click();
    return new ProcessWidgetPage();
  }

  public void openUserSettingMenu() {
    $("#top-menu").waitUntil(appear, DEFAULT_TIMEOUT);
    $("a[id='user-settings-menu']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  private void waitLeftMenuReady() {
    $("[id$=':main-navigator:main-menu']").waitUntil(Condition.exist, DEFAULT_TIMEOUT).waitUntil(appear, DEFAULT_TIMEOUT);
  }
}
