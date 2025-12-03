package com.axonivy.portal.components.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * DTO for multilingual display names supporting both locale-value pairs and CMS URI references.
 * <p>
 * <b>Usage:</b>
 * <ul>
 * <li>Locale-value pairs: {@code new DisplayNameDTO("en", "My Title")} - for user-editable content</li>
 * <li>CMS URI with project: {@code DisplayNameDTO.fromCms("/Processes/SideStep/ProcessName", "portal-developer-examples")} - for project-specific CMS</li>
 * <li>CMS URI current project: {@code DisplayNameDTO.fromCms("/Processes/SideStep/ProcessName")} - uses current process model</li>
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
   * Creates a CMS URI reference with project context.
   * 
   * @param cmsUri the CMS URI (e.g., "/Processes/SideStep/ProcessName")
   * @param projectName the project name to locate CMS content
   * @return DisplayNameDTO with CMS URI reference
   */
  public static DisplayNameDTO fromCms(String cmsUri, String projectName) {
    return new DisplayNameDTO(null, cmsUri, projectName);
  }

  /**
   * Creates a CMS URI reference using current process model.
   * 
   * @param cmsUri the CMS URI (e.g., "/Processes/SideStep/ProcessName")
   * @return DisplayNameDTO with CMS URI reference
   */
  public static DisplayNameDTO fromCms(String cmsUri) {
    return new DisplayNameDTO(null, cmsUri, Ivy.request().getProcessModel().getName());
  }

  /**
   * Checks if this DTO represents a CMS URI reference (vs locale-value pair).
   * 
   * @return true if this is a CMS URI reference, false if locale-value pair
   */
  @JsonIgnore
  public boolean isCmsUri() {
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
