package ch.ivy.addon.portalkit.ivydata.service;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.dto.IvyPasswordResultDTO;

@FunctionalInterface
public interface IPasswordService {

  /**
   * Change password of user
   * @param username
   * @param newPassword
   * @param apps
   * @return IvyLanguageResultDTO
   */
  IvyPasswordResultDTO changePassword(String username, String newPassword, List<String> apps);
}
