package com.axonivy.portal.util;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.document.SVGSecurityScanner;

import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public class WelcomeWidgetUtils {

  public static final String IMAGE_DIRECTORY = "DashboardWelcomeWidget";
  public static final String DEFAULT_LOCALE_AND_DOT = "_en.";
  public static final String DARK_MODE = "_darkmode";
  public static final String DEFAULT_LOCALE_TAG = "en";
  public static final String FOLDER_DELIMITER = "/";
  public static final String IMAGE_PREFIX = "image";

  public static boolean isObsoleteImageData(String imageLocation, String widgetId) {
    return imageLocation.startsWith(IMAGE_DIRECTORY.concat(FOLDER_DELIMITER).concat(widgetId));
  }

  /**
   * Silent migrate old welcome widget
   * @param widgetId welcome widget ID
   * @param imageType imageType of welcome widget
   * @param imageLocation location of image
   * 
   */
  public static void migrateWelcomeWidget(String widgetId, String imageType, String imageLocation) {
    // Create new file in CMS and update image location
    byte[] oldFileContent = getImageAsByteData(imageLocation);
    if (oldFileContent == null) {
      return;
    }
    String fileExtension = getFileTypeOfImage(imageType);
    imageLocation = widgetId.concat(DEFAULT_LOCALE_AND_DOT).concat(fileExtension);
    ContentObject imageObject = getImageContentObject(getFileNameOfImage(imageLocation), fileExtension);
    if (imageObject != null) {
      readObjectValueOfDefaultLocale(imageObject).write().bytes(oldFileContent);
    }
  }

  public static byte[] getImageAsByteData(String filePath) {
    byte[] fileContent = null;
    try {
      File file = new File(filePath);
      fileContent = FileUtils.readFileToByteArray(file.getJavaFile());
      file.getParentFile().forceDelete();
      Ivy.log().debug("Remove old image file {0} of Image widget", filePath);
    } catch (IOException e) {
      // just continue if cannot find the image
      Ivy.log().error("Cannot read data of old image", e);
    }
    return fileContent;
  }

  public static String generateGreetingTextByTime(int clientTime) {
    String greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Afternoon";
    if (clientTime < 12) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Morning";
    } else if (clientTime > 18) {
      greetingTextCms = "/ch.ivy.addon.portalkit.ui.jsf/dashboard/configuration/WelcomeWidget/Greeting/Evening";
    }
    return greetingTextCms;
  }

  public static int parseClientTime() {
    int parsedClientTime = 0;
    String clientTime = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clientTime");
    if (clientTime != null) {
      parsedClientTime = Integer.parseInt(clientTime);
    }
    return parsedClientTime;
  }

  public static ContentObject getImageContentObject(String imageLocation, String imageType) {
    String fileName = getFileNameOfImage(imageLocation);
    String fileExtension = getFileTypeOfImage(imageType);
    if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(fileExtension)) {
      return null;
    }
    return getApplicationCMS().child().folder(IMAGE_DIRECTORY).child()
        .file(fileName, fileExtension);
  }

  public static void removeWelcomeImage(String imageLocation, String imageType) {
    if (StringUtils.isNotEmpty(imageLocation) && StringUtils.isNotEmpty(imageType)) {
      ContentObject imageObject = getImageContentObject(imageLocation, imageType);
      if (imageObject != null) {
        imageObject.delete();
      }
    }
  }

  private static ContentObject getApplicationCMS() {
    return ContentManagement.cms(IApplication.current()).root();
  }

  public static ContentObjectValue readObjectValueOfDefaultLocale(ContentObject contentObject) {
    if (contentObject == null) {
      return null;
    }
    return contentObject.value().get(DEFAULT_LOCALE_TAG);
  }

  public static String getFileTypeOfImage(String imageType) {
    String[] types = StringUtils.defaultIfEmpty(imageType, EMPTY).split(FOLDER_DELIMITER);
    List<String> filteredTypes = Stream.of(types).filter(type -> !IMAGE_PREFIX.equals(type)).toList();
    return filteredTypes.get(filteredTypes.size() - 1);
  }

  public static String getFileNameOfImage(String imageLocation) {
    return StringUtils.defaultIfEmpty(imageLocation, EMPTY).split(DEFAULT_LOCALE_AND_DOT)[0];
  }
  
  /**
   * Create new image with new widgetId
   * 
   * @param widget
   */
  public static void writeWelcomeWidgetImage(WelcomeDashboardWidget widget) {
    if (widget.getImageType() == null && widget.getImageTypeDarkMode() == null) {
      Ivy.log().warn("WidgetId [{0}] does not has imageType. Skip write to cms.", widget.getId());
      return;
    }
    String widgetId = DashboardWidgetUtils.generateNewWidgetId(WELCOME);
    if (StringUtils.isNotBlank(widget.getImageLocation()) && StringUtils.isNotBlank(widget.getImageType())) {
      String rawExtension = WelcomeWidgetUtils.getFileTypeOfImage(widget.getImageType());
      String fileExtension = normalizeSvgExtension(rawExtension);
      String imageLocation = widgetId.concat(WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT).concat(fileExtension);
      ContentObject newImageObject = WelcomeWidgetUtils.getImageContentObject(getFileNameOfImage(imageLocation), fileExtension);
      if (StringUtils.isNotBlank(widget.getImageContent())) {
        byte[] content = Base64.getDecoder().decode(widget.getImageContent());
        if (isPotentialSvg(fileExtension, content)) {
          if (isUnsafeSvg(content)) {
            Ivy.log().warn("WidgetId [{0}] image rejected: unsafe SVG content (base64 path).", widget.getId());
            widget.setImageContent(null);
            widget.setImageLocation(null);
            return;
          }
        }
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(content);
        widget.setImageLocation(imageLocation);
        widget.setImageContent(null);
      } else {
        byte[] oldFileContent = WelcomeWidgetUtils.getImageAsByteData(widget.getImageLocation());
        if (oldFileContent != null) {
          if (isPotentialSvg(fileExtension, oldFileContent) && isUnsafeSvg(oldFileContent)) {
            Ivy.log().warn("WidgetId [{0}] image clone rejected: unsafe SVG content (clone path).", widget.getId());
            widget.setImageLocation(null);
            return;
          }
          WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(oldFileContent);
        }
      }
    }
    if (StringUtils.isNotBlank(widget.getImageLocationDarkMode()) && StringUtils.isNotBlank(widget.getImageTypeDarkMode())) {
      String fileExtensionDarkMode = WelcomeWidgetUtils.getFileTypeOfImage(widget.getImageTypeDarkMode());
      String imageLocationDarkMode = widgetId.concat(WelcomeWidgetUtils.DARK_MODE).concat(WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT).concat(fileExtensionDarkMode);
      ContentObject newImageObjectDarkMode = WelcomeWidgetUtils.getImageContentObject(getFileNameOfImage(imageLocationDarkMode), fileExtensionDarkMode);
      if (StringUtils.isNotBlank(widget.getImageContentDarkMode())) {
        // If has defined content, create new image
        byte[] content = Base64.getDecoder().decode(widget.getImageContentDarkMode());
        // Handle sanitize SVG
        if (isUnsafeSvg(content)) {
          Ivy.log().warn("WidgetId [{0}] image rejected: unsafe SVG content (base64 path).", widget.getId());
          widget.setImageContent(null);
          widget.setImageLocation(null);
          return;
        }
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObjectDarkMode).write().bytes(content);
        widget.setImageLocationDarkMode(imageLocationDarkMode);
        widget.setImageContentDarkMode(null);
      } else {
        // If has defined location, clone new image
        byte[] oldFileContentDarkMode = WelcomeWidgetUtils.getImageAsByteData(widget.getImageLocationDarkMode());
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObjectDarkMode).write().bytes(oldFileContentDarkMode);
      }
    }
  }

  public static void cloneImage(String extension, String oldImageLocation,
      WelcomeDashboardWidget newWidget, boolean isDarkMode) throws IOException {
    if (StringUtils.isBlank(extension)
        || StringUtils.isBlank(oldImageLocation)) {
      return;
    }

    String newImageLocation = newWidget.getId()
        .concat(isDarkMode ? DARK_MODE : "").concat(DEFAULT_LOCALE_AND_DOT)
        .concat(extension);

    ContentObject oldImageObject = getImageContentObject(
        WelcomeWidgetUtils.getFileNameOfImage(oldImageLocation), extension);
    ContentObject newImageObject = getImageContentObject(
        WelcomeWidgetUtils.getFileNameOfImage(newImageLocation), extension);

    byte[] oldFileContent = oldImageObject.value().get(DEFAULT_LOCALE_TAG)
        .read().inputStream().readAllBytes();
    if (oldFileContent != null && oldFileContent.length > 0) {
      WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write()
          .bytes(oldFileContent);
    }

    if (isDarkMode) {
      newWidget.setImageLocationDarkMode(newImageLocation);
    } else {
      newWidget.setImageLocation(newImageLocation);
    }
  }

  static boolean isPotentialSvg(String extension, byte[] data) {
    if (isSvgExtension(extension)) {
      return true;
    }
    return isSvgContent(data);
  }

  static boolean isSvgExtension(String extension) {
    if (extension == null) {
      return false;
    }
    String lower = extension.toLowerCase();
    return "svg".equals(lower) || lower.startsWith("svg+") || "svgz".equals(lower);
  }

  static boolean isSvgContent(byte[] data) {
    if (data == null || data.length < 4) {
      return false;
    }
    String prefix = new String(data, 0, Math.min(data.length, 200), StandardCharsets.UTF_8).trim().toLowerCase();
    return prefix.startsWith("<svg");
  }

  static boolean isUnsafeSvg(byte[] data) {
    try {
      String content = new String(data, StandardCharsets.UTF_8);
      return !SVGSecurityScanner.isSafe(content);
    } catch (Exception e) {
      Ivy.log().warn("Error during SVG safety check: {0}", e.getMessage());
      return true;
    }
  }

  static String normalizeSvgExtension(String ext) {
    if (ext == null) {
      return EMPTY;
    }
    String lower = ext.toLowerCase();
    if (lower.startsWith("svg")) {
  return "svg";
    }
    return ext;
  }
}
