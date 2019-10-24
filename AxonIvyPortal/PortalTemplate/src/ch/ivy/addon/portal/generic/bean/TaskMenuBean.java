package ch.ivy.addon.portal.generic.bean;

import static ch.ivy.addon.portalkit.util.TaskTreeUtils.getLastCategoryFromCategoryPath;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
    DefaultTreeNode allTaskNode = buildAllTaskTree(portalTaskMenuData.getAllTaskCategories(), menuState);
    allTaskNode.setParent(rootNode);
    rootNode.getChildren().add(allTaskNode);

    DefaultTreeNode personalTask = buildPersonalTaskTree(portalTaskMenuData.getMyTaskCategories(), menuState);
    rootNode.getChildren().add(personalTask);

    DefaultTreeNode groupTask = buildGroupTaskTree(portalTaskMenuData.getGroupTaskCategories(), menuState);
    rootNode.getChildren().add(groupTask);

  }

  private DefaultTreeNode buildAllTaskTree(List<CategoryData> allTaskCategories, String menuState) {
    DefaultTreeNode allTaskNode =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/AllTasks"), allTaskCategories,
            TreeNodeType.TASKS_ALL_TASKS, menuState);
    return allTaskNode;
  }

  public void onNodeExpand(NodeExpandEvent event) {
    for (TreeNode childNode : event.getTreeNode().getChildren()) {
      childNode.setExpanded(false);
    }
  }

  public void onNodeCollapse(@SuppressWarnings("unused") NodeCollapseEvent event) {

  }

  public void onNodeSelect(@SuppressWarnings("unused") NodeSelectEvent event) {

  }

  private DefaultTreeNode buildPersonalTaskTree(List<CategoryData> myTaskCategories, String menuState) {
    List<Object> params = Arrays.asList(Ivy.session().getSessionUserName());
    String personalTaskNodeName = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/PersonalTasks", params);
    DefaultTreeNode myTaskNode =
        buildTaskTree(personalTaskNodeName, myTaskCategories, TreeNodeType.TASKS_MY_TASKS, menuState);
    return myTaskNode;
  }

  private DefaultTreeNode buildGroupTaskTree(List<CategoryData> groupTaskCategories, String menuState) {
    DefaultTreeNode groupTasks =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalTaskMenu/GroupTasks"), groupTaskCategories,
            TreeNodeType.TASKS_GROUP_TASKS, menuState);

    return groupTasks;
  }

  private DefaultTreeNode buildTaskTree(String nodeDisplayName, List<CategoryData> categories, String firstCategory,
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
    if (validCategory(categories)) {
      List<TreeNode> childrenNodes =
          TaskTreeUtils.convertTaskListToTree(categories, firstCategory, isRootNodeAllTask, menuState).getChildren();
      taskNode.setChildren(childrenNodes);
    }
    return taskNode;
  }

  private boolean validCategory(List<CategoryData> categories) {
    return !Objects.isNull(categories) && !categories.isEmpty();
  }
}
