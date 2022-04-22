package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends TemplatePage {

	private final String INFO_EXPRESS_WORKFlOW = "span[id$='info-workflow']";
	private final String EDIT_EXPRESS_WORKFlOW = "a[id$='edit-express-workflow']";
	private final String DELETE_EXPRESS_WORKFlOW = "a[id$='delete-express-workflow']";
	private final String EXPRESS_PROCESS_LOGO = "span[id$='express-process-logo']";

	@Override
	protected String getLoadedLocator() {
		return "id('search-results-tabview')";
	}

  @SuppressWarnings("deprecation")
	public void openTaskTab() {
		click(By.cssSelector("li[class*='task-tab-title']"));
		waitAjaxIndicatorDisappear();
	}

  @SuppressWarnings("deprecation")
	public void openCaseTab() {
		click(By.cssSelector("li[class*='case-tab-title']"));
		waitAjaxIndicatorDisappear();
	}

  @SuppressWarnings("deprecation")
	public void openEmployeeTab() {
		click(By.xpath("//div[@id='search-results-tabview']/ul/li[4]"));
		waitAjaxIndicatorDisappear();
	}

  public void startProcess(String name) {
    ProcessWidgetPage processWidgetPage = getProcessWidget();
    if (processWidgetPage.isImageModeActivated()) {
      WebElement processListElement = findElementById("search-results-tabview:process-results");
      WebElement processItemElement = processWidgetPage.getStartImageProcess(name, processListElement);
      processItemElement.click();
      return;
    }
    processWidgetPage.startProcess(name);
  }

	public String getProcessResult(String name) {
		ProcessWidgetPage processWidgetPage = getProcessWidget();
		if (processWidgetPage.isImageModeActivated()) {
		  WebElement processItem = processWidgetPage.getProcessItem(name);
		  WebElement processNameElement = findChildElementByCssSelector(processItem, ".js-process-start-list-item-name");
		  return processNameElement.getText();
		}
		return processWidgetPage.getProcess(name).getText();
	}

	public String getTaskResult(int index) {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage("search-results-tabview:task-results") {
      @Override
      protected String getLoadedLocator() {
        return "//*[contains(@id,'task-results:task-view')]";
      }
    };
		return taskWidgetPage.getNameOfTaskAt(index);
	}

	public String getCaseResult(int index) {
		CaseWidgetPage caseWidgetPage = getCaseWidget();
		return caseWidgetPage.getCaseNameAt(index);
	}

	public int countCase() {
		CaseWidgetPage caseWidgetPage = getCaseWidget();
		return caseWidgetPage.getNumberOfCases();
	}

  private CaseWidgetPage getCaseWidget() {
    return new CaseWidgetPage("search-results-tabview:case-results");
  }

	public boolean isCaseResultEmpty() {
		CaseWidgetPage caseWidgetPage = getCaseWidget();
		return caseWidgetPage.isEmpty();
	}

	public int countNumberOfEmployee() {
	  	waitForElementDisplayed(By.id("search-results-tabview:employee-table"), true);
		WebElement employeeSearchResult = findElementById("search-results-tabview:employee-table");
		return employeeSearchResult.findElements(By.cssSelector("tr.ui-widget-content")).size();
	}

	public boolean isProcessGroupDisplay(String group) {
		ProcessWidgetPage processWidgetPage = getProcessWidget();
		return processWidgetPage.isProcessGroupDisplay(group);
	}

  private ProcessWidgetPage getProcessWidget() {
    return new ProcessWidgetPage("search-results-tabview:process-results");
  }

  public boolean isInfoWorkflowIcon() {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      WebElement expressWorkflow = findElementByClassName("express-workflow");
      WebElement icon = findChildElementByClassName(expressWorkflow, "process-image-view-icon");
      return icon.getAttribute("class").contains("si si si-startup-launch");
    }

    WebElement element = findElementByCssSelector(INFO_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-information-circle");
  }

  public boolean isEditExpressWorkflow(String processName) {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      WebElement actionMenu = getImageProcessActionMenuPanel(processName);
      WebElement icon = findChildElementByCssSelector(actionMenu, "a[id$=':image-process-action-component:edit-process']");
      return icon != null;
    }
    WebElement element = findElementByCssSelector(EDIT_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-graphic-tablet-drawing-pen");
  }

  private WebElement getImageProcessActionMenuPanel(String processName) {
    var selectedProcess = findListElementsByCssSelector("span.process-image-view-name").stream()
        .filter(process -> processName.equalsIgnoreCase(process.getText()))
        .findFirst().orElse(null);
    var processActionMenuId = selectedProcess.getAttribute(ID_PROPERTY).replace("process-item-name", "");
    processActionMenuId = processActionMenuId.concat("process-item:image-process-action-component:process-action-menu");
    return findElementByCssSelector(String.format("div[id$='%s']", processActionMenuId));
  }

  public boolean isDeleteExpressWorkflown(String processName) {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      WebElement actionMenu = getImageProcessActionMenuPanel(processName);
      WebElement icon = findChildElementByCssSelector(actionMenu, "a[id$=':image-process-action-component:delete-process']");
      return icon != null;
    }
    WebElement element = findElementByCssSelector(DELETE_EXPRESS_WORKFlOW);
    return element.getAttribute("class").contains("si-bin-1");
  }

  public boolean isExpressProcessLogo() {
    ProcessWidgetPage processWidget = getProcessWidget();
    if (processWidget.isImageModeActivated()) {
      return isInfoWorkflowIcon();
    }
    WebElement element = findElementByCssSelector(EXPRESS_PROCESS_LOGO);
    return element.getAttribute("class").contains("si-startup-launch");
  }

	public boolean isTaskCategoryColumnDisplayed() {
	  return findElementByCssSelector("span[id$=':task-category-cell']").isDisplayed();
  }
	
	public boolean isCaseCategoryColumnDisplayed() {
    return findElementByCssSelector("span[id$=':case-category-cell']").isDisplayed();
  }
}
