package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class ExpressReviewPage extends TaskTemplatePage {
  public void finish() {
    clickByCssSelector("button[id$='acknowledged']");
  }

  public String getApprovalResult() {
    List<WebElement> openFieldSetButtons = findListElementsByCssSelector("fieldset[id *= ':approval-result-fieldset'] .ui-icon-plusthick");
    for (WebElement elem : openFieldSetButtons) {
      click(elem);
    }
    Sleeper.sleep(2000);
    return findListElementsByCssSelector("div[id*='approval-result'] td").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }
}
