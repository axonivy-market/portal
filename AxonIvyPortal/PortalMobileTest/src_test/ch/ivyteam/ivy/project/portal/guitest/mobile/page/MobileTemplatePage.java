package ch.ivyteam.ivy.project.portal.guitest.mobile.page;

import org.openqa.selenium.WebElement;

import ch.ivyteam.ivy.project.portal.guitest.mobile.common.MobileAbstractPage;

public class MobileTemplatePage extends MobileAbstractPage {
  private static final String TEMPLATE_PAGE_LOCATOR = "id('logo')";

  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
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
}
