package portal.guitest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.ProcessChainPage;

public class ProcessChainTest extends BaseTest {

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(processChainShowcaseUrl);
  }

  @Test
  public void testDisplayProcessChain() {    
    ProcessChainPage processChainPage = new ProcessChainPage();
    processChainPage.waitForPageLoaded();
    assertTrue(processChainPage.isEmptyNextStepButtonDisplay());
  }
  
  @Test
  public void testNextStepActionProcessChain() {
    ProcessChainPage processChainPage = new ProcessChainPage();
    processChainPage.waitForPageLoaded();
    assertEquals("Step 3", processChainPage.getCurrentStep());
    processChainPage.nextStep();
    assertEquals("Step 4", processChainPage.getCurrentStep());
  }
}
