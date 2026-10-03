package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.List;

import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.DateBucket;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.FilterOperator;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.SortOrder;

/**
 * A chart over provider data, in the shape the agent emits.
 *
 * Separate from {@link ProviderChartSpec}, which is what gets stored: the agent names a provider
 * rather than a signature, and never sees or writes the schema snapshot. {@code
 * ProviderChartMapper} is what turns one into the other, and it is where every value is checked
 * against the real field list.
 *
 * The enums are reused from {@link ProviderChartSpec} because none of them carries
 * {@code @JsonValue} - unlike Portal's own {@code ChartType}, whose wire value would not match the
 * constant name in the generated schema.
 */
public class ProviderChartProposal implements Serializable {

  private static final long serialVersionUID = 1L;

  public static class FilterProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String path;
    private FilterOperator operator;
    private String value;

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public FilterOperator getOperator() {
      return operator;
    }

    public void setOperator(FilterOperator operator) {
      this.operator = operator;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }

  /** The provider's method name, exactly as listed in the catalogue. */
  private String provider;

  private String name;

  /** Same contract as a task or case chart: several sentences of plain business prose. */
  private String description;

  /** A {@code CustomChartType} constant. */
  private String chartType;

  /** The field to group by. Empty for a NUMBER chart. */
  private String labelPath;

  /** The field to measure. Empty when {@code aggregation} is COUNT. */
  private String valuePath;

  private Aggregation aggregation;

  /** Required when grouping by a date field, forbidden otherwise. */
  private DateBucket dateBucket;

  private SortOrder sort;

  private Integer limit;

  private List<FilterProposal> filters;

  private String xAxisTitle;
  private String yAxisTitle;

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getChartType() {
    return chartType;
  }

  public void setChartType(String chartType) {
    this.chartType = chartType;
  }

  public String getLabelPath() {
    return labelPath;
  }

  public void setLabelPath(String labelPath) {
    this.labelPath = labelPath;
  }

  public String getValuePath() {
    return valuePath;
  }

  public void setValuePath(String valuePath) {
    this.valuePath = valuePath;
  }

  public Aggregation getAggregation() {
    return aggregation;
  }

  public void setAggregation(Aggregation aggregation) {
    this.aggregation = aggregation;
  }

  public DateBucket getDateBucket() {
    return dateBucket;
  }

  public void setDateBucket(DateBucket dateBucket) {
    this.dateBucket = dateBucket;
  }

  public SortOrder getSort() {
    return sort;
  }

  public void setSort(SortOrder sort) {
    this.sort = sort;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public List<FilterProposal> getFilters() {
    return filters;
  }

  public void setFilters(List<FilterProposal> filters) {
    this.filters = filters;
  }

  public String getxAxisTitle() {
    return xAxisTitle;
  }

  public void setxAxisTitle(String xAxisTitle) {
    this.xAxisTitle = xAxisTitle;
  }

  public String getyAxisTitle() {
    return yAxisTitle;
  }

  public void setyAxisTitle(String yAxisTitle) {
    this.yAxisTitle = yAxisTitle;
  }
}
