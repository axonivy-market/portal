package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

public class RoleManagementPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:role-management-component:role-management-form']";
  }

  public void openRoleCreationDialog() {
    $("[id$=':role-management-component:role-management-form:create-role-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$=':role-management-component:role-details-dialog_content']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getRoleCreationDialog() {
    return $("[id='admin-setting-component:adminTabView:role-management-component:role-details-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnCancelLinkOfRoleDialog() {
    getRoleDetailsDialog().shouldBe(appear, DEFAULT_TIMEOUT);
    $("[id$=':role-management-component:cancel-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getRoleDetailsDialog().shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  private SelenideElement getRoleDetailsDialog() {
    return $("[id$=':role-management-component:role-details-dialog']");
  }
}
