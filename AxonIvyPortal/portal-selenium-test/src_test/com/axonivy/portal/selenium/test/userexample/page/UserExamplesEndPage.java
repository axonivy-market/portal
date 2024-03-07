package com.axonivy.portal.selenium.test.userexample.page;

import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.TemplatePage;

public class UserExamplesEndPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='iFrame']";
  }

  public CaseDetailsPage goToCaseDetail() {
    switchToIFrameOfTask();
    waitForElementClickableThenClick("[id='form:go-to-case-detail']");
    switchToDefaultContent();
    waitPageDisappear();
    return new CaseDetailsPage();
  }

}
