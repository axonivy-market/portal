package com.axonivy.portal.dto.statistic;

import java.util.List;

public class AggregationResultDTO {

  private List<AggregationDTO> aggs;

  public AggregationResultDTO() {}

  public AggregationResultDTO(List<AggregationDTO> aggs) {
    this.aggs = aggs;
  }

  public List<AggregationDTO> getAggs() {
    return aggs;
  }

  public void setAggs(List<AggregationDTO> aggs) {
    this.aggs = aggs;
  }
}
