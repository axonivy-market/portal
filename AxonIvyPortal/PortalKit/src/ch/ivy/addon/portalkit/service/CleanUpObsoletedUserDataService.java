package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import ch.ivy.addon.portalkit.bo.RemoteUser;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfigurationData;
import ch.ivy.addon.portalkit.casefilter.CaseFilterData;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivy.addon.portalkit.taskfilter.TaskFilterData;
import ch.ivy.addon.portalkit.util.SecurityServiceUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.server.ServerFactory;

public class CleanUpObsoletedUserDataService {

  private final static String SECURITY_SERVICE_CALLABLE = "MultiPortal/SecurityService";
  List<RemoteUser> currentUsers;

  @SuppressWarnings("unchecked")
  public CleanUpObsoletedUserDataService() {
    try {
      currentUsers =
          ServerFactory.getServer().getSecurityManager().executeAsSystem(new Callable<List<RemoteUser>>() {
            @Override
            public List<RemoteUser> call() throws Exception {
              return SubProcessCall.withPath(SECURITY_SERVICE_CALLABLE).withStartName("findAllUsersByApplication")
                  .call(Ivy.request().getApplication().getName())
                  .get("users", List.class);
            }
          });

    } catch (Exception e) {
      Ivy.log().error("Can't get list of users", e);
      currentUsers = new ArrayList<>();
    }
  }

  public void cleanUpData() {
    if (CollectionUtils.isEmpty(currentUsers)) {
      return ;
    }
    cleanUpUserFavouriteProcess();
    cleanUpUserTaskCaseFilter();
    cleanUpUserTaskColumnsConfigData();
    cleanUpUserStatisticChartData();
  }

  private void cleanUpUserFavouriteProcess() {
    List<String> userNames = currentUsers.stream().map(RemoteUser::getUsername).distinct().collect(Collectors.toList());
    UserProcessService userProcessService = new UserProcessService();
    List<UserProcess> userProcesses = userProcessService.findAll();
    List<UserProcess> obsoletedUserProcess = new ArrayList<>();
    if (userProcesses != null) {
      for (UserProcess userProcess : userProcesses) {
        if (!userNames.contains(userProcess.getUserName())) {
          obsoletedUserProcess.add(userProcess);
        }
      }
    }
    userProcessService.deleteAll(obsoletedUserProcess);
  }

  private void cleanUpUserTaskCaseFilter() {
    List<Long> userIds = currentUsers.stream().map(RemoteUser::getId).collect(Collectors.toList());
    AbstractFilterService<TaskFilterData> taskFilterService = new TaskFilterService();
    List<TaskFilterData> allPrivateTaskFilters = taskFilterService.getAllPrivateFilters();
    if (allPrivateTaskFilters != null) {
      for (TaskFilterData privateTaskFilter : allPrivateTaskFilters) {
        if (!userIds.contains(privateTaskFilter.getUserId())) {
          taskFilterService.delete(privateTaskFilter.getId());
        }
      }
    }
    AbstractFilterService<CaseFilterData> caseFilterService = new CaseFilterService();
    List<CaseFilterData> allPrivateCaseFilters = caseFilterService.getAllPrivateFilters();
    if (allPrivateCaseFilters != null) {
      for (CaseFilterData privateCaseFilter : allPrivateCaseFilters) {
        if (!userIds.contains(privateCaseFilter.getUserId())) {
          taskFilterService.delete(privateCaseFilter.getId());
        }
      }
    }
  }

  private void cleanUpUserTaskColumnsConfigData() {
    Long serverId = SecurityServiceUtils.getServerIdFromSession();
    List<Long> userIds = currentUsers.stream().map(RemoteUser::getId).collect(Collectors.toList());
    Long applicationId = Ivy.request().getApplication().getId();
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfigurationData> allColumnConfigs = service.getAllConfiguration(serverId, applicationId);
    if (allColumnConfigs != null) {
      for (TaskColumnsConfigurationData columnConfig : allColumnConfigs) {
        if (!userIds.contains(columnConfig.getUserId())) {
          Ivy.repo().delete(columnConfig);
        }
      }
    }
  }

  private void cleanUpUserStatisticChartData() {
    List<Long> userIds = currentUsers.stream().map(RemoteUser::getId).collect(Collectors.toList());
    StatisticService statisticService = new StatisticService();
    List<StatisticChart> allStatisticCharts = statisticService.findAllStatisticCharts();
    if (allStatisticCharts != null) {
      for (StatisticChart chart : allStatisticCharts) {
        if (!userIds.contains(chart.getUserId())) {
          statisticService.delete(chart.getId());
        }
      }
    }
  }
}
