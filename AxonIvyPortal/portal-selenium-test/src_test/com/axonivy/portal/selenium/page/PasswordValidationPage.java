package com.axonivy.portal.selenium.page;

public class PasswordValidationPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:password-validation-component:password-validation-form']";
  }
}
