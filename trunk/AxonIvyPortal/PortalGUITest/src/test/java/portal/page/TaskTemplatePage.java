package portal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskTemplatePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('request-tab')";
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
    waitForElementDisplayed(By.id(addNoteDialogId), true);
    WebElement addNoteDialog = findElementById(addNoteDialogId);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")).click();
    waitAjaxIndicatorDisappear();
  }

  public void openDocumentUploadingDialog() {
    findElementById("case-item:document:add-document-command").click();
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    waitForElementDisplayed(By.id(documentUploadingDialogId), true);
  }

  public Boolean isDocumentUploadingDialogDisplayed() {
    String documentUploadingDialogId = "case-item:document:document-upload-dialog";
    return findElementById(documentUploadingDialogId).isDisplayed();
  }

  public TaskWidgetPage openFinishedTaskInHistoryArea() {
    String firstFinishedTaskCssSelector = "div[id='case-item:history:case-histories'] > div > a";
    return openATaskInCaseDetails(firstFinishedTaskCssSelector);
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
    return findListElementsByCssSelector("div.truncate-note").size();
  }

  public int countHistoryItems() {
    return findListElementsByCssSelector("div[id='case-item:history:case-histories'] > div.author")
        .size();
  }

  public int countRelatedTasks() {
    return findListElementsByCssSelector("div[id='case-item:related-tasks'] a").size();
  }
}
