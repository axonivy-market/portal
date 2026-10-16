package com.axonivy.portal.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.IvyTest;
import ch.ivyteam.ivy.scripting.objects.File;

@IvyTest
class TestWelcomeWidgetUtils {

  private static final String WIDGET_ID = "welcome_junittest";
  private static final String IMAGE_LOCATION = WIDGET_ID + "_en.png";
  private static final String IMAGE_TYPE = "png";
  private static final byte[] IMAGE_CONTENT = "junit-image".getBytes(StandardCharsets.UTF_8);

  @AfterEach
  void removeTestImage() {
    imageFolder().flatMap(folder -> folder.child().get(WIDGET_ID)).ifPresent(ContentObject::delete);
  }

  @Test
  void getFileNameOfImage_locationWithLocaleSuffix_stripsSuffix() {
    assertThat(WelcomeWidgetUtils.getFileNameOfImage("welcome_abc_en.png")).isEqualTo("welcome_abc");
    assertThat(WelcomeWidgetUtils.getFileNameOfImage("welcome_abc_darkmode_en.png"))
        .isEqualTo("welcome_abc_darkmode");
  }

  @Test
  void getFileNameOfImage_alreadyStrippedName_isUnchanged() {
    assertThat(WelcomeWidgetUtils.getFileNameOfImage("welcome_abc")).isEqualTo("welcome_abc");
  }

  @Test
  void getFileNameOfImage_blankLocation_returnsEmpty() {
    assertThat(WelcomeWidgetUtils.getFileNameOfImage(null)).isEmpty();
    assertThat(WelcomeWidgetUtils.getFileNameOfImage("")).isEmpty();
  }

  @Test
  void getFileTypeOfImage_fileExtension_returnsExtension() {
    assertThat(WelcomeWidgetUtils.getFileTypeOfImage("png")).isEqualTo("png");
  }

  @Test
  void getFileTypeOfImage_mimeType_returnsExtensionOnly() {
    assertThat(WelcomeWidgetUtils.getFileTypeOfImage("image/png")).isEqualTo("png");
    assertThat(WelcomeWidgetUtils.getFileTypeOfImage("image/svg+xml")).isEqualTo("svg+xml");
  }

  @Test
  void getFileTypeOfImage_blankType_returnsEmpty() {
    assertThat(WelcomeWidgetUtils.getFileTypeOfImage(null)).isEmpty();
    assertThat(WelcomeWidgetUtils.getFileTypeOfImage("")).isEmpty();
  }

  @Test
  void isObsoleteImageData_locationInsideImageDirectory_isObsolete() {
    String obsoleteLocation = WelcomeWidgetUtils.IMAGE_DIRECTORY
        .concat(WelcomeWidgetUtils.FOLDER_DELIMITER).concat(IMAGE_LOCATION);

    assertThat(WelcomeWidgetUtils.isObsoleteImageData(obsoleteLocation, WIDGET_ID)).isTrue();
  }

  @Test
  void isObsoleteImageData_plainLocation_isNotObsolete() {
    assertThat(WelcomeWidgetUtils.isObsoleteImageData(IMAGE_LOCATION, WIDGET_ID)).isFalse();
  }

