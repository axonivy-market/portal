package com.axonivy.portal.component.masterdata;


public class MasterData {

  private static final long FILE_UPLOAD_SIZE_LIMIT = 20971520L;

  private MasterData() {}

  public static long getFileUploadSizeLimit() {
    return FILE_UPLOAD_SIZE_LIMIT;
  }

}
