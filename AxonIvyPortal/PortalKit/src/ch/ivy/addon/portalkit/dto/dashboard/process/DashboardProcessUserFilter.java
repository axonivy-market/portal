package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;

import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.ProcessTreeUtils;

public class DashboardProcessUserFilter {
  private List<ProcessType> processTypes = new ArrayList<>();
  private List<ProcessType> allProcessTypes;
  private String processName;
  private CheckboxTreeNode categoryTree;
  private CheckboxTreeNode[] categoryNodes;
  private List<String> categories;

  public List<ProcessType> getProcessTypes() {
    return processTypes;
  }

  public void setProcessTypes(List<ProcessType> processTypes) {
    this.processTypes = processTypes;
  }

  public List<ProcessType> getAllProcessTypes() {
    return CollectionUtils.isEmpty(this.allProcessTypes) ? Arrays.asList(ProcessType.values()) : this.allProcessTypes;
  }

  public void setAllProcessTypes(List<ProcessType> allProcessTypes) {
    this.allProcessTypes = allProcessTypes;
  }

  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public DashboardProcessUserFilter() {
    super();
    this.processTypes = new ArrayList<>();
    this.processName = "";
  }

  public CheckboxTreeNode[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode[] categoryNodes) {
    this.categoryNodes= categoryNodes;
  }

  public CheckboxTreeNode getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CheckboxTreeNode categoryTree) {
    this.categoryTree = categoryTree;
  }

  public void buildCategoryTree(List<DashboardProcess> processes, List<String> categories) {
    categoryTree = ProcessTreeUtils.buildProcessCategoryCheckboxTreeRoot(processes);
    CategoryUtils.disableSelectionWithoutSelectingExcept(categoryTree, categories);
    categoryNodes = CategoryUtils.recoverSelectedCategories(categoryTree, this.categories);
  }
  
  public String getDisplayCategories() {
    return CollectionUtils.isNotEmpty(categories) ? CategoryUtils.getNodeValue(categories) : "";
  }

  public List<String> getCategories() {
    return categories;
  }
  public void selectCategoriesToFilterProcesses() {
    categories = CategoryUtils.getCategoryPaths(categoryNodes);
  }
  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
}
