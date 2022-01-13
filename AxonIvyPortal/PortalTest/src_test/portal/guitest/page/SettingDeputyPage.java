package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

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

  protected void proceed(String selector) {
    clickByCssSelector(selector);
    waitAjaxIndicatorDisappear();
  }

  public boolean isSubstitutedUserInputElementDisplayed() {
    return isElementPresent(By.cssSelector("input[id*='substituted-user']"));
  }

  public void proceedWhenCreatingAbsence() {
    String selector = "button[id*='save-substitutes']";
    proceed(selector);
  }

  public void filterDeputyUser(String username) {
    if (StringUtils.isNotEmpty(username)) {
      var usernameInputPattern = "input[id$=':substitute-username-selection-component:substitute-username_%s']";
      waitForElementDisplayed(By.cssSelector(String.format(usernameInputPattern, "input")), true);
      WebElement usernameInput = findElementByCssSelector(String.format(usernameInputPattern, "input"));
      usernameInput.clear();
      usernameInput.sendKeys(username);
      WaitHelper.assertTrueWithWait(
          () -> findElementByCssSelector(String.format(usernameInputPattern, "hinput"))
              .getAttribute("value").contains(username));
    }
  }

  public List<String> getSearchDeputyResult() {
    var substitutePanel = "span[id$=':substitute-username-selection-component:substitute-username_panel']";
    waitForElementDisplayed(By.cssSelector(substitutePanel), true);
    waitForElementDisplayed(By.cssSelector(substitutePanel + " tr.ui-autocomplete-item.ui-autocomplete-row.ui-state-highlight"), true);
    return findListElementsByCssSelector(substitutePanel + " tr.ui-autocomplete-item.ui-autocomplete-row")
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public void selectSubstitutedUser(String username) {
    if (StringUtils.isNotEmpty(username)) {
      var usernameInputPattern = "input[id$=':substituted-user-selection-component:substituted-user_%s']";
      waitForElementDisplayed(By.cssSelector(String.format(usernameInputPattern, "input")), true);
      WebElement usernameInput = findElementByCssSelector(String.format(usernameInputPattern, "input"));
      usernameInput.clear();
      usernameInput.sendKeys(username);
      WaitHelper.assertTrueWithWait(
          () -> findElementByCssSelector(String.format(usernameInputPattern, "hinput"))
              .getAttribute("value").contains(username));
      String itemSelector = "tr[data-item-label*='" + username + "'].ui-state-highlight";
      waitForElementDisplayed(By.cssSelector(itemSelector), true);
      clickByCssSelector(itemSelector);
      WaitHelper.assertTrueWithWait(
          () -> findElementByCssSelector(String.format(usernameInputPattern, "input"))
              .getAttribute("value").contains(username));
    }
  }

  public void changeSubstitutedUser(String substitutedUser) {
    waitForElementDisplayed(By.cssSelector("input[id*='substituted-user']"), true);
    WebElement substitutedUserInput = findElementByCssSelector("input[id*='substituted-user']");
    substitutedUserInput.clear();
    substitutedUserInput.sendKeys(substitutedUser);
    waitAjaxDisappeared();
    String itemSelector = "tr[data-item-label*='" + substitutedUser + "']";
    waitForElementDisplayed(By.cssSelector(itemSelector), true);
    clickByCssSelector(itemSelector);
    waitAjaxDisappeared();
  }


  public void changeDeputy(String deputy, int indexOfApp, int indexOfRole) {
    String inputId = String.format(DEPUTY_INPUT_ID_PATTERN, indexOfApp, indexOfRole);
    waitForElementDisplayed(By.id(inputId), true);
    WebElement deputyInput = findElementById(inputId);
    deputyInput.clear();
    deputyInput.sendKeys(deputy);
    waitAjaxDisappeared();
    WebElement deputyPanel = findElementById(String.format(DEPUTY_PANEL_ID_PATTERN, indexOfApp, indexOfRole));
    click(deputyPanel.findElement(By.cssSelector("tr[data-item-label*='" + deputy + "']")));
    waitAjaxDisappeared();
  }

  private void waitAjaxDisappeared() {
    waitUntilAnimationFinished(DEFAULT_TIMEOUT,"ajax-indicator:ajax-indicator-ajax-indicator_start" , "id");
  }

  public String getMyDisabledDeputy(int appIndex, int deputyRoleIndex) {
    waitForElementDisplayed(By.id(String.format(DISABLED_DEPUTY_INPUT_ID_PATTERN, appIndex, deputyRoleIndex)), true);
    return findElementById(String.format(DISABLED_DEPUTY_INPUT_ID_PATTERN, appIndex, deputyRoleIndex)).getAttribute("value");
  }
}
