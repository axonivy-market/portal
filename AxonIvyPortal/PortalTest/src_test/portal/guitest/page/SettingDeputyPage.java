package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

public class SettingDeputyPage extends TemplatePage {

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
}
