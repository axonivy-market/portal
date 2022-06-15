/**
 * 
 */
package ch.ivy.addon.portalkit.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;

import ch.ivy.addon.portalkit.document.IvyDocumentTransformer;
import ch.ivy.addon.portalkit.ivydata.bo.IvyDocument;
import ch.ivy.addon.portalkit.service.CaseDocumentService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.document.IDocumentService;
import ch.ivyteam.ivy.workflow.document.Path;

@SuppressWarnings("deprecation")
public class DocumentFileUtils {
  
  private DocumentFileUtils() {}

  public static boolean doesDocumentExist(ICase currentCase, String filename) {
    IDocumentService documentService = currentCase.documents();
    IDocument document = documentService.get(new Path(CaseDocumentService.EXPRESS_UPLOAD_FOLDER).append(filename));
    return document != null;
  }

  public static List<IvyDocument> expressDocuments(ICase currentCase) {
    if (currentCase == null) {
      return new ArrayList<>();
    }

    IDocumentService documentService = currentCase.documents();
    Path path = new Path(CaseDocumentService.EXPRESS_UPLOAD_FOLDER);
    List<IDocument> documents = documentService.getAllBelow(path);
    if (CollectionUtils.isEmpty(documents)) {
      return new ArrayList<>();
    }

    IvyDocumentTransformer transformer = new IvyDocumentTransformer();
    return transformer.transform(documents);
  }

  public static boolean uploadExpressDocument(ICase currentCase, InputStream inputStream, String fileName) {
    IDocumentService documentService = currentCase.documents();
    String originalFileName = FilenameUtils.getName(fileName);
    try {
      documentService.add(CaseDocumentService.EXPRESS_UPLOAD_FOLDER + "/" + originalFileName).write()
          .withContentFrom(inputStream);
      return true;
    } catch (Exception e) {
      Ivy.log().error(e);
      return false;
    }
  }
}
