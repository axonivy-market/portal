package portal.test;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.AbsencePage;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.NewAbsencePage;
import portal.page.SettingDeputyPage;

public class AbsenceTest extends BaseTest {
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);

  @Before
  public void setup() {
    super.setup();
    navigateToUrl("portalKitTestHelper/1511A66AF619A768/cleanAbsences.ivp");
    navigateToUrl("portalKitTestHelper/14DE09882B540AD5/grantPermissionsToAdmin.ivp");

    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    new LoginPage(TestAccount.DEMO_USER).login();
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
    new LoginPage(TestAccount.ADMIN_USER).login();
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For party");
    createAbsence(TestAccount.DEMO_USER.getUsername(), YESTERDAY, YESTERDAY, "For travel of another user");
    createAbsence(TestAccount.DEMO_USER.getUsername(), TODAY, TODAY, "For party of another user");
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
    new LoginPage(TestAccount.DEMO_USER).login();
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(chosenDay, theNextDayOfChosenDay, "Just day off");
    assertEquals(1, absencePage.countAbsences());

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(chosenDay, theNextDayOfChosenDay, "Overlapping absence");
    newAbsencePage.proceed();

    assertTrue(newAbsencePage.isErrorMessageDisplayed());
    assertEquals("The editing absence is overlapping with an other absence", newAbsencePage.getErrorMessage());

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
    SettingDeputyPage settingDeputyPage = newAbsencePage.proceed();
    settingDeputyPage.proceedWhenCreatingAbsence();
  }

}
