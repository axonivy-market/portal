package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

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

  public void expandMainMenu() {
    waitLeftMenuReady();
    if ($("a[id$='user-menu-required-login:toggle-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).is(disappear)) {
      $("[id$=':main-navigator:main-menu']").hover();
      WaitHelper.waitNumberOfElementsToBe(WebDriverRunner.getWebDriver(), By.id("user-menu-required-login:toggle-menu"), 1);
      $("a[id$='user-menu-required-login:toggle-menu']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
  }

  public String getMenuItemsAsString() {
    expandMainMenu();
    return String.join(",", $$(".layout-menu li[role='menuitem'] a span")
        .asFixedIterable().stream()
        .map(SelenideElement::getText).collect(Collectors.toList()));
  }
  
  public StatisticWidgetPage openStatisticPage() {
    return NavigationHelper.navigateToStatisticPage();
  }

  public void closeMainMenu() {
    waitLeftMenuReady();
    if ($("a[id$='user-menu-required-login:toggle-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).is(appear)) {
      $("a[id$='user-menu-required-login:toggle-menu']").shouldBe(appear, DEFAULT_TIMEOUT)
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      $(".js-layout-main").hover();
      $(".portal-home-logo.portal-home-logo-small").should(Condition.visible, DEFAULT_TIMEOUT);
    }
  }

}
