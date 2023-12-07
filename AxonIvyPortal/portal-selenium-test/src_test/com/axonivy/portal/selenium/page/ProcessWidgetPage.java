package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.axonivy.portal.selenium.common.FileHelper;
import com.codeborne.selenide.Condition;

public class ProcessWidgetPage extends TemplatePage {
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
}
