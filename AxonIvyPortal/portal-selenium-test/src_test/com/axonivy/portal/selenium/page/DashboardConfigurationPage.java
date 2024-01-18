package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.FileHelper;
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

  public SelenideElement setupDataPublicDashboardFromScratch(String title) {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    String creationDetailsDialogId = "div[id$=':dashboard-creation-details-dialog']";
    $(creationDetailsDialogId).shouldBe(appear, DEFAULT_TIMEOUT);
    SelenideElement createDashboardDialog = $(creationDetailsDialogId);
    return createDashboardDialog;
  }

  public void createPublicDashboardFromScratch(SelenideElement createDashboardDialog, List<String> permissions) {
    String creationDetailsDialogId = "div[id$=':dashboard-creation-details-dialog']";
    if (permissions != null) {
      setPermissions(permissions, createDashboardDialog.$("div[id$=':dashboard-permission']"));
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
    SelenideElement multipleLanguageDialog = $("div[id$='dashboard-creation-component:title-language-config:multiple-languages-dialog']");
    multipleLanguageDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return multipleLanguageDialog;
  }

  public SelenideElement getImportMultipleLanguageDialog() {
    SelenideElement multipleLanguageDialog = $("div[id$='dashboard-import-component:title-language-config:multiple-languages-dialog']");
    multipleLanguageDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return multipleLanguageDialog;
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
      setPermissions(permissions, createDashboardDialog.$("div[id$=':dashboard-permission']"));
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
  
  public void setPermissions(List<String> permissions, SelenideElement permissionElement) {
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
    editMultiLangDashboardImportTitle(name, otherLangName);

    if (permissions != null) {
      setPermissions(permissions, getDashboardImportPermission());
    }
    
    getDashboardImportSaveButton().shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    importDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void editMultiLangDashboardImportTitle(String name, String updatedName) {
    getAddLanguageButton().click();;
    var multipleLanguageDialog = getImportMultipleLanguageDialog();
    var elementsInput = multipleLanguageDialog.$$("td input");

    elementsInput.get(2).setValue(updatedName);

    multipleLanguageDialog.$("button[type='submit']").click();
    multipleLanguageDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getDashboardConfigurationPage() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("div[id$='configuration-group']");
  }

  public void createPrivateDashboardFromScratch() {
    openCreatePrivateDashboardMenu();
    $("a[id$=':create-from-scratch']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $$("[id$=':dashboard-title']").filter(Condition.visible).first().sendKeys("My dashboard");
  }

  public SelenideElement getDashboardCreationDialog() {
    return $("div[id$=':dashboard-creation-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement openMultiLanguageDialog() {
    getAddLanguageButton().click();
    return $("div[id$=':dashboard-creation-component:title-language-config:multiple-languages-dialog']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void cancelMultiLanguageDialog() {
    $("a[id$=':multi-language-cancel-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$=':dashboard-creation-component:title-language-config:multiple-languages-dialog']")
        .shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void cancelCreateDashboard() {
    $("a[id$='dashboard-creation-component:dashboard-detail-close-button']").shouldBe(getClickableCondition()).click();
//    closeAddDashboardDialog();
  }

  public void closeAddDashboardDialog() {
    $("[id='dashboard-template-selection-component:create-new-dashboard-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='dashboard-template-selection-component:create-new-dashboard-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void createPublicDashboardFromScratch() {
    openCreatePublicDashboardMenu();
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getDashboardTemplates() {
    return $("[id$=':create-new-dashboard-form']");
  }

  public void openImportPublicDashboards() {
    getImportDashboardDialog();
    uploadFile("Dashboard_Dashboard_Export.json");
  }

  public SelenideElement getImportDialog() {
    $("input[id$='dashboard-template-selection-component:dashboard-import-component:import-dashboard-form:dashboards-information-form:0:import-dashboard-title']")
        .hover();
    return $("div[id$=':dashboard-import-dialog']");
  }
  
  public void cancelImportDashboard() {
    $("a[id$=':dashboard-import-close-button']").shouldBe(getClickableCondition()).click();
//    closeAddDashboardDialog();
  }
  
  public void openImportPrivateDashboards() {
    openCreatePrivateDashboardMenu();
    getImportDashboardDialog();
    uploadFile("Dashboard_Dashboard_Export.json");
  }

  public SelenideElement getShareDashboardDialog() {
    $("button[id$=':share-dashboard']").click();
    $("div[id$=':share-dashboard-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("div[id$=':share-dashboard-dialog']");
  }
}
