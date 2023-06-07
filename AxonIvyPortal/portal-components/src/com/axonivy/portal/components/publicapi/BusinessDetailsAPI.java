package com.axonivy.portal.components.publicapi;

import org.apache.commons.lang.StringUtils;

import com.axonivy.portal.components.constant.CustomFields;
import com.axonivy.portal.components.dto.BusinessDetailsDTO;
import com.axonivy.portal.components.dto.builder.BusinessDetailsBuilder;
import com.axonivy.portal.components.service.exception.PortalException;

public class BusinessDetailsAPI {

  public static void set(BusinessDetailsBuilder builder) {
    BusinessDetailsDTO businessDetailDTO = builder.build();
    String pageUrl = createPageUrl(businessDetailDTO);
    setCustomfield(businessDetailDTO, pageUrl);
  }

  private static void setCustomfield(BusinessDetailsDTO businessDetailDTO, String casePageUrl) {
    businessDetailDTO.getiCase().customFields().textField(CustomFields.BUSINESS_DETAILS).set(casePageUrl);
  }

  private static String createPageUrl(BusinessDetailsDTO businessDetailDTO) {
    String casePageUrl = null;
    if (businessDetailDTO.isFullPath()) {
      casePageUrl = businessDetailDTO.getURL();
    } else {
      String processPath = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(businessDetailDTO.getURL());
      if (StringUtils.isEmpty(processPath)) {
        throw new PortalException(String.format("Cannot find process path [%s].", businessDetailDTO.getURL()));
      }
      casePageUrl = processPath + "?caseId=" + businessDetailDTO.getiCase().getId();
    }
    return casePageUrl;
  }

}
