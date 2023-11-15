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

import com.axonivy.portal.selenium.common.Sleeper;
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

  public void switchToUploadTab() {
    switchToElementTab("File upload");
    $("[id='form:create-tabs:create-file-upload-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void switchToElementTab(String elementName) {
    $("[id='form:create-tabs']").shouldBe(appear, DEFAULT_TIMEOUT).$$("li.ui-tabs-header").asFixedIterable()
    .stream().filter(WebElement::isDisplayed)
    .filter(formElem -> formElem.$("span.form-definition-item-icon").getText().contentEquals(elementName))
    .findFirst().get().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    waitForAjaxIndicatorDisappeared();
  }

  public void createUploadComponent(String label) {
    switchToUploadTab();
    $("[id='form:create-tabs:file-upload-label']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(label);
    $("[id='form:create-tabs:add-upload-file-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitForAjaxIndicatorDisappeared();
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
      $("[id$='form:available-form-elements:" + i + ":pnl']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    }
  }
  
  public void countElementPrepareToDrag(int size) {
    $$(By.xpath("//div[@id='form:available-form-elements_content']/ul/li")).shouldBe(CollectionCondition.size(size), DEFAULT_TIMEOUT);
  }

  private void moveFormElementToPanel(long index, String position) {
    //TODO Need to be fixed - Workaround for scroll-bar issue
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    Sleeper.sleep(200); // Wait for JS executed successfully

    var formElementSelector = String.format("[id$='form:available-form-elements:%d:pnl_content']", index);
    // If elements is FileUpload, move to footer
    if (formElementIsFileUpload($(formElementSelector).shouldBe(appear, DEFAULT_TIMEOUT))) {
      position = FOOTER_POSITION;
    }

    var panelSelector = String.format("[id='form:selected-form-elements-%s-panel']",position);

    Actions builder = new Actions(WebDriverRunner.getWebDriver());
    Action moveProcessSequence = builder.dragAndDrop($(formElementSelector), $(panelSelector)).build();
    moveProcessSequence.perform();
//    waitForAjaxIndicatorDisappeared();
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
    waitForAjaxIndicatorDisappeared();
  }

  public WebElement getPageElement() {
    return $(".portal-layout-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void executeWorkflow() {
    waitForElementClickableThenClick(By.id("execute-button"));
    waitForElementDisplayed(By.id("form:dynaform-fieldset"), true);
  }
  
  @SuppressWarnings("deprecation")
  public void createTextInputField(String label, int inputFieldTypeIndex, boolean isRequired) {
    waitForElementClickableThenClick(By.xpath("//*[@id='form:create-tabs']/ul/li[@role='tab'][1]"));
    waitForElementDisplayed(By.id("form:create-tabs:create-input-field-tab"), true);
    $(By.id("form:create-tabs:input-field-label")).sendKeys(label);
    chooseInputFieldType(inputFieldTypeIndex);
    if (isRequired) {
      waitForElementClickableThenClick(By.cssSelector("div[id='form:create-tabs:input-field-required']"));
    }
    waitForElementClickableThenClick(By.id("form:create-tabs:add-input-text-btn"));
//    waitAjaxIndicatorDisappear();
//    ensureNoBackgroundRequest();
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
}
