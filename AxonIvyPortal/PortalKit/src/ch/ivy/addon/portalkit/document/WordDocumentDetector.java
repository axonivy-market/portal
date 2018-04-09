package ch.ivy.addon.portalkit.document;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.poifs.macros.VBAMacroReader;

import ch.ivyteam.ivy.environment.Ivy;

public class WordDocumentDetector implements DocumentDetector{
  
  @Override
  public boolean isSafe(InputStream inputStream) {
    if (hasMacro(inputStream)) {
      return false;
    }

    return true;
  }


  private boolean hasMacro(InputStream inputStream) {
    try (VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream)) {
      return vbaMacroReader.readMacros() != null && !vbaMacroReader.readMacros().isEmpty();
    } catch (IllegalArgumentException ex) {
      // Not contain any VBA script
      Ivy.log().debug("This file doesn't contain any macro");
      return false;
    } catch (IOException e) {
      Ivy.log().error("Can't read input stream");
      return false;
    }
  }
}
