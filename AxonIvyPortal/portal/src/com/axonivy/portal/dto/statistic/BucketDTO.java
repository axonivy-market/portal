package com.axonivy.portal.dto.statistic;

import java.util.List;

public class BucketDTO {

  private Object key;
  private Object displayKey;
  private long count;
  private List<AggregationDTO> aggs;

  public BucketDTO() {}

  public Object getKey() {
    return key;
  }

  public void setKey(Object key) {
    this.key = key;
  }

  public Object getDisplayKey() {
    return displayKey;
  }

  public void setDisplayKey(Object displayKey) {
    this.displayKey = displayKey;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public List<AggregationDTO> getAggs() {
    return aggs;
  }

  public void setAggs(List<AggregationDTO> aggs) {
    this.aggs = aggs;
  }
}
