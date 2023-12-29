package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

public class AdminSettingsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='portal-management-container']";
  }

  public ExpressManagementPage openExpressManagementTab() {
    $("[id='admin-setting-component:adminTabView']").shouldBe(appear, DEFAULT_TIMEOUT).$$("li.ui-tabs-header")
      .asFixedIterable().stream().filter(WebElement::isDisplayed).filter(tab -> tab.$("a").getText().contentEquals("Express Management")).findFirst().get()
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    $("[id='admin-setting-component:adminTabView:express-management-tab']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
    return new ExpressManagementPage();
  }

  public WebElement getAdminSettingContainer() {
    return $("[id='admin-settings-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getAddApplicationDialog( ) {
    $("[id='admin-setting-component:adminTabView:add-application-btn']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='admin-setting-component:appDialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openSettingTab() {
    $("a[href$='#admin-setting-component:adminTabView:setting-tab']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':adminTabView:settingForm']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
  }

  public WebElement getEditSettingDialogOfFirstRow() {
    $("[id='admin-setting-component:adminTabView:settingTable:0:edit']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='admin-setting-component:settingDialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public AnnouncementPage openAnnouncementTab() {
    $("a[href$='#admin-setting-component:adminTabView:announcement-tab']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:adminTabView:announcement-form']").shouldBe(appear, DEFAULT_TIMEOUT);
    waitForAjaxIndicatorDisappeared();
    return new AnnouncementPage();
  }

  public RoleManagementPage openRoleManagementTab() {
    $("a[href='#admin-setting-component:adminTabView:role-management-tab']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':role-management-component:role-management-form:role-tree-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    return new RoleManagementPage();
  }

  public void closeAddApplicationDialog() {
    $("[id='admin-setting-component:appDialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:appDialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  public void closeEditSettingDialog() {
    $("[id='admin-setting-component:settingDialog']").shouldBe(appear, DEFAULT_TIMEOUT).$("a.ui-dialog-titlebar-close").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='admin-setting-component:settingDialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public PasswordValidationPage openPasswordValidationTab() {
    $("[id$='admin-setting-component:adminTabView']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[href='#admin-setting-component:adminTabView:password-validation-tab']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':password-validation-component:password-validation-form:password-policy-setting']").shouldBe(appear, DEFAULT_TIMEOUT);
    return new PasswordValidationPage();
  }
}
