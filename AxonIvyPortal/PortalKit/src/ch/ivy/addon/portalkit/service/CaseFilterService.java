package ch.ivy.addon.portalkit.service;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.casefilter.CaseFilter;
import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import ch.ivy.addon.portalkit.datamodel.CaseLazyDataModel;
import ch.ivy.addon.portalkit.enums.JsonVariable;

public class CaseFilterService extends AbstractFilterService<CaseFilterData> {

  @Override
  public Class<CaseFilterData> getType() {
    return CaseFilterData.class;
  }

  public void applyFilter(CaseLazyDataModel dataModel, CaseFilterData savedFilterData) throws ReflectiveOperationException {
    List<CaseFilter> filters = dataModel.getFilterContainer().getFilters();
    dataModel.setSelectedFilters(new ArrayList<>());
    for (int i = 0; i < filters.size(); i++) {
      CaseFilter filter = dataModel.getFilterContainer().getFilters().get(i);
      for (int j = 0; j < savedFilterData.getFilters().size(); j++) {
        CaseFilter savedFilter = savedFilterData.getFilters().get(j);
        if (filter.getClass().equals(savedFilter.getClass())) {
           copyFilterValues(filter, savedFilter);
          dataModel.getSelectedFilters().add(filter);
          break;
        }
      }
    }
  }

  @Override
  public String getConfigKey() {
    return JsonVariable.CASE_FILTER.key;
  }
}
