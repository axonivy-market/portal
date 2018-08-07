package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portal.generic.CustomPortalLink;
import ch.ivy.addon.portal.generic.PortalConfig;
import ch.ivy.addon.portal.generic.CustomLinkGenerator.CustomLinkGeneratorData;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.environment.Ivy;

@ManagedBean
@ViewScoped
public class MainMenuBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final String PORTAL_SEARCH_DELAY_IN_MILLISECONDS = "PortalSearchDelayInMilliseconds";

  private TreeNode rootNode;
  private TreeNode selectedNode;

  private boolean hasReadAllTasksPermisson;
  private boolean hasReadAllCasesPermisson;

  public TreeNode generateMainMenu(CustomLinkGeneratorData data) {
    rootNode = new DefaultTreeNode();
    addDefaultMenuItems(data);

    for (int i = 0; i < data.getLinkList().size(); i++) {
      CustomPortalLink customPortalLink = data.getLinkList().get(i);
      MainMenuNode menuItem = new MainMenuNode();
      menuItem.setValue(customPortalLink.getDisplayName());
      menuItem.setIcon(customPortalLink.getIcon());
      menuItem.setUrl(customPortalLink.getUrl());
      menuItem.setMenuKind(MenuKind.CUSTOM);
      rootNode.getChildren().add(new DefaultTreeNode(menuItem));
    }
    return rootNode;
  }

  private void addDefaultMenuItems(CustomLinkGeneratorData data) {
    PortalConfig portalConfig = data.getPortalConfig();
    addNewProcessMenuItem(portalConfig);
    addTasksMenuItem(data);
    addCasesMenuItem(data);
    addDashboardMenuItem(portalConfig);
  }

  private void addCasesMenuItem(CustomLinkGeneratorData data) {
    PortalConfig portalConfig = data.getPortalConfig();
    if (portalConfig.getHideShowCaseMenuItem() == null || portalConfig.getHideShowCaseMenuItem()) {
      return;
    }

    hasReadAllCasesPermisson = data.getHasReadAllCasesPermission();
    DefaultTreeNode caseNode = hasReadAllCasesPermisson ? buildAdministratorCaseTree() : buildPersonalCaseTree();
    rootNode.getChildren().add(caseNode);
  }

  private DefaultTreeNode buildPersonalCaseTree() {
    DefaultTreeNode caseNode = buildPersonalCaseMenuItem();
    return caseNode;
  }

  private DefaultTreeNode buildAdministratorCaseTree() {
    DefaultTreeNode personalCaseNode =
        buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myCases"),
            TreeNodeType.CASES_MY_CASES);
    DefaultTreeNode allCaseNode =
        buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/allCases"),
            TreeNodeType.CASES_ALL_CASES);
    DefaultTreeNode taskNode = buildAllCasesMenuItem();
    taskNode.setChildren(Arrays.asList(personalCaseNode, allCaseNode));
    return taskNode;
  }

  private DefaultTreeNode buildCaseTree(String nodeDisplayName, String firstCategory) {
    CaseNode caseMenuItem = new CaseNode();
    caseMenuItem.setValue(nodeDisplayName);
    caseMenuItem.setMenuKind(MenuKind.CASE);
    boolean isRootNodeAllCase = firstCategory.equals(TreeNodeType.CASES_ALL_CASES);
    caseMenuItem.setRootNodeAllCase(isRootNodeAllCase);
    DefaultTreeNode caseNode = new DefaultTreeNode(caseMenuItem);
    caseNode.setType(firstCategory);
    caseNode.setExpanded(true);
    return caseNode;
  }

  private DefaultTreeNode buildPersonalCaseMenuItem() {
    return buildCasesMenuItem(false);
  }

  private DefaultTreeNode buildAllCasesMenuItem() {
    return buildCasesMenuItem(true);
  }

  private DefaultTreeNode buildCasesMenuItem(boolean isAllCaseNode) {
    CaseNode casesMenuItem = new CaseNode();
    casesMenuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases"));
    casesMenuItem.setMenuKind(MenuKind.CASE);
    casesMenuItem.setRootNodeAllCase(isAllCaseNode);
    casesMenuItem.setIcon("fa fa-list");
    DefaultTreeNode caseNode = new DefaultTreeNode(casesMenuItem);
    caseNode.setType(TreeNodeType.CASES);
    caseNode.setExpanded(true);
    return caseNode;
  }

  private void addTasksMenuItem(CustomLinkGeneratorData data) {
    PortalConfig portalConfig = data.getPortalConfig();
    List<CategoryData> myTaskCategories = data.getMyTaskCategories();
    List<CategoryData> allTaskCategories = data.getAllTaskCategories();
    if (portalConfig.getHideShowTaskMenuItem() == null || portalConfig.getHideShowTaskMenuItem()) {
      return;
    }

    hasReadAllTasksPermisson = data.getHasReadAllTasksPermisson();
    DefaultTreeNode taskNode =
        hasReadAllTasksPermisson ? buildAdministratorTaskTree(myTaskCategories, allTaskCategories)
            : buildPersonalTaskTree(myTaskCategories);
    rootNode.getChildren().add(taskNode);
  }

  private DefaultTreeNode buildPersonalTaskTree(List<CategoryData> myTaskCategories) {
    DefaultTreeNode taskNode = buildTaskMenuItem(myTaskCategories, false);
    List<TreeNode> childrenNodes =
        TaskTreeUtils.convertTaskListToTree(myTaskCategories, TreeNodeType.TASKS, false).getChildren();
    taskNode.setChildren(childrenNodes);
    return taskNode;
  }

  private DefaultTreeNode buildAdministratorTaskTree(List<CategoryData> myTaskCategories, List<CategoryData> allTaskCategories) {
    DefaultTreeNode personalTaskNode =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myTasks"), myTaskCategories,
            TreeNodeType.TASKS_MY_TASKS);
    DefaultTreeNode allTaskNode =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AllTasks/title"), allTaskCategories,
            TreeNodeType.TASKS_ALL_TASKS);
    DefaultTreeNode taskNode = buildTaskMenuItem(allTaskCategories, true);
    taskNode.setChildren(Arrays.asList(personalTaskNode, allTaskNode));
    return taskNode;
  }

  private DefaultTreeNode buildTaskMenuItem(List<CategoryData> categories, boolean isAllTaskNode) {
    TaskNode tasksMenuItem = new TaskNode();
    tasksMenuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/tasks"));
    tasksMenuItem.setMenuKind(MenuKind.TASK);
    tasksMenuItem.setRootNodeAllTask(isAllTaskNode);
    tasksMenuItem.setIcon("fa fa-inbox");
    DefaultTreeNode taskNode = new DefaultTreeNode(tasksMenuItem);
    taskNode.setType(TreeNodeType.TASKS);
    taskNode.setExpanded(true);
    return taskNode;
  }

  private DefaultTreeNode buildTaskTree(String nodeDisplayName, List<CategoryData> categories, String firstCategory) {
    TaskNode taskMenuItem = new TaskNode();
    taskMenuItem.setValue(nodeDisplayName);
    taskMenuItem.setMenuKind(MenuKind.TASK);
    boolean isRootNodeAllTask = firstCategory.equals(TreeNodeType.TASKS_ALL_TASKS);
    taskMenuItem.setRootNodeAllTask(isRootNodeAllTask);
    DefaultTreeNode taskNode = new DefaultTreeNode(taskMenuItem);
    taskNode.setType(firstCategory);
    List<TreeNode> childrenNodes =
        TaskTreeUtils.convertTaskListToTree(categories, firstCategory, isRootNodeAllTask).getChildren();
    taskNode.setChildren(childrenNodes);
    taskNode.setExpanded(true);
    return taskNode;
  }

  private void addDashboardMenuItem(PortalConfig portalConfig) {
    if (portalConfig.getHideDashboardMenuItem() == null || portalConfig.getHideDashboardMenuItem()) {
      return;
    }

    MainMenuNode dashboardMenuItem = new MainMenuNode();
    dashboardMenuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/dashboard"));
    dashboardMenuItem.setMenuKind(MenuKind.DASHBOARD);
    dashboardMenuItem.setIcon("fa fa-calendar");
    DefaultTreeNode dashboardMenuNode = new DefaultTreeNode(dashboardMenuItem);
    rootNode.getChildren().add(dashboardMenuNode);
  }

  private void addNewProcessMenuItem(PortalConfig portalConfig) {
    if (portalConfig.getHideCreateNewMenuItem() == null || portalConfig.getHideCreateNewMenuItem()) {
      return;
    }

    MainMenuNode newProcessMenuItem = new MainMenuNode();
    newProcessMenuItem.setValue(Ivy.cms().co("/Project/LeftMenu/process"));
    newProcessMenuItem.setMenuKind(MenuKind.PROCESS);
    newProcessMenuItem.setIcon("fa fa-magic fa-magic-o");
    DefaultTreeNode processMenuNode = new DefaultTreeNode(newProcessMenuItem);
    rootNode.getChildren().add(processMenuNode);
  }

  public Integer getSearchDelayTime() {
    String delaySearchTime = Ivy.var().get(PORTAL_SEARCH_DELAY_IN_MILLISECONDS);
    try {
      return Integer.valueOf(delaySearchTime);
    } catch (NumberFormatException e) {
      String message = String.format("Value of global variable %s  is not number", PORTAL_SEARCH_DELAY_IN_MILLISECONDS);
      Ivy.log().error(message, e);
    }
    return 0;
  }

  public void onNodeExpand(NodeExpandEvent event) {
    for (TreeNode childNode : event.getTreeNode().getChildren()) {
      childNode.setExpanded(false);
    }
  }

  public TreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(TreeNode rootNode) {
    this.rootNode = rootNode;
  }

  public TreeNode getSelectedNode() {
    return selectedNode;
  }
}
