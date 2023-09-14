package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CustomWidgetNewDashBoardPage extends TemplatePage {

  private static final String CONFIGURATION_DIALOG = "new-widget-configuration-dialog";
  private static final String CUSTOM_DATE_INPUT_PATTERN = "widget-configuration-form:new-widget-configuration-component:parammeters:%d:param-calendar-_input";
  private static final String CUSTOM_DATE_DIALOG_PATTERN = "widget-configuration-form:new-widget-configuration-component:parammeters:%d:param-calendar-_panel";
  private static final String CUSTOM_STRING_INPUT_PATTERN = "widget-configuration-form:new-widget-configuration-component:parammeters:%d:param-string-";
  private static final String CUSTOM_USER_SELECTION_INPUT_PATTERN = "widget-configuration-form:new-widget-configuration-component:parammeters:%d:param-user-:user-selection_input";
  private static final String CUSTOM_USER_SELECTION_DIALOG_PATTERN = "widget-configuration-form:new-widget-configuration-component:parammeters:%d:param-user-:user-selection_panel";

  @Override
  protected String getLoadedLocator() {
    return "[id$='widget-configuration-form']";
  }

  public void selectProcessAndSaveWidget(String processName) {
    selectProcess(processName);
    
  }

  public void selectProcess(String processName) {
    getSelectedProcess().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("input[id$=':selected-process_input']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).sendKeys(processName);
  }

  public void selectProcessAndWaitForParameterPanel(String processName) {
    selectProcess(processName);
    $("[id='widget-configuration-form:new-widget-configuration-component:selected-process_panel']").shouldBe(appear, DEFAULT_TIMEOUT)
      .$$(".autocomplete-tooltip").get(0).shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("div[id='widget-configuration-form:new-widget-configuration-component:parameters-panel']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getSelectedProcess() {
    return $("span[id$=':selected-process']");
  }

  public void clickSaveCustomWidget() {
    $("button[id='widget-configuration-save-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getSelectedProcessList() {
    return $$("span[id$=':selected-process_panel'] tr");
  }

  public void selectWidgetType(String type) {
    getWidgetType().shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("label[id$=':custom-widget-type_label']")
        .shouldBe(getClickableCondition()).click();
    $("li[data-label='" + type + "']").click();
  }

  private SelenideElement getWidgetType() {
    return $("div[id$=':custom-widget-type']");
  }

  public ElementsCollection getProcessList() {
    return $("span[id$=':selected-process_panel']").shouldBe(appear, DEFAULT_TIMEOUT).$$("table tbody tr");
  }

  public void inputUserField(int index, String value) {
    String fieldId = String.format(CUSTOM_USER_SELECTION_INPUT_PATTERN, index);
    $("[id='"+ fieldId +"']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(editable, DEFAULT_TIMEOUT).sendKeys(value);
    
    String dialogId = String.format(CUSTOM_USER_SELECTION_DIALOG_PATTERN, index);
    $("[id='"+ dialogId +"']").shouldBe(appear, DEFAULT_TIMEOUT).$$("td").get(0)
       .shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("[id='"+ dialogId +"']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void inputDateField(int index, String value) {
    String fieldId = String.format(CUSTOM_DATE_INPUT_PATTERN, index);
    $("[id='"+ fieldId +"']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(editable, DEFAULT_TIMEOUT).sendKeys(value);

    String dialogId = String.format(CUSTOM_DATE_DIALOG_PATTERN, index);
    $("[id='"+ dialogId +"']").shouldBe(appear, DEFAULT_TIMEOUT);
    unfocusInput();
  }

  public void inputStringField(int index, String value) {
    String fieldId = String.format(CUSTOM_STRING_INPUT_PATTERN, index);
    $("[id='"+ fieldId +"']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(editable, DEFAULT_TIMEOUT).sendKeys(value);
  }

  public WebElement getConfigurationDialog() {
    unfocusInput();
    return $("[id='"+ CONFIGURATION_DIALOG +"']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void unfocusInput() {
    $("[id='new-widget-configuration-dialog_title']").shouldBe(appear, DEFAULT_TIMEOUT).click();
  }
}
