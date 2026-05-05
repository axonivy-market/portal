package com.axonivy.portal.selenium.test;

import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.axonivy.portal.selenium.common.Variable.HIDE_YEAR;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewAbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;


@IvyWebTest
public class AbsenceTest extends BaseTest {
  private static final String PERMANENT_SUBSTITUTE_TYPE = "Permanent substitutes for personally assigned tasks";
  private static final String DURING_ABSENCE_SUBSTITUTE_TYPE = "Substitutes for personally assigned tasks during the absence";
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);
  private static final LocalDate TOMORROW = TODAY.plusDays(1);

  @Override
  @BeforeEach
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanUpAbsencesAndSubstituesLink, TestAccount.DEMO_USER);
    updatePortalSetting(HIDE_YEAR.getKey(), "false");
    updatePortalSetting(GLOBAL_FOOTER_INFO.getKey(), "");
    createTestingTasks();
  }

  @Test
  public void testAddAbsenceWithSubstitutesAndPermanent() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(TODAY, TODAY, "For party");
    newAbsencePage.addDeputy(TestAccount.GUEST_USER.getFullName());
    newAbsencePage.setDeputyAsPermanent(0);
    newAbsencePage.proceed();

    assertEquals(1, absencePage.getAbsenceRowCount());
    assertTrue(absencePage.getAbsenceSubstitutesText(0).contains(TestAccount.GUEST_USER.getFullName()));
    assertTrue(absencePage.hasPermanentSubstituteIcon(0));

    absencePage.openSubstitutesTab();
    assertTrue(absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_PERMANENT)
        .contains(TestAccount.GUEST_USER.getFullName()));
  }

  @Test
  public void testEditAbsenceChangeDateAndSubstitutes() {
    grantNormalUserAbsencePermissions();
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
    AbsencePage absencePage = openAbsencePage();

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(TODAY, TODAY, "Initial");
    newAbsencePage.addDeputy(TestAccount.GUEST_USER.getFullName());
    newAbsencePage.proceed();

    assertEquals(1, absencePage.getAbsenceRowCount());
    String initialPeriod = absencePage.getAbsencePeriodText(0);

    NewAbsencePage editAbsencePage = absencePage.openEditAbsenceDialog(0);
    editAbsencePage.updateDates(TOMORROW, TOMORROW);
    editAbsencePage.removeDeputy(0);
    editAbsencePage.addDeputy(TestAccount.ADMIN_USER.getFullName());
    editAbsencePage.proceed();

    absencePage.waitForAbsencePeriodToChange(0, initialPeriod);
    assertTrue(!absencePage.getAbsencePeriodText(0).equals(initialPeriod));
    assertTrue(absencePage.getAbsenceSubstitutesText(0).contains(TestAccount.ADMIN_USER.getFullName()));
  }

  @Test
  public void testRemoveAbsence() {
    grantNormalUserAbsencePermissions();
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
    AbsencePage absencePage = openAbsencePage();

    createAbsenceForCurrentUser(TODAY, TODAY, "To remove", absencePage);
    assertEquals(1, absencePage.getAbsenceRowCount());
    absencePage.deleteAbsence(0);
    redirectToNewDashBoard();
    openAbsencePage();
    assertTrue(absencePage.isEmptyMessageAvailable());
  }

  @Test
  public void testAddPastAbsenceAndToggle() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();

    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    absencePage.waitForAbsencesGrowlMessageHide();
    assertEquals(1, absencePage.getAbsenceRowCount());
    absencePage.showAbsencesInThePast(true);
    absencePage.waitForAbsenceTableChange(2);
    assertEquals(2, absencePage.getAbsenceRowCount());
  }

  @Test
  public void testAdminCanViewOtherUserAbsences() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);

    login(TestAccount.ADMIN_USER);
    grantAdminAbsencePermissions();
    AbsencePage adminAbsencePage = openAbsencePage();
    adminAbsencePage.setSelectedUser(TestAccount.DEMO_USER.getFullName());
    assertEquals(1, adminAbsencePage.getAbsenceRowCount());
  }

  @Test
  public void testAdminCanAddAbsenceForOtherUser() {
    login(TestAccount.ADMIN_USER);
    grantAdminAbsencePermissions();

    AbsencePage absencePage = openAbsencePage();
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(TestAccount.DEMO_USER.getFullName(), TODAY, TODAY, "Admin adds");
    newAbsencePage.proceed();
    absencePage.openAbsencesTab();
    absencePage.setSelectedUser(TestAccount.DEMO_USER.getFullName());
    assertEquals(1, absencePage.getAbsenceRowCount());
  }

  @Test
  public void testAdminCanEditOtherUserAbsence() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    absencePage.waitForAbsencesGrowlMessageHide();
    login(TestAccount.ADMIN_USER);
    grantAdminAbsencePermissions();
    AbsencePage adminAbsencePage = openAbsencePage();
    adminAbsencePage.setSelectedUser(TestAccount.DEMO_USER.getFullName());
    assertEquals(1, adminAbsencePage.getAbsenceRowCount());
    NewAbsencePage editAbsencePage = adminAbsencePage.openEditAbsenceDialog(0);
    editAbsencePage.updateDates(YESTERDAY, YESTERDAY);
    editAbsencePage.proceed();
    absencePage.waitForAbsencesGrowlMessageHide();
    assertTrue(absencePage.isEmptyMessageAvailable());
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, adminAbsencePage.getAbsenceRowCount());
  }

  @Test
  public void testShowErrorWhenNewAbsenceOverlaps() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "Existing", absencePage);
    assertEquals(1, absencePage.getAbsenceRowCount());

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(TODAY, TODAY, "Overlapping");
    newAbsencePage.proceed();

    assertTrue(newAbsencePage.isAbsenceErrorMessageDisplayed());
  }

  @Test
  public void testShowErrorWhenAddingAlreadySelectedSubstituteInAbsenceDialog() {
    grantNormalUserAbsencePermissions();
    AbsencePage absencePage = openAbsencePage();

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(TODAY, TODAY, "Test");
    newAbsencePage.addDeputy(TestAccount.GUEST_USER.getFullName());
    newAbsencePage.addDeputy(TestAccount.GUEST_USER.getFullName());

    assertTrue(newAbsencePage.isAbsenceErrorMessageDisplayed());
  }

  @Test
  public void testShowErrorWhenAddingAlreadyAddedSubstituteInSubstituteDialog() {
    grantNormalUserAbsencePermissions();
    grantNormalUserSubstitutePermissions();
    AbsencePage absencePage = openAbsencePage();
    absencePage.openSubstitutesTab();

    absencePage.addSubstitute(DURING_ABSENCE_SUBSTITUTE_TYPE, Arrays.asList(TestAccount.GUEST_USER.getFullName()));

    absencePage.openEditDeputiesDialog(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.addDeputyInChooseDialog(TestAccount.GUEST_USER.getFullName());

    assertTrue(absencePage.isSubstituteErrorMessageDisplayed());
  }

  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    createAbsence("", from, till, comment, absencePage);
  }

  @Test
  public void testAddEditRemoveSubstitutesAsDemoUser() {
    grantNormalUserAbsencePermissions();
    grantNormalUserSubstitutePermissions();
    AbsencePage absencePage = openAbsencePage();
    absencePage.openSubstitutesTab();

    absencePage.addSubstitute(PERMANENT_SUBSTITUTE_TYPE, Arrays.asList(TestAccount.GUEST_USER.getFullName()));
    absencePage.addSubstitute(DURING_ABSENCE_SUBSTITUTE_TYPE, Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName()));

    assertTrue(absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_PERMANENT)
        .contains(TestAccount.GUEST_USER.getFullName()));
    assertTrue(absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)
        .contains(TestAccount.CASE_OWNER_USER.getFullName()));

    absencePage.openEditDeputiesDialog(DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.removeDeputyFromChooseDialog(0);
    absencePage.addDeputyInChooseDialog(TestAccount.ADMIN_USER.getFullName());
    absencePage.saveSelectedDeputies();

    assertTrue(absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_PERMANENT)
        .contains(TestAccount.ADMIN_USER.getFullName()));

    absencePage.openEditDeputiesDialog(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.removeDeputyFromChooseDialog(0);
    absencePage.saveSelectedDeputies();

    assertTrue(!absencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)
        .contains(TestAccount.CASE_OWNER_USER.getFullName()));
  }

  @Test
  public void testAdminSeesOtherUserSubstitutes() {
    grantNormalUserAbsencePermissions();
    grantNormalUserSubstitutePermissions();
    AbsencePage absencePage = openAbsencePage();
    absencePage.openSubstitutesTab();
    absencePage.addSubstitute(DURING_ABSENCE_SUBSTITUTE_TYPE, Arrays.asList(TestAccount.GUEST_USER.getFullName()));
    absencePage.waitForSubstituteRowCountToChange(1);
    login(TestAccount.ADMIN_USER);
    grantAdminAbsencePermissions();
    AbsencePage adminAbsencePage = openAbsencePage();
    adminAbsencePage.openSubstitutesTab();
    adminAbsencePage.setSubstituteUserByAdmin(TestAccount.DEMO_USER.getFullName());
    adminAbsencePage.waitForSubstituteRowCountToChange(1);
    assertEquals(TestAccount.DEMO_USER.getFullName(), adminAbsencePage.getSelectedSubstituteUser());
    adminAbsencePage.openAbsencesTab();
    adminAbsencePage.openSubstitutesTab();
    String addedSubstitutes = adminAbsencePage.getDeputiesByRoleType(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    assertTrue(addedSubstitutes.contains(TestAccount.GUEST_USER.getFullName()));
  }

  private void createAbsence(String fullname, LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(fullname, from, till, comment);
    newAbsencePage.proceed();
  }

  private AbsencePage openAbsencePage() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    return newDashboardPage.openAbsencePage();
  }

  private void grantNormalUserAbsencePermissions() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
  }

  private void grantNormalUserSubstitutePermissions() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
  }

  private void grantAdminAbsencePermissions() {
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateSubstitutePermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
  }
}
