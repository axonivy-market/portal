package com.axonivy.portal.components.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.components.ivydata.bo.IvyDocument;
import com.axonivy.portal.components.service.IvyAdapterService;

import ch.ivyteam.ivy.process.call.SubProcessCallStart;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter;
import ch.ivyteam.ivy.process.call.SubProcessSearchFilter.SearchScope;

public final class DocumentUtils {
  public static final String DOC_FACTORY_SIGNATURE = "previewDocumentByInputStream(String,String,java.io.InputStream)";
  public static final String DOC_FACTORY_GET_SUPPORTED_FILE_TYPES_SIGNATURE = "getSupportedTypeForPreview()";
  private static final String[] SUPPORTED_PREVIEW_FILE_TYPES = {".pdf", ".txt", ".log", ".jpg", ".jpeg", ".bmp", ".png"};
  private static final List<String> DOC_FACTORY_SUPPORTED_PREVIEW_FILE_TYPES;
  
  private static boolean isDocFactoryFound;
  
  static {
    SubProcessSearchFilter filter = SubProcessSearchFilter.create().setSearchScope(SearchScope.SECURITY_CONTEXT)
        .setSignature(DOC_FACTORY_SIGNATURE).toFilter();

    List<SubProcessCallStart> subProcessStartList = SubProcessCallStart.find(filter);
    isDocFactoryFound = CollectionUtils.isNotEmpty(subProcessStartList);

    DOC_FACTORY_SUPPORTED_PREVIEW_FILE_TYPES = getDocFactorySupportedFileTypes();
  }
  
  private DocumentUtils() {}

  public static List<String> getDocFactorySupportedFileTypes() {
    if (!isDocFactoryFound) {
      return List.of();
    }

    Map<String, Object> response = IvyAdapterService.startSubProcessInSecurityContext(DocumentUtils.DOC_FACTORY_GET_SUPPORTED_FILE_TYPES_SIGNATURE, null);
    if (response != null && response.get("supportedTypes") != null) {
      return objToListOfStrHelper(response.get("supportedTypes"));
    }

    return List.of();
  }

  public static boolean isSupportedPreviewType(IvyDocument document) {
    if (isDocFactoryFound) {
      return Strings.CI.endsWithAny(document.getPath(), SUPPORTED_PREVIEW_FILE_TYPES) 
          || Strings.CI.endsWithAny(document.getPath(), DOC_FACTORY_SUPPORTED_PREVIEW_FILE_TYPES.toArray(new String[0]));
    }
    return Strings.CI.endsWithAny(document.getPath(), SUPPORTED_PREVIEW_FILE_TYPES);
  }
  
  public static boolean isImageDocument(IvyDocument document) {
    return document != null && Strings.CI.startsWith(document.getContentType(), "image/");
  }
  
  public static StreamedContent findDocFactoryAndConvert(StreamedContent streamedContent) throws IOException {
    if (streamedContent != null 
        && StringUtils.isNotEmpty(streamedContent.getName()) 
        && Strings.CI.endsWithAny(streamedContent.getName(), SUPPORTED_PREVIEW_FILE_TYPES)) {
      return streamedContent;
    }

    if (streamedContent != null && streamedContent.getStream() != null) {
      Map<String, Object> params = new HashMap<String, Object>();
      // auto-closed by try-with-resources
      try (InputStream is = streamedContent.getStream().get()) {

        if (is != null) {
          params.put("inputStream", is);
          params.put("fileName", streamedContent.getName());
          params.put("contentType", streamedContent.getContentType());
          Map<String, Object> response = IvyAdapterService
              .startSubProcessInSecurityContext(DocumentUtils.DOC_FACTORY_SIGNATURE, params);

          if (response != null && response.get("inputStream") != null) {
            DefaultStreamedContent returnDefaultStreamedContent = DefaultStreamedContent.builder()
                .stream(() -> (InputStream) response.get("inputStream"))
                .name(streamedContent.getName())
                // DocFactory always returns stream as pdf
                .contentType("application/pdf").build();
            return returnDefaultStreamedContent;
          }
        }
      }
    }
    return streamedContent;
  }

  private static List<String> objToListOfStrHelper(Object obj) {
    if (obj == null) {
      return List.of();
    }

    if (obj instanceof List<?>) {
      List<?> rawList = (List<?>) obj;
      return rawList.stream()
        .filter(e -> e instanceof String)
        .map(e -> (String) e)
        .toList();
    }

    return List.of();
  }
}
