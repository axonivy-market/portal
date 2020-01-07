package portal.guitest.page;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import ch.ivyteam.ivy.environment.Ivy;
import portal.guitest.common.Sleeper;

public class ExpressManagementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('express-helper-form')";
  }

  public void openImportDialog() {
    WebElement importButton = findElementById("express-helper-form:import-express-btn");
    click(importButton);
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS))
        .until(() -> assertTrue(findElementById("import-express-dialog").isDisplayed()));
    assertTrue(isImportDialogDisplayed());
  }

  public boolean isImportDialogDisplayed() {
    WebElement webElement = findElementById("import-express-dialog");
    return webElement.isDisplayed();
  }

  public String selectJSONFile(String pathToFile) {
    String errorMessage = StringUtils.EMPTY;
    WebElement selectButton = findElementById("import-express-form:express-process-upload_label");
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
    WebElement deployButton = findElementByCssSelector(".ui-fileupload-upload");
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> deployButton.isEnabled());
    click(deployButton);
    Sleeper.sleep(5000);
    WebElement message = findElementById("import-express-form:import-express-dialog-message");
    errorMessage = message.getText();

    WebElement closeButton = findElementById("close-import-express");
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> closeButton.isEnabled());
    click(closeButton);
    return errorMessage;
  }

}
