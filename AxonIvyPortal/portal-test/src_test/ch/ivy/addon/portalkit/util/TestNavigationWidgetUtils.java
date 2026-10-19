package ch.ivy.addon.portalkit.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.IvyTest;

@IvyTest
class TestNavigationWidgetUtils {

  private static final byte[] VALID_PNG = new byte[] {
      (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A,
      0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52
  };
  private static final String VALID_PNG_BASE64 = Base64.getEncoder().encodeToString(VALID_PNG);
  private static final String UNSAFE_SVG_BASE64 =
      Base64.getEncoder().encodeToString("<svg><script>alert(1)</script></svg>".getBytes(StandardCharsets.UTF_8));

  @AfterEach
  void cleanCmsFolder() {
    folder().ifPresent(f -> {
      for (ContentObject child : f.children()) {
        child.delete();
      }
    });
  }

  @Test
  void writeNavigateWidgetImage_validImageContent_setsLocationAndClearsContent() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_test");
    widget.setImageType("png");
    widget.setImageContent(VALID_PNG_BASE64);

    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    assertThat(widget.getImageLocation()).isNotEmpty();
    assertThat(widget.getImageContent()).isNull();
  }

  @Test
  void writeNavigateWidgetImage_unsafeImageContent_rejectsAndDoesNotSetLocation() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_test_unsafe");
    widget.setImageType("svg");
    widget.setImageContent(UNSAFE_SVG_BASE64);

    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    assertThat(widget.getImageLocation()).isNull();
    assertThat(widget.getImageType()).isNull();
    assertThat(widget.getImageContent()).isNull();
  }

  @Test
  void cloneLightModeImage_invalidExtension_clearsLocationAndType() throws Exception {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_test_clone_invalid_ext");
    widget.setImageType("exe");
    widget.setImageLocation("some/path/image.exe");

    NavigationWidgetUtils.cloneLightModeImage("exe", "some/path/image.exe", widget);

    assertThat(widget.getImageLocation()).isNull();
    assertThat(widget.getImageType()).isNull();
  }

  @Test
  void writeNavigateWidgetImage_validDarkModeContent_setsDarkModeLocation() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_test_dark");
    widget.setImageTypeDarkMode("png");
    widget.setImageContentDarkMode(VALID_PNG_BASE64);

    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    assertThat(widget.getImageLocationDarkMode()).isNotEmpty();
    assertThat(widget.getImageContentDarkMode()).isNull();
  }

  @Test
  void writeNavigateWidgetImage_noImageType_doesNothing() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_test_no_type");

    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    assertThat(widget.getImageLocation()).isNull();
    assertThat(widget.getImageLocationDarkMode()).isNull();
  }

  @Test
  void prepareWidgetForExport_existingImages_exportsToBase64() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_export");
    widget.setImageType("png");
    widget.setImageContent(VALID_PNG_BASE64);
    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    String location = widget.getImageLocation();
    assertThat(location).isNotEmpty();

    NavigationWidgetUtils.prepareWidgetForExport(widget);

    assertThat(widget.getImageLocation()).isNull();
    assertThat(widget.getImageContent()).isEqualTo(VALID_PNG_BASE64);
  }

  @Test
  void removeNavigateWidgetImage_deletesImages() {
    NavigationDashboardWidget widget = new NavigationDashboardWidget();
    widget.setId("nav_remove");
    widget.setImageType("png");
    widget.setImageContent(VALID_PNG_BASE64);
    NavigationWidgetUtils.writeNavigateWidgetImage(widget);

    assertThat(widget.getImageLocation()).isNotEmpty();

    NavigationWidgetUtils.removeNavigateWidgetImage(widget);
  }

  private static Optional<ContentObject> folder() {
    return ContentManagement.cms(Application.current()).root()
        .child().get(ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY);
  }
}
