package com.axonivy.portal.component.ivydata.service;

import java.util.List;

import com.axonivy.portal.component.ivydata.bo.IvyApplication;

public interface IApplicationService {

  List<IvyApplication> findAll();
  List<IvyApplication> findBy(List<String> names);
  List<IvyApplication> findActiveAll();
  /**
   * @deprecated don't use it, user belongs to security context, not application. Always returns empty list 
   * @param username
   * @return empty list
   */
  @Deprecated(forRemoval = true, since = "9.4")
  List<IvyApplication> findActiveAllInvolvedUser(String username);
}
