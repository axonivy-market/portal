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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.util.TreeUtils;

import ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.category.CategoryTree;

@ManagedBean
@RequestScoped
public class CaseMenuBean implements Serializable {

  private static final long serialVersionUID = 5481218944095287656L;

  private TreeNode rootNode;
  private static final String CASE_MENU_DATA_BEAN = "#{caseMenuDataBean}";

  public TreeNode getRootNode() {
    return rootNode;
  }

  public void setRootNode(TreeNode rootNode) {
    this.rootNode = rootNode;
  }

  @PostConstruct
  public void init() {
    FacesContext context = FacesContext.getCurrentInstance();
    CaseMenuDataBean caseMenuDataBean =
        context.getApplication().evaluateExpressionGet(context, CASE_MENU_DATA_BEAN, CaseMenuDataBean.class);
    PortalCaseMenuData portalCaseMenuData = caseMenuDataBean.getPortalCaseMenuData();
    if (portalCaseMenuData != null) {
      rootNode = new DefaultTreeNode();
      addCasesMenuItem(portalCaseMenuData, "");
    }
  }

  public void initMenuBean(PortalCaseMenuData portalCaseMenuData, String menuState) {
    rootNode = new DefaultTreeNode();
    addCasesMenuItem(portalCaseMenuData, menuState);
  }

  private void addCasesMenuItem(PortalCaseMenuData portalCaseMenuData, String menuState) {
    if (PermissionUtils.checkReadAllCasesPermission()) {
      TreeNode allCaseNode = buildAllCaseTree(portalCaseMenuData.getAllCaseCategoryTree(), menuState);
      allCaseNode.setParent(rootNode);
      rootNode.getChildren().add(allCaseNode);
    }

    TreeNode myCaseNode = buildMyCaseTree(portalCaseMenuData.getMyCaseCategoryTree(), menuState);
    myCaseNode.setParent(rootNode);
    rootNode.getChildren().add(myCaseNode);
  }

  private TreeNode buildAllCaseTree(CategoryTree allCaseCategoryTree, String menuState) {
    return buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalCaseMenu/AllCases"), allCaseCategoryTree,
        TreeNodeType.CASES_ALL_CASES, menuState);
  }

  private TreeNode buildMyCaseTree(CategoryTree myCaseCategoryTree, String menuState) {
    List<Object> params = Arrays.asList(Ivy.session().getSessionUserName());
    String myCaseNodeName = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalCaseMenu/MyCases", params);
    return buildCaseTree(myCaseNodeName, myCaseCategoryTree, TreeNodeType.CASES_MY_CASES, menuState);
  }

  private TreeNode buildCaseTree(String nodeDisplayName, CategoryTree categoryTree, String firstCategory,
      String menuState) {
    CaseNode caseMenuItem = new CaseNode();
    caseMenuItem.setValue(nodeDisplayName);
    caseMenuItem.setMenuKind(MenuKind.CASE);
    boolean isRootNodeAllCase = firstCategory.equals(TreeNodeType.CASES_ALL_CASES);
    caseMenuItem.setRootNodeAllCase(isRootNodeAllCase);
    caseMenuItem.setFirstCategoryNode(true);
    
    TreeNode caseNode = new DefaultTreeNode(caseMenuItem);
    caseNode.setType(firstCategory);
    if (menuState.contains(firstCategory) && !getLastCategoryFromCategoryPath(menuState).contains(firstCategory)) {
      caseNode.setExpanded(true);
    } else {
      caseNode.setExpanded(false);
    }
    if (categoryTree != null) {
      CaseTreeUtils.convertToTreeNode(caseNode, categoryTree, isRootNodeAllCase, menuState);
      sortNode(caseNode);
    }
    
    return caseNode;
  }
  
  private static void sortNode(TreeNode node) {
    Comparator<TreeNode> comparator = (firstNode, secondNode) -> {
      CaseNode firstNodeData = (CaseNode) firstNode.getData();
      CaseNode secondNodeData = (CaseNode) secondNode.getData();
      return firstNodeData.getValue().compareToIgnoreCase(secondNodeData.getValue());
    };
    TreeUtils.sortNode(node, comparator);
  }
}
