package com.axonivy.portal.components.ivydata.service;

import java.util.List;

import com.axonivy.portal.components.ivydata.dto.IvySecurityResultDTO;

public interface ISecurityService {

  /**
   * Finds the users who have the given roles in security context
   * @param query
   * @param startIndex 0..n. The index of the first record is 0
   * @param count 0..n. Use -1 to return all beginning from the startIndex
   * @param fromRoles
   * @param excludedUsernames
   * @return {@link IvySecurityResultDTO}
   */
  IvySecurityResultDTO findUsers(String query, int startIndex, int count, List<String> fromRoles, List<String> excludedUsernames);
}
