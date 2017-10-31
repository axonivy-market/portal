package portal.guitest.page;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portal.guitest.common.TaskState;

public class TaskWidgetPage extends TemplatePage {

  private static final String KEYWORD_FILTER_SELECTOR =
      "input[id='task-widget:filter-form:filter-container:ajax-keyword-filter']";

  @Override
  protected String getLoadedLocator() {
    return "id('task-widget:task-widget-sort-menu')";
  }

  public void expand() {
    WebElement fullModeButton = findElementById("task-widget:task-list-link:task-list-link");
    fullModeButton.click();
    Sleeper.sleepTight(5000);
  }

  public void openTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, true);
  }

  public void closeTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, false);
  }

  private void clickOnTaskEntryInFullMode(int index, boolean isDetailsShown) {
    WebElement taskShowHideDetailsLink = findElementByCssSelector("*[id$='" + index + ":task-item:task-info']");
    taskShowHideDetailsLink.click();
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("*[id$='" + index + ":task-item:task-details-container']"), isDetailsShown,
        DEFAULT_TIMEOUT);
  }

  public TaskDetailsPage getTaskDetailsElement(int index) {
    waitForElementDisplayed(By.cssSelector("*[id$='" + index + ":task-item:task-info']"), true, DEFAULT_TIMEOUT);
    WebElement taskDetailsItem = findElementByCssSelector("*[id$='" + index + ":task-item:task-start']");
    return new TaskDetailsPage(taskDetailsItem);
  }

  public boolean isTaskShowDetails(int index) {
    try {
      WebElement taskDetails = findElementByCssSelector("*[id$='" + index + ":task-item:task-details-container']");
      return taskDetails.isDisplayed();
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  public TaskTemplatePage startTask(int index) {
    WebElement taskListElement = findElementById("task-widget:task-list-scroller");
    if (taskListElement.getAttribute("class").contains("compact-mode")) {
      findElementByCssSelector("*[id*='" + index + ":task-item']").click();
    } else {
      findElementByCssSelector("*[id$='" + index + ":task-item:task-start-command']").click();
    }
    waitForElementPresent(By.id("copy-clipboard"), true);
    return new TaskTemplatePage();
  }

  public int countTasks() {
    List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
    return taskElements.size();
  }

  public void filterTasksBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    keywordFilter.sendKeys(keyword);
    Sleeper.sleepTight(2000);
    waitAjaxIndicatorDisappear();
  }

  public boolean isFilterDisplayed() {
    return isElementPresent(By.cssSelector(KEYWORD_FILTER_SELECTOR));
  }

  public long getIdOfTaskHasIndex(int index) {
    String text =
        findElementById(
            "task-widget:task-list-scroller:" + index + ":task-item:task-start-item-view:task-start-task-state")
            .getText().replaceAll("\\D", "");
    return Long.valueOf(text);
  }

  public CasePage openRelatedCaseOfTask(int taskId) {
    click(findElementByCssSelector("*[id$='task-details-container'] *[id$='related-case']"));
    waitForElementDisplayed(By.cssSelector("*[id$='case-widget-form']"), true);
    return new CasePage();
  }

  public String getRelatedCase() {
    WebElement relatedCaseLink = findElementByCssSelector("*[id$='task-details-container'] *[id$='related-case']");
    return relatedCaseLink.getText();
  }

  public void reserveTask(int taskId) {
    String reserveCommandButton =
        String.format("task-widget:task-list-scroller:%d:task-item:task-reserve-command", taskId);
    click(findElementById(reserveCommandButton));
  }

  public void resetTask(int taskId) {
    String resetCommandButton = String.format("task-widget:task-list-scroller:%d:task-item:task-reset-command", taskId);
    click(findElementById(resetCommandButton));
  }

  public TaskState getTaskState(int taskId) {
    WebElement stateCell =
        findElementById("task-widget:task-list-scroller:" + taskId + ":task-item:task-state-component:task-state");
    if (stateCell != null) {
      String stateClass = stateCell.findElement(By.className("fa")).getAttribute("class");
      return TaskState.fromClass(stateClass.substring(stateClass.indexOf("task-state-")));
    }
    return null;
  }

  public void changeExpiryOfTaskAt(int index, String dateStringLiteral) {
    WebElement taskExpiry =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_display", index));
    taskExpiry.click();

    String taskExpiryInlineId =
        String.format("task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:expiry-calendar_input",
            index);
    waitForElementDisplayed(By.id(taskExpiryInlineId), true);
    WebElement taskExpiryInlineEdit = findElementById(taskExpiryInlineId);
    taskExpiryInlineEdit.sendKeys(dateStringLiteral);

    WebElement editor =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_editor", index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
  }

  public String getExpiryOfTaskAt(int index) {
    String taskExpiryId =
        String.format("task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_display",
            index);
    waitForElementDisplayed(By.id(taskExpiryId), true);
    WebElement taskExpiry = findElementById(taskExpiryId);
    return taskExpiry.getText();
  }

  public void changePriorityOfTask(int index, int priorityValue) {
    WebElement taskPriority =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_display",
            index));
    taskPriority.click();
    String taskPriorityComboBoxId =
        String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:priority-select-menu_label", index);
    waitForElementDisplayed(By.id(taskPriorityComboBoxId), true);
    WebElement taskPriorityComboBox = findElementById(taskPriorityComboBoxId);
    taskPriorityComboBox.click();
    WebElement prioritySelectElement =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:priority-select-menu_%d", index,
            priorityValue));
    waitForElementDisplayed(prioritySelectElement, true);
    prioritySelectElement.click();
    WebElement editor =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_editor",
            index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  public String getPriorityOfTaskAt(int index) {
    String taskPriorityId =
        String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_display",
            index);
    waitForElementDisplayed(By.id(taskPriorityId), true);
    WebElement taskPriority = findElementById(taskPriorityId);
    return taskPriority.getText();
  }

  public boolean isTaskPriorityChangeComponentPresented(int index) {
    return isElementPresent(By.id(String.format(
        "task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace", index)));
  }

  public boolean isTaskNameChangeComponentPresented(int index) {
    return isElementPresent(By.id(String.format(
        "task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-input", index)));
  }

  public void changeNameOfTask(int index, String name) {
    String taskNameId =
        String
            .format(
                "task-widget:task-list-scroller:%d:task-item:task-name-component:task-name-edit-form:task-name-inplace_display",
                index);
    WebElement taskName = findElementById(taskNameId);
    taskName.click();
    String taskNameInputId =
        String.format(
            "task-widget:task-list-scroller:%d:task-item:task-name-component:task-name-edit-form:task-name-input",
            index);
    WebElement taskNameInput = findElementById(taskNameInputId);
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(name);
    WebElement editor =
        findElementById(String
            .format(
                "task-widget:task-list-scroller:%d:task-item:task-name-component:task-name-edit-form:task-name-inplace_content",
                index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  public String getNameOfTaskWhenDisplayingDetailsAt(int index) {
    String taskNameId =
        String
            .format(
                "task-widget:task-list-scroller:%d:task-item:task-name-component:task-name-edit-form:task-name-inplace_display",
                index);
    waitForElementDisplayed(By.id(taskNameId), true);
    WebElement taskName = findElementById(taskNameId);
    return taskName.getText();
  }

  public void changeDescriptionOfTask(int index, String description) {
    String taskDescriptionId =
        String
            .format(
                "task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_display",
                index);
    WebElement taskDescription = findElementById(taskDescriptionId);
    taskDescription.click();
    String taskDescriptionInputId =
        String.format(
            "task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-description-input",
            index);
    WebElement taskDescriptionInput = findElementById(taskDescriptionInputId);
    waitForElementDisplayed(taskDescriptionInput, true);
    taskDescriptionInput.clear();
    taskDescriptionInput.sendKeys(description);
    WebElement editor =
        findElementById(String
            .format(
                "task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_editor",
                index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitAjaxIndicatorDisappear();
  }

  public String getDescriptionOfTaskAt(int index) {
    String taskNameId =
        String
            .format(
                "task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_display",
                index);
    waitForElementDisplayed(By.id(taskNameId), true);
    WebElement taskName = findElementById(taskNameId);
    return taskName.getText();
  }

  public String getDescriptionInHeaderOfTaskAt(int index) {
    String taskNameId =
        String.format("task-widget:task-list-scroller:%d:task-item:task-name-component:task-description", index);
    waitForElementDisplayed(By.id(taskNameId), true);
    WebElement taskName = findElementById(taskNameId);
    return taskName.getText();
  }

  public boolean isTaskDescriptionChangeComponentPresented(int index) {
    return isElementPresent(By.id(String.format(
        "task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-description-input", index)));
  }

  public boolean isTaskListColumnExist(String columnHeaderText) {
    WebElement taskListHeader = findElementById("task-widget:task-widget-sort-menu");
    for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
      if (columnHeaderText.equals(column.getText())) {
        return true;
      }
    }
    return false;
  }

  public void sortTaskListByColumn(String columnHeaderText) {
    WebElement taskListHeader = findElementById("task-widget:task-widget-sort-menu");
    for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
      if (columnHeaderText.equals(column.getText())) {
        column.click();
        break;
      }
    }
  }

  public String getTaskListCellValue(int index, String columnId) {
    WebElement cell = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:%s", index, columnId));
    return cell.getText();
  }
  
  public void openTaskDelegateDialog(int index) {
    WebElement delegateButton = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-delegate-command", index));
    delegateButton.click();
  }
  
  public boolean isDelegateTypeSelectAvailable() {
    return isElementPresent(By.id("task-widget:task-list-scroller:0:task-item:task-delegate-form:activator-panel"));
  }
  
  public boolean isDelegateTypeDisabled(int taskIndex, int index) {
    WebElement delegateTypeRadioButton = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-delegate-form:activator-type-select:%d", taskIndex, index));
    return Optional.ofNullable(delegateTypeRadioButton.getAttribute("disabled")).isPresent();
  }
  
  public boolean isDelegateListSelectionAvailable() {
    return isElementPresent(By.id("task-widget:task-list-scroller:0:task-item:task-delegate-form:select-delegate-panel"));
  }

  public AdhocPage addAdhoc(int taskIndex) {
    WebElement moreAction = findElementByCssSelector("*[id$='" + taskIndex + ":task-item:task-side-steps-menu']");
    moreAction.click();
    waitAjaxIndicatorDisappear();

    WebDriverWait wait = new WebDriverWait(getDriver(), 10);
    WebElement adhocMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.side-step-item")));

    try {
      adhocMenuItem.click();
    } catch (Exception e) {
      adhocMenuItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.side-step-item")));
      adhocMenuItem.click();
    }
    waitForElementPresent(By.id("copy-clipboard"), true);
    return new AdhocPage();
  }

  public String getNameOfTaskAt(int index) {
    WebElement name =
        findElementByCssSelector("*[id$='" + index + ":task-item:task-start-item-view:task-start-task-name']");
    return name.getText();
  }
  
  public void openAdvancedFilter(String filter) {
    click(By.id("task-widget:filter-add-action"));
    WebElement filterSelectionElement = findElementById("task-widget:filter-add-form:filter-selection");
    findChildElementsByTagName(filterSelectionElement, "LABEL").forEach(filterElement -> {
      if (filter.equals(filterElement.getText())) {
        filterElement.click();
        return;
      }
    });
    waitForElementDisplayed(By.cssSelector("span[id$='" + filter.toLowerCase() + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }
  
  public void filterByDescription(String text) {
    click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
    WebElement descriptionInput = findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
    enterKeys(descriptionInput, text);
    click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
    Sleeper.sleepTight(2000);
  }
}
