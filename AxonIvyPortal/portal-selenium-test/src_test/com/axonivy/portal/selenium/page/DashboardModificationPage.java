package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

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
      clickButtonOnDashboardConfigurationActionMenu("Configuration", dashboardRow);
      NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
      return newDashboardDetailsEditPage;
    }
    return null;
  }
  
  private SelenideElement getDashboardConfigurationActionMenu(SelenideElement dashboardRow) {
    dashboardRow.$("div#dashboard-configuration-action-group").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("button[id$='dashboard-configuration-action-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $$("div[id$='dashboard-configuration-action-menu']").filter(Condition.appear).first();
  }
  
  private void clickButtonOnDashboardConfigurationActionMenu(String buttonName, SelenideElement dashboardRow) {
    getDashboardConfigurationActionMenu(dashboardRow).$$("span").filter(Condition.text(buttonName)).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickEditDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    clickButtonOnDashboardConfigurationActionMenu("Edit", dashboardRow);
    getEditDashboardDialog().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickDeleteDashboardByName(String dashboardName) {
    SelenideElement dashboardRow = getDashboardRowByName(dashboardName);
    clickButtonOnDashboardConfigurationActionMenu("Delete", dashboardRow);
    SelenideElement deleteConfirmDialog = $("[id$=':remove-dashboard-dialog']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);
    deleteConfirmDialog.$("button[id$=':remove-dashboard-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    deleteConfirmDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void selectDashboardDisplayType(DashboardDisplayType type) {
    String label = DashboardDisplayType.getDisplayLabel(type);
    $("div.create-public-dashboard-dialog").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("div[id$=':dashboard-display-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("ul[id$='dashboard-display-menu_items']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("li").filter(Condition.text(label)).first().click();
  }
  
  public void saveEditDashboard() {
    getEditDashboardDialog().$("button[id$='dashboard-detail-save-button']").click();
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
    return getDashboardConfigurationActionMenu(dashboard)
    .$$("ul > li > a").filter(Condition.attribute("title", "Export dashboard")).first();
  }

  public SelenideElement getDashboardShareLinkButton() {
    SelenideElement dashboardRow = getDashboardRowByName("Dashboard");
    return getDashboardConfigurationActionMenu(dashboardRow).$$("span").filter(Condition.text("Share")).first();
  }

  public void getDashboardShareLinkDialog() {
    SelenideElement dashboard = getDashboardRowByName("Dashboard");
    clickButtonOnDashboardConfigurationActionMenu("Share", dashboard);
    $("div[id$=':share-dashboard-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getElementBorder() {
    ElementsCollection elements = $("[id=\"task-task_1:task-component:dashboard-tasks:dashboard-tasks-columns:1\"]")
        .$$(".ui-column-resizer.ui-draggable.ui-draggable-handle");
    return elements.get(0);
}
  
  public void resizeColumn() {
    $("[id='task-task_1:task-component:dashboard-tasks-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    ElementsCollection elements = $("[id=\"task-task_1:task-component:dashboard-tasks:dashboard-tasks-columns:1\"]")
    .$$(".ui-column-resizer.ui-draggable.ui-draggable-handle");
    ElementsCollection targets = $("[id='task-task_1:task-component:dashboard-tasks:dashboard-tasks-columns:3']")
        .$$(".ui-column-resizer.ui-draggable.ui-draggable-handle");
        
    new Actions(driver)
    .dragAndDrop(elements.get(0), targets.get(0))
    .perform();
  }
  
  public int getPriorityColumnSize() {
    return $("[id=\"task-task_1:task-component:dashboard-tasks:dashboard-tasks-columns:1\"]")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .getSize().getWidth();
  }
}
