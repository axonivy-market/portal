package com.axonivy.portal.components.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.components.DownloadDocumentItemData;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.process.support.ProcessFixtures;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Process test for {@code processes/Functional Processes/DownloadDocumentItem.p.json}.
 *
 * <p>Produces a PrimeFaces {@code StreamedContent} for the requested document.
 */
@IvyProcessTest
class TestDownloadDocumentItem {

  private static final BpmProcess DOWNLOAD = BpmProcess.name("DownloadDocumentItem");
  private static final String START = "downloadDocumentItem(ICase,IvyDocument)";

  @BeforeEach
  void login() {
    ProcessFixtures.login();
  }

  @Test
  void returnsStreamedContentForDocument(BpmClient client) {
    ICase businessCase = ProcessFixtures.newBusinessCase(client);
    ProcessFixtures.uploadDocument(client, businessCase, "download-me.txt", "content".getBytes());
    IvyDocument document = ProcessFixtures.documentsOf(client, businessCase).get(0);

    DownloadDocumentItemData data = client.start()
        .subProcess(DOWNLOAD.elementName(START))
        .as().session(Ivy.session())
        .execute(businessCase, document)
        .data().last();

    assertThat(data.getStreamedContent()).isNotNull();
    assertThat(data.getStreamedContent().getName()).isEqualTo("download-me.txt");
  }
}
