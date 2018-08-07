package portal.guitest.page;

import org.openqa.selenium.WebElement;

public class LeaveRequestPage extends TemplatePage{
  @Override
  protected String getLoadedLocator() {
    return "id('request-tab')";
  }

  public String getTitle() {
    WebElement titleElement = findElementByXpath("id('request-tab')/h3");
    return titleElement.getText();
  }
  
  
}
