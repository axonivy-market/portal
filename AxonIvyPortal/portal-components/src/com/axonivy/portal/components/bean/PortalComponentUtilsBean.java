package com.axonivy.portal.components.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;

/**
 * Contains utilities, use them in xhtml. This bean is mentioned Portal documentation, handle it like public API.
 */
@ManagedBean
@ViewScoped
public class PortalComponentUtilsBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public String convertToJSON(List<String> list) {
    return BusinessEntityConverter.entityToJsonValue(list);
  }

}
