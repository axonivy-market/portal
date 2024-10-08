package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

public class SearchResultPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='search-results-tabview']";
  }

  public void openTaskTab() {
    waitForPageLoad();
    waitForElementClickableThenClick($(By.cssSelector("li[class*='task-tab-title']")));
  }

  public void openCaseTab() {
    waitForPageLoad();
    waitForElementClickableThenClick($(By.cssSelector("li[class*='case-tab-title']")));
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

  public String getCaseResult(int index) {
    CaseWidgetPage caseWidgetPage = getCaseWidget();
    return caseWidgetPage.getCaseNameAt(index);
  }

  public int countCase() {
    CaseWidgetPage caseWidgetPage = getCaseWidget();
    return caseWidgetPage.getNumberOfCases();
  }

  private CaseWidgetPage getCaseWidget() {
    return new CaseWidgetPage("search-results-tabview:case-results");
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

  public boolean isTaskCategoryColumnDisplayed() {
    return findElementByCssSelector("span[id$=':task-category-cell']").isDisplayed();
  }

  public boolean isCaseCategoryColumnDisplayed() {
    waitForPageLoad();
    return findElementByCssSelector("span[id$=':case-category-cell']").isDisplayed();
  }

  public void waitForFirstTabFinishedLoading() {
    waitForElementDisplayed(By.className("js-loading-process-list"), false);
    waitForElementDisplayed(By.className("js-process-start-list-container"), true);
  }
}
