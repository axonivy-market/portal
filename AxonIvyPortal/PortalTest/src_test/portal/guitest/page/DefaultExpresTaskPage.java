package portal.guitest.page;

import org.openqa.selenium.By;

public class DefaultExpresTaskPage extends TaskTemplatePage{
  
  public void enterTextToDefaultTask(String text) {
    type(By.cssSelector("#form\\:user-task-dyna-form textarea[id$='input-text-area']"), text);
  }
  
  public void enterTextToComment(String comment) {
    type(By.id("form:comment"), comment);
  }
  
  public void finishDefaultTask() {
    click(By.id("form:ok-btn"));
  }
  
  public void finishApprovalTask() {
    click(By.id("form:approve-btn"));
  }
  
  public void finishReviewTask() {
    click(By.id("form:acknowledged"));
  }
  
  public int countNumberOfApproval() {
    return driver.findElements(By.xpath("//div[@id='form:finished-tasks-component:approval-result:0:approval-result-component:approval-result-container:0:approval-result-table']/div/table/tbody/tr")).size();
  }
}
