package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.DeleteDocumentItemData;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/DeleteDocumentItem.p.json}.
 *
 * <p>Removes a document from a case and returns a success message; the document must no
 * longer be listed afterwards.
 */
@IvyProcessTest
class TestDeleteDocumentItem {

  private static final BpmProcess DELETE = BpmProcess.name("DeleteDocumentItem");
  private static final String START = "deleteDocumentItem(ICase,IvyDocument)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void deletesAttachedDocument(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    ProcessFixtures.uploadDocument(client, businessCase, "to-delete.txt", "bye".getBytes());
    IvyDocument document = ProcessFixtures.documentsOf(client, businessCase).get(0);

    DeleteDocumentItemData data = client.start()
        .subProcess(DELETE.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase, document)
        .data().last();

    assertThat(data.getMessage()).isNotBlank();
    assertThat(ProcessFixtures.documentsOf(client, businessCase)).isEmpty();
  }
}
