package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.CaseSearchCriteria;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

public class RelatedCaseLazyDataModel extends LazyDataModel<ICase> {

  private static final long serialVersionUID = 1L;

  protected final List<ICase> data;

  protected String caseWidgetComponentId;
  protected int rowIndex;
  protected CaseSearchCriteria criteria;

  public RelatedCaseLazyDataModel() {
    super();
    data = new ArrayList<>();
    caseWidgetComponentId = "related-cases-widget";
    buildCriteria();
  }

  @Override
  public List<ICase> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    if (first == 0) {
      initializedDataModel();
      criteria.setSortField(sortField);
      criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
    }
    
    List<ICase> foundCases = findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  protected void buildQueryToSearchCriteria() {
    this.criteria.setFinalCaseQuery(CaseQuery.create());
  }

  private List<ICase> findCases(CaseSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ICase>> findCaseCaller = new IvyComponentLogicCaller<>();
    int startIndex = first;
    int count = pageSize;
    if (startIndex < 0) {
      startIndex = 0;
      count = first + pageSize;
    }
    UIComponent component = findRelatedCaseComponent();
    if (component != null) {
      return findCaseCaller.invokeComponentLogic(component, "#{logic.findCases}", new Object[] {criteria, startIndex, count});
    }
    return new ArrayList<>();
  }

  private UIComponent findRelatedCaseComponent() {
    List<UIComponent> children = FacesContext.getCurrentInstance().getViewRoot().findComponent(":case-item-details:widgets:case-details-technical-case-card").getChildren();
    return children.stream().filter(child -> child.getId().equals(caseWidgetComponentId)).findFirst().orElse(null);
  }

  private void initializedDataModel() {
    data.clear();
    buildQueryToSearchCriteria();
    setRowCount(getCaseCount(criteria));
  }

  private int getCaseCount(CaseSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    UIComponent component = findRelatedCaseComponent();
    if (component != null) {
      Long caseCount = countCaseCaller.invokeComponentLogic(component, "#{logic.countCases}", new Object[] {criteria});
      return caseCount.intValue();
    }
    return 0;
  }

  private void buildCriteria() {
    criteria = new CaseSearchCriteria();
    criteria.setBusinessCase(true);
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(CaseState.CREATED, CaseState.RUNNING, CaseState.DONE)));
  }
}
