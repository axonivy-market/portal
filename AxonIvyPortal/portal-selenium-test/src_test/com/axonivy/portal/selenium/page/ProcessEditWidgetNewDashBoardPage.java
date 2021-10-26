package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessEditWidgetNewDashBoardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'new-widget-configuration-dialog')]";
  }
  
  public void selectImageMode(String processName) {
    selectImageMode();
    selectProcess(processName);
    clickSaveProcessWidget();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private void selectProcess(String processName) {
    $("span[id$=':selected-image-process']").find("input").clear();
    $("span[id$=':selected-image-process']").find("input").sendKeys(processName);
    $$("tr[data-item-label='" + processName + "']").get(0).click();
  }

  private void selectImageMode() {
    $("div[id$=':process-display-mode']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("li[data-label='Image mode']").click();
    $("span[id$=':selected-image-process']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  public void previewImageMode(String processName) {
    selectImageMode();
    selectProcess(processName);
    $("button[id$='preview-button']").click();
  }
  
  public SelenideElement getStartButton() {
    return $("button[id$=':start-button']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getMoreInformationLink() {
    return $("span[id$=':more-information']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void changeImageProcess(String newProcessName) {
    selectProcess(newProcessName);
    clickSaveProcessWidget();
  }

  private void clickSaveProcessWidget() {
    $("button[id='widget-configuration-save-button']").click();
  }
}
