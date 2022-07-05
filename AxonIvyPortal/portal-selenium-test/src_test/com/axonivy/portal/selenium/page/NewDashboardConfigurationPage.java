package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class NewDashboardConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#dashboard-configuration-container";
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
    return $("div[id$='configuration-dashboard-detail-dialog']");
  }

  private SelenideElement getDeleteDashboardConfirmDialog() {
    return $("div[id='remove-dashboard-dialog']");
  }

  public NewDashboardDetailsEditPage navigateToEditDashboardDetailsByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    if (dashboardRow != null) {
      dashboardRow.$("a[id$=':configure-dashboard']").click();
      NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
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
    editDashboardDialog.$("button[id$='dashboard-detail-save-button']").click();
    editDashboardDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage navigateToNewDashboardPage() {
    $("a[id='dashboard-form:cancel-button']").click();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    return newDashboardPage;
  }

  private SelenideElement openCreateDashboardDialog() {
    $("button[id$=':add-dashboard']").click();
    $("div[id='dashboard-creation-menu-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id='dashboard-creation-menu-dialog']");
  }

  public void createPrivateDashboardFromScratch(String newName, String newDescription) {
    SelenideElement createDashboardMennuDialog = openCreateDashboardDialog();
    createDashboardMennuDialog.$("a[id$=':create-from-scratch']").click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  public void createPrivateDashboardFromTemplate(String newName, String newDescription, int templateIndex) {
    SelenideElement createDashboardMennuDialog = openCreateDashboardDialog();
    createDashboardMennuDialog.$("a[id$='" + templateIndex + ":template']").click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  public void createPublicDashboardFromScratch(String newName, String newDescription, List<String> permissions) {
    SelenideElement createDashboardMennuDialog = openCreateDashboardDialog();
    createDashboardMennuDialog.$("a[id$=':create-from-scratch']").click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  public void createPublicDashboardFromTemplate(String newName, String newDescription, List<String> permissions, int templateIndex) {
    SelenideElement createDashboardMennuDialog = openCreateDashboardDialog();
    createDashboardMennuDialog.$("a[id$='" + templateIndex + ":template']").click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  private void inputCreateDashboardDialog(String newName, String newDescription, List<String> permissions) {
    SelenideElement createDashboardDialog = $("div[id$='configuration-dashboard-detail-dialog']");
    createDashboardDialog.waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    createDashboardDialog.$("input[id$=':dashboard-title']").clear();
    createDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    createDashboardDialog.$("input[id$=':dashboard-description']").clear();
    createDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);
    
    if (permissions != null) {
      ElementsCollection selectedPermissions = createDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active");
      if (!selectedPermissions.isEmpty()) {
        for(SelenideElement permission : selectedPermissions) {
          permission.$("span.ui-icon-close").click();
        }
      }

      createDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown").click();
      
      $("span[id$=':dashboard-permission_panel']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
      $("span[id$=':dashboard-permission_panel']").$$("tr.ui-autocomplete-item").forEach(item -> {
        for(String permissionName : permissions) {
          if (item.$("td").getText().contains(permissionName)) {
            item.click();
          }
        }
      });
    }

    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    createDashboardDialog.waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDashboardCellByNameAndPosition(String dashboardName, int position) {
    SelenideElement dashboard = getDashboardRowByName(dashboardName);
    dashboard.shouldBe(Condition.appear);
    return dashboard.$("td:nth-child(" + position + ")");
  }
}
