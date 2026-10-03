package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.DateBucket;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Filter;
import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.SortOrder;

/**
 * The editable half of a {@link ProviderChartSpec}, bound to the configuration form.
 *
 * Held apart from the {@code Statistic} because an agent turn replaces that object wholesale, which
 * would leave the form bound to a discarded instance. The catalogue travels along so the field
 * dropdowns can only ever offer fields the data really has.
 */
public class ProviderChartForm implements Serializable {

  private static final long serialVersionUID = 1L;

  private String providerSignature;
  private String providerName;
  private Long applicationId;
  /** Resolved for display only - the chart stores the id, which is what stays unambiguous. */
  private String applicationName;
  private String labelPath;
  private String valuePath;
  private Aggregation aggregation = Aggregation.COUNT;
  private DateBucket dateBucket;
  private SortOrder sort = SortOrder.VALUE_DESC;
  private int limit = ProviderChartSpec.DEFAULT_LIMIT;
  private List<Filter> filters = new ArrayList<>();
  private DynamicFieldCatalogue catalogue;
  /** Recomputed on every chart-type change; the view reads it as the spinner's ceiling. */
  private int maxLimit = ProviderChartSpec.DEFAULT_LIMIT;

  /**
   * What the provider actually returned, pretty-printed and cut to a readable length.
   *
   * Shown beside the configuration so the chart can be checked against the numbers it was drawn
   * from - the chart is derived, this is the source. Not persisted and not sent to the agent: it
   * is loaded on demand, because rendering it otherwise would run the provider on every request.
   */
  private String resultJson;
  private int resultTotalRows;
  private int resultShownRows;
  private boolean resultLoaded;

  /** Set when the provider was read and no longer returns the fields this chart was built on. */
  private boolean fieldsChanged;

  /** Reads a stored spec back into the form, keeping the catalogue that came with it. */
  public void apply(ProviderChartSpec spec) {
    if (spec == null) {
      return;
    }
    providerSignature = spec.getProviderSignature();
    providerName = spec.getProviderName();
    applicationId = spec.getApplicationId();
    labelPath = spec.getLabelPath();
    valuePath = spec.getValuePath();
    aggregation = spec.getAggregation() == null ? Aggregation.COUNT : spec.getAggregation();
    dateBucket = spec.getDateBucket();
    sort = spec.getSort() == null ? SortOrder.VALUE_DESC : spec.getSort();
    limit = spec.getLimit();
    filters = new ArrayList<>(spec.getFilters());
    catalogue = spec.getFieldCatalogue();
  }

  // ------------------------------------------------------------- derived, for the view

  public List<DynamicField> getAllFields() {
    return catalogue == null ? List.of() : catalogue.getFields();
  }

  public List<DynamicField> getGroupableFields() {
    return catalogue == null ? List.of() : catalogue.getGroupableFields();
  }

  public List<DynamicField> getMeasurableFields() {
    return catalogue == null ? List.of() : catalogue.getMeasurableFields();
  }

  /** The measures the chosen field's type allows; what the aggregation dropdown offers. */
  public Set<Aggregation> getAvailableMeasures() {
    return catalogue == null ? Set.of(Aggregation.values()) : catalogue.measuresFor(valuePath);
  }

  /** True when the field being grouped by is a date, which is the only case a bucket applies to. */
  public boolean isLabelDate() {
    DynamicField field = catalogue == null ? null : catalogue.find(labelPath);
    return field != null && field.isNeedsDateBucket();
  }

  public boolean isMeasureRequired() {
    return Aggregation.COUNT != aggregation;
  }

  public boolean isCatalogueEmpty() {
    return catalogue == null || catalogue.isEmpty();
  }

  public boolean isFieldsChanged() {
    return fieldsChanged;
  }

  public void setFieldsChanged(boolean fieldsChanged) {
    this.fieldsChanged = fieldsChanged;
  }

  /** What the read-only Data source field shows: the callable, then where it lives. */
  public String getApplicationLabel() {
    return StringUtils.isNotBlank(applicationName) ? applicationName
        : applicationId == null ? StringUtils.EMPTY : String.valueOf(applicationId);
  }

  // ------------------------------------------------------------------------ accessors

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

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
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

  public DynamicFieldCatalogue getCatalogue() {
    return catalogue;
  }

  public void setCatalogue(DynamicFieldCatalogue catalogue) {
    this.catalogue = catalogue;
  }

  public int getMaxLimit() {
    return maxLimit;
  }

  public void setMaxLimit(int maxLimit) {
    this.maxLimit = maxLimit;
  }

  public String getResultJson() {
    return resultJson;
  }

  public void setResultJson(String resultJson) {
    this.resultJson = resultJson;
  }

  public int getResultTotalRows() {
    return resultTotalRows;
  }

  public void setResultTotalRows(int resultTotalRows) {
    this.resultTotalRows = resultTotalRows;
  }

  public int getResultShownRows() {
    return resultShownRows;
  }

  public void setResultShownRows(int resultShownRows) {
    this.resultShownRows = resultShownRows;
  }

  public boolean isResultLoaded() {
    return resultLoaded;
  }

  public void setResultLoaded(boolean resultLoaded) {
    this.resultLoaded = resultLoaded;
  }

  /** True when more rows exist than are being shown, which the view says out loud. */
  public boolean isResultTruncated() {
    return resultTotalRows > resultShownRows;
  }
}
