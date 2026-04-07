package com.axonivy.portal.dto.statistic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
