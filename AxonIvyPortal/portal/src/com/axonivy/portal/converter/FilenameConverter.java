package com.axonivy.portal.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import ch.ivyteam.ivy.environment.Ivy;

@FacesConverter("filenameConverter")
public class FilenameConverter implements Converter {

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
    if (value == null || value.trim().isEmpty()) {
      return "";
    }
    return value.trim() + (String) component.getAttributes().get("fileExtension");
  }

  @Override
  public String getAsString(FacesContext arg0, UIComponent component, Object value) throws ConverterException {
    if (value == null) {
      return "";
    }
    String filename = value.toString();
    int lastDot = filename.lastIndexOf(".");
    component.getAttributes().put("fileExtension", filename.substring(lastDot));

    return filename.substring(0, lastDot);
  }
}
