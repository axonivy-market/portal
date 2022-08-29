package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProcessHistoryPage extends TemplatePage {
  
  @Override
  protected String getLoadedLocator() {
    return COMPONENT_PAGE_LOCATOR;
  }

  public int countCases() {
    WebElement caseList = findElementByClassName("ui-datascroller-list");
    return caseList.findElements(By.className("ui-datascroller-item")).size();
  }
  
  public boolean isEmptyMessageDisplay() {
    return isElementDisplayed(By.className("process-history-empty-message"));
  }
  
  public int openDialogAndCountCases() {
    click(findElementById("process-history-dialog-button"));
    waitForElementDisplayed(By.cssSelector("ul.ui-datascroller-list li div.js-case-item.case-list-item"), true);
    WebElement caseList = findElementByClassName("ui-datascroller-list");
    return caseList.findElements(By.className("ui-datascroller-item")).size();
  }
  
  public WebElement getProcessHistoryDialog() {
    click(findElementById("process-history-dialog-button"));
    waitForElementDisplayed(By.id("process-history-dialog_content"), true);
    return findElementById("process-history-dialog");
  }
}
