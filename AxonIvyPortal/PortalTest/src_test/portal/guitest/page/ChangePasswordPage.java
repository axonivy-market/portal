package portal.guitest.page;

import org.openqa.selenium.WebElement;

import portal.guitest.common.Sleeper;

public class ChangePasswordPage extends TemplatePage {

  
  @Override
  protected String getLoadedLocator() {
    return "id('change-password-dialog_title')";
  }

  @SuppressWarnings("deprecation")
  public void changePassword(String currentPassword, String newPassword) {
    inputCurrentPassword(currentPassword);
    inputConfirmNewPassword(newPassword);
    inputNewPassword(newPassword);
    WebElement changeButton = findElementById("change-password-form:password-setting:save-password-setting");
    click(changeButton);
    waitAjaxIndicatorDisappear();
  }
  
  private void inputCurrentPassword(String currentPassword) {
    inputField("change-password-form:password-setting:current-password", currentPassword);
  }
  
  private void inputNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:new-password", newPassword);
  }
  
  private void inputConfirmNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:confirm-new-password", newPassword);
  }
  
  private void inputField(String inputId, String value) {
    WebElement inputField = findElementById(inputId);
    inputField.sendKeys(value);
  }
  
  public boolean isWrongCurrentPasswordError() {
    WebElement element = findElementByCssSelector("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail");
    return element.getText().equalsIgnoreCase("Authentication failed, your password seems to be wrong!");
  }
  
  public boolean isNewPasswordNotStrongEnough() {
    WebElement element = findElementByCssSelector("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail");
    return element.getText().equalsIgnoreCase("Password must be at least 4 characters long, contain at least 1 lowercase character, contain at least 1 uppercase character, contain at least 1 number, contain at least 1 special character.");
  }
  
  public WebElement getChangePasswordDialog() {
    Sleeper.sleep(200);//sleep a bit to make focus field effect before taking the screenshot
    return findElementById("change-password-dialog");
  }
}