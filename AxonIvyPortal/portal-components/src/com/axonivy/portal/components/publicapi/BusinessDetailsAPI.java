package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.BusinessDetailsDTO;
import com.axonivy.portal.components.service.exception.PortalException;

/**
 * Portal API for modify business case details
 *
 */
public class BusinessDetailsAPI {

  /**
   * <p>Creates and sets path to case custom string field {@code businessDetails} for displaying business case details page.</p>
   * <p>Use {@code com.axonivy.portal.components.dto.BusinessDetailsDTO.builder()} to
   * build {@code BusinessDetailsDTO}</p>
   * <p>This API supports two types of business details:
   * process path and external link.</p> 
   *
   * <p>Examples:</p>
   * <p>For process path:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder().path("Start Processes/ProcessDemo").build());</code></pre>
   *
   * <p>For external link:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder().path("https://www.google.com").build());</code></pre>
   *
   * <p>To disable start process in frame, use {@code isEmbedInFrame(Boolean)} in builder. By default, {@code isEmbedInFrame} is true. {@code isEmbedInFrame} does not affect to business detail for external link.</p>
   * 
   * <p>To specify ICase, use {@code iCase(ICase)} in builder. By default, {@code Ivy.wfCase()} is used.
   * @param businessDetailsDTO BusinessDetailsDTO
   */
  public static void create(BusinessDetailsDTO businessDetailsDTO) {
    String pageUrl = createPageUrl(businessDetailsDTO);
    setToCustomField(businessDetailsDTO, pageUrl);
  }
  
  /**
   * <p>Creates and sets path to case custom string field {@code businessDetails} of current case for displaying business case details page.</p>
   *
   * <p>Examples:</p>
   * <pre><code>BusinessDetailsAPI.create("Start Processes/ProcessDemo");</code></pre>
   * <pre><code>BusinessDetailsAPI.create("https://www.google.com");</code></pre>
   *
   * @param link String
   */
  public static void create(String link) {
    BusinessDetailsDTO businessDetailsDTO = BusinessDetailsDTO.builder().path(link).build();
    create(businessDetailsDTO);
  }
  
  private static void setToCustomField(BusinessDetailsDTO businessDetailDTO, String casePageUrl) {
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
      casePageUrl = processPath + "?uuid=" + businessDetailsDTO.getCase().uuid() + (businessDetailsDTO.isEmbedInFrame() ? "&embedInFrame" : "");
    }
    return casePageUrl;
  }

  private static boolean detectExternalLink(String path) {
    return StringUtils.startsWithIgnoreCase(path, "http");
  }
}