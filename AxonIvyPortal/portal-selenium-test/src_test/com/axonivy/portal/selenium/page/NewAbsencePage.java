package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.DateTimePattern;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class NewAbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[class*='absence-dialog-header']";
  }

  public void input(LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");

    WebElement commentInput = $("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }

  public void updateDates(LocalDate absenceFrom, LocalDate absenceTill) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
  }

  public void input(String fullName, LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    $("textarea[id*='comment']").sendKeys(comment);
    $("div[class*='absence-dialog-header']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    if (StringUtils.isNotEmpty(fullName)) {
      WebElement usernameInput = $("input[id*='absence-username']").shouldBe(appear, DEFAULT_TIMEOUT);
      usernameInput.clear();
      usernameInput.sendKeys(fullName);
      String itemSelector = "tr[data-item-label*='" + fullName + "'].ui-state-highlight";
      $(itemSelector).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
  }

  public void addDeputy(String fullName) {
    SelenideElement input = $("input[id$='absence-form:user-selection-component:user-selection_input']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    input.clear();
    input.sendKeys(fullName);
    String panelSelector = "[id$='absence-form:user-selection-component:user-selection_panel']";
    $(panelSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    String itemSelector = "tr[data-item-label*='" + fullName + "'].ui-state-highlight";
    $(itemSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(panelSelector).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    $("button[id$='absence-form:add-deputy-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void setDeputyAsPermanent(int deputyIndex) {
    openDeputyActionMenu(deputyIndex);
    $(String.format("[id$='absence-form:selected-deputy-panel:%d:change-deputy-type']", deputyIndex))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void removeDeputy(int deputyIndex) {
    openDeputyActionMenu(deputyIndex);
    $(String.format("[id$='absence-form:selected-deputy-panel:%d:delete-deputy']", deputyIndex))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public List<String> getSelectedDeputyNames() {
    return $$("tbody[id$='absence-form:selected-deputy-panel_data'] tr")
        .asFixedIterable()
        .stream()
        .map(row -> row.getText())
      .collect(Collectors.toList());
  }

  private void openDeputyActionMenu(int deputyIndex) {
    String actionButton = String.format("[id$='absence-form:selected-deputy-panel:%d:substitute-action-button']", deputyIndex);
    $(actionButton).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  private void inputDate(LocalDate absenceFrom, String inputCssSelector) {
    $(inputCssSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    WebElement fromInput = $(inputCssSelector);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePattern.DATE_PATTERN);
    fromInput.clear();
    fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    fromInput.sendKeys(Keys.BACK_SPACE);
    fromInput.sendKeys(absenceFrom.format(formatter));
    closeDatePickerPanel(inputCssSelector);
  }

  private void closeDatePickerPanel(String inputCssSelector) {
    String panelSelector = inputCssSelector.replace("input", "div").replace("']", "_panel']");
    SelenideElement panel = $(panelSelector);
    if (panel.exists() && panel.isDisplayed()) {
      Selenide.executeJavaScript("arguments[0].style.display = 'none'", panel);
    }
  }

  public boolean isAbsenceErrorMessageDisplayed() {
    return $("div[id&=':absence-messages']").shouldBe(appear, DEFAULT_TIMEOUT).exists();
  }

  public void proceed() {
    $("div[class*='absence-dialog-header']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("button[id*='save-absence']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    $("[id$='absence-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void closeAddAbsenceDialog() {
    $("div[class*='absence-dialog-header']").shouldBe(appear, DEFAULT_TIMEOUT).click();
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
