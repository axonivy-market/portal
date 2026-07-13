package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.UploadDocumentItemData;
import com.axonivy.portal.components.enums.UploadDocumentCheckStatus;
import com.axonivy.portal.components.process.support.FakeUploadedFile;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/UploadDocumentItem.p.json}.
 *
 * <p><b>Scope note:</b> the happy (store) path of this process runs a "Validate" script that
 * reads the JSF managed bean {@code documentUploadBean}, which does not exist in a headless
 * process test. Only the early validation branch that rejects a file <i>before</i> touching
 * that bean (an empty upload) is exercised here. The store/duplicate logic is covered
 * indirectly through {@link TestUploadDocumentItemChecker} and the document CRUD tests.
 */
@IvyProcessTest
class TestUploadDocumentItem {

  // Multi-line CallSubStart display name → address it unambiguously by PID
  // (processId-elementId from UploadDocumentItem.p.json).
  private static final BpmElement UPLOAD_START = BpmElement.pid("180CC1E9248E252B-f0");

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void emptyFile_isRejected(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    var empty = new FakeUploadedFile("empty.txt", new byte[0]);

    UploadDocumentItemData data = client.start()
        .subProcess(UPLOAD_START)
        .as().session(Ivy.session())
        .execute(businessCase, empty, false, false, "")
        .data().last();

    assertThat(data.getStatus()).isEqualTo(UploadDocumentCheckStatus.FAIL);
    assertThat(data.getMessage()).isNotBlank();
  }
}
