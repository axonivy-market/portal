package portal.guitest.page;

import org.openqa.selenium.By;

public class ExpressTaskPage extends TaskTemplatePage {
  public void finish() {
    click(By.id("form:ok-btn"));
  }

  public boolean isDocumentTableVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-table')]"));
  }
  
  public boolean isDocumentUploadButtonVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-upload')]"));
  }
}
