package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.common.TestAccount;
import com.axonivy.portal.selenium.page.CaseDetailsPage;
import com.axonivy.portal.selenium.page.CaseWidgetNewDashBoardPage;
import com.axonivy.portal.selenium.page.MainMenuPage;
import com.axonivy.portal.selenium.page.NewDashboardPage;

@IvyWebTest
public class RenameDocumentTest extends BaseTest {
  private NewDashboardPage newDashboardPage;
  private MainMenuPage menuPage;
  private CaseWidgetNewDashBoardPage casePage;
  private CaseDetailsPage caseDetailsPage;

  private String duplicateNameMsg = "There is already a file with the same name in this location.";
  private String forbiddenCharsMsg = "A file name can't contain any of the following characters: \\ / : * ? \" < > |";

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    createTestingTasks();
  }

  @Test
  public void renameDocument() {
    initNewDashboardPage(TestAccount.ADMIN_USER);
    casePage = menuPage.openCaseList();
    caseDetailsPage = casePage.openDetailsCase("Leave Request");
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    // Update name
    String nameAfterUpdating = caseDetailsPage.renameDocument("dataset");
    assertEquals(nameAfterUpdating, "dataset.pdf");
    assertEquals(caseDetailsPage.getLatestHistoryContent(), "admin has renamed the file test-no-files-no-js.pdf to dataset.pdf");

    // Add second file
    caseDetailsPage.uploadDocumentWithoutError(FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));

    // Change name of second file to the same, show invalid message, name is not updated and new note is not created
    String nameAfterUpdatingSecondFile = caseDetailsPage.renameDocument("dataset");
    assertEquals(nameAfterUpdatingSecondFile, "test-no-files-no-js.pdf");
    assertEquals(caseDetailsPage.getRenamingFileMessage(), duplicateNameMsg);
    assertEquals(caseDetailsPage.getLatestHistoryContent(), "admin has uploaded test-no-files-no-js.pdf");

    // Invalid name when using forbidden characters
    caseDetailsPage.renameDocument("test-no-files-no-js?");
    assertEquals(caseDetailsPage.getRenamingFileMessage(), forbiddenCharsMsg);
    assertEquals(caseDetailsPage.getLatestHistoryContent(), "admin has uploaded test-no-files-no-js.pdf");

    // Change back to the old name, name is not changed so new note is not created
    String nameAfterUpdatingSecondFile2 = caseDetailsPage.renameDocument("test-no-files-no-js");
    assertEquals(nameAfterUpdatingSecondFile2, "test-no-files-no-js.pdf");
    assertEquals(caseDetailsPage.getLatestHistoryContent(), "admin has uploaded test-no-files-no-js.pdf");
  }

  private void initNewDashboardPage(TestAccount account) {
    login(account);
    showNewDashboard();
    newDashboardPage = new NewDashboardPage();
    newDashboardPage.waitForCaseWidgetLoaded();
    menuPage = new MainMenuPage();
  }
}
