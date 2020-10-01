package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CaseInformationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('case-info-dialog')";
  }

  public void clickOnShowMoreTasks() {
    click(findElementByCssSelector("a[id$=':related-tasks:show-more-related-tasks']"));
    waitAjaxIndicatorDisappear();
  }

  public boolean isConfirmDialogDisplayed() {
    waitForElementDisplayed(By.cssSelector("span[id$=':leave-case-info-warning-dialog_title']"), true);
    return findElementByCssSelector("div[id$=':leave-case-info-warning-dialog']").isDisplayed();
  }

  public void clickCancelLeaveTask() {
    WebElement confirmDialog = findElementByCssSelector("div[id$=':leave-case-info-warning-dialog']");
    WebElement cancelButton = confirmDialog.findElement(By.cssSelector("button[id$=':cancel-leave-task-button']"));
    click(cancelButton);
    waitForElementDisplayed(By.cssSelector("span[id$=':leave-case-info-warning-dialog_title']"), false);
  }

  public void clickOnFirstTaskOfRunningTasks() {
    click(findElementByCssSelector("a[id$='related-tasks:tasks:0:task-name']"));
    waitAjaxIndicatorDisappear();
  }

  public void clickOnShowMoreCases() {
    click(findElementByCssSelector("a[id$=':related-tasks:show-more-related-cases']"));
    waitAjaxIndicatorDisappear();
  }

  public void clickOnFirstTechCaseOfRunningCases() {
    click(findElementByCssSelector("a[id$=':related-tasks:cases:0:case-name']"));
    waitAjaxIndicatorDisappear();
  }

  public void closeCaseInfoDialog() {
    WebElement caseInfoDialog = findElementByCssSelector("div[id$='case-info-dialog']");
    WebElement closeIcon = caseInfoDialog.findElement(By.className("ui-dialog-titlebar-close"));
    click(closeIcon);
    waitForElementDisplayed(By.cssSelector("span[id$=':leave-case-info-warning-dialog_title']"), false);
  }

  public void clickOnLeaveButton() {
    WebElement confirmDialog = findElementByCssSelector("div[id$=':leave-case-info-warning-dialog']");
    WebElement leaveButton = confirmDialog.findElement(By.cssSelector("button[id$=':leave-button']"));
    click(leaveButton);
    waitAjaxIndicatorDisappear();
  }


}
