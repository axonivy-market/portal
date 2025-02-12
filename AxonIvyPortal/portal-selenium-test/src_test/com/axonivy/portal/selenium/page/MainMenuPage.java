package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
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
    return "[id $= 'main-navigator:main-menu']";
  }

  @Override
  public CaseWidgetNewDashBoardPage openCaseList() {
    return NavigationHelper.navigateToCaseList();
  }

  public ProcessWidgetPage openProcessList() {
    return NavigationHelper.navigateToProcessList();
  }

  @Override
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

  @Override
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

  public CaseWidgetNewDashBoardPage selectCaseMenu() {
    $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover().scrollTo();
    WaitHelper.waitForNavigation(() -> $(By.cssSelector("li[id$='default-case-list-dashboard-main-dashboard']"))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new CaseWidgetNewDashBoardPage();
  }

  public NewDashboardPage selectDashboardByName(String name) {
    $("[id $= 'main-navigator:main-menu']").$(".js-dashboard-group")
        .shouldBe(appear, DEFAULT_TIMEOUT).click();

    $("[id $= 'main-navigator:main-menu']").$(".js-dashboard-group")
        .$$(".menu-item-dashboard > a > span").filter(Condition.text(name))
        .get(0)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return new NewDashboardPage();
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
    waitForElementDisplayed(By.cssSelector("li[class*='thirdparty-menu-item'] > a > span"), true);
    waitForElementClickableThenClick("li[class*='thirdparty-menu-item'] > a");
  }

  public void clickMainMenuItem(String name) {
    WebElement element = driver.findElement(By.xpath(
        String.format("//li[contains(@class, ' main-dashboard-menu-item-main_dashboard') and .//span[text()='%s']]", name)));
    element.click();
  }

  public void assertThirdPartyApp(String url) {
    WebDriver driver = getDriver();
    WaitHelper.assertTrueWithWait(() -> driver.getWindowHandles().size() > 1);
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
    WaitHelper.assertTrueWithWait(() -> "Google".equals(driver.getTitle()));
    assertEquals(url, driver.getCurrentUrl());
  }

  public void assertMainMenuItem(String name) {
    WebDriver driver = getDriver();
    String tilte = String.format("%s - Portal - Axon Ivy", name);
    WaitHelper.assertTrueWithWait(() -> tilte.equals(driver.getTitle()));
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

  public NewDashboardPage selectDashboardMenu() {
    $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover().scrollTo();
    WaitHelper.waitForNavigation(() -> $(By.cssSelector("li[id$='dashboard_0-parent-dashboard']"))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new NewDashboardPage();
  }

  public TaskWidgetNewDashBoardPage selectTaskMenuItem() {
    $(By.id("left-menu")).shouldBe(appear, DEFAULT_TIMEOUT).hover().scrollTo();
    WaitHelper.waitForNavigation(() -> $(By.cssSelector("li[id$='default-task-list-dashboard-main-dashboard']"))
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
    return new TaskWidgetNewDashBoardPage();
  }

  public String getIconClassMainMenuEntryAsString() {
	  return $("div[id='user-menu-required-login']").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("li[id*='main-menu__js__DASHBOARD-parent-dashboard']").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("a").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("i").shouldBe(appear, DEFAULT_TIMEOUT)
			  .getAttribute("class").toString();	  
  }
  
  public String getMainMenuName() {
	  return $("div[id='user-menu-required-login']").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("li[id*='main-menu__js__DASHBOARD-parent-dashboard']").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("a").shouldBe(appear, DEFAULT_TIMEOUT)
			  .$("span").shouldBe(appear, DEFAULT_TIMEOUT)
			  .getText();	  
  }
}
