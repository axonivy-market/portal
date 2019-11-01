package portal.guitest.page;

import org.openqa.selenium.By;

public class SettingDeputyPage extends TemplatePage {


  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:edit-deputy-dialog_title')";
  }

  public void proceedWhenSettingDeputy() {
    String selector = "button[id*='save-deputy']";
    proceed(selector);
  }

  private void proceed(String selector) {
    clickByCssSelector(selector);
    waitAjaxIndicatorDisappear();
  }

  public boolean isSubstitutedUserInputElementDisplayed() {
    return isElementPresent(By.cssSelector("input[id*='substituted-user']"));
  }
}
