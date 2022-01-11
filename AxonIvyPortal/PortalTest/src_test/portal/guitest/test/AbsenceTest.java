package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.AddAbsencePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewAbsencePage;
import portal.guitest.page.SettingDeputyPage;

public class AbsenceTest extends BaseTest {
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink("portalKitTestHelper/1511A66AF619A768/cleanAbsences.ivp");

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel");
    createAbsenceForCurrentUser(TODAY, TODAY, "For party");
    assertEquals(1, absencePage.countAbsences());
    absencePage.showAbsencesInThePast(true);
    assertEquals(2, absencePage.countAbsences());
    SettingDeputyPage deputySettingPage = absencePage.openDeputyDialog();
    assertFalse(deputySettingPage.isSubstitutedUserInputElementDisplayed());
    deputySettingPage.proceedWhenSettingDeputy();
    assertEquals(2, absencePage.countAbsences());
  }

  @Test
  public void whenLoginAsAdminUserThenManageAbsencesOfAllUsers() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For party");
    String demoUserName = TestAccount.DEMO_USER.getFullName();
    createAbsence(demoUserName, YESTERDAY, YESTERDAY, "For travel of another user");
    createAbsence(demoUserName, TODAY, TODAY, "For party of another user");
    assertEquals(2, absencePage.countAbsences());
    absencePage.showAbsencesInThePast(true);
    assertEquals(3, absencePage.countAbsences());
    SettingDeputyPage deputySettingPage = absencePage.openDeputyDialog();
    assertTrue(deputySettingPage.isSubstitutedUserInputElementDisplayed());
    deputySettingPage.proceedWhenSettingDeputy();
    assertEquals(3, absencePage.countAbsences());

  }

  @Test
  public void displayMessageWhenInputOverlappingAbsence() {
    LocalDate chosenDay = LocalDate.now();
    LocalDate theNextDayOfChosenDay = chosenDay.plusDays(1);
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(chosenDay, theNextDayOfChosenDay, "Just day off");
    assertEquals(1, absencePage.countAbsences());

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(chosenDay, theNextDayOfChosenDay, "Overlapping absence");
    newAbsencePage.proceed();

    assertTrue(newAbsencePage.isErrorMessageDisplayed());
    assertEquals("The absence is overlapping with another absence.", newAbsencePage.getErrorMessage());

  }

  @Test
  public void testReadOwnAbsenceOnly() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForDeputy("", YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.GUEST_USER);
    absencePage = openAbsencePage();
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
  }

  @Test
  public void testReadAbsencesOfOtherUser() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForDeputy("", YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.GUEST_USER);
    absencePage = openAbsencePage();
    absencePage.showAbsencesInThePast(true);
    assertEquals(2, absencePage.countAbsences());
  }

  @Test
  public void testDeleteAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
    assertTrue(absencePage.canDeleteAbsence(0));
  }

  @Test
  public void testEditAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "Just day off");

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
    assertTrue(absencePage.canEditAbsence(0));
  }

  @Test
  public void testReadOnlyDeputyOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    SettingDeputyPage deputySettingPage = absencePage.openDeputyDialog();
    deputySettingPage.changeDeputy(TestAccount.GUEST_USER.getFullName(), 0, 0);
    deputySettingPage.proceedWhenSettingDeputy();

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    absencePage = openAbsencePage();
    deputySettingPage = absencePage.openDeputyDialog();
    deputySettingPage.changeSubstitutedUser(TestAccount.DEMO_USER.getFullName());
    assertEquals(TestAccount.GUEST_USER.getFullName(), deputySettingPage.getMyDisabledDeputy(0, 0));
  }

  private AbsencePage openAbsencePage() {
    return new HomePage().openAbsencePage();
  }

  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment) {
    createAbsence("", from, till, comment);
  }

  private void createAbsence(String username, LocalDate from, LocalDate till, String comment) {
    AbsencePage absencePage = new AbsencePage();
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(username, from, till, comment);
    AddAbsencePage addAbsencePage = newAbsencePage.proceed();
    addAbsencePage.proceedWhenCreatingAbsence();
  }

  private void createAbsenceForDeputy(String username, LocalDate from, LocalDate till, String comment) {
    AbsencePage absencePage = new AbsencePage();
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(username, from, till, comment);
    AddAbsencePage addAbsencePage = new AddAbsencePage();
    addAbsencePage.proceedWhenCreatingAbsence();
  }
}
