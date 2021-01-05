package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LendingDetailPage extends TemplatePage {
  int index;
  @Override
  protected String getLoadedLocator() {
    return "id('lending-detail-header')";
  }
  
  public LendingDetailPage(int index) {
    this.index = index;
  }

  public String getHearText() {
    WebElement element = findElementByCssSelector("[id$='lending-detail-header']");
    return element.getText();
  }
  
  public String getStageName() {
    WebElement element = findElementByCssSelector(String.format("[id$='stage-%d-name']", index));
    return element.getText();
  }
  
  public String getProcessName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:process-name']", index));
    return element.getText();
  }
  
  public String getSideStepName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:side-step-name']", index));
    return element.getText();
  }
  
  public LendingOverviewPage navigateToLendingOverview() {
    WebElement element = findElementByCssSelector("[id$='back-to-case-map']");
    element.click();
    waitForElementDisplayed(By.cssSelector("[id$='lending-header']"), true);
    return new LendingOverviewPage();
  }
  
  public LendingDetailPage navigateToPreviousDetail() {
    WebElement element = findElementByCssSelector("[id$='previous-stage']");
    element.click();
    waitForElementDisplayed(By.cssSelector(String.format("[id$='stage-%d-name']", index - 1)), true);
    return new LendingDetailPage(index - 1);
  }
  
  @SuppressWarnings("deprecation")
  public LendingDetailPage navigateToNextDetail() {
    WebElement element = findElementByCssSelector("[id$='next-stage']");
    element.click();
    waitForElementDisplayed(By.cssSelector(String.format("[id$='stage-%d-name']", index + 1)), true);
    waitAjaxIndicatorDisappear();
    return new LendingDetailPage(index + 1);
  }
}
