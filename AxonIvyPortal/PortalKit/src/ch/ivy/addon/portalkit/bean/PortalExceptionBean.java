package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;
import ch.ivyteam.util.IvyRuntimeException;

@ManagedBean
@ApplicationScoped
public class PortalExceptionBean implements Serializable {

  private static final long serialVersionUID = -248190912833727736L;
  private static final String PMV = "pmv";
  private static final String PROCESS_ELEMENT = "processElement";
  private static final String SHOW_STACK_TRACE = "Errors.ShowDetailsToEndUser";
  private String uniqueId;
  private String pmv;
  private String processElement;
  private ExceptionInfo exceptionInfo;

  public boolean getErrorDetailToEndUser() {
    try {
      return ServerFactory.getServer().getSecurityManager().executeAsSystem(() -> findShowErrorDetailSystemProperty());
    } catch (Exception e) {
      Ivy.log().error(e);
    }
    return true;
  }

  private boolean findShowErrorDetailSystemProperty() {
    ISystemProperty systemProp = ServerFactory.getServer()
        .getApplicationConfigurationManager().getSystemProp(SHOW_STACK_TRACE);
    return systemProp.getBooleanValue();
  }

  public void buildExceptionDialog(ExceptionInfo exceptionInfo) {
    if (exceptionInfo != null) {
      this.exceptionInfo = exceptionInfo;

      if (this.exceptionInfo.getException().getCause() instanceof BpmError) {
        BpmError bpmError = (BpmError) this.exceptionInfo.getException().getCause();
        this.uniqueId = bpmError.getId();
        this.processElement = bpmError.getThreadLocalValue(PROCESS_ELEMENT).toString();
        this.pmv = bpmError.getThreadLocalValue(PMV).toString();
      } else {
        IvyRuntimeException ivyRuntimeException = new IvyRuntimeException(this.exceptionInfo.getException());
        this.uniqueId = ivyRuntimeException.getId();
        this.processElement = ivyRuntimeException.getThreadLocalValue(PROCESS_ELEMENT).toString();
        this.pmv = ivyRuntimeException.getThreadLocalValue(PMV).toString();
      }
    }
  }

  public ExceptionInfo getExceptionInfo() {
    return exceptionInfo;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public String getPmv() {
    return StringUtils.replaceChars(pmv, "$", "/");
  }

  public String getProcessElement() {
    return processElement;
  }

}
