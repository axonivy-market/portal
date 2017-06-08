package ch.ivy.addon.portalkit.util;

import static org.junit.Assert.assertTrue;
import ch.ivy.ws.addon.DownloadDocumentResult;

/**
 * If there is a compile error, it means the web service jar is not patched. Follow this guideline to patch: <br>
 * 1. Copy patch/ch/ivy/ws/addon/DownloadDocumentResult.java_txt to src/ch/ivy/ws/addon/DownloadDocumentResult.java
 * (change file extension to java)<br>
 * 2. Build ant file patch_webservice_jar.xml to patch web service jar file, then refresh the project to load the
 * patched jar file.<br>
 * 3. Delete src/ch/ivy/ws/addon/DownloadDocumentResult.java<br>
 * 4. Check to make sure no compile error.
 */
public class DownloadDocumentResultTest {
  public void testDownloadDocumentResultTestPatched() {
    DownloadDocumentResult documentResult = new DownloadDocumentResult();
    assertTrue(documentResult.isPatched());
  }
}
