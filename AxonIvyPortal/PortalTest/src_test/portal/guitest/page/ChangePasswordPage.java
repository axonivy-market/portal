package portal.guitest.page;

import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends TemplatePage {

  
  @Override
  protected String getLoadedLocator() {
    return "id('change-password-dialog_title')";
  }

  public void changePassword(String currentPassword, String newPassword) {
    inputCurrentPassword(currentPassword);
    inputConfirmNewPassword(newPassword);
    inputNewPassword(newPassword);
    WebElement changeButton = findElementById("change-password-form:password-setting:save-password-setting");
    changeButton.click();
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
  
  public boolean isNewCurrentPasswordStrongEnough() {
    WebElement element = findElementByCssSelector("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail");
    return element.getText().equalsIgnoreCase("New password is not strong enough. Must contains at least 1 special character, 1 number, 1 uppercase and 1 lowercase character!");
  }
}
