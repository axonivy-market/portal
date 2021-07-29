package portal.guitest.page;

import org.openqa.selenium.By;

public class ProcessInformationPage extends TemplatePage {

  public ProcessInformationPage() {}

  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'process-information')]";
  }

  public String getProcessName() {
    waitForElementDisplayed(By.cssSelector("[id$='header']"), true);
    return findElementByCssSelector("[id='header'] > h2 ").getText();
  }

  public String getProcessDescription() {
    waitForElementDisplayed(By.cssSelector("[id$='process-description']"), true);
    return findElementByCssSelector("[id$='process-description']").getText();
  }

  public void startProcess() {
    clickByCssSelector("[id$='start-process-button']");
  }

  @Override
  public void back() {
    clickByCssSelector("[id$='back-link']");
  }
}
