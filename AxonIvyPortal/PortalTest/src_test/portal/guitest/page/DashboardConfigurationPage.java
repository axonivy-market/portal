package portal.guitest.page;

import org.openqa.selenium.By;

public class DashboardConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('configuration-group')";
  }

  public void configureDashboardByIndex(int index) {
    String configIconId = String.format("[id$=':dashboard-table:%d:configure-dashboard']", index);
    clickByCssSelector(configIconId);
  }

  public void selectPublicDashboardType() {
    waitForElementDisplayed(By.cssSelector("[id$='dashboard-configuration-type']"), true);
    clickByCssSelector("a[id$='public-dashboard-type']");
    waitForElementPresent(By.cssSelector(".dashboard-configuration__content.js-public-dashboard-configuration"), true);
  }

  public void selectEditPublicDashboards() {
    waitForElementDisplayed(By.cssSelector(".dashboard-configuration__content.js-public-dashboard-configuration"), true);
    clickByCssSelector("a[id$='edit-dashboard-action'].js-public-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$=':dashboard-modification-container']"), true);
  }
}
