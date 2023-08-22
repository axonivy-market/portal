package com.axonivy.portal.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.NoPermissionException;
import javax.ws.rs.NotFoundException;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.bo.StatisticData;
import com.axonivy.portal.dto.statisticChart.StatisticDataDto;

import ch.ivy.addon.portalkit.enums.PortalVariable;
import ch.ivy.addon.portalkit.service.JsonConfigurationService;
import ch.ivy.addon.portalkit.statistics.StatisticsChartResponse;
import ch.ivyteam.ivy.elasticsearch.client.agg.AggregationResult;
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

  public List<StatisticData> findAllCharts() {
    return findAll();
  }

  public StatisticsChartResponse getData(StatisticDataDto payload) throws NotFoundException, NoPermissionException {
    StatisticData chart = findById(payload.getChartId());
    if (chart == null) {
      throw new NotFoundException(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/idNotFound",
          Arrays.asList(payload.getChartId())));
    }

    if (!isPermissionValid(chart)) {
      throw new NoPermissionException(
          Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/statistic/chart/exception/noPermission"));
    }
    if (chart.getFilter() != null && chart.getFilter().contains("plusDays")) {
      int numberOfDays = Integer.parseInt(StringUtils.substringAfterLast(chart.getFilter(), ":"));
      String filterBy = StringUtils.substringBefore(chart.getFilter(), ":");
      chart.setFilter(filterBy + ":<=" + plusDays(numberOfDays));
    }
    AggregationResult result = getData(chart);
    return new StatisticsChartResponse(result, chart);
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

  private String plusDays(int numberOfDays) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, numberOfDays);
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    Date date = cal.getTime();
    DateFormat dateFormat = new SimpleDateFormat(pattern);
    String strDate = dateFormat.format(date);
    return strDate;
  }

}
