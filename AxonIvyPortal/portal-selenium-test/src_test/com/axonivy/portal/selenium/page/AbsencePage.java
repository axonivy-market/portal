package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

public class AbsencePage extends TemplatePage {

  private static final String DELETE_ABSENCE__LINK_ID_PATTERN =
      "absences-management-form:absence-table:%d:delete-absence";
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
    SelenideElement checkBox = $("input[id*='show-absence-in-the-past']");
    boolean checkBoxSelected = checkBox.isSelected();
    if (checkBoxSelected != shown) {
      waitForElementClickableThenClick("div[id*='show-absence-in-the-past'] div.ui-chkbox-box");
      /**
       * should wait before it switch to counting line
       */
      refreshAndWaitElement("td.absence-period");
    }
  }
  
  public void showAbsencesInThePast() {
    $("div[id*='show-absence-in-the-past'] div.ui-chkbox-box").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    /**
     * should wait before it switch to counting line
     */
    refreshAndWaitElement("td.absence-period");
  }


  public SelenideElement getMyDeputy(int deputyRoleIndex) {
    String deputiesSelector =
        String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    return $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public String getMyDisabledDeputy(int deputyRoleIndex) {
    String deputiesSelector = String
        .format("span[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    return $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getIAMDeputyFor() {
    List<SelenideElement> noteAuthorElements = $$("tbody[id*='substitution-table_data'] > tr > td");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.joining());
  }

  public int indexOfDeputyRole(DeputyRoleType deputyRoleType) {
    String deputyRoleTypeSelector = ".substitute-table .substition-role-type";
    List<SelenideElement> elements = $$(deputyRoleTypeSelector);
    if (CollectionUtils.isNotEmpty(elements)) {
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
      $("[id='deputy-selection-form:save-deputy-button']").shouldBe(getClickableCondition());
      clickByJavaScript($("[id='deputy-selection-form:save-deputy-button']"));
      $(By.id("choose-deputy-dialog")).shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementValueChanged(String cssSelector, String expectedValue) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), expectedValue));
  }

  private void clickSelectedDeputiesLink(int deputyRoleIndex) {
    String deputiesSelector =
        String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    $(deputiesSelector).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT);
    clickByJavaScript($(deputiesSelector));
    $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void selectDeputy(String responsible) {
    SelenideElement element = $(By.id("deputy-selection-form:user-selection-component:user-selection_input"));
    element.clear();
    element.sendKeys(responsible);
    SelenideElement selectionPanel = $(By.id("deputy-selection-form:user-selection-component:user-selection_panel"));
    selectionPanel.shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    selectionPanel.shouldBe(Condition.disappear, DEFAULT_TIMEOUT);
    $("[id$='deputy-selection-form:add-deputy-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    $("[id='absences-management-form:user-absence-selection-component:user-absence_panel']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
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
    return $("[id$='absences-management-form']");
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

  public void canDeleteAbsence(int index) {
    waitForElementDisplayed($(By.id(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index))), true);
  }

  public void canEditAbsence(int index) {
    waitForElementDisplayed($(By.id(String.format(EDIT_ABSENCE__LINK_ID_PATTERN, index))), true);
  }

  public void isDeputySettingSectionDisplayed(boolean isDisplay) {
    waitForElementDisplayed(By.id("deputy-setting"), isDisplay);
  }
}
