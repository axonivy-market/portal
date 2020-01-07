package portal.guitest.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskTemplateIFramePage;
import portal.guitest.page.TaskWidgetPage;

public class TaskTemplateIFrameTest extends BaseTest {

	@Override
	@Before
	public void setup() {
		super.setup();
		redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
	}

	private void createIFrameTaskLevel() {
		HomePage homePage = new HomePage();
		MainMenuPage mainMenuPage = homePage.openMainMenu();
		ProcessWidgetPage processWidgetPage = mainMenuPage.selectProcessesMenu();
		processWidgetPage.enterSearchKeyword("IFrame");
		processWidgetPage.startProcess("Create Investment (IFrame + Task custom fields)");
	}

	private TaskTemplateIFramePage inputDataForTaskTemplateIFrame() {
		TaskTemplateIFramePage taskTemplateIFramePage = new TaskTemplateIFramePage();
		taskTemplateIFramePage.switchToIFrame("iFrame");
		taskTemplateIFramePage.inputDataCreateInvestmentTask();
		return taskTemplateIFramePage;
	}

	@Ignore
	@Test
	public void testDeprecatedModenaTaskTemplate() {
		createIFrameTaskLevel();
		TaskTemplateIFramePage taskTemplateIFramePage = inputDataForTaskTemplateIFrame();
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		assertTrue("Verify iframe tag created", taskTemplateIFramePage.isIFrameTagDisplayed());
		taskTemplateIFramePage.switchToIFrame("iFrame");
		taskTemplateIFramePage.proceedDeprecateModenaTaskTemplate();
	}

	@Ignore
	@Test
	public void testDeprecatedTaskTemplate() {
		createIFrameTaskLevel();
		TaskTemplateIFramePage taskTemplateIFramePage = inputDataForTaskTemplateIFrame();
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startDeprecatedTaskTemplate(1);
		assertTrue("Verify no iframe tag created", taskTemplateIFramePage.isIFrameTagDisplayed() == false);
		taskTemplateIFramePage.proceedDeprecatedTaskTemplate();
	}
	
	@Ignore
	@Test
	public void testSerenityTaskTemplate() {
		createIFrameTaskLevel();
		TaskTemplateIFramePage taskTemplateIFramePage = inputDataForTaskTemplateIFrame();
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(2);
		assertTrue("Verify no iframe tag created", taskTemplateIFramePage.isIFrameTagDisplayed() == false);
		taskTemplateIFramePage.proceedSerenityTaskTemplate();
	}
	
	@Ignore
	@Test
	public void testTaskInIFrame() {
		createIFrameTaskLevel();
		TaskTemplateIFramePage taskTemplateIFramePage = inputDataForTaskTemplateIFrame();
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(3);
		assertTrue("Verify iframe tag created", taskTemplateIFramePage.isIFrameTagDisplayed());
		taskTemplateIFramePage.switchToIFrame("iFrame");
		taskTemplateIFramePage.approveInvestmentTask();
	}

	@Ignore
	@Test
	public void testCaseLevelInIFrame() {
		redirectToRelativeLink("internalSupport/14B2FC03D2E87141/IFrameInCaseLevel.ivp");
		TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
		taskWidgetPage.startTask(0);
		TaskTemplateIFramePage taskTemplateIFramePage = new TaskTemplateIFramePage();
		assertTrue("Verify iframe tag created", taskTemplateIFramePage.isIFrameTagDisplayed());
		taskTemplateIFramePage.switchToIFrame("iFrame");
		taskTemplateIFramePage.proceedTaskInCaseLevelIFrame();;
	}
}
