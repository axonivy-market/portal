package portal.guitest.test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.Condition;
import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;

import portal.guitest.common.BaseTest;
import portal.guitest.common.PortalGUITestException;
import portal.guitest.common.TestAccount;
import portal.guitest.page.NewDashBoardPage;

/**
 * This sample WebTest orchestrates a real browser to verify that your workflow application and especially it's Html
 * Dialogs are running as expected.
 * 
 * <p>
 * The test can either be run
 * <ul>
 * <li>in the Designer IDE ( <code>right click > run as > JUnit Test </code> )</li>
 * <li>or in a Maven continuous integration build pipeline ( <code>mvn clean verify</code> )</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Detailed guidance on writing these kind of tests can be found in our
 * <a href="https://developer.axonivy.com/doc/dev/concepts/testing/web-testing.html">WebTesting docs</a>
 * </p>
 */
@IvyWebTest(headless = false)
public class NewDashboardCaseWidgetTest extends BaseTest {

  // URL
  private final static String NEW_DASHBOARD_URL_PATTERN = "/PortalTemplate/17065D04AF6FF0C0/dashboard.ivp";
  private final static String LOGIN_URL_PATTERN =
      "/PortalKitTestHelper/1636734E13CEC872/login.ivp?username=%s&password=%s";

  // WIDGET
  private static final String YOUR_CASES_WIDGET = "Your Cases";
  private static final String YOUR_TASKS_WIDGET = "Your Tasks";

  // CASES
  private static final String LEAVE_REQUEST_CASE_NAME = "Leave Request";
  private static final String ORDER_PIZZA = "Order Pizza";
  private static final String HIDE_CASE = "Repair Computer";
  private static final String LEAVE_REQUEST_DEFAULT_CASE= "Leave Request for Default Additional Case Details";
  private static final String INVESTMENT_REQUEST_CUSTOMIZATION_CASE = "Investment Request";
  private static final String CREATE_12_CASES_WITH_CATEGORY_CASE = "Create 12 Cases with category";

  // TASKS
  private static final String REPORT_HIDE_CASE = "Report and hide case";
  
  //FILTER
  private static final String FILTER_TASK_NAME = "Task name";
  private static final String FILTER_CASE_NAME = "Case name";
  private static final String FILTER_CASE_STATE = "State";

