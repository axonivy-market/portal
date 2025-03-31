package com.axonivy.portal.dto;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.InfomationColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.ivy.addon.portalkit.configuration.AbstractConfiguration;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.environment.Ivy;

public class InfomationDTO extends AbstractConfiguration implements Serializable {

  private static final long serialVersionUID = 2826532137796682507L;

  public static final String DEFAULT_INFOMATION_ICON = "si si-infomation-circle";
  private String icon;
  private String name;
  private String content;

  @JsonIgnore
  private Locale local;
  @JsonIgnore
  private ContentObject contentObject;

  public InfomationDTO() {}

  public InfomationDTO(ContentObject contentObject) {
    this.setId(contentObject.name());
    this.contentObject = contentObject;
    this.icon = getCMSValueByKey(InfomationColumn.ICON);
    this.name = getCMSValueByKey(InfomationColumn.NAME);
    this.content = getCMSValueByKey(InfomationColumn.CONTENT);
    this.local = Ivy.session().getContentLocale();
  }

  private String getCMSValueByKey(InfomationColumn column) {
    return Ivy.cms().co(contentObject.child().string(column.getKey()).uri());
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

  public Locale getLocal() {
    return local;
  }

  public void setLocal(Locale local) {
    this.local = local;
  }

  public ContentObject getContentObject() {
    return contentObject;
  }

  public void setContentObject(ContentObject contentObject) {
    this.contentObject = contentObject;
  }


}