  @Test
  void generateGreetingTextByTime_beforeNoon_isMorning() {
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(0)).endsWith("/Morning");
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(11)).endsWith("/Morning");
  }

  @Test
  void generateGreetingTextByTime_middleOfTheDay_isAfternoon() {
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(12)).endsWith("/Afternoon");
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(18)).endsWith("/Afternoon");
  }

  @Test
  void generateGreetingTextByTime_afterSix_isEvening() {
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(19)).endsWith("/Evening");
    assertThat(WelcomeWidgetUtils.generateGreetingTextByTime(23)).endsWith("/Evening");
  }

  @Test
  void findImage_imageInCms_isFound() {
    writeTestImageToCms();

    Optional<ContentObject> image = WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE);

    assertThat(image).isPresent();
    assertThat(image.get().name()).isEqualTo(WIDGET_ID);
  }

  @Test
  void findImage_imageMissing_isEmptyAndDoesNotCreateIt() {
    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE)).isEmpty();
    assertThat(imageFolder().filter(folder -> folder.child().exists(WIDGET_ID))).isEmpty();
  }

  @Test
  void findImage_blankLocation_isEmpty() {
    assertThat(WelcomeWidgetUtils.findImage(null, IMAGE_TYPE)).isEmpty();
    assertThat(WelcomeWidgetUtils.findImage("", IMAGE_TYPE)).isEmpty();
  }

  @Test
  void findImage_blankType_isEmpty() {
    writeTestImageToCms();

    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, null)).isEmpty();
  }

  @Test
  void getImageContentObject_imageMissing_returnsHandleOfNonExistingImage() {
    ContentObject image = WelcomeWidgetUtils.getImageContentObject(IMAGE_LOCATION, IMAGE_TYPE);

    assertThat(image).isNotNull();
    assertThat(image.exists()).isFalse();
    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE)).isEmpty();
  }

  @Test
  void getImageContentObject_bytesWritten_materialisesTheImage() {
    ContentObject image = WelcomeWidgetUtils.getImageContentObject(IMAGE_LOCATION, IMAGE_TYPE);
    WelcomeWidgetUtils.readObjectValueOfDefaultLocale(image).write().bytes(IMAGE_CONTENT);

    assertThat(image.exists()).isTrue();
    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE)).isPresent();
  }

  @Test
  void getImageContentObject_blankType_returnsNull() {
    assertThat(WelcomeWidgetUtils.getImageContentObject(IMAGE_LOCATION, null)).isNull();
  }

  @Test
  void removeWelcomeImage_imageInCms_deletesIt() {
    writeTestImageToCms();
    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE)).isPresent();

    WelcomeWidgetUtils.removeWelcomeImage(IMAGE_LOCATION, IMAGE_TYPE);

    assertThat(WelcomeWidgetUtils.findImage(IMAGE_LOCATION, IMAGE_TYPE)).isEmpty();
  }

  @Test
  void encodeImage_imageInCms_returnsBase64Content() {
    writeTestImageToCms();

    assertThat(WelcomeWidgetUtils.encodeImage(IMAGE_LOCATION, IMAGE_TYPE))
        .isEqualTo(Base64.getEncoder().encodeToString(IMAGE_CONTENT));
  }

  @Test
  void encodeImage_blankLocation_returnsEmpty() {
    assertThat(WelcomeWidgetUtils.encodeImage(null, IMAGE_TYPE)).isEmpty();
  }

  @Test
  void writeWelcomeWidgetImage_lightModeRejected_darkModeStillProcessed() {
    WelcomeDashboardWidget widget = new WelcomeDashboardWidget();
    widget.setId(WIDGET_ID);
    widget.setImageType("exe");
    widget.setImageContent(Base64.getEncoder().encodeToString("bad_content".getBytes(StandardCharsets.UTF_8)));

    byte[] validPng = new byte[] {
        (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
        0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52
    };
    widget.setImageTypeDarkMode("png");
    widget.setImageContentDarkMode(Base64.getEncoder().encodeToString(validPng));

    WelcomeWidgetUtils.writeWelcomeWidgetImage(widget);

    // light mode was rejected
    assertThat(widget.getImageLocation()).isNull();
    assertThat(widget.getImageContent()).isNull();

    // dark mode was processed and saved
    assertThat(widget.getImageLocationDarkMode()).isNotNull();
    assertThat(widget.getImageLocationDarkMode()).contains("_darkmode_en.png");
    assertThat(widget.getImageContentDarkMode()).isNull();
  }

  @Test
  void writeWelcomeWidgetImage_darkModeCloneRejected_clearsLocation() throws IOException {
    File tempFile = new File("test_invalid_clone.png");
    try {
      tempFile.write("not an image");

      WelcomeDashboardWidget widget = new WelcomeDashboardWidget();
      widget.setId(WIDGET_ID);
      widget.setImageTypeDarkMode("png");
      widget.setImageLocationDarkMode(tempFile.getPath());

      WelcomeWidgetUtils.writeWelcomeWidgetImage(widget);

      assertThat(widget.getImageLocationDarkMode()).isNull();
    } finally {
      if (tempFile.exists()) {
        tempFile.forceDelete();
      }
    }
  }

  private static void writeTestImageToCms() {
    ContentManagement.cms(Application.current()).root()
        .child().folder(WelcomeWidgetUtils.IMAGE_DIRECTORY)
        .child().file(WIDGET_ID, IMAGE_TYPE)
        .value().get(WelcomeWidgetUtils.DEFAULT_LOCALE_TAG)
        .write().bytes(IMAGE_CONTENT);
  }

  private static Optional<ContentObject> imageFolder() {
    return ContentManagement.cms(Application.current()).root()
        .child().get(WelcomeWidgetUtils.IMAGE_DIRECTORY);
  }
}
