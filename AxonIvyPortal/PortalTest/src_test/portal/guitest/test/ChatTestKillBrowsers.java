package portal.guitest.test;

import java.io.IOException;

import org.junit.Test;

import portal.guitest.common.Sleeper;

/**
 * This is a work around to kill all browsers after ChatTest.
 * If killing browsers in @AfterClass of ChatTest first then in ScreenshotFailedTestRule shutdown the browser, shutting down the browser could take 3 hours to finish. 
 */
public class ChatTestKillBrowsers {


  @Test
  public void killBrowsers() {
    try {
      System.out.println("Kill all open browsers");
      Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
      Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
      Sleeper.sleep(5000);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
