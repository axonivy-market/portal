package ch.ivy.gawfs.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import ch.ivy.addon.portalkit.datamodel.ExpressProcessLazyDataModel;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.ivydata.dto.IvySecurityResultDTO;
import ch.ivy.addon.portalkit.ivydata.service.impl.SecurityService;
import ch.ivy.addon.portalkit.ivydata.utils.ServiceUtilities;
import ch.ivy.gawfs.enums.ProcessType;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IUser;

@ManagedBean
@ViewScoped
public class ExpressManagementBean implements Serializable {

  private static final long serialVersionUID = -6072339110563610370L;

  private List<UserDTO> activeUserList;
  private ExpressProcessLazyDataModel expressProcessLazyDataModel;

  @PostConstruct
  public void initHelper() {
    expressProcessLazyDataModel = new ExpressProcessLazyDataModel();
    activeUserList = findAllActiveUser();
  }

  private List<UserDTO> findAllActiveUser() {
    IvySecurityResultDTO ivySecurityResultDTO = SecurityService.newInstance().findUsers(Ivy.request().getApplication());
    return ivySecurityResultDTO.getUsers();
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

  public String getProcessTypeDescription(String processType) {
    for (ProcessType type : ProcessType.values()) {
      if (type.getValue().equalsIgnoreCase(processType)) {
        return type.getLabel();
      }
    }
    return processType;
  }

  public ExpressProcessLazyDataModel getExpressProcessLazyDataModel() {
    return expressProcessLazyDataModel;
  }

  public void setExpressProcessLazyDataModel(ExpressProcessLazyDataModel expressProcessLazyDataModel) {
    this.expressProcessLazyDataModel = expressProcessLazyDataModel;
  }

  public List<UserDTO> getActiveUserList() {
    return activeUserList;
  }

  public void setActiveUserList(List<UserDTO> activeUserList) {
    this.activeUserList = activeUserList;
  }

}
