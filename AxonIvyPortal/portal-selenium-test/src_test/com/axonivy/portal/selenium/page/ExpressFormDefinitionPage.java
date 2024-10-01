package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;

public class ExpressFormDefinitionPage extends TemplatePage {

  private static final String LEFT_POSITION = "leftpanel";
  private static final String RIGHT_POSITION = "rightpanel";
  private static final String HEADER_POSITION = "header";
  private static final String FOOTER_POSITION = "footer";
  private static final String[] POSITIONS = {LEFT_POSITION, HEADER_POSITION, FOOTER_POSITION};

  @Override
  protected String getLoadedLocator() {
    return "[id='form:create-tabs']";
  }

  public void createUploadComponent(String label) {
    clickOnFormCreationTabIndex(5);
    $("input[id$='form:create-tabs:file-upload-label']").sendKeys(label);
    $("[id='form:create-tabs:add-upload-file-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("input[id$='form:create-tabs:file-upload-label']").shouldBe(Condition.empty);
  }

  public void moveAllElementToDragAndDrogPanel() {
    int size = $$(By.xpath("//div[@id='form:available-form-elements_content']/ul/li")).size();
    long startIndex = size - 1;

    for (long i = startIndex; i >= 0; i--) {
      String panelSelector = "[id='" + String.format("form:available-form-elements:%d:pnl", i) + "']";
      $(panelSelector).shouldBe(appear, DEFAULT_TIMEOUT);

      if (i == startIndex) {
        moveFormElementToPanel(i, RIGHT_POSITION);
      } else {
        moveFormElementToPanel(i, getRandomPosition());
      }
      $$(panelSelector).shouldBe(CollectionCondition.empty);
    }
  }

  public void countElementPrepareToDrag(int size) {
    $$(By.xpath("//div[@id='form:available-form-elements_content']/ul/li")).shouldBe(CollectionCondition.size(size),
        DEFAULT_TIMEOUT);
  }

  private void moveFormElementToPanel(long index, String position) {
    // TODO Need to be fixed - Workaround for scroll-bar issue
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    var formElementSelector = String.format("[id$='form:available-form-elements:%d:pnl_content']", index);
    // If elements is FileUpload, move to footer
    if (formElementIsFileUpload($(formElementSelector).shouldBe(appear, DEFAULT_TIMEOUT))) {
      position = FOOTER_POSITION;
    }

    var panelSelector = String.format("[id='form:selected-form-elements-%s-panel']", position);

    Actions builder = new Actions(WebDriverRunner.getWebDriver());
    Action moveProcessSequence = builder.dragAndDrop($(formElementSelector), $(panelSelector)).build();
    moveProcessSequence.perform();
  }

  private boolean formElementIsFileUpload(WebElement formElement) {
    WebElement icon = formElement.findElement(By.tagName("img"));
    return icon.getAttribute("src").contains("FileUpload");
  }

  private String getRandomPosition() {
    int idx = new Random().nextInt(POSITIONS.length);
    return (POSITIONS[idx]);
  }

  public void nextStep() {
    $("[id='next-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void waitForEmailEditorDisplayed() {
    $("[id='form:information-email:email-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getPageElement() {
    return $(".portal-layout-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void executeWorkflow() {
    waitForElementClickableThenClick(By.id("execute-button"));
    waitForElementDisplayed(By.id("form:dynaform-fieldset"), true);
  }

  public void createTextInputField(String label, int inputFieldTypeIndex, boolean isRequired) {
    waitForElementClickableThenClick(By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][1]"));
    waitForElementDisplayed(By.id("form:create-tabs:create-input-field-tab"), true);
    $(By.id("form:create-tabs:input-field-label")).sendKeys(label);
    chooseInputFieldType(inputFieldTypeIndex);
    if (isRequired) {
      waitForElementClickableThenClick(By.cssSelector("div[id='form:create-tabs:input-field-required']"));
    }
    waitForElementClickableThenClick(By.id("form:create-tabs:add-input-text-btn"));
  }

  private void chooseInputFieldType(int inputTypeIndex) {
    waitForElementClickableThenClick(By.id("form:create-tabs:input-field-type_label"));
    waitForElementDisplayed(By.id("form:create-tabs:input-field-type_panel"), true);
    waitForElementClickableThenClick(By.id(String.format("form:create-tabs:input-field-type_%d", inputTypeIndex)));
  }

  public void finishWorkflow() {
    waitForElementClickableThenClick(By.id("finish-button"));
    new NewDashboardPage().isDisplayed();
  }

  public NewDashboardPage save() {
    waitForElementClickableThenClick(By.id("save-button"));
    return new NewDashboardPage();
  }

  public NewDashboardPage cancel() {
    waitForElementClickableThenClick(By.id("cancel-button"));
    waitForElementDisplayed(By.id("yes-button"), true);
    waitForElementClickableThenClick(By.id("yes-button"));
    return new NewDashboardPage();
  }

  public void createRadioButtonField(String label, int numberOfOption) {
    clickOnFormCreationTabIndex(4);
    waitForElementDisplayed(By.id("form:create-tabs:one-radio-label"), true);
    addRadioOptions(numberOfOption);
    $("input[id$='form:create-tabs:one-radio-label']").sendKeys(label);
    waitForElementClickableThenClick(By.id("form:create-tabs:add-radio-btn"));
    $("input[id$='form:create-tabs:one-radio-label']").shouldBe(Condition.empty);
  }

  private void addRadioOptions(int numberOfOptions) {
    for (int i = 1; i <= numberOfOptions; i++) {
      waitForElementClickableThenClick(By.id("form:create-tabs:one-radio-options:add-radio-option-btn"));
      $(By.xpath(String.format("//*[@id='form:create-tabs:one-radio-options_data']/tr[%d]/td/input", i)))
          .sendKeys("Radio " + i);
    }
  }

  public void createCheckboxField(String label, int numberOfSelection) {
    clickOnFormCreationTabIndex(3);
    waitForElementDisplayed(By.id("form:create-tabs:many-checkbox-options"), true);
    $("input[id='form:create-tabs:many-checkbox-label']").sendKeys(label);
    addCheckboxOptions(numberOfSelection);
    waitForElementClickableThenClick(By.id("form:create-tabs:add-checkbox-btn"));
    $("input[id='form:create-tabs:many-checkbox-label']").shouldBe(Condition.empty);
  }

  private void addCheckboxOptions(int numberOfSelection) {
    for (int i = 1; i <= numberOfSelection; i++) {
      waitForElementClickableThenClick(By.id("form:create-tabs:many-checkbox-options:add-checkbox-option-btn"));
      $(By.xpath(String.format("//*[@id='form:create-tabs:many-checkbox-options_data']/tr[%d]/td/input", i)))
          .sendKeys("Option " + i);
    }
  }

  public void createTextAreaField(String label, boolean isRequired) {
    clickOnFormCreationTabIndex(2);
    waitForElementDisplayed(By.id("form:create-tabs:create-input-area-tab"), true);
    $(By.id("form:create-tabs:input-area-label")).sendKeys(label);
    if (isRequired) {
      waitForElementClickableThenClick(By.cssSelector("div[id='form:create-tabs:input-area-required']"));
    }
    waitForElementClickableThenClick(By.id("form:create-tabs:add-text-area-btn"));
    $(By.id("form:create-tabs:input-area-label")).shouldBe(Condition.empty);
  }

  public void createCheckboxFieldWithDataProvider(String label) {
    fillDataForCheckboxProvider(label);
    waitForElementClickableThenClick(By.id("form:create-tabs:add-checkbox-btn"));
    $(By.id("form:create-tabs:many-checkbox-label")).shouldBe(Condition.empty);
  }

  public void fillDataForCheckboxProvider(String label) {
    clickOnFormCreationTabIndex(3);
    waitForElementDisplayed(By.id("form:create-tabs:create-many-checkbox-tab"), true);
    waitForElementClickableThenClick(By.id("form:create-tabs:DataProvider_label"));
    waitForElementClickableThenClick(By.xpath("//*[@data-label='TestDataProviderForPortalExpress']"));
    waitForElementDisplayed(By.cssSelector("div[id$='value-checkbox-label']"), false);
    waitForElementDisplayed(By.id("form:create-tabs:many-checkbox-label"), true);
    $(By.id("form:create-tabs:many-checkbox-label")).sendKeys(label);
  }

  public int countNumberOfElementsInPreviewDialog() {
    waitForElementClickableThenClick(By.id("form:show-preview-button"));
    waitForElementDisplayed(By.id("form:preview-dialog"), true);
    WebElement previewDialog = findElementById("form:preview-dialog");
    int numberOfInput = previewDialog.findElements(By.xpath("//table[@id='form:dyna-form']//input")).size();
    int numberOfTextArea = previewDialog.findElements(By.xpath("//table[@id='form:dyna-form']//textarea")).size();
    int numberOfUploadFile =
        previewDialog.findElements(By.xpath("//div[contains(@id,'fileUploadComponent:document-table')]")).size();
    return numberOfInput + numberOfTextArea + numberOfUploadFile;
  }

  public int countNumberOfSteps() {
    return driver.findElements(By.xpath("//div[@id='defined-task-container']//button")).size();
  }

  public void inputMailRecipient(String content) {
    findElementById("form:information-email:email-recipients").sendKeys(content);
  }

  public void inputMailSubject(String content) {
    findElementById("form:information-email:email-subject").sendKeys(content);
  }

  public void inputMailContent(String content) {
    ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(
        "document.querySelector(\"input[name='form:information-email:email-content_input'\").value='" + content + "';");
  }

  public void executeWorkflowAndWaitForUserTaskWithEmailDisplay() {
    waitForElementClickableThenClick(By.id("execute-button"));
    waitForElementDisplayed(By.id("task-form:task-view:dyna-form-fieldset"), true);
  }

  private void clickOnFormCreationTabIndex(int tabIndex) {
    WaitHelper.waitForActionComplete("#form\\:create-tabs ul", () -> waitForElementClickableThenClick(
        By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][" + tabIndex + "]")));
  }

  public void switchToCheckBoxTab() {
    waitForElementDisplayed(By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][3]"), true);
    $(By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][3]")).shouldBe(getClickableCondition()).click();
    WaitHelper.assertTrueWithWait(() -> {
      var checkboxTab = $(By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][3]"));
      return checkboxTab.getAttribute(CLASS_PROPERTY).contains("ui-state-active");
    });
  }

}
