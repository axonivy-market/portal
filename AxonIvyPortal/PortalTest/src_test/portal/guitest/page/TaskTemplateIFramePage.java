package portal.guitest.page;

import org.openqa.selenium.By;

public class TaskTemplateIFramePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-template-title')";
  }

  public void openCaseInfo() {
    clickByCssSelector("#horizontal-case-info");
  }

  @SuppressWarnings("deprecation")
  public void inputDataCreateInvestmentTask() {
    type(By.id("form:invested-amount"), "12345");
    clickByCssSelector("button[id$='form:save-btn']");
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  public void switchToIFrame(String nameOrId) {
    driver.switchTo().frame(nameOrId);
  }

  public void switchOutIFrame() {
    driver.switchTo().defaultContent();
  }

  @SuppressWarnings("deprecation")
  public void approveInvestmentTask() {
    waitForElementDisplayed(By.xpath("//label[contains(text(),'Approve')]"), true);
    click(findElementByXpath("//label[contains(text(),'Approve')]"));
    type(By.cssSelector("textarea[id$='approval-note']"), "text");
    clickByCssSelector("button[id$='approve-btn']");
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void proceedTaskInCaseLevelIFrame() {
    waitForElementDisplayed(By.xpath("//span[contains(text(),'Proceed')]"), true);
    click(findElementByXpath("//span[contains(text(),'Proceed')]"));
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void proceedSerenityTaskTemplate() {
    waitForElementDisplayed(By.cssSelector("button[id^='form']"), true);
    clickByCssSelector("button[id^='form']");
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void proceedDeprecateModenaTaskTemplate() {
    waitForElementDisplayed(By.xpath("//span[contains(text(),'Proceed')]"), true);
    type(By.cssSelector("input[id$='form:input']"), "input text");
    type(By.cssSelector("textarea[id$='form:comment-area']"), "comment");
    click(findElementByXpath("//span[contains(text(),'Proceed')]"));
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void proceedDeprecatedTaskTemplate() {
    waitForElementDisplayed(By.xpath("//span[contains(text(),'Proceed')]"), true);
    type(By.cssSelector("input[id$='form:input']"), "input text");
    type(By.cssSelector("textarea[id$='form:comment-area']"), "comment");
    click(findElementByXpath("//span[contains(text(),'Proceed')]"));
    switchOutIFrame();
    waitAjaxIndicatorDisappear();
  }

  public boolean isIFrameTagDisplayed() {
    return isElementPresent(By.cssSelector("iframe[id*='iFrame']"));
  }

  public HomePage clickSubmitButton() {
    clickByCssSelector("button[id$='button-submit']");
    switchToDefaultContent();
    return new HomePage();
  }

}
