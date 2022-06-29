package portal.guitest.page;

import org.openqa.selenium.By;

public class AnnouncementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('admin-setting-component:adminTabView:announcement-tab')";
  }

  public String getInfoSummary() {
    return driver.findElement(By.cssSelector("div[id$='messages'] span[class$='summary']")).getText();
  }

  @SuppressWarnings("deprecation")
  public void publish() {
    clickByCssSelector("button[id$='publish-announcement']");
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void dePublish() {
    clickByCssSelector("button[id$='delete-announcement']");
    waitAjaxIndicatorDisappear();
  }

  public void setAnnoucement(int Language, String content) {
    findElementByCssSelector("input[id$='" + Language + ":announcement-input']").sendKeys(content);
  }


}
