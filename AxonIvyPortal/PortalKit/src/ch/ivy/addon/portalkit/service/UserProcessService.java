package ch.ivy.addon.portalkit.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.enums.PortalLibrary;
import ch.ivy.addon.portalkit.persistence.dao.UserProcessDao;
import ch.ivy.addon.portalkit.persistence.domain.UserProcess;

public class UserProcessService extends AbstractService<UserProcess> {

  public UserProcessService() {
    super(new UserProcessDao());
  }

  @Override
  protected UserProcessDao getDao() {
    return (UserProcessDao) super.getDao();
  }

  public List<UserProcess> findByUserIdInCurrentApplication(Long userId) {
    return getDao().findByUserIdInCurrentApplication(userId);
  }

  public List<UserProcess> createDefaultUserProcesses(List<UserProcess> defaultUserProcesses, Long userId) {
    defaultUserProcesses.stream().forEach(defaultUserProcess -> defaultUserProcess.setUserId(userId));
    return saveAll(defaultUserProcesses);
  }

  public List<UserProcess> getDefaultUserProcessesFromSubProcess() {
    Map<String, Object> response =
        IvyAdapterService.startSubProcess("createDefaultUserProcesses()", null,
            Arrays.asList(PortalLibrary.PORTAL_KIT.getValue()));
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

  public List<UserProcess> findByApplicationId(long applicationId) {
    return getDao().findByApplicationID(applicationId);
  }

}
