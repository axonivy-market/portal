package portal.guitest.page;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;
import portal.guitest.common.TaskState;

public class TaskWidgetPage extends TemplatePage {

  private String taskWidgetId;
  private static final String UI_INPLACE_SAVE = "ui-inplace-save";
  private static final String TASK_ACTION = "horizontal-task-actions";
  private static final String CLASS = "class";
  private static final String ID_END = "*[id$='";
  private static final String TASK_ITEM_TASK_INFO = ":task-item:task-info']";
  private static final String TASK_STATE_OPEN_ID =
      "task-widget:task-list-scroller:%d:task-item:task-state-component:task-state-open";
  private static final String TASK_STATE_RESERVED_ID =
      "task-widget:task-list-scroller:%d:task-item:task-state-component:task-state-reserved";
  private static final String KEYWORD_FILTER_SELECTOR =
      "input[id='task-widget:filter-form:filter-container:ajax-keyword-filter']";
  private static final String KEYWORD_FILTER_SELECTOR_EXPANDED_MODE =
      "input[id='task-widget:expanded-mode-filter-form:expanded-mode-filter-container:ajax-keyword-filter']";

  public TaskWidgetPage() {
    this("task-widget");
  }

  public TaskWidgetPage(String taskWidgetId) {
    this.taskWidgetId = taskWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'task-view')]";
  }

  @Override
  protected long getTimeOutForLocator() {
    return 150L;
  }

  public void expand() {
    WebElement fullModeButton = findElementById(taskWidgetId + ":task-list-link:task-list-link");
    fullModeButton.click();
    ensureNoBackgroundRequest();
    waitForLocatorDisplayed("id('" + taskWidgetId + ":filter-save-action')");
  }

  public TaskDetailsPage openTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index);
    return new TaskDetailsPage();
  }

  public void clickBackButtonFromTaskDetails() {
    findElementById("task-detail-template:task-detail-title-form:back-to-previous-page").click();
  }

  public void showNoteHistory() {
    click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
  }

  private TaskDetailsPage clickOnTaskEntryInFullMode(int index) {
    clickByCssSelector("div[id$='" + index + "\\:task-item\\:task-info']");
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    return taskDetailsPage;
  }

  public boolean isTaskShowDetails() {
    try {
      WebElement taskDetails = findElementByCssSelector("span[id='task-detail-template:task-detail-container']");
      return taskDetails.isDisplayed();
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

	public TaskTemplatePage startTask(int index) {
	  waitTaskAppearThenClick(index);
		waitForElementPresent(By.id(TASK_ACTION), true);
		return new TaskTemplatePage();
	}

  public boolean isTaskDelegateOptionDisable(int index) {
    sideStepMenuOnMoreButton(index);
    waitForElementDisplayed(By.id(taskWidgetId + ":task-list-scroller:" + index + ":task-item:task-action:additional-options:task-delegate-command"), true);
    WebElement delegateButton =
        findElementById(taskWidgetId + ":task-list-scroller:" + index + ":task-item:task-action:additional-options:task-delegate-command");
    return delegateButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public int countTasks() {
    List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
    return taskElements.size();
  }

  public void filterTasksBy(String keyword) {
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    keywordFilter.clear();
    keywordFilter.sendKeys(keyword);
    Sleeper.sleep(2000);
    waitAjaxIndicatorDisappear();
  }

  public void filterTasksInExpendedModeBy(String keyword) {
    waitForElementDisplayed(By.cssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE), true);
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE);
    keywordFilter.clear();
    keywordFilter.sendKeys(keyword);
    Sleeper.sleep(2000);
    waitAjaxIndicatorDisappear();
  }

  public CaseDetailsPage openRelatedCaseOfTask() {
    click(findElementByCssSelector("a[id$='related-case']"));
    return new CaseDetailsPage();
  }

  public String getRelatedCase() {
    WebElement relatedCaseLink = findElementByCssSelector("a[id$='related-case']");
    return relatedCaseLink.getText();
  }

  public void sideStepMenuOnMoreButton(int taskId) {
    String moreButton = String.format("button[id$='%d\\:task-item\\:task-action\\:additional-options\\:task-side-steps-menu'] span.fa-ellipsis-v", taskId);
    waitForElementDisplayed(By.cssSelector(moreButton), true);
    // Unstable step, after go to task list, click immediately to More button, Portal opens task detail.
    // could be related to Javascript running when loading page. Try to wait page ready before clicking More button. 
    Sleeper.sleep(2);
    clickByCssSelector(moreButton);
    ensureNoBackgroundRequest();
    waitForElementDisplayed(By.cssSelector("div[id$='side-steps-panel'].ui-overlay-visible a[id$='adhoc-side-step-item']"), true);
  }

  public boolean isMoreButtonDisplayed(int taskId) {
    String moreButton = String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:task-action:additional-options:task-side-steps-menu", taskId);
    return isElementDisplayedById(moreButton);
  }
  
  public void reserveTask(int taskId) {
    String reserveCommandButton = String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:task-action:additional-options:task-reserve-command", taskId);
    waitForElementDisplayed(By.id(reserveCommandButton), true);
    click(findElementById(reserveCommandButton));
  }

  public void resetTask(int taskId) {
    String resetCommandButton =
        String.format(taskWidgetId + ":task-list-scroller:%s:task-item:task-action:additional-options:task-reset-command", taskId);
    waitForElementDisplayed(By.id(resetCommandButton), true);
    click(findElementById(resetCommandButton));
  }

  public boolean isTaskStartEnabled(int taskId) {
    String startCommandButton =
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:task-action:task-action-component", taskId);
    WebElement element = findElementById(startCommandButton);
    return !element.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public TaskState getTaskState(int taskRowIndex) {
    WebElement stateCell =
        findElementById(taskWidgetId + ":task-list-scroller:" + taskRowIndex + ":task-item:task-state-component:task-state");
    if (stateCell != null) {
      String stateClass = stateCell.findElement(By.className("fa")).getAttribute(CLASS);
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

  public void changeExpiryOfTaskAt(String dateStringLiteral) {
    click(findElementById("task-detail-template:general-information:expiry-form:edit-inplace_display"));
    waitForElementDisplayed(By.id("task-detail-template:general-information:expiry-form:expiry-calendar"), true);
    WebElement taskExpiryInlineEdit = findElementById("task-detail-template:general-information:expiry-form:expiry-calendar_input");
    taskExpiryInlineEdit.sendKeys(dateStringLiteral);

    WebElement editor = findElementById("task-detail-template:general-information:expiry-form:edit-inplace_editor");
    WebElement saveButton = findChildElementByClassName(editor, UI_INPLACE_SAVE);
    saveButton.click();
  }

  public String getExpiryOfTaskAt() {
    waitForElementDisplayed(By.id("task-detail-template:general-information:expiry-form:edit-inplace_display"), true);
    WebElement taskExpiry = findElementById("task-detail-template:general-information:expiry-form:edit-inplace_display");
    return taskExpiry.getText();
  }

  public boolean isTaskPriorityChangeComponentPresented(int index) {
    return isElementPresent(By.id(String.format(
        taskWidgetId + ":task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace", index)));
  }

  public boolean isTaskNameChangeComponentPresented(int index) {
    return isElementPresent(By.id(
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:task-name-edit-form:task-name-input", index)));
  }

  public void changeDescriptionOfTask(String description) {
    clickByCssSelector("span[id$='task-description-output']");
    WebElement taskNameInput = findElementByCssSelector("textarea[id$='task-description-input']");
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
    taskNameInput.sendKeys(description);
    clickByCssSelector("span[id$='task-description-inplace_editor']  .ui-inplace-save");
    waitAjaxIndicatorDisappear();
  }

  public String getTaskDescription() {
    return findElementByCssSelector("span[id$='task-description-output']").getText();
  }

  public String getTaskCategory() {
    return findElementByCssSelector("span[id$='task-category']").getText();
  }

  public String getCaseCategory() {
    return findElementByCssSelector("span[id$='case-category']").getText();
  }
  
  public boolean isTaskListColumnExist(String columnHeaderText) {
    String taskListHeaderSelector = taskWidgetId + ":task-widget-sort-menu";
    waitForElementDisplayed(By.id(taskListHeaderSelector), true);
    WebElement taskListHeader = findElementById(taskListHeaderSelector);
    for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
      if (columnHeaderText.equals(column.getText())) {
        return true;
      }
    }
    return false;
  }

  public void sortTaskListByColumn(String columnHeaderText) {
    WebElement taskListHeader = findElementById(taskWidgetId + ":task-widget-sort-menu");
    for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
      if (columnHeaderText.equals(column.getText())) {
        column.click();
        break;
      }
    }
  }

  public String getTaskListCustomCellValue(int index, String columnId) {
    WebElement cell = findElementById(
        String.format(taskWidgetId + ":task-list-scroller:%d:task-item:%s-component:%s", index, columnId, columnId));
    return cell.getText();
  }

  public void openTaskDelegateDialog(int index) {
    sideStepMenuOnMoreButton(index);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> findElementByCssSelector("a[id$='task-delegate-command']").isDisplayed());
    clickByCssSelector("a[id$='task-delegate-command']");
  }

  public boolean isDelegateTypeSelectAvailable() {
    return isElementPresent(By.cssSelector("div[id$=':activator-panel']"));
  }

  public boolean isDelegateTypeDisabled(int taskIndex, int index) {
    WebElement delegateTypeRadioButton = findElementByCssSelector(String.format(
        "input[id$='%d\\:task-item\\:task-action\\:additional-options\\:task-delegate-form\\:activator-type-select\\:%d']", taskIndex, index));
    
    return Optional.ofNullable(delegateTypeRadioButton.getAttribute("disabled")).isPresent();
  }

  public boolean isDelegateListSelectionAvailable() {
    return isElementPresent(By.cssSelector("div[id$='select-delegate-panel']"));
  }

  public AdhocPage addAdhoc(int taskIndex) {
    clickByCssSelector(ID_END + taskIndex + ":task-item:task-side-steps-menu']");
    waitAjaxIndicatorDisappear();

    try {
      clickByCssSelector("a.side-step-item");
    } catch (Exception e) {
      clickByCssSelector("a.side-step-item");
    }
    waitForElementPresent(By.id(TASK_ACTION), true);
    return new AdhocPage();
  }

  /**
   * Get name from task item in task scroller at specific index. Task item must not be expanded.
   * 
   * @param index
   * @return task name
   */
  public String getNameOfTaskAt(int index) {
    WebElement name = findElementByCssSelector(ID_END + index + ":task-item:task-start-item-view:task-start-task-name']");
    return name.getText();
  }

  public boolean isFilterSelectionVisible() {
    return isElementPresent(By.id(taskWidgetId + ":filter-selection-form:filter-selection-panel"));
  }

  public void openAdvancedFilter(String filterName, String filterIdName) {
    clickByCssSelector("button[id$='filter-add-action']");
    WebElement filterSelectionElement = findElementById(taskWidgetId + ":filter-add-form:filter-selection");
    List<WebElement> elements = findChildElementsByTagName(filterSelectionElement, "LABEL");
    for (WebElement element : elements) {
      if (element.getText().equals(filterName)) {
        element.click();
        break;
      }
    }
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"),
        true);
  }

  public boolean isAdvancedFilterDisplayed(String filterIdName) {
    return isElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"));
  }

  public String getFilterValue(String filterId) {
    WebElement filterElement =
        findElementByCssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']");
    return filterElement.getText();
  }

  public String getStateFilterSelection(int pos) {
    WebElement stateFilterSelectionElement =
        findElementByCssSelector("label[for$='state-filter:filter-input-form:state-selection:" + pos + "']");
    return stateFilterSelectionElement.getText();
  }

  public void openStateFilter() {
    click(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']"));
  }

  public void filterByDescription(String text) {
    click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
    WebElement descriptionInput =
        findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
    enterKeys(descriptionInput, text);
    click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
    Sleeper.sleep(2000);
  }

  public void filterByCustomerName(String text) {
    click(By.cssSelector(
        "button[id$='" + taskWidgetId + ":customer-name-filter:filter-open-form:advanced-filter-command']"));
    WebElement customerNameInput =
        findElementByCssSelector("input[id$='customer-name-filter:filter-input-form:customVarChar5']");
    enterKeys(customerNameInput, text);
    click(By.cssSelector("button[id$='" + taskWidgetId + ":customer-name-filter:filter-input-form:update-command']"));
    Sleeper.sleep(2000);
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
    Sleeper.sleep(2000);
  }

  public void saveFilter(String filterName) {
    click(By.id(taskWidgetId + ":filter-save-action"));
    Sleeper.sleep(2000);
    WebElement filterNameInput = findElementById(taskWidgetId + ":filter-save-form:save-filter-set-name-input");
    enterKeys(filterNameInput, filterName);
    click(findElementById(taskWidgetId + ":filter-save-form:filter-save-command"));
    Sleeper.sleep(2000);
  }

  public String getFilterName() {
    click(findElementById(taskWidgetId + ":filter-selection-form:filter-name"));
    WebElement descriptionInput = findElementByCssSelector(".user-defined-filter-container");

    return descriptionInput.getText();
  }

  public String getTaskId() {
    String taskTitleCssSelection = "span[id$='task-start-task-id']";
    String taskTitle = findElementByCssSelector(taskTitleCssSelection).getText();
    String taskId = taskTitle.substring(taskTitle.indexOf("#") + 1, taskTitle.indexOf(")"));
    return taskId;
  }

  public boolean hasNoTask() {
    WebElement noTaskMessage = findElementByCssSelector("label[class*='no-task-message']");
    return noTaskMessage.isDisplayed();
  }

  public void startTaskWithoutUI(int index) {
    waitTaskAppearThenClick(index);
    new HomePage().isDisplayed();
  }

  private void waitTaskAppearThenClick(int index) {
    WebElement taskListElement = findElementById(taskWidgetId + ":task-list-scroller");
    if (taskListElement.getAttribute(CLASS).contains("compact-mode")) {
      String cssSelector = String.format("a[id$='%d\\:task-item\\:compact-task-start-link']", index) ;
      refreshAndWaitElement(cssSelector);
      clickByCssSelector(cssSelector);
    } else {
      String cssSelector = ID_END + index + TASK_ITEM_TASK_INFO;
      refreshAndWaitElement(cssSelector);
      clickByCssSelector(cssSelector);
    }
  }

  public int getTaskCount() {
    WebElement taskTitleElement = findElementById("task-widget:task-widget-title");
    String title = taskTitleElement.getText();
    return Integer.parseInt(title.substring(title.lastIndexOf("(") + 1, title.length() - 1));
  }

  public boolean isTaskStateDone(int index) {
    try {
      findElementById(String.format(TASK_STATE_OPEN_ID, index));
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  public boolean isTaskStateReserved(int index) {
    try {
      findElementById(String.format(TASK_STATE_RESERVED_ID, index));
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  private boolean isTaskActionDisplayed(String action, int taskIndex) {
    return isElementDisplayedById(String
        .format("task-widget:task-list-scroller:%d:task-item:task-action:additional-options:%s", taskIndex, action));
  }

  public boolean isTaskResetDisplayed() {
    return isTaskActionDisplayed("task-reset-command", 0);
  }

  public boolean isTaskDelegateDisplayed() {
    return isTaskActionDisplayed("task-delegate-command", 0);
  }

  public boolean isTaskReserverDisplayed() {
    return isTaskActionDisplayed("task-reserve-command", 0);
  }

  public boolean isAdhocSideStepDisplayed() {
    return isElementDisplayed(By.cssSelector("a[id$='adhoc-side-step-item']"));
  }

}
