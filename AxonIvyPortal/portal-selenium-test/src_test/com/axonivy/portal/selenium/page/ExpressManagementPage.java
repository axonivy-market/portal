package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;

public class ExpressManagementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:express-management-component:express-management-form']";
  }

  public void openImportDialog() {
    $("button[id$=':express-management-form:import-express-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    $("[id$=':import-express-form:express-process-upload_label']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getImportExpressDialog() {
    return $("[id$=':express-management-component:import-express-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectJSONFile(String pathToFile) {
    $("*[id$=':express-process-upload_input']").sendKeys(pathToFile);
    $$(".ui-fileupload-upload").shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
  }

  public void clickOnDeployExpress() {
    $("[id='admin-setting-component:adminTabView:express-management-component:import-express-dialog']")
        .$(".ui-fileupload-upload").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:adminTabView:express-management-component:import-express-form:impress-export-output'] pre.express-import-result")
        .shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnCloseButton() {
    $("*[id$=':close-import-express']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:adminTabView:express-management-component:import-express-dialog']")
        .shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void clickOnSelectAllExpresses() {
    $("[id='admin-setting-component:adminTabView:express-management-component:express-management-form:express-workflow-summary-table_head_checkbox']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickOnExportButton() {
    $("[id$=':express-management-component:express-management-form:export-express-btn']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:adminTabView:express-management-component:export-express-dialog']").shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public WebElement getExportExpressDialog() {
    return $("[id$=':express-management-component:export-express-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void deployExpressFile() {
    clickOnDeployExpress();
    clickOnCloseButton();
  }

  public String getUploadMessage() {
    SelenideElement messageContainer = $("div[class$='ui-fileupload-messages'] span[class$='ui-messages-error-summary']").shouldBe(appear, DEFAULT_TIMEOUT);
    return messageContainer.getText();
  }
}
