package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.List;

/**
 * One field as the agent describes it. {@code fieldName} must be a path it was given - the merge
 * checks that, because a field nobody can resolve is worse than one left with its derived name.
 */
public class DynamicFieldProposal implements Serializable {

  private static final long serialVersionUID = 1L;

  private String fieldName;
  /** A {@code DynamicFieldType} constant. */
  private String type;
  private List<FieldTitleProposal> titles;

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<FieldTitleProposal> getTitles() {
    return titles;
  }

  public void setTitles(List<FieldTitleProposal> titles) {
    this.titles = titles;
  }
}
