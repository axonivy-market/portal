package ch.ivy.addon.portalkit.document;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.poifs.macros.VBAMacroReader;

public class ExcelDocumentDetector implements DocumentDetector{
  
  @Override
  public boolean isSafe(InputStream inputStream) {
    boolean isSafe = false;
    if(!hasMacro(inputStream)){
      isSafe = true;
    }
    return isSafe;
  }


  private boolean hasMacro(InputStream inputStream) {
    try(VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream);) {
      return vbaMacroReader.readMacros() != null && !vbaMacroReader.readMacros().isEmpty();
    }
    catch(IllegalArgumentException ex) {
      //Not contain any VBA script
      return false;
    } catch (IOException e) {
      //Can not read this stream
      return false;
    }
  }
}
