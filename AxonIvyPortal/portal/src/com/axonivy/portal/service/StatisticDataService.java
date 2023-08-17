package com.axonivy.portal.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.collections.CollectionUtils;

import com.axonivy.portal.bo.StatisticData;
import com.axonivy.portal.dto.statisticChart.StatisticDataDto;
import com.axonivy.portal.enums.ChartType;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.persistence.converter.BusinessEntityConverter;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
import ch.ivyteam.ivy.elasticsearch.client.agg.Bucket;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.stats.WorkflowStats;

public class StatisticDataService extends JsonConfigurationService<StatisticData> {
  private static final String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";

  private static StatisticDataService instance;

  public static StatisticDataService getInstance() {
    if (instance == null) {
      instance = new StatisticDataService();
    }
    return StatisticDataService.instance;
  }

  public AggregationResult getData(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
    StatisticData chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    }
    if (ChartType.TASKS_BY_EXPIRY.equals(chart.getType())) {
      return getDataExpiredTasks(chart);
    }
    return getData(chart);
  }

  private AggregationResult getData(StatisticData chart) {
    return chart.getIsCaseFilter() ? WorkflowStats.current().caze().aggregate(chart.getAggregates(), chart.getFilter())
        : WorkflowStats.current().task().aggregate(chart.getAggregates(), chart.getFilter());
  }

  private boolean isPermissionValid(StatisticData data) {
    return Optional.ofNullable(data.getPermissions()).orElse(new ArrayList<>()).stream()
        .anyMatch(permission -> Ivy.session().getSessionUser().has().role(permission));
  }

  @Override
  public Class<StatisticData> getType() {
    return StatisticData.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.STATISTIC_DATA.key;
  }

  private AggregationResult getDataExpiredTasks(StatisticData chart) {
    String nextWeek = getEndDateAtNextWeek();
    chart.setFilter("expiryTimestamp:<=" + nextWeek);
    AggregationResult data = getData(chart);
    Map<String, Object> convertObject = BusinessEntityConverter.convertValue(data.aggs().get(0), Map.class);
    List<Bucket> buckets = (List<Bucket>) convertObject.get("buckets");
    if (CollectionUtils.isNotEmpty(buckets)) {

    }
    return data;
  }

  private String getEndDateAtNextWeek() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    cal.add(Calendar.DATE, 14);
    Date date = cal.getTime();
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    String strDate = dateFormat.format(date);
    return strDate;
  }

  private String getEndDateAtThisWeek() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    cal.add(Calendar.DATE, 7);
    Date date = cal.getTime();
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    String strDate = dateFormat.format(date);
    return strDate;
  }
}
