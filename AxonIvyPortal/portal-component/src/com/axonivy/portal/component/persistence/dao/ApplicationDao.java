package com.axonivy.portal.component.persistence.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.component.persistence.domain.Application;
import ch.ivyteam.ivy.application.IApplication;

public class ApplicationDao extends AbstractDao<Application> {

  public ApplicationDao() {
    super();
  }

  public ApplicationDao(IApplication application) {
    super(application);
  }

  @ExecuteAsSystem
  public List<Application> findAllThirdPartyApplications() {
    return findAll().stream()
        .filter(application -> application.getServerId() == null)
        .collect(Collectors.toList());
  }

  @ExecuteAsSystem
  public List<Application> findAllIvyApplications() {
    return findAll().stream()
        .filter(application -> application.getServerId() != null)
        .collect(Collectors.toList());
  }

  @ExecuteAsSystem
  public Application findByDisplayNameAndName(String displayName, String applicationName) {
    return findAll().stream()
        .filter(application -> StringUtils.equals(application.getDisplayName(), displayName) && StringUtils.equals(application.getName(), applicationName))
        .findFirst()
        .orElse(null);
  }

  @ExecuteAsSystem
  public Application findByName(String name) {
    return findAll().stream()
        .filter(application -> StringUtils.equals(application.getName(), name))
        .findFirst()
        .orElse(null);
  }

  @ExecuteAsSystem
  public List<Application> findByNames(List<String> names) {
    return findAll().stream()
        .filter(application -> names.contains(application.getName()))
        .collect(Collectors.toList());
  }
}
