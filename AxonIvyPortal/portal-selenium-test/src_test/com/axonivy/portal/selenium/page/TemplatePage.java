package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import java.util.ArrayList;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class TemplatePage extends AbstractPage {
  
  private static final String TEMPLATE_PAGE_LOCATOR = "id('global-search-component:global-search-data')";

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

  public SelenideElement getStartedTaskTemplateTitle() {
    return $("span[id='title']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
}
