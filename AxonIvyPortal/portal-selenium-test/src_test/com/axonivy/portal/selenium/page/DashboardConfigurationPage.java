package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.DragAndDropOptions;
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
    $("button[id$='dashboard-modification-component:dashboard-table:0:edit']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition());
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
    $("button[id$='dashboard-modification-component:dashboard-table:0:edit']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    return new DashboardModificationPage();
  }

  public void openCreatePublicDashboardMenu() {
    selectPublicDashboardType();
    $("button[id$='create-dashboard-action']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
  }

  public void openCreatePrivateDashboardMenu() {
    selectPrivateDashboardType();
    $("button[id$='create-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
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
    $("button[id$='create-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
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

  public SelenideElement setupDataPublicDashboardFromScratch() {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    String creationDetailsDialogId = "[id$=':dashboard-title']";
    $(creationDetailsDialogId).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement createDashboardDialog = $(creationDetailsDialogId);
    return createDashboardDialog;
  }

  public void createPublicDashboardFromScratch(SelenideElement createDashboardDialog, List<String> permissions) {
    String creationDetailsDialogId = "[id$=':dashboard-title']";
    if (permissions != null) {
      setPermissions(permissions);
    }

    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    $(creationDetailsDialogId).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void changeDashboardTitle(SelenideElement dashboardDialog, String title) {
    dashboardDialog.$("input[id$=':dashboard-title']").clear();
    dashboardDialog.$("input[id$=':dashboard-title']").sendKeys(title);
  }

  public SelenideElement getAddLanguageButton() {
    SelenideElement addLanguageButton = $("button[id$='add-language-button']");
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    addLanguageButton.shouldBe(getClickableCondition());
    waitUntilElementToBeClickable(addLanguageButton);
    return addLanguageButton;
  }

  public SelenideElement getMultipleLanguageDialog() {
    SelenideElement addLanguageDialog = $("[id$='dashboard-creation-component:title-language-config:multiple-languages-dialog']");
    addLanguageDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return addLanguageDialog;
  }

  public SelenideElement getImportMultipleLanguageDialog() {
    SelenideElement addLanguageDialog = $("[id$='dashboard-import-component:title-language-config:multiple-languages-dialog']");
    addLanguageDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return addLanguageDialog;
  }

  public SelenideElement getTranslationOverlayPanel(int index) {
    SelenideElement translationOverlay = $(String.format("div[id$=':%s:overlay-panel-input']", index));
    waitUntilElementToBeClickable(translationOverlay);
    translationOverlay.shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    return translationOverlay;
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
    $("[id$='back-to-home-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new NewDashboardPage();
  }
  
  public NewDashboardPage backToHomePageBottom() {
    $("[id$='back-to-home-button']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new NewDashboardPage();
  }

  public SelenideElement getImportDashboardDialog() {
    $("a[id$=':import-dashboard']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    return $("div[id$='dashboard-import-dialog']");
  }
  
  public SelenideElement getDashboardImportButtonOfDashboard() {
    return $("a[id$=':import-dashboard']");  
  }
  
  public SelenideElement getDashboardImportSaveButton() {
    return $("button[id$=':dashboard-detail-save-button']");  
  }
  
  public SelenideElement getDashboardImportPermission() {
    return $("div[id$=':dashboard-permission']");
  }
  
  public void uploadFile(String fileName) {
    var importDialog = $("div[id$='dashboard-import-dialog']");
    importDialog.find("[id$=':dashboard-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(fileName));
  }
  
  public void setPermissions(List<String> permissions) {
    getDashboardImportPermission().$$("li.ui-state-active").asDynamicIterable().forEach(permission -> {
      permission.$("span.ui-icon-close").shouldBe(getClickableCondition()).click();
    });

    getDashboardImportPermission().$("button.ui-autocomplete-dropdown").click();
    $("span[id$=':dashboard-permission_panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-autocomplete-item").asDynamicIterable().forEach(item -> {
          for (String permissionName : permissions) {
            if (item.$("td").getText().contains(permissionName)) {
              item.shouldBe(getClickableCondition()).click();
            }
          }
        });
  }
  
  public void saveImportDashboard(String name, String otherLangName, String desc, String icon, List<String> permissions) {
    var importDialog = $("div[id$='dashboard-import-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':change-icon-link']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    selectDashboardIcon(icon);
    importDialog.$("input[id$=':import-dashboard-title']").clear();
    importDialog.$("input[id$=':import-dashboard-title']").sendKeys(name);
    importDialog.$("input[id$=':dashboard-description']").clear();
    importDialog.$("input[id$=':dashboard-description']").sendKeys(desc);
    editMultiLangDashboardImportTitle(otherLangName);

    if (permissions != null) {
      setPermissions(permissions);
    }
    importDialog.$("button[id$=':dashboard-detail-save-button']").shouldBe(getClickableCondition()).click();
    importDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void editMultiLangDashboardImportTitle(String updatedName) {
    getAddLanguageButton().click();
    var multipleLanguageDialog = getImportMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");

    elementsInput.get(2).setValue(updatedName);

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findPrivateDashboardRowByName(String dashboardName) {
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr[role='row']").asFixedIterable()
        .stream().filter(row -> row.getText().contains(dashboardName)).findFirst().get();
  }


  public void reorderPrivateDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPrivateDashboardRowByName(toDashboardName).$("i.si-move-expand-vertical");
    var fromRow = findPrivateDashboardRowByName(fromDashboardName).$("i.si-move-expand-vertical");
    dragAndDropTo(toRow, fromRow);
  }

  private void dragAndDropTo(SelenideElement toRow, SelenideElement fromRow) {
    var targetCssSelector = String.format("[id$='%s']", toRow.getAttribute("id"));
    fromRow.dragAndDropTo(targetCssSelector, DragAndDropOptions.usingActions());
  }

  public void reorderPublicDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPublicDashboardRowByName(toDashboardName).$("i.si-move-expand-vertical");
    var fromRow = findPublicDashboardRowByName(fromDashboardName).$("i.si-move-expand-vertical");
    dragAndDropTo(toRow, fromRow);
  }

  public void saveSetting() {
    $("button[id$='save-settings']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='dashboard-configuration-content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findPublicDashboardRowByName(String dashboardName) {
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr[role='row']")
        .asFixedIterable().stream().filter(row -> row.getText().contains(dashboardName)).findFirst().get();
  }
}
