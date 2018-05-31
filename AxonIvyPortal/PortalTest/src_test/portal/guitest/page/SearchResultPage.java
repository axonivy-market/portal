package portal.guitest.page;

import org.openqa.selenium.By;



public class SearchResultPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('global-search-form-in-page:global-search-data')";
  }
  
  public void openTaskTab() {
    click(By.cssSelector("li[class*='task-tab-title']"));
    waitAjaxIndicatorDisappear();
  }
  
  public void openCaseTab() {
    click(By.cssSelector("li[class*='case-tab-title']"));
    waitAjaxIndicatorDisappear();
  }

  public void startProcess(String name) {
    ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
    processWidgetPage.startProcess(name);
  }
  
  public String getProcessResult(String name) {
    ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
    return processWidgetPage.getProcess(name).getText();
  }
  
  public boolean isProcessResultEmpty() {
    ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
    return processWidgetPage.isProcessEmpty();
  }
  
  public String getTaskResult(int index) {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage("search-results-tabview:task-results");
    return taskWidgetPage.getNameOfTaskAt(index);
  }
  
  public String getCaseResult(int index) {
    CasePage caseWidgetPage = new CasePage("search-results-tabview:case-results");
    return caseWidgetPage.getCaseNameAt(index);
  }
  
  public boolean isCaseResultEmpty() {
    CasePage caseWidgetPage = new CasePage("search-results-tabview:case-results");
    return caseWidgetPage.isEmpty();
  }
}
