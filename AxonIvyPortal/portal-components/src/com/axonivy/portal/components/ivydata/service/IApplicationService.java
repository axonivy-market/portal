package com.axonivy.portal.components.ivydata.service;

import java.util.List;

import com.axonivy.portal.components.ivydata.bo.IvyApplication;

public interface IApplicationService {

  List<IvyApplication> findAll();
  List<IvyApplication> findBy(List<String> names);
  List<IvyApplication> findActiveAll();
  List<IvyApplication> findActiveAllInvolvedUser(String username);
}
