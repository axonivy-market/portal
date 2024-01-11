package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.WaitHelper;
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

  @Override
  protected String getLoadedLocator() {
    return ".view-mode-text";
  }
  
  public void addExternalLink(String name, String link, String iconClass, String imageName) {
    $("a[id$=':add-external-link-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    $("a[id$=':add-external-link-command']").click();
    
    $("input[id$=':add-external-link-form:external-link-name']").sendKeys(name);
    $("input[id$=':add-external-link-form:external-link']").sendKeys(link);
    
    // change icon
    $("a[id$=':external-link-icon:change-icon-link']").shouldBe(getClickableCondition()).click();
    $("div[id='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    
    $("input[id$=':search-icon-name-field']").sendKeys(iconClass);
    
    $("a[title='"+ iconClass + "']").click();
    $("div[id$='process-widget:add-external-link-form:external-link-icon:select-icon-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    
    $("input[id$='process-widget:add-external-link-form:external-link-image-upload_input']").sendKeys(FileHelper.getAbsolutePathToTestFile(imageName));
    
    $("img[id$='process-widget:add-external-link-form:external-link-preview-image']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    
    $("button[id$='adding-new-external-link-command']").click();
    $("div[id='process-widget:add-external-link-dialog']").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    
 
  }
  
  public void findProcess(String keyword) {
    $("input[id='process-widget:process-search:non-ajax-keyword-filter']").sendKeys(keyword);
    $("div.js-external-link-process-item").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    
    System.out.println($("i[id='icon']").getAttribute("class"));
  }

  public void waitForStartListShow() {
    $(".js-loading-process-list").shouldBe(Condition.hidden, DEFAULT_TIMEOUT);
    $(".js-process-start-list-container").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void checkProcessNotExists(String keyword) {
    $("input[id='process-widget:process-search:non-ajax-keyword-filter']").sendKeys(keyword);
    $("div.js-external-link-process-item").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
  
  public void startProcessByName(String processName) {
    WaitHelper.waitForNavigation(() -> getProcessItem(processName).$("[id$=':start-button']")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }
  
  public SelenideElement getProcessItem(String processName) {
    SelenideElement processItemElement = null;
    List<SelenideElement> processItems = $$(".js-process-start-list-item");
    for (SelenideElement process : processItems) {
      processItemElement = process.$(".js-process-start-list-item-name");
      if (processItemElement.getText().equalsIgnoreCase(processName)) {
        processItemElement = process;
        break;
      }
    }
    return processItemElement;
  }

  public void waitUtilProcessWidgetDisplayed() {
    $(".js-loading-process-list").shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    $(".js-process-start-list-container").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public ExpressProcessPage openExpressPage() {
    WaitHelper.waitForNavigation(() -> {
      clickByJavaScript(
          $("[id='process-widget:create-express-workflow']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT));
    });
    return new ExpressProcessPage();
  }

  public WebElement openExternalLinkDialog() {
    $("a[id$=':add-external-link-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $("[id='process-widget:add-external-link-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public WebElement navigateToProcessIndex(String character) {
    $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT)
        .click();
    return $(".js-process-nav-item.js-process-starts-with-" + character).shouldBe(getClickableCondition(),
        DEFAULT_TIMEOUT);
  }

  public void clickMoreButtonOfFirstImageProcess() {
    $$("[id$=':process-item:image-process-action-component:process-action-button']").asFixedIterable().stream()
        .filter(WebElement::isDisplayed).filter(WebElement::isEnabled).findFirst().ifPresent(firstActionButton -> {
          clickByJavaScript(firstActionButton);
        });
  }

  public SelenideElement getProcessEditMenu(int index) {
    return $(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index)).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void clickOnProcessEditMenu(int index) {
    waitForElementClickableThenClick($(String.format(IMAGE_EDIT_PROCESS_LINK, 0, index)));
    waitForElementDisplayed(By.cssSelector("[id$='process-widget:edit-process-dialog']"), true);
  }

  public WebElement getEditProcessDialog() {
    return $("[id$='process-widget:edit-process-dialog']");
  }

  public void selectGridMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:1']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='process-widget:grid-process-container']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public void selectCompactMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:2']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='alphabet-process-index-fieldset']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getFilterTextfield() {
    return $("[id='process-widget:process-search:non-ajax-keyword-filter']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitUntilProcessDisplayed(String name) {
    $("[id='process-widget:process-list']").shouldBe(appear, DEFAULT_TIMEOUT).$$(".js-process-start-list-item-name")
        .asFixedIterable().stream().filter(WebElement::isDisplayed)
        .filter(process -> process.getText().contentEquals(name)).findFirst().get().shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickMoreInformationLinkImage(String processName) {
    SelenideElement processItem = getProcessItem(processName);
    processItem.$(By.cssSelector(".more-information-wrapper")).click();
  }

  public void selectImageMode() {
    $("[id='process-widget:process-view-mode:view-mode-selection:0']").ancestor(".ui-button")
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='process-widget:image-process-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

}
