package portalmigration.version91.persistence.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch.ivyteam.ivy.environment.Ivy;
import portalmigration.version91.persistence.domain.UserProcess;

public class UserProcessDao extends AbstractDao<UserProcess> {

  public UserProcessDao() {
    super();
  }

  public List<UserProcess> findByUserIdInCurrentApplication(Long userId) {
    Long applicationId = Ivy.wf().getApplication().getId();
    return findAll()
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