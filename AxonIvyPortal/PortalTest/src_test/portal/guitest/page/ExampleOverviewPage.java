package portal.guitest.page;

import org.openqa.selenium.By;

public class ExampleOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('example-header')";
  }
  
  @SuppressWarnings("deprecation")
  public LeaveRequestOverviewPage openLeaveRequestOverview() {
    clickByCssSelector("[id$='0:more-info']");
    waitAjaxIndicatorDisappear();
    return new LeaveRequestOverviewPage();
  }
  
  @SuppressWarnings("deprecation")
  public LendingOverviewPage openLendingOverview() {
    clickByCssSelector("[id$='1:more-info']");
    waitAjaxIndicatorDisappear();
    return new LendingOverviewPage();
  }
  
  public void startUserExampleProcess(int index) {
    String userProcessId = String.format("[id$=':%d:start-button']", index);
    click(findElementByCssSelector(userProcessId));
  }
  
  public void waitUntilExampleOverviewDisplayed() {
    waitForElementDisplayed(By.id("example-header"), true);
  }
}
