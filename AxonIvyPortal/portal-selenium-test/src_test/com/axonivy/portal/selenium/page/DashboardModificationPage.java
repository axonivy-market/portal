package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class DashboardModificationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#dashboard-modification-component\\:dashboard-modification-container";
  }

  public ElementsCollection getDashboardRows() {
    SelenideElement dashboardTable = $("tbody[id='dashboard-modification-component:dashboard-table_data']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return dashboardTable.$$("tr:not(.ui-datatable-empty-message)");
  }

  public SelenideElement getDashboardRowByName(String dashboardName) {
    for (SelenideElement dashboardRow : getDashboardRows()) {
      if (dashboardRow.$("td:nth-child(1)").getText().contentEquals(dashboardName)) {
        return dashboardRow;
      }
    }
    return null;
  }

  private SelenideElement getEditDashboardDialog() {
    return $("div[id$='configuration-dashboard-detail-dialog']");
  }

  public NewDashboardDetailsEditPage navigateToEditDashboardDetailsByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    if (dashboardRow != null) {
      dashboardRow.$("button[id$='dashboard-modification-component:dashboard-table:0:configure-dashboard']")
          .shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
      NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
      return newDashboardDetailsEditPage;
    }
    return null;
  }

  public void clickEditDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    dashboardRow.$("[id$=':edit']").click();
    getEditDashboardDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickDeleteDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    dashboardRow.$("[id$=':delete-dashboard']").click();
    SelenideElement deleteConfirmDialog = $("[id$=':remove-dashboard-dialog']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);
    deleteConfirmDialog.$("button[id$=':remove-dashboard-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    deleteConfirmDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void editDashboardInfo(String newName, String newDescription, List<String> permissions) {
    SelenideElement editDashboardDialog = getEditDashboardDialog();
    editDashboardDialog.$("input[id$=':dashboard-title']").clear();
    editDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    editDashboardDialog.$("input[id$=':dashboard-description']").clear();
    editDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    ElementsCollection selectedPermissions = editDashboardDialog.$("div[id$=':dashboard-permission']")
        .$$("li.ui-state-active");
    if (!selectedPermissions.isEmpty()) {
      for (SelenideElement permission : selectedPermissions) {
        permission.$("span.ui-icon-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      }
    }

    editDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    SelenideElement permissionPanel = $$("span[id$=':dashboard-permission_panel']")
        .shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT).get(0).shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    permissionPanel.$$("tr.ui-autocomplete-item").asDynamicIterable().forEach(item -> {
      for (String permissionName : permissions) {
        if (item.$("td").getText().contains(permissionName)) {
          item.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
          permissionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
        }
      }
    });

    editDashboardDialog.$("button[id$='dashboard-detail-save-button']").click();
    editDashboardDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void editPrivateDashboardInfo(String newName, String newDescription) {
    SelenideElement editDashboardDialog = getEditDashboardDialog();
    editDashboardDialog.$("input[id$=':dashboard-title']").clear();
    editDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    editDashboardDialog.$("input[id$=':dashboard-description']").clear();
    editDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    editDashboardDialog.$("button[id$='dashboard-detail-save-button']").click();
    editDashboardDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage navigateToNewDashboardPage() {
    $("a[id='dashboard-form:cancel-button']").click();
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    return newDashboardPage;
  }

  public SelenideElement getDashboardCellByNameAndPosition(String dashboardName, int position) {
    SelenideElement dashboard = getDashboardRowByName(dashboardName);
    dashboard.shouldBe(Condition.appear);
    return dashboard.$("td:nth-child(" + position + ")");
  }

  public SelenideElement getDashboardExportButtonOfDashboard(String dashboardName) {
    SelenideElement dashboard = getDashboardRowByName(dashboardName);
    dashboard.shouldBe(Condition.appear);
    return dashboard.$("td:last-child button[id $=':export-dashboard']");
  }

  public SelenideElement getDashboardShareLinkButton() {
    return $("button[id$='share-dashboard']");
  }

  public void getDashboardShareLinkDialog() {
    getDashboardShareLinkButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$=':share-dashboard-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void deleteProcessWidget() {
    $("div[id='process-process_1:widget-header-actions']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='process-process_1:delete-widget-2']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='process-process_1:delete-widget-2']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='remove-widget-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("button[id='remove-widget-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(".ajax-status-position").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    }
}
