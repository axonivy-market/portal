package ch.ivy.addon.portalkit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.ColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivyteam.ivy.business.data.store.BusinessDataInfo;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public class CleanUpObsoletedUserDataService {

  public static final int PAGE_SIZE = 100;

  private List<UserDTO> currentUsers;
  private List<Long> userIds;
  private List<String> userNames;
  private Long applicationId;

  @SuppressWarnings("unchecked")
  public void cleanUpData() {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Ivy.log().info("*****Started clean up data");
    try {
      currentUsers = ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> {
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE).withStartName("findUsers")
            .call(Ivy.request().getApplication()).get("users", List.class);
      });

    } catch (Exception e) {
      Ivy.log().error("Can't get list of users", e);
      return;
    }
    applicationId = Ivy.request().getApplication().getId();
    userNames = currentUsers.stream().map(UserDTO::getName).distinct().collect(Collectors.toList());
    userIds = currentUsers.stream().map(UserDTO::getId).collect(Collectors.toList());

    Ivy.log().info("Started clean up User favourite process");
    cleanUpUserFavouriteProcess();
    Ivy.log().info("Started clean up User task filter");
    cleanUpUserTaskFilter();
    Ivy.log().info("Started clean up User case filter");
    cleanUpUserCaseFilter();
    Ivy.log().info("Started clean up User task ColumnsConfig");
    cleanUpUserTaskColumnsConfigData();
    Ivy.log().info("Started clean up User case ColumnsConfig");
    cleanUpUserCaseColumnsConfigData();
    Ivy.log().info("Started clean up User statistic chart");
    cleanUpUserStatisticChartData();
    stopWatch.stop();
    Ivy.log().info("*****Clean up data finished in {0}ms", stopWatch.getTime());
  }

  private void cleanUpUserFavouriteProcess() {
    UserProcessService userProcessService = new UserProcessService();
    List<UserProcess> userProcesses = userProcessService.findAll();
    CollectionUtils.emptyIfNull(userProcesses).stream()
        .filter(userProcess -> StringUtils.isBlank(userProcess.getUserName()) || (!checkIfUserBelongToCurrentApp(userProcess.getUserName()) && !userNames.contains(userProcess.getUserName())))
        .forEach(userProcess -> {
          Ivy.log().info("Delete UserFavourite {0} of user {1}", userProcess.getProcessName(), userProcess.getUserName());
          userProcessService.delete(userProcess);
        });
  }

  private boolean checkIfUserBelongToCurrentApp(String userName) {
    try {
      return SecurityManagerFactory.getSecurityManager().executeAsSystem(() -> {
        return Ivy.request().getApplication().getSecurityContext().findUser(userName) != null;
      });
    } catch (Exception e) {
      Ivy.log().error("Check user belongs to current app failed ", e);
      return false;
    }
  }

  private void cleanUpUserTaskFilter() {
    AbstractFilterService<TaskFilterData> taskFilterService = new TaskFilterService();
    List<BusinessDataInfo<TaskFilterData>> allPrivateTaskFilters = new ArrayList<>();
    long totalCount = taskFilterService.getTotalFilterCounts();
    if (totalCount > PAGE_SIZE) {
      int currentIndex = 0;
      do {
        allPrivateTaskFilters = taskFilterService.getAllPrivateFilters(currentIndex, PAGE_SIZE);
        cleanUpTaskFiltersData(allPrivateTaskFilters);

        currentIndex += PAGE_SIZE;
      } while (currentIndex <= totalCount);

    } else {
      allPrivateTaskFilters = taskFilterService.getAllPrivateFilters(0, (int) totalCount);
      cleanUpTaskFiltersData(allPrivateTaskFilters);
    }
  }

  private void cleanUpUserCaseFilter() {
    AbstractFilterService<CaseFilterData> caseFilterService = new CaseFilterService();
    List<BusinessDataInfo<CaseFilterData>> allPrivateCaseFilters = new ArrayList<>();
    long totalCount = caseFilterService.getTotalFilterCounts();
    if (totalCount > PAGE_SIZE) {
      int currentIndex = 0;
      do {
        allPrivateCaseFilters = caseFilterService.getAllPrivateFilters(currentIndex, PAGE_SIZE);
        cleanUpCaseFiltersData(allPrivateCaseFilters);

        currentIndex += PAGE_SIZE;
      } while (currentIndex <= totalCount);
    } else {
      allPrivateCaseFilters = caseFilterService.getAllPrivateFilters(0, (int) totalCount);
      cleanUpCaseFiltersData(allPrivateCaseFilters);
    }
  }

  private void cleanUpCaseFiltersData(List<BusinessDataInfo<CaseFilterData>> allPrivateCaseFilters) {
    CollectionUtils.emptyIfNull(allPrivateCaseFilters).stream()
        .filter(caseFilter -> caseFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdByJsonObject(caseFilter.getRawValue())))
        .forEach(caseFilter -> {
          Ivy.log().info("Delete private case filter id {0} of user {1}", caseFilter.getId(),
              caseFilter.getCreatedByUserName());
          Ivy.repo().deleteById(caseFilter.getId());
        });
  }

  private void cleanUpTaskFiltersData(List<BusinessDataInfo<TaskFilterData>> allPrivateTaskFilters) {
    CollectionUtils.emptyIfNull(allPrivateTaskFilters).stream()
        .filter(taskFilter -> taskFilter.getCreatedByAppId() == applicationId && !userIds.contains(getUserIdByJsonObject(taskFilter.getRawValue())))
        .forEach(taskFilter -> {
          Ivy.log().info("Delete private task filter id {0} of user {1}", taskFilter.getId(),
              taskFilter.getCreatedByUserName());
          Ivy.repo().deleteById(taskFilter.getId());
        });
  }

  @SuppressWarnings("unchecked")
  private Long getUserIdByJsonObject(String jsonObject) {
    Long userId = null;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = null;
    try {
      jsonMap = mapper.readValue(jsonObject, Map.class);
      if (jsonMap != null) {
        userId = Long.valueOf(jsonMap.get("userId").toString());
      }
    } catch (IOException e) {
      Ivy.log().error("Cannot parse RawValue of FilterData: " + e);
    }
    return userId;
  }

  private void cleanUpUserTaskColumnsConfigData() {
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfiguration> allColumnConfigs = new ArrayList<>();
    long totalCounts = service.getTotalCounts(applicationId);
    if (totalCounts > PAGE_SIZE) {
      int currentIndex = 0;
      do {
        allColumnConfigs = service.getAllConfiguration(applicationId, 0, PAGE_SIZE);
        cleanRepoColumnConfig(userIds, allColumnConfigs);
        currentIndex += PAGE_SIZE;

      } while (currentIndex <= totalCounts);
    } else {
      allColumnConfigs = service.getAllConfiguration(applicationId, 0, (int) totalCounts);
      cleanRepoColumnConfig(userIds, allColumnConfigs);
    }
  }

  private void cleanUpUserCaseColumnsConfigData() {
    CaseColumnsConfigurationService service = new CaseColumnsConfigurationService();
    List<CaseColumnsConfiguration> allColumnConfigs = new ArrayList<>();
    cleanRepoColumnConfig(userIds, allColumnConfigs);
    long totalCounts = service.getTotalCounts(applicationId);
    if (totalCounts > PAGE_SIZE) {
      int currentIndex = 0;
      do {
        allColumnConfigs = service.getAllConfiguration(applicationId, 0, PAGE_SIZE);
        cleanRepoColumnConfig(userIds, allColumnConfigs);
        currentIndex += PAGE_SIZE;

      } while (currentIndex <= totalCounts);
    } else {
      allColumnConfigs = service.getAllConfiguration(applicationId, 0, (int) totalCounts);
      cleanRepoColumnConfig(userIds, allColumnConfigs);
    }
  }

  private void cleanRepoColumnConfig(List<Long> userIds, List<? extends ColumnsConfiguration> allColumnConfigs) {
    CollectionUtils.emptyIfNull(allColumnConfigs).stream()
        .filter(columnConfig -> !userIds.contains(columnConfig.getUserId()))
        .forEach(columnConfig -> {
          Ivy.log().info("Delete ColumnConfigID {0} of userID {1}", columnConfig.getProcessModelId(), columnConfig.getUserId());
          Ivy.repo().delete(columnConfig);
          }
        );
  }

  private void cleanUpUserStatisticChartData() {
    StatisticService statisticService = new StatisticService();
    List<StatisticChart> allStatisticCharts = new ArrayList<>();
    long totalCounts = statisticService.getTotalCounts();
    if (totalCounts > PAGE_SIZE) {
      int currentIndex = 0;
      do {
        allStatisticCharts = statisticService.findAllStatisticCharts(currentIndex, PAGE_SIZE);
        removeStatisticCharts(statisticService, allStatisticCharts);

        currentIndex += PAGE_SIZE;
      } while (currentIndex <= totalCounts);

    } else {
      allStatisticCharts = statisticService.findAllStatisticCharts(0, (int) totalCounts);
      removeStatisticCharts(statisticService, allStatisticCharts);
    }
  }

  private void removeStatisticCharts(StatisticService statisticService, List<StatisticChart> allStatisticCharts) {
    CollectionUtils.emptyIfNull(allStatisticCharts).stream()
        .filter(chart -> Ivy.repo().getInfo(chart).getCreatedByAppId() == applicationId && !userIds.contains(chart.getUserId()))
        .forEach(chart -> {
          Ivy.log().info("Delete Statistic chart {0} of userID {1}", chart.getName(), chart.getUserId());
          statisticService.delete(chart.getId());
        });
  }

}
