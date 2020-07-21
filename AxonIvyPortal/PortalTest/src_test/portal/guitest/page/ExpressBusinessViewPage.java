package portal.guitest.page;

import java.util.List;
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
  
  public void clickOnLegendOfFieldset(int index) {
    click(getLegendFisnishedTaskFieldset(index));
  }
  
  public String getApprovalResultsText() {
    return findListElementsByCssSelector("div[id*='approval-result'] td").stream().map(WebElement::getText)
        .collect(Collectors.joining(","));
  }

  public Integer getIndexOfCurrentProcessChain() {
    WebElement processChainContainer = findElementByCssSelector("[id$='process-chain-component:process-chain-component-id']");
    List<WebElement> steps = processChainContainer.findElements(By.cssSelector("div[id^='process-chain-component:step-info-']"));
    for (WebElement step :steps) {
      if (step.getAttribute("class").contains("current")) {
        String stepId = step.getAttribute("id");
        return Integer.valueOf(stepId.replace("process-chain-component:step-info-", "").trim()).intValue();
      }
    }
    return null;
  }

}
