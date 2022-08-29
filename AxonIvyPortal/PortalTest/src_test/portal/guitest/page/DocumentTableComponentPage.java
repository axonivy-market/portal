package portal.guitest.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DocumentTableComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('form:document-table-component')";
  }

  public void uploadSampleDocument(String pathToFile) {
    click(By.id("form:document-table-component:document-upload"));
    waitForElementExisted("input[id$='form:document-table-component:document-upload_input']", true, DEFAULT_TIMEOUT);
    findElementByCssSelector("input[id$='form:document-table-component:document-upload_input']").sendKeys(pathToFile);
    waitForElementDisplayed(getDocuments().get(0), true);
    waitForElementDisplayed(By.cssSelector("span[class$='ui-messages-info-summary']"), true);
  }
  
  private List<WebElement> getDocuments() {
    return findElementById("form:document-table-component:document-table").findElement(By.cssSelector("table tbody")).findElements(By.cssSelector("tr"));
  }
  
  public void waitForDocumentTableComponentPageLoaded() {
    waitForElementDisplayed(By.id("form:document-table-component"), true);
  }
  
  public WebElement getDocumentTableComponent() {
    return findElementById("form:document-table-component");
  }
  
  public int countDocuments() {
    return getDocuments().size();
  }
}
