package portal.guitest.page;

import org.openqa.selenium.WebElement;

public class ChangePasswordPage extends TemplatePage {

  public void changePassword(String currentPassword, String newPassword) {
    inputCurrentPassword(currentPassword);
    inputConfirmNewPassword(newPassword);
    inputNewPassword(newPassword);
    WebElement changeButton = findElementById("change-password-form:password-setting:save-password-setting");
    changeButton.click();
    waitAjaxIndicatorDisappear();
  }
  
  public void inputCurrentPassword(String currentPassword) {
    inputField("change-password-form:password-setting:current-password", currentPassword);
  }
  
  public void inputNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:new-password", newPassword);
  }
  
  public void inputConfirmNewPassword(String newPassword) {
    inputField("change-password-form:password-setting:confirm-new-password", newPassword);
  }
  
  public void inputField(String inputId, String value) {
    WebElement inputField = findElementById(inputId);
    inputField.sendKeys(value);
  }
  
  public boolean isWrongCurrentPasswordError() {
    WebElement element = findElementByCssSelector("#change-password-form\\:password-setting\\:change-password-messages .ui-messages-error-detail");
    return element.getText().equalsIgnoreCase("Authentication failed, your password seems to be wrong!");
  }
}
