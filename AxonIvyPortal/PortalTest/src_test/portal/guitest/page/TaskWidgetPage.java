package portal.guitest.page;

import static portal.guitest.common.WaitHelper.assertTrueWithWait;

import java.util.ArrayList;
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

import portal.guitest.common.TaskState;
import portal.guitest.common.WaitHelper;

public class TaskWidgetPage extends TemplatePage {

	private String taskWidgetId;
	private static final String TASK_ACTION = "horizontal-task-actions";
	private static final String CLASS = "class";
	private static final String ID_END = "*[id$='";
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
		return "//*[contains(@id,'task-widget:task-view')]";
	}

	public void expand() {
	  waitForElementExisted("a[id$=':task-list-link:task-list-link']",true,5);
		WebElement fullModeButton = findElementById(taskWidgetId + ":task-list-link:task-list-link");
		click(fullModeButton);
		ensureNoBackgroundRequest();
		waitForElementExisted("[id$=':filter-save-action']",true, 10);
		waitForLocatorDisplayed("id('" + taskWidgetId + ":filter-save-action')");
	}

	public TaskDetailsPage openTaskDetails(int index) {
		return clickOnTaskEntryInFullMode(index);
	}

	public TaskDetailsPage openTaskDetailsFromActionMenu(int index) {
		sideStepMenuOnActionButton(index);
		String detailOptionCssSelector = "a[id$='task-open-detail-command']";
		waitForElementDisplayed(By.cssSelector(detailOptionCssSelector), true);
		click(By.cssSelector(detailOptionCssSelector));
		return new TaskDetailsPage();
	}

	public void showNoteHistory() {
		click(driver.findElement(By.cssSelector("a[id$='show-more-note-link']")));
	}

	private TaskDetailsPage clickOnTaskEntryInFullMode(int index) {
		clickByCssSelector("div[id$='" + index + "\\:task-item\\:task-info']");
		return new TaskDetailsPage();
	}

	public TaskTemplatePage startTask(int index) {
		waitTaskAppearThenClick(index);
		waitForElementPresent(By.id(TASK_ACTION), true);
		return new TaskTemplatePage();
	}
	
	public String getTaskIdOfRow(int taskRowIndex) {
	  return findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-id-component:task-id", taskRowIndex)).getText();
	}

	public TaskTemplatePage startTaskWithouWaitForTaskActionPresent(int index) {
    waitTaskAppearThenClick(index);
    return new TaskTemplatePage();
  }
	
	public void startDeprecatedTaskTemplate(int index) {
		waitTaskAppearThenClick(index);
		waitForElementPresent(By.id("task-actions"), true);
	}

	public boolean isTaskDelegateOptionDisable(int index) {
		sideStepMenuOnActionButton(index);
		waitForElementDisplayed(By.id(taskWidgetId + ":task-list-scroller:" + index
				+ ":task-item:task-action:additional-options:task-delegate-command"), true);
		WebElement delegateButton = findElementById(taskWidgetId + ":task-list-scroller:" + index
				+ ":task-item:task-action:additional-options:task-delegate-command");
		return delegateButton.getAttribute(CLASS).contains("ui-state-disabled");
	}

	public int countTasks() {
		List<WebElement> taskElements = findListElementsByCssSelector("div[class*='task-start-list-item']");
		return taskElements.size();
	}

  public void filterTasksBy(String keyword, int... expectedNumberOfTasksAfterFiltering) {
    if (countTasks() == getExpectedNumberOfTasks(expectedNumberOfTasksAfterFiltering)) {
      return;
    }
    WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR);
    WaitHelper.assertTrueWithWait(() -> {
      keywordFilter.clear();
      keywordFilter.click(); // To make Firefox more stable
      keywordFilter.sendKeys(keyword);
      return keywordFilter.getAttribute("value").equals(keyword);
    });
    waitForNumberOfTasks(expectedNumberOfTasksAfterFiltering);
  }

  private void waitForNumberOfTasks(int... expectedNumberOfTasksAfterFiltering) {
    int expectedNumber = getExpectedNumberOfTasks(expectedNumberOfTasksAfterFiltering);
    WaitHelper.assertTrueWithWait(() -> this.countTasks() == expectedNumber);
  }

  private int getExpectedNumberOfTasks(int... expectedNumberOfTasksAfterFiltering) {
    int expectedNumber;
    if (expectedNumberOfTasksAfterFiltering.length == 0) {
      expectedNumber = 1;
    } else {
      expectedNumber = expectedNumberOfTasksAfterFiltering[0];
    }
    return expectedNumber;
  }

	public void filterTasksInExpandedModeBy(String keyword, int... expectedNumberOfTasksAfterFiltering) {
		waitForElementDisplayed(By.cssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE), true);
		WebElement keywordFilter = findElementByCssSelector(KEYWORD_FILTER_SELECTOR_EXPANDED_MODE);
		keywordFilter.clear();
		keywordFilter.sendKeys(keyword);
    waitForNumberOfTasks(expectedNumberOfTasksAfterFiltering);
	}

	public CaseDetailsPage openRelatedCaseOfTask() {
		click(findElementByCssSelector("a[id$='related-case']"));
		return new CaseDetailsPage();
	}

	public String getRelatedCase() {
		WebElement relatedCaseLink = findElementByCssSelector("a[id$='related-case']");
		return relatedCaseLink.getText();
	}

	public void sideStepMenuOnActionButton(int index) {
		String actionButton =
		String.format("[id$='%d\\:task-item\\:task-action\\:additional-options\\:task-side-steps-menu']", index);
		waitForElementDisplayed(By.cssSelector(actionButton), true);
		clickByCssSelector(actionButton);
		ensureNoBackgroundRequest();
		waitForElementDisplayed(
		By.cssSelector("div[id$='side-steps-panel'].ui-overlay-visible a[id$='adhoc-side-step-item']"), true);
	}
	
	public TaskTemplatePage clickOnSideStepAction(int taskIndex, int sideStepIndex) {
	  String sideStepsId = String.format("task-widget:task-list-scroller:%d:task-item:task-action:additional-options:task-additional-actions", taskIndex);
	  WebElement sideStepPanel = findElementByCssSelector("[id$='" + sideStepsId + "']");
	  List<WebElement> sideSteps = sideStepPanel.findElements(By.className("option-item"));
	  click(sideSteps.get(sideStepIndex));
	  return new TaskTemplatePage();
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
		String resetCommandButton = String.format(
				taskWidgetId + ":task-list-scroller:%s:task-item:task-action:additional-options:task-reset-command", taskId);
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
		WebElement stateCell = findElementById(
				taskWidgetId + ":task-list-scroller:" + taskRowIndex + ":task-item:task-state-component:task-state");
		if (stateCell != null) {
			String stateClass = stateCell.findElement(By.className("icon")).getAttribute(CLASS);
			return TaskState.fromClass(stateClass.substring(stateClass.indexOf("task-state-")));
		}
		return null;
	}

	public String getTaskStateTooltip(int taskRowIndex) {
		WebElement stateTooltip = findElementById(
				"task-widget:task-list-scroller:" + taskRowIndex + ":task-item:task-state-component:state-tooltip");
		if (stateTooltip != null) {
			WebElement stateContent = findChildElementByClassName(stateTooltip, "ui-tooltip-text");
			return stateContent.getAttribute("innerText");
		}
		return StringUtils.EMPTY;
	}

	public String getExpiryOfTaskAt() {
		waitForElementDisplayed(By.id("task-detail-template:general-information:expiry-form:edit-inplace_display"), true);
		WebElement taskExpiry =
				findElementById("task-detail-template:general-information:expiry-form:edit-inplace_display");
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

	public void sortTaskListByColumn(String columnHeaderText, int rowIndex, String columnId, String expectedValue) {
		WebElement taskListHeader = findElementById(taskWidgetId + ":task-widget-sort-menu");
		for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
			if (columnHeaderText.equals(column.getText())) {
				column.click();
				break;
			}
		}
		WaitHelper.assertTrueWithWait(() -> getTaskListCustomCellValue(rowIndex, columnId).equals(expectedValue)); 
	}

	public String getTaskListCustomCellValue(int index, String columnId) {
		WebElement cell = findElementById(
				String.format(taskWidgetId + ":task-list-scroller:%d:task-item:%s-component:%s", index, columnId, columnId));
		return cell.getText();
	}

	public void openTaskDelegateDialog(int index) {
		sideStepMenuOnActionButton(index);
		Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
				.until(() -> findElementByCssSelector("a[id$='\\:task-delegate-command']").isDisplayed());
		clickByCssSelector("a[id$='\\:task-delegate-command']");
		waitForElementDisplayed(By.cssSelector("div[id$='task-delegate-dialog']"), true);
	}

	public void waitUntilTaskCountDifferentThanZero() {
	  Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
    .until(() -> getTaskCount().intValue() != 0);
	}
	
	public boolean isDelegateTypeSelectAvailable() {
		return isElementPresent(By.cssSelector("div[id$=':activator-panel']"));
	}

	public boolean isDelegateTypeDisabled(int index) {
		WebElement delegateTypeRadioButton = findElementByCssSelector(String.format(
				"input[id$='task-widget\\:task-item-delegate-component\\:task-delegate-form\\:activator-type-select\\:%d']",
				index));

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
		WebElement name = findElementByCssSelector(ID_END + index + ":task-item:task-name-component:task-name']");
		return name.getText();
	}

	/**
	 * Get name from task item in task scroller at specific index in compact list (homepage)
	 * 
	 * @param index
	 * @return task name
	 */
	public String getNameOfTaskInCompactListAt(int index) {
		WebElement name =
				findElementByCssSelector(ID_END + index + ":task-item:task-start-item-view:task-start-task-name']");
		return name.getText();
	}
	
	public String getResponsibleOfTaskAt(int index) {
    List<WebElement> responsibles = findListElementsByClassName("responsible-cell");
    return responsibles.get(index).getText();
  }

	public boolean isFilterSelectionVisible() {
		return isElementPresent(By.id(taskWidgetId + ":filter-selection-form:filter-selection-panel"));
	}

	public void openAdvancedFilter(String filterName, String filterIdName) {
		click(By.cssSelector("[id$='filter-add-action']"));
		WebElement filterSelectionElement = findElementById(taskWidgetId + ":filter-add-form:filter-selection");
		List<WebElement> elements = findChildElementsByTagName(filterSelectionElement, "LABEL");
		for (WebElement element : elements) {
			if (element.getText().equals(filterName)) {
				click(element);
				click(By.cssSelector("[id$='task-widget:filter-add-form:update-filter-selected-command']"));
				waitAjaxIndicatorDisappear();
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
	
  public List<String> getListStateFilterSelection() {
    List<WebElement> stateFilterSelectionElementList =
        findListElementsByCssSelector("label[for*=':state-filter:filter-input-form:state-selection:']");
    return stateFilterSelectionElementList.stream().map(WebElement::getText).collect(Collectors.toList());
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
		waitAjaxIndicatorDisappear();
	}

	public void filterByCustomerName(String text) {
		click(By.cssSelector(
				"button[id$='" + taskWidgetId + ":customer-name-filter:filter-open-form:advanced-filter-command']"));
		WebElement customerNameInput =
				findElementByCssSelector("input[id$='customer-name-filter:filter-input-form:customVarChar5']");
		enterKeys(customerNameInput, text);
		click(By.cssSelector("button[id$='" + taskWidgetId + ":customer-name-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}

	public void filterByResponsible(String text) {
	  waitForElementDisplayed(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"), true);
		click(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
		WebElement responsible =
				findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']");
		type(responsible,text);
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
		waitForElementDisplayedByCssSelector("span[id$='responsible-filter:filter-input-form:responsible_panel']");
		click(By.cssSelector("i[class*='neutral']"));
		waitAjaxIndicatorDisappear();
		click(By.cssSelector("button[id$='responsible-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	public void removeResponsibleFilter() {
		waitForElementDisplayed(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"),
				true);
		click(By.cssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command']"));
		waitForElementDisplayed(By.cssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']"),
				true);
		findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").click();
		findElementByCssSelector("input[id$='responsible-filter:filter-input-form:responsible_input']").clear();
		click(By.cssSelector("button[id$='responsible-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}


	public void openStateFilterOverlayPanel() {
		click(By.cssSelector("button[id$='state-filter:filter-open-form:advanced-filter-command']"));
	}

	public String getDisplayStateInStateFilter() {
		WebElement stateFilter =
				findElementByCssSelector("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
		List<WebElement> elements = findChildElementsByTagName(stateFilter, "LABEL");
		List<String> states = elements.stream().map(WebElement::getText).collect(Collectors.toList());
		return StringUtils.join(states, ",");
	}

	public void clickOnTaskStatesAndApply(List<String> states) {
    openStateFilter();
    WebElement stateFilterPanel = findElementByCssSelector("div[id$='state-filter:filter-input-form:advanced-filter-panel']");
    List<String> labelList = findChildElementsByTagName(stateFilterPanel, "label").stream().map(WebElement::getText).collect(Collectors.toList());
    List<WebElement> checkBoxList = stateFilterPanel.findElements(By.cssSelector("div[class*='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
    List<Integer> statesSelectedIndex = new ArrayList<>();
    states.forEach(state -> {
      int stateIndex = labelList.indexOf(state);
      if (stateIndex >= 0 && stateIndex < labelList.size()) {
        statesSelectedIndex.add(stateIndex);
      }
    });
    
    checkBoxList.forEach(checkBox -> {
      if (checkBox.getAttribute("class").contains("ui-state-active")) checkBox.click();
    });
    
    statesSelectedIndex.forEach(index -> checkBoxList.get(index).click());

    click(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"));
    waitForElementDisplayed(By.cssSelector("button[id$='state-filter:filter-input-form:update-command']"), false);
  }
	
	public void clickOnStartTaskLink(int index) {
	  String startLinkId = String.format("a[id$='task-list-scroller:%d:task-item:task-action:task-action-component']", index);
	  refreshAndWaitElement(startLinkId);
    click(findElementByCssSelector(startLinkId));
	}

	public void saveFilter(String filterName) {
		click(By.id(taskWidgetId + ":filter-save-action"));
		waitAjaxIndicatorDisappear();
		waitForElementDisplayed(By.id(taskWidgetId + ":filter-save-form:save-filter-set-name-input"), true);
		WebElement filterNameInput = findElementById(taskWidgetId + ":filter-save-form:save-filter-set-name-input");
		enterKeys(filterNameInput, filterName);
		click(findElementById(taskWidgetId + ":filter-save-form:filter-save-command"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	public void openSavedFilters(String filterName) {
		click(findElementById("task-widget:filter-selection-form:filter-name"));
		List<WebElement> saveFilters = findListElementsByCssSelector("a[id$='user-defined-filter']");
		for (WebElement filter : saveFilters) {
			if (filter.getText().equals(filterName)) {
				click(filter);
				assertTrueWithWait(() -> findElementByCssSelector(".filter-name").getText().equals(filterName));
				return;
			}
		}
	}

	public String getResponsible() {
		refreshAndWaitElement("button[id$='responsible-filter:filter-open-form:advanced-filter-command']");
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
        () -> findElementByCssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command'] > span")
            .getText().length()>1);
		return findElementByCssSelector("button[id$='responsible-filter:filter-open-form:advanced-filter-command'] > span")
				.getText();
	}

	public String getFilterName() {
	  refreshAndWaitElement("a[id$='task-widget:filter-selection-form:filter-name']");
	  waitForElementDisplayed(By.cssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span"), true);
		WebElement filterName =
				findElementByCssSelector("a[id$='task-widget:filter-selection-form:filter-name'] > span");
		return filterName.getText();
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
    String taskId = getTaskId(index);
    waitTaskAppearThenClick(index);
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> {
      try {
        if (!findListElementsByCssSelector(".no-task-message").isEmpty()) {
          return true;
        }
        return !getTaskId(index).equals(taskId);
      } catch (Exception e) {
        return false;
      }
    });
  }

  private String getTaskId(int taskIndex) {
    return findElementByCssSelector("span[id$='" + taskIndex + "\\:task-item\\:task-start-item-view\\:task-start-task-id']").getText();
  }

	private void waitTaskAppearThenClick(int index) {
		WebElement taskListElement = findElementById(taskWidgetId + ":task-list-scroller");
		if (taskListElement.getAttribute(CLASS).contains("compact-mode")) {
      List<WebElement> taskItems = taskListElement.findElements(By.className("compact-task-start-link"));
      boolean isResumedTask = taskItems.get(index).getAttribute("id").contains("compact-resumed-task-start-link");
      click(taskItems.get(index));
      if (isResumedTask) {
        resetResumedTask();
      }
		} else {
			String cssSelector = String.format("a[id$='%d:task-item:task-action:task-action-component']", index);
			refreshAndWaitElement(cssSelector);
			clickByCssSelector(cssSelector);
		}
	}
	
  public void resetResumedTask() {
    waitForElementDisplayed(By.cssSelector("[id$=':reset-task-dialog_content']"), true);
    click(findElementByCssSelector("[id$=':reset-task-form:reset-task-button']"));
  }

  public boolean isResumedTask(int index) {
    List<WebElement> taskItems;
    WebElement taskListElement = findElementById(taskWidgetId + ":task-list-scroller");
    if (taskListElement.getAttribute(CLASS).contains("compact-mode")) {
      taskItems = taskListElement.findElements(By.className("compact-task-start-link"));
      return taskItems.get(index).getAttribute("id").contains("compact-resumed-task-start-link");
    }

    taskItems = taskListElement.findElements(By.className("task-start-list-item"));
    return taskItems.get(index).getAttribute("id").contains(":task-action:resume-task-action-component");
  }

	public Integer getTaskCount() {
		if (isElementDisplayed(By.cssSelector(".compact-task-widget"))) {
			WebElement taskTitleElement = findElementById("task-widget:task-widget-title");
			String title = taskTitleElement.findElement(By.cssSelector("span")).getText();
			String count = StringUtils.substringBetween(title, "(", ")");
			return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
		} else {
			String title = getTextOfCurrentBreadcrumb();
			String count = StringUtils.substringBetween(title, "(", ")");
			return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
		}
	}

	public boolean isTaskStateOpen(int index) {
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

	public boolean isAllCategoriesSelected() {
		return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-selected"));
	}

	public boolean isAllCategoriesUnselected() {
		return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-hasselected"));
	}

	public void openCategoryFilter() {
		click(By.cssSelector("button[id$='task-category-filter:filter-open-form:advanced-filter-command']"));
	}

	public void toggleNoCategory() {
		List<WebElement> categories = findListElementsByCssSelector(".filter-category-checkbox-tree .ui-tree-selectable");
		for (WebElement category : categories) {
			if (category.getText().equals("[No Category]")) {
				click(category);
				return;
			}
		}
	}

	public void applyCategoryFilter() {
		click(By.cssSelector("button[id$='task-category-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}

	public void resetFilter() {
		click(By.cssSelector("[id$='task-widget:filter-reset-action']"));
		waitAjaxIndicatorDisappear();
		click(By.cssSelector("[id$='task-widget:filter-reset-command']"));
		waitForElementDisplayed(By.id("task-widget:reset-filter-set-dialog"), false);
	}

  public boolean isTaskDestroyEnabled(int rowIndex) {
    WebElement destroyButton = findDestroyCommand(rowIndex);
    return !destroyButton.getAttribute(CLASS).contains("ui-state-disabled");
  }

  public void destroyTask(int rowIndex) {
    click(findDestroyCommand(rowIndex));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public WebElement findDestroyCommand(int rowIndex) {
    String destroyCommandButton = String.format(
        taskWidgetId + ":task-list-scroller:%s:task-item:task-action:additional-options:task-destroy-command", rowIndex);
    waitForElementDisplayed(By.id(destroyCommandButton), true);
    return findElementById(destroyCommandButton);
  }
  
  public void confimDestruction() {
    String destroyCaseDialogId = taskWidgetId + ":destroy-task-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
    WebElement confirmButton = findChildElementById(destroyConfirmationDialog, taskWidgetId + ":confirm-destruction");
    confirmButton.click();
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }
  
	
	public void openCompactSortMenu() {
	  click(By.cssSelector("[id$='sort-task-menu_label']"));
	  waitForElementDisplayed(By.cssSelector("div[id$='sort-task-menu_panel']"), true);
	}
	
	public WebElement getSaveFilterDialog() {
	  click(By.id(taskWidgetId + ":filter-save-action"));
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.id(taskWidgetId + ":filter-save-form:save-filter-set-name-input"), true);
    return findElementById(taskWidgetId + ":save-filter-set-dialog");
	}

  public void clickColumnsButton() {
    clickByCssSelector("[id$='task-widget:task-columns-configuration:task-config-command']");
    waitForElementDisplayedByCssSelector("label[for$=':columns-checkbox:3']");
  }

  public void waitForGrowlDisappear() {
    waitForElementDisplayed(By.id("portal-global-growl_container"), false);
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
  
   public List<String> getActiveTaskAction(int taskIndex) {
    clickOnTaskActionLink(taskIndex);
    WebElement actionPanel = findElementByCssSelector(String.format("div[id$='task-list-scroller:%d:task-item:task-action:additional-options:side-steps-panel']", taskIndex));
    return actionPanel.findElements(By.cssSelector("a[class*='option-item']")).stream().map(WebElement::getText).collect(Collectors.toList());
  }
  
  public void clickOnTaskActionLink(int taskIndex) {
    click(findElementByCssSelector(String.format("a[id$='task-list-scroller:%d:task-item:task-action:additional-options:task-side-steps-menu'", taskIndex)));
    waitForElementDisplayed(By.cssSelector(String.format("div[id$='task-list-scroller:%d:task-item:task-action:additional-options:side-steps-panel'", taskIndex)), true);
  }
  
  public void waitForActionGroupDisplay() {
    waitForElementDisplayed(By.cssSelector("div[class='action-container']"), true);
  }
}
