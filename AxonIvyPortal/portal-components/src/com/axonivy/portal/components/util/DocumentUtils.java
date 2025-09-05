package com.axonivy.portal.components.util;

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
  private static final String[] SUPPORTED_PREVIEW_FILE_TYPES = {".pdf", ".txt", ".log", ".jpg", ".jpeg", ".bmp", ".png"};
  private static final String[] DOC_FACTORY_SUPPORTED_PREVIEW_FILE_TYPES = {".eml", ".docx", ".doc", ".xls", ".xlsx"};
  
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
      return Strings.CI.endsWithAny(document.getPath(), SUPPORTED_PREVIEW_FILE_TYPES) 
          || Strings.CI.endsWithAny(document.getPath(), DOC_FACTORY_SUPPORTED_PREVIEW_FILE_TYPES);
    }
    return Strings.CI.endsWithAny(document.getPath(), SUPPORTED_PREVIEW_FILE_TYPES);
  }
  
  public static boolean isImageDocument(IvyDocument document) {
    return document != null && Strings.CI.startsWith(document.getContentType(), "image/");
  }
  
  public static StreamedContent findDocFactoryAndConvert(StreamedContent streamedContent) {
    if (streamedContent != null 
        && StringUtils.isNoneEmpty(streamedContent.getName()) 
        && Strings.CI.endsWithAny(streamedContent.getName(), SUPPORTED_PREVIEW_FILE_TYPES)) {
      return streamedContent;
    }
    Map<String, Object> params = new HashMap<String, Object>();

    InputStream is = streamedContent.getStream().get();
    params.put("inputStream", is);
    params.put("fileName", streamedContent.getName());
    params.put("contentType", streamedContent.getContentType());

    Map<String, Object> response = IvyAdapterService.startSubProcessInSecurityContext(DocumentUtils.DOC_FACTORY_SIGNATURE, params);

    if (response != null && response.get("inputStream") != null){
      InputStream convertedIS = (InputStream)response.get("inputStream");
      
      return DefaultStreamedContent
          .builder()
          .stream(() -> convertedIS)
          .name(streamedContent.getName())
          // DocFactory always return stream as pdf
          .contentType("application/pdf")
          .build();
    } 
    return streamedContent;
  }
}
