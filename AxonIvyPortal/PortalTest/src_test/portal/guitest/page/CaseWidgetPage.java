package portal.guitest.page;

import static portal.guitest.common.WaitHelper.assertTrueWithWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.jayway.awaitility.core.ConditionTimeoutException;

import portal.guitest.common.CaseState;
import portal.guitest.common.WaitHelper;

public class CaseWidgetPage extends TemplatePage {

	private String caseWidgetId;
	private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
	private static final String CASE_NAME_CSS_SELECTOR = "span[class*='case-header-name-cell']";
	private static final String CASE_PAGE_LOCATION = "//*[contains(@id,'case-view')]";
	private static final String SELECT_COLUMNS_LINK_CSS_SELECTOR = "a[id$='case-config-button']";
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
		openActionStepMenu();
		waitForElementDisplayed(By.cssSelector("a[id$='destroy-case']"), true);
		Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
				.until(() -> findElementByCssSelector("a[id$='destroy-case']").isDisplayed());
		return findElementByCssSelector("a[id$='destroy-case']");
	}

  public void openActionStepMenu() {
    waitForElementDisplayed(By.cssSelector("[id$=':case-item:case-item-action-form']"), true);
    clickByCssSelector("a[id$='action-steps-menu']");
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

	private WebElement getMoreActionsPanel() {
		openActionStepMenu();
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
		  openActionStepMenu();
		  waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
	    return isElementDisplayed(By.cssSelector("a[id$='destroy-case'"));
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
				caseItem.findElement(By.cssSelector("a[id*='action-steps-menu']")).click();
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
	  waitForElementDisplayed(By.className("js-case-list"), true);
		WebElement name = findElementByCssSelector("[id$='case-list-scroller:" + index + ":case-item:case-name-component:case-header-name-cell']");
		return name.getText();
	}
	
	public String getCreatorAt(int index) {
    List<WebElement> creators = findListElementsByCssSelector(".case-header-creator-cell .name-after-avatar");
    return creators.get(index).getText();
  }

	public String getCaseName() {
		waitForElementDisplayed(By.cssSelector("*[id$='case-list']"), true);
		WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
		WebElement selectedCaseNameElement = findElementById(
				selectedCaseElement.getAttribute("id") + ":case-name-component:case-name-form:case-name-edit-inplace");
		return selectedCaseNameElement.getText();
	}

	public String getCaseId() {
		waitForElementDisplayed(By.cssSelector("*[id$=':case-list']"), true);
		WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
		WebElement selectedCaseIdElement = selectedCaseElement.findElement(By.cssSelector(".case-header-id-cell"));
		return selectedCaseIdElement.getText();
	}
	
  public String getCaseId(int caseIndex) {
    waitForElementDisplayed(By.cssSelector("*[id$=':case-list']"), true);
    WebElement selectedCaseElement = findElementByCssSelector(String.format("[id$='case-list-scroller:%d:case-item:case-item-container']", caseIndex));
    WebElement selectedCaseIdElement = selectedCaseElement.findElement(By.cssSelector("[id$=':case-id-cell']"));
    return selectedCaseIdElement.getText();
  }

  public boolean isCaseDisplayed(String name) {
    waitForElementDisplayed(By.cssSelector("div[id='case-widget:case-list-scroller']"), true);
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

	@SuppressWarnings("deprecation")
	public void openAdvancedFilter(String filterName, String filterIdName) {
		click(By.id(caseWidgetId + ":filter-add-action"));
		waitForElementDisplayed(By.cssSelector(".filter-add-panel.ui-connected-overlay-enter-done"), true);
		waitForElementDisplayed(By.cssSelector("[id$='" + caseWidgetId + ":filter-add-form:filter-selection']"), true);
		WebElement filterSelectionElement = findElementById(caseWidgetId + ":filter-add-form:filter-selection");

		System.out.println(filterSelectionElement.getTagName());
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

	@SuppressWarnings("deprecation")
  public void filterByDescription(String text) {
		click(By.cssSelector("button[id$='description-filter:filter-open-form:advanced-filter-command']"));
		WebElement descriptionInput =
				findElementByCssSelector("input[id$='description-filter:filter-input-form:description']");
		enterKeys(descriptionInput, text);
		click(By.cssSelector("button[id$='description-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}

	@SuppressWarnings("deprecation")
  public void saveFilter(String filterName) {
		getSaveFilterDialog();
		WebElement filterNameInput = findElementById(caseWidgetId + ":filter-save-form:save-filter-set-name-input");
		enterKeys(filterNameInput, filterName);
		click(findElementById(caseWidgetId + ":filter-save-form:filter-save-command"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

  public WebElement getSaveFilterDialog() {
    click(By.id(caseWidgetId + ":filter-save-action"));
    var filterId = caseWidgetId + ":filter-save-action";
    clickByCssSelector("[id$='" + filterId + "']");
    waitForElementDisplayed(By.id(caseWidgetId + ":filter-save-form:save-filter-set-name-input"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, caseWidgetId + "\\\\:filter-save-form\\\\:save-filter-set-name-input", ID_PROPERTY);
    return findElementById(caseWidgetId + ":save-filter-set-dialog");
  }

	public String getFilterName() {
		WebElement filterName = findElementByCssSelector("[id$='case-widget:filter-selection-form:filter-name'] > span");
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
    WaitHelper.assertTrueWithWait(() -> {
      return findElementById(columnId).getAttribute(CLASS_PROPERTY).contains("is-selected");
    });
  }

	public String getSelectedSortColumn() {
	  waitForElementDisplayed(By.cssSelector(".js-case-widget-column-header"), true);
	  return findElementByCssSelector(".js-case-widget-column-header a.ui-commandlink.is-selected").getText();
	}

	public String getCaseListFirstCustomCellValue() {
		return findElementByCssSelector("div[id$=':0\\:case-item\\:case-item-container'] span.customized-case-header-column").getText();
	}

	public Integer getCaseCount() {
		String title = getTextOfCurrentBreadcrumb();
		String count = StringUtils.substringBetween(title, "(", ")");
		return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
	}

	/**
	 * count number of case row
	 * @return number of case elements
	 */
	public int countCases() {
    List<WebElement> caseItems = findListElementsByCssSelector("div[class*='case-list-item']");
    return caseItems.size();
  }
	
	 public void waitUntilCaseCountDifferentThanZero() {
	    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
	    .until(() -> getCaseCount().intValue() != 0);
	  }
	
	public void clickColumnsButton() {
		clickByCssSelector(SELECT_COLUMNS_LINK_CSS_SELECTOR);
		waitForElementDisplayedByCssSelector("label[for$='columns-checkbox:3']");
	}

	public void clickColumnCheckbox(int columnIndex) {
		WebElement columnCheckbox = findElementByXpath(String.format(SELECT_ITEM_XPATH, columnIndex));
		click(columnCheckbox);
	}

	public void clickDefaultCheckbox() {
		WebElement columnCheckbox = findElementByXpath(DEFAULT_COLUMNS_XPATH);
		click(columnCheckbox);
		WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("label[for$='columns-checkbox:3']").getAttribute("class").equals("ui-state-disabled"));
	}

	@SuppressWarnings("deprecation")
  public void clickApplyButton() {
		click(By.cssSelector(APPLY_BUTTON_CSS_SELECTOR));
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
		waitForElementDisplayed(By.cssSelector(".advanced-filter-panel.ui-connected-overlay-enter-done"), true);
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
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

	@SuppressWarnings("deprecation")
  public void filterByCreator(String text) {
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		WebElement responsible = findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']");
		type(responsible, text);
		waitAjaxIndicatorDisappear();
		waitForElementDisplayedByCssSelector("div[id*='creator-filter'] .ui-avatar-text");
		click(By.cssSelector("div[id*='creator-filter'] .ui-avatar-text"));
		waitAjaxIndicatorDisappear();
		click(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}
	
	@SuppressWarnings("deprecation")
  public boolean isUserDisplayInCreatorFilter(String userFullName) {
    click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
    WebElement responsible =
        findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']");
    type(responsible,userFullName);
    waitAjaxIndicatorDisappear();
    try {
      Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
      .until(() -> findElementByCssSelector("span[id$='filter-input-form:creator-component:creator-select_panel']").isDisplayed());
    }
    catch(ConditionTimeoutException timeoutException) {
      return false;
    }
    return true;
  }

  public void openSavedFilters(String filterName) {
    refreshAndWaitElement("a[id$='case-widget:filter-selection-form:filter-name']");
    click(findElementById("case-widget:filter-selection-form:filter-name"));
    waitForElementDisplayed(By.cssSelector(".filter-name-overlay-panel.ui-connected-overlay-enter-done"), true);
    List<WebElement> saveFilters = findListElementsByCssSelector("a[id$='user-defined-filter']");
    for (WebElement filter : saveFilters) {
      if (filter.getText().equals(filterName)) {
        click(filter);
        assertTrueWithWait(() -> findElementByCssSelector(".filter-name").getText().equals(filterName));
        return;
      }
    }
  }

	@SuppressWarnings("deprecation")
  public void removeResponsibleFilter() {
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		waitForElementDisplayed(By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"), true);
		findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").clear();
		click(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']"));
		waitAjaxIndicatorDisappear();
	}

	public String getCreator() {
		refreshAndWaitElement("button[id$='creator-filter:filter-open-form:advanced-filter-command']");
		click(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
		waitForElementDisplayed(By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"),true);
		return findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']")
        .getAttribute("value");
  }
	
	public void waitUntilCaseFilterDisplayed() {
	  waitForElementDisplayed(By.id("case-widget:filter-container"), true);
	}

  public CaseState getCaseState(int caseIndex) {
    List<WebElement> caseStateCells = findListElementsByCssSelector("span[id$=':case-state-cell']");
    String stateClass = caseStateCells.get(caseIndex).findElement(By.className("case-state")).getAttribute("class");
    String[] stateClasses = stateClass.trim().split(" ");
    String state = Stream.of(stateClasses).filter(clazz -> clazz.endsWith("-case-state")).findFirst().orElse("");
    return CaseState.fromClass(state);
  }
  
  @SuppressWarnings("deprecation")
  public void filterByOwner(String text) {
    click(By.cssSelector("button[id$='owner-filter:filter-open-form:advanced-filter-command']"));
    WebElement owner = findElementByCssSelector("input[id$='owner-filter:filter-input-form:owner_input']");
    type(owner, text);
    waitAjaxIndicatorDisappear();
    waitForElementDisplayedByCssSelector("div[id*='owner-filter'] .ui-avatar-text");
    click(By.cssSelector("div[id*='owner-filter'] .ui-avatar-text"));
    waitAjaxIndicatorDisappear();
    click(By.cssSelector("button[id$='owner-filter:filter-input-form:update-command']"));
    waitAjaxIndicatorDisappear();
  }

  public WebElement getExportToExcelLink() {
    return findElementByCssSelector("a[id$=':case-export-to-excel']");
  }

  public void clickExportToExcelLink() {
    // Ensure that attribute is removed before downloading
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':status-dialog']"));
    js.executeScript("arguments[0].removeAttribute('download-status')", statusDialog);

    // click download
    WebElement downloadLink = getExportToExcelLink();
    if (downloadLink != null) {
      downloadLink.click();
    }
  }

  public boolean isDownloadCompleted() {
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':status-dialog']"));
    WaitHelper.assertTrueWithWait(() -> StringUtils.isNotBlank(statusDialog.getAttribute("download-status")));
    return StringUtils.equals(statusDialog.getAttribute("download-status"), "completed");
  }
  
  public boolean isCategoryColumnDisplayed() {
    return findElementByCssSelector("span[id$=':case-category-cell']").isDisplayed();
  }

  public List<String> getAvailableActionSteps() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-steps-panel']"), true);
    var steps = findListElementsByCssSelector("[id$=':action-steps-panel'] a.action-step-item");
    return steps.stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public void clickOnProcessViewerOption() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-steps-panel']"), true);
    clickByCssSelector("a[id$=':case-item:case-item-action-form:action-step-component:show-process-viewer-link']");
  }
}
