package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.WaitHelper;

public class ExpressApprovalPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form']";
  }

  public void approve() {
    WaitHelper.waitForNavigation(() -> clickOnApprove());

  }

  public void clickOnApprove() {
    $("button[id$='approve-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void reject() {
    WaitHelper.waitForNavigation(
        () -> $("[id='form:refuse-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }

  public void waitForCommentContainerDisplay() {
    $(".approval-comment-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void comment(String comment) {
    $("textarea[id$='comment']").shouldBe(appear, DEFAULT_TIMEOUT).clear();
    $("textarea[id$='comment']").click();
    $("textarea[id$='comment']").sendKeys(comment);
  }
}
