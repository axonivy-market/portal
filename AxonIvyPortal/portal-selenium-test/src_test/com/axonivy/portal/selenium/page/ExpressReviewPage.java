package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

public class ExpressReviewPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:acknowledged']";
  }

  public void finish() {
    WaitHelper.waitForNavigation(() -> $("button[id$='acknowledged']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }
  
  public void clickOnOkButton() {
    WaitHelper.waitForNavigation(() -> $("button[id$='close-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());
  }

  public String getApprovalResult() {
    $$("fieldset[id *= ':approval-result-fieldset'] .ui-icon-plusthick").asFixedIterable().forEach(elem -> {
      elem.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      elem.ancestor("fieldset").$(".ui-fieldset-content").shouldBe(appear, DEFAULT_TIMEOUT);
    });
    $$("div[id*='approval-result'] td").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT).get(0).shouldNotHave(Condition.empty, DEFAULT_TIMEOUT);
    return $$("div[id*='approval-result'] td").asFixedIterable().stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }
}
