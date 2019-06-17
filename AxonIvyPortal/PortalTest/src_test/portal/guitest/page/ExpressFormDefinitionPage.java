package portal.guitest.page;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;

import ch.xpertline.base.client.Browser;

public class ExpressFormDefinitionPage extends TemplatePage {
  
  private static final int TIME_OUT = 60;
  private static final String LEFT_POSITION = "LeftPanel";
  private static final String RIGHT_POSITION = "RightPanel";
  private static final String HEADER_POSITION = "Header";
  private static final String FOOTER_POSITION = "Footer";
  private static final String[] POSITIONS = {LEFT_POSITION, RIGHT_POSITION, HEADER_POSITION, FOOTER_POSITION};
  
  @Override
  protected String getLoadedLocator() {
    return "id('form:createTabsField')";
  }
  
  public void createTextInputField(String label, int inputFieldTypeIndex, boolean isRequired) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[1]"));
    waitForElementDisplayed(By.id("form:createTabs:createInputFieldTab"), true, TIME_OUT);
    type(By.id("form:createTabs:InputFieldLabel"), label);
    chooseInputFieldType(inputFieldTypeIndex);
    if(isRequired) {
      click(By.xpath("//label[@for='form:createTabs:InputFieldRequired_input']"));
    }
    click(By.id("form:createTabs:add-input-text-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void createTextAreaField(String label, boolean isRequired) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[2]"));
    ensureNoBackgroundRequest();
    Sleeper.sleepTight(3000);
    waitForElementDisplayed(By.id("form:createTabs:createInputAreaTab"), true, TIME_OUT);
    type(By.id("form:createTabs:InputAreaLabel"), label);
    if(isRequired) {
      click(By.xpath("//label[@for='form:createTabs:InputAreaRequired_input']"));
    }
    click(By.id("form:createTabs:add-text-area-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void createCheckboxField(String label, int numberOfSelection) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[3]"));
    ensureNoBackgroundRequest();
    Sleeper.sleepTight(3000);
    waitForElementDisplayed(By.id("form:createTabs:createManyCheckboxTab"), true, TIME_OUT);
    type(By.id("form:createTabs:ManyCheckboxLabel"), label);
    addCheckboxOptions(numberOfSelection);
    Sleeper.sleepTight(1000);
    click(By.id("form:createTabs:add-checkbox-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void createCheckboxFieldWithDataProvider(String label) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[3]"));
    ensureNoBackgroundRequest();
    Sleeper.sleepTight(3000);
    waitForElementDisplayed(By.id("form:createTabs:createManyCheckboxTab"), true, TIME_OUT);
    click(By.id("form:createTabs:DataProvider_label"));
    Sleeper.sleepTight(1000);
    click(By.xpath("//*[@data-label='TestDataProviderForPortalExpress']"));
    type(By.id("form:createTabs:ManyCheckboxLabel"), label);
    Sleeper.sleepTight(1000);
    click(By.id("form:createTabs:add-checkbox-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void createRadioButtonField(String label, int numberOfOption) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[4]"));
    ensureNoBackgroundRequest();
    Sleeper.sleepTight(3000);
    waitForElementDisplayed(By.id("form:createTabs:OneRadioLabel"), true, TIME_OUT);
    type(By.id("form:createTabs:OneRadioLabel"), label);
    Sleeper.sleepTight(2000);
    addRadioOptions(numberOfOption);
    click(By.id("form:createTabs:add-radio-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  public void createUploadComponent(String label) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[5]"));
    ensureNoBackgroundRequest();
    Sleeper.sleepTight(3000);
    waitForElementDisplayed(By.id("form:createTabs:createFileUploadTab"), true, TIME_OUT);
    type(By.id("form:createTabs:FileUploadLabel"), label);
    click(By.id("form:createTabs:add-upload-file-btn"));
    waitAjaxIndicatorDisappear();
    ensureNoBackgroundRequest();
  }
  
  private void addRadioOptions(int numberOfOptions) {
    for(int i=1; i<= numberOfOptions; i++) {
      click(By.id("form:createTabs:OneRadioOptions:add-radio-option-btn"));
      waitAjaxIndicatorDisappear();
      type(By.xpath(String.format("//*[@id='form:createTabs:OneRadioOptions_data']/tr[%d]/td/input", i)), "Radio " + i);
    }
  }
  
  private void addCheckboxOptions(int numberOfSelection){
    for(int i=1; i<= numberOfSelection; i++) {
      click(By.id("form:createTabs:ManyCheckboxOptions:add-checkbox-option-btn"));
      waitAjaxIndicatorDisappear();
      type(By.xpath(String.format("//*[@id='form:createTabs:ManyCheckboxOptions_data']/tr[%d]/td/input", i)), "Option " + i);
    }
  }
  
  private void chooseInputFieldType(int inputTypeIndex) {
    click(By.id("form:createTabs:InputFieldType_label"));
    waitForElementDisplayed(By.id("form:createTabs:InputFieldType_panel"), true);
    click(By.id(String.format("form:createTabs:InputFieldType_%d", inputTypeIndex)));
  }
  
  public void moveAllElementToDragAndDrogPanel() {
    int size = driver.findElements(By.xpath("//div[@id='form:availableFormelements_content']/table/tbody/tr")).size();
    for(int i=size-1; i>= 0; i--){
      moveFormElementToPanel(i, getRandomPosition());
    }
  }
  
  public void moveFormElementToPanel(int index, String position) {
    WebElement formElement = findElementById(String.format("form:availableFormelements:%d:pnl_content", index));
    //If elements is FileUpload, move to footer
    if(formElementIsFileUpload(formElement)){
      position = FOOTER_POSITION;
    }
    WebElement panel = findElementById(String.format("form:selectedFormelements%sFieldSet", position));
    Actions builder = new Actions(driver);
    Action moveProcessSequence =
        builder.clickAndHold(formElement).moveToElement(panel).release(formElement).build();
    moveProcessSequence.perform();
    waitAjaxIndicatorDisappear();
  }
  
  private String getRandomPosition(){
    int idx = new Random().nextInt(POSITIONS.length);
    return (POSITIONS[idx]);
  }
  
  private boolean formElementIsFileUpload(WebElement formElement){
    WebElement icon = formElement.findElement(By.tagName("img"));
    return icon.getAttribute("src").contains("FileUpload");
  }
  
  public int countNumberOfElementsInPreviewDialog() {
    click(By.id("form:show-preview-button"));
    waitForElementDisplayed(By.id("form:preview-dialog"), true);
    WebElement previewDialog = findElementById("form:preview-dialog");
    //if we have radio button or checkbox, remember multiply with number of options
    //Example: we have 1 checkbox with 2 options, 1 radio with 3 options. Total we have 5 inputs
    int numberOfInput = previewDialog.findElements(By.xpath("//table[@id='form:dynaForm']//input")).size();
    int numberOfTextArea = previewDialog.findElements(By.xpath("//table[@id='form:dynaForm']//textarea")).size();
    int numberOfUploadFile = previewDialog.findElements(By.xpath("//div[contains(@id,'fileUploadComponent:document-table')]")).size();
    return numberOfInput + numberOfTextArea + numberOfUploadFile;
  }
  
  public int countNumberOfSteps() {
    return driver.findElements(By.xpath("//div[@id='defined-task-container']//button")).size();
  }

  public void saveWorkflow() {
    click(By.id("save-button"));
  }

  public void finishWorkflow() {
    click(By.id("finish-button"));
  }
  
  public void executeWorkflow() {
    click(By.id("execute-button"));
    waitAjaxIndicatorDisappear();
  }

  public void nextStep() {
    click(By.id("next-button"));
    ensureNoBackgroundRequest();
  }
  
  public void inputMailSubject(String content) {
    findElementById("form:information-email:email-subject").sendKeys(content);
  }

  public void inputMailContent(String content) {
    WebDriver driver = Browser.getBrowser().getDriver();
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("document.querySelector(\"input[name='form:information-email:email-content_input'\").value='"+ content +"';");
  }
}
