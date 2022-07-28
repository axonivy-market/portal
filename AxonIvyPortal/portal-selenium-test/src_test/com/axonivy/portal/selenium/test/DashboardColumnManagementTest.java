package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseEditWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.DashboardConfigurationPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.Condition;

@IvyWebTest
public class DashboardColumnManagementTest extends BaseTest {
  // WIDGET
  private static final String YOUR_CASES_WIDGET = "Your Cases";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    redirectToRelativeLink(createTestingTasksUrl);
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }


  @Test
  public void testHideUsedField() {
    CaseWidgetNewDashBoardPage caseWidget = newDashboardPage.selectCaseWidget(YOUR_CASES_WIDGET);
    caseWidget.expand().shouldHave(sizeGreaterThanOrEqual(1));

    DashboardConfigurationPage configurationPage = newDashboardPage.openDashboardConfigurationPage();
    var modificationPage = configurationPage.openEditPublicDashboardsPage();
    modificationPage.navigateToEditDashboardDetailsByName("Dashboard");

    CaseEditWidgetNewDashBoardPage caseEditWidget = caseWidget.openEditWidget();
    caseEditWidget.preview();
    caseEditWidget.openColumnManagementDialog();

    caseEditWidget.selectCustomType();

    caseEditWidget.removeAddedField("id");
    caseEditWidget.getCustomField("id").shouldNotBe(Condition.exist);

    String addedCustomField1 = caseEditWidget.addFirstCustomField();
    caseEditWidget.getCustomField(addedCustomField1).shouldNotBe(Condition.exist);

    String addedCustomField2 = caseEditWidget.addFirstCustomField();
    caseEditWidget.getCustomField(addedCustomField2).shouldNotBe(Condition.exist);

    caseEditWidget.removeAddedField(addedCustomField1);
    caseEditWidget.getCustomField(addedCustomField1).shouldBe(Condition.exist);

    caseEditWidget.selectStandardType();
    caseEditWidget.getStandardField("id").shouldBe(Condition.exist);

    caseEditWidget.removeAddedField(addedCustomField2);
    caseEditWidget.getStandardField(addedCustomField2).shouldNotBe(Condition.exist);

    caseEditWidget.addFirstStandardField();
    caseEditWidget.getStandardField("id").shouldNotBe(Condition.exist);

    caseEditWidget.removeAddedField("id");
    caseEditWidget.getStandardField("id").shouldBe(Condition.exist);

    caseEditWidget.selectCustomType();
    caseEditWidget.getCustomField(addedCustomField2).shouldBe(Condition.exist);
  }
}
