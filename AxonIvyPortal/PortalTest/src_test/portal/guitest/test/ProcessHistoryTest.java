package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.ProcessHistoryPage;

public class ProcessHistoryTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
  }

  @Test
  public void testDisplayProcessHistory() {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    assertEquals(10, processHistoryPage.countCases());
  }
  
  @Test
  public void testDisplayProcessHistoryDialog() {
    redirectToRelativeLink(createAlphaCompanyUrl);
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryInDialogUrl);
    
    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    assertEquals(10, processHistoryPage.openDialogAndCountCases());
  }
  
  @Test
  public void testDisplayEmptyMessage() {
    redirectToRelativeLink(viewAlphaCompanyProcessHistoryUrl);

    ProcessHistoryPage processHistoryPage = new ProcessHistoryPage();
    processHistoryPage.waitForPageLoaded();
    assertTrue(processHistoryPage.isEmptyMessageDisplay());
  }
}
