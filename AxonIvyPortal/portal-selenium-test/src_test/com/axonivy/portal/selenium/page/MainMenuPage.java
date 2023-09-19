package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.axonivy.portal.selenium.page.legacy.LegacyDashboardPage;
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

  public StatisticWidgetPage openStatisticPage() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.STATISTICS").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new StatisticWidgetPage();
  }

  public LegacyDashboardPage openLegacyDashboard() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($("a[id='user-menu-required-login:logo-small']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new LegacyDashboardPage();
  }
  

  public void openUserSettingMenu() {
    $("#top-menu").shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("a[id='user-settings-menu']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
  }

  public void waitLeftMenuReady() {
    WaitHelper.waitNumberOfElementsToBe(WebDriverRunner.getWebDriver(), By.cssSelector("[id$=':main-navigator:main-menu']"), 1);
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
  
  public void closeMainMenu() {
    waitLeftMenuReady();
    if ($("a[id$='user-menu-required-login:toggle-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).is(appear)) {
      $("a[id$='user-menu-required-login:toggle-menu']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      $(".portal-home-logo-small").shouldBe(Condition.hidden, DEFAULT_TIMEOUT);
    }
  }

  public String getMenuItemsAsString() {
    expandMainMenu();
    return String.join(",", $$(".layout-menu li[role='menuitem'] a span")
        .asFixedIterable().stream()
        .map(SelenideElement::getText).collect(Collectors.toList()));
  }
}
