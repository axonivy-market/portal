package portal.guitest.page;

import org.openqa.selenium.By;

import portal.guitest.common.WaitHelper;

public class ProcessViewerPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('portal-process-viewer-form')";
  }

  public String getProcessRequestPath() {
    waitForElementDisplayed(By.id("process-viewer-information"), true);
    return findElementByCssSelector("[id$='portal-process-viewer-form:request-path']").getText();
  }

  public void clickOnCenterProcessView() {
    driver.switchTo().frame("process-viewer");
    waitForElementDisplayed(By.id("sprotty"), true); 
    var transformStart = getSprottyGraphTransformValue();
    clickByCssSelector("div[id$='sprotty_ivy-viewport-bar'] i.fa-crosshairs");
    WaitHelper.assertTrueWithWait(() -> {
      var transforEnd = getSprottyGraphTransformValue();
      return !transformStart.equalsIgnoreCase(transforEnd);
    });
  }

  public String getSprottyGraphTransformValue() {
    return findElementByCssSelector("svg.sprotty-graph g").getAttribute("transform");
  }

}
