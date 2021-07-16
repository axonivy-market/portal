package portal.guitest.test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;

import portal.guitest.common.BaseTest;
import portal.guitest.page.CaseDetailsPage;
import portal.guitest.page.TaskWidgetPage;

/**
 * This sample WebTest orchestrates a real browser to 
 * verify that your workflow application and especially it's Html Dialogs are running as expected.
 * 
 * <p>The test can either be run<ul>
 * <li>in the Designer IDE ( <code>right click > run as > JUnit Test </code> )</li>
 * <li>or in a Maven continuous integration build pipeline ( <code>mvn clean verify</code> )</li>
 * </ul></p>
 * 
 * <p>Detailed guidance on writing these kind of tests can be found in our
 * <a href="https://developer.axonivy.com/doc/dev/concepts/testing/web-testing.html">WebTesting docs</a>
 * </p>
 */
@IvyWebTest(headless = false)
public class NewDashboardTaskWidgetTest extends BaseTest {
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void testOpenRelatedCaseOfTask() {
    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    taskWidgetPage.expand();
    taskWidgetPage.openTaskDetails(0);

    String relatedCaseName = taskWidgetPage.getRelatedCase();
    CaseDetailsPage casePage = taskWidgetPage.openRelatedCaseOfTask();

    String caseName = casePage.getCaseName();
    assertTrue(relatedCaseName.contains(caseName));
  }

  @Test
  public void navigateToInfoPage(){
    open(EngineUrl.base());
    
    $("img").shouldBe(attribute("alt", "Axon Ivy"));
    $(By.tagName("img")).shouldBe(attribute("alt", "Axon Ivy"));
  }
  
  @Test
  @Disabled("illustrative code: needs adaption to your environment")
  public void fillDialogForm() {
    // valid links can be copied from the start page of the internal web-browser
    open(EngineUrl.createProcessUrl("PortalTemplate/154616078A1D629D/start.ivp"));
    
    // fill new customer form
    $(By.id("form:firstname")).sendKeys("Unit");
    $(By.id("form:lastname")).sendKeys("Test");
    
    // verify that the submit button is enabled, before clicking it.
    $(By.id("form:submit")).shouldBe(enabled).click();
    
    // verify that the registration was successful.
    $(By.id("form:newCustomer")).shouldBe(visible, text("Unit Test"));
  }
  
}