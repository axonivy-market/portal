package com.axonivy.portal.selenium.common;

import java.io.File;

public final class FileHelper {
  private FileHelper() {}

  public static String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + File.pathSeparator + "resources" + File.pathSeparator + "testFile" + File.pathSeparator  + fileName;
  }
}
