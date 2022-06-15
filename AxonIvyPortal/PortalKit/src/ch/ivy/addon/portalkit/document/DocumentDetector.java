package ch.ivy.addon.portalkit.document;

import java.io.InputStream;

/**
 * @deprecated use DocumentDetector in package com.axonivy.portal.component.document
 *
 */
@Deprecated(since="8.0.27")
@FunctionalInterface
public interface DocumentDetector {
  public boolean isSafe(InputStream inputStream);
}
