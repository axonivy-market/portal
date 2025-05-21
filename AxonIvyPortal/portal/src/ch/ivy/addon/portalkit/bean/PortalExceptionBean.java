package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.util.IvyRuntimeException;

@ManagedBean
@SessionScoped
public class PortalExceptionBean implements Serializable {

  private static final long serialVersionUID = -248190912833727736L;
  private static final String PMV = "pmv";
  private static final String PROCESS_ELEMENT = "processElement";
  private String errorId;
  private String pmv;
  private String processElement;
  private ExceptionInfo exceptionInfo;

  public void buildExceptionDialog(ExceptionInfo exceptionInfo) {
    if (exceptionInfo != null) {
      this.exceptionInfo = exceptionInfo;

      if (exceptionInfo.getException() != null) {
        Throwable exception = this.exceptionInfo.getException();
        if (exception.getCause() instanceof BpmError) {
          BpmError bpmError = (BpmError) exception.getCause();
          this.errorId = bpmError.getId();
          this.processElement = getStringValue(bpmError.getThreadLocalValue(PROCESS_ELEMENT));
          this.pmv = getStringValue(bpmError.getThreadLocalValue(PMV));
        } else {
          IvyRuntimeException ivyRuntimeException = new IvyRuntimeException(exception);
          this.errorId = ivyRuntimeException.getId();
          this.processElement = getStringValue(ivyRuntimeException.getThreadLocalValue(PROCESS_ELEMENT));
          this.pmv = getStringValue(ivyRuntimeException.getThreadLocalValue(PMV));
        }
      }
    }
  }

  private String getStringValue(Object object) {
    return Objects.toString(object, "");
  }

  public ExceptionInfo getExceptionInfo() {
    return exceptionInfo;
  }

  public String getErrorId() {
    return errorId;
  }

  public String getPmv() {
    return StringUtils.replaceChars(pmv, "$", "/");
  }

  public String getProcessElement() {
    return processElement;
  }

  public boolean getIsShowErrorLogToConsole() {
    return GlobalSettingService.getInstance().findGlobalSettingValueAsBoolean(GlobalVariable.SHOW_ERROR_LOG_TO_CONSOLE);
  }

}
