package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
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
    updateLegacyUIConfiguration();
    redirectToRelativeLink(createTestingTasksUrl);

    TaskWidgetPage taskWidgetPage = new TaskWidgetPage();
    assertEquals(1, taskWidgetPage.countTasks().size());
  }
}
