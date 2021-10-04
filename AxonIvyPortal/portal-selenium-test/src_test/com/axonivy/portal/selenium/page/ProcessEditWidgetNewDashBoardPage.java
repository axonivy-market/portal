package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class ProcessEditWidgetNewDashBoardPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'new-widget-configuration-dialog')]";
  }
  
  public void selectImageMode(String processName) {
    $("div[id$=':process-display-mode']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("li[data-label='Image mode']").click();
    
    $("span[id$=':selected-image-process']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("span[id$=':selected-image-process']").find("input").sendKeys(processName);
    $$("tr[data-item-label='" + processName + "']").get(0).click();
    $("button[id='widget-configuration-save-button']").click();
    $("div[id='new-widget-configuration-dialog']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }
}
