package com.axonivy.portal.selenium.test.globalsearch;

import static com.codeborne.selenide.Condition.appear;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.common.Variable;
import com.axonivy.portal.selenium.page.GlobalSearchResultPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest
public class GlobalSearchTest extends BaseTest {

  private static String CREATE_TESTING_TASK_PROCESS =
      "internalSupport/14B2FC03D2E87141/CreateTaskForGlobalSearchTest.ivp";

  private NewDashboardPage newDashboardPage;

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    login(TestAccount.ADMIN_USER);
    resetLanguageOfCurrentUser();
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
  }

  @Test
  public void testSearchTaskOnlyByName() {
    redirectToRelativeLink(CREATE_TESTING_TASK_PROCESS);
    updatePortalSetting(Variable.GLOBAL_SEARCH_BY_TASK_FIELDS.getKey(), "NAME");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    String taskName = "Testing Task for Global Search (Name)";
    String nameKeyword = "(Name)";
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(nameKeyword);
    resultPage.openTaskTab();
    assertEquals(taskName, resultPage.getNameOfTask(0));
    assertEquals("Tasks contain the keyword \"" + nameKeyword + "\" in Task Id, Name.",
        resultPage.getGlobalSearchByFieldTextForTaskTab());

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    String descriptionKeyword = "(Description)";
    resultPage = newDashboardPage.inputGlobalSearchKeyword(descriptionKeyword);
    resultPage.openTaskTab();
    assertEquals(0, resultPage.countTasks());
  }

  @Test
  public void testSearchCaseOnlyByDescription() {
    redirectToRelativeLink(CREATE_TESTING_TASK_PROCESS);
    updatePortalSetting(Variable.GLOBAL_SEARCH_BY_CASE_FIELDS.getKey(), "DESCRIPTION");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    String caseDescription = "Testing Case for Global Search (Description)";
    String descriptionKeyword = "(Description)";
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(descriptionKeyword);
    resultPage.openCaseTab();
    assertEquals(caseDescription, resultPage.getDescriptionOfCase(0));
    assertEquals("Cases contain the keyword \"" + descriptionKeyword + "\" in Case Id, Description.",
        resultPage.getGlobalSearchByFieldTextForCaseTab());

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    String nameKeyword = "(Name)";
    resultPage = newDashboardPage.inputGlobalSearchKeyword(nameKeyword);
    resultPage.openCaseTab();
    assertEquals(0, resultPage.countCases());
  }

  @Test
  public void testSearchCaseOnlyByCustomField() {
    redirectToRelativeLink(CREATE_TESTING_TASK_PROCESS);
    updatePortalSetting(Variable.GLOBAL_SEARCH_BY_CASE_FIELDS.getKey(), "CUSTOM");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();

    String customKeyword = "Testing Case custom field";
    String nameKeyword = "(Name)";
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(nameKeyword);
    resultPage.openCaseTab();
    assertEquals(0, resultPage.countCases());

    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    String caseName = "Testing Case for Global Search (Name)";
    resultPage = newDashboardPage.inputGlobalSearchKeyword(customKeyword);
    resultPage.openCaseTab();
    assertEquals(caseName, resultPage.getNameOfCase(0));
    assertEquals("Cases contain the keyword \"" + customKeyword + "\" in Case Id, Custom string fields.",
        resultPage.getGlobalSearchByFieldTextForCaseTab());
  }
  
  @Test
  public void testConfigGlobalSearchScopeCategories() {
    redirectToRelativeLink(CREATE_TESTING_TASK_PROCESS);
    updatePortalSetting(Variable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey(), "");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    boolean isHiddenSearchIcon = newDashboardPage.isInputGlobalSearchDisabled();
    assertFalse(isHiddenSearchIcon);

    updatePortalSetting(Variable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey(), "PROCESSES,TASKS");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    isHiddenSearchIcon = newDashboardPage.isInputGlobalSearchDisabled();
    assertTrue(isHiddenSearchIcon);

    String taskName = "Testing Task for Global Search (Name)";
    String nameKeyword = "(Name)";
    GlobalSearchResultPage resultPage = newDashboardPage.inputGlobalSearchKeyword(nameKeyword);
    resultPage.openTaskTab();
    assertEquals(taskName, resultPage.getNameOfTask(0));
    assertEquals("Tasks contain the keyword \"" + nameKeyword + "\" in Task Id, Name, Description.", resultPage.getGlobalSearchByFieldTextForTaskTab());

    resultPage.caseTabShouldBeDisappear();
  }
  
  @Test
  public void testQuickGlobalSearch() {
    redirectToRelativeLink(CREATE_TESTING_TASK_PROCESS);
    updatePortalSetting(Variable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey(), "");
    updatePortalSetting(Variable.SHOW_QUICK_GLOBAL_SEARCH.getKey(), "TRUE");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    SelenideElement searchInput = newDashboardPage.getQuickGlobalSearchInput();
    assertFalse(searchInput.isDisplayed());
    
    updatePortalSetting(Variable.GLOBAL_SEARCH_SCOPE_BY_CATEGORIES.getKey(), "PROCESSES,TASKS");
    redirectToNewDashBoard();
    newDashboardPage = new NewDashboardPage();
    searchInput = newDashboardPage.getQuickGlobalSearchInput();
    assertTrue(searchInput.shouldBe(appear, DEFAULT_TIMEOUT).isDisplayed());
    
    newDashboardPage.clickOnQuickGlobalSearchInput();
    SelenideElement searchPanel = newDashboardPage.getGlobalQuickSearchPanel();
    assertTrue(searchPanel.isDisplayed());
    
    String taskName = "Testing Task for Global Search (Name)";
    String nameKeyword = "(Name)";
    GlobalSearchResultPage resultPage = newDashboardPage.inputQuickGlobalSearchKeyword(nameKeyword);
    resultPage.openTaskTab();
    assertEquals(taskName, resultPage.getNameOfTask(0));
    assertEquals("Tasks contain the keyword \"" + nameKeyword + "\" in Task Id, Name, Description.", resultPage.getGlobalSearchByFieldTextForTaskTab());

    resultPage.caseTabShouldBeDisappear();
  }
}
