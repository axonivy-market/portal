package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import vn.wawa.guitest.base.client.Browser;


public class UserTaskWithMailFormPage extends TaskTemplatePage {

  public void selectEmailTab() {
    clickByCssSelector("a[href*='mail-tab']");
    ensureNoBackgroundRequest();
  }
  public void inputData(String recipient, String subject, String content) {
    inputRecipient(recipient);
    inputSubject(subject);
    inputContent(content);
  }

  private void inputRecipient(String recipient) {
    findElementById("mail-form:information-email:email-recipients").sendKeys(recipient);
  }

  private void inputSubject(String content) {
    findElementById("mail-form:information-email:email-subject").sendKeys(content);
  }

  private void inputContent(String content) {
    WebDriver driver = Browser.getBrowser().getDriver();
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("document.querySelector(\"input[name='mail-form:information-email:email-content_input'\").value='"
        + content + "';");
  }
  public void finish() {
    click(By.id("mail-form:email-ok-btn"));
  }
}
