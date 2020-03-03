package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends TemplatePage {

	private final String INFO_EXPRESS_WORKFlOW = "span[id$='info-workflow']";
	private final String EDIT_EXPRESS_WORKFlOW = "a[id$='edit-express-workflow']";
	private final String DELETE_EXPRESS_WORKFlOW = "a[id$='delete-express-workflow']";
	private final String EXPRESS_PROCESS_LOGO = "div[id$='express-process-logo']";

	@Override
	protected String getLoadedLocator() {
		return "id('search-results-tabview')";
	}

	public void openTaskTab() {
		click(By.cssSelector("li[class*='task-tab-title']"));
		waitAjaxIndicatorDisappear();
	}

	public void openCaseTab() {
		click(By.cssSelector("li[class*='case-tab-title']"));
		waitAjaxIndicatorDisappear();
	}

	public void openEmployeeTab() {
		click(By.xpath("//div[@id='search-results-tabview']/ul/li[4]"));
		waitAjaxIndicatorDisappear();
	}

	public void startProcess(String name) {
		ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
		processWidgetPage.waitAndStartProcess(name);
	}

	public String getProcessResult(String name) {
		ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
		return processWidgetPage.getProcess(name).getText();
	}

	public String getTaskResult(int index) {
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage("search-results-tabview:task-results");
		return taskWidgetPage.getNameOfTaskAt(index);
	}

	public String getCaseResult(int index) {
		CaseWidgetPage caseWidgetPage = new CaseWidgetPage("search-results-tabview:case-results");
		return caseWidgetPage.getCaseNameAt(index);
	}

	public int countCase() {
		CaseWidgetPage caseWidgetPage = new CaseWidgetPage("search-results-tabview:case-results");
		return caseWidgetPage.getNumberOfCases();
	}

	public boolean isCaseResultEmpty() {
		CaseWidgetPage caseWidgetPage = new CaseWidgetPage("search-results-tabview:case-results");
		return caseWidgetPage.isEmpty();
	}

	public int countNumberOfEmployee() {
	  	waitForElementDisplayed(By.id("search-results-tabview:employee-table"), true);
		WebElement employeeSearchResult = findElementById("search-results-tabview:employee-table");
		return employeeSearchResult.findElements(By.cssSelector("tr.ui-widget-content")).size();
	}

	public boolean isProcessGroupDisplay(String group) {
		ProcessWidgetPage processWidgetPage = new ProcessWidgetPage("search-results-tabview:process-results");
		return processWidgetPage.isProcessGroupDisplay(group);
	}

	public boolean isInfoWorkflowIcon() {
		WebElement element = findElementByCssSelector(INFO_EXPRESS_WORKFlOW);	
		return element.getAttribute("class").contains("fa-info-circle");
	}
	
	public boolean isEditExpressWorkflow() {
		WebElement element = findElementByCssSelector(EDIT_EXPRESS_WORKFlOW);
		return element.getAttribute("class").contains("fa-pencil");
	}
	
	public boolean isDeleteExpressWorkflown() {
		WebElement element = findElementByCssSelector(DELETE_EXPRESS_WORKFlOW);
		return element.getAttribute("class").contains("fa-trash");
	}
	
	public boolean isExpressProcessLogo() {
		WebElement element = findElementByCssSelector(EXPRESS_PROCESS_LOGO);
		return element.getAttribute("class").contains("express-process-logo");
	}
	
}
