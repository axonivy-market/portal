package com.axonivy.portal.util;

import static ch.ivy.addon.portalkit.enums.DashboardWidgetType.WELCOME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

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
    if (widget.getImageType() == null) {
      Ivy.log().warn("WidgetId [{0}] does not has imageType. Skip write to cms.", widget.getId());
      return;
    }
    if (StringUtils.isNotBlank(widget.getImageLocation())) {
      String widgetId = DashboardWidgetUtils.generateNewWidgetId(WELCOME);
      String fileExtension = WelcomeWidgetUtils.getFileTypeOfImage(widget.getImageType());
      String imageLocation = widgetId.concat(WelcomeWidgetUtils.DEFAULT_LOCALE_AND_DOT).concat(fileExtension);
      ContentObject newImageObject = WelcomeWidgetUtils.getImageContentObject(WelcomeWidgetUtils.getFileNameOfImage(imageLocation), fileExtension);
      if (StringUtils.isNotBlank(widget.getImageContent())) {
        // If has defined content, create new image
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(Base64.getDecoder().decode(widget.getImageContent()));
        widget.setImageLocation(imageLocation);
        widget.setImageContent(null);
      } else {
        // If has defined location, clone new image
        byte[] oldFileContent = WelcomeWidgetUtils.getImageAsByteData(widget.getImageLocation());
        WelcomeWidgetUtils.readObjectValueOfDefaultLocale(newImageObject).write().bytes(oldFileContent);
      }
    }
  }
}
