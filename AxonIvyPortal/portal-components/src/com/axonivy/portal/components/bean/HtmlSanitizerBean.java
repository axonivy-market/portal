package com.axonivy.portal.components.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.axonivy.portal.components.publicapi.SanitizeAPI;
import com.axonivy.portal.components.util.HtmlUtils;

/**
 * To prevent XSS, all outputText escape="false" need to use this class. It would help us check XSS easier to search all
 * escape="false" in xhtml files and checking HtmlSanitizerBean is used for value attribute.
 *
 */
@ManagedBean
@ViewScoped
public class HtmlSanitizerBean implements Serializable {

  private static final long serialVersionUID = 1L;

  public String sanitize(String content) {
    return HtmlUtils.sanitize(content);
  }

  /**
   * This method is used to clearly mark XSS is considered.
   * @param content 
   * @return santized content
   */
  public String sanitizeIgnoredWithAwareness(String content) {
    return content;
  }

  public String escapeForJS(String input) {
    return SanitizeAPI.escapeForJavascript(input);
  }

  public String escapeForIcon(String input) {
    return HtmlUtils.escapeForIcon(input);
  }
}
