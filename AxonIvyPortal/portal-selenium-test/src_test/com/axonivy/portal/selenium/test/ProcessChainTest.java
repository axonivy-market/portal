package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.ProcessChainPage;

@IvyWebTest
public class ProcessChainTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(processChainShowcaseUrl);
  }

  @Test
  public void testDisplayProcessChain() {
    ProcessChainPage processChainPage = new ProcessChainPage();
    processChainPage.waitPageLoaded();
    assertTrue(processChainPage.isEmptyNextStepButtonDisplay());
  }

  @Test
  public void testNextStepActionProcessChain() {
    ProcessChainPage processChainPage = new ProcessChainPage();
    processChainPage.waitPageLoaded();
    assertEquals("Step 3", processChainPage.getCurrentStep());
    processChainPage.nextStep();
    assertEquals("Step 4", processChainPage.getCurrentStep());
  }
}
