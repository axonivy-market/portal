package portal.guitest.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.Variable;
import portal.guitest.page.HomePage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class DateTimeDisplayTest extends BaseTest {
  
  private static final String DATE_TIME_REGEX_PATTERN = "^\\D{3}\\s\\d{1,2},\\s\\d{4}\\s\\d{1,2}:\\d{1,2}$"; //Matched date: May 19, 2021 15:00
  private HomePage homePage;
  
  @Override
  @Before
  public void setup(){
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    homePage = new HomePage();
  }

  @Test
  public void testDisplayDateTime() {
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    taskWidget.waitForPageLoaded();
    taskWidget.expand();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetails(0);
    String createdDateLiteral = taskDetailsPage.getCreatedOnDateText();
    boolean matches = Pattern.matches(DATE_TIME_REGEX_PATTERN, createdDateLiteral);
    Assert.assertTrue(matches);
  }

}
