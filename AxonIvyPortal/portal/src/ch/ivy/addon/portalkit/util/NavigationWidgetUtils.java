package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivy.addon.portalkit.constant.DashboardConstants;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.exec.ContentManagement;

public class NavigationWidgetUtils {
  public static final String DOT = ".";
  public static final String DEFAULT_LOCALE_TAG = "en";

  private static void cloneImage(String extension, String oldImageLocation,
      NavigationDashboardWidget newWidget, boolean isDarkMode) throws IOException {
    if (StringUtils.isBlank(extension)
        || StringUtils.isBlank(oldImageLocation)) {
      return;
    }

    String fileName = getFileNameOfImage(oldImageLocation);
    ContentObject imageCMSObject =
        getApplicationCMS().child().folder(DashboardConstants.NAVIGATION_WIDGET_IMAGE_DIRECTORY).child().file(fileName, extension);
    byte[] oldFileContent = imageCMSObject.value().get(DEFAULT_LOCALE_TAG)
                                          .read().inputStream().readAllBytes();

    if (oldFileContent != null && oldFileContent.length > 0) {
      String newFileName = UUID.randomUUID().toString();
      ContentObject newImageCMSObject =
          getApplicationCMS().child().folder(DashboardConstants.NAVIGATION_WIDGET_IMAGE_DIRECTORY).child().file(newFileName, extension);

      ImageUploadUtils.readObjectValueOfDefaultLocale(newImageCMSObject).write().bytes(oldFileContent);
      if (isDarkMode) {
        newWidget.setImageLocationDarkMode(newImageCMSObject.uri());
      } else {
        newWidget.setImageLocation(newImageCMSObject.uri());
      }
    }
  }
  
  public static void cloneDarkModeImage(String extension, String oldImageLocation,
      NavigationDashboardWidget newWidget) throws IOException {
    cloneImage(extension, oldImageLocation, newWidget, true);
  }
  
  public static void cloneLightModeImage(String extension, String oldImageLocation,
      NavigationDashboardWidget newWidget) throws IOException {
    cloneImage(extension, oldImageLocation, newWidget, false);
  }
  
  private static ContentObject getApplicationCMS() {
    return ContentManagement.cms(IApplication.current()).root();
  }
  
  private static String getFileNameOfImage(String imageLocation) {
    return StringUtils.defaultIfEmpty(imageLocation, "").substring(imageLocation.lastIndexOf('/') + 1);
  }
}
