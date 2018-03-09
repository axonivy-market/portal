package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdditionalCaseDetailsPage extends TemplatePage {
  
  private static final String TABLE_ROWS_PATH = "table[id*='additional-case-details-data-grid'] tbody>tr";
  
  public int countFields() {
      waitForElementDisplayed(By.cssSelector("table[id*='additional-case-details-data-grid']"), true);
      return driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
  }
  
  public String getAdditionalFieldContentOfFirstRow() {
      WebElement firstRow = driver.findElements(By.cssSelector(TABLE_ROWS_PATH)).get(0);
      return firstRow.findElement(By.id("custom-varchar-field-1")).getText();
  }
}