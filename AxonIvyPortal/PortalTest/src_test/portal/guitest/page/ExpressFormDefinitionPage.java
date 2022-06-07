package portal.guitest.page;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import portal.guitest.common.WaitHelper;
import vn.wawa.guitest.base.client.Browser;

public class ExpressFormDefinitionPage extends TemplatePage {

	private static final int TIME_OUT = 60;
	private static final String LEFT_POSITION = "leftpanel";
	private static final String RIGHT_POSITION = "rightpanel";
	private static final String HEADER_POSITION = "header";
	private static final String FOOTER_POSITION = "footer";
	private static final String[] POSITIONS = {LEFT_POSITION, HEADER_POSITION, FOOTER_POSITION};

	@Override
	protected String getLoadedLocator() {
		return "id('form:create-tabs')";
	}

	@SuppressWarnings("deprecation")
  public void createTextInputField(String label, int inputFieldTypeIndex, boolean isRequired) {
		click(By.xpath("//*[@id='form:create-tabs']/ul/li[1]"));
		waitForElementDisplayed(By.id("form:create-tabs:create-input-field-tab"), true, TIME_OUT);
		type(By.id("form:create-tabs:input-field-label"), label);
		chooseInputFieldType(inputFieldTypeIndex);
		if (isRequired) {
			click(By.cssSelector("div[id='form:create-tabs:input-field-required']"));
		}
		click(By.id("form:create-tabs:add-input-text-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	@SuppressWarnings("deprecation")
  public void createTextAreaField(String label, boolean isRequired) {
		click(By.xpath("//*[@id='form:create-tabs']/ul/li[2]"));
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("form:create-tabs:create-input-area-tab"), true, TIME_OUT);
		type(By.id("form:create-tabs:input-area-label"), label);
		if (isRequired) {
			click(By.cssSelector("div[id='form:create-tabs:input-area-required']"));
		}
		click(By.id("form:create-tabs:add-text-area-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	@SuppressWarnings("deprecation")
  public void createCheckboxField(String label, int numberOfSelection) {
		click(By.xpath("//*[@id='form:create-tabs']/ul/li[3]"));
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("form:create-tabs:many-checkbox-options"), true, TIME_OUT);
		type(By.id("form:create-tabs:many-checkbox-label"), label);
		addCheckboxOptions(numberOfSelection);
		click(By.id("form:create-tabs:add-checkbox-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

  public void switchToCheckBoxTab() {
    waitForElementDisplayed(By.xpath("//*[@id='form:create-tabs']/ul/li[3]"), true);
    click(By.xpath("//*[@id='form:create-tabs']/ul/li[3]"));
    WaitHelper.assertTrueWithWait(() -> {
      var checkboxTab = findElementByXpath("//*[@id='form:create-tabs']/ul/li[3]");
      return checkboxTab.getAttribute(CLASS_PROPERTY).contains("ui-state-active");
    });
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ui-tabs-header.ui-tabs-selected.ui-state-active", CLASS_PROPERTY);
    waitForElementDisplayed(By.id("form:create-tabs:many-checkbox-options"), true, TIME_OUT);
  }

	@SuppressWarnings("deprecation")
  public void createCheckboxFieldWithDataProvider(String label) {
		fillDataForCheckboxProvider(label);
		click(By.id("form:create-tabs:add-checkbox-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

  @SuppressWarnings("deprecation")
  public void fillDataForCheckboxProvider(String label) {
    click(By.xpath("//*[@id='form:create-tabs']/ul/li[3]"));
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("form:create-tabs:create-many-checkbox-tab"), true, TIME_OUT);
		click(By.id("form:create-tabs:DataProvider_label"));
		click(By.xpath("//*[@data-label='TestDataProviderForPortalExpress']"));
		waitAjaxIndicatorDisappear();
		waitForElementDisplayed(By.id("form:create-tabs:many-checkbox-label"), true, TIME_OUT);
		type(By.id("form:create-tabs:many-checkbox-label"), label);
		waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

	@SuppressWarnings("deprecation")
  public void createRadioButtonField(String label, int numberOfOption) {
		click(By.xpath("//*[@id='form:create-tabs']/ul/li[4]"));
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("form:create-tabs:one-radio-label"), true, TIME_OUT);
		type(By.id("form:create-tabs:one-radio-label"), label);
		addRadioOptions(numberOfOption);
		click(By.id("form:create-tabs:add-radio-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	@SuppressWarnings("deprecation")
  public void createUploadComponent(String label) {
		click(By.xpath("//*[@id='form:create-tabs']/ul/li[5]"));
		ensureNoBackgroundRequest();
		waitForElementDisplayed(By.id("form:create-tabs:create-file-upload-tab"), true, TIME_OUT);
		type(By.id("form:create-tabs:file-upload-label"), label);
		click(By.id("form:create-tabs:add-upload-file-btn"));
		waitAjaxIndicatorDisappear();
		ensureNoBackgroundRequest();
	}

	@SuppressWarnings("deprecation")
  private void addRadioOptions(int numberOfOptions) {
		for (int i = 1; i <= numberOfOptions; i++) {
			click(By.id("form:create-tabs:one-radio-options:add-radio-option-btn"));
			waitAjaxIndicatorDisappear();
			type(By.xpath(String.format("//*[@id='form:create-tabs:one-radio-options_data']/tr[%d]/td/input", i)), "Radio " + i);
		}
	}

	@SuppressWarnings("deprecation")
  private void addCheckboxOptions(int numberOfSelection) {
		for (int i = 1; i <= numberOfSelection; i++) {
			click(By.id("form:create-tabs:many-checkbox-options:add-checkbox-option-btn"));
			waitAjaxIndicatorDisappear();
			type(By.xpath(String.format("//*[@id='form:create-tabs:many-checkbox-options_data']/tr[%d]/td/input", i)),
					"Option " + i);
		}
	}

	private void chooseInputFieldType(int inputTypeIndex) {
		click(By.id("form:create-tabs:input-field-type_label"));
		waitForElementDisplayed(By.id("form:create-tabs:input-field-type_panel"), true);
		click(By.id(String.format("form:create-tabs:input-field-type_%d", inputTypeIndex)));
	}

	public void moveAllElementToDragAndDrogPanel() {
		int size = driver.findElements(By.xpath("//div[@id='form:available-form-elements_content']/ul/li")).size();
		int startIndex = size - 1;
		for (int i = startIndex; i >= 0; i--) {
		  if(i == startIndex) {
		    moveFormElementToPanel(i, RIGHT_POSITION);
		  }
		  else {
		    moveFormElementToPanel(i, getRandomPosition());
		  }
		
		}
	}

  private void moveFormElementToPanel(int index, String position) {
    var formElementSelector = String.format("[id$='form:available-form-elements:%d:pnl_content']", index);
    // If elements is FileUpload, move to footer
    if (formElementIsFileUpload(findElementByCssSelector(formElementSelector))) {
      position = FOOTER_POSITION;
    }
    var panelId = String.format("form:selected-form-elements-%s-panel", position);
    waitForElementDisplayed(By.id(panelId), true);
    // this click to fix bug can't drag on IE
    clickByCssSelector(formElementSelector);
    WebElement panel = findElementById(panelId);
    Actions builder = new Actions(driver);
    Action moveProcessSequence = builder.dragAndDrop(findElementByCssSelector(formElementSelector), panel).build();
    moveProcessSequence.perform();
    WaitHelper.assertTrueWithWait(() -> {
      var dropPanel = findElementById(panelId);
      return !dropPanel.getAttribute(CLASS_PROPERTY).contains("ui-droppable-hover");
    });
  }

	private String getRandomPosition() {
		int idx = new Random().nextInt(POSITIONS.length);
		return (POSITIONS[idx]);
	}

	private boolean formElementIsFileUpload(WebElement formElement) {
		WebElement icon = formElement.findElement(By.tagName("img"));
		return icon.getAttribute("src").contains("FileUpload");
	}

	public int countNumberOfElementsInPreviewDialog() {
		click(By.id("form:show-preview-button"));
		waitForElementDisplayed(By.id("form:preview-dialog"), true);
		WebElement previewDialog = findElementById("form:preview-dialog");
		int numberOfInput = previewDialog.findElements(By.xpath("//table[@id='form:dyna-form']//input")).size();
		int numberOfTextArea = previewDialog.findElements(By.xpath("//table[@id='form:dyna-form']//textarea")).size();
		int numberOfUploadFile = previewDialog.findElements(By.xpath("//div[contains(@id,'fileUploadComponent:document-table')]")).size();
		return numberOfInput + numberOfTextArea + numberOfUploadFile;
	}

	public int countNumberOfSteps() {
		return driver.findElements(By.xpath("//div[@id='defined-task-container']//button")).size();
	}

	public void finishWorkflow() {
		click(By.id("finish-button"));
		new HomePage().isDisplayed();
	}

	public void executeWorkflow() {
		click(By.id("execute-button"));
		waitForElementDisplayed(By.id("form:dynaform-fieldset"), true);
	}
	
	public void executeWorkflowAndWaitForUserTaskWithEmailDisplay() {
	  click(By.id("execute-button"));
    waitForElementDisplayed(By.id("task-form:task-view:dyna-form-fieldset"), true);
	}

	@SuppressWarnings("deprecation")
  public void nextStep() {
		click(By.id("next-button"));
		ensureNoBackgroundRequest();
	}

	public void inputMailRecipient(String content) {
		findElementById("form:information-email:email-recipients").sendKeys(content);
	}

	public void inputMailSubject(String content) {
	  findElementById("form:information-email:email-subject").sendKeys(content);
	}

	public void inputMailContent(String content) {
		WebDriver driver = Browser.getBrowser().getDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(
				"document.querySelector(\"input[name='form:information-email:email-content_input'\").value='" + content + "';");
	}

  public HomePage leave() {
    click(By.id("user-menu-required-login:warning-before-leaving-task-component:leave-button"));
    return new HomePage();
  }
  
  public HomePage cancel() {
    click(By.id("cancel-button"));
    waitForElementDisplayed(By.id("yes-button"), true);
    click(By.id("yes-button"));
    return new HomePage();
  }
  
  public HomePage save() {
    click(By.id("save-button"));
    return new HomePage();
  }
}
