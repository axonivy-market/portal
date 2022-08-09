package ch.ivy.addon.portalkit.dto.dashboard.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.CheckboxTreeNode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import ch.ivy.addon.portalkit.bo.CategoryNode;
import ch.ivy.addon.portalkit.enums.ProcessType;
import ch.ivy.addon.portalkit.util.CategoryUtils;
import ch.ivy.addon.portalkit.util.ProcessTreeUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardProcessUserFilter implements Serializable {

  private static final long serialVersionUID = 8951771185949809098L;

  private List<ProcessType> processTypes = new ArrayList<>();
  @JsonIgnore
  private List<ProcessType> allProcessTypes;

  private String processName;
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode> categoryTree;
  @JsonIgnore
  private CheckboxTreeNode<CategoryNode>[] categoryNodes;

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
    this.processTypes = new ArrayList<>();
    this.processName = "";
  }

  public CheckboxTreeNode<CategoryNode>[] getCategoryNodes() {
    return categoryNodes;
  }

  public void setCategoryNodes(CheckboxTreeNode<CategoryNode>[] categoryNodes) {
    this.categoryNodes= categoryNodes;
  }

  public CheckboxTreeNode<CategoryNode> getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CheckboxTreeNode<CategoryNode> categoryTree) {
    this.categoryTree = categoryTree;
  }

  @JsonIgnore
  public void buildCategoryTree(List<DashboardProcess> processes, List<String> categories) {
    categoryTree = ProcessTreeUtils.buildProcessCategoryCheckboxTreeRoot(processes);
    CategoryUtils.disableSelectionWithoutSelectingExcept(categoryTree, categories);
    categoryNodes = CategoryUtils.recoverSelectedCategories(categoryTree, this.categories);
  }
  
  @JsonIgnore
  public String getDisplayCategories() {
    return CollectionUtils.isNotEmpty(categories) ? CategoryUtils.getNodeValue(categories) : "";
  }

  @JsonIgnore
  public void selectCategoriesToFilterProcesses() {
    categories = CategoryUtils.getCategoryPaths(categoryNodes);
  }

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
    this.categories = categories;
  }
}
