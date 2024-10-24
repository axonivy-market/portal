package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.DateTimePattern;
import com.codeborne.selenide.Condition;

public class NewAbsencePage extends TemplatePage {

  private static final String ABSENCE_ERROR_MESSAGE_SELECTOR =
      "[id*='absence-messages'] span.ui-messages-error-summary";

  @Override
  protected String getLoadedLocator() {
    return "[id='absence-dialog_title']";
  }

  public void input(LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");

    WebElement commentInput = $("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }

  public void input(String fullName, LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
    $("textarea[id*='comment']").sendKeys(comment);
    $(By.id("absence-dialog_title")).click();
    if (StringUtils.isNotEmpty(fullName)) {
      WebElement usernameInput = $("input[id*='absence-username']").shouldBe(appear, DEFAULT_TIMEOUT);
      usernameInput.clear();
      usernameInput.sendKeys(fullName);
      String itemSelector = "tr[data-item-label*='" + fullName + "'].ui-state-highlight";
      $(itemSelector).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
  }

  private void inputDate(LocalDate absenceFrom, String inputCssSelector) {
    $(inputCssSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    WebElement fromInput = $(inputCssSelector);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePattern.DATE_PATTERN);
    fromInput.clear();
    fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    fromInput.sendKeys(Keys.BACK_SPACE);
    fromInput.sendKeys(absenceFrom.format(formatter));
  }

  public boolean isErrorMessageDisplayed() {
    $(ABSENCE_ERROR_MESSAGE_SELECTOR).shouldBe(appear, DEFAULT_TIMEOUT);
    return true;
  }

  public String getErrorMessage() {
    WebElement errorMessage = $(ABSENCE_ERROR_MESSAGE_SELECTOR);
    return errorMessage.getText();
  }

  public void proceed() {
    $(By.id("absence-dialog_title")).click();
    $("button[id*='save-absence']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
  }

  public void closeAddAbsenceDialog() {
    $("[id='absence-dialog_title']").click();
    $("div[id='absence-dialog']").$("span.ui-icon-closethick").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='absence-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void enterCommentForAbsence(String comment) {
    $("[id$='absence-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='absence-form']").shouldBe(appear, DEFAULT_TIMEOUT);
    clickByJavaScript($("[id$='absence-form:comment']"));
    WebElement commentInput = $("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }
}
