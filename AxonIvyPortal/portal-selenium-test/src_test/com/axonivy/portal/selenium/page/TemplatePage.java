package com.axonivy.portal.selenium.page;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public abstract class TemplatePage extends AbstractPage {

  // If page load more than 45s, mark it failed by timeout
  protected long getTimeOutForLocator() {
    return 45L;
  }

  protected Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  public void switchLastBrowserTab() {
    String oldTab = WebDriverRunner.getWebDriver().getWindowHandle();
    ArrayList<String> tabs = new ArrayList<String>(WebDriverRunner.getWebDriver().getWindowHandles());
    tabs.remove(oldTab);
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(0));
  }

  public void waitUntilElementToBeClickable(SelenideElement element) {
    new WebDriverWait(WebDriverRunner.getWebDriver(), DEFAULT_TIMEOUT)
        .until(ExpectedConditions.elementToBeClickable(element));
  }
}
