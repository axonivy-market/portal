package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;

public class UserFavoriteProcessPage extends TemplatePage {

  private final String SEARCH_ICON_NAME_CSS_SELECTOR = "input[id$='search-icon-name-field']";
  private final String DEFAULT_ICON_CSS_SELECTOR = "span[id$='awesome-icon-display']";
  private final String ICON_CSS_SELECTOR = "a[id$='awesome-icon']";

  @Override
  protected String getLoadedLocator() {
    return "id('process-widget:add-new-process-dialog')";
  }

  public void submitForm() {
    WebElement processDialog = findElementByCssSelector("div[id$='process-widget:add-new-process-dialog']");
    WebElement submitButton = findChildElementById(processDialog, "process-widget:add-process-command");
    submitButton.click();
    waitAjaxIndicatorDisappear();
  }

  public void selectProcessByName(String ivyProcessName) {
    findElementByClassName("ui-autocomplete-dropdown").click();
    String processSelector = "tr[data-item-label='" + ivyProcessName + "']";
    waitForElementDisplayed(By.cssSelector(processSelector), true);
    clickByCssSelector(processSelector);
    waitAjaxIndicatorDisappear();
  }

  public boolean isIvyProcessByNameSearchable(String ivyProcessName) {
    findElementByClassName("ui-autocomplete-dropdown").click();
    String processSelector = "tr[data-item-label='" + ivyProcessName + "']";
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    return isElementPresent(By.cssSelector(processSelector));
  }

  public void clickChangeIconButton() {
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    clickByCssSelector("a[class*='select-awesome-icon-button']");
  }
  
  public void inputSearchedIconName(String keyword) {
    WebElement searchIconNameField = findDisplayedElementByCssSelector(SEARCH_ICON_NAME_CSS_SELECTOR);
    searchIconNameField.sendKeys(keyword);
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }
  
  public int getDisplayedIconAmount() {
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    List<WebElement> icons = findListElementsByCssSelector(ICON_CSS_SELECTOR);
    return icons.stream().filter(icon -> !icon.getCssValue("display").equals("none")).collect(Collectors.toList()).size();
  }
  
  public boolean isDefaultIcon() {
    WebElement element = findDisplayedElementByCssSelector(DEFAULT_ICON_CSS_SELECTOR);
    return element.getAttribute("class").contains(AwesomeIcon.DEFAULT_ICON);
  }

  public SettingProcessLanguageDialog openAddlanguageDialog() {
    click(findElementByCssSelector("button[id$='process-widget:add-languages']"));
    waitForElementDisplayed(By.cssSelector("input[data-p-label='English']"), true);
    return new SettingProcessLanguageDialog();
  }

  public class SettingProcessLanguageDialog extends UserFavoriteProcessPage {

    public SettingProcessLanguageDialog() {}

    @Override
    protected String getLoadedLocator() {
      return "id('process-widget:favorite-process-name')";
    }
    
    public void fillProcessNamesByLocaleName() {
      WebElement defaultDisplayName = findElementByCssSelector("input[id$='process-widget:process-display-name']");
      String defaultDisplayNameText = defaultDisplayName.getAttribute("value");
      WebElement settingLanguageDialog = findElementByCssSelector("div[id$='process-widget:favorite-process-name']");
      List<WebElement> inputFields = settingLanguageDialog
          .findElements(By.cssSelector("div[class*='md-inputfield support-process-language-field']"));
      for (WebElement languageGroup : inputFields) {
        WebElement label = languageGroup.findElement(By.cssSelector("label[for$=':support-language']"));
        WebElement input = languageGroup.findElement(By.cssSelector("input[id$=':support-language']"));
        input.clear();
        input.sendKeys(String.format("%s - %s", defaultDisplayNameText, label.getText()));
      }

      WebElement submitButton =
          settingLanguageDialog.findElement(By.cssSelector("button[id$=':add-supported-language-command']"));
      click(submitButton);
      waitUntilAttributeChanged(By.cssSelector("input[id$='process-widget:process-display-name']"), "value",
          defaultDisplayNameText, DEFAULT_TIMEOUT);
    }
  }
}
