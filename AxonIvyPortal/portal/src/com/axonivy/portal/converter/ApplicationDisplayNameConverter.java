package com.axonivy.portal.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("applicationDisplayNameConverter")
public class ApplicationDisplayNameConverter implements Converter<Object> {

  @Override
  public Object getAsObject(FacesContext facesContext, UIComponent component, String value) throws ConverterException {
    return StringUtils.normalizeSpace(value);
  }

  @Override
  public String getAsString(FacesContext facesContext, UIComponent component, Object value) throws ConverterException {
    return StringUtils.normalizeSpace(value.toString());
  }

}
