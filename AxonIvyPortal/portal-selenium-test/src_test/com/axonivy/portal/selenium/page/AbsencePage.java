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

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

public class AbsencePage extends TemplatePage {

  private static final String DELETE_ABSENCE__LINK_ID_PATTERN = "absences-management-form:absence-table:%d:delete-absence";
  private static final String EDIT_ABSENCE__LINK_ID_PATTERN = "absences-management-form:absence-table:%d:edit-absence";

  @Override
  protected String getLoadedLocator() {
    return "id('absences-management-form')";
  }

  public NewAbsencePage openNewAbsenceDialog() {
    String selector = "button[id*='add-absence']";
    waitForElementClickableThenClick(selector);
//    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "absence-form\\\\:absence-start-date_input", ID_PROPERTY);
    return new NewAbsencePage();
  }

  public int countAbsences() {
    $("div[id*='absence-table']").shouldBe(appear, DEFAULT_TIMEOUT);
    return $$("td.absence-period").size();
  }

  public void showAbsencesInThePast(boolean shown) {
    WebElement checkBox = $("input[id*='show-absence-in-the-past']");
    boolean checkBoxSelected = checkBox.isSelected();
    if (checkBoxSelected != shown) {
      waitForElementClickableThenClick("div[id*='show-absence-in-the-past'] div.ui-chkbox-box");
//      waitUntilAnimationFinished(DEFAULT_TIMEOUT, "ajax-indicator:ajax-indicator-ajax-indicator_start", "id");
    }
  }

  public String getMyDeputy(int deputyRoleIndex) {
    String deputiesSelector = String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    return $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT).getText();
  }

  public String getMyDisabledDeputy(int deputyRoleIndex) {
    String deputiesSelector = String.format("span[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
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
        WebElement element = elements.get(index);
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
    try {
      clickSelectedDeputiesLink(deputyRoleIndex);
    } catch (Exception e) {
      clickSelectedDeputiesLink(deputyRoleIndex);
    }
    for (String fullName : fullNames) {
      selectDeputy(fullName);
    }
    if (saveSelectedDeputies) {
      waitForElementClickableThenClick("[id='deputy-selection-form:save-deputy-button']");
//      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    }
  }

  public void waitForElementValueChanged(String cssSelector, String expectedValue) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT).until(ExpectedConditions.textToBe(By.cssSelector(cssSelector), expectedValue));
  }

  private void clickSelectedDeputiesLink(int deputyRoleIndex) {
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    String deputiesSelector = String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    $(deputiesSelector).shouldBe(appear, DEFAULT_TIMEOUT);
    waitForElementClickableThenClick(deputiesSelector);
    $(By.id("choose-deputy-dialog")).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void selectDeputy(String responsible) {
    $(By.id("deputy-selection-form:user-selection-component:user-selection_input")).sendKeys(responsible);
    $(By.id("deputy-selection-form:user-selection-component:user-selection_panel")).shouldBe(appear, DEFAULT_TIMEOUT);
    $(By.xpath("//*[@id='deputy-selection-form:user-selection-component:user-selection_panel']/table/tbody/tr")).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    waitForElementClickableThenClick("deputy-selection-form:add-deputy-button");
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public String getChooseDeputyDialogError() {
    String errorMessageDiv = "div[id$='deputy-selection-form:error-message']";
    return $(errorMessageDiv).shouldBe(appear, DEFAULT_TIMEOUT).$(".ui-messages-error-detail").getText();
  }

  public void setSubstitutedByAdmin(String substitutedUser) {
    String selectedUserInput = "input[id$=':user-absence-selection-component:user-absence_input']";
    WebElement substituted = $(selectedUserInput).shouldBe(appear, DEFAULT_TIMEOUT);
    substituted.clear();
    substituted.sendKeys(substitutedUser);
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    String itemSelector = "tr[data-item-label*='" + substitutedUser + "']";
    waitForElementClickableThenClick(itemSelector);
    $(itemSelector).shouldBe(appear, DEFAULT_TIMEOUT).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
//    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public String getSubstitutedByAdmin(int rowIndex) {
    WebElement deputyForTable = $("[id$=':substitution-table']");
    WebElement deputyFor = deputyForTable.findElements(By.cssSelector(".name-after-avatar")).get(rowIndex);
    return deputyFor.getText();
  }

  public void saveSubstitute() {
    waitForElementClickableThenClick("button[id$='absences-management-form:save-substitute']");
//    waitAjaxIndicatorDisappear();
  }

  public WebElement getAbsenceForm() {
    return $("[id$='absences-management-form']");
  }

  public WebElement getAddAbsenceDialog() {
    return $("[id$='absence-dialog']");
  }

  public void waitForAbsencesGrowlMessageDisplay() {
    WebElement growlMessage = $("div[id$='absences-management-form:absences-management-info_container']").shouldBe(appear, DEFAULT_TIMEOUT);
    $(growlMessage.findElement(By.className("ui-growl-item-container"))).shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public boolean canDeleteAbsence(int index) {
    return $(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index)).isDisplayed();
  }

  public boolean canEditAbsence(int index) {
    return $(String.format(EDIT_ABSENCE__LINK_ID_PATTERN, index)).isDisplayed();
  }

  public boolean isDeputySettingSectionDisplayed() {
    return $(By.id("deputy-setting")).isDisplayed();
  }
}
