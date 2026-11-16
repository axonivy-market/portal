package com.axonivy.portal.bo;

import java.io.Serializable;
import java.util.Objects;

import com.axonivy.portal.enums.statistic.AggregationInterval;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import ch.ivy.addon.portalkit.enums.DashboardColumnType;
import io.github.axonivy.json.schema.annotations.CustomType;
import io.github.axonivy.json.schema.annotations.Examples;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StatisticAggregation implements Serializable {

  private static final long serialVersionUID = 1L;
  @Examples({"state", "priority"})
  private String field;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String customFieldValue;
  @CustomType(StatisticAggregationType.class)
  private DashboardColumnType type;
  private AggregationInterval interval;
  @Examples({"InvoiceTotalAmount"})
  private String kpiField;
  @Examples({"sum", "avg", "max", "min"})
  private String aggregationMethod;

  public AggregationInterval getInterval() {
    return interval;
  }

  public void setInterval(AggregationInterval interval) {
    this.interval = interval;
  }

  @JsonPropertyDescription("Field to group by.")
  @JsonProperty(required = true)
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  @JsonProperty(required = true)
  public DashboardColumnType getType() {
    return type;
  }

  public void setType(DashboardColumnType type) {
    this.type = type;
  }

  public String getCustomFieldValue() {
    return customFieldValue;
  }

  public void setCustomFieldValue(String customFieldValue) {
    this.customFieldValue = customFieldValue;
  }

  @JsonPropertyDescription("Numeric custom field for calculations.")
  public String getKpiField() {
    return kpiField;
  }

  public void setKpiField(String kpiField) {
    this.kpiField = kpiField;
  }

  @JsonPropertyDescription("Calculation method.")
  public String getAggregationMethod() {
    return Objects.isNull(this.kpiField) || this.kpiField.isEmpty() ? "" : aggregationMethod;
  }

  public void setAggregationMethod(String aggregationMethod) {
    this.aggregationMethod = aggregationMethod;
  }

  private enum StatisticAggregationType {
    standard, custom;
  }
}
