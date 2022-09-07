package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessViewerComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return COMPONENT_PAGE_LOCATOR;
  }

  public String getProcessRequestPath() {
    return findElementByCssSelector("span[id$=':request-path']").getText();
  }

  public void waitForSprottyToolDisplayed() {
    waitForIFrameContentVisible("process-viewer", 15000);
    waitForElementDisplayed(By.id("sprotty"), true); 
  }
}
