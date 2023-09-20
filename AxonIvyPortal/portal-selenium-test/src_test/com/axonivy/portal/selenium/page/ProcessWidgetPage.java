package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ProcessWidgetPage extends TemplatePage {

  private static final String MORE_ACTION_BUTTON = "[id='process-widget:image-process-group-alphabet:%d:image-processes:%d:process-item:image-process-action-component:process-action-button']";
  private static final String ACTION_DIALOG = "[id='process-widget:image-process-group-alphabet:%d:image-processes:%d:process-item:image-process-action-component:process-action-menu']";
  private static final String IMAGE_EDIT_PROCESS_LINK = "[id$=':%d:image-processes:%d:process-item:image-process-action-component:edit-process']";

  @Override
  protected String getLoadedLocator() {
    return ".view-mode-text";
  }
  
  public void addExternalLink(String name, String link, String iconClass, String imageName) {
    openExternalLinkDialog();

    $("input[id$=':add-external-link-form:external-link-name']").sendKeys(name);
    $("input[id$=':add-external-link-form:external-link']").sendKeys(link);
    
    // change icon
    $("a[id$=':change-icon-link']").click();
    $("div[id='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    
    $("input[id$=':search-icon-name-field']").sendKeys(iconClass);
    
    $("a[title='"+ iconClass + "']").click();
    $("div[id$='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    
    $("input[id$='process-widget:add-external-link-form:external-link-image-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(imageName));
    
    $("img[id$='process-widget:add-external-link-form:external-link-preview-image']").shouldBe(appear, DEFAULT_TIMEOUT);
    
    $("button[id$='adding-new-external-link-command']").click();
    $("div[id='process-widget:add-external-link-dialog']").shouldBe(disappear, DEFAULT_TIMEOUT);
    
 
  }

  public WebElement openExternalLinkDialog() {
    $("a[id$=':add-external-link-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='process-widget:add-external-link-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public void findProcess(String keyword) {
    $("input[id='process-widget:process-search:non-ajax-keyword-filter']").sendKeys(keyword);
    $("div.js-external-link-process-item").shouldBe(appear, DEFAULT_TIMEOUT);
    
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
    $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
  }

  public void clickMoreButtonOfFirstImageProcess() {
    $(String.format(MORE_ACTION_BUTTON, 0, 0)).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(String.format(ACTION_DIALOG, 0, 0)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getProcessEditMenu(int index) {
    return $(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index)).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnProcessEditMenu(int index) {
    getProcessEditMenu(index).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public WebElement getEditProcessDialog() {
    return $("[id$='process-widget:edit-process-dialog']");
  }

  public void waitUtilProcessWidgetDisplayed() {
    $(".js-loading-process-list").shouldBe(disappear, DEFAULT_TIMEOUT);
    $(".js-process-start-list-container").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectImageMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:0']").ancestor(".ui-button").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='process-widget:image-process-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectGridMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:1']").ancestor(".ui-button").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='process-widget:grid-process-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void selectCompactMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:2']").ancestor(".ui-button").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='alphabet-process-index-fieldset']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitUntilProcessDisplayed(String name) {
    $("[id='process-widget:process-list']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".js-process-start-list-item-name").asFixedIterable()
      .stream().filter(WebElement::isDisplayed).filter(process -> process.getText().contentEquals(name))
      .findFirst().get().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickMoreInformationLink(String processName) {
    getProcessItem(processName).$(".process-more-info-link")
      .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getProcessItem(String processName) {
    return $$(".js-process-start-list-item").asFixedIterable().stream().filter(WebElement::isDisplayed).filter(process -> 
      process.$(".js-process-start-list-item-name").shouldBe(appear, DEFAULT_TIMEOUT).getText().contentEquals(processName)).findFirst().get();
  }

  public SelenideElement getFilterTextfield() {
    return $("[id='process-widget:process-search:non-ajax-keyword-filter']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
