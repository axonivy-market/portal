package portal.guitest.page;

import static junit.framework.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;

public class ExpressManagementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('adminui:adminTabView:express-management-component:express-management-form')";
  }

  public void openImportDialog() {
    WebElement importButton = findElementByCssSelector("*[id$=':import-express-btn']");
    click(importButton);
    refreshAndWaitElement("div[id$=':import-express-dialog']");
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> assertTrue(findElementByCssSelector("div[id$=':import-express-dialog']").isDisplayed()));
    //assertTrue(isImportDialogDisplayed());
  }

  public boolean isImportDialogDisplayed() {
    WebElement webElement = findElementByCssSelector("div[id$=':import-express-dialog']");
    return webElement.isDisplayed();
  }

  public void selectJSONFile(String pathToFile) {
    findElementByCssSelector("*[id$=':express-process-upload_input']").sendKeys(pathToFile);
    // currently haven't found solution to check when the file upload finish, we have to wait
    if (isIntegrationTestRun()) {
      Sleeper.sleep(10000);
    } else {
      Sleeper.sleep(5000);
    }
  }

  public void clickOnCloseButton() {
    WebElement closeButton = findElementByCssSelector("*[id$=':close-import-express']");
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> closeButton.isEnabled());
    click(closeButton);
  }

  public void clickOnDeployExpress() {
    WebElement deployButton = findElementByCssSelector(".ui-fileupload-upload");
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> deployButton.isEnabled());
    click(deployButton);
  }

  public String getUploadMessage() {
    Sleeper.sleep(1000);
    return driver.findElement(By.cssSelector("div[class$='ui-fileupload-messages'] span[class$='ui-messages-error-summary']")).getText();
  }

}
