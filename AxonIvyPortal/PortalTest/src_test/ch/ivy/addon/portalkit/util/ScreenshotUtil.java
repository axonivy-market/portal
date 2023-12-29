package ch.ivy.addon.portalkit.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;
import portal.guitest.common.WaitHelper;
import vn.wawa.guitest.base.client.Browser;

public class ScreenshotUtil {
  private static final String SCREENSHOT_EXTENSION = ".png";
  private static final String SCREENSHOT_FOLDER = "target" + File.separator + "screenshots" + File.separator;
  public static final String LOGIN_FOLDER = "/login/";
  public static final String FORGOT_PASSWORD = "/forgot-password/";
  public static final String DASHBOARD_FOLDER = "/dashboard/";
  public static final String NEW_DASHBOARD_FOLDER = "/new-dashboard/";
  public static final String EXPRESS_FOLDER = "/express/";
  public static final String EXPRESS_MANAGEMENT_FOLDER = "/express-management/";
  public static final String PROCESSES_WIDGET_FOLDER = "/process/";
  public static final String PROCESSES_INFORMATION_WIDGET_FOLDER = "/process/information/";
  public static final String PROCESSES_PROCESS_IMAGE_FOLDER = "/process-image/customization/";
  public static final String TASK_WIDGET_FOLDER = "/task/";
  public static final String TASK_WIDGET_CUSTOMIZATION_FOLDER = "/task/customization/";
  public static final String TASK_DETAIL_FOLDER = "/task-detail/";
  public static final String TASK_DETAIL_CUSTOMIZATION_FOLDER = "/task-detail/customization/";
  public static final String CASE_WIDGET_FOLDER = "/case/";
  public static final String CASE_WIDGET_CUSTOMIZATION_FOLDER = "/case/customization/";
  public static final String CASE_DETAIL_FOLDER = "/case-detail/";
  public static final String CASE_DETAIL_CUSTOMIZATION_FOLDER = "/case-detail/customization/";
  public static final String STATISTIC_WIDGET_FOLDER = "/statistic/";
  public static final String TASK_ANALYSIS_FOLDER = "/statistic/task-analysis/";
  public static final String SEARCH_FOLDER = "/search/";
  public static final String SETTINGS_FOLDER = "/settings/";
  public static final String MY_PROFILE_FOLDER = "/my-profile/";
  public static final String CHAT_FOLDER = "/chat/";
  public static final String TASK_TEMPLATE_FOLDER = "/task-template/";
  public static final String COMPONENTS_FOLDER = "/components/";
  public static final String DEMO_FOLDER = "/demo-processes/";
  public static final String LAYOUT_FOLDER = "/layout-template/";
  public static final String ERROR_HANDLING_FOLDER = "/error-handling/";
  public static final String DASHBOARD_CONFIGURATION_FOLDER = "/dashboard-configuration/";
  
  @PostConstruct
  public void initFolder() {
    new File(SCREENSHOT_FOLDER).mkdirs();
  }
  
  public static void capturePageScreenshot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  
  public static void captureElementScreenshot(WebElement element, String screenshotName) throws IOException {
    File screenshot = element.getScreenshotAs(OutputType.FILE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  
  public static void captureElementWithMarginOptionScreenshot(WebElement element, String screenshotName, ScreenshotMargin screenshotMargin) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    addMarginForImage(element, screenshot, screenshotMargin);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  
  private static File addMarginForImage(WebElement element, File fileScreenShot, ScreenshotMargin screenshotMargin) throws IOException {
    BufferedImage original = ImageIO.read(fileScreenShot);
    int coordinateX = element.getLocation().getX() - screenshotMargin.getMarginLeft();
    int coordinateY = element.getLocation().getY() - screenshotMargin.getMarginTop();
    int width = element.getSize().getWidth() + screenshotMargin.getMarginRight() + screenshotMargin.getMarginLeft();
    int height = element.getSize().getHeight() + screenshotMargin.getMarginBottom() + screenshotMargin.getMarginTop();

    if (coordinateX < 0) {
      coordinateX = 0;
    } else if (coordinateX > original.getWidth()) {
      coordinateX = original.getWidth();
    }

    if (coordinateY < 0) {
      coordinateY = original.getMinY();
    } else if (coordinateY > original.getHeight()) {
      coordinateY = original.getHeight();
    }

    if (width > original.getWidth() || width + coordinateX > original.getWidth()) {
      width = original.getWidth() - coordinateX;
    }

    if (height > original.getHeight() || height + coordinateY > original.getHeight()) {
      height = original.getHeight() - coordinateY;
    }

    BufferedImage screenshot = original.getSubimage(
        coordinateX,
        coordinateY, 
        width, 
        height);
    ImageIO.write(screenshot, "png", fileScreenShot);
    return fileScreenShot;
  }

  public static void resizeBrowserAndCaptureWholeScreen(String screenshotName, Dimension size) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    Dimension oldSize = driver.manage().window().getSize();
    resizeBrowser(size);
    executeDecorateJs(driver);
    capturePageScreenshot(screenshotName);
    resizeBrowser(oldSize);
  }

  public static void resizeBrowserAndCaptureHalfRightScreen(String screenshotName, Dimension size) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    Dimension oldSize = driver.manage().window().getSize();
    resizeBrowser(size);
    executeDecorateJs(driver);
    captureHalfRightPageScreenShot(screenshotName);
    resizeBrowser(oldSize);
  }
  
