package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class GlobalGrowlIframeTemplatePage extends TemplatePage {

  private static final String CANCEL_BUTTON_ID = "content-form:cancel";
  private static final String PROCEED_BUTTON_ID = "content-form:proceed";
  private static final String COMMENT_LABEL_ID = "content-form:comment";
  private static final String CUSTOMIZED_COMMENT_LABEL = "Customized comment";

  @Override
  protected String getLoadedLocator() {
    return "#content";
  }

  public NewDashboardPage clickCancel() {
    return clickButtonAndLeaveIFrame(CANCEL_BUTTON_ID);
  }

  public NewDashboardPage clickProceed() {
    return clickButtonAndLeaveIFrame(PROCEED_BUTTON_ID);
  }

  /**
   * Proceeds to a follow-up task rendered in the same IFrame, so it stays in that IFrame instead of
   * returning to the dashboard.
   */
  public void clickProceedToNextTask() {
    clickButton(PROCEED_BUTTON_ID);
    $("label[id='" + COMMENT_LABEL_ID + "']").shouldHave(Condition.text(CUSTOMIZED_COMMENT_LABEL), DEFAULT_TIMEOUT);
  }

  private NewDashboardPage clickButtonAndLeaveIFrame(String buttonId) {
    clickButton(buttonId);
    switchToDefaultContent();
    return new NewDashboardPage();
  }

  private void clickButton(String buttonId) {
    waitForElementDisplayed(By.id(buttonId), true);
    SelenideElement button = $("button[id='" + buttonId + "']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    clickByJavaScript(button);
  }
}
