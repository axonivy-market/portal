package ch.ivy.addon.portalkit.datamodel.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.model.SortOrder;

import ch.ivy.addon.portalkit.bean.IvyComponentLogicCaller;
import ch.ivy.addon.portalkit.datamodel.TaskLazyDataModel;
import ch.ivy.addon.portalkit.enums.TaskSortField;
import ch.ivy.addon.portalkit.ivydata.searchcriteria.TaskSearchCriteria;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;

public class RelatedTaskLazyDataModel extends TaskLazyDataModel {

  private static final long serialVersionUID = 1L;

  public RelatedTaskLazyDataModel() {
    super();
    data = new ArrayList<>();
    taskWidgetComponentId = "task-widget";
    buildCriteria();
  }

  @Override
  public List<ITask> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
    criteria.setSortField(sortField);
    criteria.setSortDescending(sortOrder == SortOrder.DESCENDING);
    
    if (first == 0) {
      initializedDataModel();
    }
    
    List<ITask> foundCases = findCases(criteria, first, pageSize);
    data.addAll(foundCases);
    return foundCases;
  }

  private List<ITask> findCases(TaskSearchCriteria criteria, int first, int pageSize) {
    IvyComponentLogicCaller<List<ITask>> findCaseCaller = new IvyComponentLogicCaller<>();
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
    return children.stream().filter(child -> child.getId().equals(taskWidgetComponentId)).findFirst().orElse(null);
  }

  private void initializedDataModel() {
    data.clear();
    setRowCount(getCaseCount(criteria));
    criteria.setFinalTaskQuery(null);
  }

  private int getCaseCount(TaskSearchCriteria criteria) {
    IvyComponentLogicCaller<Long> countCaseCaller = new IvyComponentLogicCaller<>();
    UIComponent component = findRelatedCaseComponent();
    if (component != null) {
      Long caseCount = countCaseCaller.invokeComponentLogic(component, "#{logic.countCases}", new Object[] {criteria});
      return caseCount.intValue();
    }
    return 0;
  }
  
  @Override
  protected void buildCriteria() {
    criteria = new TaskSearchCriteria();
    criteria.setIncludedStates(new ArrayList<>(Arrays.asList(TaskState.CREATED, TaskState.RESUMED, TaskState.DONE)));
    criteria.setSortField(TaskSortField.NAME.toString());
    criteria.setSortDescending(false);
  }
  
  
}
