package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

public class TaskAnalysisWidgetPage extends TemplatePage {

  private static final String TASK_FILTER_SELECTION = "task-widget:task-filter-add-form:task-filter-selection";

  @Override
  protected String getLoadedLocator() {
    return "div[id='task-widget']";
  }

  public void openAdvancedTaskFilter(String filterName, String filterIdName) {
    findTaskFilterButton().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='" + TASK_FILTER_SELECTION + "']").shouldBe(appear, DEFAULT_TIMEOUT).$$("label").asFixedIterable()
      .stream().filter(filter -> filter.getText().contentEquals(filterName))
      .findFirst().get().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='task-widget:task-filter-add-form:update-task-filter-selected-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("span[id$='" + filterIdName + "-filter:filter-open-form:advanced-filter-item-container']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void filterByTaskPriority(List<String> selectedPriorities) {
    $("[id$='priority-filter:filter-open-form:advanced-filter-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id$='priority-filter:filter-input-form:advanced-filter-panel-content']").shouldBe(appear, DEFAULT_TIMEOUT)
      .$$("label").asFixedIterable()
      .stream().filter(filter -> !selectedPriorities.contains(filter.getText()))
      .forEach(filter -> filter.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click());

    $("[id$='priority-filter:filter-input-form:update-command']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void clickApplyFilter() {
    $("button[id$='task-widget:apply-filter']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitForAjaxIndicatorDisappeared();
  }

  private SelenideElement findTaskFilterButton() {
    return $("[id='task-widget:task-filter-add-action']");
  }

  public void enterDataToSaveFilterSet(String filterSetName, boolean isPersonalFilter) {
    $("[id='task-widget:filter-save-action']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='task-widget:filter-save-form:save-filter-set-name-input']").shouldBe(appear, DEFAULT_TIMEOUT).sendKeys(filterSetName);

    String label = isPersonalFilter ? "Only me" : "All users";
    $("[id='task-widget:filter-save-form:save-filter-type-radio']").shouldBe(appear, DEFAULT_TIMEOUT)
      .$$("label").asFixedIterable().stream().filter(labelElem -> labelElem.getText().contentEquals(label)).findFirst().get().click();
  }

  public WebElement waitAndGetSavingFilterDialog() {
    return $("div[id$='save-filter-set-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }
}
