package ch.ivy.gawfs.portalKitCopies;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ch.ivy.gawfs.portalKitCopies.CaseUtils;
import ch.ivy.gawfs.portalKitCopies.DocumentVO;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.scripting.objects.File;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.document.IDocumentService;
import ch.ivyteam.ivy.workflow.document.Path;

public class CaseDocumentService {

  private long caseId;

  public CaseDocumentService(long caseId) {
    this.caseId = caseId;
  }

  public boolean upload(String filename, InputStream content) {
    ICase iCase = CaseUtils.findcase(caseId);
    try {
      documentsOf(iCase).add(filename).write().withContentFrom(content);
    } catch (PersistencyException e) {
      Ivy.log().error("Cannot add document " + filename, e);
      return false;
    }
    return true;
  }

  public List<DocumentVO> getAll() {
    ICase iCase = CaseUtils.findcase(caseId);
    List<IDocument> documents = documentsOf(iCase).getAll();
    List<DocumentVO> documentVOs = new ArrayList<>();
    for (IDocument document : documents) {
      documentVOs.add(convertDocumentToVO(document));
    }
    return documentVOs;
  }

  /**
   * @throws IOException cannot find the file to delete
   */
  public void delete(String filename) throws IOException {
    ICase iCase = CaseUtils.findcase(caseId);
    IDocument document = documentsOf(iCase).get(new Path(filename));
    File file = new File(document.getPath().asString());
    file.delete();
    documentsOf(iCase).delete(document);
  }

  /**
   * @throws IOException cannot find the file to download
   */
  public StreamedContent download(String filename) throws IOException {
    ICase iCase = CaseUtils.findcase(caseId);
    IDocument document = documentsOf(iCase).get(new Path(filename));
    InputStream inputStream = document.read().asStream();
    String contentType = getContentType(document);

    StreamedContent content = new DefaultStreamedContent(inputStream, contentType, filename);
    return content;
  }

  public boolean doesDocumentExist(String filename) {
    ICase iCase = CaseUtils.findcase(caseId);
    IDocument document = documentsOf(iCase).get(new Path(filename));
    return document != null;
  }

  private IDocumentService documentsOf(ICase iCase) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<IDocumentService>() {

        @Override
        public IDocumentService call() throws Exception {
          return iCase.documents();
        }
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private DocumentVO convertDocumentToVO(IDocument document) {
    DocumentVO documentVO = new DocumentVO();
    documentVO.setName(document.getName());
    documentVO.setSize(document.getSize() < 1024 ? "1KB" : document.getSize() / 1024 + "KB");
    documentVO.setModifiedDate(document.getLastModification().getTimestamp().toJavaDate());
    return documentVO;
  }

  private String getContentType(IDocument document) throws IOException {
    String contentType;
    File file = new File(document.getPath().asString());
    contentType = Files.probeContentType(file.getJavaFile().toPath());
    return contentType;
  }



}
