package ch.ivyteam.ivy;

import ch.ivy.addon.portalkit.util.IvyExecutor;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class SystemDo {

  private SystemDo() {
  }

  public static void setCustomVarCharField1(final ITask task, final String val) {
    IvyExecutor.executeAsSystem(() -> {
      task.setCustomVarCharField1(val);
      return null;
    });
  }

  public static void setCaseName(final ICase icase, final String val) {
    IvyExecutor.executeAsSystem(() -> {
      icase.setName(val);
      return null;
    });
  }

  public static void setCaseDescription(final ICase icase, final String val) {
    IvyExecutor.executeAsSystem(() -> {
      icase.setDescription(val);
      return null;
    });
  }

  @SuppressWarnings("deprecation")
  public static void setProcess(final ICase icase, final String code, final String val) {
    IvyExecutor.executeAsSystem(() -> {
      icase.setProcess(code, val);
      return null;
    });
  }
  
  public static void attachToBusinessCase(final ICase icase, final Long businessCaseId) {
    IvyExecutor.executeAsSystem(() -> {
        icase.attachToBusinessCase(businessCaseId);
        return null;
	    });
  }
}
