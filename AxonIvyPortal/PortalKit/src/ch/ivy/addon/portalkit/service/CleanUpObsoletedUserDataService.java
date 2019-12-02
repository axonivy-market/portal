package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.bo.CaseColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.ColumnsConfiguration;
import ch.ivy.addon.portalkit.bo.TaskColumnsConfiguration;
import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivy.addon.portalkit.statistics.StatisticChart;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.server.ServerFactory;

public class CleanUpObsoletedUserDataService {

  List<IUser> currentUsers;

  @SuppressWarnings("unchecked")
  public void cleanUpData() {
    try {
      currentUsers = ServerFactory.getServer().getSecurityManager().executeAsSystem(()-> {
        return SubProcessCall.withPath(PortalConstants.SECURITY_SERVICE_CALLABLE)
            .withStartName("findUsers")
            .call(Ivy.request().getApplication()).get("users", List.class);
      });

    } catch (Exception e) {
      Ivy.log().error("Can't get list of users", e);
      return;
    }
    cleanUpUserFavouriteProcess();
    cleanUpUserTaskColumnsConfigData();
    cleanUpUserCaseColumnsConfigData();
    cleanUpUserStatisticChartData();
  }

  private void cleanUpUserFavouriteProcess() {
    List<String> userNames = currentUsers.stream().map(IUser::getName).distinct().collect(Collectors.toList());
    UserProcessService userProcessService = new UserProcessService();
    List<UserProcess> userProcesses = userProcessService.findAll();
    List<UserProcess> obsoletedUserProcess = userProcesses
      .stream()
      .filter(userProcess -> StringUtils.isBlank(userProcess.getUserName()) || (checkIfUserBelongToCurrentApp(userProcess.getUserName()) && !userNames.contains(userProcess.getUserName())))
      .collect(Collectors.toList());
    userProcessService.deleteAll(obsoletedUserProcess);
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

  private void cleanUpUserTaskColumnsConfigData() {
    List<Long> userIds = currentUsers.stream().map(IUser::getId).collect(Collectors.toList());
    TaskColumnsConfigurationService service = new TaskColumnsConfigurationService();
    List<TaskColumnsConfiguration> allColumnConfigs = service.getAllConfiguration(Ivy.request().getApplication().getId());
    cleanRepoColumnConfig(userIds, allColumnConfigs);
  }
  
  private void cleanUpUserCaseColumnsConfigData() {
    List<Long> userIds = currentUsers.stream().map(IUser::getId).collect(Collectors.toList());
    CaseColumnsConfigurationService service = new CaseColumnsConfigurationService();
    List<CaseColumnsConfiguration> allColumnConfigs = service.getAllConfiguration(Ivy.request().getApplication().getId());
    cleanRepoColumnConfig(userIds, allColumnConfigs);
  }

  private void cleanRepoColumnConfig(List<Long> userIds, List<? extends ColumnsConfiguration> allColumnConfigs) {
    CollectionUtils.emptyIfNull(allColumnConfigs)
      .stream()
      .filter(columnConfig -> !userIds.contains(columnConfig.getUserId()))
      .forEach(columnConfig -> Ivy.repo().delete(columnConfig));
  }

  private void cleanUpUserStatisticChartData() {
    List<Long> userIds = currentUsers.stream().map(IUser::getId).collect(Collectors.toList());
    StatisticService statisticService = new StatisticService();
    List<StatisticChart> allStatisticCharts = statisticService.findAllStatisticCharts();
    
    Long applicationId = Ivy.request().getApplication().getId();
    allStatisticCharts.stream()
      .filter(chart -> Ivy.repo().getInfo(chart).getCreatedByAppId() == applicationId && !userIds.contains(chart.getUserId()))
      .forEach(chart -> statisticService.delete(chart.getId()));
  }
}
