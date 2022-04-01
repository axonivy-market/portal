package com.axonivy.portal.developerexamples.util;

import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class FileUtils {
  private FileUtils() {}
  public static StreamedContent getStreamContent(String contentType, InputStream stream, String name) {
    return DefaultStreamedContent.builder()
        .contentType(contentType)
        .stream(() -> stream)
        .name(name)
        .build();
  }
}
