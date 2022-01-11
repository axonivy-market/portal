package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.common.WaitHelper;
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
  public void testCurrentUserShouldNotSelectHimAsDeputy() {
    login(TestAccount.DEMO_USER);
    AbsencePage absencePage = openAbsencePage();
    String demoUserName = TestAccount.DEMO_USER.getFullName();
    // Test add new absence
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input("", TODAY, TODAY, "Just day off");
    var addAbsencePage = newAbsencePage.proceed();
    addAbsencePage.filterDeputyUser(demoUserName);
    assertDeputySuggestionResultForNormalUser(addAbsencePage.getSearchDeputyResult());
    addAbsencePage.proceedWhenCreatingAbsence();
    WaitHelper.assertTrueWithWait(() -> absencePage.countAbsences() == 1);
    // Test set deputy
    SettingDeputyPage settingDeputyPage = absencePage.openDeputyDialog();
    settingDeputyPage.filterDeputyUser(demoUserName);
    assertDeputySuggestionResultForNormalUser(settingDeputyPage.getSearchDeputyResult());
  }

  private void assertDeputySuggestionResultForNormalUser(List<String> result) {
    assertTrue("Should not show any user for the deputy", result.size() == 1);
    assertTrue("Should show only No deputy row in search box", result.get(0).contains("No deputy"));
  }

  @Test
  public void testAdminUserShouldSelectHimAsDeputyOfOtherUser() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    var adminUserName = TestAccount.ADMIN_USER.getFullName();
    var demoUserName = TestAccount.DEMO_USER.getFullName();
    // Test add new absence
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(demoUserName, TODAY, TODAY, "Just day off");
    var addAbsencePage = newAbsencePage.proceed();

    addAbsencePage.filterDeputyUser(adminUserName);
    assertDeputySuggestionResult(adminUserName, addAbsencePage.getSearchDeputyResult());
    addAbsencePage.proceedWhenCreatingAbsence();
    WaitHelper.assertTrueWithWait(() -> absencePage.countAbsences() == 1);

    // Test set deputy
    SettingDeputyPage settingDeputyPage = absencePage.openDeputyDialog();
    settingDeputyPage.selectSubstitutedUser(demoUserName);
    settingDeputyPage.filterDeputyUser(adminUserName);
    assertDeputySuggestionResult(adminUserName, settingDeputyPage.getSearchDeputyResult());
  }

  private void assertDeputySuggestionResult(String adminUserName, List<String> result) {
    assertTrue("Should see 2 options for the deputy", result.size() == 2);
    assertTrue("Should show admin user in search box", result.get(1).contains(adminUserName));
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

}
