package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.DashboardDisplayType;

public class DashboardConfigurationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#configuration-group";
  }

  private void waitForDashboardConfigurationTypeSelectionAppear() {
    $("[id$='dashboard-configuration-type']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDashboardConfigurationPage() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("div[id$='configuration-group']");
  }
  
  public SelenideElement getDashboardConfigurationPageWithActionsMenu() {
    waitForDashboardConfigurationTypeSelectionAppear();
    getDashboardConfigurationActionMenu();
    return $("div[id$='configuration-group']"); 
  }

  public SelenideElement getPrivateDashboardConfigurationTypeSelection() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("a[id$='private-dashboard-type']");
  }

  public SelenideElement getPublicDashboardConfigurationTypeSelection() {
    waitForDashboardConfigurationTypeSelectionAppear();
    return $("a[id$='public-dashboard-type']");
  }
  
  public SelenideElement getDashboardConfigurationActionMenu() {
    $("button[id*='dashboard-modification-component:dashboard-table:0:dashboard-configuration-action-button']").shouldBe(appear, DEFAULT_TIMEOUT)
    .shouldBe(getClickableCondition()).click();
    return $$("div[id$='dashboard-configuration-action-menu']").filter(Condition.appear).first();
  }

  public void selectPublicDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='public-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-public-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectPrivateDashboardType() {
    waitForDashboardConfigurationTypeSelectionAppear();
    $("a[id$='private-dashboard-type']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $(".dashboard-configuration__content.js-private-dashboard-configuration").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public DashboardModificationPage openEditPrivateDashboardsPage() {
    selectPrivateDashboardType();
    return new DashboardModificationPage();
  }
  
  public DashboardModificationPage openEditPrivateDashboardPage(String buttonName) {
    selectPrivateDashboardType();
    clickButtonOnDashboardConfigurationActionMenu(buttonName);
    return new DashboardModificationPage();
  }
  
  public DashboardModificationPage openEditPublicDashboardsPage(String buttonName) {
    selectPublicDashboardType();
    clickButtonOnDashboardConfigurationActionMenu(buttonName);
    return new DashboardModificationPage();
  }
  
  public DashboardModificationPage openEditPublicDashboardsPage() {
    selectPublicDashboardType();
    return new DashboardModificationPage();
  }
  
  public void clickButtonOnDashboardConfigurationActionMenu(String buttonName) {
    getDashboardConfigurationActionMenu().$$("span").filter(Condition.text(buttonName)).first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickButtonOnDashboardConfigurationActionMenu(String buttonName,
      int dashboardIndex) {
    $(String.format(
        "[id$='dashboard-modification-component:dashboard-table:%d:dashboard-configuration-action-button']",
        dashboardIndex)).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();

    $$("div[id$='dashboard-configuration-action-menu']")
        .filter(Condition.appear).first().$$("span")
        .filter(Condition.text(buttonName)).first()
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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

  public void createPrivateDashboardFromTemplate(String newName, String icon, String newDescription,
      int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$=':" + templateIndex + ":template']")
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, null, false, DashboardDisplayType.SUB_MENU);
  }

  public void createPrivateDashboardFromScratch(String newName, String icon, String newDescription) {
    selectPrivateDashboardType();
    $("button[id$='create-dashboard-action'].js-private-dashboard").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition()).click();
    waitForCreateNewDashboardSectionAppear();
    $("a[id$=':create-from-scratch']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    inputCreateDashboardDialog(newName, icon, newDescription, null, false, DashboardDisplayType.SUB_MENU);
  }

  private SelenideElement waitForCreateNewDashboardSectionAppear() {
    $("div[id$=':create-new-dashboard-section']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $("div[id$=':create-new-dashboard-section']");
  }

  public void createPublicDashboardFromScratch(String newName, String icon, String newDescription,
      List<String> permissions, DashboardDisplayType type) {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, permissions, true, type);
  }

  public void createPublicDashboardFromTemplate(String newName, String icon, String newDescription,
      List<String> permissions, int templateIndex) {
    waitForCreateNewDashboardSectionAppear().$("a[id$='" + templateIndex + ":template']")
        .shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    inputCreateDashboardDialog(newName, icon, newDescription, permissions, true, DashboardDisplayType.SUB_MENU);
  }

  public SelenideElement openCreateDashboardDialog() {
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    String creationDetailsDialogId = "[id='dashboard-template-selection-component:dashboard-creation-component:dashboard-creation-details-dialog']";
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
    dashboardDialog.$(
        "input[id='dashboard-template-selection-component:dashboard-creation-component:dashboard-detail-form:dashboard-title']")
        .shouldBe(appear, DEFAULT_TIMEOUT).clear();
    dashboardDialog.$(
        "input[id='dashboard-template-selection-component:dashboard-creation-component:dashboard-detail-form:dashboard-title']")
        .sendKeys(title);
  }

  public SelenideElement getAddLanguageButton() {
    SelenideElement addLanguageButton = $$("button[id$='add-language-button']").filter(Condition.visible).get(0);
    addLanguageButton.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    addLanguageButton.shouldBe(getClickableCondition());
    waitUntilElementToBeClickable(addLanguageButton);
    return addLanguageButton;
  }

  public SelenideElement getMultipleLanguageDialog() {
    SelenideElement addLanguageDialog = $$(
        "[id$='dashboard-creation-component:title-language-config:multiple-languages-dialog']")
        .filter(Condition.visible).first();
    addLanguageDialog.shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return addLanguageDialog;
  }

  public SelenideElement getImportMultipleLanguageDialog() {
    SelenideElement addLanguageDialog = $(
        "[id$='dashboard-import-component:title-language-config:multiple-languages-dialog']");
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
    $("i.dashboard-icon-drag-drop").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
  }

  public void reorderPrivateDashboard() {
    selectPrivateDashboardType();
    $("i.dashboard-icon-drag-drop").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition())
        .click();
  }

  private void inputCreateDashboardDialog(String newName, String icon, String newDescription, List<String> permissions,
      boolean isPublicDashboard, DashboardDisplayType type) {
    String creationDetailsDialogSelector = isPublicDashboard ? "div.create-public-dashboard-dialog"
        : "div.create-private-dashboard-dialog";
    creationDetailsDialogSelector = creationDetailsDialogSelector.concat("[id$=':dashboard-creation-details-dialog']");

    SelenideElement createDashboardDialog = $(creationDetailsDialogSelector);
    createDashboardDialog.shouldBe(appear, DEFAULT_TIMEOUT);

    createDashboardDialog.$("a[id$=':change-icon-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    selectDashboardIcon(icon);

    createDashboardDialog.$("input[id$=':dashboard-title']").clear();
    createDashboardDialog.$("input[id$=':dashboard-title']").sendKeys(newName);
    createDashboardDialog.$("input[id$=':dashboard-description']").clear();
    createDashboardDialog.$("input[id$=':dashboard-description']").sendKeys(newDescription);

    if (permissions != null) {
      createDashboardDialog.$("div[id$=':dashboard-permission']").$$("li.ui-state-active").asDynamicIterable()
          .forEach(permission -> {
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
    if (isPublicDashboard) {
      selectDashboardDisplayType(type, createDashboardDialog);
    }
    createDashboardDialog.$("button[id$='dashboard-create-button']").click();
    createDashboardDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void selectDashboardDisplayType(DashboardDisplayType type, SelenideElement createDashboardDialog) {
    String label = DashboardDisplayType.getDisplayLabel(type);
    createDashboardDialog.$("div[id$=':dashboard-display-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("ul[id$='dashboard-display-menu_items']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("li").filter(Condition.text(label)).first().click();
  }

  private void selectDashboardIcon(String icon) {
    String selectIconDialogSelector = "div[id$=':select-icon-dialog']";
    SelenideElement selectIconDialog = $$(selectIconDialogSelector).filter(Condition.visible).get(0);
    selectIconDialog.shouldBe(appear, DEFAULT_TIMEOUT);
    String iconId = "a[id$=':awesome-icon'] span." + icon;
    if (icon.startsWith("si")) {
      iconId = "div[id$=':icons-selection-form:icons'] a.icon-selection-dialog-selecting-icon i." + icon;
    }
    selectIconDialog.$(iconId).shouldBe(getClickableCondition()).click();
    selectIconDialog.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public NewDashboardPage backToHomePage() {
    $("span[class*='si si-house-chimney-2']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    return new NewDashboardPage();
  }

  public NewDashboardPage backToHomePageBottom() {
    $("span[class*='si si-house-chimney-2']").shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
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
    return $("button[id$=':dashboard-import-component:dashboard-detail-save-button']");
  }

  public SelenideElement getDashboardImportPermission() {
    return $$("div[id$='0:dashboard-permission']").size() > 0
        ? $$("div[id$='0:dashboard-permission']").filter(Condition.visible).first()
        : $$("div[id$=':dashboard-permission']").filter(Condition.visible).first();
  }

  public void uploadFile(String fileName) {
    var importDialog = $("div[id$='dashboard-import-dialog']");
    importDialog.find("[id$=':dashboard-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(fileName));
  }

  public void setPermissions(List<String> permissions) {
    getDashboardImportPermission().$("button.ui-autocomplete-dropdown").shouldBe(getClickableCondition());
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

  public void saveImportDashboard(String name, String otherLangName, String desc, String icon) {
    var importDialog = $("div[id$='dashboard-import-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    importDialog.$("a[id$=':change-icon-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    selectDashboardIcon(icon);
    importDialog.$("input[id$=':import-dashboard-title']").clear();
    importDialog.$("input[id$=':import-dashboard-title']").sendKeys(name);
    importDialog.$("input[id$=':dashboard-description']").clear();
    importDialog.$("input[id$=':dashboard-description']").sendKeys(desc);
    editMultiLangDashboardImportTitle(otherLangName);
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
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-widget-content").asFixedIterable()
        .stream().filter(row -> row.getText().contains(dashboardName)).findFirst().get();
  }

  public void reorderPrivateDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPrivateDashboardRowByName(toDashboardName).$("i.si-navigation-menu");
    var fromRow = findPrivateDashboardRowByName(fromDashboardName).$("i.si-navigation-menu");
    dragAndDropTo(toRow, fromRow);
  }

  private void dragAndDropTo(SelenideElement toRow, SelenideElement fromRow) {
    SelenideElement targetCssSelector = $("[id$='"+ toRow.getAttribute("id") + "']");
    Actions builder = new Actions(WebDriverRunner.getWebDriver());
    Action dragAndDrop = builder.clickAndHold(fromRow).pause(500)
        .moveToElement(targetCssSelector, 50, 20).pause(500).release(targetCssSelector)
        .pause(500)
        .build();
    dragAndDrop.perform();

  }

  public void reorderPublicDashboard(String fromDashboardName, String toDashboardName) {
    var toRow = findPublicDashboardRowByName(toDashboardName).$("i.si-navigation-menu");
    var fromRow = findPublicDashboardRowByName(fromDashboardName).$("i.si-navigation-menu");
    dragAndDropTo(toRow, fromRow);
  }

  public void saveSetting() {
    $("button[id$='save-settings']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='dashboard-configuration-content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findPublicDashboardRowByName(String dashboardName) {
    return $("[id$=':dashboard-table_data']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("tr.ui-widget-content").asFixedIterable()
        .stream().filter(row -> row.getText().contains(dashboardName)).findFirst().get();
  }

  public void createPrivateDashboardFromScratch() {
    openCreatePrivateDashboardMenu();
    $("a[id$=':create-from-scratch']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    waitForElementDisplayed(getMenuIconContainer(), true);
    $$("[id$=':dashboard-title']").filter(Condition.visible).first().sendKeys("My dashboard");
    $$("input[id$=':dashboard-description']").filter(Condition.visible).first().sendKeys("Private dashboard description");
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
    $("div[id$='dashboard-creation-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).
    $("div[class*='ui-corner-top']").shouldBe(appear, DEFAULT_TIMEOUT).$$("a").filter(Condition.attribute("aria-label", "Close")).first().click();
    closeAddDashboardDialog();
  }

  public void createPublicDashboardFromScratch() {
    openCreatePublicDashboardMenu();
    $("a[id$=':create-from-scratch']").shouldBe(getClickableCondition()).click();
    waitForElementDisplayed(getMenuIconContainer(), true);
    $$("[id$=':dashboard-title']").filter(Condition.visible).first().sendKeys("My dashboard");
    $$("input[id$=':dashboard-description']").filter(Condition.visible).first().sendKeys("Public dashboard description");
  }
  
  public SelenideElement getMenuIconContainer() {
    return $("div[id$=':dashboard-creation-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("div[id$=':custom-array-icon']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDashboardTemplates() {
    return $("[id$=':create-new-dashboard-dialog']");
  }

  public void openImportPublicDashboards() {
    getImportDashboardDialog();
    uploadFile("Dashboard_Dashboard_Export.json");
  }

  public SelenideElement getImportDialog() {
    return $("div[id$=':dashboard-import-dialog']");
  }

  public void openImportPrivateDashboards() {
    openCreatePrivateDashboardMenu();
    getImportDashboardDialog();
    uploadFile("Dashboard_Dashboard_Export.json");
  }

  public SelenideElement getShareDashboardDialog() {
    clickButtonOnDashboardConfigurationActionMenu("Share");
    return $("div[id$=':share-dashboard-dialog']");
  }

  public void cancelImportDashboard() {
    $("div[id$='dashboard-import-dialog']").shouldBe(appear, DEFAULT_TIMEOUT).
    $("div[class*='ui-corner-top']").shouldBe(appear, DEFAULT_TIMEOUT).$$("a").filter(Condition.attribute("aria-label", "Close")).first().click();
    closeAddDashboardDialog();
  }

  public void closeAddDashboardDialog() {
    $("[id='dashboard-template-selection-component:create-new-dashboard-dialog']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='dashboard-template-selection-component:create-new-dashboard-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WebElement getConfigurationFilter() {
    return $("widget-configuration-form:new-widget-configuration-component:filter-container").shouldBe(appear,
        DEFAULT_TIMEOUT);
  }

  public WebElement openManageColumnDialog(boolean isTask) {
    String manageColumnPattern = "widget-configuration-form:new-widget-configuration-component:%s-widget-preview:column-toggler";
    String manageColumnLinkId = isTask ? String.format(manageColumnPattern, "task")
        : String.format(manageColumnPattern, "case");
    $("[id='" + manageColumnLinkId + "']").shouldBe(appear, DEFAULT_TIMEOUT)
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    String manageColumnDialogId = "widget-configuration-form:new-widget-configuration-component:column-management-component:column-management-dialog";
    return $("[id='" + manageColumnDialogId + "']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getConfigurationDialog() {
    return $("div[id='new-widget-configuration-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void closeConfigurationDialog() {
    getConfigurationDialog().$(".ui-dialog-footer").$("a").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id='new-widget-configuration-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getDashboardMultiLanguageDialog() {
    $("div[id$='dashboard-template-selection-component:dashboard-creation-component:title-language-config:multiple-languages-dialog']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return $(
        "[id='dashboard-template-selection-component:dashboard-creation-component:title-language-config:multiple-languages-dialog']");
  }

  public void clickOnTextToTranslate(int index) {
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", index))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("div[id$=':overlay-panel-input']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void setTranslatedTitle() {
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 1)).sendKeys(Keys.CONTROL,
        "a");
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 1))
        .sendKeys("Mon tableau de bord");
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 2)).sendKeys(Keys.CONTROL,
        "a");
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 2))
        .sendKeys("Mein Armaturenbrett");
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 3)).sendKeys(Keys.CONTROL,
        "a");
    $(String.format("input[id$=':add-language-detail-form:table-titles:%s:title-input']", 3))
        .sendKeys("Mi cuadro de mandos");
  }

  public void clickOkMultiLanguageDialog() {
    $("button[id$=':multi-language-ok-button']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$=':multi-language-ok-button']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOkCreateDashboard() {
    $("button[id$='dashboard-creation-component:dashboard-create-button']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnAddWidgetButton() {
    $("span#dashboard-header-action").shouldBe(appear, DEFAULT_TIMEOUT).$("button#add-button").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("div#new-widget-dialog").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public StatisticConfigurationPage clickOnCreateCustomStatisticWidgetButton() {
    $("button[id$='create-custom-statistic']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return new StatisticConfigurationPage();
  }

  public StatisticWidgetNewDashboardPage addNewStatisticWidget(String widgetName) {
    setSearchInput(widgetName);
    $("div.new-widget-dialog__content").shouldBe(appear, DEFAULT_TIMEOUT)
    .$$("div.new-widget-dialog__item").filter(Condition.visible).first().shouldBe(appear, DEFAULT_TIMEOUT)
    .$("div.new-widget-dialog__item-details").shouldBe(appear, DEFAULT_TIMEOUT)
    .$("button[id$='add-widget']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return new NewDashboardPage().selectStatisticChartWidget(widgetName);
  }
  
  public void setSearchInput(String widgetName) {
    $("div.new-widget-dialog__search").shouldBe(appear, DEFAULT_TIMEOUT)
        .$("[id $= 'search-input'").shouldBe(appear, DEFAULT_TIMEOUT)
        .sendKeys(widgetName);
  }
}
