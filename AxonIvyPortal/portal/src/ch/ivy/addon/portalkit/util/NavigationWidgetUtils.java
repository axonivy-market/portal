package ch.ivy.addon.portalkit.util;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;

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
        getApplicationCMS().child().folder(ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY).child().file(fileName, extension);
    byte[] oldFileContent = imageCMSObject.value().get(DEFAULT_LOCALE_TAG)
                                          .read().inputStream().readAllBytes();

    if (oldFileContent != null && oldFileContent.length > 0) {
      String newFileName = UUID.randomUUID().toString();
      ContentObject newImageCMSObject =
          getApplicationCMS().child().folder(ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY).child().file(newFileName, extension);

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
  
  public static void writeNavigateWidgetImage(NavigationDashboardWidget widget) {
    if (widget.getImageType() == null) {
      Ivy.log().warn("WidgetId [{0}] does not has imageType. Skip write to cms.", widget.getId());
      return;
    }
    
    if (StringUtils.isNotBlank(widget.getImageContent())) {
      String newImageLocation = ImageUploadUtils.imageBase64ToApplicationCMSFile(widget.getImageContent(), 
          widget.getImageType(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
      widget.setImageLocation(newImageLocation);
      widget.setImageContent(null);
    } else {
      try {
        cloneLightModeImage(widget.getImageType(), widget.getImageLocation(), widget);
      } catch (IOException e) {
        // just continue if cannot find the image
        Ivy.log().error("Cannot read data of old image", e);
      }
    }
    
    if (widget.getImageTypeDarkMode() == null) {
      Ivy.log().warn("WidgetId [{0}] does not has imageTypeDarkMode. Skip write to cms.", widget.getId());
      return;
    }
    if (StringUtils.isNotBlank(widget.getImageContentDarkMode())) {
      String newImageLocation = ImageUploadUtils.imageBase64ToApplicationCMSFile(widget.getImageContentDarkMode(), 
          widget.getImageTypeDarkMode(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
      widget.setImageLocationDarkMode(newImageLocation);
      widget.setImageContentDarkMode(null);
    } else {
      try {
        cloneDarkModeImage(widget.getImageTypeDarkMode(), widget.getImageLocationDarkMode(), widget);
      } catch (IOException e) {
        // just continue if cannot find the image
        Ivy.log().error("Cannot read data of old image", e);
      }
    }
  }
  
  public static void removeNavigateWidgetImage(NavigationDashboardWidget navWid) {
    if (!StringUtils.isAllBlank(navWid.getImageLocation(), navWid.getImageType())) {
      ImageUploadUtils.removeImage(navWid.getImageLocation(), navWid.getImageType());
    }
    if (!StringUtils.isAllBlank(navWid.getImageLocationDarkMode(), navWid.getImageTypeDarkMode())) {
      ImageUploadUtils.removeImage(navWid.getImageLocationDarkMode(), navWid.getImageTypeDarkMode());
    }
  }
}
