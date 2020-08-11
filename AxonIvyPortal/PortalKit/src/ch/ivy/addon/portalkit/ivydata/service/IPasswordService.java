package ch.ivy.addon.portalkit.ivydata.service;

@FunctionalInterface
public interface IPasswordService {

  /**
   * Change password of user
   * @param newPassword
   * @return boolean;
   */
  boolean changePassword(String newPassword);
}
