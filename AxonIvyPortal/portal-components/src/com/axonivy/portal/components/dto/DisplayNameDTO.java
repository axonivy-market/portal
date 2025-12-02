package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * DTO for multilingual display names supporting both locale-value pairs and CMS path references.
 * <p>
 * <b>Usage:</b>
 * <ul>
 * <li>Locale-value pairs: {@code new DisplayNameDTO("en", "My Title")} - for user-editable content</li>
 * <li>CMS path with project: {@code DisplayNameDTO.fromCms("/Labels/MyPath", "my-project")} - for project-specific
 * CMS</li>
 * <li>CMS path current project: {@code DisplayNameDTO.fromCms("/Labels/MyPath")} - uses current process model</li>
 * </ul>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DisplayNameDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String locale;
  private String value;
  private String projectName;

  public DisplayNameDTO() {}

  public DisplayNameDTO(String locale, String value) {
    this.locale = locale;
    this.value = value;
  }

  private DisplayNameDTO(String locale, String value, String projectName) {
    this.locale = locale;
    this.value = value;
    this.projectName = projectName;
  }

  /**
   * Creates a CMS path reference with project context.
   * 
   * @param cmsPath the CMS path (e.g., "/Labels/MyPath")
   * @param projectName the project name to locate CMS content
   * @return DisplayNameDTO with CMS path reference
   */
  public static DisplayNameDTO fromCms(String cmsPath, String projectName) {
    return new DisplayNameDTO(null, cmsPath, projectName);
  }

  /**
   * Creates a CMS path reference using current process model.
   * 
   * @param cmsPath the CMS path (e.g., "/Labels/MyPath")
   * @return DisplayNameDTO with CMS path reference
   */
  public static DisplayNameDTO fromCms(String cmsPath) {
    return new DisplayNameDTO(null, cmsPath, Ivy.request().getProcessModel().getName());
  }

  @JsonIgnore
  public boolean isCmsPath() {
    return locale == null;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    DisplayNameDTO other = (DisplayNameDTO) obj;
    return Objects.equals(locale, other.locale) && Objects.equals(value, other.value)
        && Objects.equals(projectName, other.projectName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locale, value, projectName);
  }
}
