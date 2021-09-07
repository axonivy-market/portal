package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;

import java.util.ArrayList;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;

import vn.wawa.guitest.base.page.AbstractPage;

public abstract class TemplatePage extends AbstractPage {
  
  private static final String TEMPLATE_PAGE_LOCATOR = "id('global-search-component:global-search-data')";
  public static final long DEFAULT_TIMEOUT  = 45000;

  public TemplatePage() {
  }

  //If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 45L;
  }
  
  protected Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }
  
  @Override
  protected String getLoadedLocator() {
    return TEMPLATE_PAGE_LOCATOR;
  }
  
  public void switchLastBrowserTab() {
    String oldTab = WebDriverRunner.getWebDriver().getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
    tabs.remove(oldTab);
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(0));
  }

}
