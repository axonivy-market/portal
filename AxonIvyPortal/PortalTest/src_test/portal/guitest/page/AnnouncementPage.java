package portal.guitest.page;

import org.openqa.selenium.By;

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
    waitAjaxIndicatorDisappear();
  }

  public void dePublish() {
    clickByCssSelector("button[id$='delete-announcement']");
    waitAjaxIndicatorDisappear();
  }

  public void setAnnoucement(int Language, String content) {
    findElementByCssSelector("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }


}
