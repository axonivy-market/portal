package com.axonivy.portal.dto;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;

public class InformationDTO extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 2826532137796682507L;

  public static final String DEFAULT_INFOMATION_ICON = "si si-infomation-circle";
  private String icon;
  private String name;
  private String content;
  private Locale locale;


  public InformationDTO() {}

  public InformationDTO(String icon, String name, String content, Locale locale) {
    super();
    this.icon = icon;
    this.name = name;
    this.content = content;
    this.locale = locale;
  }

  public InformationDTO(Locale Locale) {
    this.locale = Locale;
  }

  public String getIcon() {
    return StringUtils.isEmpty(icon) ? DEFAULT_INFOMATION_ICON : icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale local) {
    this.locale = local;
  }

}
