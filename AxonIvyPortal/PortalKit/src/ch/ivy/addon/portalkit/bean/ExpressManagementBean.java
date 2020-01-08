package ch.ivy.addon.portalkit.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.addon.portalkit.service.ProcessStartCollector;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExpressManagementBean implements Serializable {

  private static final long serialVersionUID = -6072339110563610370L;

  private List<UserDTO> activeUserList;

  @PostConstruct
  public void initManagement() {
    activeUserList = findAllActiveUser();
  }

  private List<UserDTO> findAllActiveUser() {
    IvySecurityResultDTO ivySecurityResultDTO = SecurityService.newInstance().findUsers(Ivy.request().getApplication());
    return ivySecurityResultDTO.getUsers();
  }
  
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
    if (activeUserList != null && !activeUserList.isEmpty()) {
      UserDTO activeUser = activeUserList.stream().filter(user -> user.getMemberName().equalsIgnoreCase(activatorName))
          .findFirst().get();
      displayName = activeUser.getDisplayName();
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

  public List<UserDTO> getActiveUserList() {
    return activeUserList;
  }

  public void setActiveUserList(List<UserDTO> activeUserList) {
    this.activeUserList = activeUserList;
  }

}
