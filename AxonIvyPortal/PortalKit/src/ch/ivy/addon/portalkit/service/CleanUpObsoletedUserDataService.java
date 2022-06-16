package ch.ivy.addon.portalkit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.ColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.taskfilter.impl.TaskFilterData;
import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivy.addon.portalkit.util.UserUtils;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;

@SuppressWarnings("deprecation")
public class CleanUpObsoletedUserDataService {

  private static final int OFFSET_SIZE = 100;
  private List<UserDTO> currentUsers;
  private List<Long> userIds;
  private Long applicationId;

  @SuppressWarnings("unchecked")
  public void cleanUpData() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Ivy.log().info("***** CLEAN_UP_JOB: Started Clean up data");
    try {
      if (Ivy.request().getApplication().getName().equals(PortalConstants.PORTAL_APPLICATION_NAME)) {
        currentUsers =  IvyExecutor.executeAsSystem(() -> {
          return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
          .withStartName("findUsers")
          .withParam("application", Ivy.request().getApplication())
          .withParam("query", "")
          .withParam("startIndex", 0)
          .withParam("count", -1)
          .withParam("fromRoles", null)
          .withParam("excludedUsernames", null)
          .call()
          .get("users", List.class);
        });
      } else {
        currentUsers = UserUtils.findUsers("", 0, -1, null, null);
      }
    } catch (Exception e) {
      Ivy.log().error("CLEAN_UP_JOB: Can't get list of users", e);
      return;
    }
    applicationId = Ivy.request().getApplication().getId();
    userIds = currentUsers.stream().map(UserDTO::getId).collect(Collectors.toList());

    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserTask filter");
    cleanUpUserTaskFilter();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserCase filter");
    cleanUpUserCaseFilter();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserTask ColumnsConfig");
    cleanUpUserTaskColumnsConfigData();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserCase ColumnsConfig");
    cleanUpUserCaseColumnsConfigData();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserStatistic chart");
    cleanUpUserStatisticChartData();
    stopWatch.stop();
    Ivy.log().info("***** CLEAN_UP_JOB: Finished Clean up data  in {0} ms", stopWatch.getTime());
  }

  private void cleanUpUserTaskFilter() {
    AbstractFilterService<TaskFilterData> taskFilterService = new TaskFilterService();
    List<String> privateTaskFilterIds = new ArrayList<>();
    long totalCount = taskFilterService.getTotalPrivateFilterCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<BusinessDataInfo<TaskFilterData>> queryResult = taskFilterService.getPrivateFiltersWithOffset(currentIndex, OFFSET_SIZE);
        privateTaskFilterIds.addAll(checkIfTaskFilterIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      List<BusinessDataInfo<TaskFilterData>> queryResult = taskFilterService.getPrivateFiltersWithOffset(0, (int) totalCount);
      privateTaskFilterIds.addAll(checkIfTaskFilterIsObsoleted(queryResult));
    }
    
    cleanUpTaskFiltersData(privateTaskFilterIds);
  }

  private List<String> checkIfTaskFilterIsObsoleted(List<BusinessDataInfo<TaskFilterData>> privateTaskFilters) {
    return CollectionUtils.emptyIfNull(privateTaskFilters).stream()
        .filter(taskFilter -> taskFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdFromJsonString(taskFilter.getRawValue())))
        .map(BusinessDataInfo::getId).collect(Collectors.toList());
  }

  private void cleanUpUserCaseFilter() {
    AbstractFilterService<CaseFilterData> caseFilterService = new CaseFilterService();
    List<String> privateCaseFilterIds = new ArrayList<>();
    long totalCount = caseFilterService.getTotalPrivateFilterCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<BusinessDataInfo<CaseFilterData>> queryResult = caseFilterService.getPrivateFiltersWithOffset(currentIndex, OFFSET_SIZE);
        privateCaseFilterIds.addAll(checkIfCaseFilterIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      List<BusinessDataInfo<CaseFilterData>> queryResult = caseFilterService.getPrivateFiltersWithOffset(0, (int) totalCount);
      privateCaseFilterIds.addAll(checkIfCaseFilterIsObsoleted(queryResult));
    }
    
    cleanUpCaseFiltersData(privateCaseFilterIds);
  }

  private List<String> checkIfCaseFilterIsObsoleted(List<BusinessDataInfo<CaseFilterData>> privateCaseFilters) {
    return CollectionUtils.emptyIfNull(privateCaseFilters).stream()
        .filter(caseFilter -> caseFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdFromJsonString(caseFilter.getRawValue())))
        .map(BusinessDataInfo::getId).collect(Collectors.toList());
  }

  private void cleanUpCaseFiltersData(List<String> privateCaseFilterIds) {
    CollectionUtils.emptyIfNull(privateCaseFilterIds).forEach(caseFilterId -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete private case filter id {0}", caseFilterId);
          Ivy.repo().deleteById(caseFilterId);
        });
  }

  private void cleanUpTaskFiltersData(List<String> privateTaskFilterIds) {
    CollectionUtils.emptyIfNull(privateTaskFilterIds).forEach(taskFilterId -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete private task filter id {0}", taskFilterId);
          Ivy.repo().deleteById(taskFilterId);
        });
  }

  @SuppressWarnings("unchecked")
  private Long getUserIdFromJsonString(String jsonString) {
    Long userId = null;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = null;
    try {
      jsonMap = mapper.readValue(jsonString, Map.class);
      if (jsonMap != null) {
        userId = Long.valueOf(jsonMap.get("userId").toString());
      }
    } catch (IOException e) {
      Ivy.log().error("CLEAN_UP_JOB: Cannot parse RawValue of FilterData: " + e);
    }
    return userId;
  }

  private void cleanUpUserTaskColumnsConfigData() {
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfiguration> columnConfigs = new ArrayList<>();
    long totalCount = service.getTotalTaskConfigCountByAppId(applicationId);
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<TaskColumnsConfiguration> queryResult = service.getTaskConfigurationWithOffset(applicationId, currentIndex, OFFSET_SIZE);
        columnConfigs.addAll(checkIfTaskColumnConfigIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      columnConfigs = service.getTaskConfigurationWithOffset(applicationId, 0, (int) totalCount);
      columnConfigs.addAll(checkIfTaskColumnConfigIsObsoleted(columnConfigs));
    }
    
    cleanRepoColumnConfig(columnConfigs);
  }

  private List<TaskColumnsConfiguration> checkIfTaskColumnConfigIsObsoleted(List<TaskColumnsConfiguration> columnConfigs) {
    return CollectionUtils.emptyIfNull(columnConfigs).stream()
        .filter(columnConfig -> !userIds.contains(columnConfig.getUserId()))
        .collect(Collectors.toList());
  }

  private void cleanUpUserCaseColumnsConfigData() {
    CaseColumnsConfigurationService service = new CaseColumnsConfigurationService();
    List<CaseColumnsConfiguration> columnConfigs = new ArrayList<>();
    long totalCount = service.getTotalCaseConfigCountByAppId(applicationId);
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<CaseColumnsConfiguration> queryResult = service.getCaseConfigurationWithOffset(applicationId, currentIndex, OFFSET_SIZE);
        columnConfigs.addAll(checkIfCaseColumnConfigIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      columnConfigs = service.getCaseConfigurationWithOffset(applicationId, 0, (int) totalCount);
      columnConfigs.addAll(checkIfCaseColumnConfigIsObsoleted(columnConfigs));
    }
    
    cleanRepoColumnConfig(columnConfigs);
  }

  private List<CaseColumnsConfiguration> checkIfCaseColumnConfigIsObsoleted(List<CaseColumnsConfiguration> columnConfigs) {
    return CollectionUtils.emptyIfNull(columnConfigs).stream()
        .filter(columnConfig -> !userIds.contains(columnConfig.getUserId()))
        .collect(Collectors.toList());
  }

  private void cleanRepoColumnConfig(List<? extends ColumnsConfiguration> columnConfigs) {
    CollectionUtils.emptyIfNull(columnConfigs).forEach(columnConfig -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete ColumnConfig of userID {0} in applicationID {1}", columnConfig.getUserId(), columnConfig.getApplicationId());
          Ivy.repo().delete(columnConfig);
        }
      );
  }

  private void cleanUpUserStatisticChartData() {
    StatisticService statisticService = new StatisticService();
    List<String> statisticChartIds = new ArrayList<>();
    long totalCount = statisticService.getTotalStatisticCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<StatisticChart> queryResult = statisticService.findStatisticChartsWithOffset(currentIndex, OFFSET_SIZE);
        statisticChartIds.addAll(checkIfStatisticChartIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);

    } else {
      List<StatisticChart> queryResult = statisticService.findStatisticChartsWithOffset(0, (int) totalCount);
      statisticChartIds.addAll(checkIfStatisticChartIsObsoleted(queryResult));
    }

    removeStatisticCharts(statisticService, statisticChartIds);
  }

  private List<String> checkIfStatisticChartIsObsoleted(List<StatisticChart> statisticCharts) {
    return CollectionUtils.emptyIfNull(statisticCharts).stream()
    .filter(chart -> Ivy.repo().getInfo(chart).getCreatedByAppId() == applicationId && !userIds.contains(chart.getUserId()))
    .map(StatisticChart::getId).collect(Collectors.toList());
  }

  private void removeStatisticCharts(StatisticService statisticService, List<String> statisticCharts) {
    CollectionUtils.emptyIfNull(statisticCharts).forEach(chartId -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete Statistic chart with id {0}", chartId);
          statisticService.delete(chartId);
        });
  }

}
