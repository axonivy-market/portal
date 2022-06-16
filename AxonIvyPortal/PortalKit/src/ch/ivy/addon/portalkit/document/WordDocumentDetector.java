package ch.ivy.addon.portalkit.document;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.poifs.macros.VBAMacroReader;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * @deprecated use WordDocumentDetector in package com.axonivy.portal.component.document
 *
 */
@Deprecated(since="8.0.27")
public class WordDocumentDetector implements DocumentDetector{
  
  @Override
  public boolean isSafe(InputStream inputStream) {
    return !hasMacro(inputStream);
  }


  private boolean hasMacro(InputStream inputStream) {
    try (VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream)) {
      return vbaMacroReader.readMacros() != null && !vbaMacroReader.readMacros().isEmpty();
    } catch (IllegalArgumentException ex) {
      // Not contain any VBA script
      Ivy.log().debug(ex);
      return false;
    } catch (IOException e) {
      Ivy.log().error(e);
      return false;
    }
  }
}
