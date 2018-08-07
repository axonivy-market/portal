package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class TaskMenuBean implements Serializable {

  private static final long serialVersionUID = 5481218944095287656L;

  private PortalTaskMenuData portalTaskMenuData;
  private TreeNode rootNode;

  public TreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(TreeNode rootNode) {
    this.rootNode = rootNode;
  }

  public void initMenuBean(PortalTaskMenuData portalTaskMenuData) {
    this.portalTaskMenuData = portalTaskMenuData;
    rootNode = new DefaultTreeNode();
    addTasksMenuItem();
  }

  private void addTasksMenuItem() {
    List<CategoryData> allTaskCategories = portalTaskMenuData.getAllTaskCategories();
    DefaultTreeNode allTaskNode = buildAllTaskTree(allTaskCategories);
    allTaskNode.setParent(rootNode);
    rootNode.getChildren().add(allTaskNode);

    DefaultTreeNode personalTask = buildPersonalTaskTree(portalTaskMenuData.getMyTaskCategories());
    rootNode.getChildren().add(personalTask);

    DefaultTreeNode groupTask = buildGroupTaskTree(portalTaskMenuData.getGroupTaskCategories());
    rootNode.getChildren().add(groupTask);
  }

  private DefaultTreeNode buildAllTaskTree(List<CategoryData> allTaskCategories) {
    DefaultTreeNode allTaskNode = buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/AllTasks"),
        allTaskCategories, TreeNodeType.TASKS_ALL_TASKS);
    return allTaskNode;
  }

  public void onNodeExpand(NodeExpandEvent event) {
    for (TreeNode childNode : event.getTreeNode().getChildren()) {
      childNode.setExpanded(false);
    }
  }

  public void onNodeCollapse(NodeCollapseEvent event) {

  }

  public void onNodeSelect(NodeSelectEvent event) {

  }

  private DefaultTreeNode buildPersonalTaskTree(List<CategoryData> myTaskCategories) {
    List<Object> params = Arrays.asList(Ivy.session().getSessionUserName());
    String personalTaskNodeName = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/PersonalTasks", params);
    DefaultTreeNode myTaskNode = buildTaskTree(personalTaskNodeName, myTaskCategories, TreeNodeType.TASKS_MY_TASKS);
    return myTaskNode;
  }

  private DefaultTreeNode buildGroupTaskTree(List<CategoryData> groupTaskCategories) {
    DefaultTreeNode groupTasks = buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/GroupTasks"),
        groupTaskCategories, TreeNodeType.TASKS_GROUP_TASKS);

    return groupTasks;
  }

  private DefaultTreeNode buildTaskTree(String nodeDisplayName, List<CategoryData> categories, String firstCategory) {
    TaskNode taskMenuItem = new TaskNode();
    taskMenuItem.setValue(nodeDisplayName);
    taskMenuItem.setMenuKind(MenuKind.TASK);
    boolean isRootNodeAllTask = firstCategory.equals(TreeNodeType.TASKS_ALL_TASKS);
    taskMenuItem.setRootNodeAllTask(isRootNodeAllTask);
    taskMenuItem.setFirstCategoryNode(true);
    DefaultTreeNode taskNode = new DefaultTreeNode(taskMenuItem);
    taskNode.setType(firstCategory);
    taskNode.setExpanded(true);
    if (validCategory(categories)) {
      List<TreeNode> childrenNodes = TaskTreeUtils.convertTaskListToTree(categories, firstCategory, isRootNodeAllTask)
          .getChildren();
      taskNode.setChildren(childrenNodes);
    }
    return taskNode;
  }

  private boolean validCategory(List<CategoryData> categories) {
    return !Objects.isNull(categories) && !categories.isEmpty();
  }
}
