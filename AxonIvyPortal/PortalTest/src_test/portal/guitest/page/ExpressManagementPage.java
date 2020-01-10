package portal.guitest.page;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
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
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> assertTrue(findElementByCssSelector("*[id$=':import-express-dialog']").isDisplayed()));
    assertTrue(isImportDialogDisplayed());
  }

  public boolean isImportDialogDisplayed() {
    WebElement webElement = findElementByCssSelector("*[id$=':import-express-dialog']");
    return webElement.isDisplayed();
  }

  public void selectJSONFile(String pathToFile) {
    WebElement selectButton = findElementByCssSelector("*[id$=':express-process-upload_label']");
    try {
      click(selectButton);
    } catch (UnhandledAlertException e) {
      Alert alert = driver.switchTo().alert();
      alert.accept();
    }
    StringSelection ss = new StringSelection(pathToFile);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot;
    try {
      robot = new Robot();
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
    } catch (AWTException e) {
      e.printStackTrace();
    }
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
    WebElement message = findElementByCssSelector(".ui-messages.ui-fileupload-messages .ui-messages-error-summary");
    return message.getText();
  }

}
