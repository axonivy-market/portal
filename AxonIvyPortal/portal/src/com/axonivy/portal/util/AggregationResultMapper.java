package com.axonivy.portal.util;

import java.util.List;

import com.axonivy.portal.dto.statistic.AggregationDTO;
import com.axonivy.portal.dto.statistic.AggregationResultDTO;
import com.axonivy.portal.dto.statistic.BucketDTO;

import ch.ivyteam.ivy.searchengine.client.agg.Aggregation;
import ch.ivyteam.ivy.searchengine.client.agg.AggregationResult;
import ch.ivyteam.ivy.searchengine.client.agg.Bucket;
import ch.ivyteam.ivy.searchengine.client.agg.Buckets;
import ch.ivyteam.ivy.searchengine.client.agg.Metric;

public class AggregationResultMapper {

  private AggregationResultMapper() {}

  public static AggregationResultDTO toAggResultDTO(AggregationResult result) {
    List<AggregationDTO> aggs = result.aggs().stream().map(AggregationResultMapper::mapAggregation).toList();
    AggregationResultDTO dto = new AggregationResultDTO();
    dto.setAggs(aggs);
    return dto;
  }

  private static AggregationDTO mapAggregation(Aggregation agg) {
    AggregationDTO dto = new AggregationDTO();
    dto.setName(agg.name());
    if (agg instanceof Buckets buckets) {
      List<BucketDTO> mapped = buckets.buckets().stream().map(AggregationResultMapper::mapBucket).toList();
      dto.setBuckets(mapped);
    } else if (agg instanceof Metric metric) {
      dto.setValue(metric.value());
    }
    return dto;
  }

  private static BucketDTO mapBucket(Bucket bucket) {
    BucketDTO dto = new BucketDTO();
    dto.setKey(bucket.key());
    dto.setCount(bucket.count());
    List<AggregationDTO> nestedAggs = bucket.aggs().stream().map(AggregationResultMapper::mapAggregation).toList();
    dto.setAggs(nestedAggs);
    return dto;
  }
}
