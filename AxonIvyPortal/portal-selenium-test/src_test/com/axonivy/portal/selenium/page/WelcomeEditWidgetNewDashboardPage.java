package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.Keys;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class WelcomeEditWidgetNewDashboardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id = 'welcome-widget-configuration']";
  }

  public void uploadImage(String fileName) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':image-upload-panel_input']").sendKeys(getTestFilePath(fileName));
    configDialog.find(".ui-fileupload-filename").shouldBe(Condition.disappear, DEFAULT_TIMEOUT)
        .shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void uploadImageDarkMode(String fileName) {
    var configDialog = $("#new-widget-configuration-dialog");

    configDialog.find("[id $= ':image-dark-mode-upload-panel_input']").sendKeys(getTestFilePath(fileName));
  }

  private String getTestFilePath(String filename) {
    return FileHelper.getAbsolutePathToTestFile(filename);
  }

  public void inputWelcomeTexts(List<String> welcomeTexts) {
    if (welcomeTexts.isEmpty()) {
      return;
    }
    var configDialog = $("#new-widget-configuration-dialog");
    var welcomeTextValueInput = configDialog.find("input[id$='welcome-text-value']");
    welcomeTextValueInput.sendKeys(Keys.CONTROL, "a");
    welcomeTextValueInput.sendKeys(welcomeTexts.get(0));

    configDialog.find("button[id$='add-welcome-language-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    var languagesDialog = $("div[id$='welcome-text-languages-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    for (int i = 0; i < welcomeTexts.size(); i++) {
      var languageInput = $(String.format("input[id$='table-titles:%s:title-input']", i));
      languageInput.sendKeys(Keys.CONTROL, "a");
      languageInput.sendKeys(welcomeTexts.get(i));
    }
    languagesDialog.find("button[id$='multi-language-ok-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    languagesDialog.shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void selectTextSize(String value) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':welcome-text-size']").click();
    selectOverlayItem($("[id $= ':welcome-text-size_panel']"), value);
  }

  public void selectTextPosition(String value) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':welcome-text-position']").click();
    selectOverlayItem($("[id $= ':welcome-text-position_panel']"), value);
  }

  private void selectOverlayItem(SelenideElement selectionPanel, String value) {
    selectionPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldHave(Condition.cssClass("ui-connected-overlay-enter-done"), DEFAULT_TIMEOUT);
    selectionPanel.findAll("li.ui-selectonemenu-item").filter(Condition.exactText(value)).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    selectionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void save() {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.shouldBe(appear, DEFAULT_TIMEOUT).$("button[id$='widget-configuration-save-button']")
        .shouldBe(getClickableCondition()).click();
    $("div[id$='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getConfigurationDialog() {
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForDialogLoaded() {
    getConfigurationDialog().$("input[id$=':welcome-text-color-light-mode']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    getConfigurationDialog().$("input[id$=':welcome-text-color-dark-mode']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    getConfigurationDialog().$(".user-filter__header").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }
}
