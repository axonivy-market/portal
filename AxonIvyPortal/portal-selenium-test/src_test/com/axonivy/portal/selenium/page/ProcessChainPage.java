package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProcessChainPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return ".process-chain-test-button";
  }

  public boolean isEmptyNextStepButtonDisplay() {
    return isElementDisplayed(By.id("form:next-button"));
  }

  public void nextStep() {
    waitForElementClickableThenClick($(By.id("form:next-button")));
  }

  public String getCurrentStep() {
    waitForElementDisplayed(By.id("form:process-chain-circle-horizontal:component-circle-horizontal"), true);
    WebElement step = findElementByCssSelector(".circle-horizontal-process-step.current");
    return step.findElement(By.className("circle-horizontal-step-title")).getText();
  }

}
