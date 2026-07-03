package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.page.component.DocumentTableComponentPage;
import static com.codeborne.selenide.Selenide.$;

@IvyWebTest(headless = false)
public class DocumentTableComponentTest extends BaseTest {

  @Override
  @BeforeEach
  public void setup() {
    super.setup();
    redirectToRelativeLink(documentTableComponentUrl);
  }

  @Test
  public void testUploadDocument() {
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    assertEquals(1, documentTableComponentPage.countDocuments());
  }

  @Test
  public void testUploadMultipleDocuments() {
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt") 
      + "\n" + FileHelper.getAbsolutePathToTestFile("test-no-files-no-js.pdf"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    assertEquals(2, documentTableComponentPage.countDocuments());
  }

  @Test
  public void testPreviewDocument() {
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    assertEquals(1, documentTableComponentPage.countDocuments());
    documentTableComponentPage.clickPreviewButton();
    documentTableComponentPage.waitForPreviewDialog();
    assertTrue(documentTableComponentPage.isPreviewContentDisplayed());
  }

  @Test
  public void testUploadDocumentToCustomFolder() {
    String documentTableCustomUploadFolderUrl = "portal-components-examples/1818938E7EBC9329/showDocumentTableWithUploadSubfolder.ivp";
    redirectToRelativeLink(documentTableCustomUploadFolderUrl);
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    // expect empty table because upload folder is different to display folder
    assertTrue(documentTableComponentPage.isEmptyTable());
  }
}
