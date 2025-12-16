package com.axonivy.portal.components.examples.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import com.axonivy.portal.components.publicapi.PortalDateTimePatternAPI;

@ManagedBean
@ViewScoped
public class DateTimeExampleBean implements Serializable {
  private static final long serialVersionUID = 393379085832602153L;
  
  public String getDateTimePattern() {
    return PortalDateTimePatternAPI.getDateTimePattern();
  }
  
  public String getDatePattern() {
    return PortalDateTimePatternAPI.getDatePattern();
  }

}
