package com.axonivy.portal.components.document;

import java.io.InputStream;

@FunctionalInterface
public interface DocumentDetector {
  public boolean isSafe(InputStream inputStream);
}
