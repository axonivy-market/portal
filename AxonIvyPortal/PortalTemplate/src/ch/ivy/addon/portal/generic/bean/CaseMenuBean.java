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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import ch.ivy.addon.portal.generic.PortalCaseMenu.PortalCaseMenuData;
import ch.ivy.addon.portal.generic.common.TreeNodeType;
import ch.ivy.addon.portalkit.bo.CaseNode;
import ch.ivy.addon.portalkit.enums.MenuKind;
import ch.ivy.addon.portalkit.util.CaseTreeUtils;
import ch.ivy.addon.portalkit.util.PermissionUtils;
import ch.ivy.ws.addon.CategoryData;
import ch.ivyteam.ivy.environment.Ivy;

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
      DefaultTreeNode allCaseNode = buildAllCaseTree(portalCaseMenuData.getAllCaseCategories(), menuState);
      allCaseNode.setParent(rootNode);
      rootNode.getChildren().add(allCaseNode);
    }

    DefaultTreeNode myCaseNode = buildMyCaseTree(portalCaseMenuData.getMyCaseCategories(), menuState);
    myCaseNode.setParent(rootNode);
    rootNode.getChildren().add(myCaseNode);
  }

  private DefaultTreeNode buildAllCaseTree(List<CategoryData> allCaseCategories, String menuState) {
    DefaultTreeNode allCaseNode =
        buildCaseTree(Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalCaseMenu/AllCases"), allCaseCategories,
            TreeNodeType.CASES_ALL_CASES, menuState);
    return allCaseNode;
  }

  private DefaultTreeNode buildMyCaseTree(List<CategoryData> myCaseCategories, String menuState) {
    List<Object> params = Arrays.asList(Ivy.session().getSessionUserName());
    String myCaseNodeName = Ivy.cms().co("/ch.ivy.addon.portal.generic/PortalCaseMenu/MyCases", params);
    DefaultTreeNode myCaseNode = buildCaseTree(myCaseNodeName, myCaseCategories, TreeNodeType.CASES_MY_CASES, menuState);
    return myCaseNode;
  }

  private DefaultTreeNode buildCaseTree(String nodeDisplayName, List<CategoryData> categories, String firstCategory, String menuState) {
    CaseNode caseMenuItem = new CaseNode();
    caseMenuItem.setValue(nodeDisplayName);
    caseMenuItem.setMenuKind(MenuKind.CASE);
    boolean isRootNodeAllCase = firstCategory.equals(TreeNodeType.CASES_ALL_CASES);
    caseMenuItem.setRootNodeAllCase(isRootNodeAllCase);
    caseMenuItem.setFirstCategoryNode(true);
    DefaultTreeNode caseNode = new DefaultTreeNode(caseMenuItem);
    caseNode.setType(firstCategory);
    if (menuState.contains(firstCategory) && !getLastCategoryFromCategoryPath(menuState).contains(firstCategory)) {
      caseNode.setExpanded(true);
    } else {
      caseNode.setExpanded(false);
    }
    if (validCategory(categories)) {
      List<TreeNode> childrenNodes =
          CaseTreeUtils.convertToTreeNode(categories, firstCategory, isRootNodeAllCase, menuState).getChildren();
      caseNode.setChildren(childrenNodes);
    }
    return caseNode;
  }

  private boolean validCategory(List<CategoryData> categories) {
    return !Objects.isNull(categories) && !categories.isEmpty();
  }
}
