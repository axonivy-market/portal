package portal.guitest.page;

import org.openqa.selenium.WebElement;

public class ExampleOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('example-header')";
  }
  
  public LeaveRequestOverviewPage openLeaveRequestOverview() {
    WebElement element = findElementByCssSelector("[id$='0:more-info']");
    element.click();
    waitAjaxIndicatorDisappear();
    return new LeaveRequestOverviewPage();
  }
  
  public LendingOverviewPage openLendingOverview() {
    WebElement element = findElementByCssSelector("[id$='1:more-info']");
    element.click();
    waitAjaxIndicatorDisappear();
    return new LendingOverviewPage();
  }
}
