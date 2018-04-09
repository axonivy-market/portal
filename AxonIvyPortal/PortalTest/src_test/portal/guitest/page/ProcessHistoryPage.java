package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProcessHistoryPage extends TemplatePage {

  public int countCases() {
    WebElement caseList = findElementByClassName("ui-datascroller-list");
    return caseList.findElements(By.className("ui-datascroller-item")).size();
  }
  
  public boolean isEmptyMessageDisplay() {
    return isElementDisplayed(By.className("process-history-empty-message"));
  }
  
  public int openDialogAndCountCases() {
    findElementById("process-history-dialog-button").click();
    waitAjaxIndicatorDisappear();
    WebElement caseList = findElementByClassName("ui-datascroller-list");
    return caseList.findElements(By.className("ui-datascroller-item")).size();
  }
}
