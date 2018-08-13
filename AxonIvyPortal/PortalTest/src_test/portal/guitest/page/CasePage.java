package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class CasePage extends TemplatePage {
  private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
  private static final String CASE_NAME_CSS_SELECTOR = "span[class='case-header-name-cell']";
  private final static String CASE_PAGE_LOCATION = "id('case-widget:case-list')";

  @Override
  protected String getLoadedLocator() {
    return CASE_PAGE_LOCATION;
  }

  public WebElement selectCaseItem(int index) {
    String caseItemId = String.format("case-widget:case-list-scroller:%s:case-item", index);
    return findElementById(caseItemId);
  }

  public boolean isCaseItemSelected(int index) {
    return findElementById("case-widget:case-list-scroller:" + index + ":case-item").getAttribute("class").contains(
        "case-list-item-expanded");
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
    String destroyCaseDialogId = "case-widget:destroy-case-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
    WebElement confirmButton = findChildElementById(destroyConfirmationDialog, "case-widget:confirm-destruction");
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

  public String getCaseName() {
    waitForElementDisplayed(By.cssSelector("*[id$='case-widget:case-list']"), true);
    WebElement selectedCaseElement = findElementByCssSelector(".case-list-item-expanded");
    WebElement selectedCaseNameElement =
        findElementById(selectedCaseElement.getAttribute("id") + ":case-header:case-name-form:case-name-edit-inplace");
    return selectedCaseNameElement.getText();
  }

  public boolean isCaseDisplayed(String name) {
    List<WebElement> caseNameElements = findListElementsByClassName("case-header-name-cell");
    return caseNameElements.stream().anyMatch(caseNameElement -> name.equals(caseNameElement.getText()));
  }


  public void openAdvancedFilter(String filterName, String filterIdName) {
    click(By.id("case-widget:filter-add-action"));
    WebElement filterSelectionElement = findElementById("case-widget:filter-add-form:filter-selection");
    findChildElementsByTagName(filterSelectionElement, "LABEL").forEach(filterElement -> {
      if (filterName.equals(filterElement.getText())) {
        filterElement.click();
        return;
      }
    });
    waitForElementDisplayed(
        By.cssSelector("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']"), true);
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
    click(By.id("case-widget:filter-save-action"));
    Sleeper.sleepTight(2000);
    WebElement filterNameInput = findElementById("case-widget:filter-save-form:save-filter-set-name-input");
    enterKeys(filterNameInput, filterName);
    click(findElementById("case-widget:filter-save-form:filter-save-command"));
    Sleeper.sleepTight(2000);
  }

  public Object getFilterName() {
    click(findElementById("case-widget:filter-selection-form:filter-name"));
    WebElement descriptionInput = findElementByCssSelector(".user-definied-filter-container");

    return descriptionInput.getText();
  }

  public boolean isFilterSelectionVisible() {
    return isElementPresent(By.id("case-widget:filter-selection-form:filter-selection-panel"));
  }

  public int countCategoryRoots() {
    List<WebElement> taskElements = findListElementsByCssSelector("span[class*='js-second-level-menu']");
    return taskElements.size();
  }

  public void toggleCategoryMenu() {
    click(findElementByClassName("second-level-menu-header"));
  }
  
  public int getCaseCount() {
    WebElement caseTitleElement = findElementById("case-widget:case-widget-title");
    String regExp = "\\((.*?)\\)";
    String title = caseTitleElement.getText();
    return Integer.parseInt(title.substring(title.lastIndexOf("("), title.length() -1));
  }
}
