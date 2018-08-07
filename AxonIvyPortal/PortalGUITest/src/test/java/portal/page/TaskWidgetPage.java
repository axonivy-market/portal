package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class TaskWidgetPage extends TemplatePage {

  private static final String KEYWORD_FILTER_SELECTOR =
      "input[id='task-widget:filter-form:filter-container:ajax-keyword-filter']";

  @Override
  protected String getLoadedLocator() {
    return "id('task-widget:priority-sort')";
  }

  public void expand() {
    WebElement fullModeButton = findElementById("task-widget:switch-mode-command");
    fullModeButton.click();
    waitAjaxIndicatorDisappear();
    waitForElementEnabled(By.id("task-start-0-task-action-container"), true, DEFAULT_TIMEOUT);
  }

  public void openTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, true);
  }

  public void closeTaskDetails(int index) {
    clickOnTaskEntryInFullMode(index, false);
  }

  private void clickOnTaskEntryInFullMode(int index, boolean isDetailsShown) {
    WebElement taskShowHideDetailsLink =
        findElementByCssSelector("*[id$='" + index + ":task-item:show-hide-task-details-command']");
    taskShowHideDetailsLink.click();
    waitAjaxIndicatorDisappear();
    waitForElementDisplayed(By.cssSelector("*[id$='" + index + ":task-item:task-details-container']"), isDetailsShown,
        DEFAULT_TIMEOUT);
  }
  
  public TaskDetailsPage getTaskDetailsElement(int index) {
    waitForElementDisplayed(By.cssSelector("*[id$='" + index + ":task-item:task-start']"), true,
        DEFAULT_TIMEOUT);
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
    findElementByCssSelector("*[id$='" + index + ":task-item:task-start-command']").click();
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
    String text = findElementByCssSelector("div.task-start-info-header").getText().replaceAll("\\D", "");
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
    String reserveCommandButton = String.format("task-widget:task-list-scroller:%d:task-item:task-reserve-command", taskId);
    click(findElementById(reserveCommandButton));
  }


  public void resetTask(int taskId) {
    String resetCommandButton = String.format("task-widget:task-list-scroller:%d:task-item:task-reset-command", taskId);
    click(findElementById(resetCommandButton));
  }

  public Object getTaskState(int taskId) {
    return findElementById("task-widget:task-list-scroller:" + 0 + ":task-item:task-state").getText();
  }

  public void changeExpiryOfTaskAt(int index, String dateStringLiteral) {
    WebElement taskExpiry =
        findElementById(String.format(
            "task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_display", index));
    taskExpiry.click();

    String taskExpiryInlineId =
        String.format("task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:expiry-calendar_input", index);
    waitForElementDisplayed(By.id(taskExpiryInlineId), true);
    WebElement taskExpiryInlineEdit = findElementById(taskExpiryInlineId);
    taskExpiryInlineEdit.sendKeys(dateStringLiteral);

    WebElement editor =
        findElementById(String.format("task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_editor",
            index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
  }

  public String getExpiryOfTaskAt(int index) {
    String taskExpiryId =
        String.format("task-widget:task-list-scroller:%d:task-item:general-info:expiry-form:edit-inplace_display", index);
    waitForElementDisplayed(By.id(taskExpiryId), true);
    WebElement taskExpiry = findElementById(taskExpiryId);
    return taskExpiry.getText();
  }
  
	public void changePriorityOfTask(int index, int priorityValue) {
		WebElement taskPriority = findElementById(String
				.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_display",
						index));
		taskPriority.click();
		String taskPriorityComboBoxId = String.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:priority-select-menu_label", index);
		waitForElementDisplayed(By.id(taskPriorityComboBoxId), true);
		WebElement taskPriorityComboBox = findElementById(taskPriorityComboBoxId);
		taskPriorityComboBox.click();
		WebElement prioritySelectElement = findElementById(String
				.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:priority-select-menu_%d",
						index, priorityValue));
		prioritySelectElement.click();
		WebElement editor = findElementById(String
				.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_editor",
						index));
		WebElement saveButton = findChildElementByClassName(editor,
				"ui-inplace-save");
		saveButton.click();
		waitForPageLoaded();
	}

	public String getPriorityOfTaskAt(int index) {
		String taskPriorityId = String
				.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace_display",
						index);
    waitForElementDisplayed(By.id(taskPriorityId), true);
    WebElement taskPriority = findElementById(taskPriorityId);
		return taskPriority.getText();
	}

	public boolean isTaskPriorityChangeComponentPresented(int index) {
		return isElementPresent(By.id(String.format("task-widget:task-list-scroller:%d:task-item:general-info:priority-form:edit-priority-inplace", index)));
	}
	
	public boolean isTaskNameChangeComponentPresented(int index) {
	  return isElementPresent(By.id(String.format("task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-input", index)));
	}
	
	public void changeNameOfTask(int index, String name) {
	  String taskNameId = String.format("task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-inplace_display", index);
		WebElement taskName = findElementById(taskNameId);
		taskName.click();
		String taskNameInputId = String.format("task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-input", index);
		WebElement taskNameInput = findElementById(taskNameInputId);
    waitForElementDisplayed(taskNameInput, true);
    taskNameInput.clear();
		taskNameInput.sendKeys(name);
    WebElement editor = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-inplace_content", index));
    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
    saveButton.click();
    waitForPageLoaded();
	}
	
	 public String getNameOfTaskAt(int index) {
	   String taskNameId = String.format("task-widget:task-list-scroller:%d:task-item:task-name-edit-form:task-name-inplace_display", index);
	   waitForElementDisplayed(By.id(taskNameId), true);
	   WebElement taskName = findElementById(taskNameId);
	   return taskName.getText();
	 }
	 
	  public void changeDescriptionOfTask(int index, String description) {
	    String taskDescriptionId = String.format("task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_display", index);
	    WebElement taskDescription = findElementById(taskDescriptionId);
	    taskDescription.click();
	    String taskDescriptionInputId = String.format("task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-description-input", index);
	    WebElement taskDescriptionInput = findElementById(taskDescriptionInputId);
	    waitForElementDisplayed(taskDescriptionInput, true);
	    taskDescriptionInput.clear();
	    taskDescriptionInput.sendKeys(description);
	    WebElement editor = findElementById(String.format("task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_editor", index));
	    WebElement saveButton = findChildElementByClassName(editor, "ui-inplace-save");
	    saveButton.click();
	    waitForPageLoaded();
	  }
	  
	   public String getDescriptionOfTaskAt(int index) {
	     String taskNameId = String.format("task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-desription-inplace_display", index);
	     waitForElementDisplayed(By.id(taskNameId), true);
	     WebElement taskName = findElementById(taskNameId);
	     return taskName.getText();
	   }
	   
	   public boolean isTaskDescriptionChangeComponentPresented(int index) {
	     return isElementPresent(By.id(String.format("task-widget:task-list-scroller:%d:task-item:description:task-description-form:task-description-input", index)));
	   }
}	
