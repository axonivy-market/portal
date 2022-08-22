package ch.ivy.addon.portalkit.ivydata.dto;

import java.util.List;

import ch.ivy.addon.portalkit.bo.CaseCategoryStatistic;
import ch.ivy.addon.portalkit.bo.CaseStateStatistic;
import ch.ivy.addon.portalkit.bo.ElapsedTimeStatistic;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.category.CategoryTree;

public class IvyCaseResultDTO extends AbstractResultDTO {

  private List<ICase> cases;
  private long totalCases;
  private CategoryTree categoryTree;
  private CaseStateStatistic caseStateStatistic;
  private CaseCategoryStatistic caseCategoryStatistic;
  private ElapsedTimeStatistic elapsedTimeStatistic;
  private List<String> customFields;

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

  public List<String> getCustomFields() {
    return customFields;
  }

  public void setCustomFields(List<String> customFields) {
    this.customFields = customFields;
  }

  public CaseCategoryStatistic getCaseCategoryStatistic() {
    return caseCategoryStatistic;
  }

  public void setCaseCategoryStatistic(CaseCategoryStatistic caseCategoryStatistic) {
    this.caseCategoryStatistic = caseCategoryStatistic;
  }
}
