package com.axonivy.portal.selenium.page.legacy;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;

public class LegacyTaskWidgetPage extends TemplatePage {

    @Override
    protected String getLoadedLocator() {
      return "[id='task-widget']";
    }

    public void openCompactSortMenu() {
      $("[id$='sort-task-menu_label']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      $("div[id$='sort-task-menu_panel']").shouldBe(appear, DEFAULT_TIMEOUT);
    }
}
