package portal.guitest.page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import portal.guitest.common.DateTimePattern;

public class NewAbsencePage extends TemplatePage {

  private static final String ABSENCE_ERROR_MESSAGE_SELECTOR = "[id*='absence-messages'] span.ui-messages-error-summary";

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings:add-new-absence-dialog_title')";
  }
  
  public void input(LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");

    WebElement commentInput = findElementByCssSelector("input[id*='comment']");
    commentInput.sendKeys(comment);
  }

  public void input(String username, LocalDate absenceFrom, LocalDate absenceTill, String comment) {
    if (StringUtils.isNotEmpty(username)) {
      String usernameSelector = "input[id*='username']";
      waitForElementDisplayed(By.cssSelector(usernameSelector), true);
      WebElement usernameInput = findElementByCssSelector(usernameSelector);
      usernameInput.clear();
      usernameInput.sendKeys(username);
      waitAjaxIndicatorDisappear();
      String itemSelector = "li[data-item-label*='" + username + "'].ui-state-highlight";
      waitForElementDisplayed(By.cssSelector(itemSelector), true);
      clickByCssSelector(itemSelector);
      waitAjaxIndicatorDisappear();
    }
    inputDate(absenceFrom, "input[id*='absence-start-date']");
    inputDate(absenceTill, "input[id*='absence-end-date']");
    WebElement commentInput = findElementByCssSelector("input[id*='comment']");
    commentInput.sendKeys(comment);
  }

  private void inputDate(LocalDate absenceFrom, String inputCssSelector) {
    waitForElementDisplayed(By.cssSelector(inputCssSelector), true);
    WebElement fromInput = findElementByCssSelector(inputCssSelector);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimePattern.DATE_PATTERN);
    fromInput.clear();
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

  public AddAbsencePage proceed() {
    clickByCssSelector("button[id*='next-to-deputy']");
    return new AddAbsencePage();
  }

}
