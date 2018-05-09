package portal.guitest.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import portal.guitest.bean.ExpressResponsible;
import portal.guitest.common.BaseTest;
import portal.guitest.common.TestAccount;
import portal.guitest.page.ExpressFormDefinitionPage;
import portal.guitest.page.ExpressProcessPage;
import portal.guitest.page.HomePage;
import portal.guitest.page.LoginPage;
import portal.guitest.page.ProcessWidgetPage;

public class AxonExpressTest extends BaseTest{
  private static final int USER_TASK_INDEX = 1;
  private static final int USER_TASK_WITH_EMAIL_INDEX = 2;
  private static final int APPROVAL_INDEX = 3;
  private static final int INFORMATION_EMAIL_INDEX = 4;
  
  private static final int INPUT_TEXT_TYPE_INDEX = 0;
  private static final int INPUT_NUMBER_TYPE_INDEX = 1;
  private static final int INPUT_DATE_TYPE_INDEX = 2;
  
  private HomePage homePage;
  private  ProcessWidgetPage processWidget;
  @Override
  @Before
  public void setup() {
    super.setup();
    navigateToUrl(HomePage.PORTAL_HOME_PAGE_URL);
    
    LoginPage loginPage = new LoginPage(TestAccount.DEMO_USER);
    loginPage.login();
    homePage = new HomePage();
  }
  
  @Test
  public void createFullElementsOfForm() throws InterruptedException {
    goToCreateExpressProcess();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");
    
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));
    
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input number", INPUT_NUMBER_TYPE_INDEX, true);
    formDefinition.createTextInputField("Input date", INPUT_DATE_TYPE_INDEX, true);
    formDefinition.createRadioButtonField("Radio", 3);
    formDefinition.createUploadComponent("Upload");
    formDefinition.createCheckboxField("Checkbox", 3);
    formDefinition.createTextAreaField("Text area", true);
    formDefinition.moveAllElementToDragAndDrogPanel();
    //if we have radio button or checkbox, remember multiply with number of options
    //Example: we have 1 checkbox with 2 options, 1 radio with 3 options. Total we have 5 inputs
    Assert.assertEquals(11, formDefinition.countNumberOfElementsInPreviewDialog());
  }
  
  @Test
  public void createFullTaskType() {
    goToCreateExpressProcess();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test full task type", "Test description");
    
    ExpressResponsible responsible1 = new ExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = new ExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
    
    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1, responsible2));
    
    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Task 2", "Task 2 description", Arrays.asList(responsible2));
    
    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, INFORMATION_EMAIL_INDEX, "Task 3", "Task 3 description", Arrays.asList(responsible1));
    
    expressProcessPage.addNewTask(2);
    expressProcessPage.createTask(3, APPROVAL_INDEX, "Task 4", "Task 4 description", Arrays.asList(responsible1));
    
    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    Assert.assertEquals(4, formDefinition.countNumberOfSteps());
  }
  
  private void goToCreateExpressProcess() {
    processWidget = homePage.getProcessWidget();
    processWidget.expand();
    processWidget.openExpressPage();
  }
}
