package portal.guitest.page;

import org.openqa.selenium.By;

public class AdditionalCaseDetailsPage extends TemplatePage {
  
  private static final String TABLE_ROWS_PATH = "div[id$='addtional-case-detail-table'] tbody>tr";
  
  public int countFields() {
      waitForElementDisplayed(By.cssSelector("div[id$='addtional-case-detail-table']"), true);
      return driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
  }
  
  public String getAdditionalFieldContentOfFirstRow() {
      return findElementByCssSelector("#addtional-case-detail-table\\:0\\:value").getText();
  }
}