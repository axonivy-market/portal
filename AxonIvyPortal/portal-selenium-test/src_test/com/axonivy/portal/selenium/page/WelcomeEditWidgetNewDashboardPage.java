package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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

  private String getTestFilePath(String filename) {
    return FileHelper.getAbsolutePathToTestFile(filename);
  }

  public void inputWelcomeTexts(List<String> welcomeTexts) {
    var configDialog = $("#new-widget-configuration-dialog");
    ElementsCollection welcomeTextInputs = configDialog.findAll(".js-welcome-text-input");
    welcomeTextInputs.asDynamicIterable().forEach(elem -> {
      elem.clear();
      elem.sendKeys(welcomeTexts.stream().filter(text -> welcomeTexts.indexOf(text) == welcomeTextInputs.indexOf(elem))
          .findFirst().orElse(""));
    });
  }

  public void selectTextSize(String value) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':welcome-text-size']").click();
    var selectionPanel = $("[id $= ':welcome-text-size_panel']");
    selectionPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    selectionPanel.findAll("li.ui-selectonemenu-item").asDynamicIterable().forEach(item -> {
      if (item.innerText().contentEquals(value)) {
        item.click();
        selectionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
        return;
      }
    });
  }

  public void selectTextPosition(String value) {
    var configDialog = $("#new-widget-configuration-dialog");
    configDialog.find("[id $= ':welcome-text-position']").click();
    var selectionPanel = $("[id $= ':welcome-text-position_panel']");
    selectionPanel.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    selectionPanel.findAll("li.ui-selectonemenu-item").asDynamicIterable().forEach(item -> {
      if (item.innerText().contentEquals(value)) {
        item.click();
        selectionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
        return;
      }
    });
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
    getConfigurationDialog().$("[id$=':welcome-text-color_button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    getConfigurationDialog().$(".user-filter__header").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }
}
