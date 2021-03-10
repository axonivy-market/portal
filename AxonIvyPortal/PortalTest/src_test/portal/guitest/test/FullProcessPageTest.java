package portal.guitest.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.ProcessWidgetPage.AddNewExternalLinkDialog;

public class FullProcessPageTest extends BaseTest{
  
  private static final String AAGOOGLE_LINK = "AAGoogle";
  protected HomePage homePage;
  protected ProcessWidgetPage processWidgetPage;
  
  @Override
  @Before
  public void setup() {
    super.setup();
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
  }
  
  @Test
  public void testFindProcess() {
    processWidgetPage.enterSearchKeyword("Alpha Company");
    assertTrue(processWidgetPage.isProcessDisplay("Create Alpha Company"));
    assertTrue(processWidgetPage.isProcessGroupDisplay("C"));
    processWidgetPage.enterSearchKeyword("random keyword never match with any process");
    assertTrue(processWidgetPage.isNoProcessFound());
    assertFalse(processWidgetPage.isProcessGroupDisplay("A"));
  }
  
  @Test
  public void testChangeProcessViewMode() {
    String currentView = processWidgetPage.getCurrentViewMode();
    processWidgetPage.clickOnSwitchButton();
    String newView = processWidgetPage.getCurrentViewMode();
    assertFalse("Current view is not changed", StringUtils.equals(currentView, newView));
  }
  
  @Test
  public void testEditDeleteProcessIcon() {
    login(TestAccount.ADMIN_USER);
    homePage = new HomePage();
    MainMenuPage mainMenuPage = homePage.openMainMenu();
    processWidgetPage = mainMenuPage.selectProcessesMenu();
    createExternalTestProcess(AAGOOGLE_LINK, AAGOOGLE_LINK, false);
    processWidgetPage.enterSearchKeyword("link");
    String currentIcon = processWidgetPage.getProcessItemIcon(0);
    processWidgetPage.clickOnProcessEditLink(0);
    processWidgetPage.changeProcessIcon();
    processWidgetPage.enterSearchKeyword("link");
    String newIcon = processWidgetPage.getProcessItemIcon(0);
    assertFalse("Current Icon is not changed", StringUtils.equals(currentIcon, newIcon));
    
    processWidgetPage.deleteProcess(0);
    processWidgetPage.enterSearchKeyword("link");
    assertTrue("Still see processes", processWidgetPage.isNoProcessFound());
  }
  
  private void createExternalTestProcess(String processName, String processLink, boolean isPublic) {
    AddNewExternalLinkDialog addNewExternalLinkDialog = processWidgetPage.openNewExternalLinkDialog();
    addNewExternalLinkDialog.inputDataForExternalLink(processName, processLink, isPublic);
    addNewExternalLinkDialog.submitForm();
  }
}
