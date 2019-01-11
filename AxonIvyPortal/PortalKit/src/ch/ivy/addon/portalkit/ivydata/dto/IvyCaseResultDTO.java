package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivy.addon.portalkit.ivydata.exception.PortalIvyDataException;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;

public class IvyCaseResultDTO {

  private List<ICase> cases;
  private long totalCases;
  private CategoryTree categoryTree;
  private CaseStateStatistic caseStateStatistic;
  private ElapsedTimeStatistic elapsedTimeStatistic;
  private List<String> customVarChars;
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

  public CaseStateStatistic getCaseStateStatistic() {
    return caseStateStatistic;
  }

  public void setCaseStateStatistic(CaseStateStatistic caseStateStatistic) {
    this.caseStateStatistic = caseStateStatistic;
  }

  public ElapsedTimeStatistic getElapsedTimeStatistic() {
    return elapsedTimeStatistic;
  }

  public void setElapsedTimeStatistic(ElapsedTimeStatistic elapsedTimeStatistic) {
    this.elapsedTimeStatistic = elapsedTimeStatistic;
  }

  public List<String> getCustomVarChars() {
    return customVarChars;
  }

  public void setCustomVarChars(List<String> customVarChars) {
    this.customVarChars = customVarChars;
  }

  public List<PortalIvyDataException> getErrors() {
    return errors;
  }

  public void setErrors(List<PortalIvyDataException> errors) {
    this.errors = errors;
  }

}
