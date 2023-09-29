package portal.guitest.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.NavigationHelper;
import portal.guitest.common.Variable;
import portal.guitest.page.NewDashboardPage2;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.page.UserProfilePage;

public class DateTimeDisplayTest extends BaseTest {
  
  private static final String DATE_TIME_REGEX_PATTERN = "\\d{1,2} [a-zA-Z]+ \\d{4} \\d{2}:\\d{2}"; //Matched date: 6 July 2022 10:00
  private NewDashboardPage2 newDashboardPage2;
  
  @Override
  @Before
  public void setup(){
    super.setup();
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), "ACCESS_TASK_DETAILS");
    createTestingTasks();
    resetLanguageOfCurrentUser();
    newDashboardPage2 = new NewDashboardPage2();
    UserProfilePage userProfilePage = newDashboardPage2.openMyProfilePage();
    userProfilePage.inputFormattingLanguage("English (United Kingdom)");
    newDashboardPage2 = userProfilePage.save();
  }

  @Test
  public void testDisplayDateTime() {
    TaskWidgetPage taskWidget = NavigationHelper.navigateToTaskList();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetails(0);
    String createdDateLiteral = taskDetailsPage.getCreatedOnDateText();
    boolean matches = Pattern.matches(DATE_TIME_REGEX_PATTERN, createdDateLiteral);
    
    Assert.assertTrue(matches);
  }

}
