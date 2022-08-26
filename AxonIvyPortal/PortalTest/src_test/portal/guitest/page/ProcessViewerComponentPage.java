package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessViewerComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return COMPONENT_PAGE_LOCATOR;
  }
  
  public void waitForSprottyToolDisplayed() {
    waitForIFrameContentVisible("process-viewer", 15000);
    waitForElementDisplayed(By.id("sprotty"), true); 
  }
}
