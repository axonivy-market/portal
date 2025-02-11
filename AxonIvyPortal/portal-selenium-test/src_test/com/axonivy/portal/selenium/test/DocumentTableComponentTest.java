package com.axonivy.portal.selenium.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.portal.selenium.common.BaseTest;
import com.axonivy.portal.selenium.common.FileHelper;
import com.axonivy.portal.selenium.page.component.DocumentTableComponentPage;

@IvyWebTest
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
  public void testPreviewDocument() {
    DocumentTableComponentPage documentTableComponentPage = new DocumentTableComponentPage();
    documentTableComponentPage.uploadSampleDocument(FileHelper.getAbsolutePathToTestFile("sample-file.txt"));
    refreshPage();
    documentTableComponentPage.waitForDocumentTableComponentPageLoaded();
    assertEquals(1, documentTableComponentPage.countDocuments());
    documentTableComponentPage.clickPreviewButton();
    documentTableComponentPage.waitForPreviewDialog();
    assertTrue(documentTableComponentPage.isPreviewContentDisplayed());
    documentTableComponentPage.closePreviewDialog();
  }
}
