package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.UploadDocumentItemCheckerData;
import com.axonivy.portal.components.enums.UploadDocumentCheckStatus;
import com.axonivy.portal.components.process.support.FakeUploadedFile;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

/**
 * Process test for {@code processes/Functional Processes/UploadDocumentItemChecker.p.json}.
 *
 * <p>The process validates an uploaded file and produces an {@link UploadDocumentCheckStatus}:
 * <ol>
 *   <li>extension whitelist check (always runs);</li>
 *   <li>optional virus scan (when {@code enableVirusScannerForUploadedDocument});</li>
 *   <li>optional embedded-script/macro check (when {@code enableScriptCheckingForUploadedDocument}).</li>
 * </ol>
 * Each test drives one branch and asserts the resulting status / message on the process data.
 */
@IvyProcessTest
class TestUploadDocumentItemChecker {

  private static final BpmProcess CHECKER = BpmProcess.name("UploadDocumentItemChecker");
  private static final String START = "call(UploadedFile,Boolean,Boolean,String)";

  // Params order mirrors the CallSubStart signature:
  //   uploadFile, enableVirusScannerForUploadedDocument, enableScriptCheckingForUploadedDocument, allowedUploadFileTypes

  @Test
  void invalidExtension_returnsFail(BpmClient client) {
    var upload = new FakeUploadedFile("malware.exe", "irrelevant".getBytes());

    var result = client.start()
        .subProcess(CHECKER.elementName(START))
        .execute(upload, false, false, "pdf,docx");

    UploadDocumentItemCheckerData data = result.data().last();
    assertThat(data.getCheckStatus()).isEqualTo(UploadDocumentCheckStatus.FAIL);
    assertThat(data.getMessage()).isNotBlank();
  }

  @Test
  void validExtension_noChecks_returnsOk(BpmClient client) {
    var upload = new FakeUploadedFile("report.pdf", "%PDF-1.4".getBytes());

    var result = client.start()
        .subProcess(CHECKER.elementName(START))
        .execute(upload, false, false, "pdf,docx");

    UploadDocumentItemCheckerData data = result.data().last();
    assertThat(data.getCheckStatus()).isEqualTo(UploadDocumentCheckStatus.OK);
  }

  @Test
  void emptyAllowedTypes_acceptsAnyExtension(BpmClient client) {
    // Blank config means "all file types allowed" (see CaseDocumentService.isDocumentTypeValid).
    var upload = new FakeUploadedFile("archive.zip", new byte[] {0x50, 0x4B, 0x03, 0x04});

    var result = client.start()
        .subProcess(CHECKER.elementName(START))
        .execute(upload, false, false, "");

    UploadDocumentItemCheckerData data = result.data().last();
    assertThat(data.getCheckStatus()).isEqualTo(UploadDocumentCheckStatus.OK);
  }

  @Test
  void scriptCheckEnabled_fileWithoutDetector_returnsOk(BpmClient client) {
    // A plain text file has no DocumentDetector → treated as safe.
    var upload = new FakeUploadedFile("notes.txt", "just some text".getBytes());

    var result = client.start()
        .subProcess(CHECKER.elementName(START))
        .execute(upload, false, true, "txt");

    UploadDocumentItemCheckerData data = result.data().last();
    assertThat(data.getCheckStatus()).isEqualTo(UploadDocumentCheckStatus.OK);
  }

  @Test
  void scriptCheckEnabled_docWithMacro_returnsFail(BpmClient client) throws Exception {
    var upload = new FakeUploadedFile("macro.doc", readFixture("bad.doc"));

    var result = client.start()
        .subProcess(CHECKER.elementName(START))
        .execute(upload, false, true, "doc");

    UploadDocumentItemCheckerData data = result.data().last();
    assertThat(data.getCheckStatus()).isEqualTo(UploadDocumentCheckStatus.FAIL);
    assertThat(data.getMessage()).isNotBlank();
  }

  /** Reuses the malicious-macro fixtures shipped with the document detector unit tests. */
  private static byte[] readFixture(String name) throws Exception {
    try (InputStream in = TestUploadDocumentItemChecker.class
        .getResourceAsStream("/com/axonivy/portal/document/" + name)) {
      if (in == null) {
        throw new IllegalStateException("Missing test fixture: " + name);
      }
      return in.readAllBytes();
    }
  }
}
