package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axonivy.portal.selenium.common.Sleeper;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

public class AbsencePage extends TemplatePage {

  private static final String DELETE_ABSENCE__LINK_ID_PATTERN = "absences-management-form:absences-tabview:absence-table:%d:delete-absence";
  private static final String ABSENCE_ACTION_BUTTON_PATTERN = "absences-management-form:absences-tabview:absence-table:%d:absence-action-button";
  private static final String EDIT_ABSENCE__LINK_ID_PATTERN = "absences-management-form:absences-tabview:absence-table:%d:edit-absence";
  private static final String TABVIEW_SELECTOR = "div[id='absences-management-form:absences-tabview']";
  private static final String ABSENCES_TAB_HEADER = TABVIEW_SELECTOR + " > ul > li:nth-child(1)";
  private static final String SUBSTITUTES_TAB_HEADER = TABVIEW_SELECTOR + " > ul > li:nth-child(2)";

  @Override
  protected String getLoadedLocator() {
    return "[id='absences-management-form']";
  }

  public NewAbsencePage openNewAbsenceDialog() {
    $("button[id*='add-absence']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAbsenceDialog();
    return new NewAbsencePage();
  }

  public void openAbsencesTab() {
    SelenideElement tabHeader = $(ABSENCES_TAB_HEADER);
    if (tabHeader.exists() && !isTabActive(tabHeader)) {
      tabHeader.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    assertAbsencesTabActive();
  }

  public void openSubstitutesTab() {
    SelenideElement tabHeader = $(SUBSTITUTES_TAB_HEADER);
    if (tabHeader.exists() && !isTabActive(tabHeader)) {
      tabHeader.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    }
    assertSubstitutesTabActive();
  }

  public void assertAbsencesTabActive() {
    $(ABSENCES_TAB_HEADER).shouldHave(Condition.attribute("aria-selected", "true"), DEFAULT_TIMEOUT);
  }

  public void assertSubstitutesTabActive() {
    $(SUBSTITUTES_TAB_HEADER).shouldHave(Condition.attribute("aria-selected", "true"), DEFAULT_TIMEOUT);
  }

  public boolean isAbsencesTabActive() {
    return isTabActive($(ABSENCES_TAB_HEADER));
  }

  public boolean isSubstitutesTabActive() {
    return isTabActive($(SUBSTITUTES_TAB_HEADER));
  }

  private boolean isTabActive(SelenideElement tabHeader) {
    return "true".equals(tabHeader.getAttribute("aria-selected"));
  }

  public void countAbsences(int expectedSize) {
    $("div[id*='absence-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("td.absence-period").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("td.absence-period").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(expectedSize));
  }

  public int getAbsenceRowCount() {
    return $("tbody[id$='absence-table_data']").shouldBe(appear, DEFAULT_TIMEOUT).$$("tr").size();
  }

  public boolean isEmptyMessageAvailable() {
    $("div[id*='absence-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    ElementsCollection rows = $$("tbody[id$='absence-table_data'] tr");
    SelenideElement emptyRow = rows.findBy(cssClass("ui-datatable-empty-message"));
    return emptyRow.exists();
  }

  public String getAbsencePeriodText(int rowIndex) {
    return $$("tbody[id$='absence-table_data'] tr").get(rowIndex).$("td.absence-period .absence-column-value")
        .getText();
  }

  public void waitForAbsencePeriodToChange(int rowIndex, String oldPeriod) {
    $$("tbody[id$='absence-table_data'] tr").get(rowIndex)
        .$("td.absence-period .absence-column-value")
        .shouldNotHave(Condition.text(oldPeriod), DEFAULT_TIMEOUT);
  }

  public void waitForAbsenceTableChange(int newRowCount) {
    $("tbody[id$='absence-table_data']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("tbody[id$='absence-table_data'] tr").shouldHave(CollectionCondition.size(newRowCount), DEFAULT_TIMEOUT);
  }

  public void waitForSubstituteRowCountToChange(int newRowCount) {
    $("tbody[id$='substitute-table_data']").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("tbody[id$='substitute-table_data'] tr").shouldHave(CollectionCondition.size(newRowCount), DEFAULT_TIMEOUT);
  }

  public String getAbsenceSubstitutesText(int rowIndex) {
    SelenideElement row = $$("tbody[id$='absence-table_data'] tr").get(rowIndex);
    return row.$("td.absence-substitutes").getText();
  }

  public boolean hasPermanentSubstituteIcon(int rowIndex) {
    SelenideElement row = $$("tbody[id$='absence-table_data'] tr").get(rowIndex);
    return row.$("i.fa-star").exists();
  }

  public void showAbsencesInThePast(boolean shown) {
    SelenideElement toggleSwitch = $(".show-absence-in-the-past-panel");
    boolean isChecked = toggleSwitch.getDomAttribute("class").contains("ui-toggleswitch-checked");
    if (isChecked != shown) {
      toggleSwitch.$(".ui-toggleswitch-slider").shouldBe(appear, DEFAULT_TIMEOUT).click();
    }
  }

  public SelenideElement getMyDeputy(int deputyRoleIndex) {
    String deputiesSelector = String
        .format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    return $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getMyDisabledDeputy(int deputyRoleIndex) {
    String deputiesSelector = String
        .format(
            "a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']",
            deputyRoleIndex);
    return $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getIAMDeputyFor() {
    ElementsCollection noteAuthorElements = $$("tbody[id*='substitution-table_data'] > tr > td");
    return noteAuthorElements.asFixedIterable().stream().map(w -> w.getText()).collect(Collectors.joining());
  }

  public int indexOfDeputyRole(DeputyRoleType deputyRoleType) {
    ElementsCollection rows = $$("tbody[id$='substitute-table_data'] tr");
    for (int index = 0; index < rows.size(); index++) {
      SelenideElement roleTypeCell = rows.get(index).$(".substition-role-type");
      if (roleTypeCell.exists() && String.valueOf(deputyRoleType.name()).equals(roleTypeCell.getAttribute("deputy-role-type"))) {
        return index;
      }
    }
    return -1;
  }

  public void setDeputy(List<String> fullNames, DeputyRoleType deputyRoleType) {
    setDeputy(fullNames, indexOfDeputyRole(deputyRoleType), true);
  }

  public void setDeputy(List<String> fullNames, DeputyRoleType deputyRoleType, boolean saveSelectedDeputies) {
    setDeputy(fullNames, indexOfDeputyRole(deputyRoleType), saveSelectedDeputies);
  }

  public void setDeputy(List<String> fullNames, int deputyRoleIndex) {
    setDeputy(fullNames, deputyRoleIndex, true);
  }

  public void setDeputy(List<String> fullNames, int deputyRoleIndex, boolean saveSelectedDeputies) {
    openEditDeputiesDialog(deputyRoleIndex);
    for (String fullName : fullNames) {
      selectDeputy(fullName);
    }
    if (saveSelectedDeputies) {
      waitForElementClickableThenClick("[id='deputy-selection-form:save-deputy-button']");
      $(By.id("choose-deputy-dialog")).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    }
  }

  @Override
  public void waitForElementValueChanged(String cssSelector, String expectedValue) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), expectedValue));
  }

  public void openAddSubstituteDialog() {
    waitForElementClickableThenClick("button[id$='absences-management-form:absences-tabview:add-substitute']");
    $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openEditDeputiesDialog(int deputyRoleIndex) {
    String editButton = String.format(
        "absences-management-form:absences-tabview:substitute-table:%d:edit-deputies-button",
        deputyRoleIndex);
    waitForElementClickableThenClick(By.id(editButton));
    $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void openEditDeputiesDialog(DeputyRoleType deputyRoleType) {
    int deputyRoleIndex = indexOfDeputyRole(deputyRoleType);
    if (deputyRoleIndex >= 0) {
      openEditDeputiesDialog(deputyRoleIndex);
    }
  }

  public void addSubstitute(String deputyRoleDisplayName, List<String> fullNames) {
    openAddSubstituteDialog();
    selectDeputyRoleByDisplayName(deputyRoleDisplayName);
    for (String fullName : fullNames) {
      addDeputyInChooseDialog(fullName);
    }
    saveSelectedDeputies();
  }

  public void selectDeputyRoleByDisplayName(String displayName) {
    SelenideElement selectTrigger = $(By.id("deputy-selection-form:deputy-role-selection")).shouldBe(appear,
        DEFAULT_TIMEOUT);
    selectTrigger.click();
    $("div[id$='deputy-role-selection_panel']").shouldBe(appear, DEFAULT_TIMEOUT)
        .$$("li.ui-selectonemenu-item").findBy(Condition.text(displayName))
        .shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void addDeputyInChooseDialog(String fullName) {
    selectDeputy(fullName);
  }

  private void selectDeputy(String responsible) {
    SelenideElement element = $(By.id("deputy-selection-form:user-selection-component:user-selection_input"));
    element.clear();
    element.sendKeys(responsible);
    String panelSelector = "[id$='deputy-selection-form:user-selection-component:user-selection_panel']";
    $(panelSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    String itemSelector = "tr[data-item-label*='" + responsible + "'].ui-state-highlight";
    $(itemSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $(panelSelector).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick("[id$='deputy-selection-form:add-deputy-button']");
  }

  public String getChooseDeputyDialogError() {
    String errorMessageDiv = "div[id$='deputy-selection-form:error-message']";
    return $(errorMessageDiv).shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-messages-error-detail").getText();
  }

  public void setSelectedUser(String selectedUser) {
    String selectedUserInput = "input[id$='user-absence-absences_input']";
    SelenideElement input = $(selectedUserInput).shouldBe(appear, DEFAULT_TIMEOUT);
    input.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    input.setValue(selectedUser);
    String panelSelector = isAbsencesTabActive()
        ? "span[id$='user-absence-absences_panel'] tbody tr"
        : "span[id$='user-absence-substitutes_panel'] tbody tr";
    ElementsCollection rows = $$(panelSelector);
    rows.first().shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
  }

  public void setSubstituteUserByAdmin(String substitutedUser) {
    String selectedUserInput = "input[id$='user-absence-substitutes_input']";
    SelenideElement substituted = $(selectedUserInput).shouldBe(appear, DEFAULT_TIMEOUT);
    substituted.clear();
    substituted.sendKeys(substitutedUser);
    waitForElementClickableThenClick("[id$='user-absence-substitutes_panel']");
  }

  public String getSelectedSubstituteUser() {
    return $("input[id$='user-absence-substitutes_input']").shouldBe(appear, DEFAULT_TIMEOUT).getValue();
  }

  public String getSelectedAbsenceUser() {
    return $("input[id$='user-absence-absences_input']").shouldBe(appear, DEFAULT_TIMEOUT).getValue();
  }

  public String getSubstitutedByAdmin(int rowIndex) {
    SelenideElement deputyFor = $("[id$=':substitution-table']").$$(By.cssSelector(".name-after-avatar"))
        .shouldBe(CollectionCondition.size(1)).get(rowIndex);
    return deputyFor.getText();
  }

  public void saveSubstitute() {
    waitForElementClickableThenClick("button[id$='absences-management-form:save-substitute']");
    waitForAbsencesGrowlMessageDisplay();
  }

  public void setDuringAbsenceDeputyInAbsenceDialog(List<String> fullNames) {
    for (String fullName : fullNames) {
      SelenideElement input = $(By.id("absence-form:user-selection-component:user-selection_input"));
      input.clear();
      input.sendKeys(fullName);
      SelenideElement panel = $(By.id("absence-form:user-selection-component:user-selection_panel"));
      panel.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      panel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
      waitForElementClickableThenClick("[id$='absence-form:add-deputy-button']");
    }
  }

  public String getDuringAbsenceDeputiesFromAbsenceTable() {
    $("div[id*='absence-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $("tbody[id$='absence-table_data'] tr td:nth-child(2) .absence-column-value")
        .shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getDeputiesByRoleType(DeputyRoleType deputyRoleType) {
    int deputyRoleIndex = indexOfDeputyRole(deputyRoleType);
    if (deputyRoleIndex < 0) {
      return "";
    }
    return getDeputiesByRoleIndex(deputyRoleIndex);
  }

  public String getDeputiesByRoleIndex(int deputyRoleIndex) {
    ElementsCollection rows = $$("tbody[id$='substitute-table_data'] tr");
    if (rows.isEmpty() || deputyRoleIndex >= rows.size()) {
      return "";
    }
    ElementsCollection values = rows.get(deputyRoleIndex).$$(".absence-column-value");
    if (values.size() > 1) {
      return values.get(1).getText();
    }
    return rows.get(deputyRoleIndex).getText();
  }

  public String getDeputyRoleDisplayName(DeputyRoleType deputyRoleType) {
    int deputyRoleIndex = indexOfDeputyRole(deputyRoleType);
    if (deputyRoleIndex < 0) {
      return "";
    }
    ElementsCollection rows = $$("tbody[id$='substitute-table_data'] tr");
    ElementsCollection values = rows.get(deputyRoleIndex).$$(".absence-column-value");
    return values.isEmpty() ? "" : values.get(0).getText();
  }

  public void removeDeputyFromChooseDialog(int deputyIndex) {
    String deleteSelector = String.format("a[id$='deputy-selection-form:selected-deputy-panel:%d:delete-deputy']",
        deputyIndex);
    waitForElementClickableThenClick(deleteSelector);
  }

  public WebElement getAbsenceForm() {
    Sleeper.sleep(500); // Explicitly wait for better screenshots
    return $("[id$='absences-management-container']");
  }

  public SelenideElement getAbsenceManagement() {
    return $("div[class*='absence-management']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public SelenideElement getAddAbsenceDialog() {
    $("div[class*='absence-dialog-header']").shouldBe(appear, DEFAULT_TIMEOUT).click();
    return $("[id$='absence-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForAbsencesGrowlMessageDisplay() {
    SelenideElement growlMessage = $("div[id$='absences-management-form:absences-management-info_container']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    $(growlMessage.findElement(By.className("ui-growl-item-container"))).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForAbsencesGrowlMessageHide() {
    SelenideElement growlMessage = $("div[id$='absences-management-form:absences-management-info_container']")
        .shouldBe(appear, DEFAULT_TIMEOUT);
    $(growlMessage.findElement(By.className("ui-growl-item-container"))).shouldBe(disappear, DEFAULT_TIMEOUT);
  }

  public void clickOnAbsenceAction(int index) {
    $(By.id(String.format(ABSENCE_ACTION_BUTTON_PATTERN, index))).click();
  }

  public NewAbsencePage openEditAbsenceDialog(int index) {
    clickOnAbsenceAction(index);
    SelenideElement editLink = $(By.id(String.format(EDIT_ABSENCE__LINK_ID_PATTERN, index)));
    editLink.shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAbsenceDialog();
    return new NewAbsencePage();
  }

  public void deleteAbsence(int index) {
    clickOnAbsenceAction(index);
    SelenideElement deleteLink = $(By.id(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index)));
    deleteLink.shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitForElementClickableThenClick("[id*='confirm-action']");
  }

  public boolean isSubstituteErrorMessageDisplayed() {
    return $("div[id$='error-message']").shouldBe(appear, DEFAULT_TIMEOUT).exists();
  }

  public void canDeleteAbsence(int index) {
    clickOnAbsenceAction(index);
    waitForElementDisplayed($(By.id(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index))), true);
    // Click to close
    clickOnAbsenceAction(index);
  }

  public void canEditAbsence(int index) {
    clickOnAbsenceAction(index);
    waitForElementDisplayed($(By.id(String.format(EDIT_ABSENCE__LINK_ID_PATTERN, index))), true);
    clickOnAbsenceAction(index);
  }

  public void isDeputySettingSectionDisplayed(boolean isDisplay) {
    waitForElementDisplayed(By.id("deputy-setting"), isDisplay);
  }

  public WebElement getChooseDeputyDialog() {
    return $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void saveSelectedDeputies() {
    waitForElementClickableThenClick("[id='deputy-selection-form:save-deputy-button']");
    $(By.id("choose-deputy-dialog")).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
  }
}
