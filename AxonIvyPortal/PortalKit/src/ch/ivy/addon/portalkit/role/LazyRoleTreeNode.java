package ch.ivy.addon.portalkit.role;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portalkit.enums.GlobalVariable;
import ch.ivy.addon.portalkit.service.GlobalSettingService;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LazyRoleTreeNode extends DefaultTreeNode<RoleHolder> {

  private static final long serialVersionUID = -6573776217368741636L;
  private static final String DEFAULT_ROLE_TYPE = "role";
  private static final String DUMMY_NODE = "dummy";
  private IRole role;
  private boolean childrenFetched;
  private boolean isMember;
  private boolean showMember;

  public LazyRoleTreeNode(IRole role, boolean isMember, TreeNode<RoleHolder> node) {
    super(new RoleHolder(role, isMember), node);
    this.role = role;
    this.isMember = isMember;
    setType(DEFAULT_ROLE_TYPE);
  }

  public LazyRoleTreeNode(IRole role, boolean canManage, boolean isMember, boolean showMember, TreeNode<RoleHolder> node) {
    super(new RoleHolder(role, canManage, isMember), node);
    this.role = role;
    this.isMember = isMember;
    this.showMember = showMember;
    setType(DEFAULT_ROLE_TYPE);
  }

  @Override
  public List<TreeNode<RoleHolder>> getChildren() {
    ensureChildrenFetched();
    return super.getChildren();
  }

  @Override
  public int getChildCount() {
    ensureChildrenFetched();
    return super.getChildCount();
  }

  @Override
  public boolean isLeaf() {
    ensureChildrenFetched();
    return super.isLeaf();
  }

  private void ensureChildrenFetched() {
    if (childrenFetched) {
      return;
    }
    if (isMember) {
      return;
    }
    if (role == null) {
      return;
    }
    childrenFetched = true;
    var rolesLeft = addRolesToTree(role.getChildRoles(), false);
    if (showMember) {
      addRolesToTree(role.getRoleMembers(), true);
    }
    if (rolesLeft > 0) {
      generateDummyNode(this, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/SearchToSeeMoreRole", rolesLeft);
    }
  }

  private int addRolesToTree(List<IRole> rolesToAdd, boolean isMember) {
    super.getChildren().addAll(rolesToAdd.stream().limit(getRoleChildrenLimitConfig())
        .map(child -> new LazyRoleTreeNode(child, isMember, this)).collect(Collectors.toList()));
    return rolesToAdd.size() - getRoleChildrenLimitConfig();
  }

  public static DefaultTreeNode<RoleHolder> generateDummyNode(TreeNode<RoleHolder> treeNode, String cmsURL, Object... cmsParams) {
    return new DefaultTreeNode<>(DUMMY_NODE,
        new RoleHolder(null, Ivy.cms().co(cmsURL, Arrays.asList(cmsParams)), false), treeNode);
  }

  public static TreeNode<RoleHolder> filterTreeData(String filter, Set<RoleHolder> roles) {
    TreeNode<RoleHolder> filteredTreeNode = new DefaultTreeNode<>(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/FilteredRoles"), null, null);
    if (CollectionUtils.isEmpty(roles)) {
      return filteredTreeNode;
    }
    var foundRoles = roles.stream().filter(role -> StringUtils.containsIgnoreCase(role.getName(), filter)).collect(Collectors.toList());
    if (CollectionUtils.isNotEmpty(foundRoles)) {
      var topRole = new RoleHolder(Ivy.wf().getSecurityContext().roles().topLevel());
      var everyBody = new DefaultTreeNode<>(DEFAULT_ROLE_TYPE, topRole, filteredTreeNode);
      everyBody.setExpanded(true);

      foundRoles.stream().limit(getRoleChildrenLimitConfig()).filter(Objects::nonNull)
          .sorted((RoleHolder r1, RoleHolder r2) -> r1.getName().compareTo(r2.getName()))
          .forEach(role -> {
            var limit = 0;
            var roleTreeNode = new DefaultTreeNode<>(role);
            createTreeForRole(filteredTreeNode, roleTreeNode, role, limit);
          });
    }
    if (foundRoles.size() >= getRoleChildrenLimitConfig()) {
      generateDummyNode(filteredTreeNode, "/ch.ivy.addon.portalkit.ui.jsf/components/RoleManagement/Messages/MaxSearchLimit", getRoleChildrenLimitConfig());
    }
    return filteredTreeNode;
  }

  private static void createTreeForRole(TreeNode<RoleHolder> treeNode, TreeNode<RoleHolder> roleNode,  RoleHolder role, int limit) {
    if (isNull(treeNode) || isNull(role)) {
      return;
    }
    TreeNode<RoleHolder> rootTreeNode = treeNode;
    if (nonNull(role.getParent()) && limit <= getParentRoleLimitConfig()) {
      limit++;
      var parentRoleHolder = new RoleHolder(role.getParent());
      var existedParentRole = findTreeNodeByRoleName(treeNode, parentRoleHolder);
      if (isNull(existedParentRole)) {
        existedParentRole = new DefaultTreeNode<>(parentRoleHolder);
        existedParentRole.setExpanded(true);
        createTreeForRole(treeNode, existedParentRole, new RoleHolder(parentRoleHolder.getParent()), limit);
      }
      rootTreeNode = existedParentRole;
    }
    updateExistedParentDataToRootTree(role, roleNode, rootTreeNode);
  }

  private static void updateExistedParentDataToRootTree(RoleHolder role, TreeNode<RoleHolder> roleNode,
      TreeNode<RoleHolder> parentNode) {
    var foundRole = findTreeNodeByRoleName(parentNode, role);
    if (isNull(foundRole)) {
      foundRole = createANewChildNode(role, parentNode);
    }
    var foundSelectedChild = findTreeNodeByRoleName(foundRole, roleNode.getData());
    if (isNull(foundSelectedChild)) {
      foundRole.getChildren().add(roleNode);
    }
  }

  private static DefaultTreeNode<RoleHolder> createANewChildNode(RoleHolder role, TreeNode<RoleHolder> parentRole) {
    var newNode = new DefaultTreeNode<>(DEFAULT_ROLE_TYPE, role, parentRole);
    newNode.setExpanded(true);
    return newNode;
  }

  private static TreeNode<RoleHolder> findTreeNodeByRoleName(TreeNode<RoleHolder> treeNode, RoleHolder role) {
    if (isNull(treeNode) || isNull(role)) {
      return null;
    }
    var roleName = role.getName();
    if (equalsRoleName(treeNode.getData(), roleName)) {
      return treeNode;
    }
    var parentNode = isNull(treeNode.getParent()) ? null : treeNode.getParent().getData();
    if (equalsRoleName(parentNode, roleName)) {
      return treeNode.getParent();
    }
    if (CollectionUtils.isNotEmpty(treeNode.getChildren())) {
      TreeNode<RoleHolder> foundNode = null;
      for (var node : treeNode.getChildren()) {
        if (foundNode != null) {
          break;
        }
        if (equalsRoleName(node.getData(), roleName)) {
          foundNode = node;
        } else if (CollectionUtils.isNotEmpty(node.getChildren())) {
          foundNode = findTreeNodeByRoleName(node, role);
        }
      }
      return foundNode;
    }
    return null;
  }

  private static boolean equalsRoleName(RoleHolder role, String roleName) {
    var treeDataName = isNull(role) ? null : role.getName();
    return StringUtils.equals(treeDataName, roleName);
  }

  private static int getRoleChildrenLimitConfig() {
    return Integer.valueOf(GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.ROLE_DIRECT_CHILDREN_LIMIT)).intValue();
  }

  private static int getParentRoleLimitConfig() {
    return Integer.valueOf(GlobalSettingService.getInstance().findGlobalSettingValue(GlobalVariable.ROLE_PARENT_LIMIT)).intValue();
  }
}
