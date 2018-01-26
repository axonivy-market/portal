package ch.ivy.addon.portalkit.bo;

import java.io.Serializable;

/**
 * Data class for TreeNode of Case Tree.
 */
public class CaseNode extends MainMenuNode implements Serializable {

  private static final long serialVersionUID = -7458740651177437962L;

  private boolean isRootNodeAllCase;
  private String category;
  private String categoryRawPath;
  private boolean isFirstCategoryNode;

  public CaseNode() {}

  public boolean isRootNodeAllCase() {
    return isRootNodeAllCase;
  }

  public void setRootNodeAllCase(boolean isRootNodeAllCase) {
    this.isRootNodeAllCase = isRootNodeAllCase;
  }


  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCategoryRawPath() {
    return categoryRawPath;
  }

  public void setCategoryRawPath(String categoryRawPath) {
    this.categoryRawPath = categoryRawPath;
  }

  public boolean isFirstCategoryNode() {
    return isFirstCategoryNode;
  }

  public void setFirstCategoryNode(boolean isFirstCategoryNode) {
    this.isFirstCategoryNode = isFirstCategoryNode;
  }
}
