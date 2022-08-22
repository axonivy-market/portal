package ch.ivy.addon.portalkit.casefilter;

import java.util.ArrayList;
import java.util.List;

import ch.ivy.addon.portalkit.casefilter.impl.CaseStateFilter;
import ch.ivy.addon.portalkit.casefilter.impl.DefaultCaseFilterContainer;

/**
 * Extend this class for custom case filter container.
 * This container contains all your custom filter. Refer to {@link DefaultCaseFilterContainer} for sample.
 *
 */
public abstract class CaseFilterContainer {
	protected List<CaseFilter> filters = new ArrayList<>();
	protected CaseStateFilter stateFilter = new CaseStateFilter();

	/**
	 * Initialize list of filters here.
	 * Default there is case state filter.
	 */
	public CaseFilterContainer() {
		filters.add(stateFilter);
	}

	/**
	 * Getter of all filters
	 * @return all filters
	 */
	public List<CaseFilter> getFilters() {
		return filters;
	}

	/**
	 * Setter of all filters
	 * @param filters all filters
	 */
	public void setFilters(List<CaseFilter> filters) {
		this.filters = filters;
	}

	/**
	 * Getter for default case state filter
	 * @return case state filter
	 */
  public CaseStateFilter getStateFilter() {
		return stateFilter;
	}

  /**
   * Setter of default case state filter
   * @param stateFilter case state filter
   */
	public void setStateFilter(CaseStateFilter stateFilter) {
		this.stateFilter = stateFilter;
	}
}
