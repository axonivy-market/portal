package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TaskTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('side-steps-menu')";
  }

  public void openStatusTab() {
    String statusTabXpath = "//a[@href='#status-tab']";
    waitForElementDisplayed(By.xpath(statusTabXpath), true);
    click(findElementByXpath(statusTabXpath));
  }

  public boolean containsCaseDetails() {
    WebElement caseDetails = findElementByCssSelector("div[id$='case-item']");
    return caseDetails.isDisplayed();
  }

  public void addNewNote(String content) {
    String addNoteButtonId = "case-item:history:add-note-command";
    String addNoteDialogId = "case-item:history:add-note-dialog";
    findElementById(addNoteButtonId).click();
    waitForElementDisplayed(By.cssSelector("div[id^='" + addNoteDialogId + "']"), true);
    WebElement addNoteDialog = findElementByCssSelector("div[id^='" + addNoteDialogId + "']");
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();
  }

  public void openDocumentUploadingDialog() {
    findElementById("case-item:document:add-document-command").click();
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    waitForElementDisplayed(By.cssSelector("[id^='" + documentUploadingDialogId +"']"), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    return findElementByCssSelector("[id^='" + documentUploadingDialogId +"']").isDisplayed();
  }

  public void openFinishedTaskInHistoryArea() {
    WebElement element = findElementById("case-item:history:show-more-note-link");
    String url = element.getAttribute("href");
    ((JavascriptExecutor) driver).executeScript("window.open('"+ url +"','_blank');");
  }

  public TaskWidgetPage openFirstRelatedTaskInHistoryArea() {
    String firstFinishedTaskCssSelector = "div[id='case-item:related-tasks'] a";
    return openATaskInCaseDetails(firstFinishedTaskCssSelector);
  }

  public TaskWidgetPage openATaskInCaseDetails(String taskCssSelector) {
    WebElement task = findElementByCssSelector(taskCssSelector);
    task.click();
    return new TaskWidgetPage();
  }

  public int countNoteItems() {
    return findListElementsByCssSelector("a[id$='note-content']").size();
  }

  public int countHistoryItems() {
    return findListElementsByCssSelector("a[id$='show-task-note-link']")
        .size();
  }

  public int countRelatedTasks() {
    return findListElementsByCssSelector("div[id='case-item:related-tasks'] a").size();
  }

  public void openSideStepMenu() {
    String sideStepId = "side-steps-menu";
    waitForElementDisplayed(By.id(sideStepId), true);
    click(findElementById(sideStepId));
  }

  public int countSideSteps() {
    String sideStepPanelId = "side-steps-panel";
    waitForElementDisplayed(By.id(sideStepPanelId), true);
    return findListElementsByCssSelector("a[id$='side-step-item']").size();
  }
  
  public void clickCancelButton() {
    driver.findElement(By.className("portal-cancel-button")).click();
  }
  
  public void showNoteHistory() {
    click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }
  
  public String getCaseName(){
    return findElementById("case-item:case-name-component:case-header-name-cell").getText();
  }
  
  public void clickAdhocButton() {
    String adhocButtonCssSelection = "a[id$='start-adhoc']";
    findElementByCssSelector(adhocButtonCssSelection).click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickAdhocOkButton() {
    String adhocButtonCssSelection = "button[id$='start-adhoc-ok-button']";
    findElementByCssSelector(adhocButtonCssSelection).click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickAdhocCancelButton() {
    String adhocButtonCssSelection = "button[id$='start-adhoc-cancel-button']";
    findElementByCssSelector(adhocButtonCssSelection).click();
    waitAjaxIndicatorDisappear();
  }
  
  public String getTaskId() {
    String taskTitleCssSelection = "span[id$='title']";
    String taskTitle = findElementByCssSelector(taskTitleCssSelection).getText();
    String taskId = taskTitle.substring(taskTitle.indexOf("#") + 1, taskTitle.indexOf(")"));
    return taskId;
  }
  
  public boolean isShowAdhocHistoryBtnNotExist() {
    String adhocHistoryBtnCSSSelection = "a[id$='show-adhoc-history']";
    return driver.findElements(By.cssSelector(adhocHistoryBtnCSSSelection)).isEmpty();
  }
  
  public boolean isAdhocHistoryDialogExist() {
    String adhocHistoryBtnCSSSelection = "div[id$='adhoc-task-history-dialog']";
    return !driver.findElements(By.cssSelector(adhocHistoryBtnCSSSelection)).isEmpty();
  }
}
