package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class DashboardConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#configuration-group";
  }

  private void waitForDashboardConfigurationTypeSelectionAppear() {
    $("[id$='dashboard-configuration-type']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getPrivateDashboardConfigurationTypeSelection() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("a[id$='private-dashboard-type']");
  }

  public SelenideElement getPublicDashboardConfigurationTypeSelection() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("a[id$='public-dashboard-type']");
  }

  public DashboardModificationPage openEditPublicDashboardsPage() {
    selectPublicDashboardType();
    $("a[id$='edit-dashboard-action'].js-public-dashboard").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return new DashboardModificationPage();
  }

  public void selectPublicDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='public-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-public-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectPrivateDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='private-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-private-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public DashboardModificationPage openEditPrivateDashboardsPage() {
    selectPrivateDashboardType();
    $("a[id$='edit-dashboard-action'].js-private-dashboard").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return new DashboardModificationPage();
  }

  public void openCreatePublicDashboardMenu() {
    selectPublicDashboardType();
    $("a[id$='create-dashboard-action'].js-public-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
  }

  public void openCreatePrivateDashboardMenu() {
    selectPrivateDashboardType();
    $("a[id$='create-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
  }

  public void createPrivateDashboardFromTemplate(String newName, String icon, String newDescription, int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$=':" + templateIndex + ":template']")
        .shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, null);
  }

  public void createPrivateDashboardFromScratch(String newName, String icon, String newDescription) {
    selectPrivateDashboardType();
    $("a[id$='create-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
    $("a[id$=':create-from-scratch']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    inputCreateDashboardDialog(newName, icon, newDescription, null);
  }

  private SelenideElement waitForCreateNewDashboardSectionAppear() {
    $("div[id$=':create-new-dashboard-section']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id$=':create-new-dashboard-section']");
  }

  public void createPublicDashboardFromScratch(String newName, String icon, String newDescription, List<String> permissions) {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, permissions);
  }

  public void createPublicDashboardFromTemplate(String newName, String icon, String newDescription, List<String> permissions,
      int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$='" + templateIndex + ":template']")
        .shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, permissions);
  }

  public void reorderPublicDashboard() {
    selectPublicDashboardType();
    $("a[id$='reorder-dashboard-action'].js-public-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public void reorderPrivateDashboard() {
    selectPrivateDashboardType();
    $("a[id$='reorder-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  private void inputCreateDashboardDialog(String newName, String icon, String newDescription, List<String> permissions) {
    String creationDetailsDialogId = "div[id$=':dashboard-creation-details-dialog']";
    $(creationDetailsDialogId).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement createDashboardDialog = $(creationDetailsDialogId);
    $("a[id$=':change-icon-link']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    selectDashboardIcon(icon);
    createDashboardDialog.$("input[id$=':dashboard-title']").clear();
    createDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    createDashboardDialog.$("input[id$=':dashboard-description']").clear();
    createDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    if (permissions != null) {
      createDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active").asDynamicIterable().forEach(permission -> {
        permission.$("span.ui-icon-close").shouldBe(getClickableCondition()).click();
      });

      createDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown").click();
      $("span[id$=':dashboard-permission_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
          .$$("tr.ui-autocomplete-item").asDynamicIterable().forEach(item -> {
            for (String permissionName : permissions) {
              if (item.$("td").getText().contains(permissionName)) {
                item.shouldBe(getClickableCondition()).click();
              }
            }
          });
    }

    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    $(creationDetailsDialogId).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private void selectDashboardIcon(String icon) {
    String selectIconDialogId = "div[id$=':select-icon-dialog']";
    $(selectIconDialogId).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement selectIconDialog = $(selectIconDialogId);
    String iconId = "a[id$=':awesome-icon'] span." + icon;
    if (icon.startsWith("si")) {
      iconId = "div[id$=':icons-selection-form:icons'] a.icon-selection-dialog-selecting-icon i." + icon;
    }
    selectIconDialog.$(iconId).shouldBe(getClickableCondition()).click();
    $(selectIconDialogId).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage backToHomePage() {
    $("[id$='actions-group']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$='back-to-home-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new NewDashboardPage();
  }
}
