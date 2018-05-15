package portal.guitest.page;

import org.openqa.selenium.By;

public class ExpressTaskPage extends TaskTemplatePage {
  public void finish() {
    click(By.id("form:ok-btn"));
  }
}
