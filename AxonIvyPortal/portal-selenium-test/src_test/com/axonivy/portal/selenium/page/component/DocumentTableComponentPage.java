package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

public class DocumentTableComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "div[id='form:document-table-component']";
  }

  public void uploadSampleDocument(String pathToFile) {
    $("[id$=':document-upload_input']").sendKeys(pathToFile);
    getDocuments().get(0).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("span[class$='ui-messages-info-summary']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private ElementsCollection getDocuments() {
    return $("[id$=':document-table-component:document-table']").find("table tbody").$$("tr");
  }

  public void waitForDocumentTableComponentPageLoaded() {
    $("[id$=':document-table-component']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public int countDocuments() {
    return getDocuments().size();
  }

  public void clickPreviewButton() {
    $("[id$=':document-preview']").click();
  }

  public void waitForPreviewDialog() {
    $("[id$=':preview-document-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public boolean isPreviewContentDisplayed() {
    return $("object[type='application/pdf']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).isDisplayed();
  }

  public void closePreviewDialog() {
    $("[id$=':preview-document-dialog'] .ui-dialog-titlebar-close").click();
  }
}
