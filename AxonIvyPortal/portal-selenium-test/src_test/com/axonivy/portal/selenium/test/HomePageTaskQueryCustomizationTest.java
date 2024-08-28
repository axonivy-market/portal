package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class HomePageTaskQueryCustomizationTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void testShowHideTaskDetailOnExpandedMode() {
    updateGlobalVariable(Variable.SHOW_LEGACY_UI.getKey(), "true");
    updateGlobalVariable(Variable.SHOW_USER_GUIDE.getKey(), "false");
    login(TestAccount.DEMO_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToRelativeLink(PORTAL_EXAMPLES_HOME_PAGE_URL);

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(1, taskWidgetPage.countTasks().size());
  }
}
