package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class GlobalSearchResultPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#search-results-tabview";
  }

  public void openTaskTab() {
    $("li[class*='task-tab-title']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("li[class*='task-tab-title']").click();
    $("div[id='search-results-tabview:task-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openCaseTab() {
    $("li[class*='case-tab-title']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("li[class*='case-tab-title']").click();
    $("div[id='search-results-tabview:case-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void caseTabShouldBeDisappear() {
    $("li[class*='case-tab-title']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public int countTasks() {
    $("div[id='search-results-tabview:task-results:task-list-scroller']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='search-results-tabview:task-results:task-list-scroller']").findAll("li.ui-datascroller-item").size();
  }

  public String getNameOfTask(int index) {
    String id = "search-results-tabview:task-results:task-list-scroller:" + index + ":task-item:task-name-component:task-name";
    return $$("div[id='search-results-tabview:task-results:task-list-scroller'] div ul li div[id$=':task-item:task-start'] div.task-start-link div.task-start-info span.name-cell")
        .filter(Condition.id(id)).get(0).getText();
  }

  public int countCases() {
    $("div[id='search-results-tabview:case-results:case-list-scroller']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id='search-results-tabview:case-results:case-list-scroller']").findAll("li.ui-datascroller-item").size();
  }

  public String getNameOfCase(int index) {
    String id = "search-results-tabview:case-results:case-list-scroller:" + index + ":case-item";
    return $("div[id='search-results-tabview:case-results:case-list-scroller'] div ul li div[id='" + id + "'] div span.case-info-row div span.case-header-name-cell").text();
  }

  public String getDescriptionOfCase(int index) {
    String id = "search-results-tabview:case-results:case-list-scroller:" + index + ":case-item";
    return $("div[id='search-results-tabview:case-results:case-list-scroller'] div ul li div[id='" + id + "'] div span.case-info-row div span.case-header-desc-cell").text();
  }

  public String getGlobalSearchByFieldTextForTaskTab() {
    return $("span[id = 'search-results-tabview:task-results:global-search-text']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getGlobalSearchByFieldTextForCaseTab() {
    return $("span[id = 'search-results-tabview:case-results:global-search-text']").shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }
}
