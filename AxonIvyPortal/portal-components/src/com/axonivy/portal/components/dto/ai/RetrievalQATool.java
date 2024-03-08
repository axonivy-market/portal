package com.axonivy.portal.components.dto.ai;

import com.axonivy.portal.components.enums.ToolType;

public abstract class RetrievalQATool extends AbstractTool {

  private static final long serialVersionUID = -6486736943933821211L;

  private String collection;
  
  protected RetrievalQATool() {
    init();
  }

  public String getCollection() {
    return collection;
  }

  public void setCollection(String collection) {
    this.collection = collection;
  }

  @Override
  public ToolType getType() {
    return ToolType.RETRIEVAL_QA;
  }
}
