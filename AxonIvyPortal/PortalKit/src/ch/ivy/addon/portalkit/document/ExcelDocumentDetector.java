package ch.ivy.addon.portalkit.document;

import java.io.InputStream;

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
/*    try(VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream);) {
      return vbaMacroReader.readMacros() != null && !vbaMacroReader.readMacros().isEmpty();
    }
    catch(IllegalArgumentException ex) {
      //Not contain any VBA script
      Ivy.log().debug(ex);*/
      return false;
/*    } catch (IOException e) {
      Ivy.log().error(e);
      return false;
    }*/
  }
}
