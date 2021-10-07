package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CaseWidgetPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "//*[contains(@id,'case-view')]";
  }

  public void openCase(String caseName) {
    $("div[id='case-widget:case-list']").waitUntil(appear, DEFAULT_TIMEOUT);
    $$("div[id='case-widget:case-list'] ul li div[id$=':case-item'] span.case-info-row span.case-header-name-cell")
        .filter(text(caseName)).first().click();
  }
}
