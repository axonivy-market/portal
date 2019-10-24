package portal.guitest.page;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import portal.guitest.common.TaskState;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public class TaskWidgetPage extends TemplatePage {

  private static final String KEYWORD_FILTER_SELECTOR =
      "input[id='task-widget:filter-form:filter-container:ajax-keyword-filter']";
  private static final String KEYWORD_FILTER_SELECTOR_EXPANDED_MODE =
          "input[id='task-widget:expanded-mode-filter-form:expanded-mode-filter-container:ajax-keyword-filter']";

  @Override
  protected String getLoadedLocator() {
    return "id('task-widget:task-view')";
  }

  public void expand() {
    WebElement fullModeButton = findElementById("task-widget:task-list-link:task-list-link");
    fullModeButton.click();
    String engineUrl = System.getProperty("engineUrl");
    if (ENGINE_URL_LOCAL.equals(engineUrl)) {
      Sleeper.sleepTight(7000);
    } else {
      Sleeper.sleepTight(5000);
    }
  }

  public void openTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, true);
  }

  public void closeTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, false);
  }

  public void showNoteHistory() {
    click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }

  private void clickOnTaskEntryInFullMode(int index, boolean isDetailsShown) {
    WebElement taskShowHideDetailsLink =
        findElementByXpath("//a[contains(@id, '" + index
            + ":task-item:resume-task-show-task-detail-link') or contains(@id, '" + index
            + ":task-item:show-task-detail-link')]");
    taskShowHideDetailsLink.click();
    waitAjaxIndicatorDisappear();
    waitForElementPresent(By.cssSelector("*[id$='" + index + ":task-item:description:task-description-form"), isDetailsShown,
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
      findElementByCssSelector("*[id$='" + index + ":task-item:task-info']").click();
    }
    waitForElementPresent(By.id("side-steps-menu"), true);
    return new TaskTemplatePage();
  }

  public int countTasks() {
    List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
    return taskElements.size();
  }

  public void filterTasksBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    keywordFilter.clear();
    keywordFilter.sendKeys(keyword);
    Sleeper.sleepTight(2000);
    waitAjaxIndicatorDisappear();
  }
  
  public void filterTasksInExpendedModeBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE);
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

  public CasePage openRelatedCaseOfTask() {
    click(findElementByCssSelector("*[id$='task-details-container'] *[id$='related-case']"));
    waitForElementDisplayed(By.cssSelector("*[id$='case-list']"), true);
    return new CasePage();
  }

  public String getRelatedCase() {
    WebElement relatedCaseLink = findElementByCssSelector("*[id$='task-details-container'] *[id$='related-case']");
    return relatedCaseLink.getText();
  }

  public void reserveTask(int taskId) {
    String reserveCommandButton =
        String.format("task-widget:task-list-scroller:%d:task-item:task-action:task-reserve-command", taskId);
    click(findElementById(reserveCommandButton));
  }

  public void resetTask(int taskId) {
    String resetCommandButton =
        String.format("task-widget:task-list-scroller:0:task-item:task-action:task-reset-command", taskId);
    click(findElementById(resetCommandButton));
  }

  public TaskState getTaskState(int taskRowIndex) {
    WebElement stateCell =
        findElementById("task-widget:task-list-scroller:" + taskRowIndex + ":task-item:task-state-component:task-state");
    if (stateCell != null) {
      String stateClass = stateCell.findElement(By.className("fa")).getAttribute("class");
      return TaskState.fromClass(stateClass.substring(stateClass.indexOf("task-state-")));
    }
    return null;
  }
  
  public String getTaskStateTooltip(int taskRowIndex) {
    WebElement stateTooltip =
        findElementById("task-widget:task-list-scroller:" + taskRowIndex + ":task-item:task-state-component:state-tooltip");
    if (stateTooltip != null) {
      WebElement stateContent = findChildElementByClassName(stateTooltip, "ui-tooltip-text");
      return stateContent.getAttribute("innerText");
    }
    return StringUtils.EMPTY;
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

  public String getTaskListCustomCellValue(int index, String columnId) {
    WebElement cell = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:%s-component:%s", index, columnId, columnId));
    return cell.getText();
  }

  public void openTaskDelegateDialog(int index) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
      .until(() -> findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-action:task-delegate-command",index)).isDisplayed());

    WebElement delegateButton =
        findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-action:task-delegate-command",
            index));
    delegateButton.click();
  }

  public boolean isDelegateTypeSelectAvailable() {
    return isElementPresent(By.id("task-widget:task-list-scroller:0:task-item:task-delegate-form:activator-panel"));
  }

  public boolean isDelegateTypeDisabled(int taskIndex, int index) {
    WebElement delegateTypeRadioButton =
        findElementById(String
            .format("task-widget:task-list-scroller:%d:task-item:task-delegate-form:activator-type-select:%d",
                taskIndex, index));
    return Optional.ofNullable(delegateTypeRadioButton.getAttribute("disabled")).isPresent();
  }

  public boolean isDelegateListSelectionAvailable() {
    return isElementPresent(By
        .id("task-widget:task-list-scroller:0:task-item:task-delegate-form:select-delegate-panel"));
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
    waitForElementPresent(By.id("side-steps-menu"), true);
    return new AdhocPage();
  }

  /**
   * Get name from task item in task scroller at specific index. Task item must not be expanded.
   * 
   * @param index
   * @return task name
   */
  public String getNameOfTaskAt(int index) {
    WebElement name = findElementByCssSelector("*[id$='" + index + ":task-item:task-name-component:task-name']");
    return name.getText();
  }

  public String getResposibleOfTaskAt(int index) {
    WebElement taskStartInfo = findElementByCssSelector("div[id$='" + index + ":task-item:task-info']");
    WebElement responsibleSpan = taskStartInfo.findElement(By.cssSelector("span[id$='task-responsible']"));
    return responsibleSpan.getText();
  }

  public boolean isFilterSelectionVisible() {
    return isElementPresent(By.id("task-widget:filter-selection-form:filter-selection-panel"));
  }

  public void openAdvancedFilter(String filterName, String filterIdName) {
    click(By.id("task-widget:filter-add-action"));
    WebElement filterSelectionElement = findElementById("task-widget:filter-add-form:filter-selection");
    findChildElementsByTagName(filterSelectionElement, "LABEL").forEach(filterElement -> {
      if (filterName.equals(filterElement.getText())) {
        filterElement.click();
        return;
      }
    });
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
  }
  
  public boolean isAdvancedFilterDisplayed(String filterIdName) {
    return isElementDisplayed(By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"));
  }

  public void filterByDescription(String text) {
    click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
    WebElement descriptionInput =
        findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
    enterKeys(descriptionInput, text);
    click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
    Sleeper.sleepTight(2000);
  }

  public void filterByCustomerName(String text) {
    click(By.cssSelector("button[id$='task-widget:customer-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement customerNameInput =
        findElementByCssSelector("input[id$='customer-name-filter:filter-input-form:customVarChar5']");
    enterKeys(customerNameInput, text);
    click(By.cssSelector("button[id$='task-widget:customer-name-filter:filter-input-form:update-command']"));
    Sleeper.sleepTight(2000);
  }
  
  public void openStateFilterOverlayPanel() {
    click(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']"));
  }
  
  public String getDisplayStateInStateFilter() {
    WebElement stateFilter = findElementByCssSelector("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
    List<WebElement> elements = findChildElementsByTagName(stateFilter, "LABEL");
    List<String> states = elements.stream().map(WebElement::getText).collect(Collectors.toList());
    return StringUtils.join(states, ",");
  }
  
  public void clickOnTaskStatesAndApply(List<String> states) {
    WebElement stateFilter = findElementByCssSelector("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
    List<WebElement> elements = findChildElementsByTagName(stateFilter, "LABEL");
    for(String state : states) {
      for(WebElement ele : elements){
        if (state.equals(ele.getText())) {
          ele.click();
          break;
        }
      }
    }
    click(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"));
    Sleeper.sleepTight(2000);
  }

  public void saveFilter(String filterName) {
    click(By.id("task-widget:filter-save-action"));
    Sleeper.sleepTight(2000);
    WebElement filterNameInput = findElementById("task-widget:filter-save-form:save-filter-set-name-input");
    enterKeys(filterNameInput, filterName);
    click(findElementById("task-widget:filter-save-form:filter-save-command"));
    Sleeper.sleepTight(2000);
  }

  public String getFilterName() {
    click(findElementById("task-widget:filter-selection-form:filter-name"));
    WebElement descriptionInput = findElementByCssSelector(".user-definied-filter-container");

    return descriptionInput.getText();
  }

  public boolean hasNoTask() {
    WebElement noTaskMessage = findElementByCssSelector("label[class*='no-task-message']");
    return noTaskMessage.isDisplayed();
  }

  public void startAndCancelTask() {
    findElementByCssSelector("*[id$='0:task-item:task-info']").click();
    waitForElementDisplayed(By.id("side-steps-menu"), true);
    click(findElementByClassName("portal-cancel-button"));
  }

  public boolean isTaskListShown() {
    WebElement taskDetails = findElementByCssSelector("div.js-task-list-container");
    return taskDetails.isDisplayed();
  }
  
  public String getStateInCompactMode(int index) {
    WebElement taskListElement = findElementById("task-widget:task-list-scroller");
    WebElement taskElement = taskListElement.findElement(By.cssSelector("*[id*='" + index + ":task-item']"));
    WebElement state = taskElement.findElement(By.cssSelector("*[id*='task-start-task-state']"));
    return state.getText().substring(state.getText().indexOf(" ") + 1);
  }
  
  public int getTaskCount() {
	WebElement taskTitleElement = findElementById("task-widget:task-widget-title");
	String title = taskTitleElement.getText();
	return Integer.parseInt(title.substring(title.lastIndexOf("(") + 1, title.length() -1));
  }
}