  public static void captureHalfRightPageScreenShot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.RIGHT_SIDE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }

  private static File getHalfOfPageScreenshot(File screenshot, ScreenCoordinate coordinateSide) throws IOException {
    BufferedImage original = ImageIO.read(screenshot);
    int coordinateX = 0;
    int coordinateY = 0;
    int width = original.getWidth();
    int height = original.getHeight();
    switch (coordinateSide) {
      case TOP_SIDE:
        height = original.getHeight()/2;
        break;
      case RIGHT_SIDE:
        coordinateX = original.getWidth()/2;
        if (coordinateX + width > original.getWidth()) {
          width = original.getWidth() - coordinateX;
        }
        break;
      case BOTTOM_SIDE:
        break;
      case LEFT_SIDE:
        width = original.getWidth()/2;
        break;
      case TOP_RIGHT:
        coordinateX = original.getWidth()/2;
        if (coordinateX + width > original.getWidth()) {
          width = original.getWidth() - coordinateX;
        }
        height = original.getHeight()/2;
        break;
      case CENTER_TOP_SIDE:
        coordinateX = original.getWidth()/4;
        coordinateY = 0;
        width = original.getWidth()/2;
        height = original.getHeight()/2;
        break;
      default:
        break;
    }
    BufferedImage halfOfScreenshot = original.getSubimage(
        coordinateX,
        coordinateY, 
        width, 
        height);
    ImageIO.write(halfOfScreenshot, "png", screenshot);
    return screenshot;
  }
  
  public static void captureHalfLeftPageScreenShot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.LEFT_SIDE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  
  public static void resizeBrowser(Dimension size) {
    Browser.getBrowser().getDriver().manage().window().setSize(size);
    WaitHelper.assertTrueWithWait(() -> {
      Dimension currentSize = Browser.getBrowser().getDriver().manage().window().getSize();
      return currentSize.getWidth() == size.getWidth()
          && currentSize.getHeight() == size.getHeight();
    });
  }
  
  public static void maximizeBrowser() {
    Browser.getBrowser().getDriver().manage().window().maximize();
    Sleeper.sleep(300); // Wait for window resized successfully
  }

  private static void executeDecorateJs(WebDriver driver) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("scroll(0,0);");
    Sleeper.sleep(200); // Wait for JS executed successfully
  }

  public static void captureHalfCenterTopPageScreenShot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.CENTER_TOP_SIDE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  
  public static void captureHalfTopRightPageScreenShot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.TOP_RIGHT);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }

  public static void captureHalfTopPageScreenShot(String screenshotName, Dimension size) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    resizeBrowser(size);

    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.TOP_SIDE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
  public static void captureHalfTopPageScreenShot(String screenshotName) throws IOException {
    WebDriver driver = Browser.getBrowser().getDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    getHalfOfPageScreenshot(screenshot, ScreenCoordinate.TOP_SIDE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }

  public enum ScreenCoordinate {
    LEFT_SIDE, RIGHT_SIDE, TOP_SIDE, BOTTOM_SIDE, CENTER_TOP_SIDE, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
  }

  public static boolean isDOMStatusComplete() {
    return ((JavascriptExecutor) Browser.getBrowser().getDriver())
        .executeScript("return document.readyState").equals("complete");
  }
}
