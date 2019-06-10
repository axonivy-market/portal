package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SettingDeputyPage extends TemplatePage {


  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:edit-deputy-dialog_title')";
  }
  
  public void proceedWhenCreatingAbsence() {
    String selector = "button[id*='save-substitutes']";
    proceed(selector);
  }

  public void proceedWhenSettingDeputy() {
    String selector = "button[id*='save-deputy']";
    proceed(selector);
  }

  private void proceed(String selector) {
    WebElement proceedElement = findElementByCssSelector(selector);
    proceedElement.click();
    waitAjaxIndicatorDisappear();
  }

  public boolean isSubstitutedUserInputElementDisplayed() {
    return isElementPresent(By.cssSelector("input[id*='substituted-user']"));
  }
}
