package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.List;

public abstract class CaseFilterContainer {
	protected List<CaseFilter> filters = new ArrayList<>();
	protected CaseStateFilter stateFilter = new CaseStateFilter();

	public CaseFilterContainer() {
		filters.add(stateFilter);
	}

	public List<CaseFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<CaseFilter> filters) {
		this.filters = filters;
	}

  public CaseStateFilter getStateFilter() {
		return stateFilter;
	}

	public void setStateFilter(CaseStateFilter stateFilter) {
		this.stateFilter = stateFilter;
	}
}
