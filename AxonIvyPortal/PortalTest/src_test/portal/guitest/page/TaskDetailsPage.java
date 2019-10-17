package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TaskDetailsPage extends TemplatePage {

  private WebElement taskDetailsItem;

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public String getCreatedOnDateText() {
    WebElement findElement = taskDetailsItem
        .findElement(By.cssSelector("#task-data > table > tbody > tr:nth-child(8) > td.task-details-data-2nd-column"));
    return findElement.getText();
  }

  public List<String> getTaskNoteAuthors() {
    WebElement taskHistoriesDiv = findElementById("task-note");
    List<WebElement> noteAuthorElements = taskHistoriesDiv.findElements(By.cssSelector("span[id$=user]"));
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }


  public void changePriorityOfTask(int priorityValue) {
    findElementById("task-detail-template:general-information:priority-form:edit-priority-inplace_display").click();
    waitForElementDisplayed(By.id("task-detail-template:general-information:priority-form:priority-select-menu_label"),
        true);
    findElementById("task-detail-template:general-information:priority-form:priority-select-menu_label").click();
    WebElement prioritySelectElement = findElementById(
        String.format("task-detail-template:general-information:priority-form:priority-select-menu_%d", priorityValue));
    waitForElementDisplayed(prioritySelectElement, true);
    prioritySelectElement.click();
    findElementByCssSelector(
        "#task-detail-template\\:general-information\\:priority-form\\:edit-priority-inplace_editor .ui-inplace-save")
            .click();
    waitAjaxIndicatorDisappear();
  }

  public String getPriorityOfTask() {
    return findElementByCssSelector("span[id$='edit-priority-inplace_display']").getText();
  }

  public void changeNameOfTask(String name) {
    findElementByCssSelector("span[id$='task-name-inplace_display']").click();
    WebElement taskNameInput = findElementByCssSelector("input[id$='task-name-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(name);
    findElementByCssSelector(
        "#task-detail-template\\:task-detail-title-form\\:task-name-inplace_editor .ui-inplace-save").click();
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
}
