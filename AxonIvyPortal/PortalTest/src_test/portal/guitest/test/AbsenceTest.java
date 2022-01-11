package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static portal.guitest.common.Variable.HIDE_YEAR;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;
import portal.guitest.common.BaseTest;
import portal.guitest.common.DateTimePattern;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AbsencePage;
import portal.guitest.page.HomePage;
import portal.guitest.page.NewAbsencePage;
import portal.guitest.page.UserProfilePage;

public class AbsenceTest extends BaseTest {
  private static final LocalDate TODAY = LocalDate.now();
  private static final LocalDate YESTERDAY = TODAY.minusDays(1);
  private static final LocalDate TOMORROW = TODAY.plusDays(1);

  @Override
  @Before
  public void setup() {
    super.setupWithAlternativeLinkAndAccount(cleanUpAbsencesAndSubstituesLink, TestAccount.DEMO_USER);
    updatePortalSetting(HIDE_YEAR.getKey(), "false");
  }

  private UserProfilePage changeDateFormat() {
    HomePage homePage = new HomePage();
    UserProfilePage profilePage = homePage.openMyProfilePage();
    profilePage.changeDateFormatToPattern(DateTimePattern.DATE_PATTERN);
    profilePage = profilePage.save();
    return profilePage;
  }

  @Test
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    UserProfilePage profile = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(profile);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    assertEquals(1, absencePage.countAbsences());
    absencePage.showAbsencesInThePast(true);
    assertEquals(2, absencePage.countAbsences());
  }

  @Test
  public void whenLoginAsAdminUserThenManageAbsencesOfAllUsers() {
    login(TestAccount.ADMIN_USER);
    UserProfilePage profile = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(profile);
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
    UserProfilePage profile = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(profile);
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
    List<String> personalTaskDuringAbsenceDeputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName(), TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(personalTaskDuringAbsenceDeputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    List<String> personalTaskPermanentDeputyNames = Arrays.asList(TestAccount.ADMIN_USER.getFullName(), TestAccount.HR_ROLE_USER.getFullName());
    absencePage.setDeputy(personalTaskPermanentDeputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();
    assertEquals(joinDeputyNames(personalTaskDuringAbsenceDeputyNames), absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)));
    assertEquals(joinDeputyNames(personalTaskPermanentDeputyNames), absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_PERMANENT)));
  }

  @Test
  public void testDeputyAsAdminUser() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> personalTaskDuringAbsenceDeputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName(), TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(personalTaskDuringAbsenceDeputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    List<String> personalTaskPermanentDeputyNames = Arrays.asList(TestAccount.ADMIN_USER.getFullName(), TestAccount.HR_ROLE_USER.getFullName());
    absencePage.setDeputy(personalTaskPermanentDeputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals(joinDeputyNames(personalTaskDuringAbsenceDeputyNames), absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)));
    assertEquals(joinDeputyNames(personalTaskPermanentDeputyNames), absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_PERMANENT)));
  }

  private String joinDeputyNames(List<String> deputyNames) {
    return String.join(", ", deputyNames);
  }

  @Test
  public void testAddDeputyInPermanentToDuringAbsence() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> deputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE, false);
    assertTrue(absencePage.getChooseDeputyDialogError().startsWith("Deputy is already selected in"));
  }

  @Test
  public void testAddDeputyInDuringAbsenceToPermanent() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> deputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT, false);
    assertTrue(absencePage.getChooseDeputyDialogError().startsWith("Deputy is already selected in"));
  }

  @Test
  public void testIAmDeputyFor() {
    login(TestAccount.ADMIN_USER);
    UserProfilePage profile = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(profile);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "For Family", absencePage);

    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName()), 0);
    absencePage.saveSubstitute();
    login(TestAccount.DEMO_USER);
    absencePage = openAbsencePage(profile);
    assertTrue(absencePage.getIAMDeputyFor().contains(TestAccount.ADMIN_USER.getFullName()));
  }

  private AbsencePage openAbsencePage(UserProfilePage userProfilePage) {
    return userProfilePage.openAbsencePage();
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

  @Test
  public void testReadOwnAbsenceOnly() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);

    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);

    login(TestAccount.GUEST_USER);
    absencePage = openAbsencePage();
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
  }

  @Test
  public void testReadAbsencesOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
  }

  @Test
  public void testDeleteAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
    assertTrue(absencePage.canDeleteAbsence(0));
  }

  @Test
  public void testEditAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.showAbsencesInThePast(true);
    assertEquals(1, absencePage.countAbsences());
    assertTrue(absencePage.canEditAbsence(0));
  }

  @Test
  public void testReadOnlyDeputyOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    List<String> deputyNames = Arrays.asList(TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals(TestAccount.GUEST_USER.getFullName(), absencePage.getMyDisabledDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)));
  }

  @Test
  public void testSelectDeputyOfOtherUser() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateSubstitutePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> deputyNames = Arrays.asList(TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.saveSubstitute();
    absencePage.waitForAbsencesGrowlMessageDisplay();
    assertEquals(TestAccount.GUEST_USER.getFullName(), absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)));
  }

  @Test
  public void testReadOwnDeputy() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyReadSubstitutesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    assertTrue(absencePage.isDeputySettingSectionDisplayed());

    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertFalse(absencePage.isDeputySettingSectionDisplayed());
  }
}
