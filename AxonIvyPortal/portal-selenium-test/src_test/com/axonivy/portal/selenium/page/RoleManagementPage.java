
package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.portal.selenium.common.WaitHelper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class RoleManagementPage extends TemplatePage {

  private static final String ROLE_ASSINGMENT_PAGE_ID =
      "admin-setting-component:adminTabView:role-management-component:role-management-form";

  public RoleManagementPage() {
    waitForElementDisplayed(By.id(ROLE_ASSINGMENT_PAGE_ID), true);
  }

  @Override
  protected String getLoadedLocator() {
    return "[id='admin-setting-component:adminTabView:role-management-tab']";
  }

  @Override
  public boolean isDisplayed() {
    return findElementById(ROLE_ASSINGMENT_PAGE_ID).isDisplayed();
  }

  public boolean isCreateNewRoleButtonPresent() {
    return isElementPresent(
        By.cssSelector("[id$=':role-management-component:role-management-form:create-role-button']"));
  }

  public void openRoleCreationDialog() {
    waitForElementClickableThenClick($("[id$=':role-management-component:role-management-form:create-role-button']"));
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-details-dialog_content']"), true);
  }

  public WebElement getRoleInfoDialogContent() {
    return $("[id$=':role-management-component:role-details-dialog_content']");
  }

  public void clickOnSaveRoleButtonDialog() {
    waitForElementClickableThenClick($("[id$=':role-management-component:save-role-configuration']"));
    waitForElementDisplayed(getRoleDetailsDialog(), false);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"),
        true);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"),
        false);
  }

  public void createNewRoleWithData(String parentRole, String roleName, String roleDisplayName, String roleDescription,
      List<String> members) {
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:manage-role-details-form']"), true);

    // Fill parent data
    var parentRoleInput =
        $(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_input']"));
    parentRoleInput.clear();
    parentRoleInput.sendKeys(parentRole);
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']"),
        true);
    var roleSelectionPanel =
        $(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']"));
    var parentRoleOption =
        roleSelectionPanel.findElements(By.cssSelector(".ui-autocomplete-item.ui-autocomplete-row")).get(0);
    parentRoleOption.click();
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:parent-role:parent-role-selection_panel']"),
        false);

    // Fill role name data
    var roleNameInput = $(By.cssSelector("[id$=':manage-role-details-form:role-name']"));
    roleNameInput.sendKeys(roleName);

    enterRoleAdditionalInformation(roleDisplayName, roleDescription);
    assignUsersToRole(members);
  }

  public String getRoleNamesInRoleTreeTable() {
    return getRoleTreeTable().$$(By.cssSelector("tbody tr.role span[id$=':role-username']")).asFixedIterable().stream()
        .map(SelenideElement::getText).collect(Collectors.joining(";"));
  }

  public String getRoleDisplayNameInRoleTreeTable() {
    return getRoleTreeTable().$$(By.cssSelector("tbody tr.role span[id$=':role-displayname']")).asFixedIterable()
        .stream().map(SelenideElement::getText).collect(Collectors.joining(";"));
  }

  public String getTotalUsersOfRoleInRoleTreeTable(String roleName) {
    for (var row : getRolesOnTheTreeTable()) {
      if (row.$(By.cssSelector("span[id$=':role-username']")).getText().equalsIgnoreCase(roleName)) {
        return row.$(By.cssSelector("span[id$=':role-assigned-users-text']")).getText();
      }
    }
    return "";
  }

  public SelenideElement getDeleteActionEnableForRole(String roleName) {
    return findActionForRoleByName(roleName, "delete-role-link");
  }

  public void deleteRole(String roleName) {
    var deleteLink = getDeleteActionEnableForRole(roleName);
    deleteLink.click();
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:delete-role-dialog']"), true);
    waitForElementClickableThenClick($("button[id$=':role-management-component:yes-button']"));
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:delete-role-dialog']"), false);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"),
        true);
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-management-message_container']"),
        false);
  }

  public SelenideElement getEditActionEnableForRole(String roleName) {
    return findActionForRoleByName(roleName, "edit-role-link");
  }

  public void clickOnEditRole(SelenideElement editLink) {
    editLink.shouldBe(clickable(), DEFAULT_TIMEOUT).click();
    waitForElementDisplayed(getRoleDetailsDialog(), true);
  }

  public SelenideElement getAssigningUsersActionEnableForRole(String roleName) {
    return findActionForRoleByName(roleName, "assign-users-to-role-link");
  }

  public SelenideElement findActionForRoleByName(String roleName, String actionId) {
    $$("tr.ui-node-level-2").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1), DEFAULT_TIMEOUT);
    SelenideElement searchInput = $(
        "[id='admin-setting-component:adminTabView:role-management-component:role-management-form:role-tree-table:global-filter']")
            .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
    searchInput.click();
    searchInput.clear();
    searchInput.sendKeys(roleName);
    ElementsCollection roles = $$("tr.ui-node-level-2").shouldBe(CollectionCondition.size(1), DEFAULT_TIMEOUT);
    return roles.filter(Condition.matchText(roleName + ".*")).get(0)
        .$(By.cssSelector("td.role-actions-column [id$=':" + actionId + "']"))
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }

  private ElementsCollection getRolesOnTheTreeTable() {
    return getRoleTreeTable().$$(By.cssSelector("tbody tr.role"));
  }

  private SelenideElement getRoleTreeTable() {
    return $("[id$=':role-management-form:role-tree-table']");
  }

  public void clickOnCancelLinkOfRoleDialog() {
    waitForElementClickableThenClick($("[id$=':role-management-component:cancel-link']"));
    waitForElementDisplayed(getRoleDetailsDialog(), false);
  }

  private By getRoleDetailsDialog() {
    return By.cssSelector("[id$=':role-management-component:role-details-dialog']");
  }

  public void enterRoleAdditionalInformation(String roleDisplayName, String roleDescription) {
    waitForElementDisplayed(By.cssSelector("[id$=':role-management-component:role-details-dialog_title']"), true);
    // Fill role display name data
    var roleDisplayNameInput = $("[id$=':manage-role-details-form:role-display-name']");
    roleDisplayNameInput.clear();
    roleDisplayNameInput.sendKeys(roleDisplayName);

    // Fill role description data
    var roleDescriptionInput = $("[id$=':manage-role-details-form:role-description']");
    roleDescriptionInput.clear();
    roleDescriptionInput.sendKeys(roleDescription);
  }

  public void clickOnAssignUsersToRole(String roleName) {
    var assignUsersLink = getAssigningUsersActionEnableForRole(roleName);
    assignUsersLink.click();
    waitForElementDisplayed(getRoleDetailsDialog(), true);
  }

  public void assignUsersToRole(String roleName, List<String> members) {
    clickOnAssignUsersToRole(roleName);
    assignUsersToRole(members);
  }

  private void assignUsersToRole(List<String> members) {
    for (var userName : members) {
      var usersAssignmentInput = $("[id$=':manage-role-details-form:user-assignment-selection:user-selection_input']")
          .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
      usersAssignmentInput.click();
      usersAssignmentInput.clear();
      usersAssignmentInput.sendKeys(userName);
      waitForElementDisplayed(
          By.cssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel"), true);
      var userSelectionPanel = $("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel")
          .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
      var userOption = userSelectionPanel.$$(By.cssSelector(".ui-autocomplete-item.ui-autocomplete-row")).get(0);
      userOption.shouldBe(clickable(), DEFAULT_TIMEOUT).click();

      waitForElementDisplayed(
          By.cssSelector("[id$=':manage-role-details-form:user-assignment-selection:user-selection_panel"), false);
      waitForElementClickableThenClick($(By.cssSelector("button[id$=':manage-role-details-form:add-new-user']")));
      $(By.cssSelector("button[id$=':manage-role-details-form:add-new-user']")).shouldBe(Condition.disabled,
          DEFAULT_TIMEOUT);
    }
  }

  public void clickOnCloseAssignUsersButton() {
    waitForElementClickableThenClick($("button[id$=':role-management-component:cancel-update-users']"));
    waitForElementDisplayed(getRoleDetailsDialog(), false);
  }

  public void filterRoleTreeTableByRoleName(String roleName) {
    var roleNames = getRoleNamesInRoleTreeTable();
    var filterInput = getRoleTreeTable()
        .findElement(By.cssSelector("input[id$=':role-management-form:role-tree-table:global-filter']"));
    filterInput.clear();
    filterInput.sendKeys(roleName);
    WaitHelper.assertTrueWithWait(() -> {
      return !roleNames.equalsIgnoreCase(getRoleNamesInRoleTreeTable());
    });
  }

  public void removeAllUsersOfRole(String roleName) {
    clickOnAssignUsersToRole(roleName);
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:users-of-role-table']"), true);
    removeUserOfRole(0);
  }

  private void removeUserOfRole(int index) {
    waitForElementDisplayed(By.cssSelector("[id$=':manage-role-details-form:users-of-role-table_data']"), true);
    if ($(By.cssSelector("a[id$=':delete-user-link']")).exists() && $$("a[id$=':delete-user-link']").size() > 0) {
      waitForElementClickableThenClick($$("a[id$=':delete-user-link']").get(index));
      removeUserOfRole(0);
    }
  }

  public SelenideElement getRoleCreationDialog() {
    return $("[id='admin-setting-component:adminTabView:role-management-component:role-details-dialog']")
        .shouldBe(Condition.appear, DEFAULT_TIMEOUT);
  }
}
