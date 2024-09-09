package com.axonivy.portal.selenium.test.userexample.page;

import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.TemplatePage;
import static com.codeborne.selenide.Selenide.$;

public class UserExamplesEndPage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id$='form:go-to-case-detail']";
  }

  public CaseDetailsPage goToCaseDetail() {
    $("[id$='form:go-to-case-detail']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();

    return new CaseDetailsPage();
  }

}
