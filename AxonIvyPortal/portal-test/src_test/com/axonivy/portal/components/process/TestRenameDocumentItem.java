package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.RenameDocumentItemData;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/RenameDocumentItem.p.json}.
 *
 * <p>Validates the new file name (forbidden chars / duplicates) and renames the document.
 * A valid name yields {@code isSuccess == true}; a name with forbidden characters is rejected.
 */
@IvyProcessTest
class TestRenameDocumentItem {

  private static final BpmProcess RENAME = BpmProcess.name("RenameDocumentItem");
  private static final String START = "renameDocumentItem(ICase,IvyDocument)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void validName_renamesDocument(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    ProcessFixtures.uploadDocument(client, businessCase, "original.txt", "data".getBytes());
    IvyDocument document = ProcessFixtures.documentsOf(client, businessCase).get(0);
    document.setName("renamed.txt");

    RenameDocumentItemData data = client.start()
        .subProcess(RENAME.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase, document)
        .data().last();

    assertThat(data.getIsSuccess()).isTrue();
    assertThat(ProcessFixtures.documentsOf(client, businessCase))
        .extracting(IvyDocument::getName)
        .containsExactly("renamed.txt");
  }

  @Test
  void forbiddenChars_isRejected(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    ProcessFixtures.uploadDocument(client, businessCase, "keep.txt", "data".getBytes());
    IvyDocument document = ProcessFixtures.documentsOf(client, businessCase).get(0);
    document.setName("bad/name.txt");

    RenameDocumentItemData data = client.start()
        .subProcess(RENAME.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase, document)
        .data().last();

    assertThat(data.getIsSuccess()).isFalse();
    assertThat(data.getMessage()).isNotBlank();
  }
}
