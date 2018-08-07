package portal.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
      e.printStackTrace();
    }
    return processItemElement;
  }

  public WebElement getEmptyMessageLink() {
    return findElementById("process-widget:add-new-process-message");
  }

  public WebElement getNewProcessDialog() {
    return findElementById("process-widget:add-new-process-dialog");
  }

  public void clickDeletionSwitchLink() {
    String deleteProcessLinkId = "process-widget:process-widget-footer:deletion-switch-command";
    waitForElementDisplayed(By.id(deleteProcessLinkId), true, DEFAULT_TIMEOUT);
    click(findElementById(deleteProcessLinkId));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public void clickDeleteProcess() {
    WebElement deleteProcessLink =
        findChildElementById(processWidget, "process-widget:process-widget-footer:delete-process-command");
    click(deleteProcessLink);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public boolean isEmptyFavoriteProcesses() {
    return isElementPresent(By.id("process-widget:empty-process-message"));
  }

  public AddNewProcessDialog openNewProcessDialog() {
    if (isEmptyFavoriteProcesses()) {
      click(getEmptyMessageLink());
    } else {
      click(findChildElementById(processWidget, "process-widget:process-widget-footer:show-adding-dialog-commmand"));
    }
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    return new AddNewProcessDialog();
  }

  private List<WebElement> findDeleteProcessCheckboxes() {
    waitForElementDisplayed(By.id("process-widget:process-list"), true);
    WebElement processList = findElementById("process-widget:process-list");
    List<WebElement> divElements = processList.findElements(By.tagName("div"));
    List<WebElement> deleteItems = new ArrayList<WebElement>();
    for (WebElement webElement : divElements) {
      if (webElement.getAttribute("class").equalsIgnoreCase("ui-chkbox ui-widget")) {
        deleteItems.add(webElement);
      }
    }
    return deleteItems;
  }

  public boolean isDeleteProcessItemSelected(int itemIndex) {
    WebElement deleteItem = findDeleteProcessCheckboxes().get(itemIndex);

    WebElement deleteItemCheckBox = findChildElementByXpathExpression(deleteItem, "./div[position()=2]");
    String styleClass = deleteItemCheckBox.getAttribute("class");
    return styleClass.contains("ui-state-active");
  }

  public void checkDeleteItem(int itemIndex) {
    List<WebElement> deleteCheckboxes = findDeleteProcessCheckboxes();
    WebElement checkBox = deleteCheckboxes.get(itemIndex);
    click(checkBox);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public int getNumberOfUserProcesses() {
    List<WebElement> processes = findListElementsByCssSelector("a[id*='process-widget:process-list-items']");
    return processes.size();

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
    switchModeButton = findElementById("process-widget:switch-mode-command");
  }

  public void loadLiveSearchTextField() {
    liveSearchTextField = findElementById(searchInputField);
  }

  public class AddNewProcessDialog {
    private WebElement processDialog;
    private static final String PROCESS_DIALOG_ID = "process-widget:add-new-process-dialog";

    private AddNewProcessDialog() {
      processDialog = findElementById(PROCESS_DIALOG_ID);
    }

    public void inputData(String processName, String processURL) {
      String processNameInputSelector = "input[id*='process-widget:new-process-name']";
      waitForElementDisplayed(By.cssSelector(processNameInputSelector), true);
      String processLinkId = "process-widget:process-start-link";
      waitForElementDisplayed(By.id(processLinkId), true);
      WebElement processNameInput = findElementByCssSelector(processNameInputSelector);
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
      String processSelector = "tr[data-item-value='" + ivyProcessName + "']";
      waitForElementDisplayed(By.cssSelector(processSelector), true);
      findElementByCssSelector(processSelector).click();
      waitAjaxIndicatorDisappear();
    }
  }
}
