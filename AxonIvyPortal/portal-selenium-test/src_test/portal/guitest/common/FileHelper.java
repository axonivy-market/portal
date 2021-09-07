package portal.guitest.common;

public final class FileHelper {
  private FileHelper() {}

  public static String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }
}
