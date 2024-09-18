package com.axonivy.portal.selenium.test;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.axonivy.portal.selenium.common.Sleeper;

public class ChatTestKillBrowsersTest {

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
