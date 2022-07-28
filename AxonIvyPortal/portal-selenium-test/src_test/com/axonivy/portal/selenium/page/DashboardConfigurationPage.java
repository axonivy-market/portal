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
    $("[id$='dashboard-configuration-type']").waitUntil(appear, DEFAULT_TIMEOUT);
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
    $("a[id$='edit-dashboard-action'].js-public-dashboard").waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return new DashboardModificationPage();
  }

  public void selectPublicDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='public-dashboard-type']").waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-public-dashboard-configuration").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void selectPrivateDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='private-dashboard-type']").waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-private-dashboard-configuration").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public DashboardModificationPage openEditPrivateDashboardsPage() {
    selectPrivateDashboardType();
    $("a[id$='edit-dashboard-action'].js-private-dashboard").waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return new DashboardModificationPage();
  }

  public void openCreatePublicDashboardMenu() {
    selectPublicDashboardType();
    $("a[id$='create-dashboard-action'].js-public-dashboard").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
  }

  public void openCreatePrivateDashboardMenu() {
    selectPrivateDashboardType();
    $("a[id$='create-dashboard-action'].js-private-dashboard").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
  }

  public void createPrivateDashboardFromTemplate(String newName, String newDescription, int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$=':" + templateIndex + ":template']")
        .waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  public void createPrivateDashboardFromScratch(String newName, String newDescription) {
    selectPrivateDashboardType();
    $("a[id$='create-dashboard-action'].js-private-dashboard").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
    $("a[id$=':create-from-scratch']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).click();
    inputCreateDashboardDialog(newName, newDescription, null);
  }

  private SelenideElement waitForCreateNewDashboardSectionAppear() {
    $("div[id$=':create-new-dashboard-section']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id$=':create-new-dashboard-section']");
  }

  public void createPublicDashboardFromScratch(String newName, String newDescription, List<String> permissions) {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  public void createPublicDashboardFromTemplate(String newName, String newDescription, List<String> permissions,
      int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$='" + templateIndex + ":template']")
        .waitUntil(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, newDescription, permissions);
  }

  public void reorderPublicDashboard() {
    selectPublicDashboardType();
    $("a[id$='reorder-dashboard-action'].js-public-dashboard").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  public void reorderPrivateDashboard() {
    selectPrivateDashboardType();
    $("a[id$='reorder-dashboard-action'].js-private-dashboard").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
  }

  private void inputCreateDashboardDialog(String newName, String newDescription, List<String> permissions) {
    String creationDetailsDialogId = "div[id$=':dashboard-creation-details-dialog']";
    $(creationDetailsDialogId).waitUntil(appear, DEFAULT_TIMEOUT);
    SelenideElement createDashboardDialog = $(creationDetailsDialogId);
    createDashboardDialog.$("input[id$=':dashboard-title']").clear();
    createDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    createDashboardDialog.$("input[id$=':dashboard-description']").clear();
    createDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    if (permissions != null) {
      createDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active").forEach(permission -> {
        permission.$("span.ui-icon-close").shouldBe(getClickableCondition()).click();
      });

      createDashboardDialog.$("div[id$=':dashboard-permission']").$("button.ui-autocomplete-dropdown").click();
      $("span[id$=':dashboard-permission_panel']").waitUntil(Condition.appear, DEFAULT_TIMEOUT)
          .$$("tr.ui-autocomplete-item").forEach(item -> {
            for (String permissionName : permissions) {
              if (item.$("td").getText().contains(permissionName)) {
                item.shouldBe(getClickableCondition()).click();
              }
            }
          });
    }

    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    $(creationDetailsDialogId).waitUntil(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage backToHomePage() {
    $("[id$='actions-group']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("[id$='back-to-home-button']").waitUntil(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new NewDashboardPage();
  }
}
