package ch.ivyteam.ivy;

import java.util.concurrent.Callable;

import ch.ivyteam.ivy.security.SecurityManagerFactory;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class SystemDo {

  public static void setCustomVarCharField1(final ITask task, final String val) throws Exception {
    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        task.setCustomVarCharField1(val);
        return null;
      }
    });
  }

  public static void setCaseName(final ICase icase, final String val) throws Exception {
    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        icase.setName(val);
        return null;
      }
    });
  }

  public static void setCaseDescription(final ICase icase, final String val) throws Exception {
    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        icase.setDescription(val);
        return null;
      }
    });
  }

  @SuppressWarnings("deprecation")
  public static void setProcess(final ICase icase, final String code, final String val) throws Exception {
    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        icase.setProcess(code, val);
        return null;
      }
    });
  }
  
  public static void attachToBusinessCase(final ICase icase, final Long businessCaseId) throws Exception {
	    SecurityManagerFactory.getSecurityManager().executeAsSystem(new Callable<Void>() {
	      @Override
	      public Void call() throws Exception {
	        icase.attachToBusinessCase(businessCaseId);
	        return null;
	      }
	    });
  }
}
