package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class DocumentTableComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('form:document-table-component')";
  }

  public void uploadSampleDocument(String pathToFile) {
    click(By.id("form:document-table-component:document-upload"));
    findElementByCssSelector("input[id$='form:document-table-component:document-upload_input']").sendKeys(pathToFile);
    Sleeper.sleep(2000);// currently haven't found solution to check when the file upload finish, we have to wait
    waitForElementDisplayed(By.cssSelector("span[class$='ui-messages-info-summary']"), true);
  }
  
  public void waitForDocumentTableComponentPageLoaded() {
    waitForElementDisplayed(By.id("form:document-table-component"), true);
  }
  
  public WebElement getDocumentTableComponent() {
    return findElementById("form:document-table-component");
  }
}
