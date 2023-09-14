package com.axonivy.portal.selenium.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.WebDriverRunner;

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

  public static void resizeBrowser(Dimension size) {
    WebDriver driver = WebDriverRunner.getWebDriver();
    driver.manage().window().setSize(size);
  }
  
  public static void capturePageScreenshot(String screenshotName) throws IOException {
    WebDriver driver = WebDriverRunner.getWebDriver();
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File fileScreenShot = new File(SCREENSHOT_FOLDER + screenshotName + SCREENSHOT_EXTENSION);
    FileUtils.copyFile(screenshot, fileScreenShot);
  }
}
