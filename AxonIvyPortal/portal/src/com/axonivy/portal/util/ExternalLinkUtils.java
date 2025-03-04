package com.axonivy.portal.util;

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

  public static Pair<String, String> handleImageUpload(FileUploadEvent event) {
    UploadedFile file = event.getFile();
    if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
      // save the image
      String fileName = UUID.randomUUID().toString();
      String fileExtension = FilenameUtils.getExtension(file.getFileName());
      byte[] content = file.getContent();

      // hanlde sanitize svg
      if ("svg".equals(fileExtension)) {
        content =
            PortalSanitizeUtils.sanitizeSvg(new String(content, StandardCharsets.UTF_8))
                .getBytes(StandardCharsets.UTF_8);
      }

      ContentObject imageCMSObject = getApplicationCMS().child().folder(IMAGE_DIRECTORY).child().file(fileName,
          fileExtension);

      if (imageCMSObject != null) {
        readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(content);
        return Pair.of(imageCMSObject.uri(), fileExtension);
      }
    }
    return Pair.of(StringUtils.EMPTY, StringUtils.EMPTY);
  }

  public static void removeImage(String imageUrl, String imageType) {
    ContentObject imageObject = getApplicationCMS().child().file(imageUrl, imageType);
    if (imageObject != null) {
      imageObject.delete();
    }
  }

  public static String imageBase64ToApplicationCMSFile(String base64Data, String imageType) {
    try {
      byte[] data = Base64.getDecoder().decode(base64Data.getBytes(StandardCharsets.UTF_8));
      String fileName = UUID.randomUUID().toString();
      ContentObject imageCMSObject = getApplicationCMS().child().folder(IMAGE_DIRECTORY).child().file(fileName, imageType);

      if (imageCMSObject != null) {
        readObjectValueOfDefaultLocale(imageCMSObject).write().bytes(data);
        return imageCMSObject.uri();
      }
    } catch (Exception e) {
      Ivy.log().warn("Cannot convert base64 image to cms file: {0}", e.getMessage());
    }
    return StringUtils.EMPTY;
  }
  
  public static Boolean isValidImageUrl(String imageLocation, String imageType) {
    if (StringUtils.isBlank(imageLocation)) {
      return false;
    }

    ContentObject imageCMSObject = getApplicationCMS().child().file(imageLocation, imageType);
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
}
