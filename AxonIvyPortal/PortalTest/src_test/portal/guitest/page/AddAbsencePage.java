package portal.guitest.page;

import org.openqa.selenium.By;

public class AddAbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:add-new-absence-dialog_title')";
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
    clickByCssSelector(selector);
    waitAjaxIndicatorDisappear();
  }

  public boolean isSubstitutedUserInputElementDisplayed() {
    return isElementPresent(By.cssSelector("input[id*='substituted-user']"));
  }
}
