package com.axonivy.portal.selenium.common;

import java.io.File;

public final class FileHelper {
  private FileHelper() {}

  public static String getAbsolutePathToTestFile(String fileName) {
    System.out.println("PATH IS:" + System.getProperty("user.dir") + File.separator + "resources" + File.separator + "testFile" + File.separator  + fileName);
    return System.getProperty("user.dir") + File.separator + "resources" + File.separator + "testFile" + File.separator  + fileName;
  }
}
