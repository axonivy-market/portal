package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class GlobalSearchResultPage extends TemplatePage {

  private final String INFO_EXPRESS_WORKFlOW = "span[id$='info-workflow']";
  private final String EDIT_EXPRESS_WORKFlOW = "a[id$='edit-express-workflow']";
  private final String DELETE_EXPRESS_WORKFlOW = "a[id$='delete-express-workflow']";
  private final String EXPRESS_PROCESS_LOGO = "span[id$='express-process-logo']";

  @Override
  protected String getLoadedLocator() {
    return "#search-results-tabview";
  }

  public void openTaskTab() {
    $("li[class*='task-tab-title']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("li[class*='task-tab-title']").click();
    $("div[id='search-results-tabview:task-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openCaseTab() {
    $("li[class*='case-tab-title']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("li[class*='case-tab-title']").click();
    $("div[id='search-results-tabview:case-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public int countTasks() {
    $("div[id='search-results-tabview:task-results:task-list-scroller']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='search-results-tabview:task-results:task-list-scroller']").findAll("li.ui-datascroller-item")
        .size();
  }

  public String getNameOfTask(int index) {
    String id =
        "search-results-tabview:task-results:task-list-scroller:" + index + ":task-item:task-name-component:task-name";
    return $$(
        "div[id='search-results-tabview:task-results:task-list-scroller'] div ul li div[id$=':task-item:task-start'] div.task-start-link div.task-start-info span.name-cell")
            .filter(Condition.id(id)).get(0).getText();
  }

  public int countCases() {
    $("div[id='search-results-tabview:case-results:case-list-scroller']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='search-results-tabview:case-results:case-list-scroller']").findAll("li.ui-datascroller-item")
        .size();
  }

  public String getNameOfCase(int index) {
    String id = "search-results-tabview:case-results:case-list-scroller:" + index + ":case-item";
    return $("div[id='search-results-tabview:case-results:case-list-scroller'] div ul li div[id='" + id
        + "'] div span.case-info-row div span.case-header-name-cell").text();
  }

  public String getDescriptionOfCase(int index) {
    String id = "search-results-tabview:case-results:case-list-scroller:" + index + ":case-item";
    return $("div[id='search-results-tabview:case-results:case-list-scroller'] div ul li div[id='" + id
        + "'] div span.case-info-row div span.case-header-desc-cell").text();
  }

  public String getGlobalSearchByFieldTextForTaskTab() {
    return $("span[id = 'search-results-tabview:task-results:global-search-text']").shouldBe(appear, DEFAULT_TIMEOUT)
        .getText();
  }

  public String getGlobalSearchByFieldTextForCaseTab() {
    return $("span[id = 'search-results-tabview:case-results:global-search-text']").shouldBe(appear, DEFAULT_TIMEOUT)
        .getText();
  }

  public void waitUtilProcessWidgetDisplayed() {
    $("[id='search-results-tabview:process-results:process-list']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public int countCase() {
    CaseWidgetPage caseWidgetPage = getCaseWidget();
    return caseWidgetPage.getCasesList().size();
  }

  private CaseWidgetPage getCaseWidget() {
    return new CaseWidgetPage("search-results-tabview:case-results");
  }

  public String getCaseResult(int index) {
    CaseWidgetPage caseWidgetPage = getCaseWidget();
    return caseWidgetPage.getCaseNameAt(index);
  }

  public void startProcess(String name) {
    ProcessWidgetPage processWidgetPage = getProcessWidget();
    if (processWidgetPage.isImageModeActivated()) {
      SelenideElement processListElement = $("[id='search-results-tabview:process-results']");
      SelenideElement processItemElement = processWidgetPage.getStartImageProcess(name, processListElement);
      processItemElement.click();
      return;
    }
    processWidgetPage.startProcess(name);
  }

  public String getProcessResult(String name) {
    ProcessWidgetPage processWidgetPage = getProcessWidget();
    if (processWidgetPage.isImageModeActivated()) {
      SelenideElement processItem = processWidgetPage.getProcessItem(name);
      SelenideElement processNameElement = processItem.$(".js-process-start-list-item-name");
      return processNameElement.getText();
    }
    return processWidgetPage.getProcess(name).getText();
  }

  public String getTaskResult(int index) {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage("search-results-tabview:task-results");
    return taskWidgetPage.getNameOfTaskAt(index);
  }

  public boolean isCaseResultEmpty() {
    CaseWidgetPage caseWidgetPage = getCaseWidget();
    return caseWidgetPage.isEmpty();
  }

  public boolean isProcessGroupDisplay(String group) {
    ProcessWidgetPage processWidgetPage = getProcessWidget();
    return processWidgetPage.isProcessGroupDisplay(group);
  }

  private ProcessWidgetPage getProcessWidget() {
    return new ProcessWidgetPage("search-results-tabview:process-results");
  }

  public boolean isInfoWorkflowIcon() {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      SelenideElement icon = $(".express-workflow").$(".process-image-view-icon");
      return icon.getAttribute("class").contains("si si si-startup-launch");
    }

    WebElement element = findElementByCssSelector(INFO_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-information-circle");
  }

  public boolean isEditExpressWorkflow(String processName) {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      SelenideElement actionMenu = getImageProcessActionMenuPanel(processName);
      SelenideElement icon = actionMenu.$("a[id$=':image-process-action-component:edit-process']");
      return icon != null;
    }
    SelenideElement element = findElementByCssSelector(EDIT_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-graphic-tablet-drawing-pen");
  }

  private SelenideElement getImageProcessActionMenuPanel(String processName) {
    var selectedProcess = $$("span.process-image-view-name").asFixedIterable().stream()
        .filter(process -> processName.equalsIgnoreCase(process.getText())).findFirst().orElse(null);
    var processActionMenuId = selectedProcess.getAttribute(ID_PROPERTY).replace("process-item-name", "");
    processActionMenuId = processActionMenuId.concat("process-item:image-process-action-component:process-action-menu");
    return findElementByCssSelector(String.format("div[id$='%s']", processActionMenuId));
  }

  public boolean isDeleteExpressWorkflown(String processName) {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      SelenideElement actionMenu = getImageProcessActionMenuPanel(processName);
      SelenideElement icon = actionMenu.$("a[id$=':image-process-action-component:delete-process']");
      return icon != null;
    }
    SelenideElement element = findElementByCssSelector(DELETE_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-bin-1");
  }

  public boolean isExpressProcessLogo() {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      return isInfoWorkflowIcon();
    }
    SelenideElement element = findElementByCssSelector(EXPRESS_PROCESS_LOGO);
    return element.getAttribute("class").contains("si-startup-launch");
  }

  public boolean isTaskCategoryColumnDisplayed() {
    return findElementByCssSelector("span[id$=':task-category-cell']").isDisplayed();
  }

  public boolean isCaseCategoryColumnDisplayed() {
    return findElementByCssSelector("span[id$=':case-category-cell']").isDisplayed();
  }

  public void waitForFirstTabFinishedLoading() {
    waitForElementDisplayed(By.className("js-loading-process-list"), false);
    waitForElementDisplayed(By.className("js-process-start-list-container"), true);
  }

  public void clickOnActionButton(String processName) {
    $(".express-workflow").$("button[id$=':process-action-button']").shouldBe(getClickableCondition()).click();
  }
  
  public void caseTabShouldBeDisappear() {
    $("li[class*='case-tab-title']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}
