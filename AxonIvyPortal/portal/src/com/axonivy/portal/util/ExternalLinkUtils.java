package com.axonivy.portal.util;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.util.Pair;

public class ExternalLinkUtils {

  public static final String IMAGE_DIRECTORY = "com/axonivy/portal/ExternalLink";
  public static final String DEFAULT_LOCALE_TAG = "en";
  public static final String BASE_64 = "base64";

  public static Pair<String, String> handleImageUpload(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
      // save the image
      String fileName = UUID.randomUUID().toString();
      String fileExtension = FilenameUtils.getExtension(file.getFileName());

      ContentObject imageCMSObject = getApplicationCMS().child().folder(IMAGE_DIRECTORY).child().file(fileName,
          fileExtension);

      if (imageCMSObject != null) {
        readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(file.getContent());
        return Pair.of(imageCMSObject.uri(), fileExtension);
      }
    }
    return Pair.of(EMPTY, EMPTY);
  }

  public static void removeImage(String imageUrl, String imageType) {
    ContentObject imageObject = getApplicationCMS().child().file(imageUrl, imageType);
    if (imageObject != null) {
      imageObject.delete();
    }
  }

  public static Pair<String, String> imageBase64ToApplicationCMSFile(String base64Data) {
    try {
      String encodedData = base64Data.split(",")[1];
      byte[] data = Base64.getDecoder().decode(encodedData.getBytes(StandardCharsets.UTF_8));
      String fileName = UUID.randomUUID().toString();
      String fileExtension = getImageExtensionFromBase64(base64Data);
      ContentObject imageCMSObject = getApplicationCMS().child().folder(IMAGE_DIRECTORY).child().file(fileName,
          fileExtension);

      if (imageCMSObject != null) {
        readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(data);
        return Pair.of(imageCMSObject.uri(), fileExtension);
      }
    } catch (Exception e) {
      Ivy.log().warn("Cannot convert base64 image to cms file: {0}", e.getMessage());
    }
    return Pair.of(EMPTY, EMPTY);
  }
  
  public static Boolean isValidImageUrl(String imageUrl, String imageType) {
    if (StringUtils.isBlank(imageUrl)) {
      return false;
    }

    if (imageUrl.contains(BASE_64)) {
      return true;
    }

    ContentObject imageCMSObject = getApplicationCMS().child().file(imageUrl, imageType);
    return imageCMSObject.exists();
  }

  private static ContentObjectValue readObjectValueOfDefaultLocale(ContentObject contentObject) {
    if (contentObject == null) {
      return null;
    }
    return contentObject.value().get(DEFAULT_LOCALE_TAG);
  }

  private static ContentObject getApplicationCMS() {
    return ContentManagement.cms(IApplication.current()).root();
  }

  private static String getImageExtensionFromBase64(String base64Data) {
    try {
      return base64Data.split(";")[0].split("/")[1];
    } catch (Exception e) {
      Ivy.log().warn("Invalid base64 image format: {0}", e.getMessage());
    }
    return EMPTY;
  }
}
