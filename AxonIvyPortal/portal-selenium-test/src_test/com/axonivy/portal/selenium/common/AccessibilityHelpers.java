package com.axonivy.portal.selenium.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

public class AccessibilityHelpers {
  private static final List<String> WCAG_TAGS = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa", "best-practice");

  public static void makeA11yReport() {
    WebDriver driver = WebDriverRunner.getWebDriver();

    Results results = new AxeBuilder()
        .withTags(WCAG_TAGS)
        .analyze(driver);

    // Strip passed and inapplicable results to prevent bloated JSON sizes
    results.setPasses(Collections.emptyList());
    results.setInapplicable(Collections.emptyList());

    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    AxeReporter.writeResultsToJsonFile("target/axe-results-" + methodName, results);
  }

  public static void makeElementA11yReport(SelenideElement targetElement, String reportName) {
    WebDriver driver = WebDriverRunner.getWebDriver();

    Results results = new AxeBuilder()
        .withTags(WCAG_TAGS)
        .analyze(driver, targetElement); 

    results.setPasses(Collections.emptyList());
    results.setInapplicable(Collections.emptyList());

    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    AxeReporter.writeResultsToJsonFile("target/axe-results-" + methodName + "-" + reportName, results);
}
}
