package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RoleAssignmentPage extends TemplatePage {

  private static final String ROLE_ASSINGMENT_PAGE_ID =
      "admin-setting-component:adminTabView:role-management-component:role-management-form";

  public RoleAssignmentPage() {
    waitForElementDisplayed(By.id(ROLE_ASSINGMENT_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "id('admin-setting-component:adminTabView:role-management-tab')";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(ROLE_ASSINGMENT_PAGE_ID).isDisplayed();
  }

  public boolean isCreateNewRoleButtonPresent() {
    return isElementPresent(By.cssSelector("[id$=':role-management-component:role-management-form:create-role-button']"));
  }

  public void openRoleCreationDialog() {
    clickByCssSelector("[id$=':role-management-component:role-management-form:create-role-button']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-details-dialog_content']"), true);
  }

  public WebElement getRoleInfoDialogContent() {
    return findElementByCssSelector("[id$=':role-management-component:role-details-dialog_content']");
  }

  public void clickOnSaveRoleButtonDialog() {
    clickByCssSelector("[id$=':role-management-component:save-role-configuration']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-details-dialog']"), false);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"), true);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"), false);
  }

  public void createNewRoleWithData(String parentRole, String roleName, String roleDisplayName, String roleDescription,
      List<String> members) {
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:manage-role-details-form']"), true);

    // Fill parent data
    var parentRoleInput = findElementByCssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_input']");
    parentRoleInput.clear();
    parentRoleInput.sendKeys(parentRole);
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']"), true);
    var roleSelectionPanel = findElementByCssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']");
    var parentRoleOption = roleSelectionPanel.findElements(By.cssSelector(".ui-autocomplete-item.ui-autocomplete-row")).get(0);
    click(parentRoleOption);
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']"), false);

    // Fill role name data
    var roleNameInput = findElementByCssSelector("[id$=':manage-role-details-form:role-name']");
    roleNameInput.sendKeys(roleName);

    // Fill role display name data
    var roleDisplayNameInput = findElementByCssSelector("[id$=':manage-role-details-form:role-display-name']");
    roleDisplayNameInput.sendKeys(roleDisplayName);

    // Fill role description data
    var roleDescriptionInput = findElementByCssSelector("[id$=':manage-role-details-form:role-description']");
    roleDescriptionInput.sendKeys(roleDescription);

    // Assign users for role
    for (var userName : members) {
      var usersAssignmentInput = findElementByCssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_input']");
      usersAssignmentInput.clear();
      usersAssignmentInput.sendKeys(userName);
      waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel"), true);
      var userSelectionPanel = findElementByCssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel");
      var userOption = userSelectionPanel.findElements(By.cssSelector(".ui-autocomplete-item.ui-autocomplete-row")).get(0);
      click(userOption);

      waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel"), false);
      waitForElementEnabled(By.cssSelector("button[id$=':manage-role-details-form:add-new-user']"), true, DEFAULT_TIMEOUT);
      clickByCssSelector("button[id$=':manage-role-details-form:add-new-user']");
      waitForElementEnabled(By.cssSelector("button[id$=':manage-role-details-form:add-new-user']"), false, DEFAULT_TIMEOUT);
    }
  }

  public String getRoleSummaryTreeTableData() {
    var roleTreeTable = findElementByCssSelector("[id$=':role-management-form:role-tree-table']");
    return roleTreeTable.findElements(By.cssSelector("tbody tr.role span.role-username")).stream()
        .map(WebElement::getText).collect(Collectors.joining(";"));
  }

  public List<WebElement> getActionsGroupOnRole(String roleName) {
    var roleTreeTable = findElementByCssSelector("[id$=':role-management-form:role-tree-table']");
    var roles = roleTreeTable.findElements(By.cssSelector("tbody tr.role"));
    for (var role : roles) {
      var roleNameColumn = role.findElement(By.cssSelector("td.role-name-column span.role-username"));
      if (roleNameColumn.getText().equalsIgnoreCase(roleName)) {
        return role.findElements(By.cssSelector("td.role-actions-column a.action-column-icon-button"));
      }
    }
    return null;
  }

  public WebElement getDeleteActionEnableForRole(String roleName) {
    var roleTreeTable = findElementByCssSelector("[id$=':role-management-form:role-tree-table']");
    var roles = roleTreeTable.findElements(By.cssSelector("tbody tr.role"));
    for (var role : roles) {
      var roleNameColumn = role.findElement(By.cssSelector("td.role-name-column span.role-username"));
      if (roleNameColumn.getText().equalsIgnoreCase(String.format("(%s)", roleName))) {
        return role.findElement(By.cssSelector("td.role-actions-column [id$=':delete-role-link']"));
      }
    }
    return null;
  }

  public void deleteRole(String roleName) {
    var deleteLink = getDeleteActionEnableForRole(roleName);
    click(deleteLink);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:delete-role-dialog']"), true);
    clickByCssSelector("button[id$=':role-management-component:yes-button']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:delete-role-dialog']"), false);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"), true);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"), false);
  }

  public void clickOnCancelLinkOfRoleDialog() {
    clickByCssSelector("[id$=':role-management-component:cancel-link']");
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-details-dialog']"), false);
  }
}
