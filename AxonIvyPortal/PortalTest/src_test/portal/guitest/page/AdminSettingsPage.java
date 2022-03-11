package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.Sleeper;

public class AdminSettingsPage extends TemplatePage {

  private static final String DIALOG_TITLE = "admin-ui-dialog_title";

  public AdminSettingsPage() {
    waitForElementDisplayed(By.id(DIALOG_TITLE), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('" + DIALOG_TITLE + "')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(DIALOG_TITLE).isDisplayed();
  }

  private void openSettingTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:settingTab']");
    click(settingTabLink);
    waitForElementPresent(By.id("adminui:adminTabView:settingForm"), true);
    waitAjaxIndicatorDisappear();
  }

  private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
    WebElement table = findElementById("adminui:adminTabView:settingTable");
    List<WebElement> tableRows = table.findElements(By.tagName("tr"));
    for (WebElement row : tableRows) {
      List<WebElement> columns = row.findElements(By.tagName("td"));
      if (!CollectionUtils.isEmpty(columns)) {
        WebElement keyColumn = columns.get(0);
        if (keyColumn.getText().equals(variableName)) {
          WebElement editButton = row.findElement(By.cssSelector("a[id$=edit]"));
          click(editButton);
          waitForElementPresent(By.id("adminui:settingDialogForm"), true);
          saveGlobalVariable(variableValue, isBooleanType);
          return;
        }
      }
    }
  }

  private void saveGlobalVariable(String value, boolean isBooleanType) {
    if (!isBooleanType) {
      WebElement valueInput = findElementById("adminui:valueSetting");
      valueInput.clear();
      valueInput.sendKeys(value);
    } else {
      click(By.id("adminui:valueSetting_label"));
      waitForElementDisplayed(By.id("adminui:valueSetting_panel"), true);
      boolean boolValue = Boolean.parseBoolean(value);
      int index = boolValue ? 1 : 0;
      click(By.id(String.format("adminui:valueSetting_%d", index)));
    }
    WebElement saveButton = findElementById("adminui:save-setting");
    click(saveButton);
  }

  private void editExternalSelectionGlobalVariable(String variableName, String variableValue) {
    WebElement table = findElementById("adminui:adminTabView:settingTable");
    List<WebElement> tableRows = table.findElements(By.tagName("tr"));
    for (WebElement row : tableRows) {
      List<WebElement> columns = row.findElements(By.tagName("td"));
      if (!CollectionUtils.isEmpty(columns)) {
        WebElement keyColumn = columns.get(0);
        if (keyColumn.getText().equals(variableName)) {
          WebElement editButton = row.findElement(By.cssSelector("a[id$=edit]"));
          click(editButton);
          waitForElementPresent(By.id("adminui:settingDialogForm"), true);
          saveExternalSelectionGlobalVariable(variableValue);
          return;
        }
      }
    }
  }

  private void saveExternalSelectionGlobalVariable(String value) {
    click(By.id("adminui:valueSetting_label"));
    waitForElementDisplayed(By.id("adminui:valueSetting_panel"), true);
    WebElement selection = findElementById("adminui:valueSetting_items");
    List<WebElement> items = selection.findElements(By.tagName("li"));
    for (WebElement item: items) {
      if (item.getText().equalsIgnoreCase(value)) {
        click(item);
        break;
      }
    }
    WebElement saveButton = findElementById("adminui:save-setting");
    saveButton.click();
  }

  public void closeAdminSettingDialog() {
    WebElement closeButton = findElementById("close-button");
    click(closeButton);
    waitForElementDisplayed(By.id("dialog-closing-information"), true);
  }

  public void closeInformConfigDialog() {
    String closeButtonId = "close-dialog-button";
    click(findElementById(closeButtonId));
    waitForElementDisplayed(By.id(closeButtonId), false);
  }

  public void setClientSideTimeout(String timeout) {
    openSettingTab();
    editGlobalVariable("CLIENT_SIDE_TIMEOUT", timeout, false);
    closeConfirmationDialog();
  }

  private void closeConfirmationDialog() {
    closeAdminSettingDialog();
    closeInformConfigDialog();
  }

  public void setChatGroup() {
    openSettingTab();
    editGlobalVariable("ENABLE_GROUP_CHAT", "true", true);
    closeConfirmationDialog();
  }

  public void setEnviromentInfo() {
    openSettingTab();
    editGlobalVariable("SHOW_ENVIRONMENT_INFO", "true", true);
    closeConfirmationDialog();
  }
  

  public void setChatPrivate() {
    openSettingTab();
    editGlobalVariable("ENABLE_PRIVATE_CHAT", "true", true);
    closeConfirmationDialog();
  }

  public void setEnableScriptCheckingGlobalVariable() {
    openSettingTab();
    editGlobalVariable("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "true", true);
    closeConfirmationDialog();
  }

  public void setDisableScriptCheckingGlobalVariable() {
    openSettingTab();
    editGlobalVariable("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "false", true);
    closeConfirmationDialog();
  }

  public void setFileExtensionWhiteList() {
    openSettingTab();
    editGlobalVariable("UPLOAD_DOCUMENT_WHITELIST_EXTENSION", ", abc, pdf, doc", false);
    closeConfirmationDialog();
  }

  public void setHideUploadDocumentForDoneCase() {
    openSettingTab();
    editGlobalVariable("HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE", "true", true);
    closeConfirmationDialog();
  }

  public void setDisabledTaskCount() {
    openSettingTab();
    editGlobalVariable("DISABLE_TASK_COUNT", "true", true);
    closeConfirmationDialog();
  }

  public void setDisabledCaseCount() {
    openSettingTab();
    editGlobalVariable("DISABLE_CASE_COUNT", "true", true);
    closeConfirmationDialog();
  }

  public void setRunTaskWhenClickingOnLineInTaskList() {
    openSettingTab();
    editExternalSelectionGlobalVariable("BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST", "Run the task");
    closeConfirmationDialog();
  }

  public boolean isWarningDialogShowWhenTimeoutIsLosing() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"), true, 120);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-warning-dialog"));
  }

  public boolean isInformDialogShowAfterTimeout() {
    waitForElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"), true, 180);
    return isElementDisplayed(By.id("warning-before-lost-session:timeout-dialog"));
  }

  public AnnouncementPage openAnnouncementTab() {
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:announcement-tab']");
    click(settingTabLink);
    waitForElementPresent(By.id("adminui:adminTabView:announcement-tab"), true);
    waitAjaxIndicatorDisappear();
    return new AnnouncementPage();
  }

  public void openExpressManagementTab() {
    refreshAndWaitElement("a[href$='express-management-tab']");
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(
        () -> findElementByXpath("//a[@href='#adminui:adminTabView:express-management-tab']").isEnabled()
            && findElementByXpath("//a[@href='#adminui:adminTabView:express-management-tab']").isDisplayed());
    WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:express-management-tab']");
    //Unstable click
    Sleeper.sleep(3000);
    settingTabLink.click();
    waitForElementPresent(By.id("adminui:adminTabView:express-management-tab"), true);
    waitAjaxIndicatorDisappear();
  }

}
