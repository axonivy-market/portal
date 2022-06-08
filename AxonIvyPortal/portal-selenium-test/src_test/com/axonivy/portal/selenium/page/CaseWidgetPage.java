package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class CaseWidgetPage extends TemplatePage {

  public CaseWidgetPage() {}

  public static CaseWidgetPage newInstance() {
    $(".js-case-widget-header").waitUntil(appear, DEFAULT_TIMEOUT);
    return new CaseWidgetPage();
  }

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
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").shouldBe(getClickableCondition()).click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:from-created-calendar_panel"));
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:to-created-calendar_panel"));
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").waitUntil(disappear, DEFAULT_TIMEOUT);
  }
  
  private void closePanelDatePicker(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].style.display = 'none'", element);
  }
  
  public ElementsCollection countCases() {
    return $$("div[id$='case-widget:case-list'] ul li");
  }

  public SelenideElement getCreatorAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").waitUntil(appear, DEFAULT_TIMEOUT);
  }
}
