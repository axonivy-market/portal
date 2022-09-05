package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class WelcomeWidgetNewDashboardPage extends TemplatePage {

  private static final String WELCOME_TEXT_ID_PATTERN = "%s:welcome-text";

  @Override
  protected String getLoadedLocator() {
    return "[id $= ':welcome-widget-panel']";
  }

  public SelenideElement getWelcomeText(String widgetId) {
    return $("[id = '" + String.format(WELCOME_TEXT_ID_PATTERN, widgetId) + "']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
}
