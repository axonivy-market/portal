package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.Sleeper;
import portal.guitest.common.WaitHelper;

public class ExpressProcessPage extends TemplatePage {

	@Override
	protected String getLoadedLocator() {
		return "id('form:process-setting-fieldset')";
	}

	public void fillProcessProperties(boolean isAdhocWF, boolean isCreateOwn, String processName,
			String processDescription) {
		if (isAdhocWF) {
		  click(By.cssSelector("div[id='form:process-type']"));
		  WaitHelper.assertTrueWithWait(() -> "One time".equals(findElementByCssSelector("label.switch-active").getText()));
		}
		
    if (!isCreateOwn) {
      click(By.cssSelector("div[id='form:user-interface-type']"));
      agreeToDeleteAllDefineTasks();
    }
		type(By.id("form:process-name"), processName);
		type(By.id("form:process-description"), processDescription);
	}

  private void selectCheckbox(String forAttribute) {
		WebElement checkboxLabel = findElementByXpath(String.format("//label[@for='%s']", forAttribute));
		click(checkboxLabel);
	}

  public void createTask(int taskIndex, int typeIndex, String taskName, String taskDescription,
			List<ExpressResponsible> responsibles) {
    final String TASK_NAME_FORMAT = "input[id$='%d:task-name']";
    final int INFORMATION_EMAIL_INDEX = 2;

		chooseTaskType(taskIndex, typeIndex);
		Sleeper.sleep(1000);
		if (typeIndex != INFORMATION_EMAIL_INDEX) {
			WaitHelper.typeWithRetry(this, String.format(TASK_NAME_FORMAT, taskIndex), taskName);
			WaitHelper.typeWithRetry(this, String.format("input[id$='%d:task-description']", taskIndex), taskDescription);
			click(By.id(String.format("form:defined-tasks-list:%d:task-responsible-link", taskIndex)));
			waitForElementDisplayed(By.id("choose-responsible-dialog"), true);
			for (ExpressResponsible responsible : responsibles) {
				chooseResponsible(responsible.getResponsibleName(), responsible.isGroup());
			}
			click(By.id("assignee-selection-form:save-assignee-button"));
		}
	}

	public void createDefaultTask(int taskIndex, String taskName, List<ExpressResponsible> responsibles) {
		if (taskName != null) {
			type(By.id(String.format("form:defined-tasks-list:%d:default-task-name", taskIndex)), taskName);
		}
		click(By.id(String.format("form:defined-tasks-list:%d:default-task-responsible-link", taskIndex)));
		addResponsible(responsibles);
	}

  private void addResponsible(List<ExpressResponsible> responsibles) {
		waitForElementDisplayed(By.id("choose-responsible-dialog"), true);
		for (ExpressResponsible responsible : responsibles) {
			chooseResponsible(responsible.getResponsibleName(), responsible.isGroup());
		}
		click(By.id("assignee-selection-form:save-assignee-button"));
		waitUntilAnimationFinished(DEFAULT_TIMEOUT, "choose-responsible-dialog", ID_PROPERTY);
	}

	public ExpressFormDefinitionPage goToFormDefinition() {
		click(By.id("form:save"));
		return new ExpressFormDefinitionPage();
	}

	@SuppressWarnings("deprecation")
  public void addNewTask(int currentTaskIndex) {
		click(By.id(String.format("form:defined-tasks-list:%d:add-step-button", currentTaskIndex)));
		ensureNoBackgroundRequest();
	}

	public void executeDirectly() {
		click(By.id("form:save"));
	}

	public void fillProcessOwners(List<ExpressResponsible> responsibles) {
		click(By.id("form:process-owner-link"));
		addResponsible(responsibles);
	}

