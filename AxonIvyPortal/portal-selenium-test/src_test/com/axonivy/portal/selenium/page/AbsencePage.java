package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.WebElement;

import com.codeborne.selenide.SelenideElement;

import ch.ivy.addon.portalkit.enums.DeputyRoleType;

public class AbsencePage extends TemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "[id='absences-management-form']";
  }

  public NewAbsencePage openNewAbsenceDialog() {
    $("button[id*='add-absence']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    getAddAbsenceDialog();
    return new NewAbsencePage();
  }

  public WebElement getAddAbsenceDialog() {
    return $("[id='absence-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  public WebElement getAbsenceForm() {
    return $("[id='absences-management-form']").shouldBe(appear, DEFAULT_TIMEOUT);
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
      $("[id='deputy-selection-form:save-deputy-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
      waitForAjaxIndicatorDisappeared();
    }
  }

  public int indexOfDeputyRole(DeputyRoleType deputyRoleType) {
    List<WebElement> elements = $$(".substitute-table .substition-role-type").asFixedIterable().stream().collect(Collectors.toList());
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

  private void clickSelectedDeputiesLink(int deputyRoleIndex) {
    $(String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex)).shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    $("[id='choose-deputy-dialog']").shouldBe(appear, DEFAULT_TIMEOUT);
  }

  private void selectDeputy(String responsible) {
    SelenideElement deputyInput = $("[id='deputy-selection-form:user-selection-component:user-selection_input']").shouldBe(appear, DEFAULT_TIMEOUT);
    deputyInput.clear();
    deputyInput.sendKeys(responsible);
    $("[id='deputy-selection-form:user-selection-component:user-selection_panel'] table tbody tr").shouldBe(appear, DEFAULT_TIMEOUT).click();
    $("[id='deputy-selection-form:add-deputy-button']").shouldBe(getClickableCondition(), DEFAULT_TIMEOUT).click();
    waitForAjaxIndicatorDisappeared();
  }
}
