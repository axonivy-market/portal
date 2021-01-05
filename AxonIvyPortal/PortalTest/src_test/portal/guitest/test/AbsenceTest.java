package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewAbsencePage;

public class AbsenceTest extends BaseTest {
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);
  private static final LocalDate TOMORROW = TODAY.plusDays(1);

  @Override
  @Before
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanUpAbsencesAndSubstituesLink, TestAccount.DEMO_USER);
  }

  @Test
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    assertEquals(1, absencePage.countAbsences());
    absencePage.showAbsencesInThePast(true);
    assertEquals(2, absencePage.countAbsences());
  }


  @Test
  public void whenLoginAsAdminUserThenManageAbsencesOfAllUsers() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    String demoFullName = TestAccount.DEMO_USER.getFullName();
    createAbsence(demoFullName, YESTERDAY, YESTERDAY, "For travel of another user", absencePage);
    createAbsence(demoFullName, TODAY, TODAY, "For party of another user", absencePage);
    assertEquals(1, absencePage.countAbsences());
  }

  @Test
  public void displayMessageWhenInputOverlappingAbsence() {
    LocalDate chosenDay = LocalDate.now();
    LocalDate theNextDayOfChosenDay = chosenDay.plusDays(1);
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(chosenDay, theNextDayOfChosenDay, "Just day off", absencePage);
    assertEquals(1, absencePage.countAbsences());

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(chosenDay, theNextDayOfChosenDay, "Overlapping absence");
    newAbsencePage.proceed();

    assertTrue(newAbsencePage.isErrorMessageDisplayed());
    assertEquals("The absence is overlapping with another absence.", newAbsencePage.getErrorMessage());
  }

  @Test
  public void testDeputyAsNormalUser() {
    AbsencePage absencePage = openAbsencePage();
    absencePage.setDeputy("caseOwnerUser");
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();
    assertEquals("caseOwnerUser", absencePage.getMyDeputy());
  }

  @Test
  public void testDeputyAsAdminUser() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.setDeputy("caseOwnerUser");
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals("caseOwnerUser", absencePage.getMyDeputy());
  }

  @Test
  public void testIAmDeputyFor() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "For Family", absencePage);

    absencePage.setDeputy(TestAccount.DEMO_USER.getFullName());
    absencePage.saveSubstitute();
    login(TestAccount.DEMO_USER);
    absencePage = openAbsencePage();
    assertTrue(absencePage.getIAMDeputyFor().contains(TestAccount.ADMIN_USER.getFullName()));
  }

  private AbsencePage openAbsencePage() {
    return new HomePage().openAbsencePage();
  }

  private void createAbsenceForCurrentUser(LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    createAbsence("", from, till, comment, absencePage);
  }

  private void createAbsence(String fullname, LocalDate from, LocalDate till, String comment, AbsencePage absencePage) {
    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(fullname, from, till, comment);
    newAbsencePage.proceed();
  }

}
