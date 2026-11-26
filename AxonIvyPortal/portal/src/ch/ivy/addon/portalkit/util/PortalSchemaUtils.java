package ch.ivy.addon.portalkit.util;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.PortalSchema;
import com.axonivy.portal.service.ProjectVersionService;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;

/**
 * Utility methods for building JSON {@code $schema} URLs for Portal configuration variables.
 */
public class PortalSchemaUtils {

  /**
   * Returns the {@code $schema} URL for the given Portal variable key if it has a registered
   * {@link PortalSchema}, or {@link StringUtils#EMPTY} otherwise.
   *
   * @param variableKey the Portal variable key (e.g. {@code "Portal.Dashboard"})
   * @return the full schema URL, or an empty string if the variable has no schema
   */
  public static String schemaFor(String variableKey) {
    return PortalSchema.fromVariableKey(variableKey)
        .map(schema -> schemaUrl(schema.getFileName()))
        .orElse(StringUtils.EMPTY);
  }

  /**
   * Builds the full {@code $schema} URL for a schema file name using the configured base URL
   * and the current Portal version.
   *
   * @param fileName the schema file name (e.g. {@code "portal-dashboard-schema.json"})
   * @return the full schema URL, or {@link StringUtils#EMPTY} if {@code fileName} is blank
   */
  public static String schemaUrl(String fileName) {
    if (StringUtils.isBlank(fileName)) {
      return StringUtils.EMPTY;
    }
    return schemaBaseUrl() + getPortalVersion() + "/" + fileName;
  }

  /**
   * Returns the current Portal version from {@link ProjectVersionService}.
   *
   * @return the non-blank Portal version string
   * @throws IllegalStateException if the Portal version cannot be determined
   */
  private static String getPortalVersion() {
    String portalVersion = ProjectVersionService.getInstance().getPortalVersion();
    if (StringUtils.isBlank(portalVersion)) {
      throw new IllegalStateException("Could not determine the current Portal version");
    }
    return portalVersion;
  }

  /**
   * Returns the schema base URL from {@link GlobalVariable#SCHEMA_BASE_URL} (falling back to
   * its default value), guaranteed to end with {@code "/"}.
   *
   * @return the normalized schema base URL
   */
  private static String schemaBaseUrl() {
    String baseUrl = GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.SCHEMA_BASE_URL);
    if (StringUtils.isBlank(baseUrl)) {
      baseUrl = GlobalVariable.SCHEMA_BASE_URL.getDefaultValue();
    }
    return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
  }
}
