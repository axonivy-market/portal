package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.HomePage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class HomePageTaskQueryCustomizationTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
    login(TestAccount.DEMO_USER);
  }

  @Test
  public void testShowHideTaskDetailOnExpandedMode() {
    updateLegacyUIConfiguration();
    HomePage homePage = new HomePage();
    homePage.waitForGlobalGrowlDisappear();

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(1, taskWidgetPage.countTasks().size());
  }
}
