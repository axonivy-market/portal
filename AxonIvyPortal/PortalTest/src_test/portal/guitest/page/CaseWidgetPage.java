package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class CaseWidgetPage extends TemplatePage {

	private String caseWidgetId;
	private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
	private static final String CASE_NAME_CSS_SELECTOR = "span[class*='case-header-name-cell']";
	private static final String CASE_PAGE_LOCATION = "//*[contains(@id,'case-view')]";

	public CaseWidgetPage() {
		this("case-widget");
	}

	public CaseWidgetPage(String caseWidgetId) {   
		this.caseWidgetId = caseWidgetId;
	}

	@Override
	protected String getLoadedLocator() {
		return CASE_PAGE_LOCATION;
	}

	public WebElement selectCaseItem(int index) {
		String caseItemId = String.format(caseWidgetId + ":case-list-scroller:%s:case-item", index);
		return findElementById(caseItemId);
	}

	public boolean isCaseItemSelected(int index) {
		return findElementById(caseWidgetId + ":case-list-scroller:" + index + ":case-item").getAttribute("class")
				.contains("case-list-item-expanded");
	}

	private WebElement getDestroyButtonOfCaseItem(WebElement caseItem) {
		String caseItemId = caseItem.getAttribute("id");
		String destroyButtonId = String.format("%s:destroy-case", caseItemId);
		WebElement destroyButton = findElementById(destroyButtonId);
		findElementById(destroyButtonId);
		return destroyButton;
	}

	public void clickDestroyButton(WebElement caseItem) {
		WebElement destroyButton = getDestroyButtonOfCaseItem(caseItem);
		destroyButton.click();
	}

	public boolean isDestroyButtonVisible(WebElement caseItem) {
		try {
			getDestroyButtonOfCaseItem(caseItem);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void confimDestruction() {
		String destroyCaseDialogId = caseWidgetId + ":destroy-case-confirmation-dialog";
		waitForElementDisplayed(By.id(destroyCaseDialogId), true);
		WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
		WebElement confirmButton =
				findChildElementById(destroyConfirmationDialog, caseWidgetId + ":confirm-destruction");
		confirmButton.click();
	}

	public CaseDetailsPage openDetailsOfCaseHasName(String caseName) {
		List<WebElement> caseItems = findListElementsByCssSelector(CASE_ITEM_LIST_SELECTOR);
		for (WebElement caseItem : caseItems) {
			if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
			  caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).click();
				waitAjaxIndicatorDisappear();
				CaseDetailsPage detailsPage = new CaseDetailsPage(caseItem);
				return detailsPage;
			}
		}
		throw new NoSuchElementException("Cannot find details of case that has name " + caseName);
	}

	public int getNumberOfCases() {
		List<WebElement> caseItems = findListElementsByCssSelector(CASE_ITEM_LIST_SELECTOR);
		return caseItems.size();
	}

	public String getCaseNameAt(int index) {
		WebElement name =
				findElementById(caseWidgetId + ":case-list-scroller:" + index
						+ ":case-item:case-name-component:case-header-name-cell");
		return name.getText();
	}

	public String getCaseName() {
		waitForElementDisplayed(By.cssSelector("*[id$='case-list']"), true);
		WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
		WebElement selectedCaseNameElement =
				findElementById(selectedCaseElement.getAttribute("id")
						+ ":case-name-component:case-name-form:case-name-edit-inplace");
		return selectedCaseNameElement.getText();
	}

	public String getCaseId() {
		waitForElementDisplayed(By.cssSelector("*[id$='case-list']"), true);
		WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
		WebElement selectedCaseIdElement = selectedCaseElement.findElement(By.cssSelector(".case-header-id-cell"));
		return selectedCaseIdElement.getText();
	}

	public boolean isCaseDisplayed(String name) {
		List<WebElement> caseNameElements = findListElementsByClassName("case-header-name-cell");
		return caseNameElements.stream().anyMatch(caseNameElement -> name.equals(caseNameElement.getText()));
	}


	public void openAdvancedFilter(String filterName, String filterIdName) {
		click(By.id(caseWidgetId + ":filter-add-action"));
		WebElement filterSelectionElement = findElementById(caseWidgetId + ":filter-add-form:filter-selection");
		findChildElementsByTagName(filterSelectionElement, "LABEL").forEach(filterElement -> {
			if (filterName.equals(filterElement.getText())) {
				filterElement.click();
				return;
			}
		});
		waitForElementDisplayed(
				By.cssSelector("span[id$='" + filterIdName
						+ "-filter:filter-open-form:advanced-filter-item-container']"), true);
	}

	public void filterByDescription(String text) {
		click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
		WebElement descriptionInput =
				findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
		enterKeys(descriptionInput, text);
		click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
		Sleeper.sleepTight(2000);
	}

	public void saveFilter(String filterName) {
		click(By.id(caseWidgetId + ":filter-save-action"));
		Sleeper.sleepTight(2000);
		WebElement filterNameInput = findElementById(caseWidgetId + ":filter-save-form:save-filter-set-name-input");
		enterKeys(filterNameInput, filterName);
		click(findElementById(caseWidgetId + ":filter-save-form:filter-save-command"));
		Sleeper.sleepTight(2000);
	}

	public Object getFilterName() {
		click(findElementById(caseWidgetId + ":filter-selection-form:filter-name"));
		WebElement descriptionInput = findElementByCssSelector(".user-definied-filter-container");

		return descriptionInput.getText();
	}

	public boolean isFilterSelectionVisible() {
		return isElementPresent(By.id(caseWidgetId + ":filter-selection-form:filter-selection-panel"));
	}

	public int countCategoryRoots() {
		List<WebElement> taskElements = findListElementsByCssSelector("span[class*='js-second-level-menu']");
		return taskElements.size();
	}

	public void toggleCategoryMenu() {
		click(findElementByClassName("second-level-menu-header"));
	}

	public boolean isEmpty() {
		return isElementDisplayed(By.id("search-results-tabview:case-results:case-empty-message"));
	}

	public void sortCaseListByColumn(String columnId) {
		WebElement columnHeader = findElementById(columnId);
		columnHeader.click();
		waitAjaxIndicatorDisappear();
	}

	public String getCaseListCustomCellValue(String columnId) {
		WebElement columnHeader = findElementById(columnId);
		return columnHeader.getText();
	}
	
  public int getCaseCount() {
    WebElement caseTitleElement = findElementById("case-widget:case-widget-title");
    String title = caseTitleElement.getText();
    return Integer.parseInt(title.substring(title.lastIndexOf("(") + 1, title.length() -1));
  }
}
