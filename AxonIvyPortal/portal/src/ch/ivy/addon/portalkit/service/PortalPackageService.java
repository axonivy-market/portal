package ch.ivy.addon.portalkit.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.jsonversion.DashboardJsonVersion;
import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.dto.menu.MenuOrderEntry;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalPackageService {

  private static final String PACKAGE_NAME = "Portal_Package.zip";

  private static final Map<String, String> FILE_TO_VARIABLE;
  private static final Map<String, Class<?>> FILE_TO_TYPE;
  private static final Map<String, String> FILE_DESCRIPTIONS;
  static {
    FILE_TO_VARIABLE = new LinkedHashMap<>();
    FILE_TO_VARIABLE.put("Portal_Dashboard.json", PortalVariable.DASHBOARD.key);
    FILE_TO_VARIABLE.put("Portal_CustomStatistic.json", PortalVariable.CUSTOM_STATISTIC.key);
    FILE_TO_VARIABLE.put("Portal_UserMenu.json", PortalVariable.USER_MENU.key);
    FILE_TO_VARIABLE.put("Portal_CaseDetails.json", PortalVariable.CASE_DETAIL.key);
    FILE_TO_VARIABLE.put("Portal_ThirdPartyApplications.json", PortalVariable.THIRD_PARTY_APP.key);
    FILE_TO_VARIABLE.put("Portal_CustomMenuItems.json", PortalVariable.CUSTOM_MENU_ITEMS.key);
    FILE_TO_VARIABLE.put("Portal_ExternalLinks.json", PortalVariable.EXTERNAL_LINK.key);
    FILE_TO_VARIABLE.put("Portal_MenuOrder.json", PortalVariable.MENU_ORDER.key);

    FILE_TO_TYPE = new LinkedHashMap<>();
    FILE_TO_TYPE.put("Portal_Dashboard.json", Dashboard.class);
    FILE_TO_TYPE.put("Portal_CustomStatistic.json", Statistic.class);
    FILE_TO_TYPE.put("Portal_UserMenu.json", UserMenu.class);
    FILE_TO_TYPE.put("Portal_CaseDetails.json", CaseDetails.class);
    FILE_TO_TYPE.put("Portal_ThirdPartyApplications.json", Application.class);
    FILE_TO_TYPE.put("Portal_CustomMenuItems.json", CustomSubMenuItem.class);
    FILE_TO_TYPE.put("Portal_ExternalLinks.json", ExternalLink.class);
    FILE_TO_TYPE.put("Portal_MenuOrder.json", MenuOrderEntry.class);

    FILE_DESCRIPTIONS = new LinkedHashMap<>();
    FILE_DESCRIPTIONS.put("Portal_Dashboard.json", "Dashboard widget layouts and configurations for all Portal dashboards");
    FILE_DESCRIPTIONS.put("Portal_CustomStatistic.json", "Custom statistic chart definitions displayed on Portal dashboards");
    FILE_DESCRIPTIONS.put("Portal_UserMenu.json", "User menu items and shortcuts shown in the top navigation bar");
    FILE_DESCRIPTIONS.put("Portal_CaseDetails.json", "Case detail page layouts including visible fields and widget arrangements");
    FILE_DESCRIPTIONS.put("Portal_ThirdPartyApplications.json", "Third-party application links integrated into the Portal menu");
    FILE_DESCRIPTIONS.put("Portal_CustomMenuItems.json", "Custom ordering of items in the left-side navigation menu");
    FILE_DESCRIPTIONS.put("Portal_ExternalLinks.json", "External URL links displayed in the Portal navigation");
    FILE_DESCRIPTIONS.put("Portal_MenuOrder.json", "Custom ordering of the main navigation menu items in the Portal sidebar");
  }

  public static class PackagePreview {
    private final List<String> validFiles;
    private final List<String> invalidFiles;

    public PackagePreview(List<String> validFiles, List<String> invalidFiles) {
      this.validFiles = validFiles;
      this.invalidFiles = invalidFiles;
    }

    public List<String> getValidFiles() { return validFiles; }
    public List<String> getInvalidFiles() { return invalidFiles; }
    public boolean hasValidFiles() { return !validFiles.isEmpty(); }
  }

  public static class ImportEntryResult {
    private final String filename;
    private final String status;
    private final String message;

    private ImportEntryResult(String filename, String status, String message) {
      this.filename = filename;
      this.status = status;
      this.message = message;
    }

    public static ImportEntryResult success(String filename) {
      return new ImportEntryResult(filename, "SUCCESS", "Imported successfully.");
    }

    public static ImportEntryResult failure(String filename, String message) {
      return new ImportEntryResult(filename, "FAILURE", message);
    }

    public static ImportEntryResult skipped(String filename) {
      return new ImportEntryResult(filename, "SKIPPED", "Unrecognized file — not a known Portal configuration type.");
    }

    public String getFilename() { return filename; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
  }

  // ── EXPORT ────────────────────────────────────────────────────────────────

  public StreamedContent exportPackage() throws IOException {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos)) {

      writeDashboards(zos);
      writeRawVariable(zos, PortalVariable.CUSTOM_STATISTIC.key, "Portal_CustomStatistic.json");
      writeRawVariable(zos, PortalVariable.USER_MENU.key, "Portal_UserMenu.json");
      writeRawVariable(zos, PortalVariable.CASE_DETAIL.key, "Portal_CaseDetails.json");
      writeRawVariable(zos, PortalVariable.THIRD_PARTY_APP.key, "Portal_ThirdPartyApplications.json");
      writeRawVariable(zos, PortalVariable.CUSTOM_MENU_ITEMS.key, "Portal_CustomMenuItems.json");
      writeExternalLinks(zos);
      writeRawVariable(zos, PortalVariable.MENU_ORDER.key, "Portal_MenuOrder.json");

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
    List<Dashboard> dashboards = DashboardService.getInstance().getPublicConfig();
    if (dashboards.isEmpty()) {
      return;
    }
    dashboards.forEach(this::prepareDashboardForExport);
    writeEntry(zos, "Portal_Dashboard.json",
        BusinessEntityConverter.entityToJsonValue(dashboards));
  }

  private void prepareDashboardForExport(Dashboard dashboard) {
    dashboard.setOldId(null);
    dashboard.setVersion(DashboardJsonVersion.LATEST_VERSION.getValue());
    if (!dashboard.getIsPublic()) {
      dashboard.setPermissions(null);
    }
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList()).forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget) {
        WelcomeDashboardWidget welcomeWidget = (WelcomeDashboardWidget) widget;
        welcomeWidget.setImageType(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageType()));
        welcomeWidget.setImageContent(encodeWelcomeWidgetImage(welcomeWidget.getImageLocation(), welcomeWidget.getImageType()));
        welcomeWidget.setImageTypeDarkMode(WelcomeWidgetUtils.getFileTypeOfImage(welcomeWidget.getImageTypeDarkMode()));
        welcomeWidget.setImageContentDarkMode(encodeWelcomeWidgetImage(welcomeWidget.getImageLocationDarkMode(), welcomeWidget.getImageTypeDarkMode()));
      } else if (widget instanceof NavigationDashboardWidget) {
        NavigationDashboardWidget navWidget = (NavigationDashboardWidget) widget;
        navWidget.setImageContent(ImageUploadUtils.imageToBase64(navWidget.getImageLocation(),
            navWidget.getImageType(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
        navWidget.setImageContentDarkMode(ImageUploadUtils.imageToBase64(navWidget.getImageLocationDarkMode(),
            navWidget.getImageTypeDarkMode(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
      }
    });
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList())
        .forEach(DashboardWidgetUtils::simplifyWidgetColumnData);
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
    List<ExternalLink> links = ExternalLinkService.getInstance().getPublicConfig();
    if (links.isEmpty()) {
      return;
    }
    links.forEach(this::prepareExternalLinkForExport);
    writeEntry(zos, "Portal_ExternalLinks.json",
        BusinessEntityConverter.entityToJsonValue(links));
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

  private void writeRawVariable(ZipOutputStream zos, String variableKey, String entryName) throws IOException {
    String json = Ivy.var().get(variableKey);
    if (StringUtils.isNotBlank(json) && !"[]".equals(json.trim())) {
      writeEntry(zos, entryName, json);
    }
  }

  private void writeEntry(ZipOutputStream zos, String name, String content) throws IOException {
    zos.putNextEntry(new ZipEntry(name));
    zos.write(content.getBytes(StandardCharsets.UTF_8));
    zos.closeEntry();
  }

  // ── IMPORT ────────────────────────────────────────────────────────────────

  public PackagePreview previewPackage(byte[] zipBytes) throws IOException {
    List<String> valid = new ArrayList<>();
    List<String> invalid = new ArrayList<>();
    Set<String> seen = new LinkedHashSet<>();
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipBytes))) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          String name = Paths.get(entry.getName()).getFileName().toString();
          if (seen.add(name) && name.endsWith(".json")) {
            if (FILE_TO_TYPE.containsKey(name)) {
              valid.add(name);
            } else {
              invalid.add(name);
            }
          }
        }
        zis.closeEntry();
      }
    }
    return new PackagePreview(valid, invalid);
  }

  public List<ImportEntryResult> importPackage(byte[] zipBytes) throws IOException {
    Map<String, byte[]> rawEntries = readZipEntries(zipBytes);

    List<ImportEntryResult> skipped = new ArrayList<>();
    List<ImportEntryResult> parseResults = new ArrayList<>();
    Map<String, String> validated = new LinkedHashMap<>();

    // Phase 1: classify and parse every JSON file.
    for (Map.Entry<String, byte[]> e : rawEntries.entrySet()) {
      String name = e.getKey();
      if (!name.endsWith(".json")) {
        continue; // silently ignore non-JSON (OS artifacts, etc.)
      }
      Class<?> type = FILE_TO_TYPE.get(name);
      if (type == null) {
        skipped.add(ImportEntryResult.skipped(name));
        continue;
      }
      String json = new String(e.getValue(), StandardCharsets.UTF_8);
      try {
        parseEntries(json, type);
        validated.put(name, json);
      } catch (Exception ex) {
        Throwable root = ex.getCause() != null ? ex.getCause() : ex;
        parseResults.add(ImportEntryResult.failure(name, "Content validation failed: " + root.getMessage()));
      }
    }

    List<ImportEntryResult> results = new ArrayList<>(skipped);

    // Abort if any parse failure — do not write anything.
    if (!parseResults.isEmpty()) {
      results.addAll(parseResults);
      return results;
    }

    // Phase 2: store all validated files.
    for (Map.Entry<String, String> e : validated.entrySet()) {
      String variableKey = FILE_TO_VARIABLE.get(e.getKey());
      try {
        String json = "Portal_ExternalLinks.json".equals(e.getKey())
            ? restoreExternalLinkImages(e.getValue())
            : e.getValue();
        Ivy.var().set(variableKey, json);
        results.add(ImportEntryResult.success(e.getKey()));
      } catch (Exception ex) {
        results.add(ImportEntryResult.failure(e.getKey(), ex.getMessage()));
      }
    }
    return results;
  }

  private String restoreExternalLinkImages(String json) {
    List<ExternalLink> links = BusinessEntityConverter.jsonValueToEntities(json, ExternalLink.class);
    links.forEach(link -> {
      if (StringUtils.isNotBlank(link.getImageContent())) {
        String location = ImageUploadUtils.imageBase64ToApplicationCMSFile(
            link.getImageContent(), link.getImageType(), ImageUploadUtils.EXTERNAL_LINK_IMAGE_DIRECTORY);
        link.setImageLocation(location);
        link.setImageContent(null);
      }
    });
    return BusinessEntityConverter.entityToJsonValue(links);
  }

  private Map<String, byte[]> readZipEntries(byte[] zipBytes) throws IOException {
    Map<String, byte[]> entries = new LinkedHashMap<>();
    Map<String, Long> timestamps = new HashMap<>();
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipBytes))) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          String name = Paths.get(entry.getName()).getFileName().toString();
          long time = entry.getTime();
          if (!entries.containsKey(name) || time > timestamps.getOrDefault(name, Long.MIN_VALUE)) {
            entries.put(name, zis.readAllBytes());
            timestamps.put(name, time);
          }
        }
        zis.closeEntry();
      }
    }
    return entries;
  }

  @SuppressWarnings("unchecked")
  private void parseEntries(String json, Class<?> type) {
    BusinessEntityConverter.jsonValueToEntities(json, (Class<Object>) type);
  }
}
