package ch.ivyteam.ivy.project.portal.guitest.mobile.core;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import ch.ivyteam.ivy.project.portal.guitest.mobile.enums.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;

public class Device {
  protected AppiumDriver<WebElement> driver;
  private static Device instance;

  /**
   * default constructor
   */
  public Device() {}

  public static Device getDevice() {
    if (instance == null) {
      synchronized (Device.class) {
        if (instance == null) {
          instance = new Device();
        }
      }
    }

    return instance;
  }

  /**
   * get appium driver
   * 
   * @return AppiumDriver
   */
  public AppiumDriver<WebElement> getDriver() {
    return driver;
  }

  /**
   * Shutdown driver
   */
  public void shutdown() {
    driver.manage().deleteAllCookies();
    driver.quit();
    instance = null;
  }

  /**
   * init appium driver
   * 
   * @param deviceType
   * @param appiumServerUrl
   * @param deviceName 
   * @throws MalformedURLException 
   */
  public void initAppiumDriver(DeviceType deviceType, String appiumServerUrl, String deviceName) throws MalformedURLException {
    String fullURL = appiumServerUrl + "/wd/hub/";
    URL url = new URL(fullURL);
    if(deviceType == DeviceType.ANDROID) {
      driver = new AndroidDriver<WebElement>(url, setDesiredCapabilitiesForAndroid(deviceName));
    }
    else if(deviceType == DeviceType.IOS) {
      //TODO: reserve for future needs
    }
  }

  private DesiredCapabilities setDesiredCapabilitiesForAndroid(String deviceName) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
    capabilities.setCapability("deviceName", deviceName);
    capabilities.setCapability("automationName", "uiautomator2");
    //for android, we support only Chrome
    capabilities.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
    return capabilities;
  }
  
  public void goToURL(String url) throws URISyntaxException {
    driver.get(new URI(url).toString());
  }
}
