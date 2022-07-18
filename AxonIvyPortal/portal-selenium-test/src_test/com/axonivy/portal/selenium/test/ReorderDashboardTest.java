package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.LinkNavigator;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.ReorderDashboardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

@IvyWebTest
public class ReorderDashboardTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  // Public dashboard order: public 1, public 2, public 3 (only for role CostObject)
  // Private dashboard order: private 1, private 2
  @Test
  public void testReorderMyDashboard() {
    redirectToRelativeLink(createSampleDashboardUrl);
    DashboardConfigurationPage configurationpage = navigateToReorderDashboard(false);
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.toggleVisibility("public 2");
    reorderDashboardPage.toggleVisibility("public 2");
    reorderDashboardPage.toggleVisibility("private 1");
    reorderDashboardPage.reorderPrivateDashboard("private 2", "public 1");
    reorderDashboardPage.saveSetting();
    NewDashboardPage dashboardPage = configurationpage.backToHomePage();
    ElementsCollection dashboardCollection = dashboardPage.getDashboardCollection();
    dashboardCollection.get(0).shouldBe(Condition.text("private 2"));
    dashboardCollection.get(1).shouldBe(Condition.text("public 1"));
    dashboardCollection.get(2).shouldBe(Condition.text("public 2"));
  }

  @Test
  public void testReorderPublicDashboard() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    DashboardConfigurationPage configurationpage = navigateToReorderDashboard(true);
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.reorderPublicDashboard("public 2", "public 1");
    reorderDashboardPage.saveSetting();
    NewDashboardPage dashboardPage = configurationpage.backToHomePage();
    ElementsCollection dashboardCollection = dashboardPage.getDashboardCollection();
    dashboardCollection.get(0).shouldBe(Condition.text("public 2"));
    dashboardCollection.get(1).shouldBe(Condition.text("public 1"));
    dashboardCollection.get(2).shouldBe(Condition.text("private 1"));
    dashboardCollection.get(3).shouldBe(Condition.text("private 2"));
  }

  private DashboardConfigurationPage navigateToReorderDashboard(boolean isPublic) {
    LinkNavigator.redirectToPortalDashboardConfiguration();
    var configurationPage = new DashboardConfigurationPage();
    if (isPublic) {
      configurationPage.reorderPublicDashboard();
      return configurationPage;
    }
    configurationPage.reorderPrivateDashboard();
    return configurationPage;
  }
}
