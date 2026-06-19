package com.axonivy.portal.selenium.a11y;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.WebDriverRunner;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

public class A11yHelpers {
  private static final List<String> WCAG_TAGS = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa", "best-practice");

  public static void assertA11y_noViolations() {
    WebDriver driver = WebDriverRunner.getWebDriver();

    Results results = new AxeBuilder()
        .withTags(WCAG_TAGS)
        .analyze(driver);

    // Strip passed and inapplicable results to prevent bloated JSON sizes
    results.setPasses(Collections.emptyList());
    results.setInapplicable(Collections.emptyList());

    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
    AxeReporter.writeResultsToJsonFile("target/axe-results-" + methodName, results);

    List<Rule> violations = results.getViolations();
    if (!violations.isEmpty()) {
      String summary = violations.stream()
          .map(v -> String.format("[%s] impact=%s nodes=%d — %s (%s)",
              v.getId(),
              v.getImpact(),
              v.getNodes().size(),
              v.getHelp(),
              v.getHelpUrl()))
          .collect(Collectors.joining("\n  "));
      assertThat(violations)
          .as("axe-core violations on <%s>:\n  %s", driver.getCurrentUrl(), summary)
          .isEmpty();
    }
  }
}
