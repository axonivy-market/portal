package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessViewerPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('portal-process-viewer-form')";
  }

  public String getProcessRequestPath() {
    waitForElementDisplayed(By.id("process-viewer-information"), true);
    return findElementByCssSelector("[id$='portal-process-viewer-form'] [id$='request-path']").getText();
  }

  public String getErrorMessage() {
    return findElementByCssSelector(".empty-message-text").getText();
  }

  public void waitForSprottyToolDisplayed() {
    waitForIFrameContentVisible("process-viewer", 15000);
    waitForElementDisplayed(By.id("sprotty"), true); 
  }
}
