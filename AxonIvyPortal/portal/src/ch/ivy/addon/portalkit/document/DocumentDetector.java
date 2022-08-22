package ch.ivy.addon.portalkit.document;

import java.io.InputStream;

@FunctionalInterface
public interface DocumentDetector {
  public boolean isSafe(InputStream inputStream);
}
