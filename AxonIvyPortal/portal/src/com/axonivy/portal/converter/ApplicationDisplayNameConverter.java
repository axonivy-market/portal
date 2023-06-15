package com.axonivy.portal.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("applicationDisplayNameConverter")
public class ApplicationDisplayNameConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String value) throws ConverterException {
    return StringUtils.normalizeSpace(value);
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object value) throws ConverterException {
    return StringUtils.normalizeSpace(value.toString());
  }

}
