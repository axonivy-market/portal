package portal.guitest.page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import portal.guitest.common.DateTimePattern;

public class NewAbsencePage extends TemplatePage {

  private static final String ABSENCE_ERROR_MESSAGE_SELECTOR = "[id*='absence-messages'] span.ui-messages-error-summary";

  @Override
  protected String getLoadedLocator() {
    return "id('absence-dialog_title')";
  }
  
  public void input(LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");

    WebElement commentInput = findElementByCssSelector("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }

  @SuppressWarnings("deprecation")
  public void input(String fullName, LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    if (StringUtils.isNotEmpty(fullName)) {
      String usernameSelector = "input[id*='absence-username']";
      waitForElementDisplayed(By.cssSelector(usernameSelector), true);
      WebElement usernameInput = findElementByCssSelector(usernameSelector);
      usernameInput.clear();
      usernameInput.sendKeys(fullName);
      waitAjaxIndicatorDisappear();
      String itemSelector = "tr[data-item-label*='" + fullName  + "'].ui-state-highlight";
      waitForElementDisplayed(By.cssSelector(itemSelector), true);
      clickByCssSelector(itemSelector);
      waitAjaxIndicatorDisappear();
    }
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
    WebElement commentInput = findElementByCssSelector("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }

  private void inputDate(LocalDate absenceFrom, String inputCssSelector) {
    waitForElementDisplayed(By.cssSelector(inputCssSelector), true);
    WebElement fromInput = findElementByCssSelector(inputCssSelector);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePattern.DATE_PATTERN);
    fromInput.clear();
    fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    fromInput.sendKeys(Keys.BACK_SPACE);
    fromInput.sendKeys(absenceFrom.format(formatter));
  }

  public boolean isErrorMessageDisplayed() {
    waitForElementDisplayed(By.cssSelector(ABSENCE_ERROR_MESSAGE_SELECTOR), true);
    return true;
  }

  public String getErrorMessage() {
    WebElement errorMessage = findElementByCssSelector(ABSENCE_ERROR_MESSAGE_SELECTOR);
    return errorMessage.getText();
  }

  @SuppressWarnings("deprecation")
  public void proceed() {
    clickByCssSelector("button[id*='save-absence']");
    waitAjaxIndicatorDisappear();
  }

  @SuppressWarnings("deprecation")
  public void closeAddAbsenceDialog() {
    clickByCssSelector("a[id*='close-add-absence-dialog']");
    waitForElementDisplayed(By.cssSelector("[id$='absence-dialog']"), false);
    waitAjaxIndicatorDisappear();
  }

  public void enterCommentForAbsence(String comment) {
    waitForElementDisplayed(By.cssSelector("[id$='absence-dialog']"), true);
    waitForElementDisplayed(By.cssSelector("[id$='absence-form']"), true);
    clickByJavaScript(findElementByCssSelector("[id$='absence-form:comment']"));
    WebElement commentInput = findElementByCssSelector("textarea[id*='comment']");
    commentInput.sendKeys(comment);
  }
}
