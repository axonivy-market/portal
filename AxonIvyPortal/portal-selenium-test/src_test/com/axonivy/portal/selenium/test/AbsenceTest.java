package com.axonivy.portal.selenium.test;

import static com.axonivy.portal.selenium.common.Variable.HIDE_YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewAbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TemplatePage;


@IvyWebTest(headless = false)
public class AbsenceTest extends BaseTest {
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);
  private static final LocalDate TOMORROW = TODAY.plusDays(1);

  @Override
  @BeforeEach
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanUpAbsencesAndSubstituesLink, TestAccount.DEMO_USER);
    updatePortalSetting(HIDE_YEAR.getKey(), "false");
  }

  @Test
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    NewDashboardPage newDashboardPage = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(newDashboardPage);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    assertEquals(1, absencePage.countAbsences());
    absencePage.showAbsencesInThePast(true);
    assertEquals(2, absencePage.countAbsences());
  }

  private NewDashboardPage changeDateFormat() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    return newDashboardPage;
  }

  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    createAbsence("", from, till, comment, absencePage);
  }

  private void createAbsence(String fullname, LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(fullname, from, till, comment);
    newAbsencePage.proceed();
  }

  private AbsencePage openAbsencePage(TemplatePage templatePage) {
    return templatePage.openAbsencePage();
  }

  private AbsencePage openAbsencePage() {
    return new NewDashboardPage().openAbsencePage();
  }

}
