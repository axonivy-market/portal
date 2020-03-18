package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import vn.wawa.guitest.base.page.AbstractPage;

public class TaskDelegationPage extends AbstractPage {
  
  private final String delegateUser;
  private final String delegateType;
  
  private WebElement delegateButton;
  
  @Override
  protected String getLoadedLocator() {
    return null;
  }

  public TaskDelegationPage(String delegateType, String delegateUser) {
    this.delegateUser = delegateUser;
    this.delegateType = delegateType;
  }
  
  public void delegate() {
    waitForElementEnabled(By.id("task-widget:task-delegate-form:activator-type-select_label"), true, DEFAULT_TIMEOUT);
    findElementById("task-widget:task-delegate-form:activator-type-select_label").click();
    String xpath = String.format("id('task-widget:task-delegate-form:activator-type-select_panel')/div/ul/li[@data-label='%s']", delegateType);
    findElementByXpath(xpath).click();
    
    waitForElementEnabled(By.id("task-widget:task-delegate-form:user-activator-select_label"), true, DEFAULT_TIMEOUT);
    findElementById("task-widget:task-delegate-form:user-activator-select_label").click();
    String userXpath = String.format("//div[@id='task-widget:task-delegate-form:user-activator-select_panel']/div[2]/ul/li[@data-label='%s']", delegateUser);
    findElementByXpath(userXpath).click();
    
    String delegateButtonId = "task-widget:task-delegate-form:proceed-task-delegate-command";
    waitForElementEnabled(By.id(delegateButtonId), true, DEFAULT_TIMEOUT);
    delegateButton = findElementById(delegateButtonId);
    delegateButton.click();
  }
}
