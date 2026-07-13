package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.GetDocumentItemsData;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/GetDocumentItems.p.json}.
 *
 * <p>Verifies the process transforms the case's attached {@code IDocument}s into
 * {@code IvyDocument}s, and returns an empty list for a case without documents.
 */
@IvyProcessTest
class TestGetDocumentItems {

  private static final BpmProcess GET = BpmProcess.name("GetDocumentItems");
  private static final String START = "getDocumentItems(ICase)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void emptyCase_returnsNoDocuments(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);

    GetDocumentItemsData data = client.start()
        .subProcess(GET.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase)
        .data().last();

    assertThat(data.getDocuments()).isEmpty();
  }

  @Test
  void caseWithDocuments_returnsThem(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    ProcessFixtures.uploadDocument(client, businessCase, "a.txt", "aaa".getBytes());
    ProcessFixtures.uploadDocument(client, businessCase, "b.txt", "bbb".getBytes());

    GetDocumentItemsData data = client.start()
        .subProcess(GET.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase)
        .data().last();

    List<IvyDocument> documents = data.getDocuments();
    assertThat(documents).extracting(IvyDocument::getName)
        .containsExactlyInAnyOrder("a.txt", "b.txt");
  }
}
