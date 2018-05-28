package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class ExpressReviewPage extends TaskTemplatePage {
  public void finish() {
    click(By.id("form:acknowledged"));
  }

  public String getApprovalResult() {
    List<WebElement> openFieldSetButtons = findListElementsByCssSelector("fieldset[id *= ':finished-task-fieldset'] .ui-icon-plusthick");
    for (WebElement elem : openFieldSetButtons) {
      elem.click();
    }
    Sleeper.sleepTight(2000);
    return findListElementsByCssSelector("div[id*='approval-result-table'] td").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }
}
