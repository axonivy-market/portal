package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashBoardPage;
import com.axonivy.portal.selenium.page.ProcessEditWidgetNewDashBoardPage;

@IvyWebTest
public class NewDashboardProcessWidgetTest extends BaseTest {
  private NewDashBoardPage newDashBoardPage;
  
  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashBoardPage = new NewDashBoardPage();
  }
  
  @Test
  public void testConfigImageProcess() {
    login(TestAccount.ADMIN_USER);
    redirectToNewDashBoard();
    ProcessEditWidgetNewDashBoardPage editProcessWidgetConfiguration = newDashBoardPage.editProcessWidgetConfiguration();
    editProcessWidgetConfiguration.selectImageMode("Categoried Leave Request");
    newDashBoardPage.getStartProcessButton().isDisplayed();
    newDashBoardPage.getProcessImage().attr("src").contains("PROCESSMODELING");
  }

}
