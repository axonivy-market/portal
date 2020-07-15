package portal.guitest.common;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

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
            System.out.println("Before shutdown " + new Date());
            Browser.getBrowser().shutdown();
            System.out.println("After shutdown " + new Date());
          } catch (Exception e) {
            System.out.println("Exception " + new Date());
            if (e instanceof WebDriverException) {
              System.out.println("ERROR maybe browsers are killed before shutdown");
              return;
            }
            e.printStackTrace();
            captureScreenshot(frameworkMethod.getName() + "-shutdown-error");
            System.out.println("captureScreenshot "+ new Date());
            Sleeper.sleep(5000);
            Browser.getBrowser().shutdown();
            System.out.println("shutdown" + new Date());
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
