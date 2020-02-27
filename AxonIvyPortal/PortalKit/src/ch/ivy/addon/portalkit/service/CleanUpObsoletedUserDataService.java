package ch.ivy.addon.portalkit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.server.ServerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CleanUpObsoletedUserDataService {

  private final static String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";
  private static final int OFFSET_SIZE = 100;
  private List<RemoteUser> currentUsers;
  private List<Long> userIds;
  private long applicationId;
  private boolean isError;

  public void cleanUpData() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Ivy.log().info("***** CLEAN_UP_JOB: Started Clean up data");
    try {
      currentUsers =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteUser>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<RemoteUser> call() throws Exception {
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsersByApplication")
                  .call(Ivy.request().getApplication().getName())
                  .get("users", List.class);
            }
          });

    } catch (Exception e) {
      Ivy.log().error("CLEAN_UP_JOB: Can't get list of users", e);
      return ;
    }
    applicationId = Ivy.request().getApplication().getId();
    userIds = currentUsers.stream().map(RemoteUser::getId).collect(Collectors.toList());
    
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserFavourite process");
    cleanUpUserFavouriteProcess();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserTask filter");
    cleanUpUserTaskFilter();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserCase filter");
    cleanUpUserCaseFilter();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserTask ColumnsConfig");
    cleanUpUserTaskColumnsConfigData();
    Ivy.log().info("CLEAN_UP_JOB: Started clean up UserStatistic chart");
    cleanUpUserStatisticChartData();
    stopWatch.stop();
    Ivy.log().info("***** CLEAN_UP_JOB: Finished Clean up data in {0}ms", stopWatch.getTime());
  }

  private void cleanUpUserFavouriteProcess() {
    UserProcessService userProcessService = new UserProcessService();
    List<UserProcess> userProcesses = userProcessService.findAll();

    List<String> userNameOnAllApps = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(userProcesses)) {
      userNameOnAllApps.addAll(collectAllUserOnServer());
    }
    // In case we got any errors during Collect data phase
    // Then skip clean up User Favorites data
    if (isError) {
      return;
    }

    CollectionUtils.emptyIfNull(userProcesses).stream()
        .filter(userProcess -> StringUtils.isBlank(userProcess.getUserName()) || (!userNameOnAllApps.contains(userProcess.getUserName())))
        .forEach(userProcess -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete UserFavourite {0} of user {1}", userProcess.getProcessName(), userProcess.getUserName());
          userProcessService.delete(userProcess);
        });
  }

  private List<String> collectAllUserOnServer() {
    Ivy.log().info("CLEAN_UP_JOB: Started collecting users overall apps");
    List<String> userNameOnAllApps = new ArrayList<>();
    List<IApplication> allApplications = collectPortalAppOnServer();
    CollectionUtils.emptyIfNull(allApplications).forEach(app -> {
      userNameOnAllApps.addAll(findUsersByApp(app));
    });
    Ivy.log().info("CLEAN_UP_JOB: Finished collecting users overall apps");
    return userNameOnAllApps;
  }

  private List<String> findUsersByApp(IApplication app) {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        return app.getSecurityContext().getUsers().stream().map(IUser::getName).collect(Collectors.toList());
      });
    } catch (Exception e) {
      Ivy.log().error("CLEAN_UP_JOB: cleanUpUserFavouriteProcess - Cannot get users data", e);
      isError = true;
    }
    return new ArrayList<>();
  }

  private List<IApplication> collectPortalAppOnServer() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        return ServerFactory.getServer().getApplicationConfigurationManager().getApplicationsSortedByName(false);
      });
    } catch (Exception e) {
      Ivy.log().error("CLEAN_UP_JOB: cleanUpUserFavouriteProcess - Cannot get application info", e);
      isError = true;
    }
    return new ArrayList<>();
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

  private void cleanUpUserTaskColumnsConfigData() {
    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfigurationData> columnConfigs = new ArrayList<>();
    long totalCount = service.getTotalTaskConfigCountByAppId(serverId, applicationId);
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        List<TaskColumnsConfigurationData> queryResult = service.getTaskConfigurationWithOffset(serverId, applicationId, currentIndex, OFFSET_SIZE);
        columnConfigs.addAll(checkIfTaskColumnConfigIsObsoleted(queryResult));
        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      columnConfigs = service.getTaskConfigurationWithOffset(serverId, applicationId, 0, (int) totalCount);
      columnConfigs.addAll(checkIfTaskColumnConfigIsObsoleted(columnConfigs));
    }
    
    cleanRepoColumnConfig(columnConfigs);
  }
  
  private List<TaskColumnsConfigurationData> checkIfTaskColumnConfigIsObsoleted(List<TaskColumnsConfigurationData> columnConfigs) {
    return CollectionUtils.emptyIfNull(columnConfigs).stream()
        .filter(columnConfig -> !userIds.contains(columnConfig.getUserId()))
        .collect(Collectors.toList());
  }

  private void cleanRepoColumnConfig(List<TaskColumnsConfigurationData> columnConfigs) {
    CollectionUtils.emptyIfNull(columnConfigs).forEach(columnConfig -> {
        Ivy.log().info("CLEAN_UP_JOB: Delete TaskColumnConfigID {0} of userID {1}", columnConfig.getTaskColumnsConfigDataId(), columnConfig.getUserId());
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
  
  private void removeStatisticCharts(StatisticService statisticService, List<String> allStatisticCharts) {
    CollectionUtils.emptyIfNull(allStatisticCharts).forEach(chartId -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete Statistic chart with chartId {0}", chartId);
          statisticService.delete(chartId);
        });
  }

}
