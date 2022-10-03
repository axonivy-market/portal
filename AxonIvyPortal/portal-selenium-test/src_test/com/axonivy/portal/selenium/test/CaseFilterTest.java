package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
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

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testFilterCasesByCreatedDate() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    caseWidgetPage.filterCasesByCreatedDate(fromInputText, EMPTY);
    caseWidgetPage.countCases().shouldHave(sizeGreaterThanOrEqual(1));
  }
  
  @Test
  public void testFilterCasesByApplication() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.openCaseList();
    CaseWidgetPage caseWidgetPage = new CaseWidgetPage();
    int before = caseWidgetPage.countCases().size();
    caseWidgetPage.openAdvancedFilter("Application", "application");
    caseWidgetPage.filterFirstApp();
    int after = caseWidgetPage.countCases().size();
    Assertions.assertEquals(before, after);
  }

}
