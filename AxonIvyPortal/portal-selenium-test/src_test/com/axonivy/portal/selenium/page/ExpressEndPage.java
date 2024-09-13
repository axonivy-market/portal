package com.axonivy.portal.selenium.page;

import org.openqa.selenium.By;

public class ExpressEndPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id$='form:close-button']";
  }

  public void finish() {
    waitForElementClickableThenClick(By.id("form:close-button"));
  }
}
