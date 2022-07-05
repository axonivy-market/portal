package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
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
    makeSureNotAbleToReorderPublicDashboard();
    redirectToRelativeLink(String.format(reorderDashboardUrl, "false"));
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.toggleVisibility("public 2");
    reorderDashboardPage.toggleVisibility("public 2");
    reorderDashboardPage.toggleVisibility("private 1");
    reorderDashboardPage.reorderDashboard("private 2", "public 1");
    NewDashboardPage dashboardPage = reorderDashboardPage.saveAndBackToHomePage();
    ElementsCollection dashboardCollection = dashboardPage.getDashboardCollection();
    dashboardCollection.get(0).shouldBe(Condition.text("private 2"));
    dashboardCollection.get(1).shouldBe(Condition.text("public 1"));
    dashboardCollection.get(2).shouldBe(Condition.text("public 2"));
  }

  @Test
  public void testReorderPublicDashboard() {
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createSampleDashboardUrl);
    redirectToRelativeLink(String.format(reorderDashboardUrl, "true"));
    ReorderDashboardPage reorderDashboardPage = new ReorderDashboardPage();
    reorderDashboardPage.reorderDashboard("public 2", "public 1");
    NewDashboardPage dashboardPage = reorderDashboardPage.saveAndBackToHomePage();
    ElementsCollection dashboardCollection = dashboardPage.getDashboardCollection();
    dashboardCollection.get(0).shouldBe(Condition.text("public 2"));
    dashboardCollection.get(1).shouldBe(Condition.text("public 1"));
    dashboardCollection.get(2).shouldBe(Condition.text("private 1"));
    dashboardCollection.get(3).shouldBe(Condition.text("private 2"));
  }

  private void makeSureNotAbleToReorderPublicDashboard() {
    redirectToRelativeLink(String.format(reorderDashboardUrl, "true"));
    new NewDashboardPage().waitPageLoaded();
    $("#add-button").shouldNot(exist);
  }
}
