package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;

public final class WaitHelper {

  protected static final long DEFAULT_TIMEOUT = 45000;

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState'][id$='javax.faces.ViewState:1']").getAttribute("value");
    navigationAcion.run();
    $("input[value='" + viewState + "']").waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
    $(".layout-menu li[role='menuitem'] a.DASHBOARD").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
  }

  public static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, 45);
  }

  public static void retryAction(Runnable action) {
    int attempts = 0;
    while (attempts < 10) {
      try {
        action.run();
        break;
      } catch (Exception e) {
        System.out.println("ERROR action");
      }
      attempts++;
    }
    if (attempts == 10) {
      action.run();
    }
  }
}
