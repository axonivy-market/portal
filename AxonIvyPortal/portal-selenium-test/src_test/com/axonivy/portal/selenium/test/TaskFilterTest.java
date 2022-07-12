package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.DateTimePattern;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest(headless = false)
public class TaskFilterTest extends BaseTest {

  private static final String EMPTY = "";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testFilterTasksByCreatedDate() {
    redirectToRelativeLink(createTestingTasksUrl);
    login(TestAccount.ADMIN_USER);
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    taskWidgetPage.openAdvancedFilter("Created (from/to)", "created");
    String fromInputText = new SimpleDateFormat(DateTimePattern.DATE_PATTERN).format(new Date());
    taskWidgetPage.filterTasksByCreatedDate(fromInputText, EMPTY);
    taskWidgetPage.countTasks().shouldHave(sizeGreaterThanOrEqual(1));
  }

}
