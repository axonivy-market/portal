package ch.ivy.addon.portalkit.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import ch.ivy.addon.portalkit.persistence.domain.Application;

public class ApplicationDao extends AbstractDao<Application> {

  public ApplicationDao() {
    super();
  }

  @SuppressWarnings("removal")
  public List<Application> findAllThirdPartyApplications() {
    return findAll().stream() .filter(application -> application.getServerId() == null) .collect(Collectors.toList());
  }
}