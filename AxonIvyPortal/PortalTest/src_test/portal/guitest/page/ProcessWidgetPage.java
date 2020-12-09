package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;

public class ProcessWidgetPage extends TemplatePage {

  private WebElement switchModeButton;
  private WebElement liveSearchTextField;
  private WebElement processWidget;
  private String searchInputField = "process-widget:process-search:non-ajax-keyword-filter";

  public ProcessWidgetPage() {
    processWidget = findElementById("process-widget");
  }

  @Override
  protected String getLoadedLocator() {
    return "id('process-widget')";
  }

  public WebElement getProcessWidget() {
    return processWidget;
  }

  public void startProcess(String processName) {
    WebElement processItemElement = getProcess(processName);
    click(processItemElement);
  }

  public WebElement getProcess(String processName) {
    WebElement processListElement = findElementById("process-widget:process-list");
    WebElement processItemElement = null;
    try {
      processItemElement =
          findChildElementByXpathExpression(processListElement, "//a[.//text() = '" + processName + "']");
    } catch (Exception e) {
    }
    return processItemElement;
  }

  public boolean isProcessDisplay(String processName) {
    return getProcess(processName) !=null;
  }
  
  public boolean isProcessGroupDisplay(String processGroupCharacter) {
    List<WebElement> indexGroup = findListElementsByXpath("//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default']");
    return indexGroup.stream().anyMatch(item -> processGroupCharacter.equals(item.getText()));
  }
  
  public String getProcessNameFromFavoriteProcessList(int index) {
    String id = index + ":process-item-form:process-name";
    WebElement favoriteProcessList = findElementById("process-widget:process-list");
    String name = findChildElementByCssSelector(favoriteProcessList, "span[id*='" + id + "']").getText();
    return name;
  }

  public String getProcessNameFromDefaultProcessList(int index) {
    WebElement defaultProcessList = findElementById("process-widget:user-default-process-list");
    String id = index + ":process-item-form:process-name";
    String name = findChildElementByCssSelector(defaultProcessList, "span[id*='" + id + "']").getText();
    return name;
  }

  public WebElement getEmptyMessageLink() {
    return findElementById("process-widget:add-new-process-message");
  }

