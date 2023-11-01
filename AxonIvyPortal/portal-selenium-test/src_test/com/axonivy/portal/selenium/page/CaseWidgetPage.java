package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class CaseWidgetPage extends TemplatePage {
  
  private String caseWidgetId;
  private static final String CASE_ITEM_LIST_SELECTOR = "li[class='ui-datascroller-item']";
  private static final String CASE_NAME_CSS_SELECTOR = "span[class*='case-header-name-cell']";

  public CaseWidgetPage() {
    this("case-widget");
  }

  public CaseWidgetPage(String caseWidgetId) {
    this.caseWidgetId = caseWidgetId;
  }
  
  @Override
  protected String getLoadedLocator() {
    return ".js-case-widget-header";
  }

  public CaseDetailsPage openCase(String caseName) {
    $("div[id='case-widget:case-list']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("div[id='case-widget:case-list'] ul li div[id$=':case-item'] span.case-info-row span.case-header-name-cell")
        .filter(text(caseName)).first().click();
    return new CaseDetailsPage();
  }
  
  public void openAdvancedFilter(String filterName, String filterIdName) {
    $("a[id$='filter-add-action']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$='filter-add-action']").click();
    $("table[id$='case-widget:filter-add-form:filter-selection']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("table[id$='case-widget:filter-add-form:filter-selection'] label").filter(text(filterName)).first().click();
    $("button[id$='case-widget:filter-add-form:update-filter-selected-command']").click();
    $("div[id$='case-widget:filter-add-panel']").shouldBe(disappear, DEFAULT_TIMEOUT);
    $("span[id$='" + filterIdName + "-filter:advanced-filter-component").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterCasesByCreatedDate(String fromCreatedDate, String toCreatedDate) {
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("button[id$='created-filter:filter-open-form:advanced-filter-command']").shouldBe(getClickableCondition()).click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    $("input[id$='created-filter:filter-input-form:from-created-calendar_input'").sendKeys(fromCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:from-created-calendar_panel"));
    $("input[id$='created-filter:filter-input-form:to-created-calendar_input'").sendKeys(toCreatedDate);
    closePanelDatePicker($("div[id$='created-filter:filter-input-form:to-created-calendar_panel"));
    $("button[id$='created-filter:filter-input-form:update-command']").click();
    $("div[id$='created-filter:filter-input-form:advanced-filter-panel'").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  public void filterFirstApp() {
    $("button[id$=':application-filter:filter-open-form:advanced-filter-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(appear, DEFAULT_TIMEOUT);
    final ElementsCollection checkboxLabel = $$("span.ui-chkbox-label");
    for (int i=0; i < checkboxLabel.size(); i ++) {
      if (checkboxLabel.get(i).getText().equals("Select All")) {
        checkboxLabel.get(i).click();
      }
    }
    $("button[id$=':application-filter:filter-input-form:update-command']").click();
    $("div[id$=':application-filter:filter-input-form:advanced-filter-panel'").shouldBe(disappear, DEFAULT_TIMEOUT);
  }
  
  private void closePanelDatePicker(SelenideElement element) {
    Selenide.executeJavaScript("arguments[0].style.display = 'none'", element);
  }
  
  public ElementsCollection countCases() {
    return $$("div[id$='case-widget:case-list'] ul li");
  }

  public SelenideElement getCreatorAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public String getCaseId(int caseIndex) {
    $("[id$=':case-list']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
//    WebElement selectedCaseElement = findElementByCssSelector(String.format("[id$='case-list-scroller:%d:case-item:case-item-container']", caseIndex));
    SelenideElement selectedCaseIdElement = $(String.format("[id$='case-list-scroller:%d:case-item:case-item-container']", caseIndex)).find(By.cssSelector("[id$=':case-id-cell']"));
    return selectedCaseIdElement.getText();
  }

  public SelenideElement openActionStepMenu(int index) {
    String menuSelector = String.format("[id='case-widget:case-list-scroller:%d:case-item:case-item-action-form:action-step-component:action-steps-menu']", index);
    String menuPanelSelector = String.format("[id='case-widget:case-list-scroller:%d:case-item:case-item-action-form:action-step-component:action-steps-panel']", index);
    $(menuSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    return $(menuPanelSelector).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openAdditionalCaseDetails(SelenideElement actionMenu) {
    actionMenu.$("[id$=':show-additional-case-details-link']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public SelenideElement getSaveFilterDialog() {
    $(By.id(caseWidgetId + ":filter-save-action")).shouldBe(getClickableCondition()).click();
    $(By.id(caseWidgetId + ":filter-save-form:save-filter-set-name-input")).shouldBe(Condition.visible, DEFAULT_TIMEOUT);
    return $(By.id(caseWidgetId + ":save-filter-set-dialog")).shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public CaseDetailsPage openDetailsOfCaseHasName(String caseName) {
    List<SelenideElement> caseItems = $$(CASE_ITEM_LIST_SELECTOR);
    for (SelenideElement caseItem : caseItems) {
      System.out.println(caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText());
      if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
        caseItem.findElement(By.cssSelector("span[id*='case-info-row']")).click();
        return new CaseDetailsPage();
      }
    }
    throw new NoSuchElementException("Cannot find case has name " + caseName);
  }
  
}
