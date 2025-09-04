package com.axonivy.portal.components.util;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Strings;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;

import ch.ivyteam.ivy.process.call.SubProcessCallStart;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;

public final class DocumentUtils {
  public static final String DOC_FACTORY_SIGNATURE = "previewDocumentByStream(org.primefaces.model.StreamedContent)";
  private static boolean isDocFactoryFound;
  static {
    SubProcessSearchFilter filter = SubProcessSearchFilter.create().setSearchScope(SearchScope.SECURITY_CONTEXT)
        .setSignature(DOC_FACTORY_SIGNATURE).toFilter();

    List<SubProcessCallStart> subProcessStartList = SubProcessCallStart.find(filter);
    isDocFactoryFound = CollectionUtils.isNotEmpty(subProcessStartList);
  }
  private DocumentUtils() {}

  public static boolean isSupportedPreviewType(IvyDocument document) {
    if (isDocFactoryFound) {
      return Strings.CI.endsWithAny(document.getPath(), ".pdf", ".txt", ".log", ".jpg", ".jpeg", ".bmp", ".png", ".eml", ".docx", ".doc", ".xls", ".xlsx");
    }
    return Strings.CI.endsWithAny(document.getPath(), ".pdf", ".txt", ".log", ".jpg", ".jpeg", ".bmp", ".png");
  }
  
  public static boolean isImageDocument(IvyDocument document) {
    return document != null && Strings.CI.startsWith(document.getContentType(), "image/");
  }
}
