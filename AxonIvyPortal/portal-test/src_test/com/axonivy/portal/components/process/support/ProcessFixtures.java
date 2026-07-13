package com.axonivy.portal.components.process.support;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.axonivy.portal.components.GetDocumentItemsData;
import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.service.CaseDocumentService;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Shared fixtures for the portal-components document process tests. Every document process
 * ({@code Get/Delete/Download/Rename/Upload DocumentItem}) operates on a real {@link ICase}
 * with attached documents, so these helpers mint a business case and seed it through the very
 * same callable subprocesses the code ships with.
 */
public final class ProcessFixtures {

  private static final BpmProcess SET_ENTITY = BpmProcess.name("SetCaseBusinessEntity");
  private static final BpmProcess GET = BpmProcess.name("GetDocumentItems");

  private ProcessFixtures() {}

  /** Logs in a session user; required by scripts that call {@code ivy.session} / create notes. */
  public static void login() {
    Ivy.session().loginSessionUser("admin", "admin");
  }

  /** Creates and returns a fresh business case usable as the {@code businessCase} process input. */
  public static ICase newBusinessCase(BpmClient client) {
    var result = client.start()
        .subProcess(SET_ENTITY.elementName("call(String)"))
        .as().session(Ivy.session())
        .execute("fixture-entity");
    return result.workflow().activeCase().getBusinessCase();
  }

  /**
   * Attaches a document to the case directly via {@link CaseDocumentService}. The
   * UploadDocumentItem process itself relies on a JSF managed bean ({@code documentUploadBean})
   * that is unavailable in a process test, so seeding is done through the service API instead.
   */
  public static void uploadDocument(BpmClient client, ICase businessCase, String fileName, byte[] content) {
    CaseDocumentService.newInstance(businessCase).upload(fileName, new ByteArrayInputStream(content));
  }

  /** Returns the documents currently attached to the case via the real GetDocumentItems process. */
  public static List<IvyDocument> documentsOf(BpmClient client, ICase businessCase) {
    var result = client.start()
        .subProcess(GET.elementName("getDocumentItems(ICase)"))
        .as().session(Ivy.session())
        .execute(businessCase);
    GetDocumentItemsData data = result.data().last();
    return data.getDocuments();
  }
}
