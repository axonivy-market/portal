package com.axonivy.portal.selenium.page;

import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class AdminSettingsPage extends TemplatePage {

  private static final String PORTAL_MANAGEMENT_PAGE_ID = "portal-management-container";

  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(PORTAL_MANAGEMENT_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id='" + PORTAL_MANAGEMENT_PAGE_ID + "']";
  }

  @Override
  public boolean isDisplayed() {
    return $(By.id(PORTAL_MANAGEMENT_PAGE_ID)).isDisplayed();
  }

  public void openSettingTab() {
    waitForElementClickableThenClick("a[href$='#admin-setting-component:adminTabView:setting-tab']");
    waitForElementDisplayed(By.cssSelector("[id$=':adminTabView:settingForm']"), true);
    waitForFocusEffectComplete();
  }

  private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
    openGlobalVariableEditDialog(variableName);
    waitForElementDisplayed(By.cssSelector("[id$=':settingDialogForm']"), true);
    saveGlobalVariable(variableValue, isBooleanType);
  }

  public void updateGlobalMultiSelectionVariable(String variableName, List<String> selectedValues) {
    openSettingTab();
    openGlobalVariableEditDialog(variableName);
    waitForElementDisplayed(By.cssSelector("[id$=':settingDialogForm']"), true);
    saveMultiSelectionGlobalVariable(selectedValues);
  }

  public void updateGlobalMultiSelectionItem(String variableName, String itemValue, boolean shouldBeSelected) {
    openSettingTab();
    openGlobalVariableEditDialog(variableName);
    waitForElementDisplayed(By.cssSelector("[id$=':settingDialogForm']"), true);
    setMultiSelectionItemStateAndSave(itemValue, shouldBeSelected);
  }

  private void openGlobalVariableEditDialog(String variableName) {
    $("button[id$='admin-setting-component:adminTabView:restore-all-to-default-button']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition());
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:settingTable"), true);
    var variableRow = $("[id='admin-setting-component:adminTabView:settingTable']").$$(".setting-key")
        .filter(Condition.text(variableName)).get(0);

    if (variableRow != null) {
      variableRow.ancestor("tr").scrollTo();
      SelenideElement actionButton = variableRow.ancestor("tr").$(By.cssSelector("button[id$=settings-action-button]"));
      actionButton.shouldBe(getClickableCondition()).click();
      String currentElementId = actionButton.attr("id");
      String editButtonId = currentElementId.replace("settings-action-button", "edit-application");
      $("a[id='" + editButtonId + "']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
  }

  public void resetAllSettings() {
    openSettingTab();
    waitForElementClickableThenClick("[id='admin-setting-component:adminTabView:restore-all-to-default-button']");
    waitForElementClickableThenClick("[id='admin-setting-component:reset-settings']");
    closeConfirmationDialog();
  }

  private void saveGlobalVariable(String value, boolean isBooleanType) {
    if (!isBooleanType) {
      WebElement valueInput = $("[id='admin-setting-component:valueSetting']");
      valueInput.clear();
      valueInput.sendKeys(value);
    } else {
      waitForElementClickableThenClick("[id='admin-setting-component:valueSetting_label']");
      waitForElementDisplayed(By.id("admin-setting-component:valueSetting_panel"), true);
      boolean boolValue = Boolean.parseBoolean(value);
      int index = boolValue ? 1 : 0;
      $(By.id(String.format("admin-setting-component:valueSetting_%d", index)))
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    waitForElementClickableThenClick("[id='admin-setting-component:save-setting']");
  }

  private void saveMultiSelectionGlobalVariable(List<String> selectedValues) {
    waitForElementClickableThenClick("[id='admin-setting-component:valueSetting_label']");
    SelenideElement valueSettingPanel = $(By.id("admin-setting-component:valueSetting_panel"))
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    while (valueSettingPanel.$("li.ui-selectcheckboxmenu-item div.ui-chkbox-box.ui-state-active").exists()) {
      valueSettingPanel.$("li.ui-selectcheckboxmenu-item div.ui-chkbox-box.ui-state-active")
          .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }

    for (String selectedValue : selectedValues) {
      SelenideElement valueCheckbox = findMultiSelectionValueCheckbox(valueSettingPanel, selectedValue);
      if (!valueCheckbox.getAttribute("class").contains("ui-state-active")) {
        valueCheckbox.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      }
    }

    closeMultiSelectionPanel(valueSettingPanel);
    waitForElementClickableThenClick("[id='admin-setting-component:save-setting']");
  }

  private void setMultiSelectionItemStateAndSave(String itemValue, boolean shouldBeSelected) {
    waitForElementClickableThenClick("[id='admin-setting-component:valueSetting_label']");
    SelenideElement valueSettingPanel = $(By.id("admin-setting-component:valueSetting_panel"))
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    SelenideElement valueCheckbox = findMultiSelectionValueCheckbox(valueSettingPanel, itemValue);
    boolean isSelected = valueCheckbox.getAttribute("class").contains("ui-state-active");
    if (shouldBeSelected != isSelected) {
      valueCheckbox.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }

    closeMultiSelectionPanel(valueSettingPanel);
    waitForElementClickableThenClick("[id='admin-setting-component:save-setting']");
  }

  private void closeMultiSelectionPanel(SelenideElement valueSettingPanel) {
    valueSettingPanel.$("a.ui-selectcheckboxmenu-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    valueSettingPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement findMultiSelectionValueCheckbox(SelenideElement valueSettingPanel, String optionKey) {
    String normalizedOptionKey = normalizeForLabelComparison(optionKey);
    for (SelenideElement option : valueSettingPanel.$$("li.ui-selectcheckboxmenu-item")) {
      String optionLabel = option.$("label").shouldBe(Condition.appear, DEFAULT_TIMEOUT).getText();
      if (normalizeForLabelComparison(optionLabel).equals(normalizedOptionKey)) {
        return option.$("div.ui-chkbox-box").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
      }
    }

    throw new IllegalArgumentException("Cannot find multi-selection option: " + optionKey);
  }

  private String normalizeForLabelComparison(String value) {
    return value.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
  }

  public void closeConfirmationDialog() {
    clickOnHomeLogo();
  }

  public void clickOnHomeLogo() {
    $("span[class*='ti ti-home']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public void setGlobalFooterInfo() {
    openSettingTab();
    editGlobalVariable(GLOBAL_FOOTER_INFO.getKey(), "Dev Team: Wawa, Env: Dev", false);
    closeConfirmationDialog();
  }

  public boolean isWarningDialogShowWhenTimeoutIsLosing() {
    waitForElementDisplayed(By.cssSelector("div[id$=':timeout-warning-dialog']"), true, 121);
    return $(By.cssSelector("div[id$=':timeout-warning-dialog']")).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .isDisplayed();
  }

  public boolean isInformDialogShowAfterTimeout() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 181);
    return $(By.id("warning-before-lost-session:timeout-dialog")).shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .isDisplayed();
  }

  public AnnouncementPage openAnnouncementTab() {
    waitForElementClickable($(By.xpath("//a[@href='#admin-setting-component:adminTabView:announcement-tab']"))).click();
    waitForElementDisplayed(By.id("admin-setting-component:adminTabView:announcement-tab"), true);
    waitForFocusEffectComplete();
    return new AnnouncementPage();
  }

  public RoleManagementPage openRoleManagementTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    waitForElementClickableThenClick("a[href='#admin-setting-component:adminTabView:role-management-tab']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-form:role-tree-table']"),
        true);
    waitForFocusEffectComplete();
    return new RoleManagementPage();
  }

  public boolean isRoleAssingmentTabViewPresent() {
    return isElementPresent(By.cssSelector("a[href='#admin-setting-component:adminTabView:role-management-tab']"));
  }

  public boolean isPasswordValidationTabViewPresent() {
    return isElementPresent(By.cssSelector("a[href='#admin-setting-component:adminTabView:password-validation-tab']"));
  }

  public PasswordValidationPage openPasswordValidationTab() {
    waitForElementDisplayed(By.cssSelector("[id$='admin-setting-component:adminTabView']"), true);
    waitForElementClickableThenClick($("a[href='#admin-setting-component:adminTabView:password-validation-tab']"));
    waitForElementDisplayed(
        By.cssSelector("[id$=':password-validation-component:password-validation-form:password-policy-setting']"),
        true);
    waitForFocusEffectComplete();
    return new PasswordValidationPage();
  }
  
  private void waitForFocusEffectComplete() {
    Sleeper.sleep(300);// Wait for focus effect only on a specific tab before taking screenshot
  }

  public WebElement getAdminSettingContainer() {
    return findElementById("admin-settings-container");
  }

  public WebElement getAddApplicationDialog() {
    waitForElementClickableThenClick($(By.id("admin-setting-component:adminTabView:add-application-btn")));
    waitForElementDisplayed(By.id("admin-setting-component:appDialog"), true);
    waitForFocusEffectComplete();
    return findElementById("admin-setting-component:appDialog");
  }

  public WebElement getEditSettingDialogOfFirstRow() {
    waitForElementClickableThenClick($(By.id("admin-setting-component:adminTabView:settingTable:0:settings-action-button")));
    $("a[id='admin-setting-component:adminTabView:settingTable:0:edit-application']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitForElementDisplayed(By.id("admin-setting-component:settingDialog"), true);
    waitForFocusEffectComplete();
    return findElementById("admin-setting-component:settingDialog");
  }

  public void closeAddApplicationDialog() {
    $("[id='admin-setting-component:appDialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:appDialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }

  public void closeEditSettingDialog() {
    $("[id='admin-setting-component:settingDialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
        .$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:settingDialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void clickOnActions(String dataRowIndex) {
    $("div.ui-datatable-scrollable-body").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$("table > tbody").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("tr").filter(Condition.attribute("data-ri", dataRowIndex)).first()
    .$("button[id$='settings-action-button']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).click();
    $("div[id$='settings-action-menu']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
}
