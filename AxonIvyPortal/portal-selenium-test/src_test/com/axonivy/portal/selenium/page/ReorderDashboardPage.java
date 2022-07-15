package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappears;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

public class ReorderDashboardPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "i#reorder-button";
  }

  public void toggleVisibility(String dashboardName) {
    SelenideElement toggleVisibility = findPrivateDashboardRowByName(dashboardName).$("a[id$='toggle-visibility']");
    toggleVisibility.click();
    toggleVisibility.waitUntil(disappears, DEFAULT_TIMEOUT);
  }

  public void reorderPrivateDashboard(String fromDashboardName, String toDashboardName) {
    WebElement toRow = findPrivateDashboardRowByName(toDashboardName).toWebElement();
    SelenideElement reorderButton = findPrivateDashboardRowByName(fromDashboardName).$("i#reorder-button");
    reorderButton.dragAndDropTo(toRow);
  }

  public void reorderPublicDashboard(String fromDashboardName, String toDashboardName) {
    WebElement toRow = findPublicDashboardRowByName(toDashboardName).toWebElement();
    SelenideElement reorderButton = findPublicDashboardRowByName(fromDashboardName).$("i#reorder-button");
    reorderButton.dragAndDropTo(toRow);
  }

  public void saveSetting() {
    $("button[id$='save-settings']").waitUntil(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='dashboard-configuration-content']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findPublicDashboardRowByName(String dashboardName) {
    $("[id$=':reorder-dashboard-form:public-dashboard-table']").waitUntil(appear, DEFAULT_TIMEOUT);
    return $("[id$=':public-dashboard-table_data']").waitUntil(appear, DEFAULT_TIMEOUT)
          .$$("tr[role='row']").stream()
              .filter(row -> row.$("span[id$='dashboard-title']").getText().equals(dashboardName))
              .findFirst().get();
  }

  private SelenideElement findPrivateDashboardRowByName(String dashboardName) {
    $("[id$=':reorder-dashboard-form:dashboard-table']").waitUntil(appear, DEFAULT_TIMEOUT);
    return $("[id$=':dashboard-table_data']").waitUntil(appear, DEFAULT_TIMEOUT)
          .$$("tr[role='row']").stream()
              .filter(row -> row.$("span[id$='dashboard-title']").getText().equals(dashboardName))
              .findFirst().get();
  }

}
