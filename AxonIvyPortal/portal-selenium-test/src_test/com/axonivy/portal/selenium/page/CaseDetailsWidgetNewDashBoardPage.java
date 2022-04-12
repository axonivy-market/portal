package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class CaseDetailsWidgetNewDashBoardPage extends TemplatePage {


  public CaseDetailsWidgetNewDashBoardPage() {
    
  }
  
  public SelenideElement destroyLink() {
    return $("a[id$='destroy-case']");
  }

  public void destroy() {
    destroyLink().shouldBe(getClickableCondition()).click();
    confirmDestroy();
  }

  private void confirmDestroy() {
    $("div[id$='destroy-case-confirmation-dialog']").waitUntil(appear, DEFAULT_TIMEOUT)
        .$("button[id$='confirm-destruction']").shouldBe(getClickableCondition()).click();
  }
  
  public ElementsCollection countRelatedTasks() {
    return $("div[id$='related-tasks']").waitUntil(appear, DEFAULT_TIMEOUT).$$("td.related-task-name-column");
  }

  public ElementsCollection countRelatedCases() {
    return $("div[id$='related-cases']").waitUntil(appear, DEFAULT_TIMEOUT).$$("td.name-column");
  }
  
  public void openAdditionalCaseDetailsPage() {
    $("a[id$=':show-additional-case-details-link']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
  }
  
  public ElementsCollection countAdditionalFieldsPage() {
    return $("div[id$='additional-case-detail-table']").waitUntil(appear, DEFAULT_TIMEOUT).$$("table tbody tr");
  }

  public SelenideElement firstAdditionalFieldsPage() {
    return countAdditionalFieldsPage().first();
  }

  public void openActionPanel() {
    $("a[id$=':action-group:case-details-action-link']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
      .click();
    $("div[id$=':action-group:action-steps-panel']").waitUntil(appear, DEFAULT_TIMEOUT);
  }
}
