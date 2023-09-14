package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessHistoryPage extends TemplatePage{

  @Override
  protected String getLoadedLocator() {
    return LAYOUT_WRAPPER;
  }
  
  @SuppressWarnings("deprecation")
  public SelenideElement getProcessHistoryDialog() {
    $("button[id='process-history-dialog-button']").shouldBe(getClickableCondition()).click();
    $("div[id='process-history-dialog_content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id='process-history-dialog']").shouldBe(Condition.appear);
  }
}
