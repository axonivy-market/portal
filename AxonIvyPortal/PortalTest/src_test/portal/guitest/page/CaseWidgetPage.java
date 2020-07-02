package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.CaseState;
import portal.guitest.common.Sleeper;

public class CaseWidgetPage extends TemplatePage {

	private String caseWidgetId;
	private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
	private static final String CASE_NAME_CSS_SELECTOR = "span[class*='case-header-name-cell']";
	private static final String CASE_PAGE_LOCATION = "//*[contains(@id,'case-view')]";
	private static final String COLUMNS_BUTTON_CSS_SELECTOR = "[id$='case-config-button']";
	private static final String SELECT_ITEM_XPATH =
			"//*[@id=\"case-widget:case-columns-configuration:select-columns-form:columns-checkbox\"]/tbody/tr[%s]/td/div/div[2]";
	private static final String APPLY_BUTTON_CSS_SELECTOR = "button[id$='select-columns-form:update-command']";
	private static final String DEFAULT_COLUMNS_XPATH =
			"//*[@id=\"case-widget:case-columns-configuration:select-columns-form:default-columns\"]/div[2]";

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

	public int countSideStepItems() {
		WebElement actionsPanel = getMoreActionsPanel();
		return actionsPanel.findElements(By.cssSelector("a[id$='side-step-item']")).size();
	}

	public WebElement selectCaseItem(int index) {
		String caseItemId = String.format(caseWidgetId + ":case-list-scroller:%s:case-item", index);
		return findElementById(caseItemId);
	}

	private WebElement getDestroyButtonOfCaseItem() {
		clickByCssSelector("[id$='action-steps-menu']");
		waitForElementDisplayed(By.cssSelector("a[id$='destroy-case']"), true);
		Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
				.until(() -> findElementByCssSelector("a[id$='destroy-case']").isDisplayed());
		return findElementByCssSelector("a[id$='destroy-case']");
	}

	private WebElement getMoreActionsPanel() {
		clickByCssSelector("[id$='action-steps-menu']");
		waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
		Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
				.until(() -> findElementByCssSelector("div[id$='action-steps-panel']").isDisplayed());
		return findElementByCssSelector("div[id$='action-steps-panel']");
	}

	public void clickDestroyButton() {
		WebElement destroyButton = getDestroyButtonOfCaseItem();
		destroyButton.click();
	}

	public boolean isDestroyButtonVisible() {
		try {
			getDestroyButtonOfCaseItem();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void confimDestruction() {
		String destroyCaseDialogId = caseWidgetId + ":destroy-case-confirmation-dialog";
		waitForElementDisplayed(By.id(destroyCaseDialogId), true);
		WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
		WebElement confirmButton = findChildElementById(destroyConfirmationDialog, caseWidgetId + ":confirm-destruction");
		confirmButton.click();
	}

	public CaseDetailsPage openDetailsOfCaseHasName(String caseName) {
		List<WebElement> caseItems = findListElementsByCssSelector(CASE_ITEM_LIST_SELECTOR);
		for (WebElement caseItem : caseItems) {
			if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
				caseItem.findElement(By.cssSelector("span[id*='case-info-row']")).click();
				return new CaseDetailsPage();
			}
		}
		throw new NoSuchElementException("Cannot find case has name " + caseName);
	}

	public CaseDetailsPage openCaseDetailsFromActionMenuByCaseName(String caseName) {
		List<WebElement> caseItems = findListElementsByCssSelector(CASE_ITEM_LIST_SELECTOR);
		for (WebElement caseItem : caseItems) {
			if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
				caseItem.findElement(By.cssSelector("[id*='action-steps-menu']")).click();
				waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
				findElementByCssSelector("a[id$='case-item-open-detail-link']").click();
				return new CaseDetailsPage();
			}
		}
		throw new NoSuchElementException("Cannot find case has name " + caseName);
	}

	public int getNumberOfCases() {
		List<WebElement> caseItems = findListElementsByCssSelector(CASE_ITEM_LIST_SELECTOR);
		return caseItems.size();
	}

	public String getCaseNameAt(int index) {
		WebElement name = findElementById(
				caseWidgetId + ":case-list-scroller:" + index + ":case-item:case-name-component:case-header-name-cell");
		return name.getText();
	}

