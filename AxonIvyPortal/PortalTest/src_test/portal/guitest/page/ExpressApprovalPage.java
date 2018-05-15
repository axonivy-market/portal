package portal.guitest.page;

import org.openqa.selenium.By;

public class ExpressApprovalPage extends TaskTemplatePage {
  public void finish() {
    click(By.id("form:approve-btn"));
  }

  public void comment(String comment) {
    findElementByCssSelector("textarea[id*='comment']").sendKeys(comment);
  }
}
