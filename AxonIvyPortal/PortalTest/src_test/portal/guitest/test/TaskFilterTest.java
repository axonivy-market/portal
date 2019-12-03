package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.MainMenuPage;
import portal.guitest.page.TaskWidgetPage;

public class TaskFilterTest extends BaseTest {

  @Before
  @Override
  public void setup() {
    super.setup();
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }

  @Test
  public void testFilterTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(3, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
    taskWidgetPage.filterTasksBy("Sick Leave Request Description");
    assertEquals(1, taskWidgetPage.countTasks());
  }

  @Test
  public void testAdvancedFilterTask() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(3, taskWidgetPage.countTasks());

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");

    assertEquals(1, taskWidgetPage.countTasks());
  }
  
  @Test
  public void testShowDoneStateFilterForNormalUser() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(3, taskWidgetPage.countTasks());

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: Created, Suspended, In progress, Reserved", stateFilterValue);
    
    taskWidgetPage.openStateFilter();
    assertEquals("Done", taskWidgetPage.getStateFilterSelection(4));
  }
  
  @Test
  public void testKeepSessionFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Maternity");
    assertEquals(1, taskWidgetPage.countTasks());
    
    mainMenuPage.openCaseList();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(1, taskWidgetPage.countTasks());
    assertTrue(taskWidgetPage.isAdvancedFilterDisplayed("description"));
  }

  @Test
  public void testSaveTaskFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "Maternity";
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription(filterName);
    taskWidgetPage.saveFilter(filterName);

    mainMenuPage.selectCaseMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }

  @Test
  public void testSaveTaskFilterOnDifferentTaskList() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String filterName = "MyFilter";

    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByDescription("Sick");
    taskWidgetPage.saveFilter(filterName);

    redirectToRelativeLink(HomePage.PORTAL_EXAMPLES_HOME_PAGE_URL);
    taskWidgetPage = mainMenuPage.openTaskList();

    assertFalse(taskWidgetPage.isFilterSelectionVisible());

    taskWidgetPage.openAdvancedFilter("Customer name", "customer-name");
    taskWidgetPage.filterByCustomerName("Anh");
    taskWidgetPage.saveFilter(filterName);

    mainMenuPage.selectCaseMenu();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(filterName, taskWidgetPage.getFilterName());
  }
  
  @Test
  public void testShowUnassignedTaskToPersonHaveTaskReadAllPermission() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createUnassignedTaskUrl);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.selectCaseMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertEquals(6, taskWidgetPage.countTasks());
    
    taskWidgetPage.openStateFilterOverlayPanel();
    assertEquals("Created,Suspended,In progress,Reserved,Done,Unassigned", taskWidgetPage.getDisplayStateInStateFilter());
    
    taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Created","Suspended","In progress","Reserved","Done"));
    assertEquals(1, taskWidgetPage.countTasks());
    assertEquals("OPEN (Unassigned)", taskWidgetPage.getTaskStateTooltip(0));
  }
  
  @Test
  public void testNotShowUnassignedTaskToPersonNotHaveTaskReadAllPermission() {
    redirectToRelativeLink(createUnassignedTaskUrl);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.selectCaseMenu();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    
    assertEquals(3, taskWidgetPage.countTasks());
    taskWidgetPage.openStateFilterOverlayPanel();
    assertEquals("Created,Suspended,In progress,Reserved,Done", taskWidgetPage.getDisplayStateInStateFilter());
    
  }
}
