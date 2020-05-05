package portal.guitest.test;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.CaseWidgetPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class RoleInvolvedTest extends BaseTest {

  private static final String ROLE_INVOLVED_URL = "internalSupport/171E2BB0DB49C362/roleInvolved.ivp";
  
  private HomePage homePage;
  private MainMenuPage mainMenuPage;
  private CaseWidgetPage caseWidget;
  private TaskWidgetPage taskWidget;

  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(ROLE_INVOLVED_URL);
    login(TestAccount.ROLE_INVOLVED_USER_1);
    homePage = new HomePage();
    taskWidget = homePage.getTaskWidget();
    taskWidget.startTaskWithoutUI(0);
    login(TestAccount.ROLE_INVOLVED_USER_2);
  }
  
  @Test
  public void testForTask() {
    taskWidget = homePage.getTaskWidget();
    Assert.assertEquals(0, taskWidget.countTasks());
    mainMenuPage = homePage.openMainMenu();
    taskWidget = mainMenuPage.openTaskList();
    taskWidget.openStateFilterOverlayPanel();
    taskWidget.clickOnTaskStatesAndApply(Arrays.asList("Done"));
    Assert.assertEquals("Task for role involved", taskWidget.getNameOfTaskAt(0));
  }

  @Test
  public void testForCase() {
    mainMenuPage = homePage.openMainMenu();
    caseWidget = mainMenuPage.openCaseList();
    Assert.assertEquals("Test Process: role involved", caseWidget.getCaseNameAt(0));
  }
}
