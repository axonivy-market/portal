package portal.guitest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import portal.guitest.common.BaseTest;
import portal.guitest.common.FileHelper;
import portal.guitest.page.DocumentTableComponentPage;

public class DocumentTableComponentTest extends BaseTest {

  @Override
  @Before
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

}
