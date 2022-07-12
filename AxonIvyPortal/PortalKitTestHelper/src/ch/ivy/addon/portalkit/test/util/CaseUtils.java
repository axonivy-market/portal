package ch.ivy.addon.portalkit.test.util;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.Advisor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;

@SuppressWarnings("restriction")
public class CaseUtils {
  public static void createSystemNote(final ICase wfCase, final String note) {
    final ISecurityContext s = ISecurityContext.current();
    var systemUserSession = Sudo.get(() -> s.sessions().systemUser());
    try {
      s.executeAs(new Callable<Void>() {

        @Override
        public Void call() throws Exception {
          wfCase.createNote(Ivy.session(), note);
          return null;
        }

      }, systemUserSession);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public static void deleteAllCases() throws Exception {
    Sudo.call(() -> {
      Advisor.getAdvisor().setTesting(true);
      Ivy.wf().deleteAllCases();
      Advisor.getAdvisor().setTesting(false);
      Ivy.log().warn("**********All cases are deleted*****************");
      return null;
    });
  }
}
