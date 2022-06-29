package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.RoleAssignmentPage;

public class RoleAssignmentTest extends BaseTest {

  private static final String DENY_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  private static final String GRANT_SPECIFIC_PERMISSION = "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";

  @Test
  public void testAdminUserWithRightRolePermisions() {
    login(TestAccount.ADMIN_USER);
    var homePage = new HomePage();
    assertTrue("Admin Settings menu item is not displayed", homePage.isAdminSettingsMenuItemPresent());
    var adminSettingsPage = homePage.openAdminSettings();
    assertTrue("Admin Settings dialog is not displayed", adminSettingsPage.isDisplayed());
    var roleAssignmentTab = adminSettingsPage.openRoleAssignmentTab();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentTab.isDisplayed());
    assertTrue("Create role button is not displayed", roleAssignmentTab.isCreateNewRoleButtonPresent());
    roleAssignmentTab.openRoleCreationDialog();
    assertTrue("Role info dialog is not displayed", roleAssignmentTab.getRoleInfoDialogContent().isDisplayed());
    roleAssignmentTab.clickOnCancelLinkOfRoleDialog();
  }

  @Test
  public void testWhenAdminUserLacksOfPermissions() {
    // Admin lacks RoleReadAll permission
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleReadAll"));
    var homePage = new HomePage();
    var adminSettingsPage = homePage.openAdminSettings();
    assertFalse("RoleAssignment tab is displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());

    // Admin lacks RoleCreate permission
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    homePage = new HomePage();
    adminSettingsPage = homePage.openAdminSettings();
    var roleAssignmentTab = adminSettingsPage.openRoleAssignmentTab();
    assertTrue("RoleAssignment tab is not displayed", roleAssignmentTab.isDisplayed());
    assertFalse("Create role button is displayed", roleAssignmentTab.isCreateNewRoleButtonPresent());

    // Admin lacks RoleDelete permission
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleDelete"));
    var roleName = "NewRole" + UUID.randomUUID();
    roleAssignmentTab = gotoRoleAssignmentAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleAssignmentTab.getRoleSummaryTreeTableData().contains(roleName));
    var deleteLinkClasses = roleAssignmentTab.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertTrue("User can click on delele New Role", deleteLinkClasses.contains("ui-state-disabled action-column-icon-button"));
  }

  @Test
  public void testWhenAdminUserCanManageRole() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleReadAll"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleMove"));

    var roleName = "NewRole" + UUID.randomUUID();
    var roleAssignmentTab = gotoRoleAssignmentAndCreateNewRole(roleName);
    assertTrue("New Role is not appear on tree", roleAssignmentTab.getRoleSummaryTreeTableData().contains(roleName));
    roleAssignmentTab.deleteRole(roleName);
    assertFalse("New Role still appears on tree", roleAssignmentTab.getRoleSummaryTreeTableData().contains(roleName));
  }

  private RoleAssignmentPage gotoRoleAssignmentAndCreateNewRole(String roleName) {
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    var adminSettingsPage = new HomePage().openAdminSettings();
    assertTrue("RoleAssignment tab is displayed", adminSettingsPage.isRoleAssingmentTabViewPresent());

    var roleAssignmentTab = adminSettingsPage.openRoleAssignmentTab();
    roleAssignmentTab.openRoleCreationDialog();
    roleAssignmentTab.createNewRoleWithData("Everybody", roleName, "New Role", "NewRoleDescription", Arrays.asList("admin"));
    roleAssignmentTab.clickOnSaveRoleButtonDialog();
    return roleAssignmentTab;
  }
}
