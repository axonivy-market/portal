package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

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
  public List<UserProcess> findByUserName(String userName) {
    return findAll().stream()
        .filter(userProcess -> StringUtils.equals(userProcess.getUserName(), userName) && !userProcess.isDefaultProcess())
        .collect(Collectors.toList());
  }

  @ExecuteAsSystem
  public List<UserProcess> findByApplicationID(long applicationId) {
    return findAll().stream().filter(userProcess -> userProcess.getApplicationId() == applicationId)
        .collect(Collectors.toList());
  }

}
