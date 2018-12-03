package ch.ivyteam.ivy.project.portal.guitest.mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.MobileAbstractPage;

public class MobileTemplatePage extends MobileAbstractPage {
  public static final String TEMPLATE_PAGE_LOCATOR = "id('logo')";
  public static final String PROCESS_TAB_ID = "mobile-menu:mobile-menu-form:mobile-process-menu-item";
  public static final String TASK_TAB_ID = "mobile-menu:mobile-menu-form:mobile-task-menu-item";
  
  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }
  
  public MobileTemplatePage() {
    waitForLocatorDisplayed(getLoadedLocator());
  }
  
  protected void waitForLocatorDisplayed(String locator) {
    waitForElementDisplayed(locator, true, DEFAULT_TIMEOUT);
  }
  
  public void clickOnLogo() {
    WebElement logo = findElementById("logo");
    waitForElementDisplayed(logo, true, DEFAULT_TIMEOUT);
    logo.click();
    waitAjaxIndicatorDisappear();
  }
  
  
  public void waitAjaxIndicatorDisappear() {
    WebElement ajaxIndicatorStartState = findElementById("ajax-indicator:ajax-indicator_start");
    boolean displayed = false;
    try {
      displayed = ajaxIndicatorStartState.isDisplayed();
    } catch (Exception e) {
      try {
        displayed = ajaxIndicatorStartState.isDisplayed();
      } catch (Exception e1) {
        System.out.println("Cannot check if ajax indicator is displayed");
      }
    }
    if (displayed) {
      waitForElementDisplayed(ajaxIndicatorStartState, false, DEFAULT_TIMEOUT);
    }
  }
  
  public MobileProcessPage openProcessTab() {
    click(By.id(PROCESS_TAB_ID));
    return new MobileProcessPage();
  }
  
  public MobileTaskPage openTaskTab() {
    click(By.id(TASK_TAB_ID));
    return new MobileTaskPage();
  }
  
  public WebElement getProcessTab() {
    return findElementById(PROCESS_TAB_ID);
  }
  
  public WebElement getTaskTab() {
    return findElementById(TASK_TAB_ID);
  }
}
