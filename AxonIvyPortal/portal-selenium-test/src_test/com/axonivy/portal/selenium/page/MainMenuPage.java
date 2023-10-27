package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class MainMenuPage extends TemplatePage {

  private static String PROCESS_MENU_ITEM_CSS_SELECTOR = ".layout-menu li[role='menuitem'] a.PROCESS";

  @Override
  protected String getLoadedLocator() {
    return ".layout-menu li[role='menuitem'] a.DASHBOARD";
  }

  public TaskWidgetPage openTaskList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.TASK").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT));
    });
    return new TaskWidgetPage();
  }

  public CaseWidgetPage openCaseList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.CASE").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT));
    });
    return new CaseWidgetPage();
  }

  public ProcessWidgetPage openProcessList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.PROCESS").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    waitPageLoaded();
    return new ProcessWidgetPage();
  }

  public StatisticWidgetPage openStatisticPage() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.STATISTICS").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });

    return new StatisticWidgetPage();
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
      $(".js-layout-main").hover();
      $(".portal-home-logo.portal-home-logo-small").should(Condition.visible, DEFAULT_TIMEOUT);
    }
  }

  public String getMenuItemsAsString() {
    expandMainMenu();
    return String.join(",", $$(".layout-menu li[role='menuitem'] a span").asDynamicIterable().stream().map(SelenideElement::getText).collect(Collectors.toList()));
  }

  public CaseWidgetPage selectCaseMenu() {
    WaitHelper.waitForNavigation(() -> $(By.cssSelector(".layout-menu li.sub-menu-item-case")).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new CaseWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    WaitHelper.waitForNavigation(() -> waitForElementClickableThenClick($(".layout-menu li[role='menuitem'] a.STATISTICS")));
    return new StatisticWidgetPage();
  }

  private void waitForProcessesPageAfterSelectProcessesCategory() {
    waitForElementDisplayed(By.id("process-widget:process-search:non-ajax-keyword-filter"), true);
  }

  public ProcessWidgetPage selectProcessesMenu() {
    waitForElementClickableThenClick($(PROCESS_MENU_ITEM_CSS_SELECTOR));
    waitForProcessesPageAfterSelectProcessesCategory();
    return new ProcessWidgetPage();
  }

  public String getProcessMenuItemText() {
    openMainMenu();
    return findElementByCssSelector(PROCESS_MENU_ITEM_CSS_SELECTOR).getText();
  }

  public void clickThirdPartyApp() {
    waitForElementDisplayed(By.id("left-menu"), true);
    $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover();
    Sleeper.sleep(500);
    waitForElementDisplayed($(By.id("user-menu-required-login:toggle-menu")), true);
    waitForElementDisplayed(By.cssSelector("li[class*='thirdparty-menu-item'] > a"), true);
    waitForElementClickableThenClick($("li[class*='thirdparty-menu-item'] > a"));
  }

  public boolean isProcessesDisplayed() {
    return isMenuItemDisplayed("Processes");
  }

  private boolean isMenuItemDisplayed(String menuItemName) {
    return $$("li[role='menuitem']").asFixedIterable().stream().filter(element -> element.getText().equals(menuItemName)).findFirst().map(WebElement::isDisplayed).orElse(false);
  }

  public boolean isTasksDisplayed() {
    return isMenuItemDisplayed("Tasks");
  }

  public boolean isCasesDisplayed() {
    return isMenuItemDisplayed("Cases");
  }

  public boolean isStatisticsDisplayed() {
    return isMenuItemDisplayed("Statistics");
  }
}
