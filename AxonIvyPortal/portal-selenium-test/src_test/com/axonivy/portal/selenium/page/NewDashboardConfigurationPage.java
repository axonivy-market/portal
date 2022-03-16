package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardConfigurationPage extends TemplatePage {
  public void waitPageDisplay() {
    $("#dashboard-configuration-container").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getDashboardRows() {
    SelenideElement dashboardTable = $("tbody[id='dashboard-form:dashboard-table_data']");
    return dashboardTable.$$("tr:not(.ui-datatable-empty-message)");
  }

  public SelenideElement getDashboardRowByName(String dashboardName) {
    for(SelenideElement dashboardRow : getDashboardRows()) {
      if(dashboardRow.$("td:nth-child(1)").getText().contentEquals(dashboardName)) {
        return dashboardRow;
      }
    }
    return null;
  }

  private SelenideElement getEditDashboardDialog() {
    return $("div[id='dashboard-detail-dialog']");
  }

  private SelenideElement getDeleteDashboardConfirmDialog() {
    return $("div[id='remove-dashboard-dialog']");
  }

  public NewDashboardDetailsEditPage navigateToEditDashboardDetailsByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    if (dashboardRow != null) {
      dashboardRow.$("a[id$=':configure-dashboard']").click();
      NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
      newDashboardDetailsEditPage.waitPageDisplay();
      return newDashboardDetailsEditPage;
    }
    return null;
  }

  public void clickEditDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    dashboardRow.$("a[id$=':edit']").click();
    getEditDashboardDialog().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickDeleteDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    dashboardRow.$("a[id$=':delete-dashboard']").click();
    SelenideElement deleteConfirmDialog = getDeleteDashboardConfirmDialog();
    deleteConfirmDialog.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    deleteConfirmDialog.$("button[id='remove-dashboard-button']").click();
    deleteConfirmDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void editDashboardInfo(String newName, String newDescription, List<String> permissions) {
    SelenideElement editDashboardDialog = getEditDashboardDialog();
    editDashboardDialog.$("input[id$=':dashboard-title']").clear();
    editDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    editDashboardDialog.$("input[id$=':dashboard-description']").clear();
    editDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    ElementsCollection selectedPermissions = editDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active");
    if (!selectedPermissions.isEmpty()) {
      for(SelenideElement permission : selectedPermissions) {
        permission.$("span.ui-icon-close").click();
      }
    }

    editDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown").click();
    
    $("span[id$=':dashboard-permission_panel']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    $("span[id$=':dashboard-permission_panel']").$$("tr.ui-autocomplete-item").forEach(item -> {
      for(String permissionName : permissions) {
        if (item.$("td").getText().contains(permissionName)) {
          item.click();
        }
      }
    });
    editDashboardDialog.$("button[id='dashboard-detail-save-button']").click();
    editDashboardDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage navigateToNewDashboardPage() {
    $("a[id='dashboard-form:cancel-button']").click();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    return newDashboardPage;
  }
}
