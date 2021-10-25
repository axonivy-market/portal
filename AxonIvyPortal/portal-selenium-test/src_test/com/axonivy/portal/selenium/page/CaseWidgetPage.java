package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;

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

  public void filterCasesByCreatedDate(String fromCreatedDate, String toCreatedDate) {
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection countCases() {
    return $$("div[id$='case-widget:case-list'] ul li");
  }
}
