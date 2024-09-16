package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.Keys;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ProcessWidgetNewDashBoardPage extends TemplatePage {

  private static final String YOUR_PROCESSES_WIDGET = "Your Processes";

  String processWidgetId;
  private String processWidgetName;

  public ProcessWidgetNewDashBoardPage() {
    this("div[id$='process-list']", YOUR_PROCESSES_WIDGET);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$='dashboard-cases-container']";
  }

  public ProcessWidgetNewDashBoardPage(String processWidgetName) {
    this("div[id$='dashboard-process']", processWidgetName);
  }

  public ProcessWidgetNewDashBoardPage(String processWidgetId, String processWidgetName) {
    this.processWidgetId = processWidgetId;
    this.processWidgetName = processWidgetName;
  }

  public ElementsCollection expand() {
    return $$("div.widget__header").filter(text(processWidgetName));
  }

  public void startProcessByName(String processName) {
    var startProcess = $(processWidgetId).shouldBe(appear, DEFAULT_TIMEOUT).$$("span.process-start-list-item")
        .filter(text(processName)).first().$("a");
    waitUntilElementToBeClickable(startProcess);
    startProcess.click();
  }

  public void deleteProcessWidget() {
    $$("div.table-widget-panel div.widget__header")
        .filter(text(processWidgetName)).first()
        .shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$='widget-header-actions']").$("[id*='delete-widget']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }
  
  public boolean isQuickSearchInputShow() {
    waitPageLoaded();
    return getQuickSearchForm().exists();
  }

  public void setQuickSearchKeyword(String keyword) {
    getQuickSearchForm().$("input").setValue(keyword);
  }

  public SelenideElement getQuickSearchForm() {
    return getProcessWidgetHeader().$("div[class*='widget-header-quick-search']").shouldBe(appear, DEFAULT_TIMEOUT).$("form");
  }
  
  private SelenideElement getProcessWidgetHeader() {
    return $$("div.table-widget-panel").filter(text(processWidgetName)).first();
  }

  public int getNumberOfProcessListInWidget() {
    return getProcessWidgetHeader().$("div[id*='widget-content']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("div[id*='process-list']").shouldBe(appear, DEFAULT_TIMEOUT).$$("span.process-start-list-item")
        .size();
  }

  public void clearQuickSearchInput() {
    getQuickSearchForm().$("input").clear();
    waitForPageLoad();
  }

  public void copyAndPasteOnQuickSearchInput() {
    SelenideElement searchInput = getQuickSearchForm().$("input");
    searchInput.click();
    searchInput.sendKeys(Keys.HOME);
    searchInput.sendKeys(Keys.LEFT_SHIFT, Keys.END);
    searchInput.sendKeys(Keys.CONTROL, "C");
    searchInput.sendKeys(Keys.DELETE);
    searchInput.sendKeys(Keys.CONTROL, "V");
    searchInput.sendKeys(Keys.ENTER);
    waitForPageLoad();
  }

  public void shiftAndArrowKeyOnQuickSearchInput() {
    SelenideElement searchInput = getQuickSearchForm().$("input");
    searchInput.click();
    searchInput.sendKeys(Keys.HOME);
    searchInput.sendKeys(Keys.LEFT_SHIFT, Keys.RIGHT, Keys.RIGHT, Keys.RIGHT, Keys.RIGHT);
    searchInput.sendKeys(Keys.ENTER);
    waitForPageLoad();
  }

  public boolean isExpandButtonAppear() {
    return getProcessWidgetHeader().$(".expand-link").isDisplayed();
  }

  public boolean isWidgetInfoIconAppear() {
    return getProcessWidgetHeader().$(".widget__info-sidebar-link").isDisplayed();
  }
}
