package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.bo.IvyApplication;

public interface IApplicationService {

  List<IvyApplication> findAll();
  List<IvyApplication> findBy(List<String> names);
  List<IvyApplication> findActiveAll();
  List<IvyApplication> findActiveAllInvolvedUser(String username);
}
