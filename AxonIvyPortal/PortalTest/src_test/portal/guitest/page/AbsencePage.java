package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AbsencePage extends TemplatePage {

	@Override
	protected String getLoadedLocator() {
		return "id('absence-management-dialog_title')";
	}

	public NewAbsencePage openNewAbsenceDialog() {
		String selector = "button[id*='add-absence']";
		waitForElementDisplayed(By.cssSelector(selector), true);
		clickByCssSelector(selector);
		waitAjaxIndicatorDisappear();
		return new NewAbsencePage();
	}

	public int countAbsences() {
		waitForElementDisplayed(By.cssSelector("a[id*='absence-table']"), true);
		return findListElementsByCssSelector("td.absences-table-action-column").size();
	}

	public void showAbsencesInThePast(boolean shown) {
		WebElement checkBox = findElementByCssSelector("input[id*='show-absence-in-the-past']");
		boolean checkBoxSelected = checkBox.isSelected();
		if (checkBoxSelected != shown) {
			clickByCssSelector("div[id*='show-absence-in-the-past'] div.ui-chkbox-box");
			waitAjaxIndicatorDisappear();
		}
	}

	public String getMyDeputy() {
		waitForElementDisplayed(By.cssSelector("input[id*='subsitute-username_input']"), true);
		return findElementByCssSelector("input[id*='subsitute-username_input']").getAttribute("value");
	}
  
	public List<String> getIAMDeputyFor() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector("tbody[id*='substitution-table_data'] > tr > td");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }
  
	public void setDeputy(String fullName) {
		String usernameSelector = "input[id$='subsitute-username_input']";
		waitForElementDisplayed(By.cssSelector(usernameSelector), true);
		WebElement usernameInput = findElementByCssSelector(usernameSelector);
		usernameInput.clear();
		usernameInput.sendKeys(fullName);
		waitAjaxIndicatorDisappear();
		String itemSelector = "tr[data-item-label*='" + fullName + "']";
		waitForElementDisplayed(By.cssSelector(itemSelector), true);
		clickByCssSelector(itemSelector);
		waitAjaxIndicatorDisappear();
	}
	
	public void setSubstitutedByAdmin(String substitutedUser) {
		String substitutedUserInput = "input[id$='substituted-user_input']";
		waitForElementDisplayed(By.cssSelector(substitutedUserInput), true);
		WebElement substituted = findElementByCssSelector(substitutedUserInput);
		substituted.clear();
		substituted.sendKeys(substitutedUser);
		waitAjaxIndicatorDisappear();
		String itemSelector = "tr[data-item-label*='" + substitutedUser + "']";
		waitForElementDisplayed(By.cssSelector(itemSelector), true);
		clickByCssSelector(itemSelector);
		waitAjaxIndicatorDisappear();
	}
	
  public void saveSubstitute() {
    clickByCssSelector("button[id*='absence-management:save-substitute']");
    waitAjaxIndicatorDisappear();
  }
}
