package com.axonivy.portal.selenium.test;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

@IvyWebTest
public class DisabledUserTest extends BaseTest {

  private static final String VISIBILITY_USER_FULL_NAME = "Visibility Test User";
  private static final String DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME = "(disabled) Visibility Test User";
  private static final String DURING_ABSENCE_SUBSTITUTE_TYPE = "Substitutes for personally assigned tasks during the absence";
  private static final String DISABLE_VISIBILITY_USER_CREATION_LINK =
      "portalKitTestHelper/153CACC26D0D4C3D/disableVisibilityUser.ivp";


  @Override
  @BeforeEach
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanupDataLink, TestAccount.ADMIN_USER);
    redirectToRelativeLink(DISABLE_VISIBILITY_USER_CREATION_LINK);
  }

  @Test
  public void testAbsenceWithDisabledUser() {
    redirectToRelativeLink(cleanUpAbsencesAndSubstituesLink);
    AbsencePage absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.openSubstitutesTab();
    absencePage.setSelectedUserInSubstitutesTab(VISIBILITY_USER_FULL_NAME);
    absencePage.addSubstitute(DURING_ABSENCE_SUBSTITUTE_TYPE, Arrays.asList(TestAccount.DEMO_USER.getFullName()));
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.openSubstitutesTab();
    absencePage.setSelectedUserInSubstitutesTab(TestAccount.DEMO_USER.getFullName());
    assertEquals(DISABLED_VISIBILITY_USER_BRIEF_DISPLAY_NAME, absencePage.getSubstitutedByAdmin(0));
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    absencePage = new NewDashboardPage().openAbsencePage();
    absencePage.openAbsencesTab();
    absencePage.setSelectedUser(VISIBILITY_USER_FULL_NAME);
    absencePage.openSubstitutesTab();
    String addedSubstitutes = absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    assertTrue(addedSubstitutes.contains(TestAccount.DEMO_USER.getFullName()));
  }
}
