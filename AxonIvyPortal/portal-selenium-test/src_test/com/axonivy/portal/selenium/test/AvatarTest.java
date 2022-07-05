package com.axonivy.portal.selenium.test;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.CaseWidgetPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.axonivy.portal.selenium.page.TaskDetailsPage;
import com.axonivy.portal.selenium.page.TaskWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.TaskWidgetPage;

@IvyWebTest
public class AvatarTest extends BaseTest {

  private static final String ACCESS_TASK_DETAILS = "ACCESS_TASK_DETAILS";
  private static final String SICK_LEAVE_REQUEST = "Sick Leave Request";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
  }

  @Test
  public void showAvatarAsDefault() {
    updateGlobalVariable(Variable.TASK_BEHAVIOUR_WHEN_CLICKING_ON_LINE_IN_TASK_LIST.getKey(), ACCESS_TASK_DETAILS);
    redirectToRelativeLink(createTestingTasksUrl);

    // Check dashboard
    NewDashboardPage dashboardPage = new NewDashboardPage();
    assertTrue(dashboardPage.getUserMenuAvatar().exists());
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = new TaskWidgetNewDashBoardPage();
    assertTrue(taskWidgetNewDashBoardPage.getResponsibleAvatar().exists());
    CaseWidgetNewDashBoardPage caseWidgetNewDashBoardPage = new CaseWidgetNewDashBoardPage();
    assertTrue(caseWidgetNewDashBoardPage.getCreatorAvatar().exists());

    // Check task list
    MainMenuPage mainMenuPage = new MainMenuPage();
    TaskWidgetPage taskWidgetPage = mainMenuPage.openTaskList();
    assertTrue(taskWidgetPage.getResponsibleAvatar().exists());

    // Check task details
    taskWidgetPage.openTask(SICK_LEAVE_REQUEST);
    TaskDetailsPage taskDetailsPage = new TaskDetailsPage();
    assertTrue(taskDetailsPage.getResponsibleAvatar().exists());

    // Check case list
    CaseWidgetPage caseWidgetPage = mainMenuPage.openCaseList();
    assertTrue(caseWidgetPage.getCreatorAvatar().exists());

    // Check case details
    CaseDetailsPage caseDetailsPage = caseWidgetPage.openCase("Leave Request");
    assertTrue(caseDetailsPage.getCreatorAvatar().exists());
    assertTrue(caseDetailsPage.getHistoryAuthorAvatar().exists());
  }

  @Test
  public void hideAvatarWhenConfigured() {
    updateGlobalVariable(Variable.SHOW_AVATAR.getKey(), "False");
    redirectToRelativeLink(createTestingTasksUrl);
    TaskWidgetNewDashBoardPage taskWidgetNewDashBoardPage = new TaskWidgetNewDashBoardPage();
    assertTrue(taskWidgetNewDashBoardPage.stateOfFirstTask().exists());
    $$(".ui-avatar").shouldBe(empty);
  }

}
