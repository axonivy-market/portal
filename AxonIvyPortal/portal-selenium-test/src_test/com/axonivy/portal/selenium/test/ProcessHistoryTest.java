package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.ProcessHistoryPage;

@IvyWebTest
public class ProcessHistoryTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testDisplayProcessHistory() {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitPageLoaded();
    assertEquals(1, processHistoryPage.countCases());
  }

  @Test
  public void testDisplayProcessHistoryDialog() {
    redirectToRelativeLink(createBetaCompanyUrl);
    redirectToRelativeLink(viewBetaCompanyProcessHistoryInDialogUrl);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitPageLoaded();
    assertEquals(1, processHistoryPage.openDialogAndCountCases());
  }

  @Test
  public void testDisplayEmptyMessage() {
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitPageLoaded();
    assertTrue(processHistoryPage.isEmptyMessageDisplay());
  }
}
