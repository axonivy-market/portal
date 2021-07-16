package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.userexamples.page.CaseMapPage;

public class LendingOverviewPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('lending-header')";
  }

  public String getHearText() {
    WebElement element = findElementByCssSelector("[id$='lending-header']");
    return element.getText();
  }
  
  public String getStageName(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:stage']", index));
    return element.getText();
  }
  
  public LendingDetailPage navigateToStageDetail(int index) {
    WebElement element = findElementByCssSelector(String.format("[id$=':%d:detail-stage']", index));
    element.click();
    return new LendingDetailPage(index);
  }
  
  public CaseMapPage startLendingCase() {
    WebElement element = findElementByCssSelector("[id$='start']");
    element.click();
    waitForElementDisplayed(By.cssSelector("input[id$='form:first-name']"), true);
    return new CaseMapPage();
  }
}
