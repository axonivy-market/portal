package ch.ivy.addon.portalkit.document;

import java.io.InputStream;

public interface DocumentDetector {
  public boolean isSafe(InputStream inputStream);
}
