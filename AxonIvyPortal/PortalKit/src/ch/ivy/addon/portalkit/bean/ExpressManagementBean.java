package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExpressManagementBean implements Serializable {

  private static final long serialVersionUID = -6072339110563610370L;

  private List<SecurityMemberDTO> activeMemberList;

  @PostConstruct
  public void initManagement() {
    activeMemberList = findAllActiveUser();
  }

  private List<SecurityMemberDTO> findAllActiveUser() {
    if (activeMemberList == null) {
      return SecurityMemberUtils.findAllSecurityMembers();
    }
    return activeMemberList;
  }

  /** Check Axon Express is deploy to current application
   * @return Express is present or not
   */
  public boolean isShowExpressManagementTab() {
    ProcessStartCollector collector = new ProcessStartCollector(Ivy.request().getApplication());
    return collector.findExpressCreationProcess() != null;
  }

  /**
   * Get a display name by activator name
   * 
   * @param activatorName
   * @return display name
   */
  public String getUserDisplayName(String activatorName) {
    if (StringUtils.isBlank(activatorName)) {
      return Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
    }

    String displayName = activatorName;
    if (activeMemberList != null && !activeMemberList.isEmpty()) {
      Optional<SecurityMemberDTO> activeUser = activeMemberList.stream().filter(user -> user.getMemberName().equalsIgnoreCase(activatorName))
          .findFirst();
      if (activeUser.isPresent()) {
        displayName = activeUser.get().getDisplayName();
      } else {
        displayName = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/common/notAvailable");
      }
    } else {
      try {
        IUser user = ServiceUtilities.findUser(activatorName, Ivy.request().getApplication());
        displayName = user.getFullName();
      } catch (Exception ex) {
        Ivy.log().error("Error in getting users within app {0}", ex, Ivy.request().getApplication());
      }
    }
    return displayName;
  }

}
