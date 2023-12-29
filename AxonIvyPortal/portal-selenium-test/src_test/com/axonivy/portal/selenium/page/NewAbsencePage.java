package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;

import com.axonivy.portal.selenium.common.DateTimePattern;
import com.codeborne.selenide.SelenideElement;

public class NewAbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='absence-dialog_title']";
  }

  public void enterCommentForAbsence(String comment) {
    $("[id='absence-dialog_title']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='absence-form']").shouldBe(appear, DEFAULT_TIMEOUT).$("[id$='absence-form:comment']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("textarea[id*='comment']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(comment);
  }

  public void closeAddAbsenceDialog() {
    $("a[id*='close-add-absence-dialog']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();;
    $("[id$='absence-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
  }

  public void input(LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
    $("textarea[id*='comment']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(comment);
  }

  public void input(String fullName, LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    if (StringUtils.isNotEmpty(fullName)) {
      SelenideElement usernameInput = $("input[id*='absence-username']").shouldBe(appear, DEFAULT_TIMEOUT);
      usernameInput.clear();
      usernameInput.sendKeys(fullName);

      String itemSelector = "tr[data-item-label*='" + fullName  + "'].ui-state-highlight";
      $(itemSelector).shouldBe(appear, DEFAULT_TIMEOUT).click();
      waitForAjaxIndicatorDisappeared();
    }
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
    $("textarea[id*='comment']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(comment);
  }

  private void inputDate(LocalDate absenceFrom, String inputCssSelector) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePattern.DATE_PATTERN);
    SelenideElement fromInput = $(inputCssSelector).shouldBe(appear, DEFAULT_TIMEOUT);

    fromInput.clear();
    fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    fromInput.sendKeys(Keys.BACK_SPACE);
    fromInput.sendKeys(absenceFrom.format(formatter));
    $("[id='absence-dialog_title']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void proceed() {
    $("button[id*='save-absence']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='absence-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
}
