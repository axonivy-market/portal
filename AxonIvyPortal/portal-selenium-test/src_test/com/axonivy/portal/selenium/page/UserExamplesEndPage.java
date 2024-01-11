package com.axonivy.portal.selenium.page;

import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.TemplatePage;

public class UserExamplesEndPage extends TemplatePage {
  @Override
  protected String getLoadedLocator() {
    return "[id$='form:go-to-case-detail']";
  }

  public CaseDetailsPage goToCaseDetail() {
    waitForElementClickableThenClick("[id$='form:go-to-case-detail']");
    return new CaseDetailsPage();
  }
}
