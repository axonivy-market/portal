package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

public class AbsencePage extends TemplatePage {

  private static final String DELETE_ABSENCE__LINK_ID_PATTERN =
      "absences-management-form:absence-table:%d:delete-absence";
  private static final String ABSENCE_ACTION_BUTTON_PATTERN = 
      "absences-management-form:absence-table:%d:absence-action-button";
  private static final String EDIT_ABSENCE__LINK_ID_PATTERN = "absences-management-form:absence-table:%d:edit-absence";

  @Override
  protected String getLoadedLocator() {
    return "[id='absences-management-form']";
  }

  public NewAbsencePage openNewAbsenceDialog() {
    $("button[id*='add-absence']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAbsenceDialog();
    return new NewAbsencePage();
  }

  public void countAbsences(int expectedSize) {
    $("div[id*='absence-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    $("td.absence-period").shouldBe(appear, DEFAULT_TIMEOUT);
    $$("td.absence-period").shouldBe(CollectionCondition.sizeGreaterThanOrEqual(expectedSize));
  }

  public void showAbsencesInThePast(boolean shown) {
    SelenideElement checkBox = $(".show-absence-in-the-past-panel");
    boolean checkBoxSelected = checkBox.isSelected();
    if (checkBoxSelected != shown) {
      waitForElementClickableThenClick(checkBox);
    }
  }

  public SelenideElement getMyDeputy(int deputyRoleIndex) {
    String deputiesSelector =
        String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
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
    String deputyRoleTypeSelector = ".substitute-table .substition-role-type";
    ElementsCollection elements = $$(deputyRoleTypeSelector);
    if (!elements.isEmpty()) {
      for (int index = 0; index < elements.size(); index++) {
        SelenideElement element = elements.get(index);
        String deputyRoleTypeValue = element.getAttribute("deputy-role-type");
        if (deputyRoleTypeValue != null && deputyRoleTypeValue.equals(String.valueOf(deputyRoleType))) {
          return index;
        }
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
    clickSelectedDeputiesLink(deputyRoleIndex);
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

  private void clickSelectedDeputiesLink(int deputyRoleIndex) {
    String deputiesSelector =
        String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(deputiesSelector);
    $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void selectDeputy(String responsible) {
    SelenideElement element = $(By.id("deputy-selection-form:user-selection-component:user-selection_input"));
    element.clear();
    element.sendKeys(responsible);
    SelenideElement selectionPanel = $(By.id("deputy-selection-form:user-selection-component:user-selection_panel"));
    selectionPanel.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    selectionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick("[id$='deputy-selection-form:add-deputy-button']");
  }

  public String getChooseDeputyDialogError() {
    String errorMessageDiv = "div[id$='deputy-selection-form:error-message']";
    return $(errorMessageDiv).shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-messages-error-detail").getText();
  }

  public void setSubstitutedByAdmin(String substitutedUser) {
    String selectedUserInput = "input[id$=':user-absence-selection-component:user-absence_input']";
    SelenideElement substituted = $(selectedUserInput).shouldBe(appear, DEFAULT_TIMEOUT);
    substituted.clear();
    substituted.sendKeys(substitutedUser);
    waitForElementClickableThenClick(
        "[id='absences-management-form:user-absence-selection-component:user-absence_panel']");
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

  public WebElement getAbsenceForm() {
    return $("[id$='absences-management-container']");
  }

  public SelenideElement getAddAbsenceDialog() {
    $("[id='absence-dialog_title']").click();
    return $("[id$='absence-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void waitForAbsencesGrowlMessageDisplay() {
    SelenideElement growlMessage =
        $("div[id$='absences-management-form:absences-management-info_container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $(growlMessage.findElement(By.className("ui-growl-item-container"))).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public void clickOnAbsenceAction(int index) {
    $(By.id(String.format(ABSENCE_ACTION_BUTTON_PATTERN, index))).click();
  }
  public void canDeleteAbsence(int index) {
    clickOnAbsenceAction(index);
    waitForElementDisplayed($(By.id(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index))), true);
    //Click to close
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
}
