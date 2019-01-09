package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.util.TaskTreeUtils.getLastCategoryFromCategoryPath;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portal.generic.PortalTaskMenu.PortalTaskMenuData;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.CategoryTree;

@ManagedBean
@RequestScoped
public class TaskMenuBean implements Serializable {

  private static final long serialVersionUID = 5481218944095287656L;

  private TreeNode rootNode;
  private static final String TASK_MENU_DATA_BEAN = "#{taskMenuDataBean}";

  public TreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(TreeNode rootNode) {
    this.rootNode = rootNode;
  }

  @PostConstruct
  public void init() {
    FacesContext context = FacesContext.getCurrentInstance();
    TaskMenuDataBean taskMenuDataBean =
        context.getApplication().evaluateExpressionGet(context, TASK_MENU_DATA_BEAN, TaskMenuDataBean.class);
    PortalTaskMenuData portalTaskMenuData = taskMenuDataBean.getPortalTaskMenuData();
    if (portalTaskMenuData != null) {
      rootNode = new DefaultTreeNode();
      addTasksMenuItem(portalTaskMenuData, "");
    }
  }

  public void initMenuBean(PortalTaskMenuData portalTaskMenuData, String menuState) {
    rootNode = new DefaultTreeNode();
    addTasksMenuItem(portalTaskMenuData, menuState);
  }

  private void addTasksMenuItem(PortalTaskMenuData portalTaskMenuData, String menuState) {
    DefaultTreeNode allTaskNode = buildAllTaskTree(portalTaskMenuData.getAllTaskCategoryTree(), menuState);
    allTaskNode.setParent(rootNode);
    rootNode.getChildren().add(allTaskNode);

    DefaultTreeNode personalTask = buildPersonalTaskTree(portalTaskMenuData.getMyTaskCategoryTree(), menuState);
    rootNode.getChildren().add(personalTask);

    DefaultTreeNode groupTask = buildGroupTaskTree(portalTaskMenuData.getGroupTaskCategoryTree(), menuState);
    rootNode.getChildren().add(groupTask);

    if (PermissionUtils.checkReadAllTasksPermission()) {
      DefaultTreeNode unassignedTask =
          buildUnassignedTaskTree(portalTaskMenuData.getUnassignedTaskCategoryTree(), menuState);
      rootNode.getChildren().add(unassignedTask);
    }
  }

  private DefaultTreeNode buildAllTaskTree(CategoryTree allTaskCategoryTree, String menuState) {
    return buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/AllTasks"), allTaskCategoryTree,
        TreeNodeType.TASKS_ALL_TASKS, menuState);
  }

  public void onNodeExpand(NodeExpandEvent event) {
    for (TreeNode childNode : event.getTreeNode().getChildren()) {
      childNode.setExpanded(false);
    }
  }

  public void onNodeCollapse(@SuppressWarnings("unused") NodeCollapseEvent event) {
    // leave it empty
  }

  public void onNodeSelect(@SuppressWarnings("unused") NodeSelectEvent event) {
    // leave it empty
  }

  private DefaultTreeNode buildPersonalTaskTree(CategoryTree myTaskCategoryTree, String menuState) {
    List<Object> params = Arrays.asList(Ivy.session().getSessionUserName());
    String personalTaskNodeName = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/PersonalTasks", params);
    return buildTaskTree(personalTaskNodeName, myTaskCategoryTree, TreeNodeType.TASKS_MY_TASKS, menuState);
  }

  private DefaultTreeNode buildGroupTaskTree(CategoryTree groupTaskCategoryTree, String menuState) {
    return buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/GroupTasks"), groupTaskCategoryTree,
        TreeNodeType.TASKS_GROUP_TASKS, menuState);

  }

  private DefaultTreeNode buildUnassignedTaskTree(CategoryTree unassignedTaskCategoryTree, String menuState) {
    return buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/taskState/UNASSIGNED"), unassignedTaskCategoryTree,
        TreeNodeType.TASKS_UNASSIGNED_TASKS, menuState);
  }

  private DefaultTreeNode buildTaskTree(String nodeDisplayName, CategoryTree categoryTree, String firstCategory,
      String menuState) {
    TaskNode taskMenuItem = new TaskNode();
    taskMenuItem.setValue(nodeDisplayName);
    taskMenuItem.setMenuKind(MenuKind.TASK);
    boolean isRootNodeAllTask = firstCategory.equals(TreeNodeType.TASKS_ALL_TASKS);
    taskMenuItem.setRootNodeAllTask(isRootNodeAllTask);
    taskMenuItem.setFirstCategoryNode(true);
    DefaultTreeNode taskNode = new DefaultTreeNode(taskMenuItem);
    taskNode.setType(firstCategory);
    if (menuState.contains(firstCategory) && !getLastCategoryFromCategoryPath(menuState).contains(firstCategory)) {
      taskNode.setExpanded(true);
    } else {
      taskNode.setExpanded(false);
    }
    
    if (categoryTree != null) {
      TaskTreeUtils.convertToTreeNode(taskNode, categoryTree, isRootNodeAllTask, menuState);
      sortNode(taskNode);
    }
    return taskNode;
  }
  
  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      TaskNode firstNodeData = (TaskNode) firstNode.getData();
      TaskNode secondNodeData = (TaskNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }
}
