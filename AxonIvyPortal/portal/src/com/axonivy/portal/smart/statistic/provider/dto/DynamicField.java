package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.enums.statistic.DynamicFieldType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.dto.DisplayName;
import ch.ivy.addon.portalkit.util.LanguageUtils;
import ch.ivy.addon.portalkit.util.LanguageUtils.NameResult;

/**
 * One charting dimension of a data provider. {@link #fieldName} is the JSON path itself, which
 * {@code ProviderChartProjector} resolves against the payload - the titles are presentation
 * only, so renaming one never moves a stored chart.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicField implements Serializable {

  private static final long serialVersionUID = 1L;

  private String fieldName;
  private List<DisplayName> titles = new ArrayList<>();
  private DynamicFieldType type;
  /** Distinct values seen while deriving; 0 when it was not worth counting. */
  private int cardinality;
  private List<String> samples = new ArrayList<>();

  @JsonIgnore
  private String title;

  public DynamicField() {}

  public DynamicField(String fieldName, DynamicFieldType type, int cardinality, List<String> samples) {
    this.fieldName = fieldName;
    this.type = type;
    this.cardinality = cardinality;
    this.samples = samples == null ? new ArrayList<>() : samples;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public List<DisplayName> getTitles() {
    return titles;
  }

  public void setTitles(List<DisplayName> titles) {
    this.titles = titles == null ? new ArrayList<>() : titles;
    this.title = null;
  }

  /** Resolved on read: deserialising a catalogue must not need a user session. */
  public String getTitle() {
    if (StringUtils.isNotBlank(title)) {
      return title;
    }
    // getLocalizedName honours its fallback only for a null list; titles is empty here, never null.
    return StringUtils.defaultIfBlank(LanguageUtils.getLocalizedName(titles, fieldName), fieldName);
  }

  public void setTitle(String title) {
    NameResult result = LanguageUtils.collectMultilingualNames(titles, title);
    this.titles = result.names();
    this.title = result.name();
  }

  public DynamicFieldType getType() {
    return type;
  }

  public void setType(DynamicFieldType type) {
    this.type = type;
  }

  public int getCardinality() {
    return cardinality;
  }

  public void setCardinality(int cardinality) {
    this.cardinality = cardinality;
  }

  public List<String> getSamples() {
    return samples;
  }

  public void setSamples(List<String> samples) {
    this.samples = samples == null ? new ArrayList<>() : samples;
  }

  /* Derived from the type, so kept out of the stored JSON where they could contradict it. */

  @JsonIgnore
  public boolean isGroupable() {
    return type != null && type.isGroupable();
  }

  @JsonIgnore
  public boolean isMeasurable() {
    return type != null && type.isMeasurable();
  }

  @JsonIgnore
  public boolean isNeedsDateBucket() {
    return type != null && type.needsDateBucket();
  }

  @JsonIgnore
  public Set<ProviderChartSpec.Aggregation> getMeasures() {
    return type == null ? Set.of(ProviderChartSpec.Aggregation.COUNT) : type.getMeasures();
  }
}
