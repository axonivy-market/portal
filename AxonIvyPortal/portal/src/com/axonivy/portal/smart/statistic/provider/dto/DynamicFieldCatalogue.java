package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.smart.statistic.provider.dto.ProviderChartSpec.Aggregation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicFieldCatalogue implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<DynamicField> fields = new ArrayList<>();
  private int rowCount;

  public List<DynamicField> getFields() {
    return fields;
  }

  public void setFields(List<DynamicField> fields) {
    this.fields = fields == null ? new ArrayList<>() : fields;
  }

  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  @JsonIgnore
  public boolean isEmpty() {
    return fields.isEmpty();
  }

  @JsonIgnore
  public List<String> getFieldNames() {
    return fields.stream().map(DynamicField::getFieldName).toList();
  }

  public DynamicField find(String fieldName) {
    if (StringUtils.isBlank(fieldName)) {
      return null;
    }
    return fields.stream().filter(field -> fieldName.equals(field.getFieldName())).findFirst().orElse(null);
  }

  public DynamicField findByTitle(String title) {
    if (StringUtils.isBlank(title)) {
      return null;
    }
    String wanted = title.trim();
    List<DynamicField> matches = fields.stream()
        .filter(field -> field.getTitles().stream()
            .anyMatch(name -> wanted.equalsIgnoreCase(StringUtils.trim(name.getValue()))))
        .toList();
    return matches.size() == 1 ? matches.get(0) : null;
  }

  @JsonIgnore
  public List<DynamicField> getGroupableFields() {
    return fields.stream().filter(DynamicField::isGroupable).toList();
  }

  @JsonIgnore
  public List<DynamicField> getMeasurableFields() {
    return fields.stream().filter(DynamicField::isMeasurable).toList();
  }

  public Set<Aggregation> measuresFor(String fieldName) {
    DynamicField field = find(fieldName);
    return field == null ? Set.of(Aggregation.COUNT) : field.getMeasures();
  }

  @JsonIgnore
  public Set<Aggregation> getAvailableMeasures() {
    Set<Aggregation> available = EnumSet.of(Aggregation.COUNT);
    fields.forEach(field -> available.addAll(field.getMeasures()));
    return available;
  }
}
