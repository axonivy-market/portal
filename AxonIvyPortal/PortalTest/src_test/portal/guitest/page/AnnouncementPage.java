package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AnnouncementPage extends TemplatePage {

  public static final String DE_LANGUAGE = "lies mich";
  public static final String EN_LANGUAGE = "read me";
  
  @Override
  protected String getLoadedLocator() {
    return "id('adminui:adminTabView:announcement-tab')";
  }

  public String getInfoSummary() {
    return driver.findElement(By.cssSelector("div[id$='messages'] span[class$='summary']")).getText();
  }

  public void closeInformConfigDialog() {
    WebElement closeButton = findElementById("close-dialog-button");
    closeButton.click();
  }

  public void publish() {
    clickByCssSelector("button[id$='publish-announcement']");
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }

  public void dePublish() {
    clickByCssSelector("button[id$='delete-announcement']");
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }

  public void setAnnoucement(int Language, String content) {
    findElementByCssSelector("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }

}
