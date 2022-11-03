package com.axonivy.portal.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.IOException;

import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.scripting.objects.File;

public class WelcomeWidgetUtils {

  public static final String IMAGE_DIRECTORY = "DashboardWelcomeWidget";
  public static final String DEFAULT_LOCALE_AND_DOT = "_en.";
  public static final String DEFAULT_LOCALE_TAG = "en";
  public static final String FOLDER_DELIMITER = "/";

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
    readObjectValueOfDefaultLocale(imageObject).write().bytes(oldFileContent);
  }

  private static byte[] getImageAsByteData(String filePath) {
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
    return getApplicationCMS().child().folder(IMAGE_DIRECTORY).child()
        .file(fileName, fileExtension);
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
    return types[types.length - 1];
  }

  public static String getFileNameOfImage(String imageLocation) {
    return StringUtils.defaultIfEmpty(imageLocation, EMPTY).split(DEFAULT_LOCALE_AND_DOT)[0];
  }

}
