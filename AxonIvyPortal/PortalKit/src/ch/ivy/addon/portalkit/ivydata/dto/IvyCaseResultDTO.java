package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;

public class IvyCaseResultDTO {

  private List<ICase> cases;
  private long totalCases;
  private CategoryTree categoryTree;
  private List<PortalIvyDataException> errors;

  public List<ICase> getCases() {
    return cases;
  }

  public void setCases(List<ICase> cases) {
    this.cases = cases;
  }

  public long getTotalCases() {
    return totalCases;
  }

  public void setTotalCases(long totalCases) {
    this.totalCases = totalCases;
  }
  
  public CategoryTree getCategoryTree() {
    return categoryTree;
  }

  public void setCategoryTree(CategoryTree categoryTree) {
    this.categoryTree = categoryTree;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
