package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import portal.guitest.common.FileHelper;

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
    clickByCssSelector("button[id$='dashboard-modification-component:dashboard-table:0:configure-dashboard']");
    // waitForElementDisplayed(By.cssSelector("[id$=':dashboard-modification-container']"),
    // true);
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
    findElementByCssSelector("[id$=':dashboard-title']").sendKeys("My dashboard");
  }

  public void createPrivateDashboard() {
    selectPrivateDashboardType();
    clickByCssSelector("button[id$='create-dashboard-action']");
    waitForElementDisplayed(By.cssSelector("div[id$=':create-new-dashboard-section']"), true);
  }

  public void cancelCreateDashboard() {
    clickByCssSelector("a[id$='dashboard-creation-component:dashboard-detail-close-button']");
  }

  public void clickOkCreateDashboard() {
    clickByCssSelector("button[id$='dashboard-creation-component:dashboard-create-button']");
  }

  public void openEditPrivateDashboards() {
    selectPrivateDashboardType();
    clickByCssSelector("a[id$='edit-dashboard-action'].js-private-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='dashboard-modification-container']"), true);
  }

  public void closeAddDashboardDialog() {
    waitForElementDisplayed(By.cssSelector("[id='dashboard-template-selection-component:create-new-dashboard-dialog']"), true);
    WebElement element = findElementByCssSelector("[id='dashboard-template-selection-component:create-new-dashboard-dialog']");
    waitAjaxIndicatorDisappear();
    element.findElement(By.cssSelector("a.ui-dialog-titlebar-close")).click();
  }

  public void reorderPrivateDashboards() {
    selectPrivateDashboardType();
    clickByCssSelector("a[id$='reorder-dashboard-action'].js-private-dashboard");
    waitForElementDisplayed(By.cssSelector("[id$='reorder-dashboard-form:dashboard-table']"), true);
  }

  public void createPublicDashboardFromScratch() {
    selectPublicDashboardType();
    clickByCssSelector("button[id$='create-dashboard-action']");
    waitForElementDisplayed(By.cssSelector("div[id$=':create-new-dashboard-section']"), true);
    clickByCssSelector("a[id$=':create-from-scratch']");
    waitForElementDisplayed(By.cssSelector("div[id$=':dashboard-creation-details-dialog']"), true);
    findElementByCssSelector("[id$=':dashboard-title']").sendKeys("My dashboard");
  }

  public void openEditPublicDashboards() {
    selectPublicDashboardType();
    clickByCssSelector("button[id$='dashboard-modification-component:dashboard-table:0:edit']");
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

  public WebElement getAddLanguageButton() {
    return findElementByCssSelector("button[id$=':add-language-button']");
  }

  public void openMultiLanguageDialog() {
    clickByCssSelector("button[id$=':add-language-button']");
    waitForElementDisplayed(
        By.cssSelector("div[id$=':dashboard-creation-component:title-language-config:multiple-languages-dialog']"),
        true);
  }

  public void cancelMultiLanguageDialog() {
    clickByCssSelector("a[id$=':multi-language-cancel-button']");
  }

  public void clickOkMultiLanguageDialog() {
    waitForElementDisplayed(By.cssSelector("button[id$=':multi-language-ok-button']"), true);
    clickByCssSelector("button[id$=':multi-language-ok-button']");
  }

  public WebElement getDashboardMultiLanguageDialog() {
    waitForElementDisplayed(By.cssSelector(
        "div[id$='dashboard-template-selection-component:dashboard-creation-component:title-language-config:multiple-languages-dialog']"),
        true);
    return findElementById(
        "dashboard-template-selection-component:dashboard-creation-component:title-language-config:multiple-languages-dialog");
  }

  public void openImportPublicDashboards() {
    selectPublicDashboardType();
    waitForElementDisplayed(By.cssSelector("div[id$=':create-new-dashboard-section']"), true);
    openImportDashboardDialog();
  }

  public void openImportPrivateDashboards() {
    createPrivateDashboard();
    openImportDashboardDialog();
  }

  public void openImportDashboardDialog() {
    clickByCssSelector("a[id$=':import-dashboard']");
    waitForElementDisplayed(By.cssSelector("div[id$=':dashboard-import-dialog']"), true);
    findElementByCssSelector("[id$=':dashboard-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile("Dashboard_Dashboard_Export.json"));
    waitForElementDisplayed(By.cssSelector("input[id$=':import-dashboard-form:import-dashboard-title']"), true);
  }


  public WebElement getImportDashboardDialog() {
    mouseOver(findElementByCssSelector("input[id$=':import-dashboard-form:import-dashboard-title']"));
    waitForElementDisplayed(By.cssSelector("input[id$=':import-dashboard-form:import-dashboard-title']"), true);
    return findElementByCssSelector("div[id$=':dashboard-import-dialog']");
  }

  public void clickOnTextToTranslate(int index) {
    clickByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", index));
    waitForElementDisplayed(By.cssSelector("div[id$=':overlay-panel-input']"), true);
  }

  public void setTranslatedTitle() {
    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 1))
        .sendKeys(Keys.CONTROL, "a");
    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 1))
        .sendKeys("Mon tableau de bord");

    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 2))
        .sendKeys(Keys.CONTROL, "a");
    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 2))
        .sendKeys("Mein Armaturenbrett");
    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 3))
        .sendKeys(Keys.CONTROL, "a");
    findElementByCssSelector(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 3))
        .sendKeys("Mi cuadro de mandos");
  }

  public WebElement getShareDashboardDialog() {
    clickByCssSelector("button[id$=':share-dashboard']");
    waitForElementDisplayed(By.cssSelector("div[id$=':share-dashboard-dialog']"), true);
    return findElementByCssSelector("div[id$=':share-dashboard-dialog']");
  }
}
