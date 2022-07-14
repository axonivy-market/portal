package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

public class ReorderDashboardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "i#reorder-button";
  }

  public void toggleVisibility(String dashboardName) {
    SelenideElement toggleVisibility =
        findRowWithDashboardName(dashboardName).parent().parent().$("a[id$='toggle-visibility']");
    toggleVisibility.click();
    toggleVisibility.waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public void reorderDashboard(String fromDashboardName, String toDashboardName) {
    WebElement toRow = findRowWithDashboardName(toDashboardName).parent().parent().toWebElement();
    SelenideElement reorderButton = findRowWithDashboardName(fromDashboardName).parent().parent().$("i#reorder-button");
    reorderButton.dragAndDropTo(toRow);
  }

  public void saveSetting() {
    $("button[id$='save-settings']").waitUntil(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='dashboard-configuration-content']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement findRowWithDashboardName(String dashboardName) {
    return $$("span[id$='dashboard-title']").stream().filter(e -> e.getText().equals(dashboardName)).findFirst().get();
  }
}
