package com.axonivy.portal.selenium.a11y;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;


@IvyWebTest(headless = false)
@ExtendWith({ A11yExtension.class })
public class AbsenceA11yTest {

  @Test
  public void absencePage_shouldHaveNoViolations() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    newDashboardPage.openAbsencePage();
  }

  @Test
  public void absencePage_newAbsenceDialog_shouldHaveNoViolations() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    AbsencePage absencePage = newDashboardPage.openAbsencePage();
    absencePage.openNewAbsenceDialog();
  }

@Test
  public void absencePage_substitutes_shouldHaveNoViolations() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    AbsencePage adminAbsencePage = newDashboardPage.openAbsencePage();
    adminAbsencePage.openSubstitutesTab();
  }
}
