package ch.ivy.addon.portalkit.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.axonivy.portal.dto.menu.MenuOrder;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;

import ch.ivy.addon.portalkit.configuration.Application;
import ch.ivy.addon.portalkit.configuration.ExternalLink;
import ch.ivy.addon.portalkit.dto.UserMenu;
import ch.ivy.addon.portalkit.dto.casedetails.CaseDetails;
import ch.ivy.addon.portalkit.dto.dashboard.Dashboard;
import ch.ivy.addon.portalkit.dto.dashboard.WelcomeDashboardWidget;
import ch.ivy.addon.portalkit.enums.PortalPackageFile;
import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.exception.PortalException;
import ch.ivy.addon.portalkit.util.DashboardUtils;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.NavigationWidgetUtils;
import ch.ivyteam.ivy.environment.Ivy;

public class PortalPackageService {

  private static final String PACKAGE_NAME = "Portal_Package.zip";
  public static final String ACCEPTED_FILE_TYPE = ".zip";

  private static PortalPackageService instance;

  private PortalPackageService() {}

  public static PortalPackageService getInstance() {
    if (instance == null) {
      instance = new PortalPackageService();
    }
    return instance;
  }

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
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList()).forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget welcomeWidget) {
        WelcomeWidgetUtils.prepareWidgetForExport(welcomeWidget);
      } else if (widget instanceof NavigationDashboardWidget navWidget) {
        navWidget.setImageContent(ImageUploadUtils.imageToBase64(navWidget.getImageLocation(),
            navWidget.getImageType(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
        navWidget.setImageContentDarkMode(ImageUploadUtils.imageToBase64(navWidget.getImageLocationDarkMode(),
            navWidget.getImageTypeDarkMode(), ImageUploadUtils.NAVIGATION_WIDGET_IMAGE_DIRECTORY));
      }
      DashboardWidgetUtils.simplifyWidgetColumnData(widget);
    });
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
    if (StringUtils.isNotBlank(json) && !"[]".equals(json.trim()) && !"{}".equals(json.trim())) {
      writeEntry(zos, file.getFilename(), json);
    }
  }

  private boolean isRawVariableEmpty(PortalPackageFile file) {
    String json = Ivy.var().get(file.getVariableKey());
    return StringUtils.isBlank(json) || "[]".equals(json.trim()) || "{}".equals(json.trim());
  }

  private void writeEntry(ZipOutputStream zos, String name, String content) throws IOException {
    zos.putNextEntry(new ZipEntry(name));
    zos.write(content.getBytes(StandardCharsets.UTF_8));
    zos.closeEntry();
  }

  // IMPORT

  public Map<String, Boolean> importPackage(byte[] zipBytes) {
    Map<String, Boolean> results = new LinkedHashMap<>();
    try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipBytes))) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          String name = Paths.get(entry.getName()).getFileName().toString();
          PortalPackageFile file = PortalPackageFile.fromFilename(name);
          if (file != null) {
            String json = new String(zis.readAllBytes(), StandardCharsets.UTF_8);
            results.put(name, importEntry(file, json));
          }
        }
        zis.closeEntry();
      }
    } catch (IOException e) {
      Ivy.log().error("Failed to read uploaded Portal package", e);
    }
    return results;
  }

  private boolean importEntry(PortalPackageFile file, String json) {
    try {
      switch (file) {
        case DASHBOARD -> importDashboards(json);
        case EXTERNAL_LINK -> importExternalLinks(json);
        case CUSTOM_STATISTIC -> importList(json, Statistic.class, file.getVariableKey());
        case USER_MENU -> importList(json, UserMenu.class, file.getVariableKey());
        case CASE_DETAIL -> importList(json, CaseDetails.class, file.getVariableKey());
        case THIRD_PARTY_APP -> importList(json, Application.class, file.getVariableKey());
        case CUSTOM_MENU_ITEMS -> importList(json, CustomSubMenuItem.class, file.getVariableKey());
        case MENU_ORDER -> importSingle(json, MenuOrder.class, file.getVariableKey());
      }
      return true;
    } catch (Exception e) {
      Ivy.log().error("Failed to import Portal package file {0}", e, file.getFilename());
      return false;
    }
  }

  private <T> void importList(String json, Class<T> type, String variableKey) {
    List<T> entities = BusinessEntityConverter.jsonValueToEntities(json, type);
    Ivy.var().set(variableKey, BusinessEntityConverter.entityToJsonValue(entities));
  }

  private <T> void importSingle(String json, Class<T> type, String variableKey) {
    T entity = BusinessEntityConverter.jsonValueToEntity(json, type);
    Ivy.var().set(variableKey, BusinessEntityConverter.entityToJsonValue(entity));
  }

  private void importDashboards(String json) {
    List<Dashboard> dashboards = BusinessEntityConverter.jsonValueToEntities(json, Dashboard.class);
    for (Dashboard dashboard : dashboards) {
      if (DashboardUtils.hasOversizedWidgetImage(dashboard)) {
        throw new PortalException(
            "Dashboard '" + dashboard.getTitle() + "' contains an image exceeding the upload size limit.");
      }
    }
    dashboards.forEach(this::writeDashboardWidgetImages);
    Ivy.var().set(PortalVariable.DASHBOARD.key, BusinessEntityConverter.entityToJsonValue(dashboards));
  }

  private void writeDashboardWidgetImages(Dashboard dashboard) {
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList()).forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget welcomeWidget) {
        WelcomeWidgetUtils.writeWelcomeWidgetImage(welcomeWidget);
      } else if (widget instanceof NavigationDashboardWidget navWidget) {
        NavigationWidgetUtils.writeNavigateWidgetImage(navWidget);
      }
    });
  }

  private void importExternalLinks(String json) {
    List<ExternalLink> links = BusinessEntityConverter.jsonValueToEntities(json, ExternalLink.class);
    for (ExternalLink link : links) {
      if (UploadDocumentUtils.isBase64ImageSizeExceeded(link.getImageContent())) {
        throw new PortalException(
            "External link '" + link.getName() + "' contains an image exceeding the upload size limit.");
      }
    }
    links.forEach(this::writeExternalLinkImage);
    Ivy.var().set(PortalVariable.EXTERNAL_LINK.key, BusinessEntityConverter.entityToJsonValue(links));
  }

  private void writeExternalLinkImage(ExternalLink link) {
    if (StringUtils.isNotBlank(link.getImageContent())) {
      String location = ImageUploadUtils.imageBase64ToApplicationCMSFile(link.getImageContent(),
          link.getImageType(), ImageUploadUtils.EXTERNAL_LINK_IMAGE_DIRECTORY);
      link.setImageLocation(location);
      link.setImageContent(null);
    }
  }

  public Map<String, String> getFileDescriptions() {
    Map<String, String> descriptions = new LinkedHashMap<>();
    for (PortalPackageFile file : PortalPackageFile.values()) {
      descriptions.put(file.getFilename(), file.getDescription());
    }
    return Collections.unmodifiableMap(descriptions);
  }

  public String getAcceptedFileType() {
    return ACCEPTED_FILE_TYPE;
  }

  public static String getPackageName() {
    return PACKAGE_NAME;
  }
}
