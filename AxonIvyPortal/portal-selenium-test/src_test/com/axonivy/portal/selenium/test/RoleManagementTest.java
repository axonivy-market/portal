package com.axonivy.portal.selenium.test;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.AdminSettingsPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.RoleManagementPage;
import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.PortalPermission;

@IvyWebTest
public class RoleManagementTest extends BaseTest {

  private static final String DENY_SPECIFIC_PERMISSION =
      "portalKitTestHelper/14DE09882B540AD5/denySpecificPortalPermission.ivp?portalPermission=%s";
  private static final String GRANT_SPECIFIC_PERMISSION =
      "portalKitTestHelper/14DE09882B540AD5/grantSpecificPortalPermission.ivp?portalPermission=%s";

  private NewDashboardPage newDashboardPage;
  private AdminSettingsPage adminSettingsPage;
  private RoleManagementPage roleManagementPage;

  @BeforeEach
  @Override
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testVisibilityForRoleManagementTab() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    accessToRoleManagement();
    assertTrue(roleManagementPage.isDisplayed(), "RoleManagement tab is not displayed");

    // Admin lacks RoleReadAll permission
    denySpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    newDashboardPage = new NewDashboardPage();
    adminSettingsPage = newDashboardPage.openAdminSettings();
    assertFalse(adminSettingsPage.isRoleAssingmentTabViewPresent(), "RoleManagement tab is displayed");
  }

  @Test
  public void testVisibilityForCreatingRoleButton() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    accessToRoleManagement();
    assertTrue(roleManagementPage.isCreateNewRoleButtonPresent(), "Create role button is not displayed");
    roleManagementPage.openRoleCreationDialog();
    assertTrue(roleManagementPage.getRoleInfoDialogContent().isDisplayed(), "Role info dialog is not displayed");
    roleManagementPage.clickOnCancelLinkOfRoleDialog();

    // Admin lacks RoleCreate permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertFalse(roleManagementPage.isCreateNewRoleButtonPresent(), "Create role button is displayed");
  }

  @Test
  public void testVisibilityForDeletingRoleAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleManagementPage roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role is not appear on tree");
    var deleteLinkClasses = roleManagementPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertFalse(deleteLinkClasses.contains("ui-state-disabled"), "User can not click on delele New Role");

    // Admin lacks RoleDelete permission
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue(roleManagementPage.isDisplayed(), "RoleManagement tab is not displayed");
    deleteLinkClasses = roleManagementPage.getDeleteActionEnableForRole(roleName).getAttribute("class");
    assertTrue(deleteLinkClasses.contains("ui-state-disabled action-column-icon-button"),
        "User can click on delele New Role");

    // Admin can delete role
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    accessToRoleManagement();
    assertTrue(roleManagementPage.isDisplayed(), "RoleManagement tab is not displayed");
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role is not appear on tree");
    roleManagementPage.deleteRole(roleName);
    assertFalse(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role still appears on tree");
  }

  @Test
  public void testVisibilityForEditingRoleAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleDelete"));
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleMove"));

    var roleName = "NewRole" + UUID.randomUUID();
    roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role is not appear on tree");

    // Admin lacks RoleCreate permission then he cannot edit role
    redirectToRelativeLink(String.format(DENY_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var editLinkClasses = roleManagementPage.getEditActionEnableForRole(roleName).getAttribute("class");
    assertTrue(editLinkClasses.contains("ui-state-disabled action-column-icon-button"),
        "User can click on edit New Role");

    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    assertTrue(roleManagementPage.isDisplayed(), "RoleManagement tab is not displayed");
    SelenideElement editLink = roleManagementPage.getEditActionEnableForRole(roleName);
    editLinkClasses = editLink.getAttribute("class");
    assertFalse(editLinkClasses.contains("ui-state-disabled"), "User can not click on edit New Role");
    roleManagementPage.clickOnEditRole(editLink);
    var roleDisplayName = "RoleDisplayName is edited";
    roleManagementPage.enterRoleAdditionalInformation(roleDisplayName, "RoleDescription is edited");
    roleManagementPage.clickOnSaveRoleButtonDialog();
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role is not appears on tree");
    assertTrue(roleManagementPage.getRoleDisplayNameInRoleTreeTable().contains(roleDisplayName),
        "RoleDisplayname is not be changed");
  }

  @Test
  public void testVisibilityForAssigningUsersAction() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    var roleName = "NewRole" + UUID.randomUUID();
    RoleManagementPage roleManagementPage = accessToRoleManagementAndCreateNewRole(roleName);
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().contains(roleName), "New Role is not appear on tree");
    var totalUsers = roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    var assignUsersLinkClasses =
        roleManagementPage.getAssigningUsersActionEnableForRole(roleName).getAttribute("class");
    assertFalse(assignUsersLinkClasses.contains("ui-state-disabled"), "User can not click on AssignUsers");
    roleManagementPage.assignUsersToRole(roleName, Arrays.asList("demo", "david"));
    roleManagementPage.clickOnCloseAssignUsersButton();
    assertFalse(totalUsers.equalsIgnoreCase(roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName)),
        "Total users of role is the same");
  }

  @Test
  public void testAssigningUsersToExistedRole() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    redirectToRelativeLink(String.format(GRANT_SPECIFIC_PERMISSION, "RoleCreate"));
    accessToRoleManagement();
    var roleName = "HR";
    var assignUsersLink = roleManagementPage.getAssigningUsersActionEnableForRole(roleName);
    var assignUsersLinkClasses = assignUsersLink.getAttribute("class");
    assertFalse(assignUsersLinkClasses.contains("ui-state-disabled"), "User can not click on AssignUsers");
    roleManagementPage.removeAllUsersOfRole(roleName);
    roleManagementPage.clickOnCloseAssignUsersButton();
    var totalUsers = roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName);
    roleManagementPage.assignUsersToRole(roleName, Arrays.asList("admin", "demo", "david"));
    roleManagementPage.clickOnCloseAssignUsersButton();
    assertFalse(totalUsers.equalsIgnoreCase(roleManagementPage.getTotalUsersOfRoleInRoleTreeTable(roleName)),
        "Total users of role is the same");
    denySpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
  }

  @Test
  public void testFilteringRoles() {
    grantSpecificPortalPermission(PortalPermission.ROLE_MANAGEMENT);
    accessToRoleManagement();
    String roleName = "HR";
    roleManagementPage.filterRoleTreeTableByRoleName(roleName);
    assertTrue(roleManagementPage.getRoleNamesInRoleTreeTable().equalsIgnoreCase("Everybody;HR"),
        "Role tree does not contain (Everybody);(HR)");
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
    redirectToRelativeLink(NewDashboardPage.PORTAL_HOME_PAGE_URL);
    var adminSettingsPage = new NewDashboardPage().openAdminSettings();
    assertTrue(adminSettingsPage.isRoleAssingmentTabViewPresent(), "RoleManagement tab is NOT displayed");
    roleManagementPage = adminSettingsPage.openRoleManagementTab();
    return roleManagementPage;
  }

}
