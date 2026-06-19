package com.axonivy.portal.selenium.a11y;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.PortalGUITestException;
import com.axonivy.portal.selenium.common.UrlHelpers;
import com.codeborne.selenide.WebDriverRunner;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

import ch.ivy.addon.portalkit.enums.PortalVariable;

public class A11yExtension implements AfterEachCallback, BeforeEachCallback {
  private static final List<String> WCAG_TAGS = Arrays.asList("wcag2a", "wcag2aa", "wcag21aa", "best-practice");
  private String cleanupDataLink = "portalKitTestHelper/1511A66AF619A768/cleanData.ivp";
  private String createJSonFileUrl = "PortalKitTestHelper/153CACC26D0D4C3D/createJSonFile.ivp?filePath=%s&key=%s";

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
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

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    launchBrowserAndGotoRelativeLink(cleanupDataLink);
    createJSonFile("default-dashboard.json", PortalVariable.DASHBOARD.key);
  }
  
  public void launchBrowserAndGotoRelativeLink(String relativeProcessStartLink) {
    try {
      open(UrlHelpers.generateAbsoluteProcessStartLink(relativeProcessStartLink));
    } catch (Exception e) {
      throw new PortalGUITestException(e);
    }
  }

   public void createJSonFile(String jsonFile, String key) {
    var path = FileHelper.getAbsolutePathToTestFile(jsonFile);
    String filepath = "";
    try {
      filepath = URLEncoder.encode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    LinkNavigator.redirectToRelativeLink(String.format(createJSonFileUrl, filepath, key));
  }
}
