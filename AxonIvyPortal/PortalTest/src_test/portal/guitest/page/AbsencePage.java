package portal.guitest.page;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

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
		waitForElementDisplayed(By.cssSelector(selector), true);
		clickByCssSelector(selector);
		waitUntilAnimationFinished(DEFAULT_TIMEOUT, "absence-form\\\\:absence-start-date_input", ID_PROPERTY);
		return new NewAbsencePage();
	}

	public int countAbsences() {
		waitForElementDisplayed(By.cssSelector("div[id*='absence-table']"), true);
		return findListElementsByCssSelector("td.absence-period").size();
	}

  public void showAbsencesInThePast(boolean shown) {
		WebElement checkBox = findElementByCssSelector("input[id*='show-absence-in-the-past']");
		boolean checkBoxSelected = checkBox.isSelected();
		if (checkBoxSelected != shown) {
			clickByCssSelector("div[id*='show-absence-in-the-past'] div.ui-chkbox-box");
			waitUntilAnimationFinished(DEFAULT_TIMEOUT,"ajax-indicator:ajax-indicator-ajax-indicator_start" , "id");
		}
	}

  public String getMyDeputy(int deputyRoleIndex) {
    String deputiesSelector = String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    waitForElementDisplayed(By.cssSelector(deputiesSelector), true);
    return findElementByCssSelector(deputiesSelector).getText();
  }
  
  public String getMyDisabledDeputy(int deputyRoleIndex) {
    String deputiesSelector = String.format("span[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    waitForElementDisplayed(By.cssSelector(deputiesSelector), true);
    return findElementByCssSelector(deputiesSelector).getText();
  }
  
	public List<String> getIAMDeputyFor() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector("tbody[id*='substitution-table_data'] > tr > td");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }

  public int indexOfDeputyRole(DeputyRoleType deputyRoleType) {
    String deputyRoleTypeSelector = ".substitute-table .substition-role-type";
    List<WebElement> elements = findListElementsByCssSelector(deputyRoleTypeSelector);
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
      click(By.id("deputy-selection-form:save-deputy-button"));
      waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    }
  }

  private void clickSelectedDeputiesLink(int deputyRoleIndex) {
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    String deputiesSelector = String.format("a[id$='absences-management-form:substitute-table:%d:selected-deputies-link']", deputyRoleIndex);
    waitForElementReallyDisplayed(By.cssSelector(deputiesSelector), true);
    Awaitility.await().atMost(new Duration(10, TimeUnit.SECONDS)).until(() -> {
      try {
        WebElement deputiesLink = findElementByCssSelector(deputiesSelector);
        deputiesLink.click();
      } catch (Exception e) {
        // to avoid choose-deputy-dialog displays before deputiesLink is clicked
      }
      try {
        return findElementById("choose-deputy-dialog").isDisplayed();
      } catch (Exception e) {
        return false;
      }
    });
  }

  private void selectDeputy(String responsible) {
    type(By.id("deputy-selection-form:user-selection-component:user-selection_input"), responsible);
    waitForElementDisplayed(By.id("deputy-selection-form:user-selection-component:user-selection_panel"), true);
    click(By.xpath("//*[@id='deputy-selection-form:user-selection-component:user-selection_panel']/table/tbody/tr"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
    click(By.id("deputy-selection-form:add-deputy-button"));
    waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
  }

  public String getChooseDeputyDialogError() {
    String errorMessageDiv = "div[id$='deputy-selection-form:error-message']";
    waitForElementDisplayed(By.cssSelector(errorMessageDiv), true);
    return findChildElementByCssSelector(findElementByCssSelector(errorMessageDiv), ".ui-messages-error-detail").getText();
  }

  public void setSubstitutedByAdmin(String substitutedUser) {
		String selectedUserInput = "input[id$=':user-absence-selection-component:user-absence_input']";
		waitForElementDisplayed(By.cssSelector(selectedUserInput), true);
		WebElement substituted = findElementByCssSelector(selectedUserInput);
		substituted.clear();
		substituted.sendKeys(substitutedUser);
		waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
		String itemSelector = "tr[data-item-label*='" + substitutedUser + "']";
		waitForElementDisplayed(By.cssSelector(itemSelector), true);
		clickByCssSelector(itemSelector);
		waitForJQueryAndPrimeFaces(DEFAULT_TIMEOUT);
	}
	
	public String getSubstitutedByAdmin(int rowIndex) {
	  WebElement deputyForTable = findElementByCssSelector("[id$=':substitution-table']");
	  WebElement deputyFor = deputyForTable.findElement(By.cssSelector(String.format("[id$='%d:substitution-for']", rowIndex)));
	  return deputyFor.getText();
	}
	
  @SuppressWarnings("deprecation")
  public void saveSubstitute() {
    clickByCssSelector("button[id$='absences-management-form:save-substitute']");
    waitAjaxIndicatorDisappear();
  }
  
  public WebElement getAbsenceForm() {
    return findElementById("absences-management-form");
  }

  public WebElement getAddAbsenceDialog() {
    return findElementById("absence-dialog");
  }
  
  public void waitForAbsencesGrowlMessageDisplay() {
    WebElement growlMessage = findElementByCssSelector("div[id$='absences-management-form:absences-management-info_container']");
    waitForElementDisplayed(growlMessage.findElement(By.className("ui-growl-item-container")), true, 5);
  }

  public boolean canDeleteAbsence(int index) {
    return findElementById(String.format(DELETE_ABSENCE__LINK_ID_PATTERN, index)).isDisplayed();
  }

  public boolean canEditAbsence(int index) {
    return findElementById(String.format(EDIT_ABSENCE__LINK_ID_PATTERN, index)).isDisplayed();
  }

  public boolean isDeputySettingSectionDisplayed() {
    waitUntilAnimationFinished(DEFAULT_TIMEOUT,"ajax-indicator:ajax-indicator-ajax-indicator_start" , "id");
    return isElementPresent(By.id("deputy-setting"));
  }
}
