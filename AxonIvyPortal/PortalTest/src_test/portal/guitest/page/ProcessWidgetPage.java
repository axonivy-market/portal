package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

public class ProcessWidgetPage extends TemplatePage {

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
    click(processItemElement);
  }
  
  public WebElement getProcess(String processName) {
    WebElement processListElement = findElementById(processWidgetId + ":process-list");
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
  
  public boolean isNoProcessFound() {
    return isElementPresent(By.xpath("//span[@id='process-widget:no-found-processes']"));
  }
  
  public boolean isProcessGroupDisplay(String processGroupCharacter) {
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

  public void expand() {
    loadSwitchModeButton();
    switchModeButton.click();
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
    driver.manage().window().setSize(new Dimension(3000, 2000));
    WebElement processToMove = findElementByCssSelector(".ui-orderlist-item:nth-child(" + processToMoveIndex + ")");
    WebElement destinationProcess =
        findElementByCssSelector(".ui-orderlist-item:nth-child(" + destinationProcessIndex + ")");
    Actions builder = new Actions(driver);
    Action moveProcessSequence =
        builder.clickAndHold(processToMove).moveToElement(destinationProcess).release(processToMove).build();
    moveProcessSequence.perform();
  }
  
  public class AddNewExternalLinkDialog {
    private final String EXTERNAL_LINK_NAME_INPUT_CSS_SELECTOR = "input[id$='external-link-name']";
    private final String EXTERNAL_LINK_INPUT_CSS_SELECTOR = "input[id$='external-link']";
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
        WebElement externalCheckboxLabel = findElementByXpath("//label[@for='" + processWidgetId + ":external-link-type-radio:1']");
        externalCheckboxLabel.click();
      }
    }

    public void submitForm() {
      WebElement submitButton = findElementByCssSelector(ADD_EXTERNAL_LINK_BUTTON_INPUT_CSS_SELECTOR);
      submitButton.click();
      waitAjaxIndicatorDisappear();
    }
  }
  
  public void openExpressPage() {
    waitForElementDisplayed(By.id(processWidgetId + ":create-express-workflow"), true);
    click(By.id("process-widget:create-express-workflow"));
  }
  
  public boolean isProcessEmpty() {
    return isElementDisplayed(By.id("search-results-tabview:process-results:full-process-empty-message"));
  }
  
  public boolean hasCreateNewExpressWorkflowLink() {
    return isElementPresent(By.id("process-widget:create-express-workflow"));
  }
  
  public void deleteExternalLinkByFieldsetIndexAndIndex(int fieldsetIndex, int index) {
    String deleteExternalLinkIconCssSelector = String.format("a[id$='%d:processes:%d:process-item-form:delete-external-link']", fieldsetIndex, index);
    WebElement deleteExternalIcon = findElementByCssSelector(deleteExternalLinkIconCssSelector);
    deleteExternalIcon.click();
    waitAjaxIndicatorDisappear();
    findElementByCssSelector("button[id$='delete-external-link-btn']").click();
    waitAjaxIndicatorDisappear();
  }

  
}
