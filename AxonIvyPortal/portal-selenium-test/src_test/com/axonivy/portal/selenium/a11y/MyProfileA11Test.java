package com.axonivy.portal.selenium.a11y;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class MyProfileA11Test extends BaseTest {

  public void myProfile() {
    NewDashboardPage newDashboardPage = new NewDashboardPage();
    newDashboardPage.openMyProfilePage();
    A11yHelpers.makeA11yReport();
  } 
}
