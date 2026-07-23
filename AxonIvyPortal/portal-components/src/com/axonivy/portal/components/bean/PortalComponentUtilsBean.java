package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.List;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import com.axonivy.portal.components.constant.PortalComponentConstants;
import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;

/**
 * Contains utilities, use them in xhtml. This bean is mentioned Portal documentation, handle it like public API.
 */
@Named
@ViewScoped
public class PortalComponentUtilsBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public String convertToJSON(List<String> list) {
    return BusinessEntityConverter.entityToJsonValue(list);
  }

  public int getFilenameMaxLength() {
    return PortalComponentConstants.FILENAME_MAX_LENGTH;
  }
}
