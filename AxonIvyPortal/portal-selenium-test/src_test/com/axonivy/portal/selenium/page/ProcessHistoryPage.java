package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessHistoryPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return LAYOUT_WRAPPER;
  }

  public int countCases() {
    return $(".ui-datascroller-list").$$(By.className("ui-datascroller-item")).size();
  }

  public boolean isEmptyMessageDisplay() {
    return isElementDisplayed(By.className("process-history-empty-message"));
  }

  public int openDialogAndCountCases() {
    waitForElementClickableThenClick($(By.id("process-history-dialog-button")));
    waitForElementDisplayed(By.cssSelector("ul.ui-datascroller-list li div.js-case-item.case-list-item"), true);
    return $(".ui-datascroller-list").$$(By.className("ui-datascroller-item")).size();
  }

  public SelenideElement getProcessHistoryDialog() {
    $("button[id='process-history-dialog-button']").shouldBe(getClickableCondition()).click();
    $("div[id='process-history-dialog_content']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id='process-history-dialog']").shouldBe(Condition.appear);
  }
}
