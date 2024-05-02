package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

public class AdditionalCaseDetailsPage extends TemplatePage {

  private static final String TABLE_ROWS_PATH = "div[id$='additional-case-detail-table'] tbody>tr";

  public int countFieldsInIframe() {
    $("[id='iFrame']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    switchToIFrameOfTask();
    return driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
  }

  public int countFields() {
    return driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
  }

  public String getAdditionalFieldContentOfFirstRow() {
    return findElementByCssSelector("#additional-case-detail-table\\:0\\:value").getText();
  }

  @Override
  protected String getLoadedLocator() {
    return ".js-layout-content";
  }
}
