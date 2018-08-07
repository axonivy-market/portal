package ch.ivy.addon.portalkit.singleapp.cases.CaseTable;

/**
 */
@SuppressWarnings("all")
@javax.annotation.Generated(comments="This is the java file of the ivy data class CaseTableData", value={"ch.ivyteam.ivy.scripting.streamInOut.IvyScriptJavaClassBuilder"})
public class CaseTableData extends ch.ivyteam.ivy.scripting.objects.CompositeObject
{
  /** SerialVersionUID */
  private static final long serialVersionUID = -627716566638540061L;

  private java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> cases;

  /**
   * Gets the field cases.
   * @return the value of the field cases; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> getCases()
  {
    return cases;
  }

  /**
   * Sets the field cases.
   * @param _cases the new value of the field cases.
   */
  public void setCases(java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> _cases)
  {
    cases = _cases;
  }

  private java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> filteredCases;

  /**
   * Gets the field filteredCases.
   * @return the value of the field filteredCases; may be null.
   */
  public java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> getFilteredCases()
  {
    return filteredCases;
  }

  /**
   * Sets the field filteredCases.
   * @param _filteredCases the new value of the field filteredCases.
   */
  public void setFilteredCases(java.util.List<ch.ivy.addon.portalkit.vo.CaseVO> _filteredCases)
  {
    filteredCases = _filteredCases;
  }

  private ch.ivy.addon.portalkit.vo.CaseVO selectedCase;

  /**
   * Gets the field selectedCase.
   * @return the value of the field selectedCase; may be null.
   */
  public ch.ivy.addon.portalkit.vo.CaseVO getSelectedCase()
  {
    return selectedCase;
  }

  /**
   * Sets the field selectedCase.
   * @param _selectedCase the new value of the field selectedCase.
   */
  public void setSelectedCase(ch.ivy.addon.portalkit.vo.CaseVO _selectedCase)
  {
    selectedCase = _selectedCase;
  }

  private java.lang.String processCategoryCode;

  /**
   * Gets the field processCategoryCode.
   * @return the value of the field processCategoryCode; may be null.
   */
  public java.lang.String getProcessCategoryCode()
  {
    return processCategoryCode;
  }

  /**
   * Sets the field processCategoryCode.
   * @param _processCategoryCode the new value of the field processCategoryCode.
   */
  public void setProcessCategoryCode(java.lang.String _processCategoryCode)
  {
    processCategoryCode = _processCategoryCode;
  }

  private java.util.List<ch.ivyteam.ivy.workflow.ICase> iCases;

  /**
   * Gets the field iCases.
   * @return the value of the field iCases; may be null.
   */
  public java.util.List<ch.ivyteam.ivy.workflow.ICase> getICases()
  {
    return iCases;
  }

  /**
   * Sets the field iCases.
   * @param _iCases the new value of the field iCases.
   */
  public void setICases(java.util.List<ch.ivyteam.ivy.workflow.ICase> _iCases)
  {
    iCases = _iCases;
  }

  private ch.ivyteam.ivy.workflow.IPropertyFilter propertyFilter;

  /**
   * Gets the field propertyFilter.
   * @return the value of the field propertyFilter; may be null.
   */
  public ch.ivyteam.ivy.workflow.IPropertyFilter getPropertyFilter()
  {
    return propertyFilter;
  }

  /**
   * Sets the field propertyFilter.
   * @param _propertyFilter the new value of the field propertyFilter.
   */
  public void setPropertyFilter(ch.ivyteam.ivy.workflow.IPropertyFilter _propertyFilter)
  {
    propertyFilter = _propertyFilter;
  }

}
