package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.CaseState;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class CaseWidgetPage extends TemplatePage {

  private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
  private static final String CASE_NAME_CSS_SELECTOR = "span[class*='case-header-name-cell']";
  private static final String SELECT_COLUMNS_LINK_CSS_SELECTOR = "a[id$='case-config-button']";
  private static final String SELECT_ITEM_XPATH =
      "//*[@id=\"case-widget:case-columns-configuration:select-columns-form:columns-checkbox\"]/tbody/tr[%s]/td/div/div[2]";
  private static final String APPLY_BUTTON_CSS_SELECTOR = "button[id$='select-columns-form:update-command']";
  private static final String DEFAULT_COLUMNS_XPATH =
      "//*[@id=\"case-widget:case-columns-configuration:select-columns-form:default-columns\"]/div[2]";

  private String caseWidgetId;

  public CaseWidgetPage() {
    this("case-widget");
  }

  public CaseWidgetPage(String caseWidgetId) {
    this.caseWidgetId = caseWidgetId;
  }

  @Override
  protected String getLoadedLocator() {
    return ".js-case-widget-header";
  }

  public CaseDetailsPage openCase(String caseName) {
    $("div[id='case-widget:case-list']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("div[id='case-widget:case-list'] ul li div[id$=':case-item'] span.case-info-row span.case-header-name-cell")
        .filter(text(caseName)).first().click();
    return new CaseDetailsPage();
  }

  public void openAdvancedFilter(String filterName, String filterIdName) {
    $("a[id$='filter-add-action']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$='filter-add-action']").click();
    $("table[id$='case-widget:filter-add-form:filter-selection']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("table[id$='case-widget:filter-add-form:filter-selection'] label").filter(text(filterName)).first().click();
    $("button[id$='case-widget:filter-add-form:update-filter-selected-command']").click();
    $("div[id$='case-widget:filter-add-panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("span[id$='" + filterIdName + "-filter:advanced-filter-component").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterCasesByCreatedDate(String fromCreatedDate, String toCreatedDate) {
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").shouldBe(getClickableCondition())
        .click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:from-created-calendar_panel"));
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:to-created-calendar_panel"));
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void filterFirstApp() {
    $("button[id$=':application-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    final ElementsCollection checkboxLabel = $$("span.ui-chkbox-label");
    for (int i = 0; i < checkboxLabel.size(); i++) {
      if (checkboxLabel.get(i).getText().equals("Select All")) {
        checkboxLabel.get(i).click();
      }
    }
    $("button[id$=':application-filter:filter-input-form:update-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private void closePanelDatePicker(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].style.display = 'none'", element);
  }

  public ElementsCollection countCases() {
    return $$("div[id$='case-widget:case-list'] ul li");
  }

  public SelenideElement getCreatorAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getCaseId(int caseIndex) {
    $("[id$=':case-list']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    SelenideElement selectedCaseIdElement =
        $(String.format("[id$='case-list-scroller:%d:case-item:case-item-container']", caseIndex))
            .find(By.cssSelector("[id$=':case-id-cell']"));
    return selectedCaseIdElement.getText();
  }

  public String getCaseNameAt(int index) {
    waitForElementDisplayed(By.className("js-case-list"), true);
    SelenideElement rowInfoContainer = $(String.format("[id$='case-widget:case-list-scroller:%d:case-item:case-info-row']", index));
    String name = rowInfoContainer.$("div > .case-header-name-cell").getText();
    return name;
  }
  
  public String getGlobalSearchCaseNameAt(int index) {
    waitForElementDisplayed(By.className("js-case-list"), true);
    SelenideElement name =
        $("[id$='case-list-scroller:" + index + ":case-item:case-name-component:case-header-name-cell']");
    return name.getText();
  }

  public boolean isCaseDisplayed(String name) {
    waitForElementDisplayed(By.cssSelector("div[id='case-widget:case-list-scroller']"), true);
    List<SelenideElement> caseNameElements = $$(".case-header-name-cell");
    return caseNameElements.stream().anyMatch(caseNameElement -> name.equals(caseNameElement.getText()));
  }

  public ElementsCollection getCasesList() {
    return $$(CASE_ITEM_LIST_SELECTOR);
  }

  public CaseState getCaseState(int caseIndex) {
    List<SelenideElement> caseStateCells = $$("span[id$=':case-state-cell']");
    String stateClass = caseStateCells.get(caseIndex).findElement(By.className("case-state")).getAttribute("class");
    String[] stateClasses = stateClass.trim().split(" ");
    String state = Stream.of(stateClasses).filter(clazz -> clazz.endsWith("-case-state")).findFirst().orElse("");
    return CaseState.fromClass(state);
  }

  public CaseDetailsPage openCaseDetailsFromActionMenuByCaseName(String caseName) {
    List<SelenideElement> caseItems = $$(CASE_ITEM_LIST_SELECTOR);
    for (SelenideElement caseItem : caseItems) {
      if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
        caseItem.findElement(By.cssSelector("a[id*='action-steps-menu']")).click();
        waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
        findElementByCssSelector("a[id$='case-item-open-detail-link']").click();
        return new CaseDetailsPage();
      }
    }
    throw new NoSuchElementException("Cannot find case has name " + caseName);
  }

  public void filterByOwner(String text) {
    waitForElementClickableThenClick("button[id$='owner-filter:filter-open-form:advanced-filter-command']");
    $("input[id$='owner-filter:filter-input-form:owner_input']").sendKeys(text);
    waitForElementDisplayed($("div[id*='owner-filter'] .ui-avatar-text"), true);
    waitForElementClickableThenClick("span[id$=':owner-filter:filter-input-form:owner_panel']");
    waitForElementClickableThenClick("button[id$='owner-filter:filter-input-form:update-command']");
  }

  public void filterByDescription(String text) {
    waitForElementClickableThenClick("button[id$='description-filter:filter-open-form:advanced-filter-command']");
    $("input[id$='description-filter:filter-input-form:description']").clear();
    $("input[id$='description-filter:filter-input-form:description']").sendKeys(text);
    waitForElementClickableThenClick("button[id$='description-filter:filter-input-form:update-command']");
  }

  public void saveFilter(String filterName) {
    getSaveFilterDialog();
    $(By.id(caseWidgetId + ":filter-save-form:save-filter-set-name-input")).sendKeys(filterName);
    waitForElementClickableThenClick($(By.id(caseWidgetId + ":filter-save-form:filter-save-command")));
  }

  public String getFilterName() {
    WebElement filterName = findElementByCssSelector("[id$='case-widget:filter-selection-form:filter-name'] > span");
    return filterName.getText();
  }

  public void openCategoryFilter() {
    waitForElementClickableThenClick("button[id$='case-category-filter:filter-open-form:advanced-filter-command']");
    waitForElementDisplayed(By.cssSelector("div[id$=':case-category-filter-tree']"), true);
  }

  public String getFilterValue(String filterId) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
        (driver) -> findElementByCssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']")
            .getText().length() > 1);
    waitForElementDisplayed(By.cssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']"),
        true);
    WebElement filterElement =
        findElementByCssSelector("button[id$='" + filterId + ":filter-open-form:advanced-filter-command']");
    $(By.cssSelector("button[id$='case-category-filter:filter-input-form:update-command']")).shouldBe(disappear,
        DEFAULT_TIMEOUT);
    return filterElement.getText();
  }

  public boolean isAllCategoriesSelected() {
    waitForElementDisplayed(By.cssSelector(".filter-category-checkbox-tree"), true);
    return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-selected"));
  }

  public boolean isAllCategoriesUnselected() {
    waitForElementDisplayed(By.cssSelector(".filter-category-checkbox-tree"), true);
    return isElementDisplayed(By.cssSelector(".filter-category-checkbox-tree .ui-treenode-hasselected"));
  }

  public void toggleNoCategory() {
    List<SelenideElement> categories = $$(".filter-category-checkbox-tree .ui-tree-selectable");
    for (WebElement category : categories) {
      if (category.getText().equals("[No Category]")) {
        category.click();
        return;
      }
    }
  }

  public void applyCategoryFilter() {
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='case-category-filter:filter-input-form:update-command']")));
  }

  public void filterByCreator(String text) {
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']")));
    $("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").sendKeys(text);
    waitForElementDisplayed($("div[id*='creator-filter'] .ui-avatar-text"), true);
    waitForElementClickableThenClick(
        "span[id$=':creator-filter:filter-input-form:creator-component:creator-select_panel']");
    waitForElementClickableThenClick(
        $(By.cssSelector("button[id$='creator-filter:filter-input-form:update-command']")));
  }

  public boolean isUserDisplayInCreatorFilter(String userFullName) {
    waitForElementClickableThenClick(
        $((By.cssSelector("button[id$='creator-filter:filter-open-form:advanced-filter-command']"))));
    $(("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']")).sendKeys(userFullName);
    try {
      new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(
          (driver) -> findElementByCssSelector("span[id$='filter-input-form:creator-component:creator-select_panel']")
              .isDisplayed());
    } catch (Exception timeoutException) {
      return false;
    }
    return true;
  }

  public void openSavedFilters(String filterName) {
    refreshAndWaitElement("a[id$='case-widget:filter-selection-form:filter-name']");
    waitForElementClickableThenClick($(By.id(("case-widget:filter-selection-form:filter-name"))));
    waitForElementDisplayed(By.cssSelector(".filter-name-overlay-panel.ui-connected-overlay-enter-done"), true);
    List<SelenideElement> saveFilters =
        $$("a[id$='user-defined-filter']").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT);
    for (SelenideElement filter : saveFilters) {
      if (filter.getText().equals(filterName)) {
        $(By.id("case-widget:filter-selection-form:filter-name-overlay-panel")).shouldBe(appear, DEFAULT_TIMEOUT);
        filter.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
        $(".filter-name").shouldHave(Condition.text(filterName), DEFAULT_TIMEOUT);
        return;
      }
    }
    $(".selected-filters-label").hover().click();
  }

  protected void refreshAndWaitElement(String cssSelector) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until((driver) -> {
      if (($$(cssSelector).isEmpty())) {
        WaitHelper.waitForNavigation(() -> refresh());
        return false;
      } else {
        return true;
      }
    });
  }

  public void removeResponsibleFilter() {
    waitForElementClickableThenClick($("button[id$='creator-filter:filter-open-form:advanced-filter-command']"));
    waitForElementDisplayed(
        By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"), true);
    findElementByCssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").shouldBe(appear, DEFAULT_TIMEOUT)
        .clear();
    waitForElementClickableThenClick($("button[id$='creator-filter:filter-input-form:update-command']"));
    waitForElementDisplayed($("button[id$='creator-filter:filter-input-form:update-command']"), false);
  }

  public String getCreator() {
    refreshAndWaitElement("button[id$='creator-filter:filter-open-form:advanced-filter-command']");
    waitForElementClickableThenClick($(("button[id$='creator-filter:filter-open-form:advanced-filter-command']")));
    waitForElementDisplayed(
        By.cssSelector("input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']"), true);
    return findElementByCssSelector(
        "input[id$='creator-filter:filter-input-form:creator-component:creator-select_input']").getAttribute("value");
  }

  public void clickDestroyButton() {
    WebElement destroyButton = getDestroyButtonOfCaseItem();
    destroyButton.click();
  }

  private WebElement getDestroyButtonOfCaseItem() {
    openActionStepMenu();
    waitForElementDisplayed(By.cssSelector("a[id$='destroy-case']"), true);
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> findElementByCssSelector("a[id$='destroy-case']").isDisplayed());
    return findElementByCssSelector("a[id$='destroy-case']");
  }

  public void openActionStepMenu() {
    waitForElementDisplayed(By.cssSelector("[id$=':case-item:case-item-action-form']"), true);
    waitForElementClickableThenClick($(("a[id$='action-steps-menu']")));
  }

  public boolean isDestroyButtonVisible() {
    openActionStepMenu();
    waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
    return !$$(By.cssSelector("a[id$='destroy-case'")).isEmpty();
  }

  public void confimDestruction() {
    String destroyCaseDialogId = caseWidgetId + ":destroy-case-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    $(By.id(destroyCaseDialogId)).$(By.id(caseWidgetId + ":confirm-destruction"))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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

  public void clickColumnsButton() {
    waitForElementClickableThenClick($(SELECT_COLUMNS_LINK_CSS_SELECTOR));
    waitForElementDisplayed(By.cssSelector("label[for$='columns-checkbox:3']"), true);
  }

  public void clickColumnCheckbox(int columnIndex) {
    waitForElementClickableThenClick($(By.xpath((String.format(SELECT_ITEM_XPATH, columnIndex)))));
  }

  public void clickColumnCheckboxByName(String columnName) {
    waitForElementDisplayed(By.cssSelector("[id$=':case-columns-configuration:case-config-columns-panel']"), true);
    $$("label[for*=':case-columns-configuration:select-columns-form:columns-checkbox:']").asFixedIterable().stream()
        .filter(checkbox -> checkbox.getText().equalsIgnoreCase(columnName)).findAny().ifPresent(foundCheckbox -> {
          clickByJavaScript(foundCheckbox);
        });
  }

  public void clickDefaultCheckbox() {
    waitForElementClickableThenClick($(By.xpath(DEFAULT_COLUMNS_XPATH)));
    WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("label[for$='columns-checkbox:3']")
        .getAttribute("class").equals("ui-state-disabled"));
  }

  public void clickApplyButton() {
    $(APPLY_BUTTON_CSS_SELECTOR).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(APPLY_BUTTON_CSS_SELECTOR).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void waitUntilCaseCountDifferentThanZero() {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((webDriver) -> getCaseCount().intValue() != 0);
  }

  public Integer getCaseCount() {
    String title = getTextOfCurrentBreadcrumb();
    String count = StringUtils.substringBetween(title, "(", ")");
    return StringUtils.isNotBlank(count) ? Integer.parseInt(count) : null;
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

  public WebElement getExportToExcelLink() {
    return findElementByCssSelector("a[id$=':case-export-to-excel']");
  }

  public boolean isDownloadCompleted() {
    WebElement statusDialog = driver.findElement(By.cssSelector("div[id$=':status-dialog']"));
    WaitHelper.assertTrueWithWait(() -> StringUtils.isNotBlank(statusDialog.getAttribute("download-status")));
    return StringUtils.equals(statusDialog.getAttribute("download-status"), "completed");
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

  public void clickOnProcessViewerOption() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-steps-panel']"), true);
    waitForElementClickableThenClick(
        "a[id$=':case-item:case-item-action-form:action-step-component:show-process-viewer-link']");
  }

  public List<String> getAvailableActionSteps() {
    waitForElementDisplayed(By.cssSelector("[id$=':action-step-component:action-steps-panel']"), true);
    var steps = $$("[id$=':action-step-component:action-steps-panel'] a.action-step-item");
    return steps.asFixedIterable().stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public SelenideElement openActionStepMenu(int index) {
    String menuSelector = String.format(
        "[id='case-widget:case-list-scroller:%d:case-item:case-item-action-form:action-step-component:action-steps-menu']",
        index);
    String menuPanelSelector = String.format(
        "[id='case-widget:case-list-scroller:%d:case-item:case-item-action-form:action-step-component:action-steps-panel']",
        index);
    $(menuSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $(menuPanelSelector).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openAdditionalCaseDetails(SelenideElement actionMenu) {
    actionMenu.$("[id$=':show-additional-case-details-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
  }

  public SelenideElement getSaveFilterDialog() {
    $(By.id(caseWidgetId + ":filter-save-action")).shouldBe(getClickableCondition()).click();
    $(By.id(caseWidgetId + ":filter-save-form:save-filter-set-name-input")).shouldBe(Condition.visible,
        DEFAULT_TIMEOUT);
    return $(By.id(caseWidgetId + ":save-filter-set-dialog")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public CaseDetailsPage openDetailsOfCaseHasName(String caseName) {
    List<SelenideElement> caseItems = $$(CASE_ITEM_LIST_SELECTOR);
    for (SelenideElement caseItem : caseItems) {
      if (caseItem.$(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
        SelenideElement caseInfoRow = caseItem.$(By.cssSelector("span[id*='case-info-row']")).scrollTo();
        $(caseInfoRow).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
        caseInfoRow.shouldBe(disappear, DEFAULT_TIMEOUT);
        return new CaseDetailsPage();
      }
    }
    throw new NoSuchElementException("Cannot find case has name " + caseName);
  }

  public int getNumberOfCases() {
    List<SelenideElement> caseItems = $$(CASE_ITEM_LIST_SELECTOR);
    return caseItems.size();
  }

  public boolean isEmpty() {
    return isElementDisplayed(By.id("search-results-tabview:case-results:case-empty-message"));
  }

  public int countSideStepItems() {
    SelenideElement actionsPanel = getMoreActionsPanel();
    return actionsPanel.findElements(By.cssSelector("a[id$='side-step-item']")).size();
  }

  private SelenideElement getMoreActionsPanel() {
    openActionStepMenu();
    waitForElementDisplayed(By.cssSelector("div[id$='action-steps-panel']"), true);
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until((driver) -> findElementByCssSelector("div[id$='action-steps-panel']").isDisplayed());
    return findElementByCssSelector("div[id$='action-steps-panel']");
  }

  public String getCreatorAt(int index) {
    List<SelenideElement> creators = $$(".case-header-creator-cell .name-after-avatar");
    return creators.get(index).getText();
  }

  public String getCaseListFirstCustomCellValue() {
    return $("div[id$=':0\\:case-item\\:case-item-container'] span.customized-case-header-column").getText();
  }
}
