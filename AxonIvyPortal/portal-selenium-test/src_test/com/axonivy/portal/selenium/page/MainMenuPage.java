package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class MainMenuPage extends TemplatePage {

  private static String PROCESS_MENU_ITEM_CSS_SELECTOR = ".layout-menu li[role='menuitem'] a.PROCESS";

  @Override
  protected String getLoadedLocator() {
    return ".layout-menu li[role='menuitem'] a.DASHBOARD";
  }

  public TaskWidgetPage openTaskList() {
    return NavigationHelper.navigateToTaskList();
  }

  public TaskWidgetPage openDeveloperExamplesTaskList() {
    return NavigationHelper.navigateToDeveloperExamplesTaskList();
  }

  public CaseWidgetPage openCaseList() {
    waitLeftMenuReady();
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript($(".layout-menu li[role='menuitem'] a.CASE").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()));
    });
    return new CaseWidgetPage();
  }

  public ProcessWidgetPage openProcessList() {
    return NavigationHelper.navigateToProcessList();
  }

  public StatisticWidgetPage openStatisticPage() {
    return NavigationHelper.navigateToStatisticPage();
  }

  public void openUserSettingMenu() {
    $("#top-menu").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id='user-settings-menu']").shouldBe(clickable(), DEFAULT_TIMEOUT).click();
  }

  public void waitCaseContainerAppear() {
    $(".dashboard-cases-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitLeftMenuReady() {
    $$("[id$=':main-navigator:main-menu']").shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    $("[id$=':main-navigator:main-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void expandMainMenu() {
    waitLeftMenuReady();
    if ($("a[id$='user-menu-required-login:toggle-menu']").shouldBe(Condition.exist, DEFAULT_TIMEOUT).is(disappear)) {
      $("[id$=':main-navigator:main-menu']").hover();
      $$(By.id("user-menu-required-login:toggle-menu")).shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
      $("a[id$='user-menu-required-login:toggle-menu']").shouldBe(appear, DEFAULT_TIMEOUT)
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
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

  public String getMenuItemsAsString() {
    expandMainMenu();
    return String.join(",", $$(".layout-menu li[role='menuitem'] a span").asDynamicIterable().stream()
        .map(SelenideElement::getText).collect(Collectors.toList()));
  }

  public CaseWidgetPage selectCaseMenu() {
    $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover().scrollTo();
    WaitHelper.waitForNavigation(() -> $(By.cssSelector(".layout-menu li.sub-menu-item-case"))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new CaseWidgetPage();
  }

  public StatisticWidgetPage selectStatisticDashboard() {
    WaitHelper
        .waitForNavigation(() -> waitForElementClickableThenClick($(".layout-menu li[role='menuitem'] a.STATISTICS")));
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
    waitForElementDisplayed(By.cssSelector("li[class*='thirdparty-menu-item'] > a"), true);
    waitForElementClickableThenClick("li[class*='thirdparty-menu-item'] > a");

  }

  public void assertThirdPartyApp(String url) {
    WebDriver driver = getDriver();
    WaitHelper.assertTrueWithWait(() -> driver.getWindowHandles().size() > 1);
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    WaitHelper.assertTrueWithWait(() -> "Google".equals(driver.getTitle()));
    assertEquals(url, driver.getCurrentUrl());
  }


  public boolean isProcessesDisplayed() {
    return isMenuItemDisplayed("Processes");
  }

  private boolean isMenuItemDisplayed(String menuItemName) {
    return $$("li[role='menuitem']").asFixedIterable().stream()
        .filter(element -> element.getText().equals(menuItemName)).findFirst().map(WebElement::isDisplayed)
        .orElse(false);
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

  public WorkingTaskDialogPageOfApplicationMenu selectDashboardMenu() {
    waitForElementClickableThenClick(".layout-menu li[role='menuitem'] a.DASHBOARD");
    return new WorkingTaskDialogPageOfApplicationMenu();
  }
  
  public void clickOnSupportTicketOnTaskWidget() {
    $("td[class*='dashboard-tasks__name']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("td[class*='dashboard-tasks__name'] span").filter(text("SupportTicket")).first().click();
  }
  
  public String getIconClassMainMenuEntryAsString() {
    return $("div[id='user-menu-required-login']").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("li[id*='main-menu__js__DASHBOARD-main-dashboard']").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("a").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("i").shouldBe(appear, DEFAULT_TIMEOUT)
            .getAttribute("class").toString();      
  }
  
  public String getMainMenuName() {
    return $("div[id='user-menu-required-login']").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("li[id*='main-menu__js__DASHBOARD-main-dashboard']").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("a").shouldBe(appear, DEFAULT_TIMEOUT)
            .$("span").shouldBe(appear, DEFAULT_TIMEOUT)
            .getText();     
  }
}
