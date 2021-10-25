package com.axonivy.portal.selenium.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;

@IvyWebTest
public class CaseFilterTest extends BaseTest {

  private static final String EMPTY = "";
  private CaseWidgetPage caseWidgetPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    caseWidgetPage = new CaseWidgetPage();
  }

  @Test
  public void testFilterCasesByCreatedDate() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    caseWidgetPage.filterCasesByCreatedDate(fromInputText, EMPTY);
    caseWidgetPage.countCases().shouldHaveSize(0);
  }

}
