package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.util.HtmlUtils;

@ManagedBean
@ViewScoped
public class HtmlSanitizerBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public String sanitize(String content) {
    return HtmlUtils.sanitize(content);
  }
}
