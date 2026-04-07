package com.axonivy.portal.dto.statistic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregationDTO {

  private String name;
  private List<BucketDTO> buckets;
  private Object value;

  public AggregationDTO() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<BucketDTO> getBuckets() {
    return buckets;
  }

  public void setBuckets(List<BucketDTO> buckets) {
    this.buckets = buckets;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
