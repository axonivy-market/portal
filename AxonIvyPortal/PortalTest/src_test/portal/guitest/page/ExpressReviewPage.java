package portal.guitest.page;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExpressReviewPage extends TaskTemplatePage {
  public void finish() {
    click(By.id("form:acknowledged"));
  }

  public String getApprovalResult() {
    return findListElementsByCssSelector("div[id*='approval-result-table'] td").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }
}
