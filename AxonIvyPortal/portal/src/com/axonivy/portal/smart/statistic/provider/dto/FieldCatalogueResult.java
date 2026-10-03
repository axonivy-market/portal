package com.axonivy.portal.smart.statistic.provider.dto;

import java.io.Serializable;
import java.util.List;

/** What the agent returns when asked to name and type a provider's fields. */
public class FieldCatalogueResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<DynamicFieldProposal> fields;

  public List<DynamicFieldProposal> getFields() {
    return fields;
  }

  public void setFields(List<DynamicFieldProposal> fields) {
    this.fields = fields;
  }
}
