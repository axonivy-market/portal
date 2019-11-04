package portal.guitest.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import vn.wawa.guitest.base.client.Browser;

public class ScreenshotFailedTestRule implements MethodRule {
  private static final String SCREENSHOT_FOLDER = "target" + File.separator + "test" + File.separator + "screenshot"
      + File.separator + "";

  @Override
  public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, Object arg2) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try {
          statement.evaluate();
        } catch (Throwable t) {
          t.printStackTrace();
          captureScreenshot(frameworkMethod.getName());
          throw t;
        } finally {
          try {
            Browser.getBrowser().shutdown();
          } catch (Exception e) {
            captureScreenshot(frameworkMethod.getName() + "-shutdown-error");
            Sleeper.sleep(5000);
            Browser.getBrowser().shutdown();
          }
        }
      }

      public void captureScreenshot(String methodName) throws IOException {
        WebDriver driver = Browser.getBrowser().getDriver();
        new File(SCREENSHOT_FOLDER).mkdirs();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File fileScreenShot = new File(SCREENSHOT_FOLDER + methodName + "_Failed.jpg");

        FileUtils.copyFile(screenshot, fileScreenShot);
      }
    };
  }

}
