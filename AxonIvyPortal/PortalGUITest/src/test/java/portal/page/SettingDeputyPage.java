package portal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SettingDeputyPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:add-new-absence-dialog')";
  }

  public void proceedWhenCreatingAbsence() {
    String selector = "button[id*='save-new-absence']";
    proceed(selector);
  }

  public void proceedWhenSettingDeputy() {
    String selector = "button[id*='save-deputy']";
    proceed(selector);
  }

  private void proceed(String selector) {
    waitForElementDisplayed(By.cssSelector(selector), true);
    WebElement proceedElement = findElementByCssSelector(selector);
    proceedElement.click();
    waitAjaxIndicatorDisappear();
  }

  public boolean isSubstitutedUserInputElementDisplayed() {
    return isElementPresent(By.cssSelector("input[id*='substituted-user']"));
  }
}
