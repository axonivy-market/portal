package com.axonivy.portal.selenium.page;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.masterdata.AwesomeIcon;


public class UserFavoriteProcessPage extends TemplatePage{
  private final String DEFAULT_ICON_CSS_SELECTOR = "span[id$='awesome-icon-display']";
  private final String ICON_CSS_SELECTOR = "a[id$='awesome-icon']";

  @Override
  protected String getLoadedLocator() {
    $("[id='process-widget:add-new-process-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    return "[id='process-widget:add-new-process-dialog']";
  }

  public void submitForm() {
    $("button[id='process-widget:add-process-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id='process-widget:add-process-command']").shouldBe(getClickableCondition()).click();
  }

  public void selectProcessByName(String ivyProcessName) {
    $("input[id$='process-widget:new-process-name_input']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(ivyProcessName);

    String processSelector = String.format("tr[data-item-label='%s']", ivyProcessName);
    findElementByCssSelector(processSelector).click();
    WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("input[id$='process-widget:process-display-name']")
        .getAttribute("value").equalsIgnoreCase(""));
  }

  public boolean isIvyProcessByNameSearchable(String ivyProcessName) {
    $(".ui-autocomplete-dropdown").shouldBe(getClickableCondition()).click();
    String processSelector = "tr[data-item-label='" + ivyProcessName + "']";
    return isElementPresent(By.cssSelector(processSelector));
  }

  public void clickChangeIconButton() {
    $(By.cssSelector("a[class*='select-awesome-icon-button']")).shouldBe(getClickableCondition()).click();
  }
  
  public void inputSearchedIconName(String keyword) {
    $("input[id$='search-icon-name-field']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(keyword);
  }
  
  public int getDisplayedIconAmount() {
    List<SelenideElement> icons = $$(ICON_CSS_SELECTOR);
    return icons.stream().filter(icon -> !icon.getCssValue("display").equals("none")).collect(Collectors.toList()).size();
  }
  
  public boolean isDefaultIcon() {
    WebElement element = $(By.cssSelector(DEFAULT_ICON_CSS_SELECTOR));
    return element.getAttribute("class").contains(AwesomeIcon.DEFAULT_ICON);
  }

  public SettingProcessLanguageDialog openAddlanguageDialog() {
    $("button[id$='process-widget:add-languages']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='process-widget:add-languages']").shouldBe(getClickableCondition()).click();
    $("span[id$='process-widget:favorite-process-name_title']").shouldBe(appear, DEFAULT_TIMEOUT);
    
    return new SettingProcessLanguageDialog();
  }

  public class SettingProcessLanguageDialog extends UserFavoriteProcessPage {

    public SettingProcessLanguageDialog() {}

    @Override
    protected String getLoadedLocator() {
      return "[id='process-widget:favorite-process-name']";
    }
    
    public void fillProcessNamesByLocaleName() {
      WebElement defaultDisplayName = findElementByCssSelector("input[id$='process-widget:process-display-name']");
      String defaultDisplayNameText = defaultDisplayName.getAttribute("value");
      WebElement settingLanguageDialog = findElementByCssSelector("div[id$='process-widget:favorite-process-name']");
      List<WebElement> inputFields = settingLanguageDialog
          .findElements(By.cssSelector("div[class*='support-process-language-field']"));
      for (WebElement languageGroup : inputFields) {
        WebElement input = languageGroup.findElement(By.cssSelector("input[id*=':support-language-']"));
        var inputId = input.getAttribute(ID_PROPERTY);
        WebElement label = findElementByCssSelector("label[for$='"+ inputId +"']");
        input.clear();
        input.sendKeys(String.format("%s - %s", defaultDisplayNameText, label.getText()));
      }

      settingLanguageDialog.findElement(By.cssSelector("button[id$=':add-supported-language-command']")).click();

      WaitHelper.assertTrueWithWait(() -> !findElementByCssSelector("input[id$='process-widget:process-display-name']")
          .getAttribute("value").equalsIgnoreCase(defaultDisplayNameText));
    }
  }
}
