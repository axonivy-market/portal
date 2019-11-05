package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('absence-settings-dialog_title')";
  }

  public NewAbsencePage openNewAbsenceDialog() {
    String selector = "button[id*='add-absence']";
    waitForElementDisplayed(By.cssSelector(selector), true);
    clickByCssSelector(selector);
    waitAjaxIndicatorDisappear();
    return new NewAbsencePage();
  }

  public int countAbsences() {
    waitForElementDisplayed(By.cssSelector("a[id*='absence-table']"), true);
    return findListElementsByCssSelector("td.absences-table-action-column").size();
  }

  public void showAbsencesInThePast(boolean shown) {
    WebElement checkBox = findElementByCssSelector("input[id*='show-absence-in-the-past']");
    boolean checkBoxSelected = checkBox.isSelected();
    if (checkBoxSelected != shown) {
      clickByCssSelector("div[id*='show-absence-in-the-past'] div.ui-chkbox-box");
      waitAjaxIndicatorDisappear();
    }
  }

  public SettingDeputyPage openDeputyDialog() {
    clickByCssSelector("button[id*='edit-deputy']");
    waitAjaxIndicatorDisappear();
    return new SettingDeputyPage();
  }

}