	public String getCaseName() {
		waitForElementDisplayed(By.cssSelector("*[id$='case-list']"), true);
		WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
		WebElement selectedCaseNameElement = findElementById(
				selectedCaseElement.getAttribute("id") + ":case-name-component:case-name-form:case-name-edit-inplace");
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

	public boolean isCaseListColumnExist(String columnHeaderText) {
		WebElement taskListHeader = findElementById(caseWidgetId + ":widget-column-header");
		for (WebElement column : taskListHeader.findElements(By.tagName("a"))) {
			if (columnHeaderText.equals(column.getText())) {
				return true;
			}
		}
		return false;
	}

	public void openAdvancedFilter(String filterName, String filterIdName) {
		click(By.id(caseWidgetId + ":filter-add-action"));
		WebElement filterSelectionElement = findElementById(caseWidgetId + ":filter-add-form:filter-selection");

		List<WebElement> elements = findChildElementsByTagName(filterSelectionElement, "LABEL");
		for (WebElement element : elements) {
			if (element.getText().equals(filterName)) {
				element.click();
				click(findElementById("case-widget:filter-add-form:update-filter-selected-command"));
				waitAjaxIndicatorDisappear();
				break;
			}
		}
		waitForElementDisplayed(
				By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"),
				true);
	}

	public void filterByDescription(String text) {
		click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
		WebElement descriptionInput =
				findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
		enterKeys(descriptionInput, text);
		click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
		Sleeper.sleep(2000);
	}

	public void saveFilter(String filterName) {
		click(By.id(caseWidgetId + ":filter-save-action"));
		waitAjaxIndicatorDisappear();
		Sleeper.sleep(2000);
		WebElement filterNameInput = findElementById(caseWidgetId + ":filter-save-form:save-filter-set-name-input");
		enterKeys(filterNameInput, filterName);
		click(findElementById(caseWidgetId + ":filter-save-form:filter-save-command"));
		waitAjaxIndicatorDisappear();
		Sleeper.sleep(2000);
	}

	public String getFilterName() {
		WebElement filterName = findElementByCssSelector("a[id$='case-widget:filter-selection-form:filter-name'] > span:nth-child(2) ");
		return filterName.getText();
	}

	public String getFilterValue(String filterId) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
        () -> findElementByCssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']")
            .getText().length()>1);
	  waitForElementDisplayed(By.cssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']"), true);
		WebElement filterElement =
				findElementByCssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']");
		return filterElement.getText();
	}

	public boolean isEmpty() {
		return isElementDisplayed(By.id("search-results-tabview:case-results:case-empty-message"));
	}

	public void sortCaseListByColumn(String columnId) {
		WebElement columnHeader = findElementById(columnId);
		columnHeader.click();
		waitAjaxIndicatorDisappear();
	}

	public String getCaseListFirstCustomCellValue() {
		return findElementByCssSelector(
				"div[id$=':0\\:case-item\\:case-item-container'] span.customized-case-header-column").getText();
	}

	public Integer getCaseCount() {
		WebElement caseTitleElement = findElementById("case-widget:case-widget-title");
		String title = caseTitleElement.getText();
		String count = StringUtils.substringBetween(title, "(", ")");
		return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
	}

	public void clickColumnsButton() {
		clickByCssSelector(COLUMNS_BUTTON_CSS_SELECTOR);
	}

	public void clickColumnCheckbox(int columnIndex) {
		WebElement columnCheckbox = findElementByXpath(String.format(SELECT_ITEM_XPATH, columnIndex));
		columnCheckbox.click();
	}

	public void clickDefaultCheckbox() {
		WebElement columnCheckbox = findElementByXpath(DEFAULT_COLUMNS_XPATH);
		columnCheckbox.click();
		waitAjaxIndicatorDisappear();
	}

	public void clickApplyButton() {
		clickByCssSelector(APPLY_BUTTON_CSS_SELECTOR);
		waitAjaxIndicatorDisappear();
	}

	public boolean isAllCategoriesSelected() {
		return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-selected"));
	}

	public boolean isAllCategoriesUnselected() {
		return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-hasselected"));
	}

	public void openCategoryFilter() {
		click(By.cssSelector("button[id$='case-category-filter:filter-open-form:advanced-filter-command']"));
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
		click(By.cssSelector("button[id$='case-category-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}

	public void filterByCreator(String text) {
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		WebElement responsible = findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']");
		type(responsible, text);
		waitAjaxIndicatorDisappear();
		waitForElementDisplayedByCssSelector("i[class*='fa-user']", 5);
		click(By.cssSelector("i[class*='fa-user']"));
		waitAjaxIndicatorDisappear();
		click(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
		Sleeper.sleep(2000);
	}

  public void openSavedFilters(String filterName) {
    refreshAndWaitElement("a[id$='case-widget:filter-selection-form:filter-name']");
    click(findElementById("case-widget:filter-selection-form:filter-name"));
    List<WebElement> saveFilters = findListElementsByCssSelector("a[id$='user-defined-filter']");
    for (WebElement filter : saveFilters) {
      if (filter.getText().equals(filterName)) {
        click(filter);
        waitAjaxIndicatorDisappear();
        refreshAndWaitElement("a[id$='case-widget:filter-selection-form:filter-name'] > span:nth-child(2)");
        Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
            () -> findElementByCssSelector("a[id$='case-widget:filter-selection-form:filter-name'] > span:nth-child(2)")
                .getText().contains(filterName));
        return;
      }
    }
  }

	public void removeResponsibleFilter() {
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		waitForElementDisplayed(By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"), true);
		findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").clear();
		click(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
		// Sleeper.sleep(2000);
	}

	public String getCreator() {
		refreshAndWaitElement("button[id$='creator-filter:filter-open-form:advanced-filter-command']");
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		waitForElementDisplayed(By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"),true);
		return findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']")
				.getAttribute("value");
	}

  public CaseState getCaseState(int caseIndex) {
    List<WebElement> caseStateCells = findListElementsByCssSelector("span[id$=':case-state-cell']");
    String stateClass = caseStateCells.get(caseIndex).findElement(By.className("fa")).getAttribute("class");
    return CaseState.fromClass(stateClass.substring(stateClass.indexOf("case-state-")));
  }
}
