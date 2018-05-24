package portal.guitest.page;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import ch.xpertline.base.client.Browser;

public class ExpressFormDefinitionPage extends TemplatePage {
  
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
    waitForElementDisplayed(By.id("form:createTabs:createInputFieldTab"), true);
    type(By.id("form:createTabs:InputFieldLabel"), label);
    chooseInputFieldType(inputFieldTypeIndex);
    if(isRequired) {
      click(By.xpath("//label[@for='form:createTabs:InputFieldRequired_input']"));
    }
    click(By.id("form:createTabs:add-input-text-btn"));
    waitAjaxIndicatorDisappear();
  }
  
  public void createTextAreaField(String label, boolean isRequired) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[2]"));
    waitForElementDisplayed(By.id("form:createTabs:createInputAreaTab"), true);
    type(By.id("form:createTabs:InputAreaLabel"), label);
    if(isRequired) {
      click(By.xpath("//label[@for='form:createTabs:InputAreaRequired_input']"));
    }
    click(By.id("form:createTabs:add-text-area-btn"));
    waitAjaxIndicatorDisappear();
  }
  
  public void createCheckboxField(String label, int numberOfSelection) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[3]"));
    waitForElementDisplayed(By.id("form:createTabs:createManyCheckboxTab"), true);
    type(By.id("form:createTabs:ManyCheckboxLabel"), label);
    addCheckboxOptions(numberOfSelection);
    click(By.id("form:createTabs:add-checkbox-btn"));
    waitAjaxIndicatorDisappear();
  }
  
  public void createRadioButtonField(String label, int numberOfOption) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[4]"));
    waitForElementDisplayed(By.id("form:createTabs:createOneRadioGrid"), true);
    type(By.id("form:createTabs:OneRadioLabel"), label);
    addRadioOptions(numberOfOption);
    click(By.id("form:createTabs:add-radio-btn"));
    waitAjaxIndicatorDisappear();
  }
  
  public void createUploadComponent(String label) {
    click(By.xpath("//*[@id='form:createTabs']/ul/li[5]"));
    waitForElementDisplayed(By.id("form:createTabs:createFileUploadTab"), true);
    type(By.id("form:createTabs:FileUploadLabel"), label);
    click(By.id("form:createTabs:add-upload-file-btn"));
    waitAjaxIndicatorDisappear();
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
    driver.manage().window().setSize(new Dimension(2000, 1000));
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
    int numberOfUploadFile = previewDialog.findElements(By.id("document-table-div")).size();
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
    ensureNoBackgroundRequest();
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
