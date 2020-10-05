package ch.ivy.addon.portalkit.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.persistence.domain.UserProcess;
import ch.ivyteam.ivy.environment.Ivy;

public class UserProcessDao extends AbstractDao<UserProcess> {

  public UserProcessDao() {
    super();
  }

  public List<UserProcess> findByUserIdInCurrentApplication(Long userId) {
    Long applicationId = Ivy.wf().getApplication().getId();
    return Optional.ofNullable(findAll())
        .orElse(new ArrayList<>())
        .stream()
        .filter(userProcess -> (
          Optional.ofNullable(userProcess.getUserId()).orElse(-1L).longValue() == userId.longValue() 
          && Optional.ofNullable(userProcess.getApplicationId()).orElse(-1L).longValue() == applicationId.longValue()
          && !userProcess.isDefaultProcess()))
        .collect(Collectors.toList());
  }

  public List<UserProcess> findByApplicationID(long applicationId) {
    return findAll()
        .stream()
        .filter(userProcess -> userProcess.getApplicationId() == applicationId)
        .collect(Collectors.toList());
  }
}