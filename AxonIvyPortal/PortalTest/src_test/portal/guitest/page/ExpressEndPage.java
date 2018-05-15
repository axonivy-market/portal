package portal.guitest.page;

import org.openqa.selenium.By;

public class ExpressEndPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "id('form:close-button')";
  }

  public void finish() {
    click(By.id("form:close-button"));
  }
}
