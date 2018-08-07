package portal.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.common.BaseTest;
import portal.common.TestAccount;
import portal.page.HomePage;
import portal.page.LoginPage;
import portal.page.TaskDetailsPage;
import portal.page.TaskWidgetPage;

public class DateTimeDisplayTest extends BaseTest {
  
  private static final String DATE_TIME_REGEX_PATTERN = "\\w{3} \\d{1,2}, \\d{4} \\d{2}:\\d{2}";
  private HomePage homePage;
  
  @Before
  public void setup(){
    super.setup();
    createTestingTasks();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);

    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();

    homePage = new HomePage();
  }

  @Test
  public void testDisplayDateTime() {
    TaskWidgetPage taskWidget = homePage.getTaskWidget();
    taskWidget.expand();
    taskWidget.openTaskDetails(0);
    TaskDetailsPage taskDetailsPage = taskWidget.getTaskDetailsElement(0);
    String createdDateLiteral = taskDetailsPage.getCreatedOnDateText();
    boolean matches = Pattern.matches(DATE_TIME_REGEX_PATTERN, createdDateLiteral);
    Assert.assertTrue(matches);
  }

}
