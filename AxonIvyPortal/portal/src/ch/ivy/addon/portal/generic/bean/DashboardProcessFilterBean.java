package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class DashboardProcessFilterBean implements Serializable {
  private static final long serialVersionUID = 5024917112573235208L;

  public String getUserFriendlyProcessType(ProcessType type) {
    if (type == null) {
      return EMPTY;
    }
    String displayType = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/Enums/ProcessType/" + type.toString());
    return StringUtils.isBlank(displayType) ? type.name() : displayType;
  }

}
