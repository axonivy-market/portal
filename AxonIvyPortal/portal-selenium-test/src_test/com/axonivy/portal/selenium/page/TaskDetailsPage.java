package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskDetailsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public void addNote(String noteContent) {
    $("a[id$=':task-notes:add-note-command']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("a[id$=':task-notes:add-note-command']").click();
    $("div[id$=':task-notes:add-new-note-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("div[id$=':task-notes:add-new-note-dialog']").find("textarea").sendKeys(noteContent);
    $("button[id$=':task-notes:task-add-new-note-form:save-add-note-command']").click();
    $("div[id$=':task-notes:task-add-new-note-form:save-add-note-command']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getNotesWithContent(String content) {
    $("span[id$=':task-notes:task-history-content-container']").waitUntil(appear, DEFAULT_TIMEOUT);
    return $$("span[id$=':task-notes:task-history-content-container'] table tbody tr td a").filter(text(content));
  }

  public void gotoTechnicalCase() {
    $("a[id$=':general-information:related-technical-case']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-technical-case']").click();
    $("div[id$=':general-information:business-case-information']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void gotoBusinessCase() {
    $("a[id$=':general-information:related-case']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-case']").click();
    $("div[id$=':general-information:business-case-information']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getBreadcrumbLastDisplayedItem() {
    return $$("span.ui-menuitem-text").last().waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getInformationPanel() {
    return $("div[id$='task-details-information-panel'").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void openActionPanel() {
    $("[id$=':additional-options:task-detail-more-step']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  private void openTriggerEscalationDialog() {
    $("a[id$='\\:task-trigger-escalation-command']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").waitUntil(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='\\:confirm-escalation']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  public SelenideElement getPriorityOfTask() {
    return $("span[id$='task-priority']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='priority']").closest("span");
  }
  
  public SelenideElement getStateOfTask() {
    return $("[id$=':general-information:task-detail-state']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='task-state']").closest("span");
  }
  
  public void back() {
    $("[id$=':task-detail-title-form:back-to-previous-page']").waitUntil(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
}
