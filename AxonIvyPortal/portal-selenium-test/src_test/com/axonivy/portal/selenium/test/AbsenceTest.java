package com.axonivy.portal.selenium.test;

import static com.axonivy.portal.selenium.common.Variable.GLOBAL_FOOTER_INFO;
import static com.axonivy.portal.selenium.common.Variable.HIDE_YEAR;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AbsencePage;
import com.axonivy.portal.selenium.page.NewAbsencePage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TemplatePage;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;


@IvyWebTest
public class AbsenceTest extends BaseTest {
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
  public void whenLoginAsNormalUserThenManageAbsencesOfThatUser() {
    NewDashboardPage newDashboardPage = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(newDashboardPage);
    createAbsenceForCurrentUser(TODAY, TODAY, "For party", absencePage);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    absencePage.countAbsences(1);
    absencePage.showAbsencesInThePast(true);
    absencePage.countAbsences(2);
  }

  @Test
  public void whenLoginAsAdminUserThenManageAbsencesOfAllUsers() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    NewDashboardPage newDashboardPage = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(newDashboardPage);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "For party", absencePage);
    String demoFullName = TestAccount.DEMO_USER.getFullName();
    createAbsence(demoFullName, TODAY, TODAY, "For party of another user", absencePage);
    createAbsence(demoFullName, YESTERDAY, YESTERDAY, "For travel of another user", absencePage);
    absencePage.countAbsences(1);
  }

  @Test
  public void displayMessageWhenInputOverlappingAbsence() {
    login(TestAccount.ADMIN_USER);
    LocalDate chosenDay = LocalDate.now();
    LocalDate theNextDayOfChosenDay = chosenDay.plusDays(1);
    AbsencePage absencePage = openAbsencePage(new NewDashboardPage());
    createAbsenceForCurrentUser(chosenDay, theNextDayOfChosenDay, "Just day off", absencePage);
    absencePage.countAbsences(1);

    NewAbsencePage newAbsencePage = absencePage.openNewAbsenceDialog();
    newAbsencePage.input(chosenDay, theNextDayOfChosenDay, "Overlapping absence");
    newAbsencePage.proceed();

    assertEquals(newAbsencePage.isErrorMessageDisplayed(), true);
    assertEquals("The absence is overlapping with another absence.", newAbsencePage.getErrorMessage());
  }

  @Test
  public void testDeputyAsNormalUser() {
    AbsencePage absencePage = openAbsencePage();
    List<String> personalTaskDuringAbsenceDeputyNames =
        Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName(), TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(personalTaskDuringAbsenceDeputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    List<String> personalTaskPermanentDeputyNames =
        Arrays.asList(TestAccount.ADMIN_USER.getFullName(), TestAccount.HR_ROLE_USER.getFullName());
    absencePage.setDeputy(personalTaskPermanentDeputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.saveSubstitute();
    absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE))
        .shouldBe(Condition.text(joinDeputyNames(personalTaskDuringAbsenceDeputyNames)));
    absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_PERMANENT))
        .shouldBe(Condition.text(joinDeputyNames(personalTaskPermanentDeputyNames)));
  }

  @Test
  public void testAddDeputyInPermanentToDuringAbsence() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateSubstitutePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> deputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT);
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE, false);
    assertEquals(absencePage.getChooseDeputyDialogError().startsWith("Substitute is already selected in"), true);
  }

  @Test
  public void testAddDeputyInDuringAbsenceToPermanent() {
    login(TestAccount.ADMIN_USER);
    AbsencePage absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    List<String> deputyNames = Arrays.asList(TestAccount.CASE_OWNER_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_PERMANENT, false);
    assertEquals(absencePage.getChooseDeputyDialogError().startsWith("Substitute is already selected in"), true);
  }

  @Test
  public void testIAmDeputyFor() {
    login(TestAccount.ADMIN_USER);
    NewDashboardPage newDashboardPage = changeDateFormat();
    AbsencePage absencePage = openAbsencePage(newDashboardPage);
    createAbsenceForCurrentUser(TOMORROW, TOMORROW, "For Family", absencePage);

    absencePage.setDeputy(Arrays.asList(TestAccount.DEMO_USER.getFullName()), 0);
    absencePage.saveSubstitute();
    login(TestAccount.DEMO_USER);
    absencePage = openAbsencePage(new NewDashboardPage());
    assertEquals(absencePage.getIAMDeputyFor().contains(TestAccount.ADMIN_USER.getFullName()), true);
  }

  private String joinDeputyNames(List<String> deputyNames) {
    return String.join(", ", deputyNames);
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
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForGrowlMessageDisappear();
    return newDashboardPage.openAbsencePage();
  }

  @Test
  public void testReadOwnAbsenceOnly() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
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
    absencePage.countAbsences(1);
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
    absencePage.countAbsences(1);
  }

  @Test
  public void testReadOnlyDeputyOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    List<String> deputyNames = Arrays.asList(TestAccount.GUEST_USER.getFullName());
    absencePage.setDeputy(deputyNames, DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE);
    absencePage.saveSubstitute();

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateOwnSubstitutePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    assertEquals(TestAccount.GUEST_USER.getFullName(),
        absencePage.getMyDisabledDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE)));
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
    absencePage.getMyDeputy(absencePage.indexOfDeputyRole(DeputyRoleType.PERSONAL_TASK_DURING_ABSENCE))
        .shouldBe(Condition.text(TestAccount.GUEST_USER.getFullName()));
  }

  @Test
  public void testDeleteAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For other reason", absencePage);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    absencePage.countAbsences(1);

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDeleteAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.showAbsencesInThePast(true);
    absencePage.countAbsences(2);
    absencePage.canDeleteAbsence(0);
    absencePage.canDeleteAbsence(1);
  }

  @Test
  public void testEditAbsenceOfOtherUser() {
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadOwnAbsencesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    createAbsenceForCurrentUser(TODAY, TODAY, "For other reason", absencePage);
    createAbsenceForCurrentUser(YESTERDAY, YESTERDAY, "For travel", absencePage);
    absencePage.countAbsences(1);

    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantReadAbsencesPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantCreateAbsencePermission.ivp");
    absencePage = openAbsencePage();
    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.showAbsencesInThePast(true);
    absencePage.countAbsences(2);
    absencePage.canEditAbsence(0);
    absencePage.canEditAbsence(1);
  }

  @Test
  public void testReadOwnDeputy() {
    login(TestAccount.GUEST_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/denyReadSubstitutesPermission.ivp");
    AbsencePage absencePage = openAbsencePage();
    absencePage.isDeputySettingSectionDisplayed(true);

    absencePage.setSubstitutedByAdmin(TestAccount.DEMO_USER.getFullName());
    absencePage.isDeputySettingSectionDisplayed(false);
  }
}
