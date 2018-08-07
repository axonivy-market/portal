package ch.ivy.addon.portalkit.test.util;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.Advisor;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ICase;

@SuppressWarnings("restriction")
public class CaseUtils {
  public static void createSystemNote(final ICase wfCase, final String note) {
    final ISecurityContext s = Ivy.wf().getSecurityContext();
    try {
      s.executeAsSystemUser(new Callable<Void>() {

        @Override
        public Void call() throws Exception {
          wfCase.createNote(Ivy.session(), note);
          return null;
        }

      });
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  public static void deleteAllCases() throws Exception {
    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        Advisor.getAdvisor().setTesting(true);
        Ivy.wf().deleteAllCases();
        Advisor.getAdvisor().setTesting(false);
        Ivy.log().warn("**********All cases are deleted*****************");
        return null;
      }
    });
  }
}
