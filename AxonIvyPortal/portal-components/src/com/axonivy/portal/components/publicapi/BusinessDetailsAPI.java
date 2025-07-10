package com.axonivy.portal.components.publicapi;

import static com.axonivy.portal.components.constant.CustomFields.BUSINESS_DETAILS;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.BusinessDetailsDTO;
import com.axonivy.portal.components.service.exception.PortalException;

import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.start.IWebStartable;

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
   * {@link IWebStartable#getId() IWebStartable ID} and external link.</p> 
   *
   * <p>Examples:</p>
   * <p>For IWebStartable ID:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder()
   * .path("designer/portal-components-examples/Start Processes/BusinessDetails/showInvestmentRequestCustomFields.ivp").build());</code></pre>
   * Noted that you could use the end part of an IWebStartable ID like `Start Processes/BusinessDetails/showInvestmentRequestCustomFields.ivp` 
   * but you need to make sure there is only one IWebStartable in the security context ends with that path.
   * <p>For external link:</p>
   * <pre><code>BusinessDetailsAPI.create(BusinessDetailsDTO.builder().path("https://www.google.com").build());</code></pre>
   *
   * <p>To disable start process in frame, use {@code isEmbedInFrame(Boolean)} in builder. By default, {@code isEmbedInFrame} is true. {@code isEmbedInFrame} does not affect to business detail for external link.</p>
   * 
   * <p>To specify ICase, use {@code iCase(ICase)} in builder. By default, {@code Ivy.wfCase()} is used.
   * @param businessDetailsDTO BusinessDetailsDTO
   */
  public static void create(BusinessDetailsDTO businessDetailsDTO) {
    String customField;
    String path = businessDetailsDTO.getPath();
    if (detectExternalLink(path)) {
      customField = path;
    } else {
      List<IWebStartable> iWebStartables = IWorkflowSession.current().getAllStartables().toList();

      IWebStartable targetStartable =
          iWebStartables.stream().filter(startable -> startable.getId().endsWith(path)).findAny().orElseThrow(
              () -> new PortalException(String.format("Cannot find IWebStartable by ID [%s].", path)));
      customField = targetStartable.getId();
      if (businessDetailsDTO.isEmbedInFrame()) {
        customField += "?embedInFrame";
      }
    }
    businessDetailsDTO.getCase().customFields().stringField(BUSINESS_DETAILS).set(customField);
  }

  /**
   * <p>Creates and sets path to case custom string field {@code businessDetails} of current case for displaying business case details page.</p>
   *
   * <p>Examples:</p>
   * <pre><code>BusinessDetailsAPI.create("designer/portal-components-examples/Start Processes/BusinessDetails/showInvestmentRequestCustomFields.ivp");</code></pre>
   * <pre><code>BusinessDetailsAPI.create("https://www.google.com");</code></pre>
   *
   * @param link String could be an external link or {@link IWebStartable#getId() IWebStartable ID}
   */
  public static void create(String link) {
    BusinessDetailsDTO businessDetailsDTO = BusinessDetailsDTO.builder().path(link).build();
    create(businessDetailsDTO);
  }
  

  private static boolean detectExternalLink(String path) {
    return StringUtils.startsWithIgnoreCase(path, "http:") || StringUtils.startsWithIgnoreCase(path, "https:");
  }
}