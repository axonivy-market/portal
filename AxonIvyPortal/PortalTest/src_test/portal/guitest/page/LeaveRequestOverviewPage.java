package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.userexamples.page.LeaveRequestPage;

public class LeaveRequestOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('leave-request-header')";
  }

  public String getHearText() {
    WebElement element = findElementByCssSelector("[id$='leave-request-header']");
    return element.getText();
  }
  
  public String getStepName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:process-step']", index));
    return element.getText();
  }
  
  public LeaveRequestPage start() {
    clickByCssSelector("button[id$='start']");
    waitForElementDisplayed(By.cssSelector("[id$='leave-request']"), true);
    return new LeaveRequestPage();
  }
}
