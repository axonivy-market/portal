package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;

public class NoteHistoryPage extends TemplatePage {

  private static final String TABLE_ROWS_PATH = "div[id*='notes-table'] table>tbody>tr";

  @Override
  protected String getLoadedLocator() {
    return "[id='form:notes-table']";
  }

  public int countNotes() {
    waitForElementDisplayed(By.cssSelector("div[id*='notes-table']"), true);
    return WebDriverRunner.getWebDriver().findElements(By.cssSelector(TABLE_ROWS_PATH)).size();
  }

  public int countDoneTasks() {
    waitForElementDisplayed(By.cssSelector("div[id*='notes-table']"), true);
    List<WebElement> elements =
        WebDriverRunner.getWebDriver().findElements(By.cssSelector(".case-task-note-histories"));
    int result = 0;
    if (CollectionUtils.isNotEmpty(elements)) {
      for (WebElement element : elements) {
        if (element.getText().startsWith("Task is done:")) {
          result++;
        }
      }
    }
    return result;
  }

  public String getNoteContentOfRow(int index) {
    WebElement firstRow = WebDriverRunner.getWebDriver().findElements(By.cssSelector(TABLE_ROWS_PATH)).get(index);
    return firstRow.findElements(By.xpath("td")).get(0).getText();
  }

  public String getCaseName() {
    return findElementById("form:case-name").getText();
  }

  public String getCaseState() {
    return findElementById("form:case-state:case-state-cell").getText();
  }

  public String getCaseId() {
    return findElementById("form:case-id").getText();
  }

  public List<String> getNoteAuthors() {
    ElementsCollection noteAuthorElements = $$("td.note-history-fullname-column .name-after-avatar")
        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(0), DEFAULT_TIMEOUT);
    return noteAuthorElements.asFixedIterable().stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  public void clickOnCheckboxShowSystemNotes() {
    findElementByCssSelector("[id$=':show-system-notes-checkbox']").click();
  }

  public void waitForNoteTableDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$=':task-note-table']"), true);
  }
}
