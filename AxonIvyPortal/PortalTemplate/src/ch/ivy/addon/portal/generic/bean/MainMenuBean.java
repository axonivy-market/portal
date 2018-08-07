package ch.ivy.addon.portal.generic.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.bo.MainMenuNode;
import ch.ivy.addon.portalkit.bo.RemoteCase;
import ch.ivy.addon.portalkit.bo.RemoteTask;
import ch.ivy.addon.portalkit.bo.TaskNode;
import ch.ivy.addon.portalkit.comparator.TaskPriorityComparator;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.service.FilterService;
import ch.ivy.addon.portalkit.util.TaskTreeUtils;
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
    DefaultTreeNode caseNode =
        hasReadAllCasesPermisson ? buildAdministratorCaseTree(data.getPersonalCases(), data.getAllCases())
            : buildPersonalCaseTree(data.getPersonalCases());
    rootNode.getChildren().add(caseNode);
  }

  private DefaultTreeNode buildPersonalCaseTree(List<RemoteCase> personalCases) {
    DefaultTreeNode caseNode = buildPersonalCaseMenuItem(personalCases);
    return caseNode;
  }

  private DefaultTreeNode buildAdministratorCaseTree(List<RemoteCase> personalCases, List<RemoteCase> allCases) {
    DefaultTreeNode personalCaseNode =
        buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myCases"), personalCases,
            "Cases/My_Cases");
    DefaultTreeNode allCaseNode =
        buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/allcases"), allCases,
            "Cases/All_Cases");
    DefaultTreeNode taskNode = buildAllCasesMenuItem(allCases);
    taskNode.setChildren(Arrays.asList(personalCaseNode, allCaseNode));
    return taskNode;
  }

  private DefaultTreeNode buildCaseTree(String nodeDisplayName, List<RemoteCase> cases, String firstCategory) {
    CaseNode caseMenuItem = new CaseNode();
    caseMenuItem.setCases(cases);
    caseMenuItem.setValue(nodeDisplayName);
    caseMenuItem.setMenuKind(MenuKind.CASE);
    DefaultTreeNode caseNode = new DefaultTreeNode(caseMenuItem);
    caseNode.setType(firstCategory);
    caseNode.setExpanded(true);
    return caseNode;
  }

  private DefaultTreeNode buildPersonalCaseMenuItem(List<RemoteCase> personalCases) {
    return buildCasesMenuItem(personalCases);
  }

  private DefaultTreeNode buildAllCasesMenuItem(List<RemoteCase> allCases) {
    return buildCasesMenuItem(allCases);
  }

  private DefaultTreeNode buildCasesMenuItem(List<RemoteCase> cases) {
    CaseNode casesMenuItem = new CaseNode();
    casesMenuItem.setCases(cases);
    casesMenuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/caseList/cases"));
    casesMenuItem.setMenuKind(MenuKind.CASE);
    casesMenuItem.setIcon("fa fa-list");
    DefaultTreeNode caseNode = new DefaultTreeNode(casesMenuItem);
    caseNode.setType("Cases");
    caseNode.setExpanded(true);
    return caseNode;
  }

  private void addTasksMenuItem(CustomLinkGeneratorData data) {
    PortalConfig portalConfig = data.getPortalConfig();
    List<RemoteTask> personalTasks = data.getPersonalTasks();
    List<RemoteTask> allTasks = data.getAllTasks();
    if (portalConfig.getHideShowTaskMenuItem() == null || portalConfig.getHideShowTaskMenuItem()) {
      return;
    }

    hasReadAllTasksPermisson = data.getHasReadAllTasksPermisson();
    DefaultTreeNode taskNode =
        hasReadAllTasksPermisson ? buildAdministratorTaskTree(personalTasks, allTasks)
            : buildPersonalTaskTree(personalTasks);
    rootNode.getChildren().add(taskNode);
  }

  private DefaultTreeNode buildPersonalTaskTree(List<RemoteTask> personalTasks) {
    DefaultTreeNode taskNode = buildTaskMenuItem(personalTasks);
    List<TreeNode> childrenNodes = TaskTreeUtils.convertTaskListToTree(personalTasks, "Tasks").getChildren();
    taskNode.setChildren(childrenNodes);
    return taskNode;
  }

  private DefaultTreeNode buildAdministratorTaskTree(List<RemoteTask> personalTasks, List<RemoteTask> allTasks) {
    DefaultTreeNode personalTaskNode =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/myTasks"), personalTasks,
            "Tasks/My_Tasks");
    DefaultTreeNode allTaskNode =
        buildTaskTree(Ivy.cms().co("/ch.ivy.addon.portalkit.ui.jsf/AllTasks/title"), allTasks, "Tasks/All_Tasks");
    DefaultTreeNode taskNode = buildTaskMenuItem(allTasks);
    taskNode.setChildren(Arrays.asList(personalTaskNode, allTaskNode));
    return taskNode;
  }

  private DefaultTreeNode buildTaskMenuItem(List<RemoteTask> tasks) {
    TaskNode tasksMenuItem = new TaskNode();
    tasksMenuItem.setValue(Ivy.cms().co("/ch.ivy.addon.portal.generic/CustomLinkGenerator/tasks"));
    tasksMenuItem.setMenuKind(MenuKind.TASK);
    tasksMenuItem.setTasks(tasks);
    tasksMenuItem.setIcon("fa fa-inbox");
    DefaultTreeNode taskNode = new DefaultTreeNode(tasksMenuItem);
    taskNode.setType("Tasks");
    taskNode.setExpanded(true);
    return taskNode;
  }

  private DefaultTreeNode buildTaskTree(String nodeDisplayName, List<RemoteTask> remoteTasks, String firstCategory) {
    TaskNode taskMenuItem = new TaskNode();
    taskMenuItem.setValue(nodeDisplayName);
    taskMenuItem.setMenuKind(MenuKind.TASK);
    taskMenuItem.setTasks(remoteTasks);
    DefaultTreeNode taskNode = new DefaultTreeNode(taskMenuItem);
    taskNode.setType(firstCategory);
    List<TreeNode> childrenNodes = TaskTreeUtils.convertTaskListToTree(remoteTasks, firstCategory).getChildren();
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
    rootNode.getChildren().add(new DefaultTreeNode(dashboardMenuItem));
  }

  private void addNewProcessMenuItem(PortalConfig portalConfig) {
    if (portalConfig.getHideCreateNewMenuItem() == null || portalConfig.getHideCreateNewMenuItem()) {
      return;
    }

    MainMenuNode newProcessMenuItem = new MainMenuNode();
    newProcessMenuItem.setValue(Ivy.cms().co("/Project/LeftMenu/process"));
    newProcessMenuItem.setMenuKind(MenuKind.PROCESS);
    newProcessMenuItem.setIcon("fa fa-magic fa-magic-o");
    rootNode.getChildren().add(new DefaultTreeNode(newProcessMenuItem));
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

  public List<RemoteTask> getTasksOfSelectedCategory(TreeNode selectedCategory, String keyword) {
    if (selectedCategory == null) {
      selectedCategory = initMyTasksNode();
    }
    List<RemoteTask> tasks = new ArrayList<>();
    updateTasks(tasks, rootNode, selectedCategory, keyword);
    tasks.sort(new TaskPriorityComparator());
    return tasks;
  }

  public TreeNode initMyTasksNode() {
    DefaultTreeNode personalTaskNode = new DefaultTreeNode();
    personalTaskNode.setType(hasReadAllTasksPermisson ? "Tasks/My_Tasks" : "Tasks");
    return personalTaskNode;
  }

  private void updateTasks(List<RemoteTask> tasks, TreeNode node, TreeNode selectedNode, String keyword) {
    if (areEqualNodes(node, selectedNode)) {
      TaskNode taskNode = (TaskNode) node.getData();
      tasks.addAll(keyword == null ? taskNode.getTasks() : new FilterService(keyword).filterTasks(taskNode.getTasks()));
    } else {
      for (TreeNode child : node.getChildren()) {
        updateTasks(tasks, child, selectedNode, keyword);
      }
    }
  }

  public List<RemoteCase> getCasesOfSelectedCategory(TreeNode selectedCategory, String keyword) {
    if (selectedCategory == null) {
      selectedCategory = initMyCasesNode();
    }
    List<RemoteCase> cases = new ArrayList<>();
    updateCases(cases, rootNode, selectedCategory, keyword);
    return cases;
  }

  public TreeNode initMyCasesNode() {
    DefaultTreeNode personalCaseNode = new DefaultTreeNode();
    personalCaseNode.setType(hasReadAllCasesPermisson ? "Cases/My_Cases" : "Cases");
    return personalCaseNode;
  }

  private void updateCases(List<RemoteCase> cases, TreeNode node, TreeNode selectedNode, String keyword) {
    if (areEqualNodes(node, selectedNode)) {
      CaseNode caseNode = (CaseNode) node.getData();
      cases.addAll(keyword == null ? caseNode.getCases() : new FilterService(keyword).filterCases(caseNode.getCases()));
    } else {
      for (TreeNode child : node.getChildren()) {
        updateCases(cases, child, selectedNode, keyword);
      }
    }
  }

  private boolean areEqualNodes(TreeNode node, TreeNode selectedNode) {
    if (node.getType().equals(selectedNode.getType())) {
      this.selectedNode = node;
      return true;
    }
    return false;
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
