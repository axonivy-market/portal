package portal.guitest.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class FileHelper {
  private FileHelper() {}

  public static String getAbsolutePathToTestFile(String fileName) {
    return System.getProperty("user.dir") + "\\resources\\testFile\\" + fileName;
  }

  public static void copyAndReplaceExistingFile(String copyFilePath, String destinationPath) {
    try {
      Path copied = Paths.get(copyFilePath);
      Path destination = Paths.get(destinationPath);
      Files.copy(copied, destination, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
