package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class TaskDetailsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "#role-and-user-information";
  }

  public void addNote(String noteContent) {
    $("a[id$=':task-notes:add-note-command']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':task-notes:add-note-command']").click();
    $("div[id$=':task-notes:add-new-note-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("div[id$=':task-notes:add-new-note-dialog']").find("textarea").sendKeys(noteContent);
    $("button[id$=':task-notes:task-add-new-note-form:save-add-note-command']").click();
    $("div[id$=':task-notes:task-add-new-note-form:save-add-note-command']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getNotesWithContent(String content) {
    $("span[id$=':task-notes:task-history-content-container']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("span[id$=':task-notes:task-history-content-container'] table tbody tr td a").filter(text(content));
  }

  public CaseDetailsPage gotoTechnicalCase() {
    $("a[id$=':general-information:related-technical-case']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-technical-case']").click();
    $("div[id$=':general-information:business-case-information']").shouldBe(appear, DEFAULT_TIMEOUT);
    return new CaseDetailsPage();
  }

  public void gotoBusinessCase() {
    $("a[id$=':general-information:related-case']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("a[id$=':general-information:related-case']").click();
    $("div[id$=':general-information:business-case-information']").shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getBreadcrumbLastDisplayedItem() {
    return $$("span.ui-menuitem-text").last().shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getInformationPanel() {
    return $("div[id$='task-details-information-panel'").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void openActionPanel() {
    $("[id$=':additional-options:task-detail-more-step']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("[id$=':additional-options:side-steps-panel']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  private void openTriggerEscalationDialog() {
    $("a[id$='\\:task-trigger-escalation-command']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
    $("div[id$='\\:escalation-task-confirmation-dialog']").shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
  
  public void triggerEscalation() {
    openTriggerEscalationDialog();
    $("button[id$='\\:confirm-escalation']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }
  
  public SelenideElement getPriorityOfTask() {
    return $("span[id$='task-priority']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='priority']").closest("span");
  }
  
  public SelenideElement getStateOfTask() {
    return $("[id$=':general-information:task-detail-state']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).$("i[class*='task-state']").closest("span");
  }
  
  public void back() {
    $("[id$=':task-detail-title-form:back-to-previous-page']").shouldBe(Condition.appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition()).click();
  }

  public SelenideElement getResponsibleAvatar() {
    return $(".security-member-container > .has-avatar > .ui-avatar").shouldBe(appear, DEFAULT_TIMEOUT);
  }
  
  public SelenideElement getShareButton() {
    return $("button[id$=':share-page-button']");
  }
  
  public SelenideElement getShareDialog() {
    return $("div[id$=':share-task-details-dialog']");
  }
}
