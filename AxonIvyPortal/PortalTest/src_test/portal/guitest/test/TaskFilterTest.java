package portal.guitest.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
		taskWidgetPage.filterTasksBy("    Sick Leave Request Description");
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
		assertEquals("State: Created, Ready for joining, Suspended, In progress, Reserved", stateFilterValue);

		taskWidgetPage.openStateFilter();
		assertTrue(taskWidgetPage.getListStateFilterSelection().contains("Done"));
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
		assertEquals(filterName, taskWidgetPage.getFilterName());
	}

	@Test
	public void testSaveTaskFilterForAdmin() {
		List<String> adminStates = Arrays.asList("Ready for joining", "Suspended", "In progress", "Reserved", "Delayed", "Done", "Destroyed");
		login(TestAccount.ADMIN_USER);

		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

		String filterName = "For admins";
		taskWidgetPage.openAdvancedFilter("Description", "description");
		taskWidgetPage.filterByDescription("Maternity");

		WebElement saveFilterDialog = taskWidgetPage.openSaveFilterDialog();
		assertEquals("All users", saveFilterDialog.findElement(By.cssSelector("label[for='task-widget:filter-save-form:save-filter-type-radio:1']")).getText());
		taskWidgetPage.click(saveFilterDialog.findElement(By.cssSelector("a")));
		taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.filterByStates(adminStates);

    saveFilterDialog = taskWidgetPage.openSaveFilterDialog();
    assertEquals("All administrators", saveFilterDialog.findElement(By.cssSelector("label[for='task-widget:filter-save-form:save-filter-type-radio:1']")).getText());
    taskWidgetPage.click(saveFilterDialog.findElement(By.cssSelector("a")));
    taskWidgetPage.waitAjaxIndicatorDisappear();

    taskWidgetPage.saveAdminFilter(filterName);

    login(TestAccount.DEMO_USER);
    mainMenuPage = new MainMenuPage();
    taskWidgetPage = mainMenuPage.openTaskList();
    assertFalse(taskWidgetPage.isExistedFilter(filterName));
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

		assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));

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

		taskWidgetPage.clickOnTaskStatesAndApply(Arrays.asList("Unassigned"));
		assertEquals(1, taskWidgetPage.countTasks());
		assertEquals("OPEN (Unassigned)", taskWidgetPage.getTaskStateTooltip(0));
	}

  @Test
  public void testShowSystemStatesFilterForAdminUser() {
    List<String> adminStates = Arrays.asList("Created", "Ready for joining", "Suspended", "In progress", "Reserved",
        "Delayed", "Done", "Destroyed", "Failed", "Join failed", "Waiting for event");
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

    String stateFilterValue = taskWidgetPage.getFilterValue("state-filter");
    assertEquals("State: All", stateFilterValue);

    taskWidgetPage.openStateFilter();
    List<String> states = taskWidgetPage.getListStateFilterSelection();
    assertTrue(states.size() == adminStates.size());
    assertTrue(states.containsAll(adminStates));
  }

	@Test
	public void testNotShowUnassignedTaskToPersonNotHaveTaskReadAllPermission() {
		redirectToRelativeLink(createUnassignedTaskUrl);
		MainMenuPage mainMenuPage = new MainMenuPage();
		mainMenuPage.selectCaseMenu();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

		assertEquals(3, taskWidgetPage.countTasks());
		taskWidgetPage.openStateFilterOverlayPanel();
		
    assertFalse(taskWidgetPage.getListStateFilterSelection().contains("Unassigned"));
	}

	@Test
	public void testCategory() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();

		String taskCategoryId = "task-category";
		taskWidgetPage.openAdvancedFilter("Task category", taskCategoryId);
		assertEquals("Task category: All", taskWidgetPage.getFilterValue(taskCategoryId + "-filter"));
		taskWidgetPage.openCategoryFilter();
		assertTrue(taskWidgetPage.isAllCategoriesSelected());

		taskWidgetPage.toggleNoCategory();
		assertTrue(taskWidgetPage.isAllCategoriesUnselected());

		taskWidgetPage.applyCategoryFilter();
		assertFalse(StringUtils.equals("Task category: All", taskWidgetPage.getFilterValue(taskCategoryId + "-filter")));
	}

	@Test
	public void testRemoveResponsibleAndSwitchFilter() {
		// Prepare 2 filter
		String filterResponsible = "Responsible";
		String filterMaternity = "Maternity";
		
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
		taskWidgetPage.filterByDescription(filterMaternity);
		taskWidgetPage.saveFilter(filterMaternity);

		taskWidgetPage = mainMenuPage.openTaskList();
		taskWidgetPage.filterByResponsible("Everybody");
		taskWidgetPage.saveFilter(filterResponsible);
		// Switch filter and remove responsible
		taskWidgetPage.openSavedFilters(filterMaternity);
		taskWidgetPage.openSavedFilters(filterResponsible);
		taskWidgetPage.removeResponsibleFilter();
		taskWidgetPage.openSavedFilters(filterMaternity);
		taskWidgetPage.openSavedFilters(filterResponsible);
		
		assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
	}
	
	@Test
	public void testResponsibleWithChangeFilter() {
		// Prepare 2 filter
		String filterResponsible = "Responsible";
		String filterMaternity = "Maternity";
		
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
    taskWidgetPage.filterByResponsible("Everybody");
		taskWidgetPage.filterByDescription(filterMaternity);
		taskWidgetPage.saveFilter(filterMaternity);

		taskWidgetPage = mainMenuPage.openTaskList();
		taskWidgetPage.filterByResponsible("Demo");
		taskWidgetPage.saveFilter(filterResponsible);
		// Change filter and verify responsible changed
		taskWidgetPage.openSavedFilters(filterMaternity);

		assertTrue(taskWidgetPage.getResponsible().contains("Everybody"));
	}
	
	@Test
	public void testDefaultFilter() {
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
		assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
	}
	
	@Test
	public void testNoSelectionWhenChangeFilter() {
		String filterMaternity = "Maternity";
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
		taskWidgetPage.filterByDescription(filterMaternity);
		taskWidgetPage.saveFilter(filterMaternity);
		taskWidgetPage.filterByResponsible("Demo");
		
		assertTrue(taskWidgetPage.getFilterName().contains("No Selection"));
	}
	
	@Test
	public void testResetFilter() {
		String filterMaternity = "Maternity";
		MainMenuPage mainMenuPage = new MainMenuPage();
		TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Description", "description");
		taskWidgetPage.filterByDescription(filterMaternity);
		taskWidgetPage.saveFilter(filterMaternity);
		taskWidgetPage.filterByResponsible("Demo");
		taskWidgetPage.resetFilter();
		
		assertTrue(taskWidgetPage.getFilterName().contains("Default filter"));
	}

  @Test
  public void testCreatedDateFilter() {
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Created (from/to)", "created");
    String fromInputText = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
    taskWidgetPage.filterByCreatedDate(fromInputText);
    String expectedCreatedDateForm = taskWidgetPage.getCreatedDateFrom();
    assertTrue(expectedCreatedDateForm.contains(fromInputText));
  }
}
