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
		waitAjaxIndicatorDisappear();
	}

	public void createTask(int taskIndex, int typeIndex, String taskName, String taskDescription,
			List<ExpressResponsible> responsibles) {
		chooseTaskType(taskIndex, typeIndex);
		Sleeper.sleep(1000);
		waitAjaxIndicatorDisappear();
		if (typeIndex != 2) { // 2 is INFORMATION_EMAIL_INDEX
			WaitHelper.typeWithRetry(this, String.format("input[id$='%d:task-name']", taskIndex), taskName);
			WaitHelper.typeWithRetry(this, String.format("input[id$='%d:task-description']", taskIndex), taskDescription);
			click(By.id(String.format("form:defined-tasks-list:%d:task-responsible-link", taskIndex)));
			// waitAjaxIndicatorDisappear();
			ensureNoBackgroundRequest();
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
		waitAjaxIndicatorDisappear();
		waitForElementDisplayed(By.id("choose-responsible-dialog"), true);
		for (ExpressResponsible responsible : responsibles) {
			chooseResponsible(responsible.getResponsibleName(), responsible.isGroup());
		}
		click(By.id("assignee-selection-form:save-assignee-button"));
	}

	public ExpressFormDefinitionPage goToFormDefinition() {
		click(By.id("form:save"));
		return new ExpressFormDefinitionPage();
	}

  public HomePage cancel() {
    click(By.cssSelector("button[id$='cancel-workflow-button']"));
    return new HomePage();
  }
	
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
			waitAjaxIndicatorDisappear();
			waitForElementDisplayed(By.id("assignee-selection-form:role-selection-component:role-selection_input"), true);
			type(By.id("assignee-selection-form:role-selection-component:role-selection_input"), responsible);
			waitForElementDisplayed(By.id("assignee-selection-form:role-selection-component:role-selection_panel"), true);
			click(By.xpath("//*[@id='assignee-selection-form:role-selection-component:role-selection_panel']/ul/li/span"));
		} else {
			type(By.id("assignee-selection-form:user-selection-component:user-selection_input"), responsible);
			waitForElementDisplayed(By.id("assignee-selection-form:user-selection-component:user-selection_panel"), true);
			click(By.xpath("//*[@id='assignee-selection-form:user-selection-component:user-selection_panel']/table/tbody/tr"));
		}
		waitAjaxIndicatorDisappear();
		click(By.id("assignee-selection-form:add-assignee-button"));
	}

  private void chooseTaskType(int taskIndex, int typeIndex) {
    click(By.id(String.format("form:defined-tasks-list:%d:task-type_label", taskIndex)));
    waitForElementDisplayed(By.id(String.format("form:defined-tasks-list:%d:task-type_panel", taskIndex)), true);
    String taskType =
        findElementByCssSelector(String.format("li[id$=':%d:task-type_%d']", taskIndex, typeIndex)).getText();
    click(By.xpath(String.format("//*[@id='form:defined-tasks-list:%d:task-type_%d']", taskIndex, typeIndex)));
    WaitHelper.assertTrueWithWait(() -> taskType.equals(
        findElementByCssSelector(String.format("label[id$='%d:task-type_label']", taskIndex, taskIndex)).getText()));
  }

	private void agreeToDeleteAllDefineTasks() {
		waitForElementDisplayed(By.id("delete-all-defined-tasks-warning"), true);
		click(By.id("delete-all-defined-tasks-warning-ok"));
		waitAjaxIndicatorDisappear();
		Sleeper.sleep(2000);
	}

	public String getProcessName() {
		return findElementById("form:process-name").getAttribute("value");
	}
}
