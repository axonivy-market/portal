package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.application.IApplication;

public class UserProcessDao extends AbstractDao<UserProcess> {

  public UserProcessDao() {
    super();
  }

  public UserProcessDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  public List<UserProcess> findByUserId(Long userId) {
    return Optional.ofNullable(findAll()).orElse(new ArrayList<>()).stream()
        .filter(userProcess -> (Optional.ofNullable(userProcess.getUserId()).orElse(-1L).longValue() == userId.longValue() && !userProcess.isDefaultProcess()))
        .collect(Collectors.toList());
  }

  @ExecuteAsSystem
  public List<UserProcess> findByApplicationID(long applicationId) {
    return findAll().stream().filter(userProcess -> userProcess.getApplicationId() == applicationId)
        .collect(Collectors.toList());
  }

}
