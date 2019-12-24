package portal.guitest.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;

public class AnnouncementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('adminui:adminTabView:announcement-tab')";
  }

  public String getInfoSummary() {
    return driver.findElement(By.cssSelector("div[id$='messages'] span[class$='summary']")).getText();
  }

  public void publish() {
    clickByCssSelector("button[id$='publish-announcement']");
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }

  public void dePublish() {
    Awaitility.waitAtMost(10, TimeUnit.SECONDS).until(() -> canDePublish());
    clickByCssSelector("button[id$='delete-announcement']");
    waitForElementDisplayed(By.cssSelector("div[id$='messages'] span[class$='summary']"), true);
  }

  public void setAnnoucement(int Language, String content) {
    findElementByCssSelector("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }

  public boolean canDePublish() {
    String startCommandButton =
        String.format("adminui:adminTabView:delete-announcement");
    WebElement element = findElementById(startCommandButton);
    return !element.getAttribute("class").contains("ui-state-disabled");
  }

}
