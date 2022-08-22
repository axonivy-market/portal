package portalmigration.version94.service;

import ch.ivy.addon.portalkit.casefilter.impl.CaseFilterData;

public class CaseFilterService extends AbstractFilterService<CaseFilterData> {

  @Override
  public Class<CaseFilterData> getType() {
    return CaseFilterData.class;
  }
}
