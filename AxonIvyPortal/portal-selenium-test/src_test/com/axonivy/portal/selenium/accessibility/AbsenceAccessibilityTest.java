package com.axonivy.portal.selenium.accessibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.AccessibilityHelpers;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class AbsenceAccessibilityTest extends BaseTest{

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void absencePage() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    newDashboardPage.openAbsencePage();
    AccessibilityHelpers.makeA11yReport();
  }

  @Test
  public void absencePage_newAbsenceDialog() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    AbsencePage absencePage = newDashboardPage.openAbsencePage();
    absencePage.openNewAbsenceDialog();
    AccessibilityHelpers.makeA11yReport();
  }

@Test
  public void absencePage_substitutes() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    AbsencePage adminAbsencePage = newDashboardPage.openAbsencePage();
    adminAbsencePage.openSubstitutesTab();
    AccessibilityHelpers.makeA11yReport();
  }
}
