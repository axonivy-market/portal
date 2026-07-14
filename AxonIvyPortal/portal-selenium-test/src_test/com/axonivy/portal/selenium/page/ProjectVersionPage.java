package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class ProjectVersionPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='project-version:engine-version-label']";
  }

  public boolean isEngineVersionDisplayed() {
    return $("[id='project-version:engine-version-label']").isDisplayed();
  }

  public boolean isPortalVersionDisplayed() {
    return $("[id='project-version:portal-version-label']").isDisplayed();
  }

  public boolean isFirstProjectDisplayed() {
    return $("[id='project-version:application-list:0:application-project-version-table:0:project-name']")
        .isDisplayed();
  }

  public SelenideElement getProjectVersionDialog() {
    return $("[id='project-info-dialog']");
  }
}
