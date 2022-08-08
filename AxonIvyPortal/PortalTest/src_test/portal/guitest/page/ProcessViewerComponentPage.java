package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessViewerComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('content-container')";
  }

  public void waitForSprottyToolDisplayed() {
    driver.switchTo().frame("iFrame").switchTo().frame("process-viewer");
    waitForElementDisplayed(By.id("sprotty"), true); 
  }
}
