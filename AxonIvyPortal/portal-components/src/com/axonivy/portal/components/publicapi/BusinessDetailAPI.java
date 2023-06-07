package com.axonivy.portal.components.publicapi;

import com.axonivy.portal.components.dto.BusinessDetailDTO;
import com.axonivy.portal.components.dto.builder.BusinessDetailBuilder;

public class BusinessDetailAPI {

  public static void set(BusinessDetailBuilder builder) {
    BusinessDetailDTO businessDetailDTO = builder.build();
    String pageUrl = createPageUrl(businessDetailDTO);
    setCustomfield(businessDetailDTO, pageUrl);
  }

  private static void setCustomfield(BusinessDetailDTO businessDetailDTO, String casePageUrl) {
    businessDetailDTO.getiCase().customFields().textField("businessDetails").set(casePageUrl);
  }

  private static String createPageUrl(BusinessDetailDTO businessDetailDTO) {
    String casePageUrl = null;
    if (businessDetailDTO.isFullPath()) {
      casePageUrl = businessDetailDTO.getURL();
    } else {
      casePageUrl = ProcessStartAPI.findRelativeUrlByProcessStartFriendlyRequestPath(businessDetailDTO.getURL())
          + "?caseId=" + businessDetailDTO.getiCase().getId();
    }
    return casePageUrl;
  }
}
