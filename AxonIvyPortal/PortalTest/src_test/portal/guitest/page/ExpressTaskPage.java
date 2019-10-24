package portal.guitest.page;

import org.openqa.selenium.By;

public class ExpressTaskPage extends TaskTemplatePage {
  public void finish() {
    clickByCssSelector("button[id$='ok-btn']");
  }

  public boolean isDocumentTableVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-table')]"));
  }
  
  public boolean isDocumentUploadButtonVisible() {
    return isElementPresent(By.xpath("//div[contains(@id, 'fileUploadComponent:document-upload')]"));
  }
}
