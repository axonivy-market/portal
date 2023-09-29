package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;
import portal.guitest.common.WaitHelper;

public class ExpressManagementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('admin-setting-component:adminTabView:express-management-component:express-management-form')";
  }

  public void openImportDialog() {
    click(findElementByCssSelector("button[id$=':express-management-form:import-express-btn']"));
    waitForElementDisplayed(findElementByCssSelector("[id$=':import-express-form:express-process-upload_label']"), true);
  }

  public WebElement getImportExpressDialog() {
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-fileupload.express-process-upload", CLASS_PROPERTY);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ajax-indicator\\\\:ajax-indicator-ajax-indicator_start", ID_PROPERTY);
    return findElementByCssSelector("[id$=':express-management-component:import-express-dialog']");
  }
  
  public boolean isImportDialogDisplayed() {
    WebElement webElement = findElementByCssSelector("div[id$=':import-express-dialog']");
    return webElement.isDisplayed();
  }

  public void selectJSONFile(String pathToFile) {
    findElementByCssSelector("*[id$=':express-process-upload_input']").sendKeys(pathToFile);
    // currently haven't found solution to check when the file upload finish, we have to wait
    Sleeper.sleep(2000);
  }

  public void clickOnCloseButton() {
    WebElement closeButton = findElementByCssSelector("*[id$=':close-import-express']");
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> closeButton.isEnabled());
    click(closeButton);
    waitForElementDisplayed(By.cssSelector("*[id$=':close-import-express']"), false);
  }

  public void clickOnDeployExpress() {
    WebElement deployButton = waitForDeployButtonEnabled();
    click(deployButton);
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:express-management-component:import-express-form:impress-export-output"), true);
    waitForElementEnabled(By.id("admin-setting-component:adminTabView:express-management-component:close-import-express"), true, DEFAULT_TIMEOUT);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "adminui\\\\:adminTabView\\\\:express-management-component\\\\:close-import-express", ID_PROPERTY);
  }

  public WebElement waitForDeployButtonEnabled() {
    WebElement deployButton = findElementByCssSelector(".ui-fileupload-upload");
    waitForElementDisplayed(deployButton, true);
    return deployButton;
  }

  public String getUploadMessage() {
    waitForElementDisplayed(By.cssSelector("div[class$='ui-fileupload-messages'] span[class$='ui-messages-error-summary']"), true);
    return driver.findElement(By.cssSelector("div[class$='ui-fileupload-messages'] span[class$='ui-messages-error-summary']")).getText();
  }
  
  public void clickOnSelectAllExpresses() {
    var importHeader = findElementByCssSelector("[id$=':express-management-form:express-workflow-summary-table_head']");
    WaitHelper.assertTrueWithWait(() -> {
      var checkboxAll = importHeader.findElement(By.cssSelector(".ui-chkbox-box.ui-corner-all"));
      return !checkboxAll.getAttribute(CLASS_PROPERTY).contains("ui-state-disabled");
    });
    selectExpressCheckboxByIndex(0);
  }

  public void selectExpressCheckboxByIndex(int index) {
    WebElement expressTable = findElementByCssSelector("[id$=':express-management-component:express-management-form:express-workflow-summary-table']");
    List<WebElement> checkboxSelections = findChildElementsByClassName(expressTable, "express-selection-column");
    if (checkboxSelections.size() > index) {
      var checkbox = findChildElementByClassName(checkboxSelections.get(index), "ui-chkbox-box");
      click(checkbox);
      waitUntilAnimationFinished(DEFAULT_TIMEOUT, checkbox.getAttribute(CLASS_PROPERTY).replace(" ", "."), CLASS_PROPERTY);
    }
  }

  public void clickOnExportButton() {
    waitForElementEnabled(By.cssSelector("[id$=':express-management-component:express-management-form:export-express-btn']"), true, DEFAULT_TIMEOUT);
    click(findElementByCssSelector("[id$=':express-management-component:express-management-form:export-express-btn']"));
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:express-management-component:export-express-dialog"), true);
  }

  public WebElement getExportExpressDialog() {
    return findElementByCssSelector("[id$=':express-management-component:export-express-dialog']");
  }

  public void deployExpressFile() {
    clickOnDeployExpress();
    clickOnCloseButton();
  }

}
