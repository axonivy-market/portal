package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.StringUtils;

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
}
