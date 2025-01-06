package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class ProcessWidgetPage extends TemplatePage {
  public static final String IMAGE_MODE = "IMAGE";
  public static final String GRID_MODE = "GRID";
  private static final String ACTIVE_MODE_SELECTOR =
      "#process-widget\\:process-view-mode\\:view-mode-selection > div.ui-button.ui-button-text-only.ui-state-active > span";
  private static final String IMAGE_EDIT_PROCESS_LINK =
      "[id$=':%d:image-processes:%d:process-item:image-process-action-component:edit-process']";
  private static final String GRID_ACTION_BUTTON =
      "[id$=':%d:grid-processes:%d:process-grid-item:process-item:grid-process-action-component:process-action-button']";
  private SelenideElement liveSearchTextField;
  private String processWidgetId;

  public ProcessWidgetPage() {
    this("process-widget");
  }

  public ProcessWidgetPage(String processWidgetId) {
    this.processWidgetId = processWidgetId;
    findElementById(this.processWidgetId);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id$=':process-list']";
  }

  public class AddNewExternalLinkDialog {
    private final String EXTERNAL_LINK_NAME_INPUT_CSS_SELECTOR =
        "input[id$=':add-external-link-form:external-link-name']";
    private final String EXTERNAL_LINK_INPUT_CSS_SELECTOR = "input[id$=':add-external-link-form:external-link']";
    private final String ADD_EXTERNAL_LINK_BUTTON_INPUT_CSS_SELECTOR = "button[id$='adding-new-external-link-command']";

    private AddNewExternalLinkDialog() {}

    public void inputDataForPrivateExternalLink(String processName, String processLink) {
      inputExternalLinkName(processName);
      inputExternalLink(processLink);
    }

    public void inputDataForPublicExternalLink(String processName, String processLink, String rolePermission) {
      inputExternalLinkName(processName);
      inputExternalLink(processLink);
      selectExternalLinkType(true);
      selectExternalLinkRolePermission(rolePermission);
    }

    public void inputExternalLinkName(String name) {
      SelenideElement externalLinkInputField = findDisplayedElementByCssSelector(EXTERNAL_LINK_NAME_INPUT_CSS_SELECTOR);
      externalLinkInputField.sendKeys(name);
    }

    public void inputExternalLink(String link) {
      SelenideElement externalLinkInputField = findDisplayedElementByCssSelector(EXTERNAL_LINK_INPUT_CSS_SELECTOR);
      externalLinkInputField.sendKeys(link);
    }

    public void selectExternalLinkType(boolean isPublic) {
      if (isPublic) {
        SelenideElement externalCheckboxLabel =
            findElementByCssSelector("label[for$=':add-external-link-form:external-link-type-radio:1']");
        externalCheckboxLabel.click();
      }
    }

    public void selectExternalLinkRolePermission(String rolePermission) {
      SelenideElement rolePermissionInput = findDisplayedElementByCssSelector(
          "[id$='process-widget:add-external-link-form:role-autocomplete-for-creating-external-link_input");
      rolePermissionInput.sendKeys(rolePermission);
      waitForElementDisplayed(
          By.cssSelector(
              "span[id$='process-widget:add-external-link-form:role-autocomplete-for-creating-external-link_panel']"),
          true);
      rolePermissionInput.sendKeys(Keys.DOWN);
      rolePermissionInput.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
      SelenideElement submitButton = findElementByCssSelector(ADD_EXTERNAL_LINK_BUTTON_INPUT_CSS_SELECTOR);
      waitForElementClickableThenClick(submitButton);
    }
  }

  public void addExternalLink(String name, String link, String iconClass, String imageName) {
    openExternalLinkDialog();

    $("input[id$=':add-external-link-form:external-link-name']").sendKeys(name);
    $("input[id$=':add-external-link-form:external-link']").sendKeys(link);

    // change icon
    $("a[id$=':external-link-icon:change-icon-link']").shouldBe(getClickableCondition()).click();
    $("div[id='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']").shouldBe(appear,
        DEFAULT_TIMEOUT);

    $("input[id$=':search-icon-name-field']").sendKeys(iconClass);

    $("div[id='process-widget:add-external-link-form:external-link-icon:icons-selection-form:icons']")
        .$("a[title='" + iconClass + "']").click();
    $("div[id$='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']")
        .shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

    $("input[id$='process-widget:add-external-link-form:external-link-image-upload_input']")
        .sendKeys(FileHelper.getAbsolutePathToTestFile(imageName));

    $("img[id$='process-widget:add-external-link-form:external-link-preview-image']").shouldBe(Condition.appear,
        DEFAULT_TIMEOUT);

    $("button[id$='adding-new-external-link-command']").click();
    $("div[id='process-widget:add-external-link-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);

  }

  public WebElement openExternalLinkDialog() {
    $("a[id$=':add-external-link-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='process-widget:add-external-link-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void findProcess(String keyword) {
    $("input[id='process-widget:process-search:non-ajax-keyword-filter']").sendKeys(keyword);
    $("div.js-external-link-process-item").shouldBe(Condition.appear, DEFAULT_TIMEOUT);

    System.out.println($("i[id='icon']").getAttribute("class"));
  }

  public void waitForStartListShow() {
    $(".js-loading-process-list").shouldBe(Condition.hidden, DEFAULT_TIMEOUT);
    $(".js-process-start-list-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void checkProcessNotExists(String keyword) {
    $("input[id='process-widget:process-search:non-ajax-keyword-filter']").sendKeys(keyword);
    $("div.js-external-link-process-item").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public WebElement navigateToProcessIndex(String character) {
    $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    return $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(),
        DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessEditMenu(int index) {
    return $(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getEditProcessDialog() {
    return $("[id$='process-widget:edit-process-dialog']");
  }

  public void waitUtilProcessWidgetDisplayed() {
    $(".js-loading-process-list").shouldBe(disappear, DEFAULT_TIMEOUT);
    $(".js-process-start-list-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectImageMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:0']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='process-widget:image-process-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectGridMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:1']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='process-widget:grid-process-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectCompactMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:2']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='alphabet-process-index-fieldset']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitUntilProcessDisplayed(String name) {
    $("[id='process-widget:process-list']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".js-process-start-list-item-name")
        .asFixedIterable().stream().filter(WebElement::isDisplayed)
        .filter(process -> process.getText().contentEquals(name)).findFirst().get().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFilterTextfield() {
    return $("[id='process-widget:process-search:non-ajax-keyword-filter']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void startProcessByName(String processName) {
    WaitHelper.waitForNavigation(() -> getProcessItem(processName).$("[id$=':start-button']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }

  public void selectViewMode(String viewMode) {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:process-view-mode:view-mode-selection']"), true);
    SelenideElement viewModeElement =
        findElementByCssSelector("[id$='process-widget:process-view-mode:view-mode-selection']");
    ElementsCollection webElements = viewModeElement.$$(By.cssSelector("span.ui-button-text.ui-c"));
    if (!webElements.isEmpty()) {
      for (SelenideElement webElement : webElements) {
        if (webElement.getText().equalsIgnoreCase(viewMode)) {
          waitForElementClickableThenClick(webElement);
          $(ACTIVE_MODE_SELECTOR).shouldBe(Condition.and("should be visible", appear, Condition.text(viewMode)),
              DEFAULT_TIMEOUT);
          WaitHelper.assertTrueWithWait(() -> {
            if (IMAGE_MODE.equalsIgnoreCase(viewMode)) {
              return findElementByCssSelector(".process-group.image-process-container").isDisplayed();
            }
            if (GRID_MODE.equalsIgnoreCase(viewMode)) {
              return findElementByCssSelector(".process-group.grid-process-container").isDisplayed();
            }
            return true;
          });
          return;
        }
      }
    }
  }

  public void enterSearchKeyword(String keyword) {
    liveSearchTextField = $(By.id(processWidgetId + ":process-search:non-ajax-keyword-filter"));
    liveSearchTextField.sendKeys(keyword);
  }

  public SelenideElement getStartImageProcess(String processName, SelenideElement processListElement) {
    SelenideElement startProcessItemElement = null;
    ElementsCollection processItems = processListElement.$$(".js-process-start-list-item");
    for (SelenideElement process : processItems) {
      SelenideElement processNameElement = process.$(".js-process-start-list-item-name");
      if (processNameElement.isDisplayed() && processName.equalsIgnoreCase(processNameElement.getText())) {
        startProcessItemElement = process.$("button[id$=':start-button']");
        break;
      }
    }
    return startProcessItemElement;
  }

  public SelenideElement getProcess(String processName) {
    SelenideElement processListElement = findElementById(processWidgetId + ":process-list");
    if (isImageModeActivated()) {
      return getStartImageProcess(processName, processListElement);
    }
    return processListElement.$(By.xpath("//a[.//text() = '" + processName + "']"));
  }

  public boolean isImageModeActivated() {
    ElementsCollection findElements = $$(By.cssSelector("[id$=':image-process-container']"));
    return !findElements.isEmpty();
  }

  public boolean isProcessDisplay(String processName) {
    return getProcess(processName) != null;
  }

  public boolean isNoProcessFound() {
    return $(By.xpath("//span[@id='process-widget:no-found-processes']")).isEnabled();
  }

  public boolean isProcessGroupDisplay(String processGroupCharacter) {
    if (isImageModeActivated()) {
      ElementsCollection webElements = $$(".js-grid-process-index-group");
      return webElements.asFixedIterable().stream().anyMatch(processItem -> processItem.isDisplayed()
          && processItem.getAttribute("class").endsWith(processGroupCharacter));
    }
    ElementsCollection indexGroup =
        $$(By.xpath("//legend[@class='ui-fieldset-legend ui-corner-all ui-state-default']"));
    return indexGroup.asFixedIterable().stream().anyMatch(item -> processGroupCharacter.equals(item.getText()));
  }

  public String getCurrentViewMode() {
    waitForElementDisplayed(By.cssSelector(
        "[id$=':process-view-mode:view-mode-selection'] div.ui-button.ui-button-text-only.ui-state-active"), true);
    return findElementByCssSelector(ACTIVE_MODE_SELECTOR).getText();
  }

  public String getProcessItemIcon(int index) {
    WebElement processItem = findElementByCssSelector(
        String.format("[id$='grid-process-group-alphabet:%d:grid-processes:0:process-grid-item:process-item']", index));
    return processItem.findElement(By.id("icon")).getAttribute("class");
  }

  public void waitForImageProcessListDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:image-process-container']"), true);
  }

  public void waitForGridProcessListDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:grid-process-container']"), true);
  }

  public void clickMoreButtonOfFirstImageProcess() {
    $$("[id$=':process-item:image-process-action-component:process-action-button']").asFixedIterable().stream()
        .filter(WebElement::isDisplayed).filter(WebElement::isEnabled).findFirst().ifPresent(firstActionButton -> {
          clickByJavaScript(firstActionButton);
        });
  }

  public void changeProcessIcon() {
    waitForElementClickableThenClick($(By.cssSelector("a[id^='process-widget:edit-process-form:edit-process-icon']")));
    waitForElementDisplayed(
        By.cssSelector("[id$='process-widget:edit-process-form:edit-process-icon:select-icon-dialog']"), true);
    waitForElementClickableThenClick($(By.cssSelector("[id$=':0:awesome-icon']")));

  }

  public void clickOnProcessEditMenu(int index) {
    waitForElementClickableThenClick($(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index)));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), true);
  }

  public void addNewRolePermission(String rolePermission) {
    waitForElementDisplayed(By.cssSelector(
        "span[id='process-widget:edit-process-form:role-permissions-editor-for-external-link_display'] a.si.si-graphic-tablet-drawing-pen"),
        true);
    waitForElementClickableThenClick($(
        "span[id='process-widget:edit-process-form:role-permissions-editor-for-external-link_display'] a.si.si-graphic-tablet-drawing-pen"));
    WebElement rolePermissionInput = findDisplayedElementByCssSelector(
        "[id$='process-widget:edit-process-form:role-autocomplete-for-editing-external-link_input']");
    rolePermissionInput.sendKeys(rolePermission);
    waitForElementDisplayed(By.cssSelector(
        "span[id$='process-widget:edit-process-form:role-autocomplete-for-editing-external-link_panel']"), true);
    rolePermissionInput.sendKeys(Keys.DOWN);
    rolePermissionInput.sendKeys(Keys.ENTER);
    WebElement savePermissionButton = findDisplayedElementByCssSelector(
        "span[id='process-widget:edit-process-form:role-permissions-editor-for-external-link_editor'] button.ui-button-icon-only.ui-inplace-save");
    savePermissionButton.click();
  }

  public void saveEditProcessDialog() {
    waitForElementClickableThenClick($("[id$='process-widget:save-process-command']"));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), false);
  }

  public void deleteGridProcess(int index) {
    waitForElementClickableThenClick($(String.format(
        "[id$=':%d:grid-processes:0:process-grid-item:process-item:grid-process-action-component:delete-process']",
        index)));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), true);
    waitForElementClickableThenClick("[id$='delete-process-workflow-form:remove-process-command']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), false);
  }

  public void clickMoreInformationLinkImage(String processName) {
    getProcessActionMenu(processName)
    .$("ul[class*='ui-helper-reset']").shouldBe(Condition.appear, DEFAULT_TIMEOUT)
    .$$("li > a > span").filter(Condition.exactText("More Information")).first().click();
  }

  public void clickMoreInformationLink(String processName) {
    enterSearchKeyword(processName);
    getProcessActionMenu(processName).$("ul[class*='ui-helper-reset']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$$("li > a > span").filter(Condition.exactText("More Information")).first().click();
  }
  
  private SelenideElement getProcessActionMenu(String processName) {
    clickProcessMoreActionMenu(processName);
    return $$("div[id$='process-action-menu']").filter(Condition.appear).first();
  }

  public void clickProcessMoreActionMenu(String processName) {
    SelenideElement processItem = getProcessItem(processName);
    processItem.$("button[id*=':process-action-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getProcessItem(String processName) {
    SelenideElement processItemElement = null;
    ElementsCollection processItems = $$(".js-process-start-list-item");
    for (SelenideElement process : processItems) {
      processItemElement = process.$(".js-process-start-list-item-name");
      if (processItemElement.getText().equalsIgnoreCase(processName)) {
        processItemElement = process;
        break;
      }
    }
    return processItemElement;
  }

  public AddNewExternalLinkDialog openNewExternalLinkDialog() {
    waitForElementDisplayed(By.id(processWidgetId + ":add-external-link-command"), true);
    waitForElementClickableThenClick($(By.id("process-widget:add-external-link-command")));
    return new AddNewExternalLinkDialog();
  }

  public void waitForCompactProcessListDisplayed() {
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:process-list']"), true);
  }

  public void deleteGridProcess(String processName) {
    var processItem = getProcessItemForm(processName);
    waitForElementDisplayed(By.cssSelector(String.format("[id$='%s']",
        processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-menu"))), true);
    waitForElementClickableThenClick(String.format("[id$='%s']",
        processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:delete-process")));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), true);
    waitForElementClickableThenClick("[id$='delete-process-workflow-form:remove-process-command']");
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:remove-process-workflow-dialog']"), false);
  }

  public SelenideElement getProcessItemForm(String processName) {
    SelenideElement processItemElement = null;
    ElementsCollection processItems = $$(".js-process-start-list-item form");
    for (SelenideElement process : processItems) {
      processItemElement = process.$(".js-process-start-list-item-name");
      if (processItemElement.getText().equalsIgnoreCase(processName)) {
        processItemElement = process;
        break;
      }
    }
    return processItemElement;
  }

  public void clickMoreButtonOfGridProcess(String processName) {
    var processItem = getProcessItemForm(processName);
    var actionButtonID =
        processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-button");
    waitForElementClickableThenClick(String.format("[id$='%s']", actionButtonID));
    waitForElementDisplayed(By.cssSelector(String.format("[id$='%s']",
        processItem.getAttribute(ID_PROPERTY).concat(":grid-process-action-component:process-action-menu"))), true);
  }

  public void startProcess(String processName) {
    getProcess(processName).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickMoreButtonOfFirstGridProcess() {
    waitForElementDisplayed(By.cssSelector(String.format(GRID_ACTION_BUTTON, 0, 0)), true);
    waitForElementClickableThenClick(String.format(GRID_ACTION_BUTTON, 0, 0));
  }

  public boolean isExpandedMode() {
    loadLiveSearchTextField();
    return liveSearchTextField.isDisplayed();
  }

  private void loadLiveSearchTextField() {
    liveSearchTextField = findElementById(processWidgetId + ":process-search:non-ajax-keyword-filter");
  }

}
