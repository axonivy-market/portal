package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SettingDeputyPage extends TemplatePage {

  private static final String DEPUTY_INPUT_ID_PATTERN = "absence-settings:edit-deputy-form:substitute-container:%d_%d:substitute-username-selection-component:substitute-username_input";
  private static final String DEPUTY_PANEL_ID_PATTERN = "absence-settings:edit-deputy-form:substitute-container:%d_%d:substitute-username-selection-component:substitute-username_panel";
  private static final String DISABLED_DEPUTY_INPUT_ID_PATTERN = "absence-settings:edit-deputy-form:substitute-container:%d_%d:substitute-username-selection-component:substitute-username_input";

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

  public void changeSubstitutedUser(String substitutedUser) {
    waitForElementDisplayed(By.cssSelector("input[id*='substituted-user']"), true);
    WebElement substitutedUserInput = findElementByCssSelector("input[id*='substituted-user']");
    substitutedUserInput.clear();
    substitutedUserInput.sendKeys(substitutedUser);
    waitAjaxIndicatorDisappear();
    String itemSelector = "tr[data-item-label*='" + substitutedUser + "']";
    waitForElementDisplayed(By.cssSelector(itemSelector), true);
    clickByCssSelector(itemSelector);
    waitAjaxIndicatorDisappear();
  }

  public void changeDeputy(String deputy, int indexOfApp, int indexOfRole) {
    String inputId = String.format(DEPUTY_INPUT_ID_PATTERN, indexOfApp, indexOfRole);
    waitForElementDisplayed(By.id(inputId), true);
    WebElement deputyInput = findElementById(inputId);
    deputyInput.clear();
    deputyInput.sendKeys(deputy);
    waitAjaxIndicatorDisappear();
    WebElement deputyPanel = findElementById(String.format(DEPUTY_PANEL_ID_PATTERN, indexOfApp, indexOfRole));
    click(deputyPanel.findElement(By.cssSelector("tr[data-item-label*='" + deputy + "']")));
    waitAjaxIndicatorDisappear();
  }

  public String getMyDisabledDeputy(int appIndex, int deputyRoleIndex) {
    waitForElementDisplayed(By.id(String.format(DISABLED_DEPUTY_INPUT_ID_PATTERN, appIndex, deputyRoleIndex)), true);
    return findElementById(String.format(DISABLED_DEPUTY_INPUT_ID_PATTERN, appIndex, deputyRoleIndex)).getAttribute("value");
  }
}
