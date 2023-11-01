package com.axonivy.portal.selenium.page;

public class NoteHistoryPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='form:notes-table']";
  }
}
