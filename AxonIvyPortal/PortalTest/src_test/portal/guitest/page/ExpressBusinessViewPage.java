package portal.guitest.page;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ExpressBusinessViewPage extends TaskTemplatePage {

  @Override
  protected String getLoadedLocator() {
    return "id('express-view-form')";
  }

  public WebElement getEmptyFinishedTask() {
    return findElementByCssSelector("[id$='express-view-form:empty-finished-tasks-container']");
  }
  
  public String getEmptyFinishedTaskMessage() {
    WebElement message = getEmptyFinishedTask().findElement(By.className("no-item-header"));
    return message.getText();
  }

  @SuppressWarnings("deprecation")
  public HomePage clickOnCloseButton() {
    click(findElementByCssSelector("[id$='express-view-form:cancel-btn']"));
    return new HomePage();
  }
  
  public WebElement getLegendFisnishedTaskFieldset(int index) {
    return findElementByCssSelector(String.format("span[id$=':finished-tasks-component:approval-result:%d:finished-task-fieldset-legend']", index));
  }
  
  public String getTextOfLegendFinishedTask(int index) {
    return getLegendFisnishedTaskFieldset(index).getText();
  }
  
  public String getTextOfLegendApprovalResult(int index) {
    WebElement approvalResult = findElementByCssSelector(String.format("span[id$=':approval-result-container:0:approval-result-fieldset-legend']", index));
    return approvalResult.getText();
  }
  
  @SuppressWarnings("deprecation")
  public void clickOnLegendOfFieldset(int index) {
    click(getLegendFisnishedTaskFieldset(index));
  }
  
  public String getApprovalResultsText() {
    WebElement approvalTable = findElementByClassName("approval-result-content");
    return approvalTable.findElements(By.cssSelector("tbody[id$=':approval-result-table_data'] td")).stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }

  public Integer getIndexOfCurrentProcessChain() {
    waitForElementDisplayed(By.cssSelector("div.process-chain-container.vertical-chain-shape-line"), true);
    waitUntilAnimationFinished(DEFAULT_TIMEOUT, "line-horizontal-step-container", CLASS_PROPERTY);
    WebElement processChainContainer = findElementByCssSelector("[id$='process-chain-component:process-chain-component-id']");
    for (var step : processChainContainer.findElements(By.cssSelector("div[id^='process-chain-component:step-info-']"))) {
      if (step.getAttribute(CLASS_PROPERTY).contains("current")) {
        String stepId = step.getAttribute("id");
        return Integer.valueOf(stepId.replace("process-chain-component:step-info-", "").trim()).intValue();
      }
    }
    return 0;
  }

}
