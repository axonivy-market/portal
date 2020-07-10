package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;

public class TaskDetailsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public String getCreatedOnDateText() {
    return findElementByCssSelector("span[id$='start-date']").getText();
  }

  public List<String> getTaskNoteAuthors() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector("td.task-document-author");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }


  public void changePriorityOfTask(int priorityValue) {
    click(findElementById("task-detail-template:general-information:priority-form:edit-priority-inplace_display"));
    waitForElementDisplayed(By.id("task-detail-template:general-information:priority-form:priority-select-menu_label"),
        true);
    click(findElementById("task-detail-template:general-information:priority-form:priority-select-menu_label"));
    WebElement prioritySelectElement = findElementById(
        String.format("task-detail-template:general-information:priority-form:priority-select-menu_%d", priorityValue));
    waitForElementDisplayed(prioritySelectElement, true);
    click(prioritySelectElement);
    clickByCssSelector(
        "#task-detail-template\\:general-information\\:priority-form\\:edit-priority-inplace_editor .ui-inplace-save");
    waitAjaxIndicatorDisappear();
  }

  public String getPriorityOfTask() {
    return findElementByCssSelector("span[id$='edit-priority-inplace_display']").getText();
  }

  public void changeNameOfTask(String name) {
    clickByCssSelector("span[id$='task-name-inplace_display']");
    WebElement taskNameInput = findElementByCssSelector("input[id$='task-name-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(name);
    clickByCssSelector(
        "#task-detail-template\\:task-detail-title-form\\:task-name-inplace_editor .ui-inplace-save");
    waitAjaxIndicatorDisappear();
  }


  public String getNameOfTaskWhenDisplayingDetailsAt() {
    return findElementByCssSelector("span[id$='task-name-inplace_display']").getText();
  }

  public boolean isAddNoteButtonDisplayed() {
    return isElementDisplayedById("task-detail-template:task-notes:add-note-command");
  }

  public boolean isShowMoreNoteButtonDisplayed() {
    return isElementDisplayedById("task-detail-template:task-notes:show-more-note-link");
  }

  public boolean isAddDocumentLinkDisplayed() {
    return isElementDisplayedById("task-detail-template:task-documents:add-document-command");
  }
  
  public TaskWidgetPage goBackToTaskListFromTaskDetails() {
    clickBackButton();
    return new TaskWidgetPage();
  }
  
  public boolean isBackButtonPresented() {
    return findElementById("task-detail-template:task-detail-title-form:back-to-previous-page").isDisplayed();
  }
  
  public void clickBackButton() {
    findElementById("task-detail-template:task-detail-title-form:back-to-previous-page").click();
  }

  public TaskTemplatePage clickStartTask() {
    findElementById("task-detail-template:task-detail-start-command").click();
    return new TaskTemplatePage();
  }

  public void clickTaskListBreadCrumb() {
    click(By.cssSelector(".portal-breadcrumb ul li:nth-of-type(3) .ui-menuitem-link"));
    ensureNoBackgroundRequest();
  }
  
  public String getTaskName() {
    String[] breadcrumbTexts = getTextOfCurrentBreadcrumb().split(":");
    int item = breadcrumbTexts.length;
    if (item > 1) {
      return breadcrumbTexts[item - 1].trim();
    }
    return breadcrumbTexts[0].trim();
  }
  
  public CaseDetailsPage backToCaseDetails() {
    clickBackButton();
    return new CaseDetailsPage();
  }

  public WebElement getTaskGeneralInformation() {
    return findElementByCssSelector("[id$='task-detail-template:task-detail-general-container']");
  }

  public void openAddNoteDialog() {
    click(findElementByCssSelector("[id$='task-detail-template:task-notes:add-note-command']"));
    waitForElementDisplayed(By.id("task-detail-template:task-notes:add-new-note-dialog"), true);
  }

  public WebElement getAddNoteDialog() {
    return findElementByCssSelector("[id$='task-detail-template:task-notes:add-new-note-dialog']");
  }

  public void openAddAttachmentDialog() {
    click(findElementByCssSelector("[id$='task-detail-template:task-documents:add-document-command']"));
    waitForElementDisplayed(By.id("task-detail-template:task-documents:document-upload-dialog"), true);
  }

  public void addNoteToTaskWithContent(String content) {
    openAddNoteDialog();
    waitForElementDisplayed(By.cssSelector("div.ui-dialog[aria-hidden='false']"), true);
    WebElement addNoteDialog = findElementByCssSelector("div.ui-dialog[aria-hidden='false']");
    waitForElementDisplayed(addNoteDialog, true);
    addNoteDialog.findElement(By.cssSelector("textarea[id$='note-content']")).sendKeys(content);
    click(addNoteDialog.findElement(By.cssSelector("button[id$='save-add-note-command']")));
    waitAjaxIndicatorDisappear();
  }

  public void uploadDocument(String path) {
    Sleeper.sleep(500);//slow down a bit for FF
    openAddAttachmentDialog();
    Sleeper.sleep(500);
    uploadDocumentByPath(path);
    waitForElementDisplayed(By.cssSelector("span[class$='ui-messages-info-summary']"), true);
    click(By.cssSelector("button[id$=':task-documents:document-upload-close-command']"));
  }

  private void uploadDocumentByPath(String path) {
    findElementByCssSelector("input[id$='document-upload-panel_input']").sendKeys(path);
  }
  
  public void waitUtilsTaskDetailsDisplayed() {
    waitForElementDisplayed(By.id("task-detail-template:task-detail-container"), true);
  }

  public WebElement getTaskHistories() {
    return findElementByCssSelector("[id$='task-detail-template:task-detail-note-container']");
  }

  public WebElement getTaskAttachment() {
    return findElementByCssSelector("[id$='task-detail-template:task-detail-document-container']");
  }

  public WebElement getAddAttachmentDialog() {
    return findElementByCssSelector("[id$='task-detail-template:task-documents:document-upload-dialog']");
  }

  public void clickOnDeleteDocumentIcon(int index) {
    String deleteIconId = String.format("[id$=':task-documents:task-details-documents:%s:delete-file']", index);
    click(findElementByCssSelector(deleteIconId));
    waitForElementDisplayed(By.id("task-detail-template:task-documents:document-deletion-dialog_content"), true);
  }

  public WebElement getDeleteDocumentConfirmDialog() {
    return findElementByCssSelector("[id$=':task-documents:document-deletion-dialog']");
  }

  public void clickOnShowMoreHistories() {
    click(findElementByCssSelector("[id$=':task-notes:show-more-note-link']"));
  }
  
  public String getTaskResponsible() {
    return findElementById("task-detail-template:general-information:task-activator:user").getText();
  }
  
  public String getTaskId() {
    return findElementById("task-id").getText();
  }
  
  
  public void openTaskDelegateDialog() {
    openActionPanel();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> findElementByCssSelector("a[id$='task-delegate-command']").isDisplayed());
    clickByCssSelector("a[id$='task-delegate-command']");
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
  }
  
  public void openActionPanel() {
    String actionButtonId = "task-detail-template:additional-options:task-detail-more-step";
    waitForElementDisplayed(By.id(actionButtonId), true);
    click(By.id(actionButtonId));
    waitForElementDisplayed(By.id("task-detail-template:additional-options:side-steps-panel"),true);
  }
  
  public void selectDelegateResponsible(String responsibleName, boolean isRole) {
    if(isRole) {
      List<WebElement> radioButtonLabels = findListElementsByCssSelector("table[id$='activator-type-select'] label");
      click(radioButtonLabels.get(1));
      waitAjaxIndicatorDisappear();
      waitForElementDisplayed(By.cssSelector("input[id$='group-activator-select_input']"), true);
      type(By.cssSelector("input[id$='group-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='group-activator-select_panel']"), true);
      List<WebElement> foundRoles = findElementByCssSelector("span[id$='group-activator-select_panel").findElements(By.tagName("li"));
      click(foundRoles.get(0));
    }
    else {
      waitForElementDisplayed(By.cssSelector("input[id$='user-activator-select_input']"), true);
      type(By.cssSelector("input[id$='user-activator-select_input']"), responsibleName);
      waitForElementDisplayed(By.cssSelector("span[id$='user-activator-select_panel']"), true);
      List<WebElement> foundUsers = findElementByCssSelector("span[id$='user-activator-select_panel']").findElements(By.tagName("tr"));
      click(foundUsers.get(0));
    }
    waitAjaxIndicatorDisappear();
    click(By.cssSelector("button[id$='proceed-task-delegate-command']"));
    waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), false);
  }
}
