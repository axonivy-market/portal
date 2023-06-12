package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.BusinessDetailsDTO;
import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

/**
 * Portal API for modify business case details
 *
 */
public class BusinessDetailsAPI {

  /**
   * <p>Create and set URL to BUSINESS_DETAILS custom field.</p>
   * <p>Use {@code com.axonivy.portal.components.dto.BusinessDetailsDTO.builder()} to
   * build {@code BusinessDetailsDTO}</p>
   * <p> This API support create two type of business details in just one method.
   * Process path type and External link.</p> 
   *
   * <p>Examples:</p>
   * <p>For process path:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder().path("Start Processes/ProcessDemo").build());</code></pre>
   *
   * <p>For external link:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder().path("https://www.google.com").build());</code></pre>
   *
   * <p>To disable start process in frame, use {@code isEmbedInFrame(Boolean)} in builder. By default, {@code isEmbedInFrame} is true. {@code isEmbedInFrame} is not affect to business detail for external link.</p>
   * 
   * <p>To customize ICase, use use {@code iCase(ICase)} in builder. By default, {@code Ivy.wfCase()} will be used.
   * @param businessDetailsDTO Business Details Builder
   * 
   */
  public static void create(BusinessDetailsDTO businessDetailsDTO) {
    String pageUrl = createPageUrl(businessDetailsDTO);
    setToCustomfield(businessDetailsDTO, pageUrl);
  }

  private static void setToCustomfield(BusinessDetailsDTO businessDetailDTO, String casePageUrl) {
    businessDetailDTO.getCase().customFields().stringField(CustomFields.BUSINESS_DETAILS).set(casePageUrl);
  }

  private static String createPageUrl(BusinessDetailsDTO businessDetailsDTO) {
    String casePageUrl = null;
    if (detectExternalLink(businessDetailsDTO.getPath())) {
      casePageUrl = businessDetailsDTO.getPath();
    } else {
      String processPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(businessDetailsDTO.getPath());
      if (StringUtils.isEmpty(processPath)) {
        throw new PortalException(String.format("Cannot find process path [%s].", businessDetailsDTO.getPath()));
      }
      casePageUrl = buildUrl(processPath, businessDetailsDTO);
    }
    return casePageUrl;
  }

  private static boolean detectExternalLink(String path) {
    return StringUtils.startsWithIgnoreCase(path, "http");
  }

  private static String buildUrl(String processPath, BusinessDetailsDTO businessDetailsDTO) {
    return processPath + "?caseId=" + businessDetailsDTO.getCase().getId() + (businessDetailsDTO.isEmbedInFrame() ? "&embedInFrame" : "");
  }

}
