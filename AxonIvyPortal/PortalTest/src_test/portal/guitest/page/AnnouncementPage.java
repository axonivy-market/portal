package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AnnouncementPage extends TemplatePage {


  public static final String DE_LANGUAGE = "lies mich";
  public static final String EN_LANGUAGE = "read me";
//  private static final String FR_LANGUAGE = "lis moi";
//  private static final String PT_LANGUAGE = "me leia";

  public String getInfoSummary() {
    return driver.findElement(By.cssSelector("div[id$='messages'] span[class$='summary']")).getText();
  }

  public void closeInformConfigDialog() {
    // waitForElementPresent(By.id("close-dialog-button"), true);
    // waitForElementDisplayed("dialog-closing-information", true);
    WebElement closeButton = findElementById("close-dialog-button");
    closeButton.click();
  }

  public void publish() {
    click(By.id("adminui:adminTabView:publish-announcement"));
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }

  public void dePublish() {
    click(By.id("adminui:adminTabView:delete-announcement"));
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }


  public void setAnnoucement(int Language, String content) {
    findElementByCssSelector("input[id$='"+Language+":announcement-input']").sendKeys(content);
    
  }

}
