package ch.ivy.addon.portalkit.document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.macros.VBAMacroReader;

import ch.ivyteam.ivy.environment.Ivy;

public class ExcelDocumentDetector implements DocumentDetector {

  @Override
  public boolean isSafe(InputStream inputStream) {
    return !containsMacrosOrActiveX(inputStream);
  }

  private boolean containsMacrosOrActiveX(InputStream inputStream) {
    try (POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        VBAMacroReader vbaMacroReader = new VBAMacroReader(fs)) {

      // Check for VBA macros
      Map<String, String> macros = vbaMacroReader.readMacros();
      if (macros != null && !macros.isEmpty()) {
        Ivy.log().warn("Potential malicious Excel document: VBA macros detected.");
        return true;
      }

      // Check for ActiveX controls
      if (fs.getRoot().hasEntry("Macros") || fs.getRoot().hasEntry("ActiveX")) {
        Ivy.log().warn("Potential malicious Excel document: ActiveX controls detected.");
        return true;
      }

    } catch (IllegalArgumentException ex) {
      Ivy.log().info("No VBA macros found, safe Excel document: " + ex.getMessage());
    } catch (IOException e) {
      Ivy.log().error("Error scanning Excel document for macros/ActiveX", e);
    }
    return false;
  }
}
