package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
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

  public boolean isFirstVersionDisplayed() {
    return $("[id='project-version:application-list:0:application-version-table:0:application-version']")
        .isDisplayed();
  }

  public boolean isFirstProjectDisplayed() {
    return $("[id='project-version:application-list:0:application-project-table:0:project-name']")
        .isDisplayed();
  }

  public int getVersionRowCount() {
    return $$("[id='project-version:application-list:0:application-version-table_data'] tr").size();
  }

  public void clickVersionRow(int versionRowIndex) {
    $("[id='project-version:application-list:0:application-version-table:" + versionRowIndex + ":application-version']")
        .click();
    $("[id='project-version:application-list:0:application-version-table_data'] > tr:nth-child(" + (versionRowIndex + 1) + ")")
        .shouldHave(Condition.cssClass("project-info-row--selected"), DEFAULT_TIMEOUT);
  }

  public String getProjectsOfVersionHeading() {
    return $("[id='project-version:application-list:0:application-panel'] .project-info-card__subtitle").getText();
  }

  public SelenideElement getProjectVersionDialog() {
    return $("[id='project-info-dialog']");
  }
}