  private NewDashBoardPage newDashBoardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashBoardPage = new NewDashBoardPage();
    createTestingTasks();
  }

  private void openNewDashBoard() {
    open(EngineUrl.createProcessUrl(NEW_DASHBOARD_URL_PATTERN));
  }

  private Condition getClickableCondition() {
    return and("should be clickable", visible, exist);
  }

  protected void login(TestAccount testAccount) {
    String username;
    String password;
    try {
      username = URLEncoder.encode(testAccount.getUsername(), "UTF-8");
      password = URLEncoder.encode(testAccount.getPassword(), "UTF-8");
      open(EngineUrl.createProcessUrl(String.format(LOGIN_URL_PATTERN, username, password)));
    } catch (UnsupportedEncodingException e) {
      throw new PortalGUITestException(e);
    }
  }

  @Test
  public void testHideCase() {
    redirectToRelativeLink(hideCaseUrl);
    login(TestAccount.ADMIN_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_TASKS_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getFilterWidget(YOUR_TASKS_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getFilterInput(YOUR_TASKS_WIDGET,FILTER_TASK_NAME).sendKeys(REPORT_HIDE_CASE);
    newDashBoardPage.getApplyButtonFilter(YOUR_TASKS_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getTaskOfTaskWidgetByIndex(0,"Start").shouldBe(getClickableCondition()).click();
    openNewDashBoard();
    newDashBoardPage.getCasesOfCaseWidgetHasName(HIDE_CASE).shouldHaveSize(0);
  }

  @Test
  public void testDestroyCaseWithPermission() {
    login(TestAccount.ADMIN_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getDestroyLink().shouldBe(getClickableCondition()).click();
    newDashBoardPage.getConfirmDestroyButton().shouldBe(getClickableCondition()).click();
    openNewDashBoard();
    newDashBoardPage.getColumnOfCaseHasIndex(0,"state").shouldHave(text("Destroyed"));
  }

  @Test
  public void testDestroyCaseWithoutPermission() {
    login(TestAccount.DEMO_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getDestroyLink().shouldNotHave(visible);
  }

  @Test
  public void testOpenRelatedTasksOfCase() {
    login(TestAccount.DEMO_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getCasesOfCaseWidgetHasName(LEAVE_REQUEST_CASE_NAME).first().shouldBe(getClickableCondition())
        .click();
    newDashBoardPage.countRelatedTasks().shouldHaveSize(4);
  }

  @Test
  public void testOpenRelatedCasesOfCase() {
    redirectToRelativeLink(createCaseWithTechnicalCaseUrl);
    login(TestAccount.DEMO_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getCasesOfCaseWidgetHasName(ORDER_PIZZA).first().shouldBe(getClickableCondition()).click();
    newDashBoardPage.countRelatedCases().shouldHaveSize(1);
  }
  
  @Test
  public void testOpenAdditionalCaseDetailsPage() {
    redirectToRelativeLink(createTestingCaseUrlForDefaultAdditionalCaseDetails);
    login(TestAccount.ADMIN_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getFilterWidget(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getFilterInput(YOUR_CASES_WIDGET, FILTER_CASE_NAME)
        .sendKeys(LEAVE_REQUEST_DEFAULT_CASE);
    newDashBoardPage.getApplyButtonFilter(YOUR_TASKS_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getAdditionalCaseDetailsPage().shouldBe(getClickableCondition()).click();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> newDashBoardPage.countBrowserTab() > 1);
    newDashBoardPage.switchLastBrowserTab();
    newDashBoardPage.getAdditionalFieldsPage().shouldHaveSize(15);
    newDashBoardPage.getAdditionalFieldsPage().first().shouldBe(text("CustomVarCharField 1"));
  }
  
  @Test
  public void testOpenCustomizationAdditionalCaseDetailsPage() {
    redirectToRelativeLink(createTestingCaseUrlForCustomizationAdditionalCaseDetails);
    login(TestAccount.ADMIN_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));
    newDashBoardPage.getFilterWidget(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getFilterInput(YOUR_CASES_WIDGET, FILTER_CASE_NAME)
        .sendKeys(INVESTMENT_REQUEST_CUSTOMIZATION_CASE);
    newDashBoardPage.getApplyButtonFilter(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getCaseOfCaseWidgetHasIndex(0).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getAdditionalCaseDetailsPage().shouldBe(getClickableCondition()).click();
    Awaitility.await().atMost(new Duration(5, TimeUnit.SECONDS)).until(() -> newDashBoardPage.countBrowserTab() > 1);
    newDashBoardPage.switchLastBrowserTab();
    newDashBoardPage.getAdditionalFieldsPage().shouldHaveSize(7);
    newDashBoardPage.getAdditionalFieldsPage().first().shouldBe(text("Apartment A"));
  }
  
  @Test
  public void testStickyFilterCaseList() {
    redirectToRelativeLink(create12CasesWithCategoryUrl);
    login(TestAccount.DEMO_USER);
    openNewDashBoard();
    newDashBoardPage.getWidget(YOUR_CASES_WIDGET).shouldHave(sizeGreaterThanOrEqual(1));   
    //Filter Case Name
    newDashBoardPage.getFilterWidget(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getFilterInput(YOUR_CASES_WIDGET, FILTER_CASE_NAME)
        .sendKeys("TestCase");
    newDashBoardPage.getApplyButtonFilter(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getCasesOfCaseWidgetHasName("TestCase").shouldHaveSize(10);
    newDashBoardPage.getNextPageWidget(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getCasesOfCaseWidgetHasName("TestCase").shouldHaveSize(2);   
    //Filter State
    newDashBoardPage.getFilterWidget(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getFilterInput(YOUR_CASES_WIDGET, FILTER_CASE_NAME).clear();
    newDashBoardPage.getFilterInput(YOUR_CASES_WIDGET, FILTER_CASE_NAME).sendKeys(CREATE_12_CASES_WITH_CATEGORY_CASE);
    newDashBoardPage.getFilterCheckBox(YOUR_CASES_WIDGET, FILTER_CASE_STATE).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getValueOfCheckBox("Done").shouldBe(getClickableCondition()).click();
    newDashBoardPage.getCloseCheckBox().shouldBe(getClickableCondition()).click();
    newDashBoardPage.getApplyButtonFilter(YOUR_CASES_WIDGET).shouldBe(getClickableCondition()).click();
    newDashBoardPage.getColumnOfCaseHasIndex(0,"state").shouldHave(text("Done"));
  }
  
}
