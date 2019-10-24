package portal.guitest.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.TaskDetailsPage;
import portal.guitest.page.TaskWidgetPage;

public class DateTimeDisplayTest extends BaseTest {
  
  private static final String DATE_TIME_REGEX_PATTERN = "\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{2}:\\d{2}";
  private HomePage homePage;
  
  @Override
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
    taskWidget.waitForPageLoaded();
    taskWidget.expand();
    TaskDetailsPage taskDetailsPage = taskWidget.openTaskDetails(0);
    String createdDateLiteral = taskDetailsPage.getCreatedOnDateText();
    boolean matches = Pattern.matches(DATE_TIME_REGEX_PATTERN, createdDateLiteral);
    Assert.assertTrue(matches);
  }

}
