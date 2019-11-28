package ch.ivy.addon.portalkit.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ch.ivy.addon.portalkit.document.DocumentDetector;
import ch.ivy.addon.portalkit.document.DocumentDetectorFactory;
import ch.ivy.addon.portalkit.document.DocumentExtensionConstants;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.ivydata.bo.IvyDocument;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.document.IDocumentService;
import ch.ivyteam.ivy.workflow.document.Path;

public class CaseDocumentService {

  public static final String EXPRESS_UPLOAD_FOLDER = "AxonIvyExpress";
  private ICase iCase;

  private CaseDocumentService(ICase iCase) {
    this.iCase = iCase;
  }
  
  public static CaseDocumentService newInstance(ICase iCase) {
    return new CaseDocumentService(iCase);
  }

  public IDocument upload(String filename, InputStream content) {
    try {
      return documentsOf(iCase).add(filename).write().withContentFrom(content);
    } catch (PersistencyException e) {
      Ivy.log().error("Error in uploading the document {0} ", e, filename);
      return null;
    }
  }

  public List<IDocument> getAll() {
    List<IDocument> documents = new ArrayList<>(getAllDocumentsOf(iCase));
    List<IDocument> expressDocs = new ArrayList<>();
    for (IDocument doc : documents) {
      if (doc.getPath().asString().contains(EXPRESS_UPLOAD_FOLDER)) {
        expressDocs.add(doc);
      }
    }
    documents.removeAll(expressDocs);
    return new ArrayList<>(documents);
  }

  /**
   * We make it as deprecated from 8.0
   * Please refer to sub-function process DeleteDocument
   * @param document
   */
  @Deprecated
  public void delete(IDocument document) {
    documentsOf(iCase).delete(document);
  }

  /**
   * @param document
   * @return streamed content
   */
  public StreamedContent download(IvyDocument document) {
    InputStream inputStream = documentsOf(iCase).get(Long.valueOf(document.getId())).read().asStream();
    return new DefaultStreamedContent(inputStream, document.getContentType(), document.getName());
  }

  public boolean doesDocumentExist(String filename) {
    IDocument document = documentsOf(iCase).get(new Path(filename));
    return document != null && !document.getPath().asString().contains(EXPRESS_UPLOAD_FOLDER);
  }

  public static boolean isDocumentTypeValid(String filename) {
    String fileType = FilenameUtils.getExtension(filename);
    List<String> allowedFileType = getAllowedUploadFileType();
    // If admin leave the config blank, mean all the file type are accepted
    if (allowedFileType.isEmpty()) {
      return true;
    } else {
      return allowedFileType.contains(StringUtils.lowerCase(fileType));
    }
  }

  public static boolean isDocumentSafe(UploadedFile uploadedFile) {
    if (uploadedFile != null) {
      DocumentDetectorFactory documentDetectorFactory = new DocumentDetectorFactory();
      DocumentDetector documentDetector = documentDetectorFactory
          .getDocumentDetector(FilenameUtils.getExtension(StringUtils.lowerCase(uploadedFile.getFileName())));
      if (documentDetector != null) {
        try {
          return documentDetector.isSafe(uploadedFile.getInputstream());
        } catch (IOException e) {
          Ivy.log().error(e);
          return false;
        }
      }
      // File type doesn't support for scanning inside script
      else
        return true;
    }
    return false;
  }

  public static boolean enableScriptCheckingForUploadedDocument() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    String enableScriptCheckingForUploadedDocument = globalSettingService
        .findGlobalSettingValue(GlobalVariable.ENABLE_SCRIPT_CHECKING_FOR_UPLOADED_DOCUMENT.toString());
    return Boolean.parseBoolean(enableScriptCheckingForUploadedDocument);
  }

  private IDocumentService documentsOf(ICase iCase) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> iCase.documents()); 
    } catch (Exception e) {
      throw new PortalException(e);
    }
  }
  
  private List<IDocument> getAllDocumentsOf(ICase iCase) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> iCase.documents().getAll());
    } catch (Exception e) {
      throw new PortalException(e);
    }
  }

  private static List<String> getAllowedUploadFileType() {
    GlobalSettingService globalSettingService = new GlobalSettingService();
    if (globalSettingService.isGlobalSettingAvailable(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.toString())) {
      String supportedFileType =
          globalSettingService.findGlobalSettingValue(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION.toString());
      if (StringUtils.EMPTY.equals(supportedFileType)) {
        return new ArrayList<>();
      } else {
        String[] supportedFileTypeArr = supportedFileType.toLowerCase().split("\\s*,[,\\s]*");
        return Arrays.asList(supportedFileTypeArr);
      }
    }
    return DocumentExtensionConstants.DEFAULT_WHITELIST_EXTENSION;
  }

}
