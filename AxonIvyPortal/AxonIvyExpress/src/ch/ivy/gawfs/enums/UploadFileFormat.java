package ch.ivy.gawfs.enums;

import ch.ivyteam.ivy.environment.Ivy;

public enum UploadFileFormat {
  PDF("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/PDF"),
  WORD("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/Word"),
  EXCEL("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/Excel"),
  OTHERS("/Dialogs/workflowCreation/FormDefinition/UploadFileFormat/OtherFormats");

  private final String cmsURI;

  UploadFileFormat(String cmsURI) {
    this.cmsURI = cmsURI;
  }

  public String getLabel() {
    return Ivy.cms().co(cmsURI);
  }
}
