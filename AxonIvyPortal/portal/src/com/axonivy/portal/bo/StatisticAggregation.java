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
  @JsonPropertyDescription("Field to group by.")
  @JsonProperty(required = true)
  @Examples({"state", "priority"})
  private String field;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String customFieldValue;
  @JsonProperty(required = true)
  @CustomType(StatisticAggregationType.class)
  private DashboardColumnType type;
  private AggregationInterval interval;
  @JsonPropertyDescription("Numeric custom field for calculations.")
  @Examples({"InvoiceTotalAmount"})
  private String kpiField;
  @JsonPropertyDescription("Calculation method.")
  @Examples({"sum", "avg", "max", "min"})
  private String aggregationMethod;

  public AggregationInterval getInterval() {
    return interval;
  }

  public void setInterval(AggregationInterval interval) {
    this.interval = interval;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

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

  public String getKpiField() {
    return kpiField;
  }

  public void setKpiField(String kpiField) {
    this.kpiField = kpiField;
  }

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
