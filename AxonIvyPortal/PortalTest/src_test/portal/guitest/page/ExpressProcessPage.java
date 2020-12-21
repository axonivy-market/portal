package portal.guitest.page;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ExpressProcessPage extends TemplatePage {
  private static final String LEFT_POSITION = "LeftPanel";
  private static final String RIGHT_POSITION = "RightPanel";
  private static final String HEADER_POSITION = "Header";
  private static final String FOOTER_POSITION = "Footer";

  private static final String[] POSITIONS = {LEFT_POSITION, RIGHT_POSITION, HEADER_POSITION, FOOTER_POSITION};

  @Override
  protected String getLoadedLocator() {
    return "id('form:prozessSettingsField')";
  }

  public void waitUntilExpressProcessDisplay() {
    waitForElementDisplayed(By.id("form:prozessSettingsField"), true);
  }

  public void fillProcessProperties(boolean isAdhocWF, String processName, String processDescription) {
    chooseTypeOfProcess(isAdhocWF ? 0 : 1);
    type(By.id("form:processname"), processName);
    type(By.id("form:processdescription"), processDescription);
  }

  private void chooseTypeOfProcess(int typeNo) {
    WebElement createExpressWorkflowForm = findElementById("form:prozessSettingsField");
    List<WebElement> processTypes =
        createExpressWorkflowForm.findElements(By.cssSelector("div[class*=ui-radiobutton-box]"));
    if (!processTypes.isEmpty()) {
      processTypes.get(typeNo).click();
    }
  }

  public void createDefaultTask(int taskIndex, String taskName, String taskDescription) {
    waitForElementDisplayed(By.id(String.format("form:processFlowGrid:%d:taskPnl", taskIndex)), true);
    type(By.id(String.format("form:processFlowGrid:%d:subjectfield", taskIndex)), taskName);
    type(By.id(String.format("form:processFlowGrid:%d:descriptionfield", taskIndex)), taskDescription);
    WebElement chooseAssignee = findElementById(String.format("form:processFlowGrid:%d:actorfield", taskIndex));
    chooseAssignee.click();
    waitForElementDisplayed(By.id("chooseAssignee"), true);
    WebElement chooseAssigneeDialog = findElementById("chooseAssignee");
    WebElement saveChooseAssigneeButton =
        chooseAssigneeDialog.findElement(By.cssSelector("button[class*=ui-button-text-icon-left]"));
    saveChooseAssigneeButton.click();
    waitAjaxIndicatorDisappear();
  }

  public void createDefaultInputField(String inputFieldName, String inputFieldLabel) {
    type(By.id("form:createTabs:InputFieldName"), inputFieldName);
    type(By.id("form:createTabs:InputFieldLabel"), inputFieldLabel);
    click(By.id("form:createTabs:create-element-button"));
    waitAjaxIndicatorDisappear();
  }

  public void waitForCreateElementFormDisplayed() {
    waitForElementDisplayed(By.id("form:createTabsField"), true);
  }

  public void waitForChooseAssigneeDialogHidden() {
    waitForElementDisplayed(By.id("chooseAssignee"), false);
  }

  public boolean isCreateElementFormDisplayed() {
    WebElement RequestTab = findElementById("form:createTabsField");
    return RequestTab.isDisplayed();
  }

  public void addNewOrRemoveTask(boolean isAddNewTask) {
    WebElement actionContainer = findElementByCssSelector("table[class*=dynamic-task-action-container]");
    List<WebElement> actions = actionContainer.findElements(By.cssSelector("button"));
    actions.get(isAddNewTask ? 0 : 1).click();
    waitAjaxIndicatorDisappear();
  }

  public void moveNextStep() {
    click(By.id("form:save"));
    waitAjaxIndicatorDisappear();
  }

  public void moveAllElementToDragAndDrogPanel() {
    waitAjaxIndicatorDisappear();
    int size =
        driver.findElements(By.xpath("//div[@id='form:availableFormelements_content']/table/tbody/tr/td/div")).size();
    for (int i = 0; i < size; i++) {
      moveFormElementToPanel(0, getRandomPosition());
    }
  }

  private void moveFormElementToPanel(int index, String position) {
    WebElement formElement = findElementById(String.format("form:availableFormelements:%d:pnl_content", index));
    waitForElementDisplayed(By.id(String.format("form:selectedFormelements%sPanel", position)), true);
    // this click to fix bug can't drag on IE
    formElement.click();
    WebElement panel = findElementById(String.format("form:selectedFormelements%sPanel", position));
    Actions builder = new Actions(driver);
    Action moveProcessSequence = builder.dragAndDrop(formElement, panel).build();
    moveProcessSequence.perform();
    waitAjaxIndicatorDisappear();
  }

  private String getRandomPosition() {
    int idx = new Random().nextInt(POSITIONS.length);
    return (POSITIONS[idx]);
  }

  public boolean hasElementsInPanel() {
    WebElement dynaFormFieldSetElement = findElementById("form:dynaFormFieldSet");
    List<WebElement> inputs = dynaFormFieldSetElement.findElements(By.cssSelector("input"));
    return inputs.size() > 0;
  }

  public void executeWorkflowDirectly() {
    waitForElementDisplayed(By.id("form:execute-workflow-directly-button"), true);
    click(By.id("form:execute-workflow-directly-button"));
  }

  public void startWorkflow() {
    waitForElementDisplayed(By.id("form:start-workflow-button"), true);
    click(By.id("form:start-workflow-button"));
  }

  public void saveWorkflow() {
    waitForElementDisplayed(By.id("form:save-workflow-button"), true);
    click(By.id("form:save-workflow-button"));
  }

  public void waitStartWorkFlowButtonDisplayed() {
    waitForElementDisplayed(By.cssSelector("button[id$='form:start-workflow-button']"), true);
  }

  public void acknowledgedTask() {
    waitForElementDisplayed(By.id("form:acknowledged-button"), true);
    click(By.id("form:acknowledged-button"));
  }

  public void closeWorkflow() {
    waitForElementDisplayed(By.id("form:close-button"), true);
    click(By.id("form:close-button"));
  }

  public void deleteCreatedExpressWorkFlowProcess() {
    waitForElementDisplayed(
        By.id("process-widget:express-process-list:0:express-process-item-form:delete-express-workflow"), true);
    click(By.id("process-widget:express-process-list:0:express-process-item-form:delete-express-workflow"));
    waitForElementDisplayed(By.id("process-widget:remove-express-workflow-dialog"), true);
    waitForElementDisplayed(By.id("process-widget:delete-workflow-form:remove-express-workflow-button"), true);
    click(By.id("process-widget:delete-workflow-form:remove-express-workflow-button"));
  }

  public boolean hasExpressProcessList() {
    boolean hasExpressProcessList = true;
    try {
      findElementById("process-widget:express-process-list");
    } catch (NoSuchElementException ex) {
      hasExpressProcessList = false;
    }
    return hasExpressProcessList;
  }
}
