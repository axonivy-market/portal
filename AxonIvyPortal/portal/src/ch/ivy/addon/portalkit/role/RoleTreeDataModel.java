package ch.ivy.addon.portalkit.role;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivyteam.ivy.security.ISecurityContext;

public class RoleTreeDataModel implements Serializable {

  private static final long serialVersionUID = -399521062928922551L;
  private static final String ROOT_NODE = "Tree";
  private TreeNode<RoleHolder> rootTreeNode;
  private TreeNode<RoleHolder> filteredTreeNode;
  private Set<RoleHolder> roles;
  private String filterKeyword;

  public RoleTreeDataModel() {}

  public void reloadTree() {
    this.filterKeyword = "";
    rootTreeNode = new DefaultTreeNode<RoleHolder>(ROOT_NODE, null, null);
    rootTreeNode.setExpanded(true);
    buildTree();
  }

  public TreeNode<RoleHolder> getTree() {
    if (StringUtils.isBlank(filterKeyword)) {
      return rootTreeNode;
    }
    return filteredTreeNode;
  }

  public void expandNode(NodeExpandEvent event) {
    event.getTreeNode().setExpanded(true);
  }

  public void collapseNode(NodeCollapseEvent event) {
    event.getTreeNode().setExpanded(false);
  }

  public void expandAllNodes() {
    expandAllNodes(rootTreeNode, true);
  }

  public void collapseAllNodes() {
    expandAllNodes(rootTreeNode, false);
  }

  private static <Role> void expandAllNodes(TreeNode<Role> treeNode, boolean expand) {
    var children = treeNode.getChildren();
    for (var child : children) {
      expandAllNodes(child, expand);
    }
    treeNode.setExpanded(expand);
  }

  public String getFilterKeyword() {
    return filterKeyword;
  }

  public void setFilterKeyword(String filterKeyword) {
    this.filterKeyword = filterKeyword;
    if (StringUtils.isBlank(filterKeyword)) {
      return;
    }
    this.filteredTreeNode = LazyRoleTreeNode.filterTreeData(filterKeyword, getRoles());
  }

  private void buildTree() {
    var node = new LazyRoleTreeNode(ISecurityContext.current().roles().topLevel(), false, false, true, rootTreeNode);
    node.setExpanded(true);
  }

  public Set<RoleHolder> getRoles() {
    if (CollectionUtils.isEmpty(roles)) { 
      roles = ISecurityContext.current().roles().all().stream().map(role -> new RoleHolder(role))
          .collect(Collectors.toSet());
    }
    return roles;
  }

  public TreeNode<RoleHolder> getRootTreeNode() {
    return rootTreeNode;
  }

  public void setRootTreeNode(TreeNode<RoleHolder> rootTreeNode) {
    this.rootTreeNode = rootTreeNode;
  }

  public TreeNode<RoleHolder> getFilteredTreeNode() {
    return filteredTreeNode;
  }

  public void setFilteredTreeNode(TreeNode<RoleHolder> filteredTreeNode) {
    this.filteredTreeNode = filteredTreeNode;
  }
}
