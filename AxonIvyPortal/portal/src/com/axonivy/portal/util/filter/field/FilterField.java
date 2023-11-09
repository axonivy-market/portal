package com.axonivy.portal.util.filter.field;

import java.util.Objects;

import com.axonivy.portal.dto.dashboard.filter.DashboardFilter;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCreatedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCustomString;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldCustomTimestamp;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldDescription;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldFinishedDate;
import com.axonivy.portal.util.filter.field.caze.CaseFilterFieldName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.ivyteam.ivy.workflow.query.CaseQuery;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = CaseFilterFieldName.class, name = "case-name"),
    @Type(value = CaseFilterFieldDescription.class, name = "case-description"),
    @Type(value = CaseFilterFieldCreatedDate.class, name = "case-created-date"),
    @Type(value = CaseFilterFieldFinishedDate.class, name = "case-finished-date"),
    @Type(value = CaseFilterFieldCustomString.class, name = "case-custom-string"),
    @Type(value = CaseFilterFieldCustomTimestamp.class, name = "case-custom-timestamp")})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class FilterField {

  private String name;

  public FilterField() {
    super();
  }

  public FilterField(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract String getLabel();

  public abstract void initFilter(DashboardFilter filter);

  public abstract CaseQuery generateFilterQuery(DashboardFilter filter);

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    FilterField other = (FilterField) obj;
    return Objects.equals(name, other.name);
  }

}
