package com.axonivy.portal.components.publicapi.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.persistence.converter.BusinessEntityConverter;

@ManagedBean
@ViewScoped
public class PortalComponentUtilsBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public static String convertToJSON(List<String> list) {
    return BusinessEntityConverter.entityToJsonValue(list);
  }

}
