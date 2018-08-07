package portal.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CasePage extends TemplatePage {
  private static final String CASE_NAME_CSS_SELECTOR = "div[id$='case-header-data-middle']";
  private final static String CASE_PAGE_LOCATION = "id('case-widget-form')";

  @Override
  protected String getLoadedLocator() {
    return CASE_PAGE_LOCATION;
  }

  public WebElement selectCaseItem(int index) {
    String caseItemId = String.format("case-widget:case-items:%s:case-item", index);
    return findElementById(caseItemId);
  }

  public boolean isCaseItemEmpty() {
    try {
      findElementByClassName("case-widget-empty-message");
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  private WebElement getDestroyButtonOfCaseItem(WebElement caseItem) {
    String caseItemId = caseItem.getAttribute("id");
    String destroyButtonId = String.format("%s:destroy-case", caseItemId);
    WebElement destroyButton = findElementById(destroyButtonId);
    findElementById(destroyButtonId);
    return destroyButton;
  }

  public void clickDestroyButton(WebElement caseItem) {
    WebElement destroyButton = getDestroyButtonOfCaseItem(caseItem);
    destroyButton.click();
  }

  public boolean isDestroyButtonVisible(WebElement caseItem) {
    try {
      getDestroyButtonOfCaseItem(caseItem);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public void confimDestruction() {
    String destroyCaseDialogId = "destroy-case-confirmation-dialog";
    waitForElementDisplayed(By.id(destroyCaseDialogId), true);
    WebElement destroyConfirmationDialog = findElementById(destroyCaseDialogId);
    WebElement confirmButton = findChildElementById(destroyConfirmationDialog, "confirm-destruction");
    confirmButton.click();
  }

  public CaseDetailsPage openDetailsOfCaseHasName(String caseName) {
    List<WebElement> caseItems = findListElementsByCssSelector("div[id$='case-item']");
    for (WebElement caseItem : caseItems) {
      if (caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).getText().equals(caseName)) {
        caseItem.findElement(By.cssSelector(CASE_NAME_CSS_SELECTOR)).click();
        caseItem = waitAjaxIndicatorDisappearAndReloadElement(caseItem);
        CaseDetailsPage detailsPage = new CaseDetailsPage(caseItem);
        return detailsPage;
      }
    }
    throw new NoSuchElementException("Cannot find details of case that has name " + caseName);
  }

  private WebElement waitAjaxIndicatorDisappearAndReloadElement(WebElement caseItem) {
    String caseItemId = caseItem.getAttribute("id");
    waitAjaxIndicatorDisappear();
    caseItem = findElementById(caseItemId);
    return caseItem;
  }
}
