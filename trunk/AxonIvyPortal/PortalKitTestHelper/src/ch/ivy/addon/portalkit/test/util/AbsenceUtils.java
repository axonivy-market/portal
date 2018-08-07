package ch.ivy.addon.portalkit.test.util;

import java.util.List;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.IUserAbsence;
import ch.ivyteam.ivy.server.ServerFactory;

public class AbsenceUtils {
  public static void cleanAllAbsences() {
    List<IApplication> applications = ServerFactory.getServer().getApplicationConfigurationManager().getApplications();
    for (IApplication application : applications) {
      List<IUser> users = application.getSecurityContext().getUsers();
      for (IUser user : users) {
        List<IUserAbsence> absences = user.getAbsences();
        for (IUserAbsence absence : absences) {
          user.deleteAbsence(absence);
        }
      }
    }
  }
}
