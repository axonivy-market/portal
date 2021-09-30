package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;

public class TaskDetailsPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('task-detail-template:task-detail-container')";
  }

  public TaskDetailsPage() {}

  public void addNote(String noteContent) {
    $("a[id$=':task-notes:add-note-command']").click();
    $("div[id$=':task-notes:add-new-note-dialog']").waitUntil(appear, DEFAULT_TIMEOUT);
    $("div[id$=':task-notes:add-new-note-dialog']").find("textarea").sendKeys(noteContent);
    $("button[id$=':task-notes:task-add-new-note-form:save-add-note-command']").click();
    $("div[id$=':task-notes:add-new-note-dialog']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }

  public ElementsCollection getNotesWithContent(String content) {
    return $$("span[id$=':task-notes:task-history-content-container'] table tbody tr td a").filter(text(content));
  }

  public void gotoTechnicalCase() {
    $("a[id$=':general-information:related-technical-case']").click();
    $("div[id$=':general-information:business-case-information']").waitUntil(appear, DEFAULT_TIMEOUT);
  }

  public void gotoBusinessCase() {
    $("a[id$=':general-information:related-case']").click();
    $("div[id$=':general-information:business-case-information']").waitUntil(disappear, DEFAULT_TIMEOUT);
  }
}
