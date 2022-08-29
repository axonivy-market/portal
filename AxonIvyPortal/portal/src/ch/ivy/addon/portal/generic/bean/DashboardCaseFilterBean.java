package ch.ivy.addon.portal.generic.bean;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.portal.components.dto.UserDTO;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.util.DashboardWidgetUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;

@ManagedBean
@ViewScoped
public class DashboardCaseFilterBean implements Serializable {

  private static final long serialVersionUID = -5375268615120879916L;
  private List<CaseState> states;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> creators;
  private List<SecurityMemberDTO> owners;
  private CaseDashboardWidget widget;

  @PostConstruct
  public void init() {
    this.states = Arrays.asList(CaseState.values()).stream()
        .sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString())).collect(Collectors.toList());
    this.creators = new ArrayList<>();
  }

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
  }

  public String formatName(SecurityMemberDTO memberDTO) {
    String memberName = EMPTY;
    if (memberDTO != null) {
      if (StringUtils.isBlank(memberDTO.getDisplayName())) {
        memberName = memberDTO.getName();
      } else {
        memberName = String.format("%s (%s)", memberDTO.getDisplayName(), memberDTO.getName());
      }
      return memberDTO.isEnabled() ? memberName : String.format("%s %s", Ivy.cms().co("/Labels/disabledUserPrefix"), memberName);
    }
    return memberName;
  }

  public List<SecurityMemberDTO> completeCreators(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE).stream()
        .filter(SecurityMemberDTO::isUser).collect(Collectors.toList());
  }

  public List<SecurityMemberDTO> completeOwners(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public String getUserFriendlyCaseState(CaseState state) {
    if (state == null) {
      return EMPTY;
    }
    String displayState = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state.toString());
    return StringUtils.isBlank(displayState) ? state.name() : displayState;
  }

  public boolean hasPredefinedFilter(CaseDashboardWidget widget) {
    return DashboardWidgetUtils.hasPredefinedFilter(widget);
  }

  public List<CaseState> getStates() {
    return states;
  }

  public void setStates(List<CaseState> states) {
    this.states = states;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<SecurityMemberDTO> getCreators() {
    return creators;
  }

  public void setCreators(List<SecurityMemberDTO> creators) {
    this.creators = creators;
  }

  public List<SecurityMemberDTO> getOwners() {
    return owners;
  }

  public void setOwners(List<SecurityMemberDTO> owners) {
    this.owners = owners;
  }
}
