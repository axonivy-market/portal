package com.axonivy.portal.selenium.page;

public class TaskTemplateIFramePage extends TemplatePage{

  @Override
  protected String getLoadedLocator() {
    return "[id='task-template-title']";
  }

}
