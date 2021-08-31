package portalmigration.version93.service;

import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;
import portalmigration.enums.PortalVariable;

public class CaseFilterService extends AbstractFilterService<ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData> {

  @Override
  public Class<CaseFilterData> getType() {
    return CaseFilterData.class;
  }

  @Override
  public String getConfigKey() {
    return PortalVariable.CASE_FILTER.key;
  }
}
