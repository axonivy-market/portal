package portal.guitest.page;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import portal.guitest.common.WaitHelper;

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
		waitForElementDisplayed(By.cssSelector("input[id*='substitute-username_input']"), true);
		return findElementByCssSelector("input[id*='substitute-username_input']").getAttribute("value");
	}
  
	public List<String> getIAMDeputyFor() {
    List<WebElement> noteAuthorElements = findListElementsByCssSelector("tbody[id*='substitution-table_data'] > tr > td");
    return noteAuthorElements.stream().map(w -> w.getText()).collect(Collectors.toList());
  }
  
	public void setDeputy(String fullName) {
		String usernameSelector = "input[id$='substitute-username_input']";
		waitForElementPresent(By.cssSelector(usernameSelector), true);
		WebElement usernameInput = findElementByCssSelector(usernameSelector);
		//We have javascript behavior to clear input when the text is No deputy,
		//so strange behavior when clear this input => need to find it again because DOM is changed in background
		if(StringUtils.isEmpty(usernameInput.getAttribute("value")) || "No deputy".equals(usernameInput.getAttribute("value"))) {
		  usernameInput.click();
		}
		else {
		  usernameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		}
    WaitHelper.retryAction(() -> {
      WebElement input = findElementByCssSelector(usernameSelector);
      input.clear();
      clickByCssSelector(usernameSelector);
      input.sendKeys(fullName);
    });

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
	
	public String getSubstitutedByAdmin() {
	  return findElementByCssSelector("input[id$='substituted-user_input']").getAttribute("value");
	}
	
  public void saveSubstitute() {
    clickByCssSelector("button[id*='absence-management:save-substitute']");
    waitAjaxIndicatorDisappear();
  }
  
  public WebElement getAbsenceDialog() {
    return findElementById("absence-management-dialog");
  }
}
