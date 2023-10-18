package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;

public class AdditionalCaseDetailsPage extends TemplatePage {
  
  private static final String TABLE_ROWS_PATH = "div[id$='additional-case-detail-table'] tbody>tr";
  
  public int countFields() {
      switchToIFrameOfTask();
      waitForElementDisplayed(By.cssSelector("div[id$='additional-case-detail-table']"), true);
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