package com.axonivy.portal.selenium.accessibility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.AccessibilityHelpers;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class MyProfileAccessibilityTest extends BaseTest {

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void myProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openMyProfilePage();
    AccessibilityHelpers.makeA11yReport();
  }
}
