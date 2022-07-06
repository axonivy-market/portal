package com.axonivy.portal.selenium.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.NewDashboardDetailsEditPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

import ch.ivy.addon.portalkit.enums.PortalVariable;

@IvyWebTest
public class DashboardRestoreConfigurationTest extends BaseTest {

  private NewDashboardPage newDashboardPage;
  private static final String CASE_WIDGET_NAME = "Your New Cases Widget";
  private static final String DASHBOARD_NAME = "New public dashboard";
  private static final String DASHBOARD_DESCRIPTION = "New public dashboard description";
  private static final List<String> DASHBOARD_PERMISSION = Arrays.asList("Cost Object (CostObject)");

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    newDashboardPage = new NewDashboardPage();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWritePublicPermission.ivp");
    redirectToRelativeLink("PortalKitTestHelper/14DE09882B540AD5/grantDashboardWriteOwnPermission.ivp");
    redirectToNewDashBoard();
  }

  @Test
  public void testRestorePublishDashboardToScratch() {
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromScratch(DASHBOARD_NAME, DASHBOARD_DESCRIPTION, DASHBOARD_PERMISSION);
    verifyEditingDashboardContent(DASHBOARD_NAME, CollectionCondition.empty);
    addNewCaseWidget();
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getWidgets().shouldHave(CollectionCondition.size(1));
    verifyRestoreDashboardMessage("Create from scratch");
    newDashboardDetailsEditPage.restoreDashboardToStandard();
    verifyEditingDashboardContent(DASHBOARD_NAME, CollectionCondition.empty);
  }

  @Test
  public void testRestorePublishDashboardToDefaultTemplate() {
    newDashboardPage.openDashboardConfigurationDialog();
    newDashboardPage.openCreatePublicDashboardMenu();
    newDashboardPage.createPublicDashboardFromTemplate(DASHBOARD_NAME, DASHBOARD_DESCRIPTION, DASHBOARD_PERMISSION, 0);
    verifyEditingDashboardContent(DASHBOARD_NAME, CollectionCondition.size(3));
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.deleteCompactModeProcess();
    newDashboardDetailsEditPage.getWidgets().shouldHave(CollectionCondition.size(2));
    verifyRestoreDashboardMessage("Default template");
    newDashboardDetailsEditPage.restoreDashboardToStandard();
    verifyEditingDashboardContent(DASHBOARD_NAME, CollectionCondition.size(3));
  }

  @Test
  public void testCannotRestoreDashboardWhenTemplateNotFound() {
    var dashboardName = "Dashboard";
    createJSonFile("dashboard-has-one-chart-example.json", PortalVariable.DASHBOARD.key);
    newDashboardPage.waitForAbsencesGrowlMessageDisplay();
    newDashboardPage.openDashboardConfigurationDialog();
    var configPage = newDashboardPage.navigateToEditPublicDashboardPage();
    configPage.navigateToEditDashboardDetailsByName(dashboardName);
    verifyEditingDashboardContent(dashboardName, CollectionCondition.size(1));
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    var restoreButton = newDashboardDetailsEditPage.getRestoreDashboardButton();
    restoreButton.shouldHave(Condition.cssClass("ui-state-disabled"));
  }

  private void verifyEditingDashboardContent(String dashboardName, CollectionCondition collectionCondition) {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.getTitleByIndex(0).shouldBe(Condition.exactText(dashboardName));
    newDashboardDetailsEditPage.getWidgets().shouldBe(collectionCondition);
  }

  private void addNewCaseWidget() {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.addWidget();
    var newCaseWidget = newDashboardDetailsEditPage.addNewCaseWidget();
    newCaseWidget.changeWidgetTitle(CASE_WIDGET_NAME);
    newCaseWidget.save();
    var caseWidget = newDashboardPage.selectCaseWidget(CASE_WIDGET_NAME);
    caseWidget.expand().shouldHaveSize(1);
  }

  private void verifyRestoreDashboardMessage(String templateName) {
    NewDashboardDetailsEditPage newDashboardDetailsEditPage = new NewDashboardDetailsEditPage();
    newDashboardDetailsEditPage.clickOnRestoreDashboard();
    var message = newDashboardDetailsEditPage.getRestoreDashboardMessage();
    message.shouldHave(Condition.text(templateName));
  }
}
