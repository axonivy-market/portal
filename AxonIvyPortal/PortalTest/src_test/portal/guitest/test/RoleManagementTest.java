package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import ch.ivy.addon.portalkit.enums.PortalPermission;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.AdminSettingsPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.RoleManagementPage;

public class RoleManagementTest extends BaseTest {

  private static final String DENY_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  private static final String GRANT_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";

  private HomePage homePage;
  private AdminSettingsPage adminSettingsPage;
  private RoleManagementPage roleManagementPage;

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
    assertTrue("RoleManagement tab is not displayed", roleManagementPage.isDisplayed());

    // Admin lacks RoleReadAll permission
    denySpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    homePage = new HomePage();
    adminSettingsPage = homePage.openAdminSettings();
    assertFalse("RoleManagement tab is displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());
  }

  @Test
  public void testVisibilityForCreatingRoleButton() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    accessToRoleManagement();
    assertTrue("Create role button is not displayed", roleManagementPage.isCreateNewRoleButtonPresent());
    roleManagementPage.openRoleCreationDialog();
    assertTrue("Role info dialog is not displayed", roleManagementPage.getRoleInfoDialogContent().isDisplayed());
    roleManagementPage.clickOnCancelLinkOfRoleDialog();

    // Admin lacks RoleCreate permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertFalse("Create role button is displayed", roleManagementPage.isCreateNewRoleButtonPresent());
  }

  @Test
  public void testVisibilityForDeletingRoleAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleManagementPage roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));
    var deleteLinkClasses = roleManagementPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on delele New Role", deleteLinkClasses.contains("ui-state-disabled"));

    // Admin lacks RoleDelete permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue("RoleManagement tab is not displayed", roleManagementPage.isDisplayed());
    deleteLinkClasses = roleManagementPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertTrue("User can click on delele New Role",
        deleteLinkClasses.contains("ui-state-disabled action-column-icon-button"));

    // Admin can delete role
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue("RoleManagement tab is not displayed", roleManagementPage.isDisplayed());
    assertTrue("New Role is not appear on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));
    roleManagementPage.deleteRole(roleName);
    assertFalse("New Role still appears on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));
  }

  @Test
  public void testVisibilityForEditingRoleAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleMove"));

    var roleName = "NewRole" + UUID.randomUUID();
    roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));

    // Admin lacks RoleCreate permission then he cannot edit role
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var editLinkClasses = roleManagementPage.getEditActionEnableForRole(roleName).getAttribute("class");
    assertTrue("User can click on edit New Role",
        editLinkClasses.contains("ui-state-disabled action-column-icon-button"));

    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertTrue("RoleManagement tab is not displayed", roleManagementPage.isDisplayed());
    editLinkClasses = roleManagementPage.getEditActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on edit New Role", editLinkClasses.contains("ui-state-disabled"));
    roleManagementPage.clickOnEditRole(roleName);
    var roleDisplayName = "RoleDisplayName is edited";
    roleManagementPage.enterRoleAdditionalInformation(roleDisplayName, "RoleDescription is edited");
    roleManagementPage.clickOnSaveRoleButtonDialog();
    assertTrue("New Role is not appears on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));
    assertTrue("RoleDisplayname is not be changed",
        roleManagementPage.getRoleDisplayNameInRoleTreeTable().contains(roleDisplayName));
  }

  @Test
  public void testVisibilityForAssigningUsersAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleManagementPage roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName));
    var totalUsers = roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    var assignUsersLinkClasses = roleManagementPage.getAssigningUsersActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on AssignUsers", assignUsersLinkClasses.contains("ui-state-disabled"));
    roleManagementPage.assignUsersToRole(roleName, Arrays.asList("demo", "david"));
    roleManagementPage.clickOnCloseAssignUsersButton();
    assertFalse("Total users of role is the same",
        totalUsers.equalsIgnoreCase(roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName)));
  }

  @Test
  public void testAssigningUsersToExistedRole() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var roleName = "HR";
    var assignUsersLinkClasses =
        roleManagementPage.getAssigningUsersActionEnableForRole(roleName).getAttribute("class");
    assertFalse("User can not click on AssignUsers", assignUsersLinkClasses.contains("ui-state-disabled"));
    roleManagementPage.removeAllUsersOfRole(roleName);
    roleManagementPage.clickOnCloseAssignUsersButton();
    var totalUsers = roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    roleManagementPage.assignUsersToRole(roleName, Arrays.asList("admin", "demo", "david"));
    roleManagementPage.clickOnCloseAssignUsersButton();
    assertFalse("Total users of role is the same",
        totalUsers.equalsIgnoreCase(roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName)));
  }

  @Test
  public void testFilteringRoles() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    accessToRoleManagement();
    var roleName = "HR";
    roleManagementPage.filterRoleTreeTableByRoleName(roleName);
    assertTrue("Role tree does not contain (Everybody);(HR)",
        roleManagementPage.getRoleNamesInRoleTreeTable().equalsIgnoreCase("Everybody;HR"));
  }

  private RoleManagementPage accessToRoleManagementAndCreateNewRole(String roleName) {
    accessToRoleManagement();
    roleManagementPage.openRoleCreationDialog();
    roleManagementPage.createNewRoleWithData("Everybody", roleName, "New Role", "NewRoleDescription",
        Arrays.asList("admin"));
    roleManagementPage.clickOnSaveRoleButtonDialog();
    return roleManagementPage;
  }

  private RoleManagementPage accessToRoleManagement() {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    var adminSettingsPage = new HomePage().openAdminSettings();
    assertTrue("RoleManagement tab is NOT displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());
    roleManagementPage = adminSettingsPage.openRoleManagementTab();
    return roleManagementPage;
  }

}
