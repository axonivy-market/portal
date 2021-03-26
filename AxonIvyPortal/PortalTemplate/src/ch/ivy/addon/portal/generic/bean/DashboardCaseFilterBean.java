package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.constant.PortalConstants;
import ch.ivy.addon.portalkit.dto.SecurityMemberDTO;
import ch.ivy.addon.portalkit.dto.UserDTO;
import ch.ivy.addon.portalkit.dto.dashboard.CaseDashboardWidget;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.SecurityMemberUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;

@ManagedBean
@ViewScoped
public class DashboardCaseFilterBean implements Serializable {

  private static final long serialVersionUID = -8452774999524582551L;
  private List<CaseState> states;
  private List<WorkflowPriority> priorities;
  private UserDTO selectedUser;
  private List<SecurityMemberDTO> creators;
  private CheckboxTreeNode categoryTree;
  private CheckboxTreeNode[] categoryNodes;
  private CaseDashboardWidget widget;

  @PostConstruct
  public void init() {
    this.states = Arrays.asList(CaseState.values()).stream()
        .sorted((s1, s2) -> StringUtils.compare(s1.toString(), s2.toString())).collect(Collectors.toList());
    this.priorities = Arrays.asList(WorkflowPriority.values());
    this.creators = new ArrayList<>();
  }

  public void preRender(CaseDashboardWidget widget) {
    this.widget = widget;
    this.widget.setInConfiguration(true);
    buildCategoryTree();
  }

  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes = categoryNodes;
    if (this.widget != null) {
      this.widget.setCategories(CategoryUtils.getCategoryPaths(categoryNodes));
    }
  }

  private void buildCategoryTree() {
    this.categoryTree = CaseTreeUtils.buildCaseCategoryCheckboxTreeRoot();
    this.categoryNodes = CategoryUtils.recoverSelectedCategories(this.categoryTree, this.widget.getCategories());
  }

  public String formatName(SecurityMemberDTO creator) {
    String creatorName = "";
    if (creator != null) {
      if (StringUtils.isBlank(creator.getDisplayName())) {
        creatorName = creator.getName();
      } else {
        creatorName = String.format("%s (%s)", creator.getDisplayName(), creator.getName());
      }
      return creator.isEnabled() ? creatorName : Ivy.cms().co("/Labels/disabledUserPrefix") + " " + creatorName;
    }
    return creatorName;
  }

  public List<SecurityMemberDTO> completeCreators(String query) {
    return SecurityMemberUtils.findSecurityMembers(query, 0, PortalConstants.MAX_USERS_IN_AUTOCOMPLETE);
  }

  public String getUserFriendlyCaseState(CaseState state) {
    if (state == null) {
      return StringUtils.EMPTY;
    }
    String displayState = Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseState/" + state.toString());
    return StringUtils.isBlank(displayState) ? state.name() : displayState;
  }

  public List<CaseState> getStates() {
    return states;
  }

  public void setStates(List<CaseState> states) {
    this.states = states;
  }

  public List<WorkflowPriority> getPriorities() {
    return priorities;
  }

  public void setPriorities(List<WorkflowPriority> priorities) {
    this.priorities = priorities;
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

  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }
}
