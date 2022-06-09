package ch.ivy.addon.portalkit.test.util;

import ch.ivyteam.ivy.security.ISecurityContextRepository;

public class AbsenceUtils {

  public static void cleanAllAbsencesAndSubstitutes() {
    for (var securityContext : ISecurityContextRepository.instance().all()) {
      for (var user : securityContext.users().paged()) {
        for (var absence : user.getAbsences()) {
          user.deleteAbsence(absence);
        }
        for (var substitute : user.getSubstitutes()) {
          user.deleteSubstitute(substitute);
        }
      }
    }
  }
}
