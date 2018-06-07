package ch.internalsupport;

import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;

public class CustomizedCaseLazyDataModel extends CaseLazyDataModel{

  /**
   * 
   */
  private static final long serialVersionUID = 2442877563230054720L;

  public CustomizedCaseLazyDataModel() {
    super();
  }
  
  public CustomizedCaseLazyDataModel(String caseWidgetComponentId) {
    super(caseWidgetComponentId);
  }

  @Override
  protected void initFilterContainer() {
    filterContainer = new CustomizedCaseFilterContainer();
  }
}
