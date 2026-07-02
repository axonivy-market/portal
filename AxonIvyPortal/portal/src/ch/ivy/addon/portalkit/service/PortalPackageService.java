package ch.ivy.addon.portalkit.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.PortalPackageFile;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalPackageService {

  private static final String PACKAGE_NAME = "Portal_Package.zip";

  // ── EXPORT ────────────────────────────────────────────────────────────────

  public StreamedContent exportPackage() throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos)) {

      writeDashboards(zos);
      writeRawVariable(zos, PortalPackageFile.CUSTOM_STATISTIC);
      writeRawVariable(zos, PortalPackageFile.USER_MENU);
      writeRawVariable(zos, PortalPackageFile.CASE_DETAIL);
      writeRawVariable(zos, PortalPackageFile.THIRD_PARTY_APP);
      writeRawVariable(zos, PortalPackageFile.CUSTOM_MENU_ITEMS);
      writeExternalLinks(zos);
      writeRawVariable(zos, PortalPackageFile.MENU_ORDER);

      zos.finish();
      byte[] zipBytes = baos.toByteArray();

      return DefaultStreamedContent.builder()
          .stream(() -> new ByteArrayInputStream(zipBytes))
          .contentType("application/zip")
          .name(PACKAGE_NAME)
          .build();
    }
  }

  private void writeDashboards(ZipOutputStream zos) throws IOException {
    var dashboards = DashboardService.getInstance().getPublicConfig();
    if (dashboards.isEmpty()) {
      return;
    }
    dashboards.forEach(this::prepareDashboardForExport);
    writeEntry(zos, PortalPackageFile.DASHBOARD.getFilename(), BusinessEntityConverter.entityToJsonValue(dashboards));
  }

  private void prepareDashboardForExport(Dashboard dashboard) {
    dashboard.setOldId(null);
    dashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());
    if (!dashboard.getIsPublic()) {
      dashboard.setPermissions(null);
    }
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList()).forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget welcomeWidget) {
        welcomeWidget.setImageType(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageType()));
        welcomeWidget.setImageContent(encodeWelcomeWidgetImage(welcomeWidget.getImageLocation(), welcomeWidget.getImageType()));
        welcomeWidget.setImageTypeDarkMode(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageTypeDarkMode()));
        welcomeWidget.setImageContentDarkMode(encodeWelcomeWidgetImage(welcomeWidget.getImageLocationDarkMode(), welcomeWidget.getImageTypeDarkMode()));
      } else if (widget instanceof NavigationDashboardWidget navWidget) {
        navWidget.setImageContent(ImageUploadUtils.imageToBase64(navWidget.getImageLocation(),
            navWidget.getImageType(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
        navWidget.setImageContentDarkMode(ImageUploadUtils.imageToBase64(navWidget.getImageLocationDarkMode(),
            navWidget.getImageTypeDarkMode(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
      }
      DashboardWidgetUtils.simplifyWidgetColumnData(widget);
    });
  }

  private String encodeWelcomeWidgetImage(String imageLocation, String imageType) {
    if (StringUtils.isBlank(imageLocation)) {
      return "";
    }
    var widgetImage = WelcomeWidgetUtils.getImageContentObject(imageLocation, imageType);
    if (widgetImage != null && widgetImage.exists()) {
      return new String(Base64.getEncoder().encode(
          WelcomeWidgetUtils.readObjectValueOfDefaultLocale(widgetImage).read().bytes()));
    }
    return "";
  }

  private void writeExternalLinks(ZipOutputStream zos) throws IOException {
    if (isRawVariableEmpty(PortalPackageFile.EXTERNAL_LINK)) {
      return;
    }
    var links = ExternalLinkService.getInstance().getPublicConfig();
    links.forEach(this::prepareExternalLinkForExport);
    writeEntry(zos, PortalPackageFile.EXTERNAL_LINK.getFilename(), BusinessEntityConverter.entityToJsonValue(links));
  }

  private void prepareExternalLinkForExport(ExternalLink link) {
    if (StringUtils.isBlank(link.getImageLocation())) {
      return;
    }
    String base64 = ImageUploadUtils.imageToBase64(link.getImageLocation(), link.getImageType(),
        ImageUploadUtils.EXTERNAL_LINK_IMAGE_DIRECTORY);
    link.setImageContent(base64);
    link.setImageLocation(null);
  }

  private void writeRawVariable(ZipOutputStream zos, PortalPackageFile file) throws IOException {
    String json = Ivy.var().get(file.getVariableKey());
    if (StringUtils.isNotBlank(json) && !"[]".equals(json.trim())) {
      writeEntry(zos, file.getFilename(), json);
    }
  }

  private boolean isRawVariableEmpty(PortalPackageFile file) {
    String json = Ivy.var().get(file.getVariableKey());
    return StringUtils.isBlank(json) || "[]".equals(json.trim());
  }

  private void writeEntry(ZipOutputStream zos, String name, String content) throws IOException {
    zos.putNextEntry(new ZipEntry(name));
    zos.write(content.getBytes(StandardCharsets.UTF_8));
    zos.closeEntry();
  }

  public Map<String, String> getFileDescriptions() {
    Map<String, String> descriptions = new LinkedHashMap<>();
    for (PortalPackageFile file : PortalPackageFile.values()) {
      descriptions.put(file.getFilename(), file.getDescription());
    }
    return Collections.unmodifiableMap(descriptions);
  }
}
