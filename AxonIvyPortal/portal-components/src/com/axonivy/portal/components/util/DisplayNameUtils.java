package com.axonivy.portal.components.util;

import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.DisplayNameDTO;

import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;

/**
 * Utility for resolving DisplayNameDTO to display text. Supports both locale-value pairs and CMS URI references.
 */
public class DisplayNameUtils {

  private DisplayNameUtils() {}

  /**
   * Gets display text. For CMS URI, uses projectName to locate the correct process model version and CMS
   * automatically resolves the locale. For locale-value pairs, finds matching locale entry.
   */
  public static String getDisplayText(List<DisplayNameDTO> displayNames, String locale) {
    if (isEmpty(displayNames)) {
      return EMPTY;
    }

    if (isDisplayNameBasedOnCms(displayNames)) {
      return resolveCmsUri(displayNames.get(0));
    }

    return displayNames.stream().filter(dn -> locale.equals(dn.getLocale())).map(DisplayNameDTO::getValue).findFirst()
        .orElse(EMPTY);
  }

  /**
   * Gets display text using current session locale. For CMS URI, uses projectName to locate the correct process
   * model version and CMS automatically resolves the locale. For locale-value pairs, finds matching locale entry.
   */
  public static String getDisplayText(List<DisplayNameDTO> displayNames) {
    if (isDisplayNameBasedOnCms(displayNames)) {
      return resolveCmsUri(displayNames.get(0));
    }

    return getDisplayText(displayNames, Ivy.session().getContentLocale().toString());
  }

  /**
   * Checks if the list represents a CMS URI reference.
   */
  public static boolean isDisplayNameBasedOnCms(List<DisplayNameDTO> displayNames) {
    // List of display names is considered as CMS URI reference only if it contains exactly one entry which is a CMS URI.
    return ofNullable(displayNames).filter(list -> list.size() == 1).map(list -> list.get(0))
        .map(DisplayNameDTO::isCmsUri).orElse(false);
  }

  /**
   * Creates a single-element list of DisplayNameDTO for CMS URI reference.
   * 
   * @param cmsUri the CMS URI (e.g., "/Processes/SideStep/ProcessName")
   * @param projectName the project name to locate CMS content (optional)
   * @return single-element list containing the DisplayNameDTO with CMS reference
   */
  public static List<DisplayNameDTO> createCmsDisplayName(String cmsUri, String projectName) {
    if (StringUtils.isNotBlank(projectName)) {
      return List.of(DisplayNameDTO.fromCms(cmsUri, projectName));
    }
    return List.of(DisplayNameDTO.fromCms(cmsUri));
  }

  private static String resolveCmsUri(DisplayNameDTO cmsDisplayName) {
    String projectName = cmsDisplayName.getProjectName();
    return IApplicationRepository.of(ISecurityContext.current()).all().stream()
        .map(app -> app.findProcessModelVersion(projectName)).filter(Objects::nonNull).findFirst()
        .map(pmv -> ContentManagement.of(pmv).co(cmsDisplayName.getValue())).orElse(EMPTY);
  }
}
