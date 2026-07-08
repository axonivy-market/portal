package com.axonivy.portal.selenium.a11y;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.A11yHelpers;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class MyProfileA11Test extends BaseTest {

  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void myProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openMyProfilePage();
    A11yHelpers.makeA11yReport();
  }
}
