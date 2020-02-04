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
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CleanUpObsoletedUserDataService {

  private final static String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";
  private static final int OFFSET_SIZE = 100;
  private List<RemoteUser> currentUsers;
  private List<String> userNames;
  private List<Long> userIds;
  private long applicationId;

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
    userNames = currentUsers.stream().map(RemoteUser::getUsername).distinct().collect(Collectors.toList());
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
    List<UserProcess> obsoletedUserProcess = new ArrayList<>();
    if (userProcesses != null) {
      for (UserProcess userProcess : userProcesses) {
        String processUserName = userProcess.getUserName();
        if (StringUtils.isBlank(processUserName) || (!checkIfUserBelongToCurrentApp(processUserName) && !userNames.contains(processUserName))) {
          Ivy.log().info("CLEAN_UP_JOB: UserFavourite {0} of user {1} will be deleted", userProcess.getProcessName(), userProcess.getUserName());
          obsoletedUserProcess.add(userProcess);
        }
      }
    }
    userProcessService.deleteAll(obsoletedUserProcess);
  }

  private boolean checkIfUserBelongToCurrentApp(String userName) {
    try {
          return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> {
            return Ivy.request().getApplication().getSecurityContext().findUser(userName) != null;
          });
    } catch (Exception e) {
          Ivy.log().error("CLEAN_UP_JOB: Check user belongs to current app failed ", e);
          return false;
    }
  }

  private void cleanUpUserTaskFilter() {
    AbstractFilterService<TaskFilterData> taskFilterService = new TaskFilterService();
    List<BusinessDataInfo<TaskFilterData>> privateTaskFilters;
    long totalCount = taskFilterService.getTotalPrivateFilterCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        privateTaskFilters = taskFilterService.getPrivateFiltersWithOffset(currentIndex, OFFSET_SIZE);
        cleanUpTaskFiltersData(privateTaskFilters);

        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);

    } else {
      privateTaskFilters = taskFilterService.getPrivateFiltersWithOffset(0, (int) totalCount);
      cleanUpTaskFiltersData(privateTaskFilters);
    }
  }

  private void cleanUpTaskFiltersData(List<BusinessDataInfo<TaskFilterData>> privateTaskFilters) {
    CollectionUtils.emptyIfNull(privateTaskFilters).stream()
        .filter(taskFilter -> taskFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdFromJsonString(taskFilter.getRawValue())))
        .forEach(taskFilter -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete private task filter id {0}", taskFilter.getId());
          Ivy.repo().deleteById(taskFilter.getId());
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
    List<BusinessDataInfo<CaseFilterData>> privateCaseFilters;
    long totalCount = caseFilterService.getTotalPrivateFilterCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        privateCaseFilters = caseFilterService.getPrivateFiltersWithOffset(currentIndex, OFFSET_SIZE);
        cleanUpCaseFiltersData(privateCaseFilters);

        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      privateCaseFilters = caseFilterService.getPrivateFiltersWithOffset(0, (int) totalCount);
      cleanUpCaseFiltersData(privateCaseFilters);
    }
  }

  private void cleanUpCaseFiltersData(List<BusinessDataInfo<CaseFilterData>> privateCaseFilters) {
    CollectionUtils.emptyIfNull(privateCaseFilters).stream()
        .filter(caseFilter -> caseFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdFromJsonString(caseFilter.getRawValue())))
        .forEach(caseFilter -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete private case filter id {0}", caseFilter.getId());
          Ivy.repo().deleteById(caseFilter.getId());
        });
  }

  private void cleanUpUserTaskColumnsConfigData() {
    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfigurationData> columnConfigs;
    long totalCount = service.getTotalTaskConfigCountByAppId(serverId, applicationId);
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        columnConfigs = service.getTaskConfigurationWithOffset(serverId, applicationId, currentIndex, OFFSET_SIZE);
        cleanRepoColumnConfig(columnConfigs);

        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);
    } else {
      columnConfigs = service.getTaskConfigurationWithOffset(serverId, applicationId, 0, (int) totalCount);
      cleanRepoColumnConfig(columnConfigs);
    }
  }

  private void cleanRepoColumnConfig(List<TaskColumnsConfigurationData> columnConfigs) {
    if (columnConfigs != null) {
      for (TaskColumnsConfigurationData columnConfig : columnConfigs) {
        if (!userIds.contains(columnConfig.getUserId())) {
          Ivy.log().info("CLEAN_UP_JOB: Delete TaskColumnConfigID {0} of userID {1}", columnConfig.getTaskColumnsConfigDataId(), columnConfig.getUserId());
          Ivy.repo().delete(columnConfig);
        }
      }
    }
  }

  private void cleanUpUserStatisticChartData() {
    StatisticService statisticService = new StatisticService();
    List<StatisticChart> allStatisticCharts;
    long totalCount = statisticService.getTotalStatisticCount();
    if (totalCount > OFFSET_SIZE) {
      int currentIndex = 0;
      do {
        allStatisticCharts = statisticService.findStatisticChartsWithOffset(currentIndex, OFFSET_SIZE);
        removeStatisticCharts(statisticService, allStatisticCharts);

        currentIndex += OFFSET_SIZE;
      } while (currentIndex < totalCount);

    } else {
      allStatisticCharts = statisticService.findStatisticChartsWithOffset(0, (int) totalCount);
      removeStatisticCharts(statisticService, allStatisticCharts);
    }
  }

  private void removeStatisticCharts(StatisticService statisticService, List<StatisticChart> allStatisticCharts) {
    CollectionUtils.emptyIfNull(allStatisticCharts).stream()
        .filter(chart -> Ivy.repo().getInfo(chart).getCreatedByAppId() == applicationId && !userIds.contains(chart.getUserId()))
        .forEach(chart -> {
          Ivy.log().info("CLEAN_UP_JOB: Delete Statistic chart {0} of userID {1}", chart.getName(), chart.getUserId());
          statisticService.delete(chart.getId());
        });
  }

}
