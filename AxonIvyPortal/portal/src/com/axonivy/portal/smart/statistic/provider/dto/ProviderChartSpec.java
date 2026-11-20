package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.axonivy.portal.smart.ai.AgentEnums;

/**
 * How one chart is built from a tagged data provider: which callable to run, and how to turn the
 * JSON it returns into buckets.
 *
 * Stored on the {@code Statistic}, which is what makes a later render deterministic - the agent
 * shapes this once, and every refresh afterwards is {@code ProviderChartProjector} applying it.
 * The {@link #schema} snapshot travels with it so the next prompt about this chart starts from the
 * real field list instead of guessing the data structure again.
 *
 * Persisted under the field names themselves. The stored chart is the first thing anyone opens
 * when a chart is wrong, so it is written to be read rather than to be small.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProviderChartSpec implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final int DEFAULT_LIMIT = 20;

  public enum Aggregation {
    COUNT("Count of records"), SUM("Total of"), AVG("Average of"), MIN("Lowest"), MAX("Highest");

    private final String label;

    Aggregation(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }

    @JsonCreator
    public static Aggregation fromJson(String value) {
      return AgentEnums.parse(Aggregation.class, value);
    }
  }

  public enum DateBucket {
    DAY("Day"), WEEK("Week"), MONTH("Month"), YEAR("Year");

    private final String label;

    DateBucket(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }

    @JsonCreator
    public static DateBucket fromJson(String value) {
      return AgentEnums.parse(DateBucket.class, value);
    }
  }

  public enum SortOrder {
    VALUE_DESC("Value, highest first"), VALUE_ASC("Value, lowest first"),
    LABEL_ASC("Label, A to Z"), LABEL_DESC("Label, Z to A");

    private final String label;

    SortOrder(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }

    @JsonCreator
    public static SortOrder fromJson(String value) {
      return AgentEnums.parse(SortOrder.class, value);
    }
  }

  public enum FilterOperator {
    EQUALS("is"), NOT_EQUALS("is not"), GREATER("is greater than"),
    GREATER_OR_EQUAL("is at least"), LESS("is less than"), LESS_OR_EQUAL("is at most"),
    CONTAINS("contains"), IS_EMPTY("is empty"), NOT_EMPTY("is not empty");

    private final String label;

    FilterOperator(String label) {
      this.label = label;
    }

    public String getLabel() {
      return label;
    }

    @JsonCreator
    public static FilterOperator fromJson(String value) {
      return AgentEnums.parse(FilterOperator.class, value);
    }
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Filter implements Serializable {

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

  /** The callable's signature, as {@code provideAccountsReceivable()}. */
  private String providerSignature;

  /** The callable's method name, kept for messages and for re-resolving after a redeploy. */
  private String providerName;

  /**
   * The application the callable was resolved in. Stored next to the signature because a signature
   * alone is only unique within one application, so without this a chart exported to an engine that
   * runs several of them could bind to the wrong provider.
   */
  private Long applicationId;

  /** Field to group by. Empty for a NUMBER chart, which has no breakdown. */
  private String labelPath;

  /** Field to measure. Empty when {@link #aggregation} is COUNT. */
  private String valuePath;

  private Aggregation aggregation = Aggregation.COUNT;

  /** Set only when grouping by a date field. */
  private DateBucket dateBucket;

  private SortOrder sort = SortOrder.VALUE_DESC;

  private int limit = DEFAULT_LIMIT;

  private List<Filter> filters = new ArrayList<>();

  /** The fields this chart was built against. */
  private DynamicFieldCatalogue fieldCatalogue;

  public String getProviderSignature() {
    return providerSignature;
  }

  public void setProviderSignature(String providerSignature) {
    this.providerSignature = providerSignature;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
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

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<Filter> getFilters() {
    return filters;
  }

  public void setFilters(List<Filter> filters) {
    this.filters = filters == null ? new ArrayList<>() : filters;
  }

  public DynamicFieldCatalogue getFieldCatalogue() {
    return fieldCatalogue;
  }

  public void setFieldCatalogue(DynamicFieldCatalogue fieldCatalogue) {
    this.fieldCatalogue = fieldCatalogue;
  }
}
