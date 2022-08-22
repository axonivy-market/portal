package ch.ivy.addon.portalkit.casefilter;

import ch.ivy.addon.portalkit.casefilter.impl.CaseNameFilter;
import ch.ivy.addon.portalkit.filter.AbstractFilter;
import ch.ivyteam.ivy.workflow.query.CaseQuery;

/**
 * Extend this class for custom case filter for example case name, case owner..
 * Refer to {@link CaseNameFilter} for sample.
 *
 */
public abstract class CaseFilter extends AbstractFilter<CaseQuery> {
}
