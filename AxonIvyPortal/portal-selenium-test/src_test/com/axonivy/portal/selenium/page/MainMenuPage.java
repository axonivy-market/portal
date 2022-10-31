package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;

public class MainMenuPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".layout-menu li[role='menuitem'] a.DASHBOARD";
  }

  public TaskWidgetPage openTaskList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.TASK").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.CASE").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new CaseWidgetPage();
  }

  public ProcessWidgetPage openProcessList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.PROCESS").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new ProcessWidgetPage();
  }

  public void openUserSettingMenu() {
    $("#top-menu").shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("a[id='user-settings-menu']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
  }

  private void waitLeftMenuReady() {
    $("[id$=':main-navigator:main-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