  public WebElement getNewProcessDialog() {
    return findElementById("process-widget:add-new-process-dialog");
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

  public boolean isEmptyFavoriteProcesses() {
    return isElementPresent(By.id("process-widget:empty-process-message"));
  }

  public AddNewProcessDialog openNewProcessDialog() {
    click(findChildElementByCssSelector(processWidget, "[id$='show-adding-dialog-commmand']"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    return new AddNewProcessDialog();
  }

  private List<WebElement> findDeleteIcons() {
    waitForElementDisplayed(By.id("process-widget:process-list"), true);
    WebElement processList = findElementById("process-widget:process-list");
    List<WebElement> deleteItems = processList.findElements(By.cssSelector("a[id*='process-delete-link']"));
    return deleteItems;
  }

  public boolean isDeleteProcessItemSelected(int itemIndex) {
    WebElement deleteItem = findDeleteIcons().get(itemIndex);

    WebElement deleteItemCheckBox = findChildElementByClassName(deleteItem, "fa");
    String styleClass = deleteItemCheckBox.getAttribute("class");
    return styleClass.contains("fa-undo");
  }

  public void checkDeleteItem(int itemIndex) {
    List<WebElement> deleteCheckboxes = findDeleteIcons();
    WebElement checkBox = deleteCheckboxes.get(itemIndex);
    click(checkBox);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public int getNumberOfFavoriteUserProcesses() {
    WebElement favoriteProcessList = findElementById("process-widget:process-list");
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
    waitForElementDisplayed(By.id(searchInputField), true, DEFAULT_TIMEOUT);
    loadLiveSearchTextField();
    return liveSearchTextField.isDisplayed();
  }

  public boolean isCompactMode() {
    List<WebElement> findElements = driver.findElements(By.id(searchInputField));
    return findElements.isEmpty();
  }

  public void expand() {
    loadSwitchModeButton();
    switchModeButton.click();
  }

  public void collapse() {
    loadSwitchModeButton();
    switchModeButton.click();
  }

  public void loadSwitchModeButton() {
    switchModeButton = findElementById("process-widget:process-link:process-link-label");
  }

  public void loadLiveSearchTextField() {
    liveSearchTextField = findElementById(searchInputField);
  }

  public void enterSearchKeyword(String keyword) {
    waitForElementDisplayed(By.id(searchInputField), true, DEFAULT_TIMEOUT);
    loadLiveSearchTextField();
    type(liveSearchTextField, keyword);
  }
  
  public boolean hasCreateNewExpressWorkflowLink() {
    return isElementPresent(By.id("process-widget:axon-express-form:create-express-workflow"));
  }
  
  public void moveFavoriteProcess(int processToMoveIndex, int destinationProcessIndex) {
    WebElement processToMove = findElementByCssSelector(".ui-orderlist-item:nth-child(" + processToMoveIndex + ")");
    WebElement destinationProcess =
        findElementByCssSelector(".ui-orderlist-item:nth-child(" + destinationProcessIndex + ")");
    Actions builder = new Actions(driver);
    int x = destinationProcess.getSize().getWidth();
    Action moveProcessSequence =
        builder.clickAndHold(processToMove).moveToElement(destinationProcess, x/2, 0)
        .release(processToMove).build();
    moveProcessSequence.perform();
  }

  public class AddNewProcessDialog {
    private WebElement processDialog;
    private final String PROCESS_DIALOG_ID = "process-widget:add-new-process-dialog";
    private final String SEARCH_ICON_NAME_CSS_SELECTOR = "input[id$='search-icon-name-field']";
    private final String DEFAULT_ICON_CSS_SELECTOR = "span[id$='awesome-icon-display']";
    private final String ICON_CSS_SELECTOR = "a[id$='awesome-icon']";

    private AddNewProcessDialog() {
      processDialog = findElementById(PROCESS_DIALOG_ID);
    }

    public void inputDataForExternalProcess(String processName, String processURL) {
      String processLinkId = "process-widget:process-start-link";
      waitForElementDisplayed(By.id(processLinkId), true);
      String processNameId = "process-widget:external-process-name";
      WebElement processNameInput = findElementById(processNameId);
      type(processNameInput, processName);
      WebElement processURLInput = findElementById(processLinkId);
      type(processURLInput, processURL);
    }

    public void submitForm() {
      WebElement submitButton = findChildElementById(processDialog, "process-widget:add-process-command");
      submitButton.click();
      waitAjaxIndicatorDisappear();
    }

    public void selectIvyProcessByName(String ivyProcessName) {
      findElementByClassName("ui-autocomplete-dropdown").click();
      String processSelector = "tr[data-item-label='" + ivyProcessName + "']";
      waitForElementDisplayed(By.cssSelector(processSelector), true);
      findElementByCssSelector(processSelector).click();
      waitAjaxIndicatorDisappear();
    }

    public boolean isIvyProcessByNameSearchable(String ivyProcessName) {
      findElementByClassName("ui-autocomplete-dropdown").click();
      String processSelector = "tr[data-item-label='" + ivyProcessName + "']";
      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
      return isElementPresent(By.cssSelector(processSelector));
    }
    
    public void clickChangeIconButton() {
      WebElement changeButton = findElementByCssSelector("a[class*='select-awesome-icon-button']");
      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
      changeButton.click();
    }
    
    public void inputSearchedIconName(String keyword) {
      WebElement searchIconNameField = findDisplayedElementBySelector(SEARCH_ICON_NAME_CSS_SELECTOR);
      searchIconNameField.sendKeys(keyword);
      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    }
    
    public int getDisplayedIconAmount() {
    	waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    	List<WebElement> icons = findListElementsByCssSelector(ICON_CSS_SELECTOR);
    	return icons.stream().filter(icon -> !icon.getCssValue("display").equals("none")).collect(Collectors.toList()).size();
    }
    
    public boolean isDefaultIcon() {
    	WebElement element = findDisplayedElementBySelector(DEFAULT_ICON_CSS_SELECTOR);
    	return element.getAttribute("class").contains(AwesomeIcon.DEFAULT_ICON);
    }
  }

  public void selectProcessTypeExternal() {
    WebElement externalCheckboxLabel = findElementByXpath("//label[@for='process-widget:process-type:1']");
    externalCheckboxLabel.click();
    waitAjaxIndicatorDisappear();
  }

  public void loadAllProcessesPage() {
    waitForElementDisplayed(By.id("process-widget:process-link:process-link"), true);
    click(By.id("process-widget:process-link:process-link"));
    this.waitForPageLoaded();
  }
  
  public void goToNewExpressWorkFlowPage() {
    waitForElementDisplayed(By.cssSelector("a[id$='process-widget:process-widget-action-form:create-express-workflow']"), true);
    click(By.cssSelector("a[id$='process-widget:process-widget-action-form:create-express-workflow']"));
    this.waitForPageLoaded();
  }
  
  public void goToCreatedExpressWorkFlowPage() {
    waitForElementDisplayed(By.id("process-widget:express-process-list:0:express-process-item-form:process-item"), true);
    click(By.id("process-widget:express-process-list:0:express-process-item-form:process-item"));
    this.waitForPageLoaded();
  }
}
