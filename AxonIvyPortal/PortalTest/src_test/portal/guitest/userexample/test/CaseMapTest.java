package portal.guitest.userexample.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import portal.guitest.common.BaseTest;
import portal.guitest.page.HomePage;
import portal.guitest.page.ProcessWidgetPage;
import portal.guitest.page.TaskTemplatePage;
import portal.guitest.page.TaskWidgetPage;
import portal.guitest.userexamples.page.CaseMapPage;

public class CaseMapTest extends BaseTest {

  
  private static final String CASE_MAP_URL = "/portal-user-examples/70765b37-a3e8-418a-a8d5-c2b3a539408e.icm";
  private ProcessWidgetPage processWidget;
  private HomePage homePage;

  
  @Override
  @Before
  public void setup() {
    super.setup();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
  }
  
  @Test
  public void testCaseMapApprovalWorkflow() {
    redirectToRelativeLink(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN","20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitButton();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Verify Personal Data");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Internal Solvency Check");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 1");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    
    Assert.assertEquals("John", caseMapPage.getCustomerName());
    Assert.assertEquals("Jack", caseMapPage.getCustomerFirstName());
    Assert.assertEquals("VN", caseMapPage.getCountry());
    Assert.assertEquals("20000", caseMapPage.getAmount());
    Assert.assertEquals("To buy a new car", caseMapPage.getReason());
    Assert.assertEquals("80000", caseMapPage.getSalary());
    Assert.assertEquals("100000", caseMapPage.getOtherCredits());
    taskTemplatePage.clickByCssSelector("button[id$='form:approval-button']");
    
    taskWidgetPage.waitForElementDisplayed(By.cssSelector(".no-task-message"), true, 10);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 2");
    taskTemplatePage = taskWidgetPage.startTask(0);
    
    Assert.assertEquals("John", caseMapPage.getCustomerName());
    Assert.assertEquals("Jack", caseMapPage.getCustomerFirstName());
    Assert.assertEquals("VN", caseMapPage.getCountry());
    Assert.assertEquals("20000", caseMapPage.getAmount());
    Assert.assertEquals("To buy a new car", caseMapPage.getReason());
    Assert.assertEquals("80000", caseMapPage.getSalary());
    Assert.assertEquals("100000", caseMapPage.getOtherCredits());
    taskTemplatePage.clickByCssSelector("button[id$='form:approval-button']");
  }
  
  @Test
  public void testCaseMapRejectedWorkflow() {
    redirectToRelativeLink(CASE_MAP_URL);
    CaseMapPage caseMapPage = new CaseMapPage();
    caseMapPage.inputFields("John", "Jack", "1.1.2019", "VN","20000", "To buy a new car", "80000", "100000");
    caseMapPage.clickSubmitButton();
    
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Verify Personal Data");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Internal Solvency Check");
    taskWidgetPage.startTaskWithoutUI(0);
    homePage = new HomePage();
    redirectToRelativeLink(HomePage.PORTAL_HOME_PAGE_URL);
    
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 1");
    TaskTemplatePage taskTemplatePage = taskWidgetPage.startTask(0);
    
    Assert.assertEquals("John", caseMapPage.getCustomerName());
    Assert.assertEquals("Jack", caseMapPage.getCustomerFirstName());
    Assert.assertEquals("VN", caseMapPage.getCountry());
    Assert.assertEquals("20000", caseMapPage.getAmount());
    Assert.assertEquals("To buy a new car", caseMapPage.getReason());
    Assert.assertEquals("80000", caseMapPage.getSalary());
    Assert.assertEquals("100000", caseMapPage.getOtherCredits());
    
    taskTemplatePage.clickByCssSelector("button[id$='form:rejected-button']");
    
    taskWidgetPage.waitForElementDisplayed(By.cssSelector(".no-task-message"), true, 10);
    taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.filterTasksBy("Approve Level 2");
    Assert.assertEquals(0, taskWidgetPage.countTasks());
  }
  
  @Test
  public void testCollectPersonalDataValidation() {
      redirectToRelativeLink(CASE_MAP_URL);
      CaseMapPage caseMapPage = new CaseMapPage();
      caseMapPage.inputFields("","", "", "","", "", "", "");
      Assert.assertEquals("Name: Value is required."
          + ",Country: Value is required.,"
          + "Amount (SFr.): Value is required.,"
          + "Yearly salary: Value is required.,"
          + "Amount of other open credits (SFr.)*: Value is required.",
          caseMapPage.clickSubmitAndGetValidationMsg());
      caseMapPage.inputFields("John", "Jack", "1.1.2020", "VN","20000", "To buy a new car", "80000", "100000");
      caseMapPage.clickSubmitButton();
  }
  
}
