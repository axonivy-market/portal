package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.WaitHelper;

public class ProcessWidgetPage extends TemplatePage {

  private static final String ACTIVE_MODE_SELECTOR = "[id$=':process-view-mode:view-mode-selection'] div.ui-button.ui-button-text-only.ui-state-active .ui-button-text";
  private static final String IMAGE_ACTION_BUTTON = "[id$=':%d:image-processes:%d:process-item:image-process-action-component:process-action-button']";
  private static final String GRID_ACTION_BUTTON = "[id$=':%d:grid-processes:%d:process-grid-item:process-item:grid-process-action-component:process-action-button']";
  private static final String IMAGE_EDIT_PROCESS_LINK = "[id$=':%d:image-processes:%d:process-item:image-process-action-component:edit-process']";
  private WebElement switchModeButton;
  private WebElement liveSearchTextField;
  private WebElement processWidget;
  private String processWidgetId;
  

  public ProcessWidgetPage() {
    this("process-widget");
  }
  
  public ProcessWidgetPage(String processWidgetId) {
    this.processWidgetId = processWidgetId;
    processWidget = findElementById(this.processWidgetId);
  }
  
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'process-list')]";
  }

  public void waitAndStartProcess(String processName) {
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> {
      if (findListElementsByCssSelector(".js-no-found-processes.u-display-none").get(0).isDisplayed()) {
        refresh();
        return false;
      } else {
        return true;
      }
    });    

    startProcess(processName);
  }

  public void startProcess(String processName) {
    WebElement processItemElement = getProcess(processName);
    processItemElement.click();
  }

  public WebElement getProcess(String processName) {
    WebElement processListElement = findElementById(processWidgetId + ":process-list");
    if (isImageModeActivated()) {
      return getStartImageProcess(processName, processListElement);
    }
    return findChildElementByXpathExpression(processListElement, "//a[.//text() = '" + processName + "']");
  }

  public WebElement getStartImageProcess(String processName, WebElement processListElement) {
    WebElement startProcessItemElement = null;
    List<WebElement> processItems = findChildElementsByCssSelector(processListElement, ".js-process-start-list-item");
    for (WebElement process : processItems) {
      WebElement processNameElement = findChildElementByCssSelector(process, ".js-process-start-list-item-name");
      if (processNameElement.getText().equalsIgnoreCase(processName)) {
        startProcessItemElement = findChildElementByCssSelector(process, "[id$=':process-item:start-button']");
        break;
      }
    }
    return startProcessItemElement;
  }

  public WebElement getProcessItem(String processName) {
    WebElement processItemElement = null;
    List<WebElement> processItems = findListElementsByClassName("js-process-start-list-item");
    for (WebElement process : processItems) {
      processItemElement = findChildElementByCssSelector(process, ".js-process-start-list-item-name");
      if (processItemElement.getText().equalsIgnoreCase(processName)) {
        processItemElement = process;
        break;
      }
    }
    return processItemElement;
  }

  public ExpressProcessPage editExpressWF(String wfName) {
    int numberOfRefesh = 5;
    for (int i = 0; i < numberOfRefesh; i++) {
      waitForElementDisplayed(By.id(processWidgetId + ":process-search:non-ajax-keyword-filter"), true);
      enterSearchKeyword(wfName);
      if (isImageModeActivated()) {
        WebElement processForm = findElementByCssSelector("form.image-view-form.is-express");
        var processFormID = processForm.getAttribute("id");
        clickByCssSelector("[id$='" + processFormID + ":image-process-action-component:process-action-button']");
        waitForElementDisplayed(By.cssSelector(".process-action-menu.ui-connected-overlay-enter-done"), true);
        if (isElementDisplayed(By.cssSelector("div[id$='"+ processFormID + ":image-process-action-component:process-action-menu']"))) {
          WebElement actionMenu = findElementByCssSelector("div[id$='"+ processFormID + ":image-process-action-component:process-action-menu']");
          WebElement icon = findChildElementByCssSelector(actionMenu, "a[id$=':process-item:image-process-action-component:edit-process']");
          icon.click();
          waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), true);
          clickByCssSelector("a[id$='process-widget:edit-process-form:edit-express-workflow']");
          break;
        }
      } else {
        if (isElementDisplayed(By.cssSelector("[id$='edit-express-workflow']"))) {
          click(By.cssSelector("[id$='edit-express-workflow']"));
          break;
        }
      }

      refresh();
    }
    return new ExpressProcessPage();
  }

  public boolean isProcessDisplay(String processName) {
    return getProcess(processName) !=null;
  }
  
  public boolean isNoProcessFound() {
    return isElementPresent(By.xpath("//span[@id='process-widget:no-found-processes']"));
  }
  
  public boolean isProcessGroupDisplay(String processGroupCharacter) {
    if (isImageModeActivated()) {
      List<WebElement> webElements = findListElementsByClassName("js-grid-process-index-group");
      return webElements.stream().anyMatch(processItem -> processItem.isDisplayed() && processItem.getAttribute("class").endsWith(processGroupCharacter));
    }
    List<WebElement> indexGroup = findListElementsByXpath("//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default']");
    return indexGroup.stream().anyMatch(item -> processGroupCharacter.equals(item.getText()));
  }
  
  public String getProcessNameFromFavoriteProcessList(int index) {
    String id = index + ":process-item-form:process-name";
    WebElement favoriteProcessList = findElementById(processWidgetId + ":process-list");
    String name = findChildElementByCssSelector(favoriteProcessList, "span[id*='" + id + "']").getText();
    return name;
  }

  public String getProcessNameFromDefaultProcessList(int index) {
    WebElement defaultProcessList = findElementById(processWidgetId + ":user-default-process-list");
    String id = index + ":process-item-form:process-name";
    String name = findChildElementByCssSelector(defaultProcessList, "span[id*='" + id + "']").getText();
    return name;
  }

  public void clickEditSwitchLink() {
    waitForElementDisplayed(By.cssSelector("[id$='editing-switch-command']"), true, DEFAULT_TIMEOUT);
    click(findElementByCssSelector("[id$='editing-switch-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickSaveProcess() {
    WebElement deleteProcessLink = findChildElementByCssSelector(processWidget, "[id$='save-process-command']");
    click(deleteProcessLink);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public UserFavoriteProcessPage openNewProcessDialog() {
    click(findChildElementByCssSelector(processWidget, "[id$='show-adding-dialog-commmand']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    return new UserFavoriteProcessPage();
  }
  
  public AddNewExternalLinkDialog openNewExternalLinkDialog() {
    waitForElementDisplayed(By.id(processWidgetId + ":add-external-link-command"), true);
    click(By.id("process-widget:add-external-link-command"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "process-widget\\\\:add-external-link-form\\\\:external-link-name", ID_PROPERTY);
    return new AddNewExternalLinkDialog();
  }

  private List<WebElement> findDeleteIcons() {
    waitForElementDisplayed(By.id(processWidgetId + ":process-list"), true);
    WebElement processList = findElementById(processWidgetId + ":process-list");
    List<WebElement> deleteItems = processList.findElements(By.cssSelector("a[id*='process-delete-link']"));
    return deleteItems;
  }

  public boolean isDeleteProcessItemSelected(int itemIndex) {
    WebElement deleteItem = findDeleteIcons().get(itemIndex);

    WebElement deleteItemCheckBox = findChildElementByClassName(deleteItem, "si");
    String styleClass = deleteItemCheckBox.getAttribute("class");
    return styleClass.contains("si-undo");
  }

  public void checkDeleteItem(int itemIndex) {
    List<WebElement> deleteCheckboxes = findDeleteIcons();
    WebElement checkBox = deleteCheckboxes.get(itemIndex);
    click(checkBox);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public int getNumberOfFavoriteUserProcesses() {
    WebElement favoriteProcessList = findElementById(processWidgetId + ":process-list");
    List<WebElement> processes = findChildElementsByCssSelector(favoriteProcessList, "form[id*='process-item-form']");
    return processes.size();
  }

  public void clickSortFavoriteProcessByName() {
    waitForElementDisplayed(By.cssSelector("[id$='name-sort-command']"), true, DEFAULT_TIMEOUT);
    click(findElementByCssSelector("[id$='name-sort-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickSortDefaultProcessByName() {
    waitForElementDisplayed(By.cssSelector("[id$='default-process-name-sort-command']"), true, DEFAULT_TIMEOUT);
    click(findElementByCssSelector("[id$='default-process-name-sort-command']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean isExpandedMode() {
    loadLiveSearchTextField();
    return liveSearchTextField.isDisplayed();
  }

  public boolean isCompactMode() {
    List<WebElement> findElements = driver.findElements(By.id(processWidgetId + ":process-search:non-ajax-keyword-filter"));
    return findElements.isEmpty();
  }

  public boolean isImageModeActivated() {
    List<WebElement> findElements = driver.findElements(By.cssSelector("[id$=':image-process-container']"));
    return !findElements.isEmpty();
  }

  public void expand() {
    loadSwitchModeButton();
    click(switchModeButton);
    waitForElementDisplayed(By.id(processWidgetId + ":process-search:non-ajax-keyword-filter"), true, DEFAULT_TIMEOUT);
  }

  private void loadSwitchModeButton() {
    switchModeButton = findElementById(processWidgetId + ":process-link:process-link-label");
  }

  private void loadLiveSearchTextField() {
    liveSearchTextField = findElementById(processWidgetId + ":process-search:non-ajax-keyword-filter");
  }
  
  public void enterSearchKeyword(String keyword) {
    liveSearchTextField = findElementById(processWidgetId + ":process-search:non-ajax-keyword-filter");
    type(liveSearchTextField, keyword);
  }
  
  public void moveFavoriteProcess(int processToMoveIndex, int destinationProcessIndex) {
    WebElement editProcesses = findElementByCssSelector("form[id$=':edit-process-item-form']");
    List<WebElement> processItems = findChildElementsByCssSelector(editProcesses, ".ui-orderlist-list .ui-orderlist-item");
    WebElement processToMove = processItems.get(processToMoveIndex - 1);
    WebElement destinationProcess = processItems.get(destinationProcessIndex - 1);

    Actions builder = new Actions(driver);
    Action moveProcessSequence = builder.dragAndDrop(processToMove, destinationProcess).build();
    moveProcessSequence.perform();
  }
  
  public class AddNewExternalLinkDialog {
    private final String EXTERNAL_LINK_NAME_INPUT_CSS_SELECTOR = "input[id$=':add-external-link-form:external-link-name']";
    private final String EXTERNAL_LINK_INPUT_CSS_SELECTOR = "input[id$=':add-external-link-form:external-link']";
    private final String ADD_EXTERNAL_LINK_BUTTON_INPUT_CSS_SELECTOR = "button[id$='adding-new-external-link-command']";
    
    private AddNewExternalLinkDialog() {}

    public void inputDataForExternalLink(String processName, String processLink, boolean isPublic) {
      inputExternalLinkName(processName);
      inputExternalLink(processLink);
      selectExternalLinkType(isPublic);
    }
    
    public void inputExternalLinkName(String name) {
      WebElement externalLinkInputField = findDisplayedElementByCssSelector(EXTERNAL_LINK_NAME_INPUT_CSS_SELECTOR);
      externalLinkInputField.sendKeys(name);
    }

    public void inputExternalLink(String link) {
      WebElement externalLinkInputField = findDisplayedElementByCssSelector(EXTERNAL_LINK_INPUT_CSS_SELECTOR);
      externalLinkInputField.sendKeys(link);
    }
    
    public void selectExternalLinkType(boolean isPublic) {
      if (isPublic) {
        WebElement externalCheckboxLabel = findElementByCssSelector("label[for$=':add-external-link-form:external-link-type-radio:1']");
        externalCheckboxLabel.click();
      }
    }

    @SuppressWarnings("deprecation")
    public void submitForm() {
      WebElement submitButton = findElementByCssSelector(ADD_EXTERNAL_LINK_BUTTON_INPUT_CSS_SELECTOR);
      submitButton.click();
      waitAjaxIndicatorDisappear();
    }
  }
  
  public ExpressProcessPage openExpressPage() {
    waitForElementDisplayed(By.id(processWidgetId + ":create-express-workflow"), true);
    click(By.id("process-widget:create-express-workflow"));
    return new ExpressProcessPage();
  }
  
  public boolean isProcessEmpty() {
    return isElementDisplayed(By.id("search-results-tabview:process-results:full-process-empty-message"));
  }
  
  public boolean hasCreateNewExpressWorkflowLink() {
    return isElementPresent(By.id("process-widget:create-express-workflow"));
  }

  @SuppressWarnings("deprecation")
  public void deleteExternalLinkByFieldsetIndexAndIndex(int fieldsetIndex, int index) {
    String deleteExternalLinkIconCssSelector = String.format("a[id$='%d:processes:%d:process-item-form:delete-external-link']", fieldsetIndex, index);
    WebElement deleteExternalIcon = findElementByCssSelector(deleteExternalLinkIconCssSelector);
    deleteExternalIcon.click();
    waitAjaxIndicatorDisappear();
    findElementByCssSelector("button[id$=':remove-process-command']").click();
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public ExampleOverviewPage openExampleOverviewPage(String exampleProcessName) {
    click(getProcess(exampleProcessName));
    waitAjaxIndicatorDisappear();
    return new ExampleOverviewPage();
  }

  public WebElement getAddExternalLinkDialog() {
    return findElementById("process-widget:add-external-link-dialog");
  }

  public WebElement navigateToProcessIndex(String character) {
    WebElement processIndex = findElementByCssSelector(".js-process-nav-item.js-process-starts-with-" + character);
    processIndex.click();
    return processIndex;
  }
  
  public void waitUtilProcessWidgetDisplayed() {
    waitForElementDisplayed(By.id("process-widget"), true);
  }
  
  public void waitUtilProcessWidgetUserFavoriteDisplayed() {
    waitForElementDisplayed(By.id("process-widget:user-process-container"), true);
  }

  public String getCurrentViewMode() {
    waitForElementDisplayed(
        By.cssSelector("[id$=':process-view-mode:view-mode-selection'] div.ui-button.ui-button-text-only.ui-state-active"),
        true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT,
        "process-widget\\\\:process-view-mode\\\\:view-mode-selection",
        ID_PROPERTY);
    return findElementByCssSelector(ACTIVE_MODE_SELECTOR).getText();
  }

  public void clickOnProcessEditLink(int index) {
    clickByCssSelector(String.format("[id$=':%d:grid-processes:0:process-grid-item:process-item:edit-link']", index));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), true);
  }

  public WebElement getProcessEditMenu(int index) {
    return findElementByCssSelector(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index));
  }

  public void clickOnProcessEditMenu(int index) {
    clickByCssSelector(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), true);
  }
  
  public WebElement getEditProcessDialog() {
    return findElementByCssSelector("[id$='process-widget:edit-process-dialog']");
  }

  public void changeProcessIcon() {
    clickByCssSelector("a[id^='process-widget:edit-process-form:edit-process-icon']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-form:edit-process-icon:select-icon-dialog']"), true);
    clickByCssSelector("[id$=':0:awesome-icon']");
    clickByCssSelector("[id$='process-widget:save-process-command']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), false);
  }

  public String getProcessItemIcon(int index) {
    WebElement processItem = findElementByCssSelector(String.format("[id$='grid-process-group-alphabet:%d:grid-processes:0:process-grid-item:process-item']", index));
    return processItem.findElement(By.id("icon")).getAttribute("class");
  }

  public void deleteGridProcess(int index) {
    clickByCssSelector(String.format("[id$=':%d:grid-processes:0:process-grid-item:process-item:grid-process-action-component:delete-process']", index));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), true);
    clickByCssSelector("[id$='delete-process-workflow-form:remove-process-command']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), false);
  }

  public void clickMoreInformationLink(String processName) {
    WebElement processItem = getProcessItem(processName);
    processItem.findElement(By.cssSelector(".more-information")).click();
  }
  
  public void waitForGridProcessListDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:grid-process-container']"), true);
  }

  public void waitForCompactProcessListDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:process-list']"), true);
  }

  public void selectViewMode(String viewMode) {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:process-view-mode:view-mode-selection']"), true);
    WebElement viewModeElement = findElementByCssSelector("[id$='process-widget:process-view-mode:view-mode-selection']");
    List<WebElement> webElements = viewModeElement.findElements(By.cssSelector("span.ui-button-text.ui-c"));
    if (CollectionUtils.isNotEmpty(webElements)) {
      for(WebElement webElement: webElements) {
        if(webElement.getText().equalsIgnoreCase(viewMode)) {
          webElement.click();
          WaitHelper.assertTrueWithWait(() -> {
            return findElementByCssSelector(ACTIVE_MODE_SELECTOR)
                .getText().equalsIgnoreCase(viewMode);
          });
          return;
        }
      }
    }
  }

  public void clickMoreButtonOfFirstImageProcess() {
    waitForElementDisplayed(By.cssSelector(String.format(IMAGE_ACTION_BUTTON, 0, 0)), true);
    clickByCssSelector(String.format(IMAGE_ACTION_BUTTON, 0, 0));
  }

  public WebElement getMoreMenuButtonOfImageProcess(int index) {
    return findElementByCssSelector(String.format(IMAGE_ACTION_BUTTON, index, 0));
  } 

  public void waitForMenuActionsDisplayed() {
    waitForElementDisplayed(By.cssSelector(String.format(IMAGE_ACTION_BUTTON, 0, 0)), true);
  }

  public void clickMoreButtonOfFirstGridProcess() {
    waitForElementDisplayed(By.cssSelector(String.format(GRID_ACTION_BUTTON, 0, 0)), true);
    clickByCssSelector(String.format(GRID_ACTION_BUTTON, 0, 0));
  }

  public void waitForMenuActionsOnGridModeDisplayed() {
    waitForElementDisplayed(
        By.cssSelector(
            "[id$='process-widget:grid-process-group-alphabet:0:grid-processes:0:process-grid-item:process-item:process-delete']"),
        true);
  }

  public void clickMoreButtonOfGridProcess(String processName) {
    var processItem = getProcessItemForm(processName);
    var actionButtonID = processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-button");
    clickByCssSelector(String.format("[id$='%s']", actionButtonID));
    waitForElementDisplayed(By.cssSelector(String
        .format("[id$='%s']", processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-menu"))),
        true);
  }

  public WebElement getProcessItemForm(String processName) {
    WebElement processItemElement = null;
    List<WebElement> processItems = findListElementsByCssSelector(".js-process-start-list-item form");
    for (WebElement process : processItems) {
      processItemElement = findChildElementByCssSelector(process, ".js-process-start-list-item-name");
      if (processItemElement.getText().equalsIgnoreCase(processName)) {
        processItemElement = process;
        break;
      }
    }
    return processItemElement;
  }

  public void deleteGridProcess(String processName) {
    var processItem = getProcessItemForm(processName);
    waitForElementDisplayed(By.cssSelector(String.format("[id$='%s']",
        processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-menu"))),
        true);
    clickByCssSelector(String.format("[id$='%s']", processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:delete-process")));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), true);
    clickByCssSelector("[id$='delete-process-workflow-form:remove-process-command']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), false);
  }
}
