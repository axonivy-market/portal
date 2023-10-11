package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;

public class StatisticWidgetPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='statistics-widget']";
  }

  public TaskAnalysisWidgetPage navigateToTaskAnalysisPage() {
    var taskAnalysLink = $("a[id='statistics-widget:task-analysis-page-navigation-link']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition());
    WaitHelper.waitForNavigation(() -> taskAnalysLink.click());
    $("[id='task-widget']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return new TaskAnalysisWidgetPage();
  }
}
