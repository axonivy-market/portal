package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import org.primefaces.application.exceptionhandler.ExceptionInfo;

import ch.ivyteam.ivy.bpm.error.BpmError;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.server.ServerFactory;
import ch.ivyteam.ivy.system.ISystemProperty;
import ch.ivyteam.util.IvyRuntimeException;

@ManagedBean
@ApplicationScoped
public class PortalExceptionBean implements Serializable {

  private static final long serialVersionUID = -1206213764780245939L;
  private static final String PMV = "pmv";
  private static final String PROCESS_ELEMENT = "processElement";
  private static final String SHOW_STACK_TRACE = "Errors.ShowDetailsToEndUser";
  private String uniqueId;

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

  public Object findExceptionValueByKey(ExceptionInfo exceptionInfo, String key) {
    Object value = EMPTY;
    this.uniqueId = null;
    if (exceptionInfo == null) {
      return value;
    }

    if (exceptionInfo.getException().getCause() instanceof BpmError) {
      BpmError bpmError = (BpmError) exceptionInfo.getException().getCause();
      this.uniqueId = bpmError.getId();
      value = bpmError.getThreadLocalValue(key);
    } else {
      IvyRuntimeException ivyRuntimeException = new IvyRuntimeException(exceptionInfo.getException());
      this.uniqueId = ivyRuntimeException.getId();
      value = ivyRuntimeException.getThreadLocalValue(key);
    }
    return value;
  }

  public Object getUniqueId(ExceptionInfo exceptionInfo) {
    findExceptionValueByKey(exceptionInfo, EMPTY);
    return this.uniqueId;
  }

  public Object getPmvName(ExceptionInfo exceptionInfo) {
    return findExceptionValueByKey(exceptionInfo, PMV);
  }

  public Object getProcessElementId(ExceptionInfo exceptionInfo) {
    return findExceptionValueByKey(exceptionInfo, PROCESS_ELEMENT);
  }
}
