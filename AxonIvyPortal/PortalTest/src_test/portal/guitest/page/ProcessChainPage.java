package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProcessChainPage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return COMPONENT_PAGE_LOCATOR;
  }
  
  public boolean isEmptyNextStepButtonDisplay() {
    return isElementDisplayed(By.id("form:next-button"));
  }
  
  public void nextStep() {
    waitForElementDisplayed(By.id("form:next-button"), true);
    click(findElementById("form:next-button"));
  }
  
  public String getCurrentStep() {
    waitForElementDisplayed(By.id("form:process-chain-circle-horizontal:component-circle-horizontal"), true);
    WebElement step = findElementByCssSelector(".circle-horizontal-process-step.current");
    return step.findElement(By.className("circle-horizontal-step-title")).getText();
  }

}
