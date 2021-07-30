package portal.migration.version91.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import portal.migration.version91.persistence.domain.Application;

public class ApplicationDao extends AbstractDao<Application> {

  public ApplicationDao() {
    super();
  }

  public List<Application> findAllThirdPartyApplications() {
    return findAll().stream() .filter(application -> application.getServerId() == null) .collect(Collectors.toList());
  }
}
