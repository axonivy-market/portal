package com.axonivy.portal.components.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("filenameConverter")
public class FilenameConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }
    return value.strip() + (String) component.getAttributes().get("fileExtension");
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent component, Object value) throws ConverterException {
    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }
    String filename = value.toString();
    int lastDot = filename.lastIndexOf(".");

    if (lastDot == -1) {
      component.getAttributes().put("fileExtension", StringUtils.EMPTY);
      return filename;
    }

    component.getAttributes().put("fileExtension", filename.substring(lastDot));
    return filename.substring(0, lastDot);
  }
}
