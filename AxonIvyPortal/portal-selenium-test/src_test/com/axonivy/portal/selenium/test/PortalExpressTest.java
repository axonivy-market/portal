package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.NavigationHelper;
import com.axonivy.portal.selenium.common.ScreenshotUtil;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.ExpressFormDefinitionPage;
import com.axonivy.portal.selenium.page.ExpressProcessPage;
import com.axonivy.portal.selenium.page.ExpressTaskPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ProcessWidgetPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;
import com.codeborne.selenide.Condition;

import ch.ivyteam.ivy.project.portal.test.ExpressResponsible;

@IvyWebTest(headless = false)
public class PortalExpressTest extends BaseTest{
  protected static final int USER_TASK_INDEX = 0;
  protected static final int USER_TASK_WITH_EMAIL_INDEX = 1;
  protected static final int INFORMATION_EMAIL_INDEX = 2;
  protected static final int APPROVAL_INDEX = 3;

  protected static final int INPUT_TEXT_TYPE_INDEX = 0;
  protected static final int INPUT_NUMBER_TYPE_INDEX = 1;
  protected static final int INPUT_DATE_TYPE_INDEX = 2;

  protected NewDashboardPage newDashboardPage;
  protected ProcessWidgetPage processWidget;
  protected TaskWidgetPage taskWidgetPage;

  ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
  ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);
  ExpressResponsible groupHr = setExpressResponsible("Human resources department", true);

  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(grantPortalPermission);
    newDashboardPage = new NewDashboardPage();
  }
  
  @Test
  public void testOpenProcessWidgetWithoutCreateExpressWorkflowPermission() throws Exception {
    String denyAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/denyPortalPermission.ivp";
    redirectToRelativeLink(denyAllPermissionsForAdminUserURL);
    MainMenuPage mainMenuPage = newDashboardPage.openMainMenu();
    processWidget = mainMenuPage.selectProcessesMenu();
    assertEquals(false, processWidget.hasCreateNewExpressWorkflowLink());
    // run process to grant permission back to normal
    String grantAllPermissionsForAdminUserURL = "portalKitTestHelper/14DE09882B540AD5/grantPortalPermission.ivp";
    redirectToRelativeLink(grantAllPermissionsForAdminUserURL);
  }

  @Test
  public void testCreateDocumentElement() {
    goToCreateExpressProcess();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createUploadComponent("Upload");
    formDefinition.moveAllElementToDragAndDrogPanel();
    formDefinition.executeWorkflow();
    ExpressTaskPage expressTaskPage = new ExpressTaskPage();
    assertTrue(expressTaskPage.isDocumentTableVisible());
    assertTrue(expressTaskPage.isDocumentUploadButtonVisible());
    expressTaskPage.finish();
  }

  @Test
  public void createFullElementsOfForm() {
    ScreenshotUtil.resizeBrowser(new Dimension(2560, 1440));
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test 1", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    formDefinition.createTextInputField("Input Text", INPUT_TEXT_TYPE_INDEX, false);
    formDefinition.createTextInputField("Input number", INPUT_NUMBER_TYPE_INDEX, true);
    formDefinition.createTextInputField("Input date", INPUT_DATE_TYPE_INDEX, true);
    formDefinition.createRadioButtonField("Radio", 3);
    formDefinition.createUploadComponent("Upload");
    formDefinition.createCheckboxField("Checkbox", 3);
    formDefinition.createTextAreaField("Text area", true);
    formDefinition.createCheckboxFieldWithDataProvider("Checkbox with data provider");
    formDefinition.moveAllElementToDragAndDrogPanel();

    assertEquals(14, formDefinition.countNumberOfElementsInPreviewDialog());
    $(By.xpath("//label[text()='Data Provider Item 1']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
    $(By.xpath("//label[text()='Data Provider Item 2']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
    $(By.xpath("//label[text()='Data Provider Item 3']")).shouldBe(Condition.exist, DEFAULT_TIMEOUT);
  }
  
  @Test
  public void createFullTaskType() {
    goToExpressCreationPage();
    ExpressProcessPage expressProcessPage = new ExpressProcessPage();
    expressProcessPage.fillProcessProperties(true, true, "Test full task type", "Test description");

    ExpressResponsible responsible1 = setExpressResponsible(TestAccount.ADMIN_USER.getUsername(), false);
    ExpressResponsible responsible2 = setExpressResponsible(TestAccount.DEMO_USER.getUsername(), false);

    expressProcessPage.createTask(0, USER_TASK_INDEX, "Task 1", "Task 1 description",
        Arrays.asList(responsible1, responsible2));

    expressProcessPage.addNewTask(0);
    expressProcessPage.createTask(1, USER_TASK_WITH_EMAIL_INDEX, "Task 2", "Task 2 description",
        Arrays.asList(responsible2));

    expressProcessPage.addNewTask(1);
    expressProcessPage.createTask(2, INFORMATION_EMAIL_INDEX, null, null, null);

    expressProcessPage.addNewTask(2);
    expressProcessPage.createTask(3, APPROVAL_INDEX, "Task 4", "Task 4 description", Arrays.asList(responsible1));

    ExpressFormDefinitionPage formDefinition = expressProcessPage.goToFormDefinition();
    assertEquals(4, formDefinition.countNumberOfSteps());
  }
  
  protected void goToCreateExpressProcess() {
    processWidget = NavigationHelper.navigateToProcessList();
    processWidget.openExpressPage();
  }
  
  protected void goToExpressCreationPage() {
    redirectToRelativeLink(expressStartLink);
  }
}
