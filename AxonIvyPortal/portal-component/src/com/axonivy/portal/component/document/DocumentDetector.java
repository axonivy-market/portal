package com.axonivy.portal.component.document;

import java.io.InputStream;

@FunctionalInterface
public interface DocumentDetector {
  public boolean isSafe(InputStream inputStream);
}
