package com.axonivy.portal.selenium.common;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;

public final class WaitHelper {

  protected static final long DEFAULT_TIMEOUT = 45000;

  public static void waitForNavigation(Runnable navigationAcion) {
    String viewState = $("input[name='javax.faces.ViewState']").getAttribute("value");
    navigationAcion.run();
    $$("input[value='" + viewState + "']").shouldHave(size(0), DEFAULT_TIMEOUT);
    $(".layout-menu li[role='menuitem'] a.DASHBOARD").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public static void waitForIFrameAvailable(WebDriver driver, String frameId) {
    wait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
  }

  public static WebDriverWait wait(WebDriver driver) {
    return new WebDriverWait(driver, 45);
  }
}
