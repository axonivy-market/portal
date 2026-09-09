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
import java.util.function.UnaryOperator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.axonivy.portal.bo.Statistic;
import com.axonivy.portal.bo.jsonversion.AbstractJsonVersion;
import com.axonivy.portal.components.configuration.CustomSubMenuItem;
import com.axonivy.portal.components.dto.JsonListWrapper;
import com.axonivy.portal.dto.dashboard.NavigationDashboardWidget;
import com.axonivy.portal.dto.menu.MenuOrder;
import com.axonivy.portal.migration.casedetails.migrator.JsonCaseDetailsMigrator;
import com.axonivy.portal.migration.dashboard.migrator.JsonDashboardMigrator;
import com.axonivy.portal.migration.statistic.migrator.JsonStatisticMigrator;
import com.axonivy.portal.migration.thirdpartyapplication.migrator.JsonThirdPartyApplicationMigrator;
import com.axonivy.portal.util.ImageUploadUtils;
import com.axonivy.portal.util.UploadDocumentUtils;
import com.axonivy.portal.util.WelcomeWidgetUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

/**
 * Exports and imports Portal configuration as a zip package of per-entity-type JSON files.
 *
 * <p>Each JSON file (other than {@code Portal_MenuOrder.json}, a single object) is a wrapper
 * {@code {"version": ..., "items": [...]}}. Only the wrapper's own "version" is written on export -
 * individual entities are not stamped with a version - and only the wrapper's version is read to
 * decide which migrations to run on import.
 *
 * <p><b>Downgrade compatibility (open question, resolved here by assumption):</b> because
 * individual entities no longer carry their own version, a package exported from this version of
 * Portal cannot be distinguished, entity-by-entity, from one whose entities were already at the
 * latest schema before export - both simply have no per-item version. Importing such a package into
 * an <em>older</em> Portal version - one that still expects/reads a per-item version field to decide
 * how to interpret an entity - is therefore not a supported scenario; this class only guarantees
 * forward migration (older exports imported into this or a newer version). If downgrade support is
 * ever required, it would need either a re-introduced per-item version stamp on export, or an
 * explicit compatibility shim in the older version's import path.
 */
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
    JsonListWrapper<Dashboard> wrapper = new JsonListWrapper<>(JsonListWrapper.FORMAT_VERSION, dashboards);
    writeEntry(zos, PortalPackageFile.DASHBOARD.getFilename(), BusinessEntityConverter.entityToJsonValue(wrapper));
  }

  private void writeRawVariable(ZipOutputStream zos, PortalPackageFile file) throws IOException {
    String json = Ivy.var().get(file.getVariableKey());
    if (StringUtils.isBlank(json) || isEmptyJsonCollection(json)) {
      return;
    }
    writeEntry(zos, file.getFilename(), prepareRawVariableForExport(file, json));
  }

  /**
   * Prepares a raw Ivy-variable JSON string for inclusion in the export package.
   *
   * <p>MENU_ORDER holds a single configuration object, never a list - it is written back
   * completely unchanged so it is never mistaken for (or wrapped into) a list.
   *
   * <p>For every other type: if the content is already in the canonical
   * {"version": ..., "items": [...]} wrapper shape, it is passed through unchanged <em>as long as its
   * items are already clean</em> - re-exporting an already-migrated variable must not reformat it
   * (see exportPackage_KeepExactValue). A wrapper-shaped variable can still have a lingering per-item
   * "version" - e.g. content stored before per-item versioning was removed, or written by other code
   * that still stamps one - so that case is detected and the version stripped in place rather than
   * silently re-exported as-is. Otherwise (not yet wrapper-shaped) the type-appropriate legacy
   * migrator is run over the raw items first (mutating them in place to the latest per-item shape),
   * any lingering per-item "version" field is stripped - once wrapped, the container's own version is
   * what a subsequent import reads - and the result is wrapped.
   */
  private String prepareRawVariableForExport(PortalPackageFile file, String json) throws IOException {
    if (file == PortalPackageFile.MENU_ORDER) {
      return json;
    }
    JsonNode node = BusinessEntityConverter.getObjectMapper().readTree(json.trim());
    if (JsonListWrapper.isListWrapper(node)) {
      JsonNode items = node.get(JsonListWrapper.ITEMS_FIELD_NAME);
      if (!hasPerItemVersion(items)) {
        return json;
      }
      stripPerItemVersion(items);
      return BusinessEntityConverter.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }
    JsonNode migrated = migrateRawVariable(file, node);
    JsonNode items = toItemsArray(file, migrated);
    stripPerItemVersion(items);
    ObjectNode wrapped = BusinessEntityConverter.getObjectMapper().createObjectNode();
    wrapped.put("version", JsonListWrapper.FORMAT_VERSION);
    wrapped.set(JsonListWrapper.ITEMS_FIELD_NAME, items);
    return BusinessEntityConverter.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(wrapped);
  }

  /**
   * Normalizes a migrated raw-variable node into the array the wrapper's "items" field requires.
   * The per-type migrators mutate their input in place and return it in whatever shape it was
   * given, not necessarily an array: {@code JsonCaseDetailsMigrator}/{@code JsonTaskDetailsMigrator}
   * return a single bare object unchanged for the shipped-default (single-configuration) legacy
   * shape, and {@code JsonThirdPartyApplicationMigrator} returns the whole legacy dynamic-root-key
   * object (e.g. {@code {"third-party-application": [...]}}) unchanged. Dropping either straight
   * into "items" would produce a non-array items field - not just malformed, but silently
   * undetectable as a wrapper on the next import (isListWrapper requires items to be an array), so
   * the import would see no items and discard the entire configuration without any error.
   */
  private JsonNode toItemsArray(PortalPackageFile file, JsonNode migrated) {
    if (migrated.isArray()) {
      return migrated;
    }
    if (file == PortalPackageFile.THIRD_PARTY_APP && migrated.isObject() && migrated.size() == 1) {
      JsonNode onlyValue = migrated.elements().next();
      if (onlyValue.isArray()) {
        return onlyValue;
      }
    }
    ArrayNode array = BusinessEntityConverter.getObjectMapper().createArrayNode();
    array.add(migrated);
    return array;
  }

  /**
   * True if any top-level item still carries its own "version" property. Only checked one level
   * deep (the items themselves, not their nested objects) - this is specifically about the legacy
   * per-item version stamp, not an incidental "version" field inside some unrelated nested object.
   */
  private boolean hasPerItemVersion(JsonNode items) {
    if (items == null) {
      return false;
    }
    if (items.isArray()) {
      for (JsonNode item : items) {
        if (item.isObject() && item.has(AbstractJsonVersion.VERSION_FIELD_NAME)) {
          return true;
        }
      }
      return false;
    }
    return items.isObject() && items.has(AbstractJsonVersion.VERSION_FIELD_NAME);
  }

  /**
   * Runs the legacy per-item migration chain for raw-variable types that have one. USER_MENU and
   * CUSTOM_MENU_ITEMS have no migrator (and no legacy pre-wrapper format to convert from), so their
   * items pass through unchanged other than the stripping/wrapping done by the caller.
   */
  private JsonNode migrateRawVariable(PortalPackageFile file, JsonNode node) {
    return switch (file) {
      case CUSTOM_STATISTIC -> new JsonStatisticMigrator(node).migrate();
      case CASE_DETAIL -> new JsonCaseDetailsMigrator(node).migrate();
      case THIRD_PARTY_APP -> new JsonThirdPartyApplicationMigrator(node).migrate();
      default -> node;
    };
  }

  /**
   * Recursively removes the per-item "version" field left behind by the legacy migration chain.
   * Once entries live inside the {"version": ..., "items": [...]} wrapper, the container's version
   * is the single source of truth for the collection's format, and a lingering per-item version is
   * a redundant vestige of the pre-wrapper design.
   */
  private void stripPerItemVersion(JsonNode node) {
    if (node.isArray()) {
      node.elements().forEachRemaining(this::stripPerItemVersion);
    } else if (node.isObject()) {
      ((ObjectNode) node).remove(AbstractJsonVersion.VERSION_FIELD_NAME);
    }
  }

  private void prepareDashboardForExport(Dashboard dashboard) {
    dashboard.setOldId(null);
    // Versioning now lives on the wrapper container (see writeDashboards()), not on individual
    // entities. JsonListWrapper's constructor already clears this on every item it wraps, but clear
    // it here too so the entity itself never carries a stale, previously-persisted version even
    // outside that path.
    dashboard.setVersion(null);
    Optional.ofNullable(dashboard.getWidgets()).orElse(Collections.emptyList()).forEach(widget -> {
      if (widget instanceof WelcomeDashboardWidget welcomeWidget) {
        WelcomeWidgetUtils.prepareWidgetForExport(welcomeWidget);
      } else if (widget instanceof NavigationDashboardWidget navWidget) {
        NavigationWidgetUtils.prepareWidgetForExport(navWidget);
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
    // Versioning now lives on the wrapper container, not on individual entities.
    link.setVersion(null);
    if (StringUtils.isBlank(link.getImageLocation())) {
      return;
    }
    String base64 = ImageUploadUtils.imageToBase64(link.getImageLocation(), link.getImageType(),
        ImageUploadUtils.EXTERNAL_LINK_IMAGE_DIRECTORY);
    // imageToBase64 returns "" on any failure (missing CMS object, extension mismatch, etc.)
    // without throwing - only clear the location once encoding actually produced something, so a
    // failed conversion doesn't turn a broken-but-visible reference into no reference at all.
    if (StringUtils.isNotBlank(base64)) {
      link.setImageContent(base64);
      link.setImageLocation(null);
    }
  }

  private boolean isRawVariableEmpty(PortalPackageFile file) {
    String json = Ivy.var().get(file.getVariableKey());
    return StringUtils.isBlank(json) || isEmptyJsonCollection(json);
  }

  /**
   * Treats "[]", "{}", and the canonical {@code {"version": "...", "items": []}}
   * wrapper (empty or missing items) as an empty collection.
   */
  private boolean isEmptyJsonCollection(String json) {
    String trimmed = json.trim();
    if ("[]".equals(trimmed) || "{}".equals(trimmed)) {
      return true;
    }
    try {
      JsonNode node = BusinessEntityConverter.getObjectMapper().readTree(trimmed);
      if (JsonListWrapper.isListWrapper(node)) {
        return node.get(JsonListWrapper.ITEMS_FIELD_NAME).isEmpty();
      }
    } catch (IOException e) {
      // Not parseable JSON - treat as non-empty raw content rather than fail export/import.
    }
    return false;
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
        case DASHBOARD -> importDashboards(migrate(json, node -> new JsonDashboardMigrator(node).migrate()));
        case EXTERNAL_LINK -> importExternalLinks(json);
        case CUSTOM_STATISTIC ->
            importList(migrate(json, node -> new JsonStatisticMigrator(node).migrate()), Statistic.class,
                file.getVariableKey());
        case USER_MENU -> importList(json, UserMenu.class, file.getVariableKey());
        case CASE_DETAIL ->
            importList(migrate(json, node -> new JsonCaseDetailsMigrator(node).migrate()), CaseDetails.class,
                file.getVariableKey());
        case THIRD_PARTY_APP -> importList(
            migrate(json, node -> new JsonThirdPartyApplicationMigrator(node).migrate()), Application.class,
            file.getVariableKey());
        case CUSTOM_MENU_ITEMS -> importList(json, CustomSubMenuItem.class, file.getVariableKey());
        case MENU_ORDER -> importSingle(json, MenuOrder.class, file.getVariableKey());
      }
      return true;
    } catch (Exception e) {
      Ivy.log().error("Failed to import Portal package file {0}", e, file.getFilename());
      return false;
    }
  }

  /**
   * Runs the given migrator (which mutates its JsonNode argument in place) over the parsed JSON
   * before it reaches entity deserialization, so package files exported from older Portal versions
   * go through the same migration chain as data already stored in Ivy variables, instead of being
   * deserialized as-is and silently dropping renamed/restructured fields.
   */
  private String migrate(String json, UnaryOperator<JsonNode> migrator) throws IOException {
    JsonNode node = BusinessEntityConverter.getObjectMapper().readTree(json);
    JsonNode migrated = migrator.apply(node);
    return BusinessEntityConverter.getObjectMapper().writeValueAsString(migrated);
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
