package com.axonivy.portal.selenium.page.component;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SecurityMemberNameAndAvatarComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='content-container']";
  }

  public SelenideElement getCurrentSessionUserSecurityMemberNameAndAvatarComponentContainer() {
    switchToIFrameOfTask();
    var element = $("[id='current-session-user-security-member-name-and-avatar-component-container']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return element;
  }

  public SelenideElement getCurrentSessionRoleSecurityMemberNameAndAvatarComponentContainer() {
    var element = $("[id='current-session-role-security-member-name-and-avatar-component-container']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    return element;
  }
}
