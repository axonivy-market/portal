package com.axonivy.portal.selenium.test.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.CollectionCondition;

@IvyWebTest
public class TaskFilterTest extends BaseTest {

  private static final String EMPTY = "";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testFilterTasksByCreatedDate() {
    TaskWidgetPage taskWidgetPage = createTestData();
    taskWidgetPage.openAdvancedFilter("Created (from/to)", "created");
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    taskWidgetPage.filterTasksByCreatedDate(fromInputText, EMPTY);
    taskWidgetPage.countTasks().shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
  }

  @Test
  public void testFilterTasksByApplication() {
    TaskWidgetPage taskWidgetPage = createTestData();
    int before = taskWidgetPage.countTasks().size();
    taskWidgetPage.openAdvancedFilter("Application", "application");
    taskWidgetPage.filterFirstApp();
    int after = taskWidgetPage.countTasks().size();
    Assertions.assertEquals(before, after);
  }

  private TaskWidgetPage createTestData() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    mainMenuPage.waitForGrowlMessageDisappear();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    return taskWidgetPage;
  }

}
