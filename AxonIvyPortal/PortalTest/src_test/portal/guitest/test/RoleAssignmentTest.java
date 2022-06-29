package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.RoleAssignmentPage;

public class RoleAssignmentTest extends BaseTest {

  private static final String DENY_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  private static final String GRANT_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private RoleAssignmentPage roleAssignmentPage;

  @Before
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
  }

  @Test
  public void testVisibilityForRoleManagementTab() {
    accessToRoleManagement();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentPage.isDisplayed());

    // Admin lacks RoleReadAll permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleReadAll"));
    homePage = new HomePage();
    adminSettingsPage = homePage.openAdminSettings();
    assertFalse("RoleAssignment tab is displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());
  }

  @Test
  public void testVisibilityForCreatingRoleButton() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    accessToRoleManagement();
    assertTrue("Create role button is not displayed", roleAssignmentPage.isCreateNewRoleButtonPresent());
    roleAssignmentPage.openRoleCreationDialog();
    assertTrue("Role info dialog is not displayed", roleAssignmentPage.getRoleInfoDialogContent().isDisplayed());
    roleAssignmentPage.clickOnCancelLinkOfRoleDialog();

    // Admin lacks RoleCreate permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertFalse("Create role button is displayed", roleAssignmentPage.isCreateNewRoleButtonPresent());
  }

  @Test
  public void testVisibilityForDeletingRoleAction() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleAssignmentPage roleAssignmentPage = gotoRoleAssignmentAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));
    var deleteLinkClasses = roleAssignmentPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on delele New Role", deleteLinkClasses.contains("ui-state-disabled"));

    // Admin lacks RoleDelete permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentPage.isDisplayed());
    deleteLinkClasses = roleAssignmentPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertTrue("User can click on delele New Role",
        deleteLinkClasses.contains("ui-state-disabled action-column-icon-button"));

    // Admin can delete role
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentPage.isDisplayed());
    assertTrue("New Role is not appear on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));
    roleAssignmentPage.deleteRole(roleName);
    assertFalse("New Role still appears on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));
  }

  @Test
  public void testVisibilityForEditingRoleAction() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleMove"));

    var roleName = "NewRole" + UUID.randomUUID();
    roleAssignmentPage = gotoRoleAssignmentAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));

    // Admin lacks RoleCreate permission then he cannot edit role
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var editLinkClasses = roleAssignmentPage.getEditActionEnableForRole(roleName).getAttribute("class");
    assertTrue("User can click on edit New Role",
        editLinkClasses.contains("ui-state-disabled action-column-icon-button"));

    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentPage.isDisplayed());
    editLinkClasses = roleAssignmentPage.getEditActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on edit New Role", editLinkClasses.contains("ui-state-disabled"));
    roleAssignmentPage.clickOnEditRole(roleName);
    var roleDisplayName = "RoleDisplayName is edited";
    roleAssignmentPage.enterRoleAdditionalInformation(roleDisplayName, "RoleDescription is edited");
    roleAssignmentPage.clickOnSaveRoleButtonDialog();
    assertTrue("New Role is not appears on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));
    assertTrue("RoleDisplayname is not be changed",
        roleAssignmentPage.getRoleDisplayNameInRoleTreeTable().contains(roleDisplayName));
  }

  @Test
  public void testVisibilityForAssigningUsersAction() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleAssignmentPage roleAssignmentPage = gotoRoleAssignmentAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleAssignmentPage.getRoleNamesInRoleTreeTable().contains(roleName));
    var totalUsers = roleAssignmentPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    var assignUsersLinkClasses =
        roleAssignmentPage.getAssigningUsersActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on AssignUsers", assignUsersLinkClasses.contains("ui-state-disabled"));
    roleAssignmentPage.assignUsersToRole(roleName, Arrays.asList("demo", "david"));
    roleAssignmentPage.clickOnCloseAssignUsersButton();
    assertFalse("Total users of role is the same",
        totalUsers.equalsIgnoreCase(roleAssignmentPage.getTotalUsersOfRoleInRoleTreeTable(roleName)));
  }

  @Test
  public void testAssigningUsersToExistedRole() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var roleName = "HR";
    var totalUsers = roleAssignmentPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    var assignUsersLinkClasses =
        roleAssignmentPage.getAssigningUsersActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on AssignUsers", assignUsersLinkClasses.contains("ui-state-disabled"));
    roleAssignmentPage.assignUsersToRole(roleName, Arrays.asList("david", "admin"));
    roleAssignmentPage.clickOnCloseAssignUsersButton();
    assertFalse("Total users of role is the same",
        totalUsers.equalsIgnoreCase(roleAssignmentPage.getTotalUsersOfRoleInRoleTreeTable(roleName)));
  }

  @Test
  public void testFilteringRoles() {
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    accessToRoleManagement();
    var roleName = "HR";
    roleAssignmentPage.filterRoleTreeTableByRoleName(roleName);
    assertTrue("RoleAssignment tab is displayed",
        roleAssignmentPage.getRoleNamesInRoleTreeTable().equalsIgnoreCase("(Everybody);(HR)"));
  }

  private RoleAssignmentPage gotoRoleAssignmentAndCreateNewRole(String roleName) {
    accessToRoleManagement();
    roleAssignmentPage.openRoleCreationDialog();
    roleAssignmentPage.createNewRoleWithData("Everybody", roleName, "New Role", "NewRoleDescription",
        Arrays.asList("admin"));
    roleAssignmentPage.clickOnSaveRoleButtonDialog();
    return roleAssignmentPage;
  }

  private RoleAssignmentPage accessToRoleManagement() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    var adminSettingsPage = new HomePage().openAdminSettings();
    assertTrue("RoleAssignment tab is displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());
    roleAssignmentPage = adminSettingsPage.openRoleAssignmentTab();
    return roleAssignmentPage;
  }

}
