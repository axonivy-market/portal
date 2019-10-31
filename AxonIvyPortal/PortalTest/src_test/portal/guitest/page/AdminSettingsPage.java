package portal.guitest.page;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import vn.wawa.guitest.base.client.Browser;

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
		settingTabLink.click();
		waitForElementPresent(By.id("adminui:adminTabView:settingForm"), true);
		waitAjaxIndicatorDisappear();
	}

	public void openDesignTab() {
		WebElement settingTabLink = findElementByXpath("//a[@href='#adminui:adminTabView:designTab']");
		settingTabLink.click();
		waitForElementPresent(By.id("adminui:adminTabView:logo-color-form"), true);
	}

	public void chooseMainColor(String color) {
		click(By.id("adminui:adminTabView:logo-color-form:main-color-chooser_button"));
		waitForElementDisplayed(By.className("ui-colorpicker-container"), true);

		WebDriver driver = Browser.getBrowser().getDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.querySelector('div.ui-colorpicker_hex > input').value='" + color + "';");
		jse.executeScript("document.getElementById('adminui:adminTabView:logo-color-form:main-color-chooser_input').value='"
				+ color + "';");
	}

	public String getMainColor() {
		return findElementById("adminui:adminTabView:logo-color-form:main-color-chooser_input").getText();
	}

	public HomePage applyNewColor() {
		click(By.id("adminui:adminTabView:logo-color-form:apply-button"));
		return new HomePage();
	}

	private void editGlobalVariable(String variableName, String variableValue, boolean isBooleanType) {
		WebElement table = findElementById("adminui:adminTabView:settingTable");
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		int index = 0;
		for (WebElement row : tableRows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			if (!CollectionUtils.isEmpty(columns)) {
				WebElement keyColumn = columns.get(0);
				if (keyColumn.getText().equals(variableName)) {
					WebElement editButton = row.findElement(By.id("adminui:adminTabView:settingTable:" + (index - 3) + ":edit"));
					editButton.click();
					waitForElementPresent(By.id("adminui:settingDialogForm"), true);
					saveGlobalVariable(variableValue, isBooleanType);
					return;
				}
			}
			index++;
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
		saveButton.click();
	}

  
  public void closeConfirmationDialog() {
  	waitForElementDisplayed(By.id("adminui:resetConfirmationDialog"),true,3);
    WebElement okButton=findElementById("adminui:resetConfirmationDialog");
    okButton.sendKeys(Keys.ENTER);
    waitAjaxIndicatorDisappear();
  }
	public void closeAdminSettingDialog() {
		WebElement closeButton = findElementById("close-button");
		closeButton.click();
		waitForElementDisplayed(By.id("dialog-closing-information"), true);
	}

	public void closeInformConfigDialog() {
		WebElement closeButton = findElementById("close-dialog-button");
		closeButton.click();
	}

	public void restoreAllToDefaults() {
		openSettingTab();
		WebElement restoreButton = findElementById("adminui:adminTabView:restore-all-to-default-button");
		restoreButton.click();
		closeConfirmationDialog();
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setClientSideTimeout(String timeout) {
		openSettingTab();
		editGlobalVariable("CLIENT_SIDE_TIMEOUT", timeout, false);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setChatGroup() {
		openSettingTab();
		editGlobalVariable("ENABLE_GROUP_CHAT", "true", true);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setChatPrivate() {
		openSettingTab();
		editGlobalVariable("ENABLE_PRIVATE_CHAT", "true", true);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setEnableScriptCheckingGlobalVariable() {
		openSettingTab();
		editGlobalVariable("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "true", true);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}
	
	public void setDisableScriptCheckingGlobalVariable() {
		openSettingTab();
		editGlobalVariable("ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT", "false", true);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setFileExtensionWhiteList() {
		openSettingTab();
		editGlobalVariable("UPLOAD_DOCUMENT_WHITELIST_EXTENSION", ", abc, pdf, doc", false);
		closeAdminSettingDialog();
		closeInformConfigDialog();
	}

	public void setHideUploadDocumentForDoneCase() {
		openSettingTab();
		editGlobalVariable("HIDE_UPLOAD_DOCUMENT_FOR_DONE_CASE", "true", true);
		closeAdminSettingDialog();
		closeInformConfigDialog();
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
		settingTabLink.click();
		waitForElementPresent(By.id("adminui:adminTabView:announcement-tab"), true);
		waitAjaxIndicatorDisappear();
		return new AnnouncementPage();
	}

}
