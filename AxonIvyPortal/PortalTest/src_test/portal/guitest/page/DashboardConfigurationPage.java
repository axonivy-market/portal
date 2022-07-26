package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
  
  public void selectPrivateDashboardType() {
    waitForElementDisplayed(By.cssSelector("[id$='dashboard-configuration-type']"), true);
    clickByCssSelector("a[id$='private-dashboard-type']");
    waitForElementPresent(By.cssSelector(".dashboard-configuration__content.js-private-dashboard-configuration"), true);
  }

  public void selectEditPublicDashboards() {
    waitForElementDisplayed(By.cssSelector(".dashboard-configuration__content.js-public-dashboard-configuration"), true);
    clickByCssSelector("a[id$='edit-dashboard-action'].js-public-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$=':dashboard-modification-container']"), true);
  }
  
  public WebElement getDashboardConfigurationPage() {
    return findElementById("configuration-group");
  }
  public WebElement getPublicConfigurationContent() {
    return findElementByCssSelector(".dashboard-configuration__content.js-public-dashboard-configuration");
  }
  
  public WebElement getPrivateConfigurationContent() {
    return findElementByCssSelector(".dashboard-configuration__content.js-private-dashboard-configuration");
  }
  
  public void createPrivateDashboardFromScratch() {
    createPrivateDashboard();
    clickByCssSelector("a[id$=':create-from-scratch']");
    waitForElementDisplayed(By.cssSelector("div[id$=':dashboard-creation-details-dialog']"), true);
  }
  
  public void createPrivateDashboard() {
    selectPrivateDashboardType();
    clickByCssSelector("a[id$='create-dashboard-action'].js-private-dashboard");
    waitForElementDisplayed(By.cssSelector("div[id$=':create-new-dashboard-section']"), true);
  }
  
  public void cancelCreateDashboard() {
    clickByCssSelector("a[id$='dashboard-creation-component:dashboard-detail-close-button']");
  }
  
  public void openEditPrivateDashboards() {
    selectPrivateDashboardType();
    clickByCssSelector("a[id$='edit-dashboard-action'].js-private-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='dashboard-modification-container']"), true);
  }
  
  public void reorderPrivateDashboards() {
    selectPrivateDashboardType();
    clickByCssSelector("a[id$='reorder-dashboard-action'].js-private-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='reorder-dashboard-form:dashboard-table']"), true);
  }
  
  public void createPublicDashboardFromScratch() {
    selectPublicDashboardType();
    clickByCssSelector("a[id$='create-dashboard-action'].js-public-dashboard");
    waitForElementDisplayed(By.cssSelector("div[id$=':create-new-dashboard-section']"), true);
    clickByCssSelector("a[id$=':create-from-scratch']");
    waitForElementDisplayed(By.cssSelector("div[id$=':dashboard-creation-details-dialog']"), true);
  }
  
  public void openEditPublicDashboards() {
    selectPublicDashboardType();
    clickByCssSelector("a[id$='edit-dashboard-action'].js-public-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='dashboard-modification-container']"), true);
  }
  
  public void reorderPublicDashboards() {
    selectPublicDashboardType();
    clickByCssSelector("a[id$='reorder-dashboard-action'].js-public-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='reorder-dashboard-form:public-dashboard-table']"), true);
  }
  
  public WebElement getDashboardTemplates() {
    return findElementByCssSelector("[id$=':create-new-dashboard-form']");
  }
  
  public WebElement getDashboardCreationDialog() {
    mouseOver(findElementByCssSelector("input[id$=':dashboard-detail-form:dashboard-title']"));
    waitForElementDisplayed(By.cssSelector("input[id$=':dashboard-detail-form:dashboard-title']"), true);
    return findElementByCssSelector("div[id$=':dashboard-creation-details-dialog']");
  }
}
