package ch.ivy.addon.portalkit.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

import ch.ivy.addon.portalkit.configuration.GlobalSetting;
import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.PersistencyException;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.document.IDocument;
import ch.ivyteam.ivy.workflow.document.IDocumentService;
import ch.ivyteam.ivy.workflow.document.Path;

public class CaseDocumentService {

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
    return getAllDocumentsOf(iCase);
  }

  /**
   * @param document
   * @return streamed content
   */
  public StreamedContent download(IvyDocument document) {
    InputStream inputStream = documentsOf(iCase).get(Long.valueOf(document.getId())).read().asStream();
    return DefaultStreamedContent
        .builder()
        .stream(() -> inputStream)
        .contentType(document.getContentType())
        .name(document.getName())
        .build();
  }

  public boolean doesDocumentExist(String filename) {
    IDocument document = documentsOf(iCase).get(new Path(filename));
    return document != null;
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

  private IDocumentService documentsOf(ICase iCase) {
    try {
      return Sudo.call(iCase::documents);
    } catch (Exception e) {
      throw new PortalException(e);
    }
  }

  private List<IDocument> getAllDocumentsOf(ICase iCase) {
    try {
      return Sudo.call(() -> iCase.documents().getAll());
    } catch (Exception e) {
      throw new PortalException(e);
    }
  }

  public static List<String> getAllowedUploadFileType() {
    GlobalSettingService globalSettingService = GlobalSettingService.getInstance();
    GlobalSetting documentSetting =
        globalSettingService.findGlobalSettingByGlobalVariable(GlobalVariable.UPLOAD_DOCUMENT_WHITELIST_EXTENSION);
    if (StringUtils.isBlank(documentSetting.getValue())) {
      return new ArrayList<>();
    } else {
      String[] supportedFileTypeArr = documentSetting.getValue().toLowerCase().split("\\s*,[,\\s]*");
      return Arrays.asList(supportedFileTypeArr);
    }
  }

}
