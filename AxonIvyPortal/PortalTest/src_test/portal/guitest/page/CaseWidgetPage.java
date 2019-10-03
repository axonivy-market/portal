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
	private static final String COLUMNS_BUTTON_CSS_SELECTOR = "button[id$='case-config-button']";
	private static final String SELECT_ITEM_XPATH = "//*[@id=\"case-widget:case-columns-configuration:select-columns-form:columns-checkbox\"]/tbody/tr[%s]/td/div/div[2]";
	private static final String APPLY_BUTTON_CSS_SELECTOR = "button[id$='select-columns-form:update-command']";
	private static final String CANCEL_BUTTON_CSS_SELECTOR = "button[id$='cancel-command']";
	private static final String DEFAULT_COLUMNS_XPATH = "//*[@id=\"case-widget:case-columns-configuration:select-columns-form:default-columns\"]/div[2]";
	private static final String ADD_NOTE_BUTTON_ID = "case-widget:case-list-scroller:%d:case-item:case-body:history:add-note-command";
	private static final String SHOW_MORE_NOTE_LINK_ID = "case-widget:case-list-scroller:%d:case-item:case-body:history:show-more-note-link";
	private static final String SHOW_DETAILS_LINK_ID = "case-widget:case-list-scroller:%d:case-item:case-body:general-information:show-additional-case-details-link";
	private static final String SHOW_ALL_TASKS_ID ="case-widget:case-list-scroller:%d:case-item:case-body:related-tasks:show-all-tasks";
	private static final String ADD_DOCUMENT_LINK_ID = "case-widget:case-list-scroller:%d:case-item:case-body:document:add-document-command";
	
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
	
	public void openSideStepPopup(int index) {
	  click(By.id("case-widget:case-list-scroller:" + index + ":case-item:side-step-component:case-side-steps-menu"));
	  waitForElementDisplayed(By.id("case-widget:case-list-scroller:" + index + ":case-item:side-step-component:side-steps-panel"), true);
	}
	
	public int countSideStepItems(int index) {
	  WebElement sideStepPanel = findElementById("case-widget:case-list-scroller:" + index + ":case-item:side-step-component:side-steps-panel");
    return sideStepPanel.findElements(By.tagName("a")).size();
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
  
  public void clickColumnsButton() {
    WebElement columnsButton = findElementByCssSelector(COLUMNS_BUTTON_CSS_SELECTOR);
    columnsButton.click();
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
    WebElement applyButton = findDisplayedElementBySelector(APPLY_BUTTON_CSS_SELECTOR);
    applyButton.click();
    waitAjaxIndicatorDisappear();
  }
  
  public void clickCancelButton() {
    WebElement applyButton = findElementByCssSelector(CANCEL_BUTTON_CSS_SELECTOR);
    applyButton.click();
  }
  
  public boolean isAddNoteButtonDisplayed() {
    return isElementDisplayedById(String.format(ADD_NOTE_BUTTON_ID, 0));
  }
  
  public boolean isShowMoreNoteButtonDisplayed() {
    return isElementDisplayedById(String.format(SHOW_MORE_NOTE_LINK_ID, 0));
  }
  
  public boolean isShowDetailsDisplayed() {
    return isElementDisplayedById(String.format(SHOW_DETAILS_LINK_ID, 0));
  }
  
  public boolean isShowAllTasksDisplayed() {
    return isElementDisplayedById(String.format(SHOW_ALL_TASKS_ID, 0));
  }
  
  public boolean isAddDocumentLinkDisplayed() {
    return isElementDisplayedById(String.format(ADD_DOCUMENT_LINK_ID, 0));
  }
}
