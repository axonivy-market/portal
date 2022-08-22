package portal.guitest.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SecurityMemberNameAndAvatarComponentPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('content-container')";
  }

  public WebElement getCurrentSessionUserSecurityMemberNameAndAvatarComponentContainer() {
    switchToIFrameOfTask();
    waitForElementDisplayed(By.id("current-session-user-security-member-name-and-avatar-component-container"), true); 
    return findElementById("current-session-user-security-member-name-and-avatar-component-container");
  }

  public WebElement getCurrentSessionRoleSecurityMemberNameAndAvatarComponentContainer() {
    switchToIFrameOfTask();
    waitForElementDisplayed(By.id("current-session-role-security-member-name-and-avatar-component-container"), true); 
    return findElementById("current-session-role-security-member-name-and-avatar-component-container");
  }
}
