package ch.ivy.addon.portalkit.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.persistence.dao.UserProcessDao;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

public class UserProcessService extends AbstractService<UserProcess> {

  public UserProcessService() {
    super(UserProcessDao.class);
  }

  @Override
  protected UserProcessDao getDao() {
    return (UserProcessDao) super.getDao();
  }

  public List<UserProcess> findByUserName(String userName) {
    return getDao().findByUserName(userName);
  }

  public List<UserProcess> findDeletedDefaultProcessesByUserName(String userName) {
    return getDao().findDeletedDefaultProcessesByUserName(userName);
  }

  public List<UserProcess> createDefaultUserProcesses(List<UserProcess> defaultUserProcesses, String userName) {
    defaultUserProcesses.stream().forEach(defaultUserProcess -> {
      defaultUserProcess.setUserName(userName);
    });
    return saveAll(defaultUserProcesses);
  }

  public List<UserProcess> getDefaultUserProcessesFromSubProcess() {
    Map<String, Object> response = IvyAdapterService.startSubProcess("createDefaultUserProcesses()", null);
    @SuppressWarnings("unchecked")
    List<UserProcess> defaultUserProcesses = (List<UserProcess>) response.get("defaultUserProcesses");
    return defaultUserProcesses;
  }

  public List<UserProcess> subtractByLink(List<UserProcess> processesToSubtractFrom,
      List<UserProcess> processesToSubtract) {
    return processesToSubtractFrom
        .stream()
        .filter(
            userProcess -> !processesToSubtract.stream().map(UserProcess::getLink).collect(Collectors.toSet())
                .contains(userProcess.getLink())).collect(Collectors.toList());
  }
}
