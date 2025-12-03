package com.axonivy.portal.components.util;

import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.DisplayNameDTO;

import ch.ivyteam.ivy.application.app.IApplicationRepository;
import ch.ivyteam.ivy.cm.ContentObjectReader;
import ch.ivyteam.ivy.cm.ContentObjectValue;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;

/**
 * Utility for resolving DisplayNameDTO to display text. Supports both locale-value pairs and CMS URI references.
 */
public class DisplayNameUtils {

  private DisplayNameUtils() {}

  /**
   * Resolves display text by locale. For CMS URI, uses projectName to locate the correct process model version. For
   * locale-value pairs, finds matching locale entry.
   */
  public static String getDisplayText(List<DisplayNameDTO> displayNames, String locale) {
    if (isEmpty(displayNames)) {
      return EMPTY;
    }

    if (isDisplayNameBasedOnCms(displayNames)) {
      return resolveCmsUri(displayNames.get(0), locale);
    }

    return displayNames.stream().filter(dn -> locale.equals(dn.getLocale())).map(DisplayNameDTO::getValue).findFirst()
        .orElse(EMPTY);
  }

  /**
   * Resolves display text using current user's content locale.
   */
  public static String getDisplayText(List<DisplayNameDTO> displayNames) {
    return getDisplayText(displayNames, Ivy.session().getContentLocale().toString());
  }

  /**
   * Checks if the list represents a CMS URI reference.
   */
  public static boolean isDisplayNameBasedOnCms(List<DisplayNameDTO> displayNames) {
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

  private static String resolveCmsUri(DisplayNameDTO cmsDisplayName, String locale) {
    String projectName = cmsDisplayName.getProjectName();
    return IApplicationRepository.of(ISecurityContext.current()).all().stream()
        .map(app -> app.findProcessModelVersion(projectName)).filter(Objects::nonNull).findFirst()
        .flatMap(pmv -> ContentManagement.cms(pmv).get(cmsDisplayName.getValue()))
        .flatMap(co -> ofNullable(co.value().get(locale))).map(ContentObjectValue::read)
        .map(ContentObjectReader::string).orElse(EMPTY);
  }
}