  private void chooseResponsible(String responsible, boolean isGroup) {
    if (isGroup) {
      selectCheckbox("assignee-selection-form:assignee-type:1");
      WaitHelper.assertTrueWithWait(() -> {
        return findElementByCssSelector("[id$=':role-selection-autocomplete-panel']").isDisplayed();
      });
      waitForElementDisplayed(By.id("assignee-selection-form:role-selection-component:role-selection_input"), true);
      type(By.id("assignee-selection-form:role-selection-component:role-selection_input"), responsible);
      waitForElementDisplayed(By.id("assignee-selection-form:role-selection-component:role-selection_panel"), true);
      click(
          By.cssSelector("span[id='assignee-selection-form:role-selection-component:role-selection_panel'] .gravatar"));
    } else {
      type(By.id("assignee-selection-form:user-selection-component:user-selection_input"), responsible);
      waitForElementDisplayed(By.id("assignee-selection-form:user-selection-component:user-selection_panel"), true);
      click(By.xpath("//*[@id='assignee-selection-form:user-selection-component:user-selection_panel']/table/tbody/tr"));
    }
    WaitHelper.assertTrueWithWait(() -> isElementEnabled(By.id("assignee-selection-form:add-assignee-button")));
    click(By.id("assignee-selection-form:add-assignee-button"));
    waitForElementDisplayed(By.className("assignee-name-col"), true);
    var responsiblesSelection = findElementByCssSelector("[id$='assignee-selection-form:select-assignee']");
    var inputText = responsiblesSelection.findElement(By.cssSelector(".ui-autocomplete-input.ui-autocomplete-dd-input.ui-inputfield"));
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, inputText.getAttribute(CLASS_PROPERTY).replace(" ", "."), CLASS_PROPERTY);
  }

  private void chooseTaskType(int taskIndex, int typeIndex) {
    if (typeIndex == 0) {
      // If the selected task type is already task type? ignore click on the drop-down
      return;
    }
    
    final String TASK_TYPE_FORMAT = "li[id$=':%d:task-type_%d']";
    final String TASK_TYPE_LABEL_FORMAT = "label[id$=':%d:task-type_label']";

    clickByCssSelector(String.format(TASK_TYPE_LABEL_FORMAT, taskIndex));
    waitForElementDisplayed(By.cssSelector(String.format("[id$=':%d:task-type_panel']", taskIndex)), true);
    String taskType = findElementByCssSelector(String.format(TASK_TYPE_FORMAT, taskIndex, typeIndex)).getText();
    clickByCssSelector(String.format(TASK_TYPE_FORMAT, taskIndex, typeIndex));
    WaitHelper.assertTrueWithWait(() -> taskType.equals(findElementByCssSelector(String.format(TASK_TYPE_LABEL_FORMAT, taskIndex, taskIndex)).getText()));
  }

	@SuppressWarnings("deprecation")
  private void agreeToDeleteAllDefineTasks() {
		waitForElementDisplayed(By.id("delete-all-defined-tasks-warning"), true);
		click(By.id("delete-all-defined-tasks-warning-ok"));
		waitAjaxIndicatorDisappear();
		Sleeper.sleep(2000);
	}

	public String getProcessName() {
		return findElementById("form:process-name").getAttribute("value");
	}
	
	public String getProcessOwnerNames() {
	  return findElementById("form:process-owner-link").getText();
	}
	
	public String getAbleToStartNames() {
    return getResponsiblesOfTask(0);
  }
	
	public String getResponsiblesOfTask(int taskIndex) {
	  return findElementById(String.format("form:defined-tasks-list:%d:task-responsible-link", taskIndex)).getText();
	}
	
	public WebElement getDefineTaskStep(int stepIndex) {
	  String defineTaskStepId = String.format(":defined-tasks-list:%s:process-flow-field", stepIndex);
	  return findElementByCssSelector("[id$='"+ defineTaskStepId + "']");
	}
	
	public void waitUntilExpressProcessDisplay() {
	  waitForElementDisplayed(By.id("form:process-setting-fieldset"), true);
	}

  public void waitForChooseResponsibleDialogHidden() {
    waitForElementDisplayed(findElementById("choose-responsible-dialog"), false);
  }
  
  public HomePage cancelWorkflowDefinition() {
    click(By.id("form:cancel-workflow-button"));
    return new HomePage();
  }
}